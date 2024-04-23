package com.ruoyi.system.controller;

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
import com.ruoyi.system.domain.PsbsClerk;
import com.ruoyi.system.service.IPsbsClerkService;
import com.ruoyi.common.utils.poi.ExcelUtil;
import com.ruoyi.common.core.page.TableDataInfo;

/**
 * 店员Controller
 * 
 * @author ruoyi
 * @date 2024-04-23
 */
@RestController
@RequestMapping("/system/clerk")
public class PsbsClerkController extends BaseController
{
    @Autowired
    private IPsbsClerkService psbsClerkService;

    /**
     * 查询店员列表
     */
    @PreAuthorize("@ss.hasPermi('system:clerk:list')")
    @GetMapping("/list")
    public TableDataInfo list(PsbsClerk psbsClerk)
    {
        startPage();
        List<PsbsClerk> list = psbsClerkService.selectPsbsClerkList(psbsClerk);
        return getDataTable(list);
    }

    /**
     * 导出店员列表
     */
    @PreAuthorize("@ss.hasPermi('system:clerk:export')")
    @Log(title = "店员", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    public void export(HttpServletResponse response, PsbsClerk psbsClerk)
    {
        List<PsbsClerk> list = psbsClerkService.selectPsbsClerkList(psbsClerk);
        ExcelUtil<PsbsClerk> util = new ExcelUtil<PsbsClerk>(PsbsClerk.class);
        util.exportExcel(response, list, "店员数据");
    }

    /**
     * 获取店员详细信息
     */
    @PreAuthorize("@ss.hasPermi('system:clerk:query')")
    @GetMapping(value = "/{clid}")
    public AjaxResult getInfo(@PathVariable("clid") Long clid)
    {
        return success(psbsClerkService.selectPsbsClerkByClid(clid));
    }

    /**
     * 新增店员
     */
    @PreAuthorize("@ss.hasPermi('system:clerk:add')")
    @Log(title = "店员", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody PsbsClerk psbsClerk)
    {
        return toAjax(psbsClerkService.insertPsbsClerk(psbsClerk));
    }

    /**
     * 修改店员
     */
    @PreAuthorize("@ss.hasPermi('system:clerk:edit')")
    @Log(title = "店员", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody PsbsClerk psbsClerk)
    {
        return toAjax(psbsClerkService.updatePsbsClerk(psbsClerk));
    }

    /**
     * 删除店员
     */
    @PreAuthorize("@ss.hasPermi('system:clerk:remove')")
    @Log(title = "店员", businessType = BusinessType.DELETE)
	@DeleteMapping("/{clids}")
    public AjaxResult remove(@PathVariable Long[] clids)
    {
        return toAjax(psbsClerkService.deletePsbsClerkByClids(clids));
    }
}
