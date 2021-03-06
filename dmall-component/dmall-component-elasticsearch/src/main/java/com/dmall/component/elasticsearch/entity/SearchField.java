package com.dmall.component.elasticsearch.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @description: ES搜索实体
 * @author: created by hang.yu on 2019/11/6 23:22
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class SearchField {

    /**
     * 属性名称
     */
    private String fieldName;

    /**
     * 属性值
     */
    private Object fieldValue;
}
