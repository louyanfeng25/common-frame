package com.baiyan.common.base.model.dto;

import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

/**
 * 文件传输实体
 * @author baiyan
 * @date 2020/02/05
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class FileDTO implements Serializable {

    @ApiModelProperty("文件名")
    private String name;

    @ApiModelProperty("文件url")
    private String url;

    @ApiModelProperty("文件key")
    private String fileId;

}
