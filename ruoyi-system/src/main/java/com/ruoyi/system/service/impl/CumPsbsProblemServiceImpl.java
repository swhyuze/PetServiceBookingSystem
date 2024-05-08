package com.ruoyi.system.service.impl;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.ruoyi.system.mapper.CumPsbsProblemMapper;
import com.ruoyi.system.domain.CumPsbsProblem;
import com.ruoyi.system.service.ICumPsbsProblemService;

/**
 * 问题反馈Service业务层处理
 * 
 * @author 御泽
 * @date 2024-05-08
 */
@Service
public class CumPsbsProblemServiceImpl implements ICumPsbsProblemService 
{
    @Autowired
    private CumPsbsProblemMapper cumPsbsProblemMapper;

    /**
     * 查询问题反馈
     * 
     * @param proid 问题反馈主键
     * @return 问题反馈
     */
    @Override
    public CumPsbsProblem selectCumPsbsProblemByProid(Long proid)
    {
        return cumPsbsProblemMapper.selectCumPsbsProblemByProid(proid);
    }

    /**
     * 查询问题反馈列表
     * 
     * @param cumPsbsProblem 问题反馈
     * @return 问题反馈
     */
    @Override
    public List<CumPsbsProblem> selectCumPsbsProblemList(CumPsbsProblem cumPsbsProblem)
    {
        return cumPsbsProblemMapper.selectCumPsbsProblemList(cumPsbsProblem);
    }

    /**
     * 新增问题反馈
     * 
     * @param cumPsbsProblem 问题反馈
     * @return 结果
     */
    @Override
    public int insertCumPsbsProblem(CumPsbsProblem cumPsbsProblem)
    {
        return cumPsbsProblemMapper.insertCumPsbsProblem(cumPsbsProblem);
    }

    /**
     * 修改问题反馈
     * 
     * @param cumPsbsProblem 问题反馈
     * @return 结果
     */
    @Override
    public int updateCumPsbsProblem(CumPsbsProblem cumPsbsProblem)
    {
        return cumPsbsProblemMapper.updateCumPsbsProblem(cumPsbsProblem);
    }

    /**
     * 批量删除问题反馈
     * 
     * @param proids 需要删除的问题反馈主键
     * @return 结果
     */
    @Override
    public int deleteCumPsbsProblemByProids(Long[] proids)
    {
        return cumPsbsProblemMapper.deleteCumPsbsProblemByProids(proids);
    }

    /**
     * 删除问题反馈信息
     * 
     * @param proid 问题反馈主键
     * @return 结果
     */
    @Override
    public int deleteCumPsbsProblemByProid(Long proid)
    {
        return cumPsbsProblemMapper.deleteCumPsbsProblemByProid(proid);
    }
}
