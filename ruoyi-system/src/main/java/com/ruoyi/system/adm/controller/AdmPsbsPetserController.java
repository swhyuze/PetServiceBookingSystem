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
import com.ruoyi.system.adm.domain.AdmPsbsPetser;
import com.ruoyi.system.adm.service.IAdmPsbsPetserService;
import com.ruoyi.common.utils.poi.ExcelUtil;
import com.ruoyi.common.core.page.TableDataInfo;

/**
 * 宠物服务Controller
 * 
 * @author 御泽
 * @date 2024-05-06
 */
@RestController
@RequestMapping("/system/adm_petser")
public class AdmPsbsPetserController extends BaseController
{
    @Autowired
    private IAdmPsbsPetserService admPsbsPetserService;

    /**
     * 查询宠物服务列表
     */
    @PreAuthorize("@ss.hasPermi('system:adm_petser:list')")
    @GetMapping("/list")
    public TableDataInfo list(AdmPsbsPetser admPsbsPetser)
    {
        startPage();
        List<AdmPsbsPetser> list = admPsbsPetserService.selectAdmPsbsPetserList(admPsbsPetser);
        return getDataTable(list);
    }

    /**
     * 导出宠物服务列表
     */
    @PreAuthorize("@ss.hasPermi('system:adm_petser:export')")
    @Log(title = "宠物服务", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    public void export(HttpServletResponse response, AdmPsbsPetser admPsbsPetser)
    {
        List<AdmPsbsPetser> list = admPsbsPetserService.selectAdmPsbsPetserList(admPsbsPetser);
        ExcelUtil<AdmPsbsPetser> util = new ExcelUtil<AdmPsbsPetser>(AdmPsbsPetser.class);
        util.exportExcel(response, list, "宠物服务数据");
    }

    /**
     * 获取宠物服务详细信息
     */
    @PreAuthorize("@ss.hasPermi('system:adm_petser:query')")
    @GetMapping(value = "/{psid}")
    public AjaxResult getInfo(@PathVariable("psid") Long psid)
    {
        return success(admPsbsPetserService.selectAdmPsbsPetserByPsid(psid));
    }

    /**
     * 新增宠物服务
     */
    @PreAuthorize("@ss.hasPermi('system:adm_petser:add')")
    @Log(title = "宠物服务", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody AdmPsbsPetser admPsbsPetser)
    {
        return toAjax(admPsbsPetserService.insertAdmPsbsPetser(admPsbsPetser));
    }

    /**
     * 修改宠物服务
     */
    @PreAuthorize("@ss.hasPermi('system:adm_petser:edit')")
    @Log(title = "宠物服务", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody AdmPsbsPetser admPsbsPetser)
    {
        return toAjax(admPsbsPetserService.updateAdmPsbsPetser(admPsbsPetser));
    }

    /**
     * 删除宠物服务
     */
    @PreAuthorize("@ss.hasPermi('system:adm_petser:remove')")
    @Log(title = "宠物服务", businessType = BusinessType.DELETE)
	@DeleteMapping("/{psids}")
    public AjaxResult remove(@PathVariable Long[] psids)
    {
        return toAjax(admPsbsPetserService.deleteAdmPsbsPetserByPsids(psids));
    }
}
