package com.ruoyi.system.cum.service.impl;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.ruoyi.system.cum.mapper.CumPsbsServicetpMapper;
import com.ruoyi.system.cum.domain.CumPsbsServicetp;
import com.ruoyi.system.cum.service.ICumPsbsServicetpService;

/**
 * 服务种类Service业务层处理
 * 
 * @author ruoyi
 * @date 2024-05-08
 */
@Service
public class CumPsbsServicetpServiceImpl implements ICumPsbsServicetpService 
{
    @Autowired
    private CumPsbsServicetpMapper cumPsbsServicetpMapper;

    /**
     * 查询服务种类
     * 
     * @param stid 服务种类主键
     * @return 服务种类
     */
    @Override
    public CumPsbsServicetp selectCumPsbsServicetpByStid(Long stid)
    {
        return cumPsbsServicetpMapper.selectCumPsbsServicetpByStid(stid);
    }

    /**
     * 查询服务种类列表
     * 
     * @param cumPsbsServicetp 服务种类
     * @return 服务种类
     */
    @Override
    public List<CumPsbsServicetp> selectCumPsbsServicetpList(CumPsbsServicetp cumPsbsServicetp)
    {
        return cumPsbsServicetpMapper.selectCumPsbsServicetpList(cumPsbsServicetp);
    }

    /**
     * 新增服务种类
     * 
     * @param cumPsbsServicetp 服务种类
     * @return 结果
     */
    @Override
    public int insertCumPsbsServicetp(CumPsbsServicetp cumPsbsServicetp)
    {
        return cumPsbsServicetpMapper.insertCumPsbsServicetp(cumPsbsServicetp);
    }

    /**
     * 修改服务种类
     * 
     * @param cumPsbsServicetp 服务种类
     * @return 结果
     */
    @Override
    public int updateCumPsbsServicetp(CumPsbsServicetp cumPsbsServicetp)
    {
        return cumPsbsServicetpMapper.updateCumPsbsServicetp(cumPsbsServicetp);
    }

    /**
     * 批量删除服务种类
     * 
     * @param stids 需要删除的服务种类主键
     * @return 结果
     */
    @Override
    public int deleteCumPsbsServicetpByStids(Long[] stids)
    {
        return cumPsbsServicetpMapper.deleteCumPsbsServicetpByStids(stids);
    }

    /**
     * 删除服务种类信息
     * 
     * @param stid 服务种类主键
     * @return 结果
     */
    @Override
    public int deleteCumPsbsServicetpByStid(Long stid)
    {
        return cumPsbsServicetpMapper.deleteCumPsbsServicetpByStid(stid);
    }
}
