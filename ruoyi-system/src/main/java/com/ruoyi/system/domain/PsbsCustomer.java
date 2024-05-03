package com.ruoyi.system.domain;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import com.ruoyi.common.annotation.Excel;
import com.ruoyi.common.core.domain.BaseEntity;

/**
 * 顾客管理对象 psbs_customer
 * 
 * @author 御泽
 * @date 2024-05-03
 */
public class PsbsCustomer extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** 顾客编号 */
    private Long cuid;

    /** 顾客性别 */
    @Excel(name = "顾客性别")
    private Long cusex;

    /** 顾客电话 */
    @Excel(name = "顾客电话")
    private String cunum;

    /** 顾客姓名 */
    @Excel(name = "顾客姓名")
    private String cuname;

    /** 登录账号 */
    @Excel(name = "登录账号")
    private Long uid;

    /** 顾客类型 */
    @Excel(name = "顾客类型")
    private Long cutp;

    public void setCuid(Long cuid) 
    {
        this.cuid = cuid;
    }

    public Long getCuid() 
    {
        return cuid;
    }
    public void setCusex(Long cusex) 
    {
        this.cusex = cusex;
    }

    public Long getCusex() 
    {
        return cusex;
    }
    public void setCunum(String cunum) 
    {
        this.cunum = cunum;
    }

    public String getCunum() 
    {
        return cunum;
    }
    public void setCuname(String cuname) 
    {
        this.cuname = cuname;
    }

    public String getCuname() 
    {
        return cuname;
    }
    public void setUid(Long uid) 
    {
        this.uid = uid;
    }

    public Long getUid() 
    {
        return uid;
    }
    public void setCutp(Long cutp) 
    {
        this.cutp = cutp;
    }

    public Long getCutp() 
    {
        return cutp;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this,ToStringStyle.MULTI_LINE_STYLE)
            .append("cuid", getCuid())
            .append("cusex", getCusex())
            .append("cunum", getCunum())
            .append("cuname", getCuname())
            .append("uid", getUid())
            .append("cutp", getCutp())
            .toString();
    }
}
