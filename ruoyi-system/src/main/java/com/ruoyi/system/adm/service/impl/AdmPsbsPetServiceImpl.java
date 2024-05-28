package com.ruoyi.system.adm.service.impl;

import java.util.List;

import com.ruoyi.system.adm.domain.AdmPsbsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.ruoyi.system.adm.mapper.AdmPsbsPetMapper;
import com.ruoyi.system.adm.domain.AdmPsbsPet;
import com.ruoyi.system.adm.service.IAdmPsbsPetService;

/**
 * 宠物管理Service业务层处理
 * 
 * @author 御泽
 * @date 2024-05-06
 */
@Service
public class AdmPsbsPetServiceImpl implements IAdmPsbsPetService 
{
    @Autowired
    private AdmPsbsPetMapper admPsbsPetMapper;

    /**
     * 查询宠物管理
     * 
     * @param pid 宠物管理主键
     * @return 宠物管理
     */
    @Override
    public AdmPsbsPet selectAdmPsbsPetByPid(Long pid)
    {
        return admPsbsPetMapper.selectAdmPsbsPetByPid(pid);
    }

    /**
     * 查询宠物管理列表
     * 
     * @param admPsbsPet 宠物管理
     * @return 宠物管理
     */
    @Override
    public List<AdmPsbsPet> selectAdmPsbsPetList(AdmPsbsPet admPsbsPet)
    {
        return admPsbsPetMapper.selectAdmPsbsPetList(admPsbsPet);
    }

    /**
     * 新增宠物管理
     * 
     * @param admPsbsPet 宠物管理
     * @return 结果
     */
    @Override
    public int insertAdmPsbsPet(AdmPsbsPet admPsbsPet)
    {
        return admPsbsPetMapper.insertAdmPsbsPet(admPsbsPet);
    }

    /**
     * 修改宠物管理
     * 
     * @param admPsbsPet 宠物管理
     * @return 结果
     */
    @Override
    public int updateAdmPsbsPet(AdmPsbsPet admPsbsPet)
    {
        return admPsbsPetMapper.updateAdmPsbsPet(admPsbsPet);
    }

    /**
     * 批量删除宠物管理
     * 
     * @param pids 需要删除的宠物管理主键
     * @return 结果
     */
    @Override
    public int deleteAdmPsbsPetByPids(Long[] pids)
    {
        return admPsbsPetMapper.deleteAdmPsbsPetByPids(pids);
    }

    /**
     * 删除宠物管理信息
     * 
     * @param pid 宠物管理主键
     * @return 结果
     */
    @Override
    public int deleteAdmPsbsPetByPid(Long pid)
    {
        return admPsbsPetMapper.deleteAdmPsbsPetByPid(pid);
    }

    @Override
    public List<AdmPsbsPet> selectPet(AdmPsbsService admPsbsService){
        return admPsbsPetMapper.selectPet(admPsbsService);
    }
}
