package com.ruoyi.system.clk.service.impl;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.ruoyi.system.clk.mapper.ClkPsbsServicetpMapper;
import com.ruoyi.system.clk.domain.ClkPsbsServicetp;
import com.ruoyi.system.clk.service.IClkPsbsServicetpService;

/**
 * 服务资质Service业务层处理
 * 
 * @author ruoyi
 * @date 2024-05-08
 */
@Service
public class ClkPsbsServicetpServiceImpl implements IClkPsbsServicetpService 
{
    @Autowired
    private ClkPsbsServicetpMapper clkPsbsServicetpMapper;

    /**
     * 查询服务资质
     * 
     * @param stid 服务资质主键
     * @return 服务资质
     */
    @Override
    public ClkPsbsServicetp selectClkPsbsServicetpByStid(Long stid)
    {
        return clkPsbsServicetpMapper.selectClkPsbsServicetpByStid(stid);
    }

    /**
     * 查询服务资质列表
     * 
     * @param clkPsbsServicetp 服务资质
     * @return 服务资质
     */
    @Override
    public List<ClkPsbsServicetp> selectClkPsbsServicetpList(ClkPsbsServicetp clkPsbsServicetp)
    {
        return clkPsbsServicetpMapper.selectClkPsbsServicetpList(clkPsbsServicetp);
    }

    /**
     * 新增服务资质
     * 
     * @param clkPsbsServicetp 服务资质
     * @return 结果
     */
    @Override
    public int insertClkPsbsServicetp(ClkPsbsServicetp clkPsbsServicetp)
    {
        return clkPsbsServicetpMapper.insertClkPsbsServicetp(clkPsbsServicetp);
    }

    /**
     * 修改服务资质
     * 
     * @param clkPsbsServicetp 服务资质
     * @return 结果
     */
    @Override
    public int updateClkPsbsServicetp(ClkPsbsServicetp clkPsbsServicetp)
    {
        return clkPsbsServicetpMapper.updateClkPsbsServicetp(clkPsbsServicetp);
    }

    /**
     * 批量删除服务资质
     * 
     * @param stids 需要删除的服务资质主键
     * @return 结果
     */
    @Override
    public int deleteClkPsbsServicetpByStids(Long[] stids)
    {
        return clkPsbsServicetpMapper.deleteClkPsbsServicetpByStids(stids);
    }

    /**
     * 删除服务资质信息
     * 
     * @param stid 服务资质主键
     * @return 结果
     */
    @Override
    public int deleteClkPsbsServicetpByStid(Long stid)
    {
        return clkPsbsServicetpMapper.deleteClkPsbsServicetpByStid(stid);
    }
}
