package com.xs.my_security.demo.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.extension.activerecord.Model;
import java.util.Date;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableField;
import java.io.Serializable;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

/**
 * <p>
 * 
 * </p>
 *
 * @author 谢霜
 * @since 2018-12-01
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@TableName("sys_resource")
@ApiModel(value="Resource对象", description="")
public class Resource extends Model<Resource> {

    private static final long serialVersionUID = 1L;

    @TableId("id")
    private String id;

    @ApiModelProperty(value = "权限名称")
    @TableField("name")
    private String name;

    @ApiModelProperty(value = "父id")
    @TableField("parent_id")
    private String parentId;

    @ApiModelProperty(value = "类型（0菜单1按钮）")
    @TableField("type")
    private Integer type;

    @ApiModelProperty(value = "访问路径")
    @TableField("url")
    private String url;

    @ApiModelProperty(value = "权限标识")
    @TableField("permission")
    private String permission;

    @ApiModelProperty(value = "颜色")
    @TableField("color")
    private String color;

    @ApiModelProperty(value = "图标")
    @TableField("icon")
    private String icon;

    @ApiModelProperty(value = "排序")
    @TableField("sort")
    private Integer sort;

    @ApiModelProperty(value = "验证与否（0不验证，1验证，2，无法访问）")
    @TableField("verification")
    private Integer verification;

    @ApiModelProperty(value = "创建时间")
    @TableField("create_date")
    private Date createDate;

    @ApiModelProperty(value = "日志是否保存（1是0否）")
    @TableField("log_is_save")
    private Boolean logIsSave;


    @Override
    protected Serializable pkVal() {
        return this.id;
    }

}
