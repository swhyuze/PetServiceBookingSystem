package com.ruoyi.system.man.service.impl;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.ruoyi.system.man.mapper.ManPsbsServiceMapper;
import com.ruoyi.system.man.domain.ManPsbsService;
import com.ruoyi.system.man.service.IManPsbsServiceService;

/**
 * 订单管理Service业务层处理
 * 
 * @author 御泽
 * @date 2024-05-08
 */
@Service
public class ManPsbsServiceServiceImpl implements IManPsbsServiceService 
{
    @Autowired
    private ManPsbsServiceMapper manPsbsServiceMapper;

    /**
     * 查询订单管理
     * 
     * @param serid 订单管理主键
     * @return 订单管理
     */
    @Override
    public ManPsbsService selectManPsbsServiceBySerid(Long serid)
    {
        return manPsbsServiceMapper.selectManPsbsServiceBySerid(serid);
    }

    /**
     * 查询订单管理列表
     * 
     * @param manPsbsService 订单管理
     * @return 订单管理
     */
    @Override
    public List<ManPsbsService> selectManPsbsServiceList(ManPsbsService manPsbsService)
    {
        return manPsbsServiceMapper.selectManPsbsServiceList(manPsbsService);
    }

    /**
     * 新增订单管理
     * 
     * @param manPsbsService 订单管理
     * @return 结果
     */
    @Override
    public int insertManPsbsService(ManPsbsService manPsbsService)
    {
        return manPsbsServiceMapper.insertManPsbsService(manPsbsService);
    }

    /**
     * 修改订单管理
     * 
     * @param manPsbsService 订单管理
     * @return 结果
     */
    @Override
    public int updateManPsbsService(ManPsbsService manPsbsService)
    {
        return manPsbsServiceMapper.updateManPsbsService(manPsbsService);
    }

    /**
     * 批量删除订单管理
     * 
     * @param serids 需要删除的订单管理主键
     * @return 结果
     */
    @Override
    public int deleteManPsbsServiceBySerids(Long[] serids)
    {
        return manPsbsServiceMapper.deleteManPsbsServiceBySerids(serids);
    }

    /**
     * 删除订单管理信息
     * 
     * @param serid 订单管理主键
     * @return 结果
     */
    @Override
    public int deleteManPsbsServiceBySerid(Long serid)
    {
        return manPsbsServiceMapper.deleteManPsbsServiceBySerid(serid);
    }
    public List<ManPsbsService> selectTime(ManPsbsService manPsbsService){
        return manPsbsServiceMapper.selectTime(manPsbsService);
    }
}
