package com.ruoyi.system.adm.service;

import java.util.List;
import com.ruoyi.system.adm.domain.AdmPsbsClkser;

/**
 * 店员服务Service接口
 * 
 * @author 御泽
 * @date 2024-05-06
 */
public interface IAdmPsbsClkserService 
{
    /**
     * 查询店员服务
     * 
     * @param clser 店员服务主键
     * @return 店员服务
     */
    public AdmPsbsClkser selectAdmPsbsClkserByClser(Long clser);

    /**
     * 查询店员服务列表
     * 
     * @param admPsbsClkser 店员服务
     * @return 店员服务集合
     */
    public List<AdmPsbsClkser> selectAdmPsbsClkserList(AdmPsbsClkser admPsbsClkser);

    /**
     * 新增店员服务
     * 
     * @param admPsbsClkser 店员服务
     * @return 结果
     */
    public int insertAdmPsbsClkser(AdmPsbsClkser admPsbsClkser);

    /**
     * 修改店员服务
     * 
     * @param admPsbsClkser 店员服务
     * @return 结果
     */
    public int updateAdmPsbsClkser(AdmPsbsClkser admPsbsClkser);

    /**
     * 批量删除店员服务
     * 
     * @param clsers 需要删除的店员服务主键集合
     * @return 结果
     */
    public int deleteAdmPsbsClkserByClsers(Long[] clsers);

    /**
     * 删除店员服务信息
     * 
     * @param clser 店员服务主键
     * @return 结果
     */
    public int deleteAdmPsbsClkserByClser(Long clser);
}
