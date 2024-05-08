package com.ruoyi.system.adm.service.impl;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.ruoyi.system.adm.mapper.AdmPsbsServiceMapper;
import com.ruoyi.system.adm.domain.AdmPsbsService;
import com.ruoyi.system.adm.service.IAdmPsbsServiceService;

/**
 * 服务记录Service业务层处理
 * 
 * @author 御泽
 * @date 2024-05-06
 */
@Service
public class AdmPsbsServiceServiceImpl implements IAdmPsbsServiceService 
{
    @Autowired
    private AdmPsbsServiceMapper admPsbsServiceMapper;

    /**
     * 查询服务记录
     * 
     * @param serid 服务记录主键
     * @return 服务记录
     */
    @Override
    public AdmPsbsService selectAdmPsbsServiceBySerid(Long serid)
    {
        return admPsbsServiceMapper.selectAdmPsbsServiceBySerid(serid);
    }

    /**
     * 查询服务记录列表
     * 
     * @param admPsbsService 服务记录
     * @return 服务记录
     */
    @Override
    public List<AdmPsbsService> selectAdmPsbsServiceList(AdmPsbsService admPsbsService)
    {
        return admPsbsServiceMapper.selectAdmPsbsServiceList(admPsbsService);
    }

    /**
     * 新增服务记录
     * 
     * @param admPsbsService 服务记录
     * @return 结果
     */
    @Override
    public int insertAdmPsbsService(AdmPsbsService admPsbsService)
    {
        return admPsbsServiceMapper.insertAdmPsbsService(admPsbsService);
    }

    /**
     * 修改服务记录
     * 
     * @param admPsbsService 服务记录
     * @return 结果
     */
    @Override
    public int updateAdmPsbsService(AdmPsbsService admPsbsService)
    {
        return admPsbsServiceMapper.updateAdmPsbsService(admPsbsService);
    }

    /**
     * 批量删除服务记录
     * 
     * @param serids 需要删除的服务记录主键
     * @return 结果
     */
    @Override
    public int deleteAdmPsbsServiceBySerids(Long[] serids)
    {
        return admPsbsServiceMapper.deleteAdmPsbsServiceBySerids(serids);
    }

    /**
     * 删除服务记录信息
     * 
     * @param serid 服务记录主键
     * @return 结果
     */
    @Override
    public int deleteAdmPsbsServiceBySerid(Long serid)
    {
        return admPsbsServiceMapper.deleteAdmPsbsServiceBySerid(serid);
    }
}
