package com.ruoyi.system.man.controller;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import javax.servlet.http.HttpServletResponse;

import com.ruoyi.common.utils.SecurityUtils;
import com.ruoyi.system.adm.domain.*;
import com.ruoyi.system.adm.service.*;
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
    @Autowired
    private IAdmPsbsCustomerService admPsbsCustomerService;
    @Autowired
    private IAdmPsbsPetService admPsbsPetService;
    @Autowired
    private IAdmPsbsClerkService admPsbsClerkService;
    @Autowired
    private IAdmPsbsServicetpService admPsbsServicetpService;
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
        Long sttime=admPsbsServicetpService.selectAdmPsbsServicetpByStid(manPsbsService.getStid()).getSttime();
        System.out.println(sttime);
        Date endDate= (Date) manPsbsService.getSerstime().clone();
        endDate.setTime(endDate.getTime()+sttime*60*1000);
        manPsbsService.setSeretime(endDate);
        manPsbsService.setSerstate((long)0);
        List<ManPsbsService> list=manPsbsServiceService.selectTime(manPsbsService);
        if (manPsbsService.getSeretime().getHours()<9||manPsbsService.getSeretime().getHours()>20){
            return toAjax(0);
        }
        for (ManPsbsService service : list) {
            if (service.getSerstime().before(manPsbsService.getSeretime()) && service.getSeretime().after(manPsbsService.getSerstime())) {
                return toAjax(0);
            }
        }
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

    @GetMapping("/getMid")
    public TableDataInfo getMid(ManPsbsService manPsbsService)
    {
        List list=new ArrayList<>();
        AdmPsbsManager admPsbsManager=new AdmPsbsManager();
        admPsbsManager.setUid(SecurityUtils.getUserId());
        admPsbsManagerService.selectAdmPsbsManagerList(admPsbsManager).get(0).getMid();
        list.add(admPsbsManagerService.selectAdmPsbsManagerList(admPsbsManager).get(0).getMid());
        list.add(admPsbsManagerService.selectAdmPsbsManagerList(admPsbsManager).get(0).getMsname());
        return getDataTable(list);
    }
    @GetMapping("/selectCumstomer")
    public TableDataInfo selectCumstomer(){
        List<AdmPsbsCustomer> list = admPsbsCustomerService.selectAdmPsbsCustomerList(new AdmPsbsCustomer());
        return getDataTable(list);
    }
    @GetMapping("/selectPet")
    public TableDataInfo selectPet(AdmPsbsPet admPsbsPet){
        List<AdmPsbsPet> list=admPsbsPetService.selectAdmPsbsPetList(admPsbsPet);
        return getDataTable(list);
    }

    @GetMapping("/selectSerivetp")
    public TableDataInfo selectSerivetp(AdmPsbsServicetp admPsbsServicetp){
        List<AdmPsbsServicetp> list=admPsbsServicetpService.selectSerivetp(admPsbsServicetp);
        return getDataTable(list);
    }
    @GetMapping("/selectClerk")
    public TableDataInfo selectSerivetp(AdmPsbsClerk admPsbsClerk){
        List<AdmPsbsClerk> list=admPsbsClerkService.selectClerk(admPsbsClerk);
        return getDataTable(list);
    }

    @GetMapping("/selectTime")
    public TableDataInfo selectTime(ManPsbsService manPsbsService){
        manPsbsService.setSerstate((long)0);
        List<ManPsbsService> list=manPsbsServiceService.selectTime(manPsbsService);
        return getDataTable(list);
    }
}
