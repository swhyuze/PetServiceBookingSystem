package com.ruoyi.system.cum.domain;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import com.ruoyi.common.annotation.Excel;
import com.ruoyi.common.core.domain.BaseEntity;

/**
 * 服务种类对象 psbs_servicetp
 * 
 * @author ruoyi
 * @date 2024-05-08
 */
public class CumPsbsServicetp extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** 服务种类编号 */
    private Long stid;

    /** 服务名称 */
    @Excel(name = "服务名称")
    private String stname;

    /** 服务价格 */
    @Excel(name = "服务价格")
    private Long stmoney;

    /** 收费方式 */
    @Excel(name = "收费方式")
    private Long stmtp;

    /** 服务描述 */
    private String stps;

    private long sttime;
    private long clid;
    private String clname;
    private long mid;
    private String msname;


    public long getMid() {
        return mid;
    }

    public void setMid(long mid) {
        this.mid = mid;
    }

    public long getSttime() {
        return sttime;
    }

    public void setSttime(long sttime) {
        this.sttime = sttime;
    }

    public long getClid() {
        return clid;
    }

    public void setClid(long clid) {
        this.clid = clid;
    }

    public String getClname() {
        return clname;
    }

    public void setClname(String clname) {
        this.clname = clname;
    }

    public String getMsname() {
        return msname;
    }

    public void setMsname(String msname) {
        this.msname = msname;
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
