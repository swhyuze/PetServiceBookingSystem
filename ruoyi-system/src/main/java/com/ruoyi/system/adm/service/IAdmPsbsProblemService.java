package com.ruoyi.system.adm.service;

import java.util.List;
import com.ruoyi.system.adm.domain.AdmPsbsProblem;

/**
 * 问题反馈Service接口
 * 
 * @author 御泽
 * @date 2024-05-06
 */
public interface IAdmPsbsProblemService 
{
    /**
     * 查询问题反馈
     * 
     * @param proid 问题反馈主键
     * @return 问题反馈
     */
    public AdmPsbsProblem selectAdmPsbsProblemByProid(Long proid);

    /**
     * 查询问题反馈列表
     * 
     * @param admPsbsProblem 问题反馈
     * @return 问题反馈集合
     */
    public List<AdmPsbsProblem> selectAdmPsbsProblemList(AdmPsbsProblem admPsbsProblem);

    /**
     * 新增问题反馈
     * 
     * @param admPsbsProblem 问题反馈
     * @return 结果
     */
    public int insertAdmPsbsProblem(AdmPsbsProblem admPsbsProblem);

    /**
     * 修改问题反馈
     * 
     * @param admPsbsProblem 问题反馈
     * @return 结果
     */
    public int updateAdmPsbsProblem(AdmPsbsProblem admPsbsProblem);

    /**
     * 批量删除问题反馈
     * 
     * @param proids 需要删除的问题反馈主键集合
     * @return 结果
     */
    public int deleteAdmPsbsProblemByProids(Long[] proids);

    /**
     * 删除问题反馈信息
     * 
     * @param proid 问题反馈主键
     * @return 结果
     */
    public int deleteAdmPsbsProblemByProid(Long proid);
}
