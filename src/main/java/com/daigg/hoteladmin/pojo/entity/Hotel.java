package com.daigg.hoteladmin.pojo.entity;

import com.baomidou.mybatisplus.annotation.*;
import lombok.Data;

import java.io.Serializable;
import java.util.Date;

/**
 * 酒店实体类
 * @author daiyang
 */
@Data
@TableName("hotel")
public class Hotel implements Serializable {
    @TableId("id")
    private String id;

    @TableField("dept_id")
    private String deptId;

    @TableField("name")
    private String name;

    @TableField("province")
    private String province;

    @TableField("city")
    private String city;

    @TableField("address")
    private String address;

    @TableField("parking_lot")
    private String parkingLot;

    @TableField("longitude")
    private String longitude;

    @TableField("latitude")
    private String latitude;

    @TableField("introduce")
    private String introduce;

    @TableField("facilities")
    private String facilities;

    @TableField("allow_isolation")
    private Integer allowIsolation;

    @TableLogic
    @TableField("is_deleted")
    private Integer isDeleted;

    @TableField("other_policy")
    private String otherPolicy;

    @TableField("cover")
    private String cover;

    @TableField("badge")
    private String badge;

    @TableField("sort")
    private Integer sort;


    @TableField(value = "create_by", fill = FieldFill.INSERT)
    private String createBy;

    @TableField(value = "create_time", fill = FieldFill.INSERT)
    private Date createTime;

    @TableField(value = "update_by", fill = FieldFill.UPDATE)
    private String updateBy;

    @TableField(value = "update_time", fill = FieldFill.UPDATE)
    private Date updateTime;
}

