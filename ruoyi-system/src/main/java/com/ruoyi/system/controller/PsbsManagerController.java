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
import com.ruoyi.system.domain.PsbsManager;
import com.ruoyi.system.service.IPsbsManagerService;
import com.ruoyi.common.utils.poi.ExcelUtil;
import com.ruoyi.common.core.page.TableDataInfo;

/**
 * 宠物店管理Controller
 * 
 * @author ruoyi
 * @date 2024-04-23
 */
@RestController
@RequestMapping("/system/manager")
public class PsbsManagerController extends BaseController
{
    @Autowired
    private IPsbsManagerService psbsManagerService;

    /**
     * 查询宠物店管理列表
     */
    @PreAuthorize("@ss.hasPermi('system:manager:list')")
    @GetMapping("/list")
    public TableDataInfo list(PsbsManager psbsManager)
    {
        startPage();
        List<PsbsManager> list = psbsManagerService.selectPsbsManagerList(psbsManager);
        return getDataTable(list);
    }

    /**
     * 导出宠物店管理列表
     */
    @PreAuthorize("@ss.hasPermi('system:manager:export')")
    @Log(title = "宠物店管理", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    public void export(HttpServletResponse response, PsbsManager psbsManager)
    {
        List<PsbsManager> list = psbsManagerService.selectPsbsManagerList(psbsManager);
        ExcelUtil<PsbsManager> util = new ExcelUtil<PsbsManager>(PsbsManager.class);
        util.exportExcel(response, list, "宠物店管理数据");
    }

    /**
     * 获取宠物店管理详细信息
     */
    @PreAuthorize("@ss.hasPermi('system:manager:query')")
    @GetMapping(value = "/{mid}")
    public AjaxResult getInfo(@PathVariable("mid") Long mid)
    {
        return success(psbsManagerService.selectPsbsManagerByMid(mid));
    }

    /**
     * 新增宠物店管理
     */
    @PreAuthorize("@ss.hasPermi('system:manager:add')")
    @Log(title = "宠物店管理", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody PsbsManager psbsManager)
    {
        return toAjax(psbsManagerService.insertPsbsManager(psbsManager));
    }

    /**
     * 修改宠物店管理
     */
    @PreAuthorize("@ss.hasPermi('system:manager:edit')")
    @Log(title = "宠物店管理", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody PsbsManager psbsManager)
    {
        return toAjax(psbsManagerService.updatePsbsManager(psbsManager));
    }

    /**
     * 删除宠物店管理
     */
    @PreAuthorize("@ss.hasPermi('system:manager:remove')")
    @Log(title = "宠物店管理", businessType = BusinessType.DELETE)
	@DeleteMapping("/{mids}")
    public AjaxResult remove(@PathVariable Long[] mids)
    {
        return toAjax(psbsManagerService.deletePsbsManagerByMids(mids));
    }
}
