package com.ruoyi.system.cum.service.impl;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.ruoyi.system.cum.mapper.CumPsbsServiceMapper;
import com.ruoyi.system.cum.domain.CumPsbsService;
import com.ruoyi.system.cum.service.ICumPsbsServiceService;

/**
 * 订单查询Service业务层处理
 * 
 * @author 御泽
 * @date 2024-05-08
 */
@Service
public class CumPsbsServiceServiceImpl implements ICumPsbsServiceService 
{
    @Autowired
    private CumPsbsServiceMapper cumPsbsServiceMapper;

    /**
     * 查询订单查询
     * 
     * @param serid 订单查询主键
     * @return 订单查询
     */
    @Override
    public CumPsbsService selectCumPsbsServiceBySerid(Long serid)
    {
        return cumPsbsServiceMapper.selectCumPsbsServiceBySerid(serid);
    }

    /**
     * 查询订单查询列表
     * 
     * @param cumPsbsService 订单查询
     * @return 订单查询
     */
    @Override
    public List<CumPsbsService> selectCumPsbsServiceList(CumPsbsService cumPsbsService)
    {
        return cumPsbsServiceMapper.selectCumPsbsServiceList(cumPsbsService);
    }

    /**
     * 新增订单查询
     * 
     * @param cumPsbsService 订单查询
     * @return 结果
     */
    @Override
    public int insertCumPsbsService(CumPsbsService cumPsbsService)
    {
        return cumPsbsServiceMapper.insertCumPsbsService(cumPsbsService);
    }

    /**
     * 修改订单查询
     * 
     * @param cumPsbsService 订单查询
     * @return 结果
     */
    @Override
    public int updateCumPsbsService(CumPsbsService cumPsbsService)
    {
        return cumPsbsServiceMapper.updateCumPsbsService(cumPsbsService);
    }

    /**
     * 批量删除订单查询
     * 
     * @param serids 需要删除的订单查询主键
     * @return 结果
     */
    @Override
    public int deleteCumPsbsServiceBySerids(Long[] serids)
    {
        return cumPsbsServiceMapper.deleteCumPsbsServiceBySerids(serids);
    }

    /**
     * 删除订单查询信息
     * 
     * @param serid 订单查询主键
     * @return 结果
     */
    @Override
    public int deleteCumPsbsServiceBySerid(Long serid)
    {
        return cumPsbsServiceMapper.deleteCumPsbsServiceBySerid(serid);
    }
}
