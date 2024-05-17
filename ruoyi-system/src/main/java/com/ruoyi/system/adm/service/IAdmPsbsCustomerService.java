package com.ruoyi.system.adm.service;

import java.util.List;
import com.ruoyi.system.adm.domain.AdmPsbsCustomer;

/**
 * 顾客管理Service接口
 * 
 * @author 御泽
 * @date 2024-05-06
 */
public interface IAdmPsbsCustomerService 
{
    /**
     * 查询顾客管理
     * 
     * @param cuid 顾客管理主键
     * @return 顾客管理
     */
    public AdmPsbsCustomer selectAdmPsbsCustomerByCuid(Long cuid);

    public AdmPsbsCustomer selectAdmPsbsCustomerByUid(Long uid);
    /**
     * 查询顾客管理列表
     * 
     * @param admPsbsCustomer 顾客管理
     * @return 顾客管理集合
     */
    public List<AdmPsbsCustomer> selectAdmPsbsCustomerList(AdmPsbsCustomer admPsbsCustomer);

    /**
     * 新增顾客管理
     * 
     * @param admPsbsCustomer 顾客管理
     * @return 结果
     */
    public int insertAdmPsbsCustomer(AdmPsbsCustomer admPsbsCustomer);

    /**
     * 修改顾客管理
     * 
     * @param admPsbsCustomer 顾客管理
     * @return 结果
     */
    public int updateAdmPsbsCustomer(AdmPsbsCustomer admPsbsCustomer);

    /**
     * 批量删除顾客管理
     * 
     * @param cuids 需要删除的顾客管理主键集合
     * @return 结果
     */
    public int deleteAdmPsbsCustomerByCuids(Long[] cuids);

    /**
     * 删除顾客管理信息
     * 
     * @param cuid 顾客管理主键
     * @return 结果
     */
    public int deleteAdmPsbsCustomerByCuid(Long cuid);
}
