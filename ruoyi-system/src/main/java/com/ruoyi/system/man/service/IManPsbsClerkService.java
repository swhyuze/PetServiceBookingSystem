package com.ruoyi.system.man.service;

import java.util.List;
import com.ruoyi.system.man.domain.ManPsbsClerk;

/**
 * 店员管理Service接口
 * 
 * @author 御泽
 * @date 2024-05-08
 */
public interface IManPsbsClerkService 
{
    /**
     * 查询店员管理
     * 
     * @param clid 店员管理主键
     * @return 店员管理
     */
    public ManPsbsClerk selectManPsbsClerkByClid(Long clid);

    /**
     * 查询店员管理列表
     * 
     * @param manPsbsClerk 店员管理
     * @return 店员管理集合
     */
    public List<ManPsbsClerk> selectManPsbsClerkList(ManPsbsClerk manPsbsClerk);

    /**
     * 新增店员管理
     * 
     * @param manPsbsClerk 店员管理
     * @return 结果
     */
    public int insertManPsbsClerk(ManPsbsClerk manPsbsClerk);

    /**
     * 修改店员管理
     * 
     * @param manPsbsClerk 店员管理
     * @return 结果
     */
    public int updateManPsbsClerk(ManPsbsClerk manPsbsClerk);

    /**
     * 批量删除店员管理
     * 
     * @param clids 需要删除的店员管理主键集合
     * @return 结果
     */
    public int deleteManPsbsClerkByClids(Long[] clids);

    /**
     * 删除店员管理信息
     * 
     * @param clid 店员管理主键
     * @return 结果
     */
    public int deleteManPsbsClerkByClid(Long clid);
}
