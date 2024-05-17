package com.ruoyi.system.adm.controller;

import java.util.List;
import javax.servlet.http.HttpServletResponse;

import com.ruoyi.system.cum.domain.CumPsbsPet;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.ruoyi.common.annotation.Log;
import com.ruoyi.common.core.controller.BaseController;
import com.ruoyi.common.core.domain.AjaxResult;
import com.ruoyi.common.enums.BusinessType;
import com.ruoyi.system.adm.domain.AdmPsbsPettp;
import com.ruoyi.system.adm.service.IAdmPsbsPettpService;
import com.ruoyi.common.utils.poi.ExcelUtil;
import com.ruoyi.common.core.page.TableDataInfo;

/**
 * 宠物品种Controller
 * 
 * @author 御泽
 * @date 2024-05-06
 */
@RestController
@RequestMapping("/system/adm_pettp")
public class AdmPsbsPettpController extends BaseController
{
    @Autowired
    private IAdmPsbsPettpService admPsbsPettpService;

    /**
     * 查询宠物品种列表
     */
    @PreAuthorize("@ss.hasPermi('system:adm_pettp:list')")
    @GetMapping("/list")
    public TableDataInfo list(AdmPsbsPettp admPsbsPettp)
    {
        startPage();
        List<AdmPsbsPettp> list = admPsbsPettpService.selectAdmPsbsPettpList(admPsbsPettp);
        return getDataTable(list);
    }

    /**
     * 导出宠物品种列表
     */
    @PreAuthorize("@ss.hasPermi('system:adm_pettp:export')")
    @Log(title = "宠物品种", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    public void export(HttpServletResponse response, AdmPsbsPettp admPsbsPettp)
    {
        List<AdmPsbsPettp> list = admPsbsPettpService.selectAdmPsbsPettpList(admPsbsPettp);
        ExcelUtil<AdmPsbsPettp> util = new ExcelUtil<AdmPsbsPettp>(AdmPsbsPettp.class);
        util.exportExcel(response, list, "宠物品种数据");
    }

    /**
     * 获取宠物品种详细信息
     */
    @PreAuthorize("@ss.hasPermi('system:adm_pettp:query')")
    @GetMapping(value = "/{ptid}")
    public AjaxResult getInfo(@PathVariable("ptid") Long ptid)
    {
        return success(admPsbsPettpService.selectAdmPsbsPettpByPtid(ptid));
    }

    /**
     * 新增宠物品种
     */
    @PreAuthorize("@ss.hasPermi('system:adm_pettp:add')")
    @Log(title = "宠物品种", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody AdmPsbsPettp admPsbsPettp)
    {
        return toAjax(admPsbsPettpService.insertAdmPsbsPettp(admPsbsPettp));
    }

    /**
     * 修改宠物品种
     */
    @PreAuthorize("@ss.hasPermi('system:adm_pettp:edit')")
    @Log(title = "宠物品种", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody AdmPsbsPettp admPsbsPettp)
    {
        return toAjax(admPsbsPettpService.updateAdmPsbsPettp(admPsbsPettp));
    }

    /**
     * 删除宠物品种
     */
    @PreAuthorize("@ss.hasPermi('system:adm_pettp:remove')")
    @Log(title = "宠物品种", businessType = BusinessType.DELETE)
	@DeleteMapping("/{ptids}")
    public AjaxResult remove(@PathVariable Long[] ptids)
    {
        return toAjax(admPsbsPettpService.deleteAdmPsbsPettpByPtids(ptids));
    }


}
