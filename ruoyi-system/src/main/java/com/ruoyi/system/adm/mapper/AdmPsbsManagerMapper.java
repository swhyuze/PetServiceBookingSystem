package com.ruoyi.system.adm.mapper;

import java.util.List;
import com.ruoyi.system.adm.domain.AdmPsbsManager;

/**
 * 宠物店管理Mapper接口
 * 
 * @author 御泽
 * @date 2024-05-06
 */
public interface AdmPsbsManagerMapper 
{
    /**
     * 查询宠物店管理
     * 
     * @param mid 宠物店管理主键
     * @return 宠物店管理
     */
    public AdmPsbsManager selectAdmPsbsManagerByMid(Long mid);

    /**
     * 查询宠物店管理列表
     * 
     * @param admPsbsManager 宠物店管理
     * @return 宠物店管理集合
     */
    public List<AdmPsbsManager> selectAdmPsbsManagerList(AdmPsbsManager admPsbsManager);
    public List<AdmPsbsManager> selectAllAdmManager();

    /**
     * 新增宠物店管理
     * 
     * @param admPsbsManager 宠物店管理
     * @return 结果
     */
    public int insertAdmPsbsManager(AdmPsbsManager admPsbsManager);

    /**
     * 修改宠物店管理
     * 
     * @param admPsbsManager 宠物店管理
     * @return 结果
     */
    public int updateAdmPsbsManager(AdmPsbsManager admPsbsManager);

    /**
     * 删除宠物店管理
     * 
     * @param mid 宠物店管理主键
     * @return 结果
     */
    public int deleteAdmPsbsManagerByMid(Long mid);

    /**
     * 批量删除宠物店管理
     * 
     * @param mids 需要删除的数据主键集合
     * @return 结果
     */
    public int deleteAdmPsbsManagerByMids(Long[] mids);
}
