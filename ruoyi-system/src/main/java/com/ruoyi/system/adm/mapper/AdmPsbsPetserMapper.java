package com.ruoyi.system.adm.mapper;

import java.util.List;
import com.ruoyi.system.adm.domain.AdmPsbsPetser;

/**
 * 宠物服务Mapper接口
 * 
 * @author 御泽
 * @date 2024-05-06
 */
public interface AdmPsbsPetserMapper 
{
    /**
     * 查询宠物服务
     * 
     * @param psid 宠物服务主键
     * @return 宠物服务
     */
    public AdmPsbsPetser selectAdmPsbsPetserByPsid(Long psid);

    /**
     * 查询宠物服务列表
     * 
     * @param admPsbsPetser 宠物服务
     * @return 宠物服务集合
     */
    public List<AdmPsbsPetser> selectAdmPsbsPetserList(AdmPsbsPetser admPsbsPetser);

    /**
     * 新增宠物服务
     * 
     * @param admPsbsPetser 宠物服务
     * @return 结果
     */
    public int insertAdmPsbsPetser(AdmPsbsPetser admPsbsPetser);

    /**
     * 修改宠物服务
     * 
     * @param admPsbsPetser 宠物服务
     * @return 结果
     */
    public int updateAdmPsbsPetser(AdmPsbsPetser admPsbsPetser);

    /**
     * 删除宠物服务
     * 
     * @param psid 宠物服务主键
     * @return 结果
     */
    public int deleteAdmPsbsPetserByPsid(Long psid);

    /**
     * 批量删除宠物服务
     * 
     * @param psids 需要删除的数据主键集合
     * @return 结果
     */
    public int deleteAdmPsbsPetserByPsids(Long[] psids);
}
