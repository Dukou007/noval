package com.util;

import org.apache.log4j.Logger;
import org.codehaus.jackson.map.ObjectMapper;
import org.codehaus.jackson.map.type.TypeFactory;

import java.util.List;
import java.util.Map;

/**
 * @author Andy
 */
public class JsonUtils {
    private static Logger logger = Logger.getLogger(JsonUtils.class);

    //each thread has its own ObjectMapper instance
    private static ThreadLocal<ObjectMapper> objMapperLocal = new ThreadLocal<ObjectMapper>(){
        @Override
        public ObjectMapper initialValue(){
            return new ObjectMapper();
        }
    };

    public static String toJSON(Object value) {
        String result = null;
        try {
            result = objMapperLocal.get().writeValueAsString(value);
        } catch (Exception e) {
            e.printStackTrace();
        }
        // Fix null string
        if ("null".equals(result)) {
            result = null;
        }
        return result;
    }

    public static <T> T toT(String jsonString, Class<T> clazz) {
        try {
            return objMapperLocal.get().readValue(jsonString, clazz);
        } catch (Exception e) {
            LogUtils.error(logger, e, "toT error: {0}", jsonString);
        }
        return null;
    }

    @SuppressWarnings("deprecation")
	public static <T> List<T> toTList(String jsonString, Class<T> clazz) {
        try {
            return objMapperLocal.get().readValue(jsonString, TypeFactory.collectionType(List.class, clazz));
        } catch (Exception e) {
            LogUtils.error(logger, e, "toTList error: {0}", jsonString);
        }
        return null;
    }

    @SuppressWarnings("unchecked")
    public static Map<String, Object> toMap(String jsonString) {
        return toT(jsonString, Map.class);
    }

}


