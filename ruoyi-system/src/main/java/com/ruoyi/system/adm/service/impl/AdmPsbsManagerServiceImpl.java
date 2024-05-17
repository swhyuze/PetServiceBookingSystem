package com.ruoyi.system.adm.service.impl;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.ruoyi.system.adm.mapper.AdmPsbsManagerMapper;
import com.ruoyi.system.adm.domain.AdmPsbsManager;
import com.ruoyi.system.adm.service.IAdmPsbsManagerService;

/**
 * 宠物店管理Service业务层处理
 * 
 * @author 御泽
 * @date 2024-05-06
 */
@Service
public class AdmPsbsManagerServiceImpl implements IAdmPsbsManagerService 
{
    @Autowired
    private AdmPsbsManagerMapper admPsbsManagerMapper;

    /**
     * 查询宠物店管理
     * 
     * @param mid 宠物店管理主键
     * @return 宠物店管理
     */
    @Override
    public AdmPsbsManager selectAdmPsbsManagerByMid(Long mid)
    {
        return admPsbsManagerMapper.selectAdmPsbsManagerByMid(mid);
    }

    /**
     * 查询宠物店管理列表
     * 
     * @param admPsbsManager 宠物店管理
     * @return 宠物店管理
     */
    @Override
    public List<AdmPsbsManager> selectAdmPsbsManagerList(AdmPsbsManager admPsbsManager)
    {
        return admPsbsManagerMapper.selectAdmPsbsManagerList(admPsbsManager);
    }

    public List<AdmPsbsManager> selectAllAdmManager()
    {
        return admPsbsManagerMapper.selectAllAdmManager();
    }
    /**
     * 新增宠物店管理
     * 
     * @param admPsbsManager 宠物店管理
     * @return 结果
     */
    @Override
    public int insertAdmPsbsManager(AdmPsbsManager admPsbsManager)
    {
        return admPsbsManagerMapper.insertAdmPsbsManager(admPsbsManager);
    }

    /**
     * 修改宠物店管理
     * 
     * @param admPsbsManager 宠物店管理
     * @return 结果
     */
    @Override
    public int updateAdmPsbsManager(AdmPsbsManager admPsbsManager)
    {
        return admPsbsManagerMapper.updateAdmPsbsManager(admPsbsManager);
    }

    /**
     * 批量删除宠物店管理
     * 
     * @param mids 需要删除的宠物店管理主键
     * @return 结果
     */
    @Override
    public int deleteAdmPsbsManagerByMids(Long[] mids)
    {
        return admPsbsManagerMapper.deleteAdmPsbsManagerByMids(mids);
    }

    /**
     * 删除宠物店管理信息
     * 
     * @param mid 宠物店管理主键
     * @return 结果
     */
    @Override
    public int deleteAdmPsbsManagerByMid(Long mid)
    {
        return admPsbsManagerMapper.deleteAdmPsbsManagerByMid(mid);
    }
}
