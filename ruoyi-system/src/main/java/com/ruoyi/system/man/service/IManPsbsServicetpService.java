package com.ruoyi.system.man.service;

import java.util.List;
import com.ruoyi.system.man.domain.ManPsbsServicetp;

/**
 * 服务种类Service接口
 * 
 * @author ruoyi
 * @date 2024-05-08
 */
public interface IManPsbsServicetpService 
{
    /**
     * 查询服务种类
     * 
     * @param stid 服务种类主键
     * @return 服务种类
     */
    public ManPsbsServicetp selectManPsbsServicetpByStid(Long stid);

    /**
     * 查询服务种类列表
     * 
     * @param manPsbsServicetp 服务种类
     * @return 服务种类集合
     */
    public List<ManPsbsServicetp> selectManPsbsServicetpList(ManPsbsServicetp manPsbsServicetp);

    /**
     * 新增服务种类
     * 
     * @param manPsbsServicetp 服务种类
     * @return 结果
     */
    public int insertManPsbsServicetp(ManPsbsServicetp manPsbsServicetp);

    /**
     * 修改服务种类
     * 
     * @param manPsbsServicetp 服务种类
     * @return 结果
     */
    public int updateManPsbsServicetp(ManPsbsServicetp manPsbsServicetp);

    /**
     * 批量删除服务种类
     * 
     * @param stids 需要删除的服务种类主键集合
     * @return 结果
     */
    public int deleteManPsbsServicetpByStids(Long[] stids);

    /**
     * 删除服务种类信息
     * 
     * @param stid 服务种类主键
     * @return 结果
     */
    public int deleteManPsbsServicetpByStid(Long stid);
}
