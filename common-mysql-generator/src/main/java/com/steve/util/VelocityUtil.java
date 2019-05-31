package com.steve.util;

import org.apache.velocity.Template;
import org.apache.velocity.VelocityContext;
import org.apache.velocity.app.VelocityEngine;
import org.apache.velocity.runtime.RuntimeConstants;
import org.apache.velocity.runtime.resource.loader.ClasspathResourceLoader;

import java.io.Writer;
import java.util.Map;

public class VelocityUtil {

    private static final String CLASSPATH_RESOURCE_LOADER_CLASS_PATH = "classpath.resource.loader.class";
    private static VelocityEngine velocity;

    public static void initVelocityEngine() throws Exception {
        velocity = new VelocityEngine();
        velocity.setProperty(RuntimeConstants.RESOURCE_LOADER, "classpath");
        velocity.setProperty(CLASSPATH_RESOURCE_LOADER_CLASS_PATH, ClasspathResourceLoader.class.getName());
        velocity.init();
    }

    public static void merge(Map<String, Object> data, String resource, Writer writer, String encoding) throws Exception {
        resource = "templates/" + resource;
        Template temp = velocity.getTemplate(resource, encoding);
        temp.merge(new VelocityContext(data), writer);
    }

}
