package com.ruoyi.system.adm.domain;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import com.ruoyi.common.annotation.Excel;
import com.ruoyi.common.core.domain.BaseEntity;

/**
 * 宠物店管理对象 psbs_manager
 * 
 * @author 御泽
 * @date 2024-05-06
 */
public class AdmPsbsManager extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** 店长编号 */
    @Excel(name = "店长编号")
    private Long mid;

    /** 店长性别 */
    @Excel(name = "店长性别")
    private Long msex;

    /** 店长电话 */
    @Excel(name = "店长电话")
    private String mnum;

    /** 店面地址 */
    @Excel(name = "店面地址")
    private String maddress;

    /** 店长姓名 */
    @Excel(name = "店长姓名")
    private String mname;

    /** 店面名称 */
    @Excel(name = "店面名称")
    private String msname;

    /** 登录账号 */
    @Excel(name = "登录账号")
    private Long uid;

    private String uname;

    public void setUname(String uname) {
        this.uname = uname;
    }

    public String getUname() {
        return uname;
    }

    public void setMid(Long mid)
    {
        this.mid = mid;
    }

    public Long getMid() 
    {
        return mid;
    }
    public void setMsex(Long msex) 
    {
        this.msex = msex;
    }

    public Long getMsex() 
    {
        return msex;
    }
    public void setMnum(String mnum) 
    {
        this.mnum = mnum;
    }

    public String getMnum() 
    {
        return mnum;
    }
    public void setMaddress(String maddress) 
    {
        this.maddress = maddress;
    }

    public String getMaddress() 
    {
        return maddress;
    }
    public void setMname(String mname) 
    {
        this.mname = mname;
    }

    public String getMname() 
    {
        return mname;
    }
    public void setMsname(String msname) 
    {
        this.msname = msname;
    }

    public String getMsname() 
    {
        return msname;
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
            .append("mid", getMid())
            .append("msex", getMsex())
            .append("mnum", getMnum())
            .append("maddress", getMaddress())
            .append("mname", getMname())
            .append("msname", getMsname())
            .append("uid", getUid())
            .toString();
    }
}
