package com.baiyan.common.base.model.dto;

import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * 基础字典类
 * @author baiyan
 * @time 2020/11/17 20:04
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class DataDictDTO {

    @ApiModelProperty("key值")
    private String key;

    @ApiModelProperty("value值")
    private String value;

}
