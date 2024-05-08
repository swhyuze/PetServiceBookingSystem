package com.ruoyi.system.adm.domain;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import com.ruoyi.common.annotation.Excel;
import com.ruoyi.common.core.domain.BaseEntity;

/**
 * 店员服务对象 psbs_clkser
 * 
 * @author 御泽
 * @date 2024-05-06
 */
public class AdmPsbsClkser extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** 记录编号 */
    @Excel(name = "记录编号")
    private Long clser;

    /** 店员编号 */
    @Excel(name = "店员编号")
    private Long clid;

    /** 服务种类编号 */
    @Excel(name = "服务种类编号")
    private Long stid;

    public void setClser(Long clser) 
    {
        this.clser = clser;
    }

    public Long getClser() 
    {
        return clser;
    }
    public void setClid(Long clid) 
    {
        this.clid = clid;
    }

    public Long getClid() 
    {
        return clid;
    }
    public void setStid(Long stid) 
    {
        this.stid = stid;
    }

    public Long getStid() 
    {
        return stid;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this,ToStringStyle.MULTI_LINE_STYLE)
            .append("clser", getClser())
            .append("clid", getClid())
            .append("stid", getStid())
            .toString();
    }
}
