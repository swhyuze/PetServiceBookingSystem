package com.ruoyi.system.man.mapper;

import java.util.List;
import com.ruoyi.system.man.domain.ManPsbsClerk;
import com.ruoyi.system.man.domain.ManPsbsClkser;

/**
 * 店员管理Mapper接口
 * 
 * @author 御泽
 * @date 2024-05-08
 */
public interface ManPsbsClerkMapper 
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
     * 删除店员管理
     * 
     * @param clid 店员管理主键
     * @return 结果
     */
    public int deleteManPsbsClerkByClid(Long clid);

    /**
     * 批量删除店员管理
     * 
     * @param clids 需要删除的数据主键集合
     * @return 结果
     */
    public int deleteManPsbsClerkByClids(Long[] clids);

    /**
     * 批量删除店员服务
     * 
     * @param clids 需要删除的数据主键集合
     * @return 结果
     */
    public int deleteManPsbsClkserByClids(Long[] clids);
    
    /**
     * 批量新增店员服务
     * 
     * @param manPsbsClkserList 店员服务列表
     * @return 结果
     */
    public int batchManPsbsClkser(List<ManPsbsClkser> manPsbsClkserList);
    

    /**
     * 通过店员管理主键删除店员服务信息
     * 
     * @param clid 店员管理ID
     * @return 结果
     */
    public int deleteManPsbsClkserByClid(Long clid);
}
