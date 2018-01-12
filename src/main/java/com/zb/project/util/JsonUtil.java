package com.zb.project.util;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.core.Version;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.JavaType;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.PropertyNamingStrategy;
import com.fasterxml.jackson.databind.module.SimpleModule;
import com.fasterxml.jackson.databind.ser.std.ToStringSerializer;
import com.sun.corba.se.spi.ior.ObjectId;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.*;

/**
 * @author Hover Ruan
 */
public class JsonUtil {
    private static final Logger log = LoggerFactory.getLogger("json-util");
    private static ObjectMapper mapper = new ObjectMapper();

    static {
        mapper.setPropertyNamingStrategy(new PropertyNamingStrategy.PropertyNamingStrategyBase() {
            public String translate(String propertyName) {
                return this.convertName(propertyName);
            }

            String convertName(String defaultName) {
                StringBuilder buf = new StringBuilder();
                char[] var6;
                int var5 = (var6 = defaultName.toCharArray()).length;

                for (int var4 = 0; var4 < var5; ++var4) {
                    char ch = var6[var4];
                    if (Character.isUpperCase(ch)) {
                        buf.append('_').append(Character.toLowerCase(ch));
                    } else {
                        buf.append(ch);
                    }
                }
                return buf.toString();
            }
        });

        SimpleModule module = new SimpleModule("lt", new Version(1, 0, 0, null));
        module.addSerializer(ObjectId.class, new ToStringSerializer());
        //设置输入时忽略JSON字符串中存在而Java对象实际没有的属性
        mapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
        // 设置null字段不返回该属性
        mapper.setSerializationInclusion(JsonInclude.Include.NON_NULL);
        mapper.registerModule(module);
    }

    public static ObjectMapper getMapper() {
        return mapper;
    }

    public static ResponseEntity<String> renderJsonStringResponse(String content) {
        return renderJsonStringResponse(content, HttpStatus.OK);
    }

    public static ResponseEntity<String> renderJsonResponse(Object model, HttpStatus status, boolean rn) {
        String content = parse(model);

        if (content != null) {
            HttpHeaders headers = new HttpHeaders();
            headers.add("Content-Type", "application/json; charset=utf-8");

            if (rn && (!(content.endsWith("\r") || content.endsWith("\n")))) {
                content = content + "\r\n";
            }

            return new ResponseEntity<>(content, headers, status);
        } else {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    public static ResponseEntity<String> renderJsonStringResponse(String content, HttpStatus status) {
        HttpHeaders headers = new HttpHeaders();
        headers.add("Content-Type", "application/json; charset=utf-8");

        if (!(content.endsWith("\r") || content.endsWith("\n"))) {
            content = content + "\r\n";
        }

        return new ResponseEntity<String>(content, headers, status);
    }

    public static ResponseEntity<String> renderJsonResponse(Object model) {
        return renderJsonResponse(model, HttpStatus.OK);
    }

    public static ResponseEntity<String> renderJsonResponse(Object model, HttpStatus status) {
        String content = parse(model);

        if (content != null) {
            return renderJsonStringResponse(content, status);
        } else {
            return new ResponseEntity<String>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    public static String parse(Object model) {
        try {
            return mapper.writeValueAsString(model);
        } catch (Exception e) {
            log.warn("parse failed: " + model, e);
            return null;
        }
    }

    public static HashMap readHashMap(String string) {
        try {
            return mapper.readValue(string, HashMap.class);
        } catch (Exception e) {
            log.warn("Failed readHashMap: " + string);
            return new HashMap();
        }
    }

    public static LinkedHashMap readLinkedHashMap(String string) {
        try {
            return mapper.readValue(string, LinkedHashMap.class);
        } catch (Exception e) {
            log.warn("Failed readHashMap: " + string);
            return new LinkedHashMap();
        }
    }

    public static <T> T readObject(String string, Class<T> clazz) {
        try {
            return mapper.readValue(string, clazz);
        } catch (Exception e) {
            log.warn("Failed readObject: " + string + " " + e.getMessage(), e);
            return null;
        }
    }

    public static <T> List<T> readList(String string, Class<T> clazz) {
        JavaType javaType = mapper.getTypeFactory().constructCollectionType(List.class, clazz);
        try {
            return mapper.readValue(string, javaType);
        } catch (Exception e) {
            log.warn("Failed readList: " + string + " " + e.getMessage(), e);
            return Collections.emptyList();
        }
    }

    public static <T> Set<T> readSet(String string, Class<T> clazz) {
        JavaType javaType = mapper.getTypeFactory().constructCollectionType(Set.class, clazz);
        try {
            return mapper.readValue(string, javaType);
        } catch (Exception e) {
            log.warn("Failed readObject: " + string + " " + e.getMessage(), e);
            return Collections.emptySet();
        }
    }

    public static <T> T readObject(String string, TypeReference valueTypeRef) {
        try {
            return mapper.readValue(string, valueTypeRef);
        } catch (Exception e) {
            log.warn("Failed readObject: " + string + " " + e.getMessage(), e);
            return null;
        }
    }
}
