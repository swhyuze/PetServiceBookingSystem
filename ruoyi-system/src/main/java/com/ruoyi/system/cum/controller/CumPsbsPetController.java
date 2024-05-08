package com.ruoyi.system.cum.controller;

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
import com.ruoyi.system.cum.domain.CumPsbsPet;
import com.ruoyi.system.cum.service.ICumPsbsPetService;
import com.ruoyi.common.utils.poi.ExcelUtil;
import com.ruoyi.common.core.page.TableDataInfo;

/**
 * 宠物管理Controller
 * 
 * @author 御泽
 * @date 2024-05-08
 */
@RestController
@RequestMapping("/system/cum_pet")
public class CumPsbsPetController extends BaseController
{
    @Autowired
    private ICumPsbsPetService cumPsbsPetService;

    /**
     * 查询宠物管理列表
     */
    @PreAuthorize("@ss.hasPermi('system:cum_pet:list')")
    @GetMapping("/list")
    public TableDataInfo list(CumPsbsPet cumPsbsPet)
    {
        startPage();
        List<CumPsbsPet> list = cumPsbsPetService.selectCumPsbsPetList(cumPsbsPet);
        return getDataTable(list);
    }

    /**
     * 导出宠物管理列表
     */
    @PreAuthorize("@ss.hasPermi('system:cum_pet:export')")
    @Log(title = "宠物管理", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    public void export(HttpServletResponse response, CumPsbsPet cumPsbsPet)
    {
        List<CumPsbsPet> list = cumPsbsPetService.selectCumPsbsPetList(cumPsbsPet);
        ExcelUtil<CumPsbsPet> util = new ExcelUtil<CumPsbsPet>(CumPsbsPet.class);
        util.exportExcel(response, list, "宠物管理数据");
    }

    /**
     * 获取宠物管理详细信息
     */
    @PreAuthorize("@ss.hasPermi('system:cum_pet:query')")
    @GetMapping(value = "/{pid}")
    public AjaxResult getInfo(@PathVariable("pid") Long pid)
    {
        return success(cumPsbsPetService.selectCumPsbsPetByPid(pid));
    }

    /**
     * 新增宠物管理
     */
    @PreAuthorize("@ss.hasPermi('system:cum_pet:add')")
    @Log(title = "宠物管理", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody CumPsbsPet cumPsbsPet)
    {
        return toAjax(cumPsbsPetService.insertCumPsbsPet(cumPsbsPet));
    }

    /**
     * 修改宠物管理
     */
    @PreAuthorize("@ss.hasPermi('system:cum_pet:edit')")
    @Log(title = "宠物管理", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody CumPsbsPet cumPsbsPet)
    {
        return toAjax(cumPsbsPetService.updateCumPsbsPet(cumPsbsPet));
    }

    /**
     * 删除宠物管理
     */
    @PreAuthorize("@ss.hasPermi('system:cum_pet:remove')")
    @Log(title = "宠物管理", businessType = BusinessType.DELETE)
	@DeleteMapping("/{pids}")
    public AjaxResult remove(@PathVariable Long[] pids)
    {
        return toAjax(cumPsbsPetService.deleteCumPsbsPetByPids(pids));
    }
}
