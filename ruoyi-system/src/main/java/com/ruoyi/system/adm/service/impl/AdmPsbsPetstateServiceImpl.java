package com.ruoyi.system.adm.service.impl;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.ruoyi.system.adm.mapper.AdmPsbsPetstateMapper;
import com.ruoyi.system.adm.domain.AdmPsbsPetstate;
import com.ruoyi.system.adm.service.IAdmPsbsPetstateService;

/**
 * 宠物健康Service业务层处理
 * 
 * @author 御泽
 * @date 2024-05-06
 */
@Service
public class AdmPsbsPetstateServiceImpl implements IAdmPsbsPetstateService 
{
    @Autowired
    private AdmPsbsPetstateMapper admPsbsPetstateMapper;

    /**
     * 查询宠物健康
     * 
     * @param pstid 宠物健康主键
     * @return 宠物健康
     */
    @Override
    public AdmPsbsPetstate selectAdmPsbsPetstateByPstid(Long pstid)
    {
        return admPsbsPetstateMapper.selectAdmPsbsPetstateByPstid(pstid);
    }

    /**
     * 查询宠物健康列表
     * 
     * @param admPsbsPetstate 宠物健康
     * @return 宠物健康
     */
    @Override
    public List<AdmPsbsPetstate> selectAdmPsbsPetstateList(AdmPsbsPetstate admPsbsPetstate)
    {
        return admPsbsPetstateMapper.selectAdmPsbsPetstateList(admPsbsPetstate);
    }

    /**
     * 新增宠物健康
     * 
     * @param admPsbsPetstate 宠物健康
     * @return 结果
     */
    @Override
    public int insertAdmPsbsPetstate(AdmPsbsPetstate admPsbsPetstate)
    {
        return admPsbsPetstateMapper.insertAdmPsbsPetstate(admPsbsPetstate);
    }

    /**
     * 修改宠物健康
     * 
     * @param admPsbsPetstate 宠物健康
     * @return 结果
     */
    @Override
    public int updateAdmPsbsPetstate(AdmPsbsPetstate admPsbsPetstate)
    {
        return admPsbsPetstateMapper.updateAdmPsbsPetstate(admPsbsPetstate);
    }

    /**
     * 批量删除宠物健康
     * 
     * @param pstids 需要删除的宠物健康主键
     * @return 结果
     */
    @Override
    public int deleteAdmPsbsPetstateByPstids(Long[] pstids)
    {
        return admPsbsPetstateMapper.deleteAdmPsbsPetstateByPstids(pstids);
    }

    /**
     * 删除宠物健康信息
     * 
     * @param pstid 宠物健康主键
     * @return 结果
     */
    @Override
    public int deleteAdmPsbsPetstateByPstid(Long pstid)
    {
        return admPsbsPetstateMapper.deleteAdmPsbsPetstateByPstid(pstid);
    }
}
