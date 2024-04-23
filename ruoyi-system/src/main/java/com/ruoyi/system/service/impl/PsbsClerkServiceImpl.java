package com.ruoyi.system.service.impl;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.ruoyi.system.mapper.PsbsClerkMapper;
import com.ruoyi.system.domain.PsbsClerk;
import com.ruoyi.system.service.IPsbsClerkService;

/**
 * 店员Service业务层处理
 * 
 * @author ruoyi
 * @date 2024-04-23
 */
@Service
public class PsbsClerkServiceImpl implements IPsbsClerkService 
{
    @Autowired
    private PsbsClerkMapper psbsClerkMapper;

    /**
     * 查询店员
     * 
     * @param clid 店员主键
     * @return 店员
     */
    @Override
    public PsbsClerk selectPsbsClerkByClid(Long clid)
    {
        return psbsClerkMapper.selectPsbsClerkByClid(clid);
    }

    /**
     * 查询店员列表
     * 
     * @param psbsClerk 店员
     * @return 店员
     */
    @Override
    public List<PsbsClerk> selectPsbsClerkList(PsbsClerk psbsClerk)
    {
        return psbsClerkMapper.selectPsbsClerkList(psbsClerk);
    }

    /**
     * 新增店员
     * 
     * @param psbsClerk 店员
     * @return 结果
     */
    @Override
    public int insertPsbsClerk(PsbsClerk psbsClerk)
    {
        return psbsClerkMapper.insertPsbsClerk(psbsClerk);
    }

    /**
     * 修改店员
     * 
     * @param psbsClerk 店员
     * @return 结果
     */
    @Override
    public int updatePsbsClerk(PsbsClerk psbsClerk)
    {
        return psbsClerkMapper.updatePsbsClerk(psbsClerk);
    }

    /**
     * 批量删除店员
     * 
     * @param clids 需要删除的店员主键
     * @return 结果
     */
    @Override
    public int deletePsbsClerkByClids(Long[] clids)
    {
        return psbsClerkMapper.deletePsbsClerkByClids(clids);
    }

    /**
     * 删除店员信息
     * 
     * @param clid 店员主键
     * @return 结果
     */
    @Override
    public int deletePsbsClerkByClid(Long clid)
    {
        return psbsClerkMapper.deletePsbsClerkByClid(clid);
    }
}
