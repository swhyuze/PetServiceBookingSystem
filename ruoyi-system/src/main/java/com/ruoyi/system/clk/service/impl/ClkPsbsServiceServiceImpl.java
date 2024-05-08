package com.ruoyi.system.clk.service.impl;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.ruoyi.system.clk.mapper.ClkPsbsServiceMapper;
import com.ruoyi.system.clk.domain.ClkPsbsService;
import com.ruoyi.system.clk.service.IClkPsbsServiceService;

/**
 * 订单查询Service业务层处理
 * 
 * @author 御泽
 * @date 2024-05-08
 */
@Service
public class ClkPsbsServiceServiceImpl implements IClkPsbsServiceService 
{
    @Autowired
    private ClkPsbsServiceMapper clkPsbsServiceMapper;

    /**
     * 查询订单查询
     * 
     * @param serid 订单查询主键
     * @return 订单查询
     */
    @Override
    public ClkPsbsService selectClkPsbsServiceBySerid(Long serid)
    {
        return clkPsbsServiceMapper.selectClkPsbsServiceBySerid(serid);
    }

    /**
     * 查询订单查询列表
     * 
     * @param clkPsbsService 订单查询
     * @return 订单查询
     */
    @Override
    public List<ClkPsbsService> selectClkPsbsServiceList(ClkPsbsService clkPsbsService)
    {
        return clkPsbsServiceMapper.selectClkPsbsServiceList(clkPsbsService);
    }

    /**
     * 新增订单查询
     * 
     * @param clkPsbsService 订单查询
     * @return 结果
     */
    @Override
    public int insertClkPsbsService(ClkPsbsService clkPsbsService)
    {
        return clkPsbsServiceMapper.insertClkPsbsService(clkPsbsService);
    }

    /**
     * 修改订单查询
     * 
     * @param clkPsbsService 订单查询
     * @return 结果
     */
    @Override
    public int updateClkPsbsService(ClkPsbsService clkPsbsService)
    {
        return clkPsbsServiceMapper.updateClkPsbsService(clkPsbsService);
    }

    /**
     * 批量删除订单查询
     * 
     * @param serids 需要删除的订单查询主键
     * @return 结果
     */
    @Override
    public int deleteClkPsbsServiceBySerids(Long[] serids)
    {
        return clkPsbsServiceMapper.deleteClkPsbsServiceBySerids(serids);
    }

    /**
     * 删除订单查询信息
     * 
     * @param serid 订单查询主键
     * @return 结果
     */
    @Override
    public int deleteClkPsbsServiceBySerid(Long serid)
    {
        return clkPsbsServiceMapper.deleteClkPsbsServiceBySerid(serid);
    }
}
