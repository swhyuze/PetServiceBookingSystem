package com.ruoyi.system.adm.mapper;

import java.util.List;
import com.ruoyi.system.adm.domain.AdmPsbsService;

/**
 * 服务记录Mapper接口
 * 
 * @author 御泽
 * @date 2024-05-06
 */
public interface AdmPsbsServiceMapper 
{
    /**
     * 查询服务记录
     * 
     * @param serid 服务记录主键
     * @return 服务记录
     */
    public AdmPsbsService selectAdmPsbsServiceBySerid(Long serid);

    /**
     * 查询服务记录列表
     * 
     * @param admPsbsService 服务记录
     * @return 服务记录集合
     */
    public List<AdmPsbsService> selectAdmPsbsServiceList(AdmPsbsService admPsbsService);

    /**
     * 新增服务记录
     * 
     * @param admPsbsService 服务记录
     * @return 结果
     */
    public int insertAdmPsbsService(AdmPsbsService admPsbsService);

    /**
     * 修改服务记录
     * 
     * @param admPsbsService 服务记录
     * @return 结果
     */
    public int updateAdmPsbsService(AdmPsbsService admPsbsService);

    /**
     * 删除服务记录
     * 
     * @param serid 服务记录主键
     * @return 结果
     */
    public int deleteAdmPsbsServiceBySerid(Long serid);

    /**
     * 批量删除服务记录
     * 
     * @param serids 需要删除的数据主键集合
     * @return 结果
     */
    public int deleteAdmPsbsServiceBySerids(Long[] serids);
}
