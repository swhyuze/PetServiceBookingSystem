package com.ruoyi.system.clk.service;

import java.util.List;
import com.ruoyi.system.clk.domain.ClkPsbsService;

/**
 * 订单查询Service接口
 * 
 * @author 御泽
 * @date 2024-05-08
 */
public interface IClkPsbsServiceService 
{
    /**
     * 查询订单查询
     * 
     * @param serid 订单查询主键
     * @return 订单查询
     */
    public ClkPsbsService selectClkPsbsServiceBySerid(Long serid);

    /**
     * 查询订单查询列表
     * 
     * @param clkPsbsService 订单查询
     * @return 订单查询集合
     */
    public List<ClkPsbsService> selectClkPsbsServiceList(ClkPsbsService clkPsbsService);

    /**
     * 新增订单查询
     * 
     * @param clkPsbsService 订单查询
     * @return 结果
     */
    public int insertClkPsbsService(ClkPsbsService clkPsbsService);

    /**
     * 修改订单查询
     * 
     * @param clkPsbsService 订单查询
     * @return 结果
     */
    public int updateClkPsbsService(ClkPsbsService clkPsbsService);

    /**
     * 批量删除订单查询
     * 
     * @param serids 需要删除的订单查询主键集合
     * @return 结果
     */
    public int deleteClkPsbsServiceBySerids(Long[] serids);

    /**
     * 删除订单查询信息
     * 
     * @param serid 订单查询主键
     * @return 结果
     */
    public int deleteClkPsbsServiceBySerid(Long serid);
}
