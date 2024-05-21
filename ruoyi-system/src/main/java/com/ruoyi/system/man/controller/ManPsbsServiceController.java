package com.ruoyi.system.man.controller;

import java.util.List;
import javax.servlet.http.HttpServletResponse;

import com.ruoyi.common.utils.SecurityUtils;
import com.ruoyi.system.adm.domain.AdmPsbsManager;
import com.ruoyi.system.adm.service.IAdmPsbsManagerService;
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
import com.ruoyi.system.man.domain.ManPsbsService;
import com.ruoyi.system.man.service.IManPsbsServiceService;
import com.ruoyi.common.utils.poi.ExcelUtil;
import com.ruoyi.common.core.page.TableDataInfo;

/**
 * 订单管理Controller
 * 
 * @author 御泽
 * @date 2024-05-08
 */
@RestController
@RequestMapping("/system/man_service")
public class ManPsbsServiceController extends BaseController
{
    @Autowired
    private IManPsbsServiceService manPsbsServiceService;
    @Autowired
    private IAdmPsbsManagerService admPsbsManagerService;
    /**
     * 查询订单管理列表
     */
    @PreAuthorize("@ss.hasPermi('system:man_service:list')")
    @GetMapping("/list")
    public TableDataInfo list(ManPsbsService manPsbsService)
    {
        AdmPsbsManager admPsbsManager=new AdmPsbsManager();
        admPsbsManager.setUid(SecurityUtils.getUserId());
        manPsbsService.setMid(admPsbsManagerService.selectAdmPsbsManagerList(admPsbsManager).get(0).getMid());
        startPage();
        List<ManPsbsService> list = manPsbsServiceService.selectManPsbsServiceList(manPsbsService);
        return getDataTable(list);
    }

    /**
     * 导出订单管理列表
     */
    @PreAuthorize("@ss.hasPermi('system:man_service:export')")
    @Log(title = "订单管理", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    public void export(HttpServletResponse response, ManPsbsService manPsbsService)
    {
        List<ManPsbsService> list = manPsbsServiceService.selectManPsbsServiceList(manPsbsService);
        ExcelUtil<ManPsbsService> util = new ExcelUtil<ManPsbsService>(ManPsbsService.class);
        util.exportExcel(response, list, "订单管理数据");
    }

    /**
     * 获取订单管理详细信息
     */
    @PreAuthorize("@ss.hasPermi('system:man_service:query')")
    @GetMapping(value = "/{serid}")
    public AjaxResult getInfo(@PathVariable("serid") Long serid)
    {
        return success(manPsbsServiceService.selectManPsbsServiceBySerid(serid));
    }

    /**
     * 新增订单管理
     */
    @PreAuthorize("@ss.hasPermi('system:man_service:add')")
    @Log(title = "订单管理", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody ManPsbsService manPsbsService)
    {
        return toAjax(manPsbsServiceService.insertManPsbsService(manPsbsService));
    }

    /**
     * 修改订单管理
     */
    @PreAuthorize("@ss.hasPermi('system:man_service:edit')")
    @Log(title = "订单管理", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody ManPsbsService manPsbsService)
    {
        return toAjax(manPsbsServiceService.updateManPsbsService(manPsbsService));
    }

    /**
     * 删除订单管理
     */
    @PreAuthorize("@ss.hasPermi('system:man_service:remove')")
    @Log(title = "订单管理", businessType = BusinessType.DELETE)
	@DeleteMapping("/{serids}")
    public AjaxResult remove(@PathVariable Long[] serids)
    {
        return toAjax(manPsbsServiceService.deleteManPsbsServiceBySerids(serids));
    }
}
