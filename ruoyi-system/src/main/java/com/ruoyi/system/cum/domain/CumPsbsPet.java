package com.ruoyi.system.cum.domain;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import com.ruoyi.common.annotation.Excel;
import com.ruoyi.common.core.domain.BaseEntity;

/**
 * 宠物管理对象 psbs_pet
 * 
 * @author 御泽
 * @date 2024-05-08
 */
public class CumPsbsPet extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** 宠物编号 */
    private Long pid;

    /** 宠物姓名 */
    @Excel(name = "宠物姓名")
    private String pname;

    /** 宠物注意事项 */
    @Excel(name = "宠物注意事项")
    private String pps;

    /** 宠物性别 */
    @Excel(name = "宠物性别")
    private Long psex;

    /** 种类编号 */
    @Excel(name = "种类编号")
    private Long ptid;
    /** 种类名称 */
    private String ptname;
    /** 主人编号 */
    private Long cuid;
    /** 宠物照片 */
    private String ptpic;
    public void setPid(Long pid) 
    {
        this.pid = pid;
    }

    public Long getPid() 
    {
        return pid;
    }
    public void setPname(String pname) 
    {
        this.pname = pname;
    }

    public String getPname() 
    {
        return pname;
    }
    public void setPps(String pps) 
    {
        this.pps = pps;
    }

    public String getPps() 
    {
        return pps;
    }
    public void setPsex(Long psex) 
    {
        this.psex = psex;
    }

    public Long getPsex() 
    {
        return psex;
    }
    public void setPtid(Long ptid) 
    {
        this.ptid = ptid;
    }

    public Long getPtid() 
    {
        return ptid;
    }
    public void setPtpic(String ptpic) 
    {
        this.ptpic = ptpic;
    }

    public String getPtpic() 
    {
        return ptpic;
    }

    public void setPtname(String ptname)
    {
        this.ptname = ptname;
    }

    public String getPtname()
    {
        return ptname;
    }

    public Long getCuid() { return cuid; }

    public void setCuid(Long cuid) {this.cuid = cuid;}

    @Override
    public String toString() {
        return new ToStringBuilder(this,ToStringStyle.MULTI_LINE_STYLE)
            .append("pid", getPid())
            .append("pname", getPname())
            .append("pps", getPps())
            .append("psex", getPsex())
            .append("ptid", getPtid())
            .append("ptpic", getPtpic())
            .toString();
    }
}
