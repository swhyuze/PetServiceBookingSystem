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
import com.ruoyi.system.adm.domain.AdmPsbsPetstate;
import com.ruoyi.system.adm.service.IAdmPsbsPetstateService;
import com.ruoyi.common.utils.poi.ExcelUtil;
import com.ruoyi.common.core.page.TableDataInfo;

/**
 * 宠物健康Controller
 * 
 * @author 御泽
 * @date 2024-05-06
 */
@RestController
@RequestMapping("/system/adm_petstate")
public class AdmPsbsPetstateController extends BaseController
{
    @Autowired
    private IAdmPsbsPetstateService admPsbsPetstateService;

    /**
     * 查询宠物健康列表
     */
    @PreAuthorize("@ss.hasPermi('system:adm_petstate:list')")
    @GetMapping("/list")
    public TableDataInfo list(AdmPsbsPetstate admPsbsPetstate)
    {
        startPage();
        List<AdmPsbsPetstate> list = admPsbsPetstateService.selectAdmPsbsPetstateList(admPsbsPetstate);
        return getDataTable(list);
    }

    /**
     * 导出宠物健康列表
     */
    @PreAuthorize("@ss.hasPermi('system:adm_petstate:export')")
    @Log(title = "宠物健康", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    public void export(HttpServletResponse response, AdmPsbsPetstate admPsbsPetstate)
    {
        List<AdmPsbsPetstate> list = admPsbsPetstateService.selectAdmPsbsPetstateList(admPsbsPetstate);
        ExcelUtil<AdmPsbsPetstate> util = new ExcelUtil<AdmPsbsPetstate>(AdmPsbsPetstate.class);
        util.exportExcel(response, list, "宠物健康数据");
    }

    /**
     * 获取宠物健康详细信息
     */
    @PreAuthorize("@ss.hasPermi('system:adm_petstate:query')")
    @GetMapping(value = "/{pstid}")
    public AjaxResult getInfo(@PathVariable("pstid") Long pstid)
    {
        return success(admPsbsPetstateService.selectAdmPsbsPetstateByPstid(pstid));
    }

    /**
     * 新增宠物健康
     */
    @PreAuthorize("@ss.hasPermi('system:adm_petstate:add')")
    @Log(title = "宠物健康", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody AdmPsbsPetstate admPsbsPetstate)
    {
        return toAjax(admPsbsPetstateService.insertAdmPsbsPetstate(admPsbsPetstate));
    }

    /**
     * 修改宠物健康
     */
    @PreAuthorize("@ss.hasPermi('system:adm_petstate:edit')")
    @Log(title = "宠物健康", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody AdmPsbsPetstate admPsbsPetstate)
    {
        return toAjax(admPsbsPetstateService.updateAdmPsbsPetstate(admPsbsPetstate));
    }

    /**
     * 删除宠物健康
     */
    @PreAuthorize("@ss.hasPermi('system:adm_petstate:remove')")
    @Log(title = "宠物健康", businessType = BusinessType.DELETE)
	@DeleteMapping("/{pstids}")
    public AjaxResult remove(@PathVariable Long[] pstids)
    {
        return toAjax(admPsbsPetstateService.deleteAdmPsbsPetstateByPstids(pstids));
    }
}
