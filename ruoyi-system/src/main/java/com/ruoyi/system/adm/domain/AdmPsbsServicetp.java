package com.ruoyi.system.adm.domain;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import com.ruoyi.common.annotation.Excel;
import com.ruoyi.common.core.domain.BaseEntity;

/**
 * 服务种类对象 psbs_servicetp
 * 
 * @author ruoyi
 * @date 2024-05-09
 */
public class AdmPsbsServicetp extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** 服务种类编号 */
    @Excel(name = "服务种类编号")
    private Long stid;

    /** 服务名称 */
    @Excel(name = "服务名称")
    private String stname;

    /** 服务价格 */
    @Excel(name = "服务价格")
    private Long stmoney;

    /** 收费方式 */
    private Long stmtp;

    /** 服务描述 */
    private String stps;

    private Long mid;
    private Long pid;
    private Long sttime;
    public Long getSttime() {
        return sttime;
    }

    public void setSttime(Long sttime) {
        this.sttime = sttime;
    }
    public void setStid(Long stid) 
    {
        this.stid = stid;
    }

    public Long getStid() 
    {
        return stid;
    }
    public void setStname(String stname) 
    {
        this.stname = stname;
    }

    public String getStname() 
    {
        return stname;
    }
    public void setStmoney(Long stmoney) 
    {
        this.stmoney = stmoney;
    }

    public Long getStmoney() 
    {
        return stmoney;
    }
    public void setStmtp(Long stmtp) 
    {
        this.stmtp = stmtp;
    }

    public Long getStmtp() 
    {
        return stmtp;
    }
    public void setStps(String stps) 
    {
        this.stps = stps;
    }

    public String getStps() 
    {
        return stps;
    }

    public Long getMid() {
        return mid;
    }

    public void setMid(Long mid) {
        this.mid = mid;
    }

    public Long getPid() {
        return pid;
    }

    public void setPid(Long pid) {
        this.pid = pid;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this,ToStringStyle.MULTI_LINE_STYLE)
            .append("stid", getStid())
            .append("stname", getStname())
            .append("stmoney", getStmoney())
            .append("stmtp", getStmtp())
            .append("stps", getStps())
            .toString();
    }
}
