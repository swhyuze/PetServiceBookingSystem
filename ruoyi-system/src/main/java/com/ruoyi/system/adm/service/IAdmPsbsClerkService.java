package com.ruoyi.system.adm.service;

import java.util.List;
import com.ruoyi.system.adm.domain.AdmPsbsClerk;

/**
 * 店员管理Service接口
 * 
 * @author 御泽
 * @date 2024-05-03
 */
public interface IAdmPsbsClerkService 
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
     * 批量删除店员管理
     * 
     * @param clids 需要删除的店员管理主键集合
     * @return 结果
     */
    public int deleteAdmPsbsClerkByClids(Long[] clids);

    /**
     * 删除店员管理信息
     * 
     * @param clid 店员管理主键
     * @return 结果
     */
    public int deleteAdmPsbsClerkByClid(Long clid);
}
