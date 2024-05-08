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
import com.ruoyi.system.adm.domain.AdmPsbsManager;
import com.ruoyi.system.adm.service.IAdmPsbsManagerService;
import com.ruoyi.common.utils.poi.ExcelUtil;
import com.ruoyi.common.core.page.TableDataInfo;

/**
 * 宠物店管理Controller
 * 
 * @author 御泽
 * @date 2024-05-06
 */
@RestController
@RequestMapping("/system/adm_manager")
public class AdmPsbsManagerController extends BaseController
{
    @Autowired
    private IAdmPsbsManagerService admPsbsManagerService;

    /**
     * 查询宠物店管理列表
     */
    @PreAuthorize("@ss.hasPermi('system:adm_manager:list')")
    @GetMapping("/list")
    public TableDataInfo list(AdmPsbsManager admPsbsManager)
    {
        startPage();
        List<AdmPsbsManager> list = admPsbsManagerService.selectAdmPsbsManagerList(admPsbsManager);
        return getDataTable(list);
    }

    /**
     * 导出宠物店管理列表
     */
    @PreAuthorize("@ss.hasPermi('system:adm_manager:export')")
    @Log(title = "宠物店管理", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    public void export(HttpServletResponse response, AdmPsbsManager admPsbsManager)
    {
        List<AdmPsbsManager> list = admPsbsManagerService.selectAdmPsbsManagerList(admPsbsManager);
        ExcelUtil<AdmPsbsManager> util = new ExcelUtil<AdmPsbsManager>(AdmPsbsManager.class);
        util.exportExcel(response, list, "宠物店管理数据");
    }

    /**
     * 获取宠物店管理详细信息
     */
    @PreAuthorize("@ss.hasPermi('system:adm_manager:query')")
    @GetMapping(value = "/{mid}")
    public AjaxResult getInfo(@PathVariable("mid") Long mid)
    {
        return success(admPsbsManagerService.selectAdmPsbsManagerByMid(mid));
    }

    /**
     * 新增宠物店管理
     */
    @PreAuthorize("@ss.hasPermi('system:adm_manager:add')")
    @Log(title = "宠物店管理", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody AdmPsbsManager admPsbsManager)
    {
        return toAjax(admPsbsManagerService.insertAdmPsbsManager(admPsbsManager));
    }

    /**
     * 修改宠物店管理
     */
    @PreAuthorize("@ss.hasPermi('system:adm_manager:edit')")
    @Log(title = "宠物店管理", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody AdmPsbsManager admPsbsManager)
    {
        return toAjax(admPsbsManagerService.updateAdmPsbsManager(admPsbsManager));
    }

    /**
     * 删除宠物店管理
     */
    @PreAuthorize("@ss.hasPermi('system:adm_manager:remove')")
    @Log(title = "宠物店管理", businessType = BusinessType.DELETE)
	@DeleteMapping("/{mids}")
    public AjaxResult remove(@PathVariable Long[] mids)
    {
        return toAjax(admPsbsManagerService.deleteAdmPsbsManagerByMids(mids));
    }
}
