package com.ruoyi.system.man.service;

import java.util.List;
import com.ruoyi.system.man.domain.ManPsbsClkser;

/**
 * 店员服务Service接口
 * 
 * @author 御泽
 * @date 2024-05-08
 */
public interface IManPsbsClkserService 
{
    /**
     * 查询店员服务
     * 
     * @param clser 店员服务主键
     * @return 店员服务
     */
    public ManPsbsClkser selectManPsbsClkserByClser(Long clser);

    /**
     * 查询店员服务列表
     * 
     * @param manPsbsClkser 店员服务
     * @return 店员服务集合
     */
    public List<ManPsbsClkser> selectManPsbsClkserList(ManPsbsClkser manPsbsClkser);

    /**
     * 新增店员服务
     * 
     * @param manPsbsClkser 店员服务
     * @return 结果
     */
    public int insertManPsbsClkser(ManPsbsClkser manPsbsClkser);

    /**
     * 修改店员服务
     * 
     * @param manPsbsClkser 店员服务
     * @return 结果
     */
    public int updateManPsbsClkser(ManPsbsClkser manPsbsClkser);

    /**
     * 批量删除店员服务
     * 
     * @param clsers 需要删除的店员服务主键集合
     * @return 结果
     */
    public int deleteManPsbsClkserByClsers(Long[] clsers);

    /**
     * 删除店员服务信息
     * 
     * @param clser 店员服务主键
     * @return 结果
     */
    public int deleteManPsbsClkserByClser(Long clser);
}
