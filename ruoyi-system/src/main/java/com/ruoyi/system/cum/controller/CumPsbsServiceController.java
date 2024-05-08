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
import com.ruoyi.system.cum.domain.CumPsbsService;
import com.ruoyi.system.cum.service.ICumPsbsServiceService;
import com.ruoyi.common.utils.poi.ExcelUtil;
import com.ruoyi.common.core.page.TableDataInfo;

/**
 * 订单查询Controller
 * 
 * @author 御泽
 * @date 2024-05-08
 */
@RestController
@RequestMapping("/system/cum_service")
public class CumPsbsServiceController extends BaseController
{
    @Autowired
    private ICumPsbsServiceService cumPsbsServiceService;

    /**
     * 查询订单查询列表
     */
    @PreAuthorize("@ss.hasPermi('system:cum_service:list')")
    @GetMapping("/list")
    public TableDataInfo list(CumPsbsService cumPsbsService)
    {
        startPage();
        List<CumPsbsService> list = cumPsbsServiceService.selectCumPsbsServiceList(cumPsbsService);
        return getDataTable(list);
    }

    /**
     * 导出订单查询列表
     */
    @PreAuthorize("@ss.hasPermi('system:cum_service:export')")
    @Log(title = "订单查询", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    public void export(HttpServletResponse response, CumPsbsService cumPsbsService)
    {
        List<CumPsbsService> list = cumPsbsServiceService.selectCumPsbsServiceList(cumPsbsService);
        ExcelUtil<CumPsbsService> util = new ExcelUtil<CumPsbsService>(CumPsbsService.class);
        util.exportExcel(response, list, "订单查询数据");
    }

    /**
     * 获取订单查询详细信息
     */
    @PreAuthorize("@ss.hasPermi('system:cum_service:query')")
    @GetMapping(value = "/{serid}")
    public AjaxResult getInfo(@PathVariable("serid") Long serid)
    {
        return success(cumPsbsServiceService.selectCumPsbsServiceBySerid(serid));
    }

    /**
     * 新增订单查询
     */
    @PreAuthorize("@ss.hasPermi('system:cum_service:add')")
    @Log(title = "订单查询", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody CumPsbsService cumPsbsService)
    {
        return toAjax(cumPsbsServiceService.insertCumPsbsService(cumPsbsService));
    }

    /**
     * 修改订单查询
     */
    @PreAuthorize("@ss.hasPermi('system:cum_service:edit')")
    @Log(title = "订单查询", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody CumPsbsService cumPsbsService)
    {
        return toAjax(cumPsbsServiceService.updateCumPsbsService(cumPsbsService));
    }

    /**
     * 删除订单查询
     */
    @PreAuthorize("@ss.hasPermi('system:cum_service:remove')")
    @Log(title = "订单查询", businessType = BusinessType.DELETE)
	@DeleteMapping("/{serids}")
    public AjaxResult remove(@PathVariable Long[] serids)
    {
        return toAjax(cumPsbsServiceService.deleteCumPsbsServiceBySerids(serids));
    }
}
