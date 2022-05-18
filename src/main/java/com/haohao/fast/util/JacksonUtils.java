package com.haohao.fast.util;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JavaType;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import java.util.Collection;
import java.util.List;
import java.util.Set;

/**
 * 封装了Jackson的json工具类，用于代替JSONObject
 *
 * @author haohao
 */
@Slf4j
public class JacksonUtils {

    public static ObjectMapper objectMapper = new ObjectMapper();

    /**
     * Java对象转JSON字符串
     *
     * @param object object
     * @return String
     */
    public static String toJsonString(Object object) {
        try {
            return objectMapper.writeValueAsString(object);
        } catch (JsonProcessingException e) {
            log.error("The JacksonUtil toJsonString is error : \n", e);
            throw new RuntimeException();
        }
    }

    /**
     * Java对象转JSON字符串 - 美化输出
     *
     * @param object object
     * @return String
     */
    public static String toJsonStringWithPretty(Object object) {
        try {
            return objectMapper.writerWithDefaultPrettyPrinter().writeValueAsString(object);
        } catch (JsonProcessingException e) {
            log.error("The JacksonUtil toJsonString is error : \n", e);
            throw new RuntimeException();
        }
    }

    /**
     * JSON字符串转对象
     *
     * @param json  json
     * @param clazz clazz
     * @param <T>   <T>
     * @return <T> T
     */
    public static <T> T parseObject(String json, Class<T> clazz) {
        try {
            return objectMapper.readValue(json, clazz);
        } catch (Exception e) {
            log.error("The JacksonUtil parseObject is error, json str is {}, class name is {} \n", json, clazz.getName(), e);
            throw new RuntimeException();
        }
    }

    /**
     * JSON字符串转List集合
     *
     * @param json           json
     * @param elementClasses elementClasses
     * @param <T>            <T>
     * @return <T> List<T>
     */
    @SafeVarargs
    public static <T> List<T> parseList(String json, Class<T>... elementClasses) {
        try {
            return objectMapper.readValue(json, getCollectionType(objectMapper, List.class, elementClasses));
        } catch (Exception e) {
            log.error("The JacksonUtil parseList is error, json str is {}, element class name is {} \n",
                    json, elementClasses.getClass().getName(), e);
            throw new RuntimeException();
        }
    }

    /**
     * JSON字符串转Set集合
     *
     * @param json           json
     * @param elementClasses elementClasses
     * @param <T>            <T>
     * @return <T> Set<T>
     */
    @SafeVarargs
    public static <T> Set<T> parseSet(String json, Class<T>... elementClasses) {
        try {
            return objectMapper.readValue(json, getCollectionType(objectMapper, Set.class, elementClasses));
        } catch (Exception e) {
            log.error("The JacksonUtil parseSet is error, json str is {}, element class name is {} \n",
                    json, elementClasses.getClass().getName(), e);
            throw new RuntimeException();
        }
    }

    /**
     * JSON字符串转Collection集合
     *
     * @param json           json
     * @param elementClasses elementClasses
     * @param <T>            <T>
     * @return <T> Collection<T>
     */
    @SafeVarargs
    public static <T> Collection<T> parseCollection(String json, Class<T>... elementClasses) {
        try {
            return objectMapper.readValue(json, getCollectionType(objectMapper, Collection.class, elementClasses));
        } catch (Exception e) {
            log.error("The JacksonUtil parseCollection is error, json str is {}, element class name is {} \n",
                    json, elementClasses.getClass().getName(), e);
            throw new RuntimeException();
        }
    }

    /**
     * 获取泛型的Collection Type
     *
     * @param collectionClass 泛型的Collection
     * @param elementClasses  元素类
     * @return JavaType Java类型
     */
    public static JavaType getCollectionType(ObjectMapper mapper, Class<?> collectionClass, Class<?>... elementClasses) {
        return mapper.getTypeFactory().constructParametricType(collectionClass, elementClasses);
    }
}
