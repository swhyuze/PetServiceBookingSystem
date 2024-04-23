package com.ruoyi.system.service.impl;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.ruoyi.system.mapper.PsbsManagerMapper;
import com.ruoyi.system.domain.PsbsManager;
import com.ruoyi.system.service.IPsbsManagerService;

/**
 * 宠物店管理Service业务层处理
 * 
 * @author ruoyi
 * @date 2024-04-23
 */
@Service
public class PsbsManagerServiceImpl implements IPsbsManagerService 
{
    @Autowired
    private PsbsManagerMapper psbsManagerMapper;

    /**
     * 查询宠物店管理
     * 
     * @param mid 宠物店管理主键
     * @return 宠物店管理
     */
    @Override
    public PsbsManager selectPsbsManagerByMid(Long mid)
    {
        return psbsManagerMapper.selectPsbsManagerByMid(mid);
    }

    /**
     * 查询宠物店管理列表
     * 
     * @param psbsManager 宠物店管理
     * @return 宠物店管理
     */
    @Override
    public List<PsbsManager> selectPsbsManagerList(PsbsManager psbsManager)
    {
        return psbsManagerMapper.selectPsbsManagerList(psbsManager);
    }

    /**
     * 新增宠物店管理
     * 
     * @param psbsManager 宠物店管理
     * @return 结果
     */
    @Override
    public int insertPsbsManager(PsbsManager psbsManager)
    {
        return psbsManagerMapper.insertPsbsManager(psbsManager);
    }

    /**
     * 修改宠物店管理
     * 
     * @param psbsManager 宠物店管理
     * @return 结果
     */
    @Override
    public int updatePsbsManager(PsbsManager psbsManager)
    {
        return psbsManagerMapper.updatePsbsManager(psbsManager);
    }

    /**
     * 批量删除宠物店管理
     * 
     * @param mids 需要删除的宠物店管理主键
     * @return 结果
     */
    @Override
    public int deletePsbsManagerByMids(Long[] mids)
    {
        return psbsManagerMapper.deletePsbsManagerByMids(mids);
    }

    /**
     * 删除宠物店管理信息
     * 
     * @param mid 宠物店管理主键
     * @return 结果
     */
    @Override
    public int deletePsbsManagerByMid(Long mid)
    {
        return psbsManagerMapper.deletePsbsManagerByMid(mid);
    }
}
