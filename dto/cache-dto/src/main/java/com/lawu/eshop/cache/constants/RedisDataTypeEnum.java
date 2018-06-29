package com.lawu.eshop.cache.constants;

public enum RedisDataTypeEnum {

    VALUE("value"),
    LIST("list"),
    MAP("map"),;

    private String name;

    public static RedisDataTypeEnum get(String name){
        RedisDataTypeEnum[] values = RedisDataTypeEnum.values();
        for (RedisDataTypeEnum object : values) {
            if (object.name.equals(name)) {
                return object;
            }
        }
        return null;
    }

    RedisDataTypeEnum(String name) {
        this.name = name;
    }
    public String getName() {
        return name;
    }
}
