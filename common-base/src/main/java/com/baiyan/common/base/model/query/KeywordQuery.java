package com.baiyan.common.base.model.query;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * 关键字查询条件
 *
 * @author baiyan
 * @date 2020/11/17
 */
@Data
@EqualsAndHashCode(callSuper = true)
public class KeywordQuery extends PageQuery {

    @ApiModelProperty("关键字查询")
    private String keyword;
}
