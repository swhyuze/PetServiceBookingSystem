package com.ruoyi.system.man.service.impl;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.ruoyi.system.man.mapper.ManPsbsClkserMapper;
import com.ruoyi.system.man.domain.ManPsbsClkser;
import com.ruoyi.system.man.service.IManPsbsClkserService;

/**
 * 店员服务Service业务层处理
 * 
 * @author 御泽
 * @date 2024-05-08
 */
@Service
public class ManPsbsClkserServiceImpl implements IManPsbsClkserService 
{
    @Autowired
    private ManPsbsClkserMapper manPsbsClkserMapper;

    /**
     * 查询店员服务
     * 
     * @param clser 店员服务主键
     * @return 店员服务
     */
    @Override
    public ManPsbsClkser selectManPsbsClkserByClser(Long clser)
    {
        return manPsbsClkserMapper.selectManPsbsClkserByClser(clser);
    }

    /**
     * 查询店员服务列表
     * 
     * @param manPsbsClkser 店员服务
     * @return 店员服务
     */
    @Override
    public List<ManPsbsClkser> selectManPsbsClkserList(ManPsbsClkser manPsbsClkser)
    {
        return manPsbsClkserMapper.selectManPsbsClkserList(manPsbsClkser);
    }

    /**
     * 新增店员服务
     * 
     * @param manPsbsClkser 店员服务
     * @return 结果
     */
    @Override
    public int insertManPsbsClkser(ManPsbsClkser manPsbsClkser)
    {
        return manPsbsClkserMapper.insertManPsbsClkser(manPsbsClkser);
    }

    /**
     * 修改店员服务
     * 
     * @param manPsbsClkser 店员服务
     * @return 结果
     */
    @Override
    public int updateManPsbsClkser(ManPsbsClkser manPsbsClkser)
    {
        return manPsbsClkserMapper.updateManPsbsClkser(manPsbsClkser);
    }

    /**
     * 批量删除店员服务
     * 
     * @param clsers 需要删除的店员服务主键
     * @return 结果
     */
    @Override
    public int deleteManPsbsClkserByClsers(Long[] clsers)
    {
        return manPsbsClkserMapper.deleteManPsbsClkserByClsers(clsers);
    }

    /**
     * 删除店员服务信息
     * 
     * @param clser 店员服务主键
     * @return 结果
     */
    @Override
    public int deleteManPsbsClkserByClser(Long clser)
    {
        return manPsbsClkserMapper.deleteManPsbsClkserByClser(clser);
    }
}
