package com.ruoyi.system.adm.controller;

import java.util.Date;
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
import com.ruoyi.system.adm.domain.AdmPsbsService;
import com.ruoyi.system.adm.service.IAdmPsbsServiceService;
import com.ruoyi.common.utils.poi.ExcelUtil;
import com.ruoyi.common.core.page.TableDataInfo;

/**
 * 服务记录Controller
 * 
 * @author 御泽
 * @date 2024-05-06
 */
@RestController
@RequestMapping("/system/adm_service")
public class AdmPsbsServiceController extends BaseController
{
    @Autowired
    private IAdmPsbsServiceService admPsbsServiceService;

    /**
     * 查询服务记录列表
     */
    @PreAuthorize("@ss.hasPermi('system:adm_service:list')")
    @GetMapping("/list")
    public TableDataInfo list(AdmPsbsService admPsbsService)
    {
        startPage();
        List<AdmPsbsService> list = admPsbsServiceService.selectAdmPsbsServiceList(admPsbsService);
        return getDataTable(list);
    }

    /**
     * 导出服务记录列表
     */
    @PreAuthorize("@ss.hasPermi('system:adm_service:export')")
    @Log(title = "服务记录", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    public void export(HttpServletResponse response, AdmPsbsService admPsbsService)
    {
        List<AdmPsbsService> list = admPsbsServiceService.selectAdmPsbsServiceList(admPsbsService);
        ExcelUtil<AdmPsbsService> util = new ExcelUtil<AdmPsbsService>(AdmPsbsService.class);
        util.exportExcel(response, list, "服务记录数据");
    }

    /**
     * 获取服务记录详细信息
     */
    @PreAuthorize("@ss.hasPermi('system:adm_service:query')")
    @GetMapping(value = "/{serid}")
    public AjaxResult getInfo(@PathVariable("serid") Long serid)
    {
        return success(admPsbsServiceService.selectAdmPsbsServiceBySerid(serid));
    }

    /**
     * 新增服务记录
     */
    @Log(title = "服务记录", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody AdmPsbsService admPsbsService)
    {
        return toAjax(admPsbsServiceService.insertAdmPsbsService(admPsbsService));
    }

    /**
     * 修改服务记录
     */
    @PreAuthorize("@ss.hasPermi('system:adm_service:edit')")
    @Log(title = "服务记录", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody AdmPsbsService admPsbsService)
    {
        return toAjax(admPsbsServiceService.updateAdmPsbsService(admPsbsService));
    }

    /**
     * 删除服务记录
     */
    @PreAuthorize("@ss.hasPermi('system:adm_service:remove')")
    @Log(title = "服务记录", businessType = BusinessType.DELETE)
	@DeleteMapping("/{serids}")
    public AjaxResult remove(@PathVariable Long[] serids)
    {
        return toAjax(admPsbsServiceService.deleteAdmPsbsServiceBySerids(serids));
    }
}
