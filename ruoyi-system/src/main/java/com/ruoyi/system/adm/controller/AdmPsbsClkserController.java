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
import com.ruoyi.system.adm.domain.AdmPsbsClkser;
import com.ruoyi.system.adm.service.IAdmPsbsClkserService;
import com.ruoyi.common.utils.poi.ExcelUtil;
import com.ruoyi.common.core.page.TableDataInfo;

/**
 * 店员服务Controller
 * 
 * @author 御泽
 * @date 2024-05-03
 */
@RestController
@RequestMapping("/system/adm_clkser")
public class AdmPsbsClkserController extends BaseController
{
    @Autowired
    private IAdmPsbsClkserService admPsbsClkserService;

    /**
     * 查询店员服务列表
     */
    @PreAuthorize("@ss.hasPermi('system:adm_clkser:list')")
    @GetMapping("/list")
    public TableDataInfo list(AdmPsbsClkser admPsbsClkser)
    {
        startPage();
        List<AdmPsbsClkser> list = admPsbsClkserService.selectAdmPsbsClkserList(admPsbsClkser);
        return getDataTable(list);
    }

    /**
     * 导出店员服务列表
     */
    @PreAuthorize("@ss.hasPermi('system:adm_clkser:export')")
    @Log(title = "店员服务", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    public void export(HttpServletResponse response, AdmPsbsClkser admPsbsClkser)
    {
        List<AdmPsbsClkser> list = admPsbsClkserService.selectAdmPsbsClkserList(admPsbsClkser);
        ExcelUtil<AdmPsbsClkser> util = new ExcelUtil<AdmPsbsClkser>(AdmPsbsClkser.class);
        util.exportExcel(response, list, "店员服务数据");
    }

    /**
     * 获取店员服务详细信息
     */
    @PreAuthorize("@ss.hasPermi('system:adm_clkser:query')")
    @GetMapping(value = "/{clser}")
    public AjaxResult getInfo(@PathVariable("clser") Long clser)
    {
        return success(admPsbsClkserService.selectAdmPsbsClkserByClser(clser));
    }

    /**
     * 新增店员服务
     */
    @PreAuthorize("@ss.hasPermi('system:adm_clkser:add')")
    @Log(title = "店员服务", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody AdmPsbsClkser admPsbsClkser)
    {
        return toAjax(admPsbsClkserService.insertAdmPsbsClkser(admPsbsClkser));
    }

    /**
     * 修改店员服务
     */
    @PreAuthorize("@ss.hasPermi('system:adm_clkser:edit')")
    @Log(title = "店员服务", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody AdmPsbsClkser admPsbsClkser)
    {
        return toAjax(admPsbsClkserService.updateAdmPsbsClkser(admPsbsClkser));
    }

    /**
     * 删除店员服务
     */
    @PreAuthorize("@ss.hasPermi('system:adm_clkser:remove')")
    @Log(title = "店员服务", businessType = BusinessType.DELETE)
	@DeleteMapping("/{clsers}")
    public AjaxResult remove(@PathVariable Long[] clsers)
    {
        return toAjax(admPsbsClkserService.deleteAdmPsbsClkserByClsers(clsers));
    }
}
