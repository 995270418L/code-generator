package com.steve.enums.type;
import com.steve.enums.BaseEnumInterface;
import java.util.Collections;
import java.util.Map;
import java.util.HashMap;
/**
 * create by steve
 * blog: https://www.jianshu.com/u/c8df39415ca7
 */
public enum PackageType implements BaseEnumInterface {

    TEST(1, "test"); // Template code, it should be replace the true code

    private static Map<Integer, PackageType> _MAP = new HashMap();

    static {
        for (PackageType type : PackageType.values()) {
            _MAP.put(type.getValue(), type);
        }
        _MAP = Collections.unmodifiableMap(_MAP);
    }

    private int value;
    private String name;

    PackageType(int value, String name) {
        this.value = value;
        this.name = name;
    }

    public static PackageType get(Integer value) {
        return _MAP.get(value);
    }

    public String getName() {
        return name;
    }

    public int getValue() {
        return value;
    }

}
