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
import com.ruoyi.system.cum.domain.CumPsbsServicetp;
import com.ruoyi.system.cum.service.ICumPsbsServicetpService;
import com.ruoyi.common.utils.poi.ExcelUtil;
import com.ruoyi.common.core.page.TableDataInfo;

/**
 * 服务种类Controller
 * 
 * @author ruoyi
 * @date 2024-05-08
 */
@RestController
@RequestMapping("/system/cum_servicetp")
public class CumPsbsServicetpController extends BaseController
{
    @Autowired
    private ICumPsbsServicetpService cumPsbsServicetpService;

    /**
     * 查询服务种类列表
     */
    @PreAuthorize("@ss.hasPermi('system:cum_servicetp:list')")
    @GetMapping("/list")
    public TableDataInfo list(CumPsbsServicetp cumPsbsServicetp)
    {
        startPage();
        List<CumPsbsServicetp> list = cumPsbsServicetpService.selectCumPsbsServicetpList(cumPsbsServicetp);
        return getDataTable(list);
    }

    /**
     * 导出服务种类列表
     */
    @PreAuthorize("@ss.hasPermi('system:cum_servicetp:export')")
    @Log(title = "服务种类", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    public void export(HttpServletResponse response, CumPsbsServicetp cumPsbsServicetp)
    {
        List<CumPsbsServicetp> list = cumPsbsServicetpService.selectCumPsbsServicetpList(cumPsbsServicetp);
        ExcelUtil<CumPsbsServicetp> util = new ExcelUtil<CumPsbsServicetp>(CumPsbsServicetp.class);
        util.exportExcel(response, list, "服务种类数据");
    }

    /**
     * 获取服务种类详细信息
     */
    @PreAuthorize("@ss.hasPermi('system:cum_servicetp:query')")
    @GetMapping(value = "/{stid}")
    public AjaxResult getInfo(@PathVariable("stid") Long stid)
    {
        return success(cumPsbsServicetpService.selectCumPsbsServicetpByStid(stid));
    }

    /**
     * 新增服务种类
     */
    @PreAuthorize("@ss.hasPermi('system:cum_servicetp:add')")
    @Log(title = "服务种类", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody CumPsbsServicetp cumPsbsServicetp)
    {
        return toAjax(cumPsbsServicetpService.insertCumPsbsServicetp(cumPsbsServicetp));
    }

    /**
     * 修改服务种类
     */
    @PreAuthorize("@ss.hasPermi('system:cum_servicetp:edit')")
    @Log(title = "服务种类", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody CumPsbsServicetp cumPsbsServicetp)
    {
        return toAjax(cumPsbsServicetpService.updateCumPsbsServicetp(cumPsbsServicetp));
    }

    /**
     * 删除服务种类
     */
    @PreAuthorize("@ss.hasPermi('system:cum_servicetp:remove')")
    @Log(title = "服务种类", businessType = BusinessType.DELETE)
	@DeleteMapping("/{stids}")
    public AjaxResult remove(@PathVariable Long[] stids)
    {
        return toAjax(cumPsbsServicetpService.deleteCumPsbsServicetpByStids(stids));
    }
}
