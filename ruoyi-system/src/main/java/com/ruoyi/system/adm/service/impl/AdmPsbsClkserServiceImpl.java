package com.ruoyi.system.adm.service.impl;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.ruoyi.system.adm.mapper.AdmPsbsClkserMapper;
import com.ruoyi.system.adm.domain.AdmPsbsClkser;
import com.ruoyi.system.adm.service.IAdmPsbsClkserService;

/**
 * 店员服务Service业务层处理
 * 
 * @author 御泽
 * @date 2024-05-06
 */
@Service
public class AdmPsbsClkserServiceImpl implements IAdmPsbsClkserService 
{
    @Autowired
    private AdmPsbsClkserMapper admPsbsClkserMapper;

    /**
     * 查询店员服务
     * 
     * @param clser 店员服务主键
     * @return 店员服务
     */
    @Override
    public AdmPsbsClkser selectAdmPsbsClkserByClser(Long clser)
    {
        return admPsbsClkserMapper.selectAdmPsbsClkserByClser(clser);
    }

    /**
     * 查询店员服务列表
     * 
     * @param admPsbsClkser 店员服务
     * @return 店员服务
     */
    @Override
    public List<AdmPsbsClkser> selectAdmPsbsClkserList(AdmPsbsClkser admPsbsClkser)
    {
        return admPsbsClkserMapper.selectAdmPsbsClkserList(admPsbsClkser);
    }

    /**
     * 新增店员服务
     * 
     * @param admPsbsClkser 店员服务
     * @return 结果
     */
    @Override
    public int insertAdmPsbsClkser(AdmPsbsClkser admPsbsClkser)
    {
        return admPsbsClkserMapper.insertAdmPsbsClkser(admPsbsClkser);
    }

    /**
     * 修改店员服务
     * 
     * @param admPsbsClkser 店员服务
     * @return 结果
     */
    @Override
    public int updateAdmPsbsClkser(AdmPsbsClkser admPsbsClkser)
    {
        return admPsbsClkserMapper.updateAdmPsbsClkser(admPsbsClkser);
    }

    /**
     * 批量删除店员服务
     * 
     * @param clsers 需要删除的店员服务主键
     * @return 结果
     */
    @Override
    public int deleteAdmPsbsClkserByClsers(Long[] clsers)
    {
        return admPsbsClkserMapper.deleteAdmPsbsClkserByClsers(clsers);
    }

    /**
     * 删除店员服务信息
     * 
     * @param clser 店员服务主键
     * @return 结果
     */
    @Override
    public int deleteAdmPsbsClkserByClser(Long clser)
    {
        return admPsbsClkserMapper.deleteAdmPsbsClkserByClser(clser);
    }
}
