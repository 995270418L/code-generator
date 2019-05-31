package com.steve.enums.deserializer;

import com.steve.enums.type.PackageType;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonDeserializer;

import java.io.IOException;

/**
 * create by steve
 * blog: https://www.jianshu.com/u/c8df39415ca7
 */
public class PackageTypeJsonDeserializer extends JsonDeserializer<PackageType> {

    @Override
    public PackageType deserialize(JsonParser p, DeserializationContext ctxt) throws IOException {
        Integer value = p.readValueAs(Integer.class);
        return PackageType
                    .get(value);
    }

}