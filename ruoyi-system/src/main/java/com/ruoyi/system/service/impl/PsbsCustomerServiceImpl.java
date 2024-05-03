package com.ruoyi.system.service.impl;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.ruoyi.system.mapper.PsbsCustomerMapper;
import com.ruoyi.system.domain.PsbsCustomer;
import com.ruoyi.system.service.IPsbsCustomerService;

/**
 * 顾客管理Service业务层处理
 * 
 * @author 御泽
 * @date 2024-05-03
 */
@Service
public class PsbsCustomerServiceImpl implements IPsbsCustomerService 
{
    @Autowired
    private PsbsCustomerMapper psbsCustomerMapper;

    /**
     * 查询顾客管理
     * 
     * @param cuid 顾客管理主键
     * @return 顾客管理
     */
    @Override
    public PsbsCustomer selectPsbsCustomerByCuid(Long cuid)
    {
        return psbsCustomerMapper.selectPsbsCustomerByCuid(cuid);
    }

    /**
     * 查询顾客管理列表
     * 
     * @param psbsCustomer 顾客管理
     * @return 顾客管理
     */
    @Override
    public List<PsbsCustomer> selectPsbsCustomerList(PsbsCustomer psbsCustomer)
    {
        return psbsCustomerMapper.selectPsbsCustomerList(psbsCustomer);
    }

    /**
     * 新增顾客管理
     * 
     * @param psbsCustomer 顾客管理
     * @return 结果
     */
    @Override
    public int insertPsbsCustomer(PsbsCustomer psbsCustomer)
    {
        return psbsCustomerMapper.insertPsbsCustomer(psbsCustomer);
    }

    /**
     * 修改顾客管理
     * 
     * @param psbsCustomer 顾客管理
     * @return 结果
     */
    @Override
    public int updatePsbsCustomer(PsbsCustomer psbsCustomer)
    {
        return psbsCustomerMapper.updatePsbsCustomer(psbsCustomer);
    }

    /**
     * 批量删除顾客管理
     * 
     * @param cuids 需要删除的顾客管理主键
     * @return 结果
     */
    @Override
    public int deletePsbsCustomerByCuids(Long[] cuids)
    {
        return psbsCustomerMapper.deletePsbsCustomerByCuids(cuids);
    }

    /**
     * 删除顾客管理信息
     * 
     * @param cuid 顾客管理主键
     * @return 结果
     */
    @Override
    public int deletePsbsCustomerByCuid(Long cuid)
    {
        return psbsCustomerMapper.deletePsbsCustomerByCuid(cuid);
    }
}
