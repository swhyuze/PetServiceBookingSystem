package com.ruoyi.system.adm.domain;

import java.util.List;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import com.ruoyi.common.annotation.Excel;
import com.ruoyi.common.core.domain.BaseEntity;

/**
 * 店员管理对象 psbs_clerk
 * 
 * @author 御泽
 * @date 2024-05-06
 */
public class AdmPsbsClerk extends BaseEntity
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

    private String uname;
    private String msname;
    /** 店员服务信息 */
    private List<AdmPsbsClkser> admPsbsClkserList;

    private Long stid;

    public Long getStid() {
        return stid;
    }

    public void setStid(Long stid) {
        this.stid = stid;
    }

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


    public List<AdmPsbsClkser> getAdmPsbsClkserList()
    {
        return admPsbsClkserList;
    }

    public void setAdmPsbsClkserList(List<AdmPsbsClkser> admPsbsClkserList)
    {
        this.admPsbsClkserList = admPsbsClkserList;
    }

    public String getUname(){ return this.uname; }
    public void setUname(String uname){ this.uname=uname;}
    public String getMsname(){ return this.msname; }
    public void setMsname(String msname){ this.msname=msname;}

    @Override
    public String toString() {
        return new ToStringBuilder(this,ToStringStyle.MULTI_LINE_STYLE)
            .append("clid", getClid())
            .append("clname", getClname())
            .append("clsex", getClsex())
            .append("clnum", getClnum())
            .append("mid", getMid())
            .append("uid", getUid())
            .append("admPsbsClkserList", getAdmPsbsClkserList())
            .toString();
    }
}
