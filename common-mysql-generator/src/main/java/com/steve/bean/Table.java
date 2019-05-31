package com.steve.bean;

import lombok.Data;

import java.util.List;
import java.util.Set;


/**
 * @author pst
 */
@Data
public class Table {

    private Column columnKey;
    private Column columnKeyCollection;
    private List<Column> columnList;
    private Set<String> dataTypeSet;

    public Table() {
        super();
    }

    public Table(Column columnKey,Column columnKeyCollection,  List<Column> columnList, Set<String> dataTypeSet) {
        super();
        this.columnKey = columnKey;
        this.columnKeyCollection = columnKeyCollection;
        this.columnList = columnList;
        this.dataTypeSet = dataTypeSet;
    }

    public Column getColumnKey() {
        return this.columnKey;
    }

    public Column getColumnKeyCollection() {
        return this.columnKeyCollection;
    }

    public List<Column> getColumnList() {
        return this.columnList;
    }

    public Set<String> getDataTypeSet() {
        return this.dataTypeSet;
    }

    public void setColumnKey(Column columnKey) {
        this.columnKey = columnKey;
    }

    public void setColumnKeyCollection(Column columnKeyCollection) {
        this.columnKeyCollection = columnKeyCollection;
    }

    public void setColumnList(List<Column> columnList) {
        this.columnList = columnList;
    }

    public void setDataTypeSet(Set<String> dataTypeSet) {
        this.dataTypeSet = dataTypeSet;
    }

    public boolean equals(final Object o) {
        if (o == this) return true;
        if (!(o instanceof Table)) return false;
        final Table other = (Table) o;
        if (!other.canEqual((Object) this)) return false;
        final Object this$columnKey = this.getColumnKey();
        final Object other$columnKey = other.getColumnKey();
        if (this$columnKey == null ? other$columnKey != null : !this$columnKey.equals(other$columnKey)) return false;
        final Object this$columnKeyCollection = this.getColumnKeyCollection();
        final Object other$columnKeyCollection = other.getColumnKeyCollection();
        if (this$columnKeyCollection == null ? other$columnKeyCollection != null : !this$columnKeyCollection.equals(other$columnKeyCollection))
            return false;
        final Object this$columnList = this.getColumnList();
        final Object other$columnList = other.getColumnList();
        if (this$columnList == null ? other$columnList != null : !this$columnList.equals(other$columnList))
            return false;
        final Object this$dataTypeSet = this.getDataTypeSet();
        final Object other$dataTypeSet = other.getDataTypeSet();
        if (this$dataTypeSet == null ? other$dataTypeSet != null : !this$dataTypeSet.equals(other$dataTypeSet))
            return false;
        return true;
    }

    protected boolean canEqual(final Object other) {
        return other instanceof Table;
    }

    public int hashCode() {
        final int PRIME = 59;
        int result = 1;
        final Object $columnKey = this.getColumnKey();
        result = result * PRIME + ($columnKey == null ? 43 : $columnKey.hashCode());
        final Object $columnKeyCollection = this.getColumnKeyCollection();
        result = result * PRIME + ($columnKeyCollection == null ? 43 : $columnKeyCollection.hashCode());
        final Object $columnList = this.getColumnList();
        result = result * PRIME + ($columnList == null ? 43 : $columnList.hashCode());
        final Object $dataTypeSet = this.getDataTypeSet();
        result = result * PRIME + ($dataTypeSet == null ? 43 : $dataTypeSet.hashCode());
        return result;
    }

    public String toString() {
        return "Table(columnKey=" + this.getColumnKey() + ", columnKeyCollection=" + this.getColumnKeyCollection() + ", columnList=" + this.getColumnList() + ", dataTypeSet=" + this.getDataTypeSet() + ")";
    }
}
