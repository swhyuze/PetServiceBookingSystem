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
import com.ruoyi.system.clk.domain.ClkPsbsServicetp;
import com.ruoyi.system.clk.service.IClkPsbsServicetpService;
import com.ruoyi.common.utils.poi.ExcelUtil;
import com.ruoyi.common.core.page.TableDataInfo;

/**
 * 服务资质Controller
 * 
 * @author ruoyi
 * @date 2024-05-08
 */
@RestController
@RequestMapping("/system/clk_servicetp")
public class ClkPsbsServicetpController extends BaseController
{
    @Autowired
    private IClkPsbsServicetpService clkPsbsServicetpService;

    /**
     * 查询服务资质列表
     */
    @PreAuthorize("@ss.hasPermi('system:clk_servicetp:list')")
    @GetMapping("/list")
    public TableDataInfo list(ClkPsbsServicetp clkPsbsServicetp)
    {
        clkPsbsServicetp.setUid(SecurityUtils.getUserId());
        startPage();
        List<ClkPsbsServicetp> list = clkPsbsServicetpService.selectClkPsbsServicetpList(clkPsbsServicetp);
        return getDataTable(list);
    }

    /**
     * 导出服务资质列表
     */
    @PreAuthorize("@ss.hasPermi('system:clk_servicetp:export')")
    @Log(title = "服务资质", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    public void export(HttpServletResponse response, ClkPsbsServicetp clkPsbsServicetp)
    {
        List<ClkPsbsServicetp> list = clkPsbsServicetpService.selectClkPsbsServicetpList(clkPsbsServicetp);
        ExcelUtil<ClkPsbsServicetp> util = new ExcelUtil<ClkPsbsServicetp>(ClkPsbsServicetp.class);
        util.exportExcel(response, list, "服务资质数据");
    }

    /**
     * 获取服务资质详细信息
     */
    @PreAuthorize("@ss.hasPermi('system:clk_servicetp:query')")
    @GetMapping(value = "/{stid}")
    public AjaxResult getInfo(@PathVariable("stid") Long stid)
    {
        return success(clkPsbsServicetpService.selectClkPsbsServicetpByStid(stid));
    }

    /**
     * 新增服务资质
     */
    @PreAuthorize("@ss.hasPermi('system:clk_servicetp:add')")
    @Log(title = "服务资质", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody ClkPsbsServicetp clkPsbsServicetp)
    {
        return toAjax(clkPsbsServicetpService.insertClkPsbsServicetp(clkPsbsServicetp));
    }

    /**
     * 修改服务资质
     */
    @PreAuthorize("@ss.hasPermi('system:clk_servicetp:edit')")
    @Log(title = "服务资质", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody ClkPsbsServicetp clkPsbsServicetp)
    {
        return toAjax(clkPsbsServicetpService.updateClkPsbsServicetp(clkPsbsServicetp));
    }

    /**
     * 删除服务资质
     */
    @PreAuthorize("@ss.hasPermi('system:clk_servicetp:remove')")
    @Log(title = "服务资质", businessType = BusinessType.DELETE)
	@DeleteMapping("/{stids}")
    public AjaxResult remove(@PathVariable Long[] stids)
    {
        return toAjax(clkPsbsServicetpService.deleteClkPsbsServicetpByStids(stids));
    }
}
