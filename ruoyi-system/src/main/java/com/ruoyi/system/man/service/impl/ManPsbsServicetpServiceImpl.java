package com.ruoyi.system.man.service.impl;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.ruoyi.system.man.mapper.ManPsbsServicetpMapper;
import com.ruoyi.system.man.domain.ManPsbsServicetp;
import com.ruoyi.system.man.service.IManPsbsServicetpService;

/**
 * 服务种类Service业务层处理
 * 
 * @author ruoyi
 * @date 2024-05-08
 */
@Service
public class ManPsbsServicetpServiceImpl implements IManPsbsServicetpService 
{
    @Autowired
    private ManPsbsServicetpMapper manPsbsServicetpMapper;

    /**
     * 查询服务种类
     * 
     * @param stid 服务种类主键
     * @return 服务种类
     */
    @Override
    public ManPsbsServicetp selectManPsbsServicetpByStid(Long stid)
    {
        return manPsbsServicetpMapper.selectManPsbsServicetpByStid(stid);
    }

    /**
     * 查询服务种类列表
     * 
     * @param manPsbsServicetp 服务种类
     * @return 服务种类
     */
    @Override
    public List<ManPsbsServicetp> selectManPsbsServicetpList(ManPsbsServicetp manPsbsServicetp)
    {
        return manPsbsServicetpMapper.selectManPsbsServicetpList(manPsbsServicetp);
    }

    /**
     * 新增服务种类
     * 
     * @param manPsbsServicetp 服务种类
     * @return 结果
     */
    @Override
    public int insertManPsbsServicetp(ManPsbsServicetp manPsbsServicetp)
    {
        return manPsbsServicetpMapper.insertManPsbsServicetp(manPsbsServicetp);
    }

    /**
     * 修改服务种类
     * 
     * @param manPsbsServicetp 服务种类
     * @return 结果
     */
    @Override
    public int updateManPsbsServicetp(ManPsbsServicetp manPsbsServicetp)
    {
        return manPsbsServicetpMapper.updateManPsbsServicetp(manPsbsServicetp);
    }

    /**
     * 批量删除服务种类
     * 
     * @param stids 需要删除的服务种类主键
     * @return 结果
     */
    @Override
    public int deleteManPsbsServicetpByStids(Long[] stids)
    {
        return manPsbsServicetpMapper.deleteManPsbsServicetpByStids(stids);
    }

    /**
     * 删除服务种类信息
     * 
     * @param stid 服务种类主键
     * @return 结果
     */
    @Override
    public int deleteManPsbsServicetpByStid(Long stid)
    {
        return manPsbsServicetpMapper.deleteManPsbsServicetpByStid(stid);
    }
}
