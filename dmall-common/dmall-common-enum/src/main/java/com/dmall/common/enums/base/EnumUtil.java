package com.dmall.common.enums.base;

import java.util.Objects;

/**
 * @description: 枚举工具类
 * @author: created by hang.yu on 2019/11/23 17:33
 */
public class EnumUtil {

    /**
     * 获取枚举对象
     */
    public static <CODE> KeyValueEnum<CODE> getKeyValueEnum(Class<? extends KeyValueEnum> enumClazz, CODE code) {
        if (enumClazz.isEnum()) {
            for (KeyValueEnum enumConstant : enumClazz.getEnumConstants()) {
                if (Objects.equals(enumConstant.getCode(), code)) {
                    return enumConstant;
                }
            }
        }
        return null;
    }

    /**
     * 获取枚举描述
     */
    public static <CODE> String getDesc(Class<? extends KeyValueEnum> enumClazz, CODE code){
        KeyValueEnum<CODE> keyValueEnum = getKeyValueEnum(enumClazz, code);
        return keyValueEnum == null ? null : keyValueEnum.getDesc();
    }

    /**
     * 获取枚举数据
     */
    public static <CODE,T> T getData(Class<? extends KeyValueEnum> enumClazz, CODE code){
        KeyValueEnum<CODE> keyValueEnum = getKeyValueEnum(enumClazz, code);
        if (keyValueEnum == null){
            return null;
        }
        if (keyValueEnum instanceof KeyValueDataEnum){
            KeyValueDataEnum<T> keyValueDataEnum = (KeyValueDataEnum) keyValueEnum;
            return keyValueDataEnum.getData();
        }
        return null;
    }
}
