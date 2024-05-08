package com.ruoyi.system.clk.mapper;

import java.util.List;
import com.ruoyi.system.clk.domain.ClkPsbsServicetp;

/**
 * 服务资质Mapper接口
 * 
 * @author ruoyi
 * @date 2024-05-08
 */
public interface ClkPsbsServicetpMapper 
{
    /**
     * 查询服务资质
     * 
     * @param stid 服务资质主键
     * @return 服务资质
     */
    public ClkPsbsServicetp selectClkPsbsServicetpByStid(Long stid);

    /**
     * 查询服务资质列表
     * 
     * @param clkPsbsServicetp 服务资质
     * @return 服务资质集合
     */
    public List<ClkPsbsServicetp> selectClkPsbsServicetpList(ClkPsbsServicetp clkPsbsServicetp);

    /**
     * 新增服务资质
     * 
     * @param clkPsbsServicetp 服务资质
     * @return 结果
     */
    public int insertClkPsbsServicetp(ClkPsbsServicetp clkPsbsServicetp);

    /**
     * 修改服务资质
     * 
     * @param clkPsbsServicetp 服务资质
     * @return 结果
     */
    public int updateClkPsbsServicetp(ClkPsbsServicetp clkPsbsServicetp);

    /**
     * 删除服务资质
     * 
     * @param stid 服务资质主键
     * @return 结果
     */
    public int deleteClkPsbsServicetpByStid(Long stid);

    /**
     * 批量删除服务资质
     * 
     * @param stids 需要删除的数据主键集合
     * @return 结果
     */
    public int deleteClkPsbsServicetpByStids(Long[] stids);
}
