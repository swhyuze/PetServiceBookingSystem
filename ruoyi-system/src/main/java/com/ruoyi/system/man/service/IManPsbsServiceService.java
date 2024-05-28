package com.ruoyi.system.man.service;

import java.util.List;
import com.ruoyi.system.man.domain.ManPsbsService;

/**
 * 订单管理Service接口
 * 
 * @author 御泽
 * @date 2024-05-08
 */
public interface IManPsbsServiceService 
{
    /**
     * 查询订单管理
     * 
     * @param serid 订单管理主键
     * @return 订单管理
     */
    public ManPsbsService selectManPsbsServiceBySerid(Long serid);

    /**
     * 查询订单管理列表
     * 
     * @param manPsbsService 订单管理
     * @return 订单管理集合
     */
    public List<ManPsbsService> selectManPsbsServiceList(ManPsbsService manPsbsService);

    /**
     * 新增订单管理
     * 
     * @param manPsbsService 订单管理
     * @return 结果
     */
    public int insertManPsbsService(ManPsbsService manPsbsService);

    /**
     * 修改订单管理
     * 
     * @param manPsbsService 订单管理
     * @return 结果
     */
    public int updateManPsbsService(ManPsbsService manPsbsService);

    /**
     * 批量删除订单管理
     * 
     * @param serids 需要删除的订单管理主键集合
     * @return 结果
     */
    public int deleteManPsbsServiceBySerids(Long[] serids);

    /**
     * 删除订单管理信息
     * 
     * @param serid 订单管理主键
     * @return 结果
     */
    public int deleteManPsbsServiceBySerid(Long serid);

    public List<ManPsbsService> selectTime(ManPsbsService manPsbsService);
}
