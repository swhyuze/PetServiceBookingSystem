package com.ruoyi.system.service;

import java.util.List;
import com.ruoyi.system.domain.PsbsClerk;

/**
 * 店员Service接口
 * 
 * @author ruoyi
 * @date 2024-04-23
 */
public interface IPsbsClerkService 
{
    /**
     * 查询店员
     * 
     * @param clid 店员主键
     * @return 店员
     */
    public PsbsClerk selectPsbsClerkByClid(Long clid);

    /**
     * 查询店员列表
     * 
     * @param psbsClerk 店员
     * @return 店员集合
     */
    public List<PsbsClerk> selectPsbsClerkList(PsbsClerk psbsClerk);

    /**
     * 新增店员
     * 
     * @param psbsClerk 店员
     * @return 结果
     */
    public int insertPsbsClerk(PsbsClerk psbsClerk);

    /**
     * 修改店员
     * 
     * @param psbsClerk 店员
     * @return 结果
     */
    public int updatePsbsClerk(PsbsClerk psbsClerk);

    /**
     * 批量删除店员
     * 
     * @param clids 需要删除的店员主键集合
     * @return 结果
     */
    public int deletePsbsClerkByClids(Long[] clids);

    /**
     * 删除店员信息
     * 
     * @param clid 店员主键
     * @return 结果
     */
    public int deletePsbsClerkByClid(Long clid);
}
