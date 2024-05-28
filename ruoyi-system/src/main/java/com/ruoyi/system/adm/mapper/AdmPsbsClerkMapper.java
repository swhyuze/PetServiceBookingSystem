package com.ruoyi.system.adm.mapper;

import java.util.List;
import com.ruoyi.system.adm.domain.AdmPsbsClerk;
import com.ruoyi.system.adm.domain.AdmPsbsClkser;

/**
 * 店员管理Mapper接口
 * 
 * @author 御泽
 * @date 2024-05-06
 */
public interface AdmPsbsClerkMapper 
{
    /**
     * 查询店员管理
     * 
     * @param clid 店员管理主键
     * @return 店员管理
     */
    public AdmPsbsClerk selectAdmPsbsClerkByClid(Long clid);

    /**
     * 查询店员管理列表
     * 
     * @param admPsbsClerk 店员管理
     * @return 店员管理集合
     */
    public List<AdmPsbsClerk> selectAdmPsbsClerkList(AdmPsbsClerk admPsbsClerk);

    /**
     * 新增店员管理
     * 
     * @param admPsbsClerk 店员管理
     * @return 结果
     */
    public int insertAdmPsbsClerk(AdmPsbsClerk admPsbsClerk);

    /**
     * 修改店员管理
     * 
     * @param admPsbsClerk 店员管理
     * @return 结果
     */
    public int updateAdmPsbsClerk(AdmPsbsClerk admPsbsClerk);

    /**
     * 删除店员管理
     * 
     * @param clid 店员管理主键
     * @return 结果
     */
    public int deleteAdmPsbsClerkByClid(Long clid);

    /**
     * 批量删除店员管理
     * 
     * @param clids 需要删除的数据主键集合
     * @return 结果
     */
    public int deleteAdmPsbsClerkByClids(Long[] clids);

    /**
     * 批量删除店员服务
     * 
     * @param clids 需要删除的数据主键集合
     * @return 结果
     */
    public int deleteAdmPsbsClkserByClids(Long[] clids);
    
    /**
     * 批量新增店员服务
     * 
     * @param admPsbsClkserList 店员服务列表
     * @return 结果
     */
    public int batchAdmPsbsClkser(List<AdmPsbsClkser> admPsbsClkserList);
    

    /**
     * 通过店员管理主键删除店员服务信息
     * 
     * @param clid 店员管理ID
     * @return 结果
     */
    public int deleteAdmPsbsClkserByClid(Long clid);
    public List<AdmPsbsClerk> selectClerk(AdmPsbsClerk admPsbsClerk);
}
