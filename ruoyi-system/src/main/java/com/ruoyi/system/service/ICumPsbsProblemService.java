package com.ruoyi.system.service;

import java.util.List;
import com.ruoyi.system.domain.CumPsbsProblem;

/**
 * 问题反馈Service接口
 * 
 * @author 御泽
 * @date 2024-05-08
 */
public interface ICumPsbsProblemService 
{
    /**
     * 查询问题反馈
     * 
     * @param proid 问题反馈主键
     * @return 问题反馈
     */
    public CumPsbsProblem selectCumPsbsProblemByProid(Long proid);

    /**
     * 查询问题反馈列表
     * 
     * @param cumPsbsProblem 问题反馈
     * @return 问题反馈集合
     */
    public List<CumPsbsProblem> selectCumPsbsProblemList(CumPsbsProblem cumPsbsProblem);

    /**
     * 新增问题反馈
     * 
     * @param cumPsbsProblem 问题反馈
     * @return 结果
     */
    public int insertCumPsbsProblem(CumPsbsProblem cumPsbsProblem);

    /**
     * 修改问题反馈
     * 
     * @param cumPsbsProblem 问题反馈
     * @return 结果
     */
    public int updateCumPsbsProblem(CumPsbsProblem cumPsbsProblem);

    /**
     * 批量删除问题反馈
     * 
     * @param proids 需要删除的问题反馈主键集合
     * @return 结果
     */
    public int deleteCumPsbsProblemByProids(Long[] proids);

    /**
     * 删除问题反馈信息
     * 
     * @param proid 问题反馈主键
     * @return 结果
     */
    public int deleteCumPsbsProblemByProid(Long proid);
}
