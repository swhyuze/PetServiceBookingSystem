package com.ruoyi.system.clk.service.impl;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.ruoyi.system.clk.mapper.ClkPsbsProblemMapper;
import com.ruoyi.system.clk.domain.ClkPsbsProblem;
import com.ruoyi.system.clk.service.IClkPsbsProblemService;

/**
 * 问题反馈Service业务层处理
 * 
 * @author 御泽
 * @date 2024-05-08
 */
@Service
public class ClkPsbsProblemServiceImpl implements IClkPsbsProblemService 
{
    @Autowired
    private ClkPsbsProblemMapper clkPsbsProblemMapper;

    /**
     * 查询问题反馈
     * 
     * @param proid 问题反馈主键
     * @return 问题反馈
     */
    @Override
    public ClkPsbsProblem selectClkPsbsProblemByProid(Long proid)
    {
        return clkPsbsProblemMapper.selectClkPsbsProblemByProid(proid);
    }

    /**
     * 查询问题反馈列表
     * 
     * @param clkPsbsProblem 问题反馈
     * @return 问题反馈
     */
    @Override
    public List<ClkPsbsProblem> selectClkPsbsProblemList(ClkPsbsProblem clkPsbsProblem)
    {
        return clkPsbsProblemMapper.selectClkPsbsProblemList(clkPsbsProblem);
    }

    /**
     * 新增问题反馈
     * 
     * @param clkPsbsProblem 问题反馈
     * @return 结果
     */
    @Override
    public int insertClkPsbsProblem(ClkPsbsProblem clkPsbsProblem)
    {
        return clkPsbsProblemMapper.insertClkPsbsProblem(clkPsbsProblem);
    }

    /**
     * 修改问题反馈
     * 
     * @param clkPsbsProblem 问题反馈
     * @return 结果
     */
    @Override
    public int updateClkPsbsProblem(ClkPsbsProblem clkPsbsProblem)
    {
        return clkPsbsProblemMapper.updateClkPsbsProblem(clkPsbsProblem);
    }

    /**
     * 批量删除问题反馈
     * 
     * @param proids 需要删除的问题反馈主键
     * @return 结果
     */
    @Override
    public int deleteClkPsbsProblemByProids(Long[] proids)
    {
        return clkPsbsProblemMapper.deleteClkPsbsProblemByProids(proids);
    }

    /**
     * 删除问题反馈信息
     * 
     * @param proid 问题反馈主键
     * @return 结果
     */
    @Override
    public int deleteClkPsbsProblemByProid(Long proid)
    {
        return clkPsbsProblemMapper.deleteClkPsbsProblemByProid(proid);
    }
}
