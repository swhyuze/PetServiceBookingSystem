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
import com.ruoyi.system.domain.PsbsCustomer;
import com.ruoyi.system.service.IPsbsCustomerService;
import com.ruoyi.common.utils.poi.ExcelUtil;
import com.ruoyi.common.core.page.TableDataInfo;

/**
 * 顾客管理Controller
 * 
 * @author 御泽
 * @date 2024-05-03
 */
@RestController
@RequestMapping("/system/adm_customer")
public class PsbsCustomerController extends BaseController
{
    @Autowired
    private IPsbsCustomerService psbsCustomerService;

    /**
     * 查询顾客管理列表
     */
    @PreAuthorize("@ss.hasPermi('system:adm_customer:list')")
    @GetMapping("/list")
    public TableDataInfo list(PsbsCustomer psbsCustomer)
    {
        startPage();
        List<PsbsCustomer> list = psbsCustomerService.selectPsbsCustomerList(psbsCustomer);
        return getDataTable(list);
    }

    /**
     * 导出顾客管理列表
     */
    @PreAuthorize("@ss.hasPermi('system:adm_customer:export')")
    @Log(title = "顾客管理", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    public void export(HttpServletResponse response, PsbsCustomer psbsCustomer)
    {
        List<PsbsCustomer> list = psbsCustomerService.selectPsbsCustomerList(psbsCustomer);
        ExcelUtil<PsbsCustomer> util = new ExcelUtil<PsbsCustomer>(PsbsCustomer.class);
        util.exportExcel(response, list, "顾客管理数据");
    }

    /**
     * 获取顾客管理详细信息
     */
    @PreAuthorize("@ss.hasPermi('system:adm_customer:query')")
    @GetMapping(value = "/{cuid}")
    public AjaxResult getInfo(@PathVariable("cuid") Long cuid)
    {
        return success(psbsCustomerService.selectPsbsCustomerByCuid(cuid));
    }

    /**
     * 新增顾客管理
     */
    @PreAuthorize("@ss.hasPermi('system:adm_customer:add')")
    @Log(title = "顾客管理", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody PsbsCustomer psbsCustomer)
    {
        return toAjax(psbsCustomerService.insertPsbsCustomer(psbsCustomer));
    }

    /**
     * 修改顾客管理
     */
    @PreAuthorize("@ss.hasPermi('system:adm_customer:edit')")
    @Log(title = "顾客管理", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody PsbsCustomer psbsCustomer)
    {
        return toAjax(psbsCustomerService.updatePsbsCustomer(psbsCustomer));
    }

    /**
     * 删除顾客管理
     */
    @PreAuthorize("@ss.hasPermi('system:adm_customer:remove')")
    @Log(title = "顾客管理", businessType = BusinessType.DELETE)
	@DeleteMapping("/{cuids}")
    public AjaxResult remove(@PathVariable Long[] cuids)
    {
        return toAjax(psbsCustomerService.deletePsbsCustomerByCuids(cuids));
    }
}
