package com.ruoyi.system.adm.domain;

import java.util.Date;
import com.fasterxml.jackson.annotation.JsonFormat;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import com.ruoyi.common.annotation.Excel;
import com.ruoyi.common.core.domain.BaseEntity;

/**
 * 问题反馈对象 psbs_problem
 * 
 * @author 御泽
 * @date 2024-05-06
 */
public class AdmPsbsProblem extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** 问题编号 */
    private Long proid;

    /** 问题描述 */
    @Excel(name = "问题描述")
    private String protext;

    /** 问题回复 */
    @Excel(name = "问题回复")
    private String prore;

    /** 问题上传时间 */
    @JsonFormat(pattern = "yyyy-MM-dd")
    @Excel(name = "问题上传时间", width = 30, dateFormat = "yyyy-MM-dd")
    private Date proutime;

    /** 反馈时间 */
    @JsonFormat(pattern = "yyyy-MM-dd")
    @Excel(name = "反馈时间", width = 30, dateFormat = "yyyy-MM-dd")
    private Date prortime;

    /** 问题反馈人员账号 */
    @Excel(name = "问题反馈人员账号")
    private Long uid;

    public void setProid(Long proid) 
    {
        this.proid = proid;
    }

    public Long getProid() 
    {
        return proid;
    }
    public void setProtext(String protext) 
    {
        this.protext = protext;
    }

    public String getProtext() 
    {
        return protext;
    }
    public void setProre(String prore) 
    {
        this.prore = prore;
    }

    public String getProre() 
    {
        return prore;
    }
    public void setProutime(Date proutime) 
    {
        this.proutime = proutime;
    }

    public Date getProutime() 
    {
        return proutime;
    }
    public void setPrortime(Date prortime) 
    {
        this.prortime = prortime;
    }

    public Date getPrortime() 
    {
        return prortime;
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
            .append("proid", getProid())
            .append("protext", getProtext())
            .append("prore", getProre())
            .append("proutime", getProutime())
            .append("prortime", getPrortime())
            .append("uid", getUid())
            .toString();
    }
}
