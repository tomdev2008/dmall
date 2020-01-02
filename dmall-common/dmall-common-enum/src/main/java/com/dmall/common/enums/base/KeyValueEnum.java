package com.dmall.common.enums.base;

/**
 * @description: key-value枚举基类
 * @author: created by hang.yu on 2019/11/7 21:03
 */
public interface KeyValueEnum<CODE> extends KeyEnum<CODE> {

    /**
     * 描述
     */
    String getDesc();

}
