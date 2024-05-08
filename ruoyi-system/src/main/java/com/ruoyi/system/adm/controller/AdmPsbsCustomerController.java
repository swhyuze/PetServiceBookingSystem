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
import com.ruoyi.system.adm.domain.AdmPsbsCustomer;
import com.ruoyi.system.adm.service.IAdmPsbsCustomerService;
import com.ruoyi.common.utils.poi.ExcelUtil;
import com.ruoyi.common.core.page.TableDataInfo;

/**
 * 顾客管理Controller
 * 
 * @author 御泽
 * @date 2024-05-06
 */
@RestController
@RequestMapping("/system/adm_customer")
public class AdmPsbsCustomerController extends BaseController
{
    @Autowired
    private IAdmPsbsCustomerService admPsbsCustomerService;

    /**
     * 查询顾客管理列表
     */
    @PreAuthorize("@ss.hasPermi('system:adm_customer:list')")
    @GetMapping("/list")
    public TableDataInfo list(AdmPsbsCustomer admPsbsCustomer)
    {
        startPage();
        List<AdmPsbsCustomer> list = admPsbsCustomerService.selectAdmPsbsCustomerList(admPsbsCustomer);
        return getDataTable(list);
    }

    /**
     * 导出顾客管理列表
     */
    @PreAuthorize("@ss.hasPermi('system:adm_customer:export')")
    @Log(title = "顾客管理", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    public void export(HttpServletResponse response, AdmPsbsCustomer admPsbsCustomer)
    {
        List<AdmPsbsCustomer> list = admPsbsCustomerService.selectAdmPsbsCustomerList(admPsbsCustomer);
        ExcelUtil<AdmPsbsCustomer> util = new ExcelUtil<AdmPsbsCustomer>(AdmPsbsCustomer.class);
        util.exportExcel(response, list, "顾客管理数据");
    }

    /**
     * 获取顾客管理详细信息
     */
    @PreAuthorize("@ss.hasPermi('system:adm_customer:query')")
    @GetMapping(value = "/{cuid}")
    public AjaxResult getInfo(@PathVariable("cuid") Long cuid)
    {
        return success(admPsbsCustomerService.selectAdmPsbsCustomerByCuid(cuid));
    }

    /**
     * 新增顾客管理
     */
    @PreAuthorize("@ss.hasPermi('system:adm_customer:add')")
    @Log(title = "顾客管理", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody AdmPsbsCustomer admPsbsCustomer)
    {
        return toAjax(admPsbsCustomerService.insertAdmPsbsCustomer(admPsbsCustomer));
    }

    /**
     * 修改顾客管理
     */
    @PreAuthorize("@ss.hasPermi('system:adm_customer:edit')")
    @Log(title = "顾客管理", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody AdmPsbsCustomer admPsbsCustomer)
    {
        return toAjax(admPsbsCustomerService.updateAdmPsbsCustomer(admPsbsCustomer));
    }

    /**
     * 删除顾客管理
     */
    @PreAuthorize("@ss.hasPermi('system:adm_customer:remove')")
    @Log(title = "顾客管理", businessType = BusinessType.DELETE)
	@DeleteMapping("/{cuids}")
    public AjaxResult remove(@PathVariable Long[] cuids)
    {
        return toAjax(admPsbsCustomerService.deleteAdmPsbsCustomerByCuids(cuids));
    }
}
