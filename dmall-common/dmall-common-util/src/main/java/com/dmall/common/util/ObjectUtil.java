package com.dmall.common.util;

import org.apache.commons.lang3.StringUtils;

import java.lang.reflect.Array;
import java.util.Collection;
import java.util.Map;
import java.util.Objects;

/**
 * @description: 对象工具类
 * @author: created by hang.yu on 2019/11/23 10:06
 */
public class ObjectUtil extends StringUtils {

    /**
     * 按照规则判断对象是否为空
     * 支持 字符串、集合、map、数组
     */
    public static boolean isEmpty(Object obj) {
        if (obj == null) {
            return true;
        }
        // 字符串
        if (obj instanceof String) {
            if (ObjectUtil.isBlank((String) obj)) {
                return true;
            }
        }

        // 集合
        if (obj instanceof Collection) {
            Collection collection = (Collection) obj;
            if (collection.isEmpty()) {
                return true;
            }
            return collection.stream().allMatch(Objects::isNull);
        }

        //map
        if (obj instanceof Map) {
            Map map = (Map) obj;
            return map.isEmpty();
        }

        // 数组
        if (obj.getClass().isArray()) {
            return Array.getLength(obj) == 0;
        }

        return false;
    }

    /**
     * 按照规则判断对象是否非空
     */
    public static boolean isNotEmpty(Object obj) {
        return !isEmpty(obj);
    }

    /**
     * 判断一组对象 是否有空对象
     */
    public static boolean containsEmpty(Object... obj) {
        for (Object o : obj) {
            if (isEmpty(o)) {
                return true;
            }
        }
        return false;
    }


    /**
     * 判断一组对象 是否全部不为空
     */
    public static boolean allNotEmpty(Object... obj) {
        for (Object o : obj) {
            if (isEmpty(o)) {
                return false;
            }
        }
        return true;
    }


}
