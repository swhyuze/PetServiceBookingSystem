package com.ruoyi.system.adm.mapper;

import java.util.List;
import com.ruoyi.system.adm.domain.AdmPsbsPet;
import com.ruoyi.system.adm.domain.AdmPsbsService;

/**
 * 宠物管理Mapper接口
 * 
 * @author 御泽
 * @date 2024-05-06
 */
public interface AdmPsbsPetMapper 
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
     * 删除宠物管理
     * 
     * @param pid 宠物管理主键
     * @return 结果
     */
    public int deleteAdmPsbsPetByPid(Long pid);

    /**
     * 批量删除宠物管理
     * 
     * @param pids 需要删除的数据主键集合
     * @return 结果
     */
    public int deleteAdmPsbsPetByPids(Long[] pids);
    List<AdmPsbsPet> selectPet(AdmPsbsService admPsbsService);
}
