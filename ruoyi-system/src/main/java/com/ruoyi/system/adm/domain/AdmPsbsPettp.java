package com.ruoyi.system.adm.domain;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import com.ruoyi.common.annotation.Excel;
import com.ruoyi.common.core.domain.BaseEntity;

/**
 * 宠物品种对象 psbs_pettp
 * 
 * @author 御泽
 * @date 2024-05-06
 */
public class AdmPsbsPettp extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** 品种编号 */
    @Excel(name = "品种编号")
    private Long ptid;

    /** 品种名 */
    @Excel(name = "品种名")
    private String ptname;

    /** 品种介绍 */
    @Excel(name = "品种介绍")
    private String ptps;

    public void setPtid(Long ptid) 
    {
        this.ptid = ptid;
    }

    public Long getPtid() 
    {
        return ptid;
    }
    public void setPtname(String ptname) 
    {
        this.ptname = ptname;
    }

    public String getPtname() 
    {
        return ptname;
    }
    public void setPtps(String ptps) 
    {
        this.ptps = ptps;
    }

    public String getPtps() 
    {
        return ptps;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this,ToStringStyle.MULTI_LINE_STYLE)
            .append("ptid", getPtid())
            .append("ptname", getPtname())
            .append("ptps", getPtps())
            .toString();
    }
}
