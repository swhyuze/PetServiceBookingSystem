package com.ruoyi.system.adm.domain;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import com.ruoyi.common.annotation.Excel;
import com.ruoyi.common.core.domain.BaseEntity;

/**
 * 宠物服务对象 psbs_petser
 * 
 * @author 御泽
 * @date 2024-05-06
 */
public class AdmPsbsPetser extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** 宠物服务编号 */
    private Long psid;

    /** 服务编号 */
    @Excel(name = "服务编号")
    private Long stid;

    /** 宠物种类编号 */
    @Excel(name = "宠物种类编号")
    private Long ptid;

    public void setPsid(Long psid) 
    {
        this.psid = psid;
    }

    public Long getPsid() 
    {
        return psid;
    }
    public void setStid(Long stid) 
    {
        this.stid = stid;
    }

    public Long getStid() 
    {
        return stid;
    }
    public void setPtid(Long ptid) 
    {
        this.ptid = ptid;
    }

    public Long getPtid() 
    {
        return ptid;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this,ToStringStyle.MULTI_LINE_STYLE)
            .append("psid", getPsid())
            .append("stid", getStid())
            .append("ptid", getPtid())
            .toString();
    }
}
