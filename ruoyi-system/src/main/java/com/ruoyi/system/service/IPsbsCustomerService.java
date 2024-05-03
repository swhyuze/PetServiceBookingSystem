package com.ruoyi.system.service;

import java.util.List;
import com.ruoyi.system.domain.PsbsCustomer;

/**
 * 顾客管理Service接口
 * 
 * @author 御泽
 * @date 2024-05-03
 */
public interface IPsbsCustomerService 
{
    /**
     * 查询顾客管理
     * 
     * @param cuid 顾客管理主键
     * @return 顾客管理
     */
    public PsbsCustomer selectPsbsCustomerByCuid(Long cuid);

    /**
     * 查询顾客管理列表
     * 
     * @param psbsCustomer 顾客管理
     * @return 顾客管理集合
     */
    public List<PsbsCustomer> selectPsbsCustomerList(PsbsCustomer psbsCustomer);

    /**
     * 新增顾客管理
     * 
     * @param psbsCustomer 顾客管理
     * @return 结果
     */
    public int insertPsbsCustomer(PsbsCustomer psbsCustomer);

    /**
     * 修改顾客管理
     * 
     * @param psbsCustomer 顾客管理
     * @return 结果
     */
    public int updatePsbsCustomer(PsbsCustomer psbsCustomer);

    /**
     * 批量删除顾客管理
     * 
     * @param cuids 需要删除的顾客管理主键集合
     * @return 结果
     */
    public int deletePsbsCustomerByCuids(Long[] cuids);

    /**
     * 删除顾客管理信息
     * 
     * @param cuid 顾客管理主键
     * @return 结果
     */
    public int deletePsbsCustomerByCuid(Long cuid);
}
