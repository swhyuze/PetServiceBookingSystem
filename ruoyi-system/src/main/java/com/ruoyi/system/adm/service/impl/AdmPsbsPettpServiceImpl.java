package com.ruoyi.system.adm.service.impl;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.ruoyi.system.adm.mapper.AdmPsbsPettpMapper;
import com.ruoyi.system.adm.domain.AdmPsbsPettp;
import com.ruoyi.system.adm.service.IAdmPsbsPettpService;

/**
 * 宠物品种Service业务层处理
 * 
 * @author 御泽
 * @date 2024-05-03
 */
@Service
public class AdmPsbsPettpServiceImpl implements IAdmPsbsPettpService 
{
    @Autowired
    private AdmPsbsPettpMapper admPsbsPettpMapper;

    /**
     * 查询宠物品种
     * 
     * @param ptid 宠物品种主键
     * @return 宠物品种
     */
    @Override
    public AdmPsbsPettp selectAdmPsbsPettpByPtid(Long ptid)
    {
        return admPsbsPettpMapper.selectAdmPsbsPettpByPtid(ptid);
    }

    /**
     * 查询宠物品种列表
     * 
     * @param admPsbsPettp 宠物品种
     * @return 宠物品种
     */
    @Override
    public List<AdmPsbsPettp> selectAdmPsbsPettpList(AdmPsbsPettp admPsbsPettp)
    {
        return admPsbsPettpMapper.selectAdmPsbsPettpList(admPsbsPettp);
    }

    /**
     * 新增宠物品种
     * 
     * @param admPsbsPettp 宠物品种
     * @return 结果
     */
    @Override
    public int insertAdmPsbsPettp(AdmPsbsPettp admPsbsPettp)
    {
        return admPsbsPettpMapper.insertAdmPsbsPettp(admPsbsPettp);
    }

    /**
     * 修改宠物品种
     * 
     * @param admPsbsPettp 宠物品种
     * @return 结果
     */
    @Override
    public int updateAdmPsbsPettp(AdmPsbsPettp admPsbsPettp)
    {
        return admPsbsPettpMapper.updateAdmPsbsPettp(admPsbsPettp);
    }

    /**
     * 批量删除宠物品种
     * 
     * @param ptids 需要删除的宠物品种主键
     * @return 结果
     */
    @Override
    public int deleteAdmPsbsPettpByPtids(Long[] ptids)
    {
        return admPsbsPettpMapper.deleteAdmPsbsPettpByPtids(ptids);
    }

    /**
     * 删除宠物品种信息
     * 
     * @param ptid 宠物品种主键
     * @return 结果
     */
    @Override
    public int deleteAdmPsbsPettpByPtid(Long ptid)
    {
        return admPsbsPettpMapper.deleteAdmPsbsPettpByPtid(ptid);
    }
}
