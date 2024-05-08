package com.ruoyi.system.adm.service;

import java.util.List;
import com.ruoyi.system.adm.domain.AdmPsbsPetstate;

/**
 * 宠物健康Service接口
 * 
 * @author 御泽
 * @date 2024-05-06
 */
public interface IAdmPsbsPetstateService 
{
    /**
     * 查询宠物健康
     * 
     * @param pstid 宠物健康主键
     * @return 宠物健康
     */
    public AdmPsbsPetstate selectAdmPsbsPetstateByPstid(Long pstid);

    /**
     * 查询宠物健康列表
     * 
     * @param admPsbsPetstate 宠物健康
     * @return 宠物健康集合
     */
    public List<AdmPsbsPetstate> selectAdmPsbsPetstateList(AdmPsbsPetstate admPsbsPetstate);

    /**
     * 新增宠物健康
     * 
     * @param admPsbsPetstate 宠物健康
     * @return 结果
     */
    public int insertAdmPsbsPetstate(AdmPsbsPetstate admPsbsPetstate);

    /**
     * 修改宠物健康
     * 
     * @param admPsbsPetstate 宠物健康
     * @return 结果
     */
    public int updateAdmPsbsPetstate(AdmPsbsPetstate admPsbsPetstate);

    /**
     * 批量删除宠物健康
     * 
     * @param pstids 需要删除的宠物健康主键集合
     * @return 结果
     */
    public int deleteAdmPsbsPetstateByPstids(Long[] pstids);

    /**
     * 删除宠物健康信息
     * 
     * @param pstid 宠物健康主键
     * @return 结果
     */
    public int deleteAdmPsbsPetstateByPstid(Long pstid);
}
