package com.ruoyi.system.adm.mapper;

import java.util.List;
import com.ruoyi.system.adm.domain.AdmPsbsServicetp;

/**
 * 服务种类Mapper接口
 * 
 * @author ruoyi
 * @date 2024-05-09
 */
public interface AdmPsbsServicetpMapper 
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
     * 删除服务种类
     * 
     * @param stid 服务种类主键
     * @return 结果
     */
    public int deleteAdmPsbsServicetpByStid(Long stid);

    /**
     * 批量删除服务种类
     * 
     * @param stids 需要删除的数据主键集合
     * @return 结果
     */
    public int deleteAdmPsbsServicetpByStids(Long[] stids);

    public List<AdmPsbsServicetp> selectAllAdmServicetp();
}
