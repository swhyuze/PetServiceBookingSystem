package com.ruoyi.system.adm.service.impl;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.ruoyi.system.adm.mapper.AdmPsbsProblemMapper;
import com.ruoyi.system.adm.domain.AdmPsbsProblem;
import com.ruoyi.system.adm.service.IAdmPsbsProblemService;

/**
 * 问题反馈Service业务层处理
 * 
 * @author 御泽
 * @date 2024-05-06
 */
@Service
public class AdmPsbsProblemServiceImpl implements IAdmPsbsProblemService 
{
    @Autowired
    private AdmPsbsProblemMapper admPsbsProblemMapper;

    /**
     * 查询问题反馈
     * 
     * @param proid 问题反馈主键
     * @return 问题反馈
     */
    @Override
    public AdmPsbsProblem selectAdmPsbsProblemByProid(Long proid)
    {
        return admPsbsProblemMapper.selectAdmPsbsProblemByProid(proid);
    }

    /**
     * 查询问题反馈列表
     * 
     * @param admPsbsProblem 问题反馈
     * @return 问题反馈
     */
    @Override
    public List<AdmPsbsProblem> selectAdmPsbsProblemList(AdmPsbsProblem admPsbsProblem)
    {
        return admPsbsProblemMapper.selectAdmPsbsProblemList(admPsbsProblem);
    }

    /**
     * 新增问题反馈
     * 
     * @param admPsbsProblem 问题反馈
     * @return 结果
     */
    @Override
    public int insertAdmPsbsProblem(AdmPsbsProblem admPsbsProblem)
    {
        return admPsbsProblemMapper.insertAdmPsbsProblem(admPsbsProblem);
    }

    /**
     * 修改问题反馈
     * 
     * @param admPsbsProblem 问题反馈
     * @return 结果
     */
    @Override
    public int updateAdmPsbsProblem(AdmPsbsProblem admPsbsProblem)
    {
        return admPsbsProblemMapper.updateAdmPsbsProblem(admPsbsProblem);
    }

    /**
     * 批量删除问题反馈
     * 
     * @param proids 需要删除的问题反馈主键
     * @return 结果
     */
    @Override
    public int deleteAdmPsbsProblemByProids(Long[] proids)
    {
        return admPsbsProblemMapper.deleteAdmPsbsProblemByProids(proids);
    }

    /**
     * 删除问题反馈信息
     * 
     * @param proid 问题反馈主键
     * @return 结果
     */
    @Override
    public int deleteAdmPsbsProblemByProid(Long proid)
    {
        return admPsbsProblemMapper.deleteAdmPsbsProblemByProid(proid);
    }
}
