package com.ruoyi.system.cum.mapper;

import java.util.List;
import com.ruoyi.system.cum.domain.CumPsbsPet;
import com.ruoyi.system.adm.domain.AdmPsbsPettp;

/**
 * 宠物管理Mapper接口
 * 
 * @author 御泽
 * @date 2024-05-08
 */
public interface CumPsbsPetMapper 
{
    /**
     * 查询宠物管理
     * 
     * @param pid 宠物管理主键
     * @return 宠物管理
     */
    public CumPsbsPet selectCumPsbsPetByPid(Long pid);

    /**
     * 查询宠物管理列表
     * 
     * @param cumPsbsPet 宠物管理
     * @return 宠物管理集合
     */
    public List<CumPsbsPet> selectCumPsbsPetList(CumPsbsPet cumPsbsPet);

    /**
     * 新增宠物管理
     * 
     * @param cumPsbsPet 宠物管理
     * @return 结果
     */
    public int insertCumPsbsPet(CumPsbsPet cumPsbsPet);

    /**
     * 修改宠物管理
     * 
     * @param cumPsbsPet 宠物管理
     * @return 结果
     */
    public int updateCumPsbsPet(CumPsbsPet cumPsbsPet);

    /**
     * 删除宠物管理
     * 
     * @param pid 宠物管理主键
     * @return 结果
     */
    public int deleteCumPsbsPetByPid(Long pid);

    /**
     * 批量删除宠物管理
     * 
     * @param pids 需要删除的数据主键集合
     * @return 结果
     */
    public int deleteCumPsbsPetByPids(Long[] pids);
}
