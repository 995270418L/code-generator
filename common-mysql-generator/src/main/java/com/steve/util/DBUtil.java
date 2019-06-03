package com.steve.util;

import com.steve.bean.Column;
import com.steve.bean.Table;
import com.steve.bootstrap.MakerInsertSelect;
import com.sun.org.apache.bcel.internal.classfile.Code;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.sql.*;
import java.util.*;

public class DBUtil {

    public static final String TYPE_SUFFIX_MARK = "_type";
    private static final String TYPE_BASE_PREFIX = "com.steve.enums";
    private static final String TYPE_HANDLER_PREFIX = "com.steve.mysql";
    public static final String TYPE_PATH_PREFIX = TYPE_BASE_PREFIX + ".type.";
    public static final String TYPE_DESERIALIZER_PATH = TYPE_BASE_PREFIX + ".deserializer.";
    public static final String TYPE_SERIALIZER_PATH = TYPE_BASE_PREFIX + ".serializer.";
    public static final String TYPE_HANDLER_PATH = TYPE_HANDLER_PREFIX + ".type.";

    public static final String TYPE_FILE_MODULE = "common-enums" + "\\src\\main\\java";
    public static final String TYPE_HANDLER_MODULE = "common-mysql" + "\\src\\main\\java";

    public static Properties config;
    public static Connection conn;

    static {
        try {
            config = new Properties();
            String configFile = "config.properties";
            InputStream is = null;
            try {
                is = DBUtil.class.getClassLoader().getResourceAsStream(configFile);
                config.load(is);
            } catch (FileNotFoundException e) {
                FileOutputStream fos = new FileOutputStream(DBUtil.class.getClass().getResource(configFile).getFile());
                setDefaultValue();
                config.store(fos, null);
                if (fos != null) {
                    fos.close();
                }
                System.exit(1);
            } finally {
                if (is != null) {
                    is.close();
                }
            }
            initProperties();
            Class.forName(config.getProperty("mysql.className"));
            conn = DriverManager.getConnection(
                    config.getProperty("mysql.url"),
                    config.getProperty("mysql.user"),
                    config.getProperty("mysql.password"));
            if (conn.isClosed()) {
                System.out.println("closed connection");
            } else {
                System.out.println("open connection");
            }
        } catch (Exception e) {
            e.printStackTrace();
            System.exit(1);
        }
    }

    private static void setDefaultValue() {
        config.setProperty("mysql.className", "com.mysql.jdbc.Driver");
        config.setProperty("mysql.url", "jdbc:mysql://localhost:3306/test");
        config.setProperty("mysql.user", "root");
        config.setProperty("mysql.password", "root");
        //jdbcType-JavaType
        config.setProperty("bigint", "Long");
        config.setProperty("mediumint", "Integer");
        config.setProperty("integer", "Integer");
        config.setProperty("smallint", "Integer");
        config.setProperty("tinyint", "Integer");
        config.setProperty("int", "Integer");
        config.setProperty("char", "String");
        config.setProperty("varchar", "String");
        config.setProperty("text", "String");
        config.setProperty("mediumtext", "String");
        config.setProperty("float", "Float");
        config.setProperty("double", "Double");
        config.setProperty("mediumtext", "String");
        config.setProperty("date", "java.util.Date");
        config.setProperty("timestamp", "java.util.Date");
        config.setProperty("datetime", "java.util.Date");
        config.setProperty("longtext", "String");
    }

    private static void initProperties() {
        String url = config.getProperty("mysql.url");
        int index = url.lastIndexOf("/") + 1;
        int last = url.lastIndexOf("?");
        last = (last == -1) ? url.length() : last;
        String schema = url.substring(index, last);
        config.setProperty("mysql.schema", schema);
    }

    public static Table getTable(String tableName) {
        Table table = null;
        String sql = "select COLUMN_NAME,DATA_TYPE,COLUMN_KEY,COLUMN_COMMENT " +
                "from information_schema.COLUMNS " +
                "where TABLE_SCHEMA=? " +
                "and TABLE_NAME=?";
        try {
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setString(1, config.getProperty("mysql.schema"));
            ps.setString(2, tableName);
            ResultSet rs = ps.executeQuery();

            Set<String> dataTypeSet = new HashSet<String>();
            Column columnKey = null;
            Column columnKeyCollection = null;
            List<Column> columnList = new ArrayList<Column>();
            while (rs.next()) {

                String columnName = rs.getString("COLUMN_NAME");
                String propertyName = getVariable(columnName);
                String columnDataType = rs.getString("DATA_TYPE");
                String propertyDataType = config.getProperty(columnDataType);
                String typeHandler = null;
                /**
                 * 所有以 _type 结尾的数据库字段都会自动生成一个 typeHandler 类处理, 生成的type enums 类需要自己修改其中的 enum 字段
                 */
                if(columnName.endsWith("_type")){
                    // 生成 type 文件
                    Map<String, Object> data = new HashMap<>();
                    String tmpPropertyName = CodeUtil.getUpperCaseVariable(propertyName);
                    data.put("typeName",tmpPropertyName);
                    data.put("typePath",TYPE_PATH_PREFIX+ tmpPropertyName);
                    data.put("typeNameValues", tmpPropertyName+".values()");
                    data.put("typeHandlerName", tmpPropertyName+"Handler");
                    data.put("typeJsonDeserializeName", tmpPropertyName+"JsonDeserializer");
                    data.put("typeJsonSerializerName", tmpPropertyName+"JsonSerializer");

                    MakerInsertSelect.makeFile(data,"type.vm", TYPE_FILE_MODULE,
                            CodeUtil.convertClassNameToPath(TYPE_PATH_PREFIX + tmpPropertyName, "java"));
                    MakerInsertSelect.makeFile(data,"typeHandler.vm", TYPE_HANDLER_MODULE,
                            CodeUtil.convertClassNameToPath(TYPE_HANDLER_PATH + tmpPropertyName + "Handler", "java"));
                    MakerInsertSelect.makeFile(data,"typeJsonDeserializer.vm", TYPE_FILE_MODULE,
                            CodeUtil.convertClassNameToPath(TYPE_DESERIALIZER_PATH + tmpPropertyName + "JsonDeserializer", "java"));
                    MakerInsertSelect.makeFile(data,"typeJsonSerializer.vm", TYPE_FILE_MODULE,
                            CodeUtil.convertClassNameToPath(TYPE_SERIALIZER_PATH + tmpPropertyName + "JsonSerializer", "java"));
                    propertyDataType = TYPE_PATH_PREFIX + tmpPropertyName;
                    typeHandler = TYPE_HANDLER_PATH + tmpPropertyName + "Handler";
                }
                String columnComment = rs.getString("COLUMN_COMMENT");
                propertyDataType = propertyDataType.trim();
                int index = propertyDataType.lastIndexOf(".");
                if (index != -1) {
                    dataTypeSet.add(propertyDataType);
                    propertyDataType = propertyDataType.substring(index + 1);
                }
                Column column = new Column(
                        columnName,
                        columnDataType,
                        columnComment,
                        propertyName,
                        propertyDataType,
                        typeHandler,
                        null);
                if (columnKey == null
                        && "PRI".equalsIgnoreCase(rs.getString("COLUMN_KEY"))
                        /*&& "auto_increment".equalsIgnoreCase(rs.getString("EXTRA"))*/) {
                    columnKey = column;
                    columnKeyCollection = (Column) column.clone();
                    columnKeyCollection.setPropertyNameCollection(columnKeyCollection.getPropertyName() + "Collection");
                } else {
                    columnList.add(column);
                }
            }
            /*if (columnKey == null) {
                throw new SQLException("columnKey is null");
            }*/
            if (columnList.isEmpty()) {
                throw new SQLException("columnList is empty");
            }
            if (dataTypeSet.isEmpty()) {
                dataTypeSet = null;
            }
            table = new Table(columnKey, columnKeyCollection, columnList, dataTypeSet);
        } catch (SQLException e) {
            e.printStackTrace();
            System.exit(1);
        }
        return table;
    }

    public static String getVariable(String columnName) {
        StringBuilder sb = new StringBuilder(columnName.toLowerCase());
        int fromIndex = 0;
        int index = -1;
        while ((index = sb.indexOf("_", fromIndex)) != -1) {
            sb.replace(index, index + 2, sb.substring(index + 1, index + 2).toUpperCase());
            fromIndex = index + 1;
        }
        return sb.toString();
    }
}
