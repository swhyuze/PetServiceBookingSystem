package com.ruoyi.system.adm.service.impl;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.ruoyi.system.adm.mapper.AdmPsbsCustomerMapper;
import com.ruoyi.system.adm.domain.AdmPsbsCustomer;
import com.ruoyi.system.adm.service.IAdmPsbsCustomerService;

/**
 * 顾客管理Service业务层处理
 * 
 * @author 御泽
 * @date 2024-05-06
 */
@Service
public class AdmPsbsCustomerServiceImpl implements IAdmPsbsCustomerService 
{
    @Autowired
    private AdmPsbsCustomerMapper admPsbsCustomerMapper;

    /**
     * 查询顾客管理
     * 
     * @param cuid 顾客管理主键
     * @return 顾客管理
     */
    @Override
    public AdmPsbsCustomer selectAdmPsbsCustomerByCuid(Long cuid)
    {
        return admPsbsCustomerMapper.selectAdmPsbsCustomerByCuid(cuid);
    }

    /**
     * 查询顾客管理列表
     * 
     * @param admPsbsCustomer 顾客管理
     * @return 顾客管理
     */
    @Override
    public List<AdmPsbsCustomer> selectAdmPsbsCustomerList(AdmPsbsCustomer admPsbsCustomer)
    {
        return admPsbsCustomerMapper.selectAdmPsbsCustomerList(admPsbsCustomer);
    }

    /**
     * 新增顾客管理
     * 
     * @param admPsbsCustomer 顾客管理
     * @return 结果
     */
    @Override
    public int insertAdmPsbsCustomer(AdmPsbsCustomer admPsbsCustomer)
    {
        return admPsbsCustomerMapper.insertAdmPsbsCustomer(admPsbsCustomer);
    }

    /**
     * 修改顾客管理
     * 
     * @param admPsbsCustomer 顾客管理
     * @return 结果
     */
    @Override
    public int updateAdmPsbsCustomer(AdmPsbsCustomer admPsbsCustomer)
    {
        return admPsbsCustomerMapper.updateAdmPsbsCustomer(admPsbsCustomer);
    }

    /**
     * 批量删除顾客管理
     * 
     * @param cuids 需要删除的顾客管理主键
     * @return 结果
     */
    @Override
    public int deleteAdmPsbsCustomerByCuids(Long[] cuids)
    {
        return admPsbsCustomerMapper.deleteAdmPsbsCustomerByCuids(cuids);
    }

    /**
     * 删除顾客管理信息
     * 
     * @param cuid 顾客管理主键
     * @return 结果
     */
    @Override
    public int deleteAdmPsbsCustomerByCuid(Long cuid)
    {
        return admPsbsCustomerMapper.deleteAdmPsbsCustomerByCuid(cuid);
    }
}
