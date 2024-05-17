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
import com.ruoyi.system.adm.domain.AdmPsbsServicetp;
import com.ruoyi.system.adm.service.IAdmPsbsServicetpService;
import com.ruoyi.common.utils.poi.ExcelUtil;
import com.ruoyi.common.core.page.TableDataInfo;

/**
 * 服务种类Controller
 * 
 * @author ruoyi
 * @date 2024-05-09
 */
@RestController
@RequestMapping("/system/adm_servicetp")
public class AdmPsbsServicetpController extends BaseController
{
    @Autowired
    private IAdmPsbsServicetpService admPsbsServicetpService;

    /**
     * 查询服务种类列表
     */
    @PreAuthorize("@ss.hasPermi('system:adm_servicetp:list')")
    @GetMapping("/list")
    public TableDataInfo list(AdmPsbsServicetp admPsbsServicetp)
    {
        startPage();
        List<AdmPsbsServicetp> list = admPsbsServicetpService.selectAdmPsbsServicetpList(admPsbsServicetp);
        return getDataTable(list);
    }

    /**
     * 导出服务种类列表
     */
    @PreAuthorize("@ss.hasPermi('system:adm_servicetp:export')")
    @Log(title = "服务种类", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    public void export(HttpServletResponse response, AdmPsbsServicetp admPsbsServicetp)
    {
        List<AdmPsbsServicetp> list = admPsbsServicetpService.selectAdmPsbsServicetpList(admPsbsServicetp);
        ExcelUtil<AdmPsbsServicetp> util = new ExcelUtil<AdmPsbsServicetp>(AdmPsbsServicetp.class);
        util.exportExcel(response, list, "服务种类数据");
    }

    /**
     * 获取服务种类详细信息
     */
    @PreAuthorize("@ss.hasPermi('system:adm_servicetp:query')")
    @GetMapping(value = "/{stid}")
    public AjaxResult getInfo(@PathVariable("stid") Long stid)
    {
        return success(admPsbsServicetpService.selectAdmPsbsServicetpByStid(stid));
    }

    /**
     * 新增服务种类
     */
    @PreAuthorize("@ss.hasPermi('system:adm_servicetp:add')")
    @Log(title = "服务种类", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody AdmPsbsServicetp admPsbsServicetp)
    {
        return toAjax(admPsbsServicetpService.insertAdmPsbsServicetp(admPsbsServicetp));
    }

    /**
     * 修改服务种类
     */
    @PreAuthorize("@ss.hasPermi('system:adm_servicetp:edit')")
    @Log(title = "服务种类", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody AdmPsbsServicetp admPsbsServicetp)
    {
        return toAjax(admPsbsServicetpService.updateAdmPsbsServicetp(admPsbsServicetp));
    }

    /**
     * 删除服务种类
     */
    @PreAuthorize("@ss.hasPermi('system:adm_servicetp:remove')")
    @Log(title = "服务种类", businessType = BusinessType.DELETE)
	@DeleteMapping("/{stids}")
    public AjaxResult remove(@PathVariable Long[] stids)
    {
        return toAjax(admPsbsServicetpService.deleteAdmPsbsServicetpByStids(stids));
    }

    @GetMapping("/selectAllAdmServicetp")
    public TableDataInfo list()
    {
        List<AdmPsbsServicetp> list = admPsbsServicetpService.selectAllAdmServicetp();
        return getDataTable(list);
    }
}
