package com.steve.enums.serializer;

import com.steve.enums.type.PackageType;
import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.SerializerProvider;

import java.io.IOException;

/**
 * create by steve
 * blog: https://www.jianshu.com/u/c8df39415ca7
 */
public class PackageTypeJsonSerializer extends JsonSerializer<PackageType> {

    @Override
    public void serialize(PackageType baseType, JsonGenerator gen, SerializerProvider serializers) throws IOException {
        gen.writeRawValue(String.valueOf(baseType.getValue()));
        gen.writeFieldName(gen.getOutputContext().getCurrentName() + "_name");
        gen.writeRawValue("\"" + baseType.getName() + "\"");
    }

}
