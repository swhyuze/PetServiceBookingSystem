package com.ruoyi.system.adm.service;

import java.util.List;
import com.ruoyi.system.adm.domain.AdmPsbsPet;

/**
 * 宠物管理Service接口
 * 
 * @author 御泽
 * @date 2024-05-06
 */
public interface IAdmPsbsPetService 
{
    /**
     * 查询宠物管理
     * 
     * @param pid 宠物管理主键
     * @return 宠物管理
     */
    public AdmPsbsPet selectAdmPsbsPetByPid(Long pid);

    /**
     * 查询宠物管理列表
     * 
     * @param admPsbsPet 宠物管理
     * @return 宠物管理集合
     */
    public List<AdmPsbsPet> selectAdmPsbsPetList(AdmPsbsPet admPsbsPet);

    /**
     * 新增宠物管理
     * 
     * @param admPsbsPet 宠物管理
     * @return 结果
     */
    public int insertAdmPsbsPet(AdmPsbsPet admPsbsPet);

    /**
     * 修改宠物管理
     * 
     * @param admPsbsPet 宠物管理
     * @return 结果
     */
    public int updateAdmPsbsPet(AdmPsbsPet admPsbsPet);

    /**
     * 批量删除宠物管理
     * 
     * @param pids 需要删除的宠物管理主键集合
     * @return 结果
     */
    public int deleteAdmPsbsPetByPids(Long[] pids);

    /**
     * 删除宠物管理信息
     * 
     * @param pid 宠物管理主键
     * @return 结果
     */
    public int deleteAdmPsbsPetByPid(Long pid);
}
