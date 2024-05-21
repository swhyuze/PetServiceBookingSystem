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
import com.ruoyi.system.man.domain.ManPsbsClerk;
import com.ruoyi.system.man.service.IManPsbsClerkService;
import com.ruoyi.common.utils.poi.ExcelUtil;
import com.ruoyi.common.core.page.TableDataInfo;

/**
 * 店员管理Controller
 * 
 * @author 御泽
 * @date 2024-05-08
 */
@RestController
@RequestMapping("/system/man_clerk")
public class ManPsbsClerkController extends BaseController
{
    @Autowired
    private IManPsbsClerkService manPsbsClerkService;

    @Autowired
    private IAdmPsbsManagerService admPsbsManagerService;
    /**
     * 查询店员管理列表
     */
    @PreAuthorize("@ss.hasPermi('system:man_clerk:list')")
    @GetMapping("/list")
    public TableDataInfo list(ManPsbsClerk manPsbsClerk)
    {
        AdmPsbsManager admPsbsManager=new AdmPsbsManager();
        admPsbsManager.setUid(SecurityUtils.getUserId());
        manPsbsClerk.setMid(admPsbsManagerService.selectAdmPsbsManagerList(admPsbsManager).get(0).getMid());
        startPage();
        List<ManPsbsClerk> list = manPsbsClerkService.selectManPsbsClerkList(manPsbsClerk);
        return getDataTable(list);
    }

    /**
     * 导出店员管理列表
     */
    @PreAuthorize("@ss.hasPermi('system:man_clerk:export')")
    @Log(title = "店员管理", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    public void export(HttpServletResponse response, ManPsbsClerk manPsbsClerk)
    {
        List<ManPsbsClerk> list = manPsbsClerkService.selectManPsbsClerkList(manPsbsClerk);
        ExcelUtil<ManPsbsClerk> util = new ExcelUtil<ManPsbsClerk>(ManPsbsClerk.class);
        util.exportExcel(response, list, "店员管理数据");
    }

    /**
     * 获取店员管理详细信息
     */
    @PreAuthorize("@ss.hasPermi('system:man_clerk:query')")
    @GetMapping(value = "/{clid}")
    public AjaxResult getInfo(@PathVariable("clid") Long clid)
    {
        return success(manPsbsClerkService.selectManPsbsClerkByClid(clid));
    }

    /**
     * 新增店员管理
     */
    @PreAuthorize("@ss.hasPermi('system:man_clerk:add')")
    @Log(title = "店员管理", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody ManPsbsClerk manPsbsClerk)
    {
        return toAjax(manPsbsClerkService.insertManPsbsClerk(manPsbsClerk));
    }

    /**
     * 修改店员管理
     */
    @PreAuthorize("@ss.hasPermi('system:man_clerk:edit')")
    @Log(title = "店员管理", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody ManPsbsClerk manPsbsClerk)
    {
        return toAjax(manPsbsClerkService.updateManPsbsClerk(manPsbsClerk));
    }

    /**
     * 删除店员管理
     */
    @PreAuthorize("@ss.hasPermi('system:man_clerk:remove')")
    @Log(title = "店员管理", businessType = BusinessType.DELETE)
	@DeleteMapping("/{clids}")
    public AjaxResult remove(@PathVariable Long[] clids)
    {
        return toAjax(manPsbsClerkService.deleteManPsbsClerkByClids(clids));
    }
}
