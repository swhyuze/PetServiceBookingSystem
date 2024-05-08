package com.ruoyi.system.adm.domain;

import java.util.Date;
import com.fasterxml.jackson.annotation.JsonFormat;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import com.ruoyi.common.annotation.Excel;
import com.ruoyi.common.core.domain.BaseEntity;

/**
 * 宠物健康对象 psbs_petstate
 * 
 * @author 御泽
 * @date 2024-05-06
 */
public class AdmPsbsPetstate extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** 宠物健康记录编号 */
    @Excel(name = "宠物健康记录编号")
    private Long pstid;

    /** 宠物编号 */
    @Excel(name = "宠物编号")
    private Long pid;

    /** 宠物重量 */
    @Excel(name = "宠物重量")
    private Long pstweight;

    /** 记录时间 */
    @JsonFormat(pattern = "yyyy-MM-dd")
    @Excel(name = "记录时间", width = 30, dateFormat = "yyyy-MM-dd")
    private Date psttime;

    /** 状态描述 */
    @Excel(name = "状态描述")
    private String pstps;

    public void setPstid(Long pstid) 
    {
        this.pstid = pstid;
    }

    public Long getPstid() 
    {
        return pstid;
    }
    public void setPid(Long pid) 
    {
        this.pid = pid;
    }

    public Long getPid() 
    {
        return pid;
    }
    public void setPstweight(Long pstweight) 
    {
        this.pstweight = pstweight;
    }

    public Long getPstweight() 
    {
        return pstweight;
    }
    public void setPsttime(Date psttime) 
    {
        this.psttime = psttime;
    }

    public Date getPsttime() 
    {
        return psttime;
    }
    public void setPstps(String pstps) 
    {
        this.pstps = pstps;
    }

    public String getPstps() 
    {
        return pstps;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this,ToStringStyle.MULTI_LINE_STYLE)
            .append("pstid", getPstid())
            .append("pid", getPid())
            .append("pstweight", getPstweight())
            .append("psttime", getPsttime())
            .append("pstps", getPstps())
            .toString();
    }
}
