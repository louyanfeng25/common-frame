package com.baiyan.common.base.model.po;

import com.baomidou.mybatisplus.annotation.TableId;
import lombok.Data;

import java.time.LocalDateTime;

/**
 * po层实体
 *
 * 物理删除
 *
 * @author baiyan
 * @date 2021/11/19
 */
@Data
public class BaseEntity {

    /**
     * 主键id 采用默认雪花算法
     */
    @TableId
    private Long id;

    /**
     * 创建时间
     */
    private LocalDateTime gmtCreate;

    /**
     * 修改时间
     */
    private LocalDateTime gmtModified;

}
