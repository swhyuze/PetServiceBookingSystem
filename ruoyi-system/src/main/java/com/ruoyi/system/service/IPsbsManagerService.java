package com.ruoyi.system.service;

import java.util.List;
import com.ruoyi.system.domain.PsbsManager;

/**
 * 宠物店管理Service接口
 * 
 * @author ruoyi
 * @date 2024-04-23
 */
public interface IPsbsManagerService 
{
    /**
     * 查询宠物店管理
     * 
     * @param mid 宠物店管理主键
     * @return 宠物店管理
     */
    public PsbsManager selectPsbsManagerByMid(Long mid);

    /**
     * 查询宠物店管理列表
     * 
     * @param psbsManager 宠物店管理
     * @return 宠物店管理集合
     */
    public List<PsbsManager> selectPsbsManagerList(PsbsManager psbsManager);

    /**
     * 新增宠物店管理
     * 
     * @param psbsManager 宠物店管理
     * @return 结果
     */
    public int insertPsbsManager(PsbsManager psbsManager);

    /**
     * 修改宠物店管理
     * 
     * @param psbsManager 宠物店管理
     * @return 结果
     */
    public int updatePsbsManager(PsbsManager psbsManager);

    /**
     * 批量删除宠物店管理
     * 
     * @param mids 需要删除的宠物店管理主键集合
     * @return 结果
     */
    public int deletePsbsManagerByMids(Long[] mids);

    /**
     * 删除宠物店管理信息
     * 
     * @param mid 宠物店管理主键
     * @return 结果
     */
    public int deletePsbsManagerByMid(Long mid);
}