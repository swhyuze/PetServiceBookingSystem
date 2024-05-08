package com.ruoyi.system.adm.service.impl;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.ArrayList;
import com.ruoyi.common.utils.StringUtils;
import org.springframework.transaction.annotation.Transactional;
import com.ruoyi.system.adm.domain.AdmPsbsClkser;
import com.ruoyi.system.adm.mapper.AdmPsbsClerkMapper;
import com.ruoyi.system.adm.domain.AdmPsbsClerk;
import com.ruoyi.system.adm.service.IAdmPsbsClerkService;

/**
 * 店员管理Service业务层处理
 * 
 * @author 御泽
 * @date 2024-05-06
 */
@Service
public class AdmPsbsClerkServiceImpl implements IAdmPsbsClerkService 
{
    @Autowired
    private AdmPsbsClerkMapper admPsbsClerkMapper;

    /**
     * 查询店员管理
     * 
     * @param clid 店员管理主键
     * @return 店员管理
     */
    @Override
    public AdmPsbsClerk selectAdmPsbsClerkByClid(Long clid)
    {
        return admPsbsClerkMapper.selectAdmPsbsClerkByClid(clid);
    }

    /**
     * 查询店员管理列表
     * 
     * @param admPsbsClerk 店员管理
     * @return 店员管理
     */
    @Override
    public List<AdmPsbsClerk> selectAdmPsbsClerkList(AdmPsbsClerk admPsbsClerk)
    {
        return admPsbsClerkMapper.selectAdmPsbsClerkList(admPsbsClerk);
    }

    /**
     * 新增店员管理
     * 
     * @param admPsbsClerk 店员管理
     * @return 结果
     */
    @Transactional
    @Override
    public int insertAdmPsbsClerk(AdmPsbsClerk admPsbsClerk)
    {
        int rows = admPsbsClerkMapper.insertAdmPsbsClerk(admPsbsClerk);
        insertAdmPsbsClkser(admPsbsClerk);
        return rows;
    }

    /**
     * 修改店员管理
     * 
     * @param admPsbsClerk 店员管理
     * @return 结果
     */
    @Transactional
    @Override
    public int updateAdmPsbsClerk(AdmPsbsClerk admPsbsClerk)
    {
        admPsbsClerkMapper.deleteAdmPsbsClkserByClid(admPsbsClerk.getClid());
        insertAdmPsbsClkser(admPsbsClerk);
        return admPsbsClerkMapper.updateAdmPsbsClerk(admPsbsClerk);
    }

    /**
     * 批量删除店员管理
     * 
     * @param clids 需要删除的店员管理主键
     * @return 结果
     */
    @Transactional
    @Override
    public int deleteAdmPsbsClerkByClids(Long[] clids)
    {
        admPsbsClerkMapper.deleteAdmPsbsClkserByClids(clids);
        return admPsbsClerkMapper.deleteAdmPsbsClerkByClids(clids);
    }

    /**
     * 删除店员管理信息
     * 
     * @param clid 店员管理主键
     * @return 结果
     */
    @Transactional
    @Override
    public int deleteAdmPsbsClerkByClid(Long clid)
    {
        admPsbsClerkMapper.deleteAdmPsbsClkserByClid(clid);
        return admPsbsClerkMapper.deleteAdmPsbsClerkByClid(clid);
    }

    /**
     * 新增店员服务信息
     * 
     * @param admPsbsClerk 店员管理对象
     */
    public void insertAdmPsbsClkser(AdmPsbsClerk admPsbsClerk)
    {
        List<AdmPsbsClkser> admPsbsClkserList = admPsbsClerk.getAdmPsbsClkserList();
        Long clid = admPsbsClerk.getClid();
        if (StringUtils.isNotNull(admPsbsClkserList))
        {
            List<AdmPsbsClkser> list = new ArrayList<AdmPsbsClkser>();
            for (AdmPsbsClkser admPsbsClkser : admPsbsClkserList)
            {
                admPsbsClkser.setClid(clid);
                list.add(admPsbsClkser);
            }
            if (list.size() > 0)
            {
                admPsbsClerkMapper.batchAdmPsbsClkser(list);
            }
        }
    }
}
