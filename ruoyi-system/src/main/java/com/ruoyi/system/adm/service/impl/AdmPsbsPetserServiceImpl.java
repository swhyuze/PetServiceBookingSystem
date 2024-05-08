package com.ruoyi.system.adm.service.impl;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.ruoyi.system.adm.mapper.AdmPsbsPetserMapper;
import com.ruoyi.system.adm.domain.AdmPsbsPetser;
import com.ruoyi.system.adm.service.IAdmPsbsPetserService;

/**
 * 宠物服务Service业务层处理
 * 
 * @author 御泽
 * @date 2024-05-06
 */
@Service
public class AdmPsbsPetserServiceImpl implements IAdmPsbsPetserService 
{
    @Autowired
    private AdmPsbsPetserMapper admPsbsPetserMapper;

    /**
     * 查询宠物服务
     * 
     * @param psid 宠物服务主键
     * @return 宠物服务
     */
    @Override
    public AdmPsbsPetser selectAdmPsbsPetserByPsid(Long psid)
    {
        return admPsbsPetserMapper.selectAdmPsbsPetserByPsid(psid);
    }

    /**
     * 查询宠物服务列表
     * 
     * @param admPsbsPetser 宠物服务
     * @return 宠物服务
     */
    @Override
    public List<AdmPsbsPetser> selectAdmPsbsPetserList(AdmPsbsPetser admPsbsPetser)
    {
        return admPsbsPetserMapper.selectAdmPsbsPetserList(admPsbsPetser);
    }

    /**
     * 新增宠物服务
     * 
     * @param admPsbsPetser 宠物服务
     * @return 结果
     */
    @Override
    public int insertAdmPsbsPetser(AdmPsbsPetser admPsbsPetser)
    {
        return admPsbsPetserMapper.insertAdmPsbsPetser(admPsbsPetser);
    }

    /**
     * 修改宠物服务
     * 
     * @param admPsbsPetser 宠物服务
     * @return 结果
     */
    @Override
    public int updateAdmPsbsPetser(AdmPsbsPetser admPsbsPetser)
    {
        return admPsbsPetserMapper.updateAdmPsbsPetser(admPsbsPetser);
    }

    /**
     * 批量删除宠物服务
     * 
     * @param psids 需要删除的宠物服务主键
     * @return 结果
     */
    @Override
    public int deleteAdmPsbsPetserByPsids(Long[] psids)
    {
        return admPsbsPetserMapper.deleteAdmPsbsPetserByPsids(psids);
    }

    /**
     * 删除宠物服务信息
     * 
     * @param psid 宠物服务主键
     * @return 结果
     */
    @Override
    public int deleteAdmPsbsPetserByPsid(Long psid)
    {
        return admPsbsPetserMapper.deleteAdmPsbsPetserByPsid(psid);
    }
}
