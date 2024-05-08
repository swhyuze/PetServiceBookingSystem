package com.ruoyi.system.man.mapper;

import java.util.List;
import com.ruoyi.system.man.domain.ManPsbsService;

/**
 * 订单管理Mapper接口
 * 
 * @author 御泽
 * @date 2024-05-08
 */
public interface ManPsbsServiceMapper 
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
     * 删除订单管理
     * 
     * @param serid 订单管理主键
     * @return 结果
     */
    public int deleteManPsbsServiceBySerid(Long serid);

    /**
     * 批量删除订单管理
     * 
     * @param serids 需要删除的数据主键集合
     * @return 结果
     */
    public int deleteManPsbsServiceBySerids(Long[] serids);
}
