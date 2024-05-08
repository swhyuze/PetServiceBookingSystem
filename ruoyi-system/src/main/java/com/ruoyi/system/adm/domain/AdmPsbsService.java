package com.ruoyi.system.adm.domain;

import java.util.Date;
import com.fasterxml.jackson.annotation.JsonFormat;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import com.ruoyi.common.annotation.Excel;
import com.ruoyi.common.core.domain.BaseEntity;

/**
 * 服务记录对象 psbs_service
 * 
 * @author 御泽
 * @date 2024-05-06
 */
public class AdmPsbsService extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** 服务记录编号 */
    private Long serid;

    /** 服务种类 */
    @Excel(name = "服务种类")
    private Long stid;

    /** 店员 */
    @Excel(name = "店员")
    private Long clid;

    /** 宠物店 */
    @Excel(name = "宠物店")
    private Long mid;

    /** 宠物编号 */
    @Excel(name = "宠物编号")
    private Long pid;

    /** 服务开始时间 */
    @JsonFormat(pattern = "yyyy-MM-dd")
    @Excel(name = "服务开始时间", width = 30, dateFormat = "yyyy-MM-dd")
    private Date serstime;

    /** 服务结束时间 */
    @JsonFormat(pattern = "yyyy-MM-dd")
    @Excel(name = "服务结束时间", width = 30, dateFormat = "yyyy-MM-dd")
    private Date seretime;

    /** 服务评分 */
    @Excel(name = "服务评分")
    private Long serscore;

    /** 服务评价 */
    @Excel(name = "服务评价")
    private String serassess;

    /** 服务状态 */
    @Excel(name = "服务状态")
    private Long serstate;

    /** 服务花费 */
    @Excel(name = "服务花费")
    private Long sermoney;

    public void setSerid(Long serid) 
    {
        this.serid = serid;
    }

    public Long getSerid() 
    {
        return serid;
    }
    public void setStid(Long stid) 
    {
        this.stid = stid;
    }

    public Long getStid() 
    {
        return stid;
    }
    public void setClid(Long clid) 
    {
        this.clid = clid;
    }

    public Long getClid() 
    {
        return clid;
    }
    public void setMid(Long mid) 
    {
        this.mid = mid;
    }

    public Long getMid() 
    {
        return mid;
    }
    public void setPid(Long pid) 
    {
        this.pid = pid;
    }

    public Long getPid() 
    {
        return pid;
    }
    public void setSerstime(Date serstime) 
    {
        this.serstime = serstime;
    }

    public Date getSerstime() 
    {
        return serstime;
    }
    public void setSeretime(Date seretime) 
    {
        this.seretime = seretime;
    }

    public Date getSeretime() 
    {
        return seretime;
    }
    public void setSerscore(Long serscore) 
    {
        this.serscore = serscore;
    }

    public Long getSerscore() 
    {
        return serscore;
    }
    public void setSerassess(String serassess) 
    {
        this.serassess = serassess;
    }

    public String getSerassess() 
    {
        return serassess;
    }
    public void setSerstate(Long serstate) 
    {
        this.serstate = serstate;
    }

    public Long getSerstate() 
    {
        return serstate;
    }
    public void setSermoney(Long sermoney) 
    {
        this.sermoney = sermoney;
    }

    public Long getSermoney() 
    {
        return sermoney;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this,ToStringStyle.MULTI_LINE_STYLE)
            .append("serid", getSerid())
            .append("stid", getStid())
            .append("clid", getClid())
            .append("mid", getMid())
            .append("pid", getPid())
            .append("serstime", getSerstime())
            .append("seretime", getSeretime())
            .append("serscore", getSerscore())
            .append("serassess", getSerassess())
            .append("serstate", getSerstate())
            .append("sermoney", getSermoney())
            .toString();
    }
}
