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
@TableName("sys_log")
@ApiModel(value="Log对象", description="")
public class Log extends Model<Log> {

    private static final long serialVersionUID = 1L;

    @TableId("id")
    private String id;

    @TableField("username")
    private String username;

    @TableField("uid")
    private String uid;

    @TableField("ip")
    private String ip;

    @TableField("ajax")
    private Integer ajax;

    @TableField("uri")
    private String uri;

    @TableField("params")
    private String params;

    @TableField("http_method")
    private String httpMethod;

    @TableField("class_method")
    private String classMethod;

    @TableField("action_name")
    private String actionName;

    @TableField("create_date")
    private Date createDate;


    @Override
    protected Serializable pkVal() {
        return this.id;
    }

}
