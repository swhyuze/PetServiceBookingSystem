package com.ruoyi.system.cum.service.impl;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.ruoyi.system.cum.mapper.CumPsbsPetMapper;
import com.ruoyi.system.cum.domain.CumPsbsPet;
import com.ruoyi.system.cum.service.ICumPsbsPetService;

/**
 * 宠物管理Service业务层处理
 * 
 * @author 御泽
 * @date 2024-05-08
 */
@Service
public class CumPsbsPetServiceImpl implements ICumPsbsPetService 
{
    @Autowired
    private CumPsbsPetMapper cumPsbsPetMapper;

    /**
     * 查询宠物管理
     * 
     * @param pid 宠物管理主键
     * @return 宠物管理
     */
    @Override
    public CumPsbsPet selectCumPsbsPetByPid(Long pid)
    {
        return cumPsbsPetMapper.selectCumPsbsPetByPid(pid);
    }

    /**
     * 查询宠物管理列表
     * 
     * @param cumPsbsPet 宠物管理
     * @return 宠物管理
     */
    @Override
    public List<CumPsbsPet> selectCumPsbsPetList(CumPsbsPet cumPsbsPet)
    {
        return cumPsbsPetMapper.selectCumPsbsPetList(cumPsbsPet);
    }

    /**
     * 新增宠物管理
     * 
     * @param cumPsbsPet 宠物管理
     * @return 结果
     */
    @Override
    public int insertCumPsbsPet(CumPsbsPet cumPsbsPet)
    {
        return cumPsbsPetMapper.insertCumPsbsPet(cumPsbsPet);
    }

    /**
     * 修改宠物管理
     * 
     * @param cumPsbsPet 宠物管理
     * @return 结果
     */
    @Override
    public int updateCumPsbsPet(CumPsbsPet cumPsbsPet)
    {
        return cumPsbsPetMapper.updateCumPsbsPet(cumPsbsPet);
    }

    /**
     * 批量删除宠物管理
     * 
     * @param pids 需要删除的宠物管理主键
     * @return 结果
     */
    @Override
    public int deleteCumPsbsPetByPids(Long[] pids)
    {
        return cumPsbsPetMapper.deleteCumPsbsPetByPids(pids);
    }

    /**
     * 删除宠物管理信息
     * 
     * @param pid 宠物管理主键
     * @return 结果
     */
    @Override
    public int deleteCumPsbsPetByPid(Long pid)
    {
        return cumPsbsPetMapper.deleteCumPsbsPetByPid(pid);
    }
}
