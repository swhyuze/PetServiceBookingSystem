package com.ruoyi.system.cum.service;

import java.util.List;
import com.ruoyi.system.cum.domain.CumPsbsService;

/**
 * 订单查询Service接口
 * 
 * @author 御泽
 * @date 2024-05-08
 */
public interface ICumPsbsServiceService 
{
    /**
     * 查询订单查询
     * 
     * @param serid 订单查询主键
     * @return 订单查询
     */
    public CumPsbsService selectCumPsbsServiceBySerid(Long serid);

    /**
     * 查询订单查询列表
     * 
     * @param cumPsbsService 订单查询
     * @return 订单查询集合
     */
    public List<CumPsbsService> selectCumPsbsServiceList(CumPsbsService cumPsbsService);

    /**
     * 新增订单查询
     * 
     * @param cumPsbsService 订单查询
     * @return 结果
     */
    public int insertCumPsbsService(CumPsbsService cumPsbsService);

    /**
     * 修改订单查询
     * 
     * @param cumPsbsService 订单查询
     * @return 结果
     */
    public int updateCumPsbsService(CumPsbsService cumPsbsService);

    /**
     * 批量删除订单查询
     * 
     * @param serids 需要删除的订单查询主键集合
     * @return 结果
     */
    public int deleteCumPsbsServiceBySerids(Long[] serids);

    /**
     * 删除订单查询信息
     * 
     * @param serid 订单查询主键
     * @return 结果
     */
    public int deleteCumPsbsServiceBySerid(Long serid);
}
