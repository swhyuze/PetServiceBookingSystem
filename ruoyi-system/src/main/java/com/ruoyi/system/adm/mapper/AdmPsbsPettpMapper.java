package com.ruoyi.system.adm.mapper;

import java.util.List;
import com.ruoyi.system.adm.domain.AdmPsbsPettp;

/**
 * 宠物品种Mapper接口
 * 
 * @author 御泽
 * @date 2024-05-06
 */
public interface AdmPsbsPettpMapper 
{
    /**
     * 查询宠物品种
     * 
     * @param ptid 宠物品种主键
     * @return 宠物品种
     */
    public AdmPsbsPettp selectAdmPsbsPettpByPtid(Long ptid);

    /**
     * 查询宠物品种列表
     * 
     * @param admPsbsPettp 宠物品种
     * @return 宠物品种集合
     */
    public List<AdmPsbsPettp> selectAdmPsbsPettpList(AdmPsbsPettp admPsbsPettp);

    /**
     * 新增宠物品种
     * 
     * @param admPsbsPettp 宠物品种
     * @return 结果
     */
    public int insertAdmPsbsPettp(AdmPsbsPettp admPsbsPettp);

    /**
     * 修改宠物品种
     * 
     * @param admPsbsPettp 宠物品种
     * @return 结果
     */
    public int updateAdmPsbsPettp(AdmPsbsPettp admPsbsPettp);

    /**
     * 删除宠物品种
     * 
     * @param ptid 宠物品种主键
     * @return 结果
     */
    public int deleteAdmPsbsPettpByPtid(Long ptid);

    /**
     * 批量删除宠物品种
     * 
     * @param ptids 需要删除的数据主键集合
     * @return 结果
     */
    public int deleteAdmPsbsPettpByPtids(Long[] ptids);
}
