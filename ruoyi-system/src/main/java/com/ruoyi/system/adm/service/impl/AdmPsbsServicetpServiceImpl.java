package com.ruoyi.system.adm.service.impl;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.ruoyi.system.adm.mapper.AdmPsbsServicetpMapper;
import com.ruoyi.system.adm.domain.AdmPsbsServicetp;
import com.ruoyi.system.adm.service.IAdmPsbsServicetpService;

/**
 * 服务种类Service业务层处理
 * 
 * @author ruoyi
 * @date 2024-05-09
 */
@Service
public class AdmPsbsServicetpServiceImpl implements IAdmPsbsServicetpService 
{
    @Autowired
    private AdmPsbsServicetpMapper admPsbsServicetpMapper;

    /**
     * 查询服务种类
     * 
     * @param stid 服务种类主键
     * @return 服务种类
     */
    @Override
    public AdmPsbsServicetp selectAdmPsbsServicetpByStid(Long stid)
    {
        return admPsbsServicetpMapper.selectAdmPsbsServicetpByStid(stid);
    }

    /**
     * 查询服务种类列表
     * 
     * @param admPsbsServicetp 服务种类
     * @return 服务种类
     */
    @Override
    public List<AdmPsbsServicetp> selectAdmPsbsServicetpList(AdmPsbsServicetp admPsbsServicetp)
    {
        return admPsbsServicetpMapper.selectAdmPsbsServicetpList(admPsbsServicetp);
    }
    @Override
    public List<AdmPsbsServicetp> selectSerivetp(AdmPsbsServicetp admPsbsServicetp){
        return admPsbsServicetpMapper.selectSerivetp(admPsbsServicetp);
    }
    /**
     * 新增服务种类
     * 
     * @param admPsbsServicetp 服务种类
     * @return 结果
     */
    @Override
    public int insertAdmPsbsServicetp(AdmPsbsServicetp admPsbsServicetp)
    {
        return admPsbsServicetpMapper.insertAdmPsbsServicetp(admPsbsServicetp);
    }

    /**
     * 修改服务种类
     * 
     * @param admPsbsServicetp 服务种类
     * @return 结果
     */
    @Override
    public int updateAdmPsbsServicetp(AdmPsbsServicetp admPsbsServicetp)
    {
        return admPsbsServicetpMapper.updateAdmPsbsServicetp(admPsbsServicetp);
    }

    /**
     * 批量删除服务种类
     * 
     * @param stids 需要删除的服务种类主键
     * @return 结果
     */
    @Override
    public int deleteAdmPsbsServicetpByStids(Long[] stids)
    {
        return admPsbsServicetpMapper.deleteAdmPsbsServicetpByStids(stids);
    }

    /**
     * 删除服务种类信息
     * 
     * @param stid 服务种类主键
     * @return 结果
     */
    @Override
    public int deleteAdmPsbsServicetpByStid(Long stid)
    {
        return admPsbsServicetpMapper.deleteAdmPsbsServicetpByStid(stid);
    }

    @Override
    public List<AdmPsbsServicetp> selectAllAdmServicetp(){
        return admPsbsServicetpMapper.selectAllAdmServicetp();
    }

}
