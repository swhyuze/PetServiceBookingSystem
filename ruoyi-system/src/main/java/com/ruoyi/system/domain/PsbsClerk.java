package com.ruoyi.system.domain;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import com.ruoyi.common.annotation.Excel;
import com.ruoyi.common.core.domain.BaseEntity;

/**
 * 店员对象 psbs_clerk
 * 
 * @author ruoyi
 * @date 2024-04-23
 */
public class PsbsClerk extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** 店员编号 */
    private Long clid;

    /** 店员姓名 */
    @Excel(name = "店员姓名")
    private String clname;

    /** 店员性别 */
    @Excel(name = "店员性别")
    private Long clsex;

    /** 店员电话 */
    @Excel(name = "店员电话")
    private String clnum;

    /** 上司编号 */
    @Excel(name = "上司编号")
    private Long mid;

    /** 登录账号 */
    @Excel(name = "登录账号")
    private Long uid;

    public void setClid(Long clid) 
    {
        this.clid = clid;
    }

    public Long getClid() 
    {
        return clid;
    }
    public void setClname(String clname) 
    {
        this.clname = clname;
    }

    public String getClname() 
    {
        return clname;
    }
    public void setClsex(Long clsex) 
    {
        this.clsex = clsex;
    }

    public Long getClsex() 
    {
        return clsex;
    }
    public void setClnum(String clnum) 
    {
        this.clnum = clnum;
    }

    public String getClnum() 
    {
        return clnum;
    }
    public void setMid(Long mid) 
    {
        this.mid = mid;
    }

    public Long getMid() 
    {
        return mid;
    }
    public void setUid(Long uid) 
    {
        this.uid = uid;
    }

    public Long getUid() 
    {
        return uid;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this,ToStringStyle.MULTI_LINE_STYLE)
            .append("clid", getClid())
            .append("clname", getClname())
            .append("clsex", getClsex())
            .append("clnum", getClnum())
            .append("mid", getMid())
            .append("uid", getUid())
            .toString();
    }
}
