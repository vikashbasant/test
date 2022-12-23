package decimal.test.controller;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ArrayNode;
import com.fasterxml.jackson.databind.node.ObjectNode;

import java.io.File;
import java.io.IOException;
import java.io.Reader;

//
public final class JsonUtils {
    private static ObjectMapper mapper = null;

    private JsonUtils() {
    }

    //STG - String to generic
    public static <T> T stringToGeneric(String string) {
        try {
            return mapper().readValue(string, new TypeReference<T>() {
            });
        } catch (IOException e) {
            throw new RuntimeException("Error in String To Generic Conversion " + e.getMessage(), e);
        }
    }

    //OTG - Object To Generic
    public static <T> T objectToGeneric(Object obj) {
        try {
            String string = mapper().writeValueAsString(obj);
            return mapper().readValue(string, new TypeReference<T>() {
            });
        } catch (IOException e) {
            throw new RuntimeException("Error in Object To Generic Conversion " + e.getMessage(), e);
        }
    }

    //JNFS - json Node from string
    public static JsonNode getNodeFromJsonString(String jsonString, String node) {
        try {
            JsonNode rootNode = mapper().readTree(jsonString);
            return rootNode.path(node);
        } catch (IOException e) {
            throw new RuntimeException("Error in Get from Json String " + e.getMessage(), e);
        }
    }


    //RVFS - read value from string
    public static <T> T readValue(String string, Class<T> clazz) {
        try {
            return mapper().readValue(string, clazz);
        } catch (IOException e) {
            throw new RuntimeException("Error in read value from json string " + e.getMessage(), e);
        }
    }

    //HCVFOT - hard convert value from object to pojo
    public static <T> T convertValue(Object fromValue, Class<T> toValueType) {
        return mapper().convertValue(fromValue, toValueType);
    }

    //SCVOT - soft convert value from object to pojo
    public static <T> T softConvertValue(Object fromValue, Class<T> toValueType) {
        return mapper().configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false)
                .convertValue(fromValue, toValueType);
    }

    //RTFS - Read Tree from String
    public static JsonNode readTree(String string) {
        try {
            return mapper().readTree(string);
        } catch (IOException e) {
            throw new RuntimeException("Error in read tree from json string " + e.getMessage(), e);
        }
    }

    //WVAS - Write value as string
    public static String writeValueAsString(Object obj) {
        try {
            return mapper().writeValueAsString(obj);
        } catch (IOException e) {
            throw new RuntimeException("Error in write value as json string " + e.getMessage(), e);
        }
    }

    // Updating Object with Json
    //UOWJ - update object with json
    public static <T> T updateObjectWithJson(T t, JsonNode node) {
        try {
            return mapper().configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false)
                    .readerForUpdating(t).readValue(node);
        } catch (IOException e) {
            throw new RuntimeException("Error in update json " + e.getMessage(), e);
        }
    }

    //RVFT - read value from file to class
    public static <T> T readValue(File src, Class<T> valueType) {
        try {
            return mapper().readValue(src, valueType);
        } catch (IOException e) {
            throw new RuntimeException("Error in read value from json string " + e.getMessage(), e);
        }
    }

    //RVRT - read value from reader to class
    public static <T> T readValue(Reader src, Class<T> valueType) {
        try {
            return mapper().readValue(src, valueType);
        } catch (IOException e) {
            throw new RuntimeException("Error in read value from json string " + e.getMessage(), e);
        }
    }


    //CON - create object node
    public static ObjectNode createObjectNode() {
        try {
            return mapper().createObjectNode();
        } catch (Exception e) {
            throw new RuntimeException("Error in creating object node " + e.getMessage(), e);
        }
    }

    public static JsonNode at(ObjectNode sourceObjet, String path) {
        return sourceObjet.at(path);
    }

    //OTJ - object to json node
    public static JsonNode objectToJsonNode(Object o) {
        try {
            return mapper().valueToTree(o);
        } catch (Exception e) {
            throw new RuntimeException("Error in converting object to json node " + e.getMessage(), e);
        }
    }

    public static ArrayNode createArrayNode() {
        return mapper().createArrayNode();
    }

    //VTTO - value to tree from object
    public static JsonNode valueToTree(Object fromValue) {
        try {
            return mapper().valueToTree(fromValue);
        } catch (Exception e) {
            throw new RuntimeException("Error in converting value to json tree " + e.getMessage(), e);
        }
    }

    private static ObjectMapper mapper() {
        if (mapper == null) {
            mapper = new ObjectMapper();
        }
        return mapper;
    }

    //TJNO -  to json node from object
    public static JsonNode toJsonNode(Object fromValue) {
        try {
            return mapper().readTree(fromValue.toString());
        } catch (Exception e) {
            throw new RuntimeException("Error in converting to json node " + e.getMessage(), e);
        }
    }

    //Convert Jon File to Java Generic Collection
    //RVF - read value from file
    public static <T> T readValue(File src) {
        try {
            return mapper().readValue(src, new TypeReference<T>() {
            });
        } catch (IOException e) {
            throw new RuntimeException("Error in read value from file " + e.getMessage(), e);
        }
    }
}
