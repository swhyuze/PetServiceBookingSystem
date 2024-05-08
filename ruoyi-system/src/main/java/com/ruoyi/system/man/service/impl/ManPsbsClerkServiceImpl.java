package com.ruoyi.system.man.service.impl;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.ArrayList;
import com.ruoyi.common.utils.StringUtils;
import org.springframework.transaction.annotation.Transactional;
import com.ruoyi.system.man.domain.ManPsbsClkser;
import com.ruoyi.system.man.mapper.ManPsbsClerkMapper;
import com.ruoyi.system.man.domain.ManPsbsClerk;
import com.ruoyi.system.man.service.IManPsbsClerkService;

/**
 * 店员管理Service业务层处理
 * 
 * @author 御泽
 * @date 2024-05-08
 */
@Service
public class ManPsbsClerkServiceImpl implements IManPsbsClerkService 
{
    @Autowired
    private ManPsbsClerkMapper manPsbsClerkMapper;

    /**
     * 查询店员管理
     * 
     * @param clid 店员管理主键
     * @return 店员管理
     */
    @Override
    public ManPsbsClerk selectManPsbsClerkByClid(Long clid)
    {
        return manPsbsClerkMapper.selectManPsbsClerkByClid(clid);
    }

    /**
     * 查询店员管理列表
     * 
     * @param manPsbsClerk 店员管理
     * @return 店员管理
     */
    @Override
    public List<ManPsbsClerk> selectManPsbsClerkList(ManPsbsClerk manPsbsClerk)
    {
        return manPsbsClerkMapper.selectManPsbsClerkList(manPsbsClerk);
    }

    /**
     * 新增店员管理
     * 
     * @param manPsbsClerk 店员管理
     * @return 结果
     */
    @Transactional
    @Override
    public int insertManPsbsClerk(ManPsbsClerk manPsbsClerk)
    {
        int rows = manPsbsClerkMapper.insertManPsbsClerk(manPsbsClerk);
        insertManPsbsClkser(manPsbsClerk);
        return rows;
    }

    /**
     * 修改店员管理
     * 
     * @param manPsbsClerk 店员管理
     * @return 结果
     */
    @Transactional
    @Override
    public int updateManPsbsClerk(ManPsbsClerk manPsbsClerk)
    {
        manPsbsClerkMapper.deleteManPsbsClkserByClid(manPsbsClerk.getClid());
        insertManPsbsClkser(manPsbsClerk);
        return manPsbsClerkMapper.updateManPsbsClerk(manPsbsClerk);
    }

    /**
     * 批量删除店员管理
     * 
     * @param clids 需要删除的店员管理主键
     * @return 结果
     */
    @Transactional
    @Override
    public int deleteManPsbsClerkByClids(Long[] clids)
    {
        manPsbsClerkMapper.deleteManPsbsClkserByClids(clids);
        return manPsbsClerkMapper.deleteManPsbsClerkByClids(clids);
    }

    /**
     * 删除店员管理信息
     * 
     * @param clid 店员管理主键
     * @return 结果
     */
    @Transactional
    @Override
    public int deleteManPsbsClerkByClid(Long clid)
    {
        manPsbsClerkMapper.deleteManPsbsClkserByClid(clid);
        return manPsbsClerkMapper.deleteManPsbsClerkByClid(clid);
    }

    /**
     * 新增店员服务信息
     * 
     * @param manPsbsClerk 店员管理对象
     */
    public void insertManPsbsClkser(ManPsbsClerk manPsbsClerk)
    {
        List<ManPsbsClkser> manPsbsClkserList = manPsbsClerk.getManPsbsClkserList();
        Long clid = manPsbsClerk.getClid();
        if (StringUtils.isNotNull(manPsbsClkserList))
        {
            List<ManPsbsClkser> list = new ArrayList<ManPsbsClkser>();
            for (ManPsbsClkser manPsbsClkser : manPsbsClkserList)
            {
                manPsbsClkser.setClid(clid);
                list.add(manPsbsClkser);
            }
            if (list.size() > 0)
            {
                manPsbsClerkMapper.batchManPsbsClkser(list);
            }
        }
    }
}
