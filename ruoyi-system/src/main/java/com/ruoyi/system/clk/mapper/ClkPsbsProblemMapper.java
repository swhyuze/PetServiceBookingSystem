package com.ruoyi.system.clk.mapper;

import java.util.List;
import com.ruoyi.system.clk.domain.ClkPsbsProblem;

/**
 * 问题反馈Mapper接口
 * 
 * @author 御泽
 * @date 2024-05-08
 */
public interface ClkPsbsProblemMapper 
{
    /**
     * 查询问题反馈
     * 
     * @param proid 问题反馈主键
     * @return 问题反馈
     */
    public ClkPsbsProblem selectClkPsbsProblemByProid(Long proid);

    /**
     * 查询问题反馈列表
     * 
     * @param clkPsbsProblem 问题反馈
     * @return 问题反馈集合
     */
    public List<ClkPsbsProblem> selectClkPsbsProblemList(ClkPsbsProblem clkPsbsProblem);

    /**
     * 新增问题反馈
     * 
     * @param clkPsbsProblem 问题反馈
     * @return 结果
     */
    public int insertClkPsbsProblem(ClkPsbsProblem clkPsbsProblem);

    /**
     * 修改问题反馈
     * 
     * @param clkPsbsProblem 问题反馈
     * @return 结果
     */
    public int updateClkPsbsProblem(ClkPsbsProblem clkPsbsProblem);

    /**
     * 删除问题反馈
     * 
     * @param proid 问题反馈主键
     * @return 结果
     */
    public int deleteClkPsbsProblemByProid(Long proid);

    /**
     * 批量删除问题反馈
     * 
     * @param proids 需要删除的数据主键集合
     * @return 结果
     */
    public int deleteClkPsbsProblemByProids(Long[] proids);
}
