package com.ruoyi.system.cum.mapper;

import java.util.List;
import com.ruoyi.system.cum.domain.CumPsbsServicetp;

/**
 * 服务种类Mapper接口
 * 
 * @author ruoyi
 * @date 2024-05-08
 */
public interface CumPsbsServicetpMapper 
{
    /**
     * 查询服务种类
     * 
     * @param stid 服务种类主键
     * @return 服务种类
     */
    public CumPsbsServicetp selectCumPsbsServicetpByStid(Long stid);

    /**
     * 查询服务种类列表
     * 
     * @param cumPsbsServicetp 服务种类
     * @return 服务种类集合
     */
    public List<CumPsbsServicetp> selectCumPsbsServicetpList(CumPsbsServicetp cumPsbsServicetp);

    /**
     * 新增服务种类
     * 
     * @param cumPsbsServicetp 服务种类
     * @return 结果
     */
    public int insertCumPsbsServicetp(CumPsbsServicetp cumPsbsServicetp);

    /**
     * 修改服务种类
     * 
     * @param cumPsbsServicetp 服务种类
     * @return 结果
     */
    public int updateCumPsbsServicetp(CumPsbsServicetp cumPsbsServicetp);

    /**
     * 删除服务种类
     * 
     * @param stid 服务种类主键
     * @return 结果
     */
    public int deleteCumPsbsServicetpByStid(Long stid);

    /**
     * 批量删除服务种类
     * 
     * @param stids 需要删除的数据主键集合
     * @return 结果
     */
    public int deleteCumPsbsServicetpByStids(Long[] stids);
}
