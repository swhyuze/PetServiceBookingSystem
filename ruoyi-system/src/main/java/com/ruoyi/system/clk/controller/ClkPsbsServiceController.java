package com.ruoyi.system.clk.controller;

import java.util.List;
import javax.servlet.http.HttpServletResponse;

import com.ruoyi.common.utils.SecurityUtils;
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
import com.ruoyi.system.clk.domain.ClkPsbsService;
import com.ruoyi.system.clk.service.IClkPsbsServiceService;
import com.ruoyi.common.utils.poi.ExcelUtil;
import com.ruoyi.common.core.page.TableDataInfo;

/**
 * 订单查询Controller
 * 
 * @author 御泽
 * @date 2024-05-08
 */
@RestController
@RequestMapping("/system/clk_service")
public class ClkPsbsServiceController extends BaseController
{
    @Autowired
    private IClkPsbsServiceService clkPsbsServiceService;
    /**
     * 查询订单查询列表
     */
    @PreAuthorize("@ss.hasPermi('system:clk_service:list')")
    @GetMapping("/list")
    public TableDataInfo list(ClkPsbsService clkPsbsService)
    {
        startPage();
        List<ClkPsbsService> list = clkPsbsServiceService.selectClkPsbsServiceList(clkPsbsService);
        return getDataTable(list);
    }

    /**
     * 导出订单查询列表
     */
    @PreAuthorize("@ss.hasPermi('system:clk_service:export')")
    @Log(title = "订单查询", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    public void export(HttpServletResponse response, ClkPsbsService clkPsbsService)
    {
        List<ClkPsbsService> list = clkPsbsServiceService.selectClkPsbsServiceList(clkPsbsService);
        ExcelUtil<ClkPsbsService> util = new ExcelUtil<ClkPsbsService>(ClkPsbsService.class);
        util.exportExcel(response, list, "订单查询数据");
    }

    /**
     * 获取订单查询详细信息
     */
    @PreAuthorize("@ss.hasPermi('system:clk_service:query')")
    @GetMapping(value = "/{serid}")
    public AjaxResult getInfo(@PathVariable("serid") Long serid)
    {
        return success(clkPsbsServiceService.selectClkPsbsServiceBySerid(serid));
    }

    /**
     * 新增订单查询
     */
    @PreAuthorize("@ss.hasPermi('system:clk_service:add')")
    @Log(title = "订单查询", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody ClkPsbsService clkPsbsService)
    {
        return toAjax(clkPsbsServiceService.insertClkPsbsService(clkPsbsService));
    }

    /**
     * 修改订单查询
     */
    @PreAuthorize("@ss.hasPermi('system:clk_service:edit')")
    @Log(title = "订单查询", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody ClkPsbsService clkPsbsService)
    {
        return toAjax(clkPsbsServiceService.updateClkPsbsService(clkPsbsService));
    }

    /**
     * 删除订单查询
     */
    @PreAuthorize("@ss.hasPermi('system:clk_service:remove')")
    @Log(title = "订单查询", businessType = BusinessType.DELETE)
	@DeleteMapping("/{serids}")
    public AjaxResult remove(@PathVariable Long[] serids)
    {
        return toAjax(clkPsbsServiceService.deleteClkPsbsServiceBySerids(serids));
    }
}
