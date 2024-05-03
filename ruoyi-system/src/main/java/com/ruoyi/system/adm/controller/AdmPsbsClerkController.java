package com.ruoyi.system.adm.controller;

import java.util.List;
import javax.servlet.http.HttpServletResponse;
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
import com.ruoyi.system.adm.domain.AdmPsbsClerk;
import com.ruoyi.system.adm.service.IAdmPsbsClerkService;
import com.ruoyi.common.utils.poi.ExcelUtil;
import com.ruoyi.common.core.page.TableDataInfo;

/**
 * 店员管理Controller
 * 
 * @author 御泽
 * @date 2024-05-03
 */
@RestController
@RequestMapping("/system/adm_clerk")
public class AdmPsbsClerkController extends BaseController
{
    @Autowired
    private IAdmPsbsClerkService admPsbsClerkService;

    /**
     * 查询店员管理列表
     */
    @PreAuthorize("@ss.hasPermi('system:adm_clerk:list')")
    @GetMapping("/list")
    public TableDataInfo list(AdmPsbsClerk admPsbsClerk)
    {
        startPage();
        List<AdmPsbsClerk> list = admPsbsClerkService.selectAdmPsbsClerkList(admPsbsClerk);
        return getDataTable(list);
    }

    /**
     * 导出店员管理列表
     */
    @PreAuthorize("@ss.hasPermi('system:adm_clerk:export')")
    @Log(title = "店员管理", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    public void export(HttpServletResponse response, AdmPsbsClerk admPsbsClerk)
    {
        List<AdmPsbsClerk> list = admPsbsClerkService.selectAdmPsbsClerkList(admPsbsClerk);
        ExcelUtil<AdmPsbsClerk> util = new ExcelUtil<AdmPsbsClerk>(AdmPsbsClerk.class);
        util.exportExcel(response, list, "店员管理数据");
    }

    /**
     * 获取店员管理详细信息
     */
    @PreAuthorize("@ss.hasPermi('system:adm_clerk:query')")
    @GetMapping(value = "/{clid}")
    public AjaxResult getInfo(@PathVariable("clid") Long clid)
    {
        return success(admPsbsClerkService.selectAdmPsbsClerkByClid(clid));
    }

    /**
     * 新增店员管理
     */
    @PreAuthorize("@ss.hasPermi('system:adm_clerk:add')")
    @Log(title = "店员管理", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody AdmPsbsClerk admPsbsClerk)
    {
        return toAjax(admPsbsClerkService.insertAdmPsbsClerk(admPsbsClerk));
    }

    /**
     * 修改店员管理
     */
    @PreAuthorize("@ss.hasPermi('system:adm_clerk:edit')")
    @Log(title = "店员管理", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody AdmPsbsClerk admPsbsClerk)
    {
        return toAjax(admPsbsClerkService.updateAdmPsbsClerk(admPsbsClerk));
    }

    /**
     * 删除店员管理
     */
    @PreAuthorize("@ss.hasPermi('system:adm_clerk:remove')")
    @Log(title = "店员管理", businessType = BusinessType.DELETE)
	@DeleteMapping("/{clids}")
    public AjaxResult remove(@PathVariable Long[] clids)
    {
        return toAjax(admPsbsClerkService.deleteAdmPsbsClerkByClids(clids));
    }
}
