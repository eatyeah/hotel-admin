package com.daigg.hoteladmin.pojo.vo;

import com.daigg.hoteladmin.tools.Page;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

/**
 * 酒店VO类
 * @author daiyang
 */
@Data
@ApiModel("酒店实体")
public class HotelVO implements Serializable {
    @ApiModelProperty("主键")
    private String id;

    @ApiModelProperty("是否允许设置隔离酒店")
    private Integer allowIsolation;

    @ApiModelProperty("部门id")
    private  String deptId;

    @ApiModelProperty("酒店名")
    private String name;

    @ApiModelProperty("省")
    private String province;

    @ApiModelProperty("市")
    private String city;

    @ApiModelProperty("酒店地址")
    private String address;

    @ApiModelProperty("酒店停车场")
    private String parkingLot;

    @ApiModelProperty("其他政策")
    private String otherPolicy;

    @ApiModelProperty("徽章")
    private String badge;

    @ApiModelProperty("经度")
    private String longitude;

    private String minLongitude;

    private String maxLongitude;

    @ApiModelProperty("纬度")
    private String latitude;

    private String minLatitude;

    private String maxLatitude;

    @ApiModelProperty("距离(输入时为半径条件,输出时为实际距离)")
    private String distance;

    @ApiModelProperty("酒店介绍")
    private String introduce;

    @ApiModelProperty("设施服务")
    private String facilities;

    @ApiModelProperty("封面")
    private String cover;

    @ApiModelProperty("排序")
    private Integer sort;

    @ApiModelProperty("收藏列表")
    private List<String> collection;

    @ApiModelProperty("是否删除")
    private Integer isDeleted;

    @ApiModelProperty("创建人")
    private String createBy;

    @ApiModelProperty("创建时间")
    private Date createTime;

    @ApiModelProperty("更新人")
    private String updateBy;

    @ApiModelProperty("更新时间")
    private Date updateTime;

    private Page page;
}
