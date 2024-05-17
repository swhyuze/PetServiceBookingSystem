package com.ruoyi.system.adm.service;

import java.util.List;
import com.ruoyi.system.adm.domain.AdmPsbsServicetp;

/**
 * 服务种类Service接口
 * 
 * @author ruoyi
 * @date 2024-05-09
 */
public interface IAdmPsbsServicetpService 
{
    /**
     * 查询服务种类
     * 
     * @param stid 服务种类主键
     * @return 服务种类
     */
    public AdmPsbsServicetp selectAdmPsbsServicetpByStid(Long stid);

    /**
     * 查询服务种类列表
     * 
     * @param admPsbsServicetp 服务种类
     * @return 服务种类集合
     */
    public List<AdmPsbsServicetp> selectAdmPsbsServicetpList(AdmPsbsServicetp admPsbsServicetp);

    /**
     * 新增服务种类
     * 
     * @param admPsbsServicetp 服务种类
     * @return 结果
     */
    public int insertAdmPsbsServicetp(AdmPsbsServicetp admPsbsServicetp);

    /**
     * 修改服务种类
     * 
     * @param admPsbsServicetp 服务种类
     * @return 结果
     */
    public int updateAdmPsbsServicetp(AdmPsbsServicetp admPsbsServicetp);

    /**
     * 批量删除服务种类
     * 
     * @param stids 需要删除的服务种类主键集合
     * @return 结果
     */
    public int deleteAdmPsbsServicetpByStids(Long[] stids);

    /**
     * 删除服务种类信息
     * 
     * @param stid 服务种类主键
     * @return 结果
     */
    public int deleteAdmPsbsServicetpByStid(Long stid);
}
