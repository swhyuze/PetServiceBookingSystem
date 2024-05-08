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
import com.ruoyi.system.domain.AdmPsbsProblem;
import com.ruoyi.system.service.IAdmPsbsProblemService;
import com.ruoyi.common.utils.poi.ExcelUtil;
import com.ruoyi.common.core.page.TableDataInfo;

/**
 * 问题反馈Controller
 * 
 * @author 御泽
 * @date 2024-05-06
 */
@RestController
@RequestMapping("/system/adm_problem")
public class AdmPsbsProblemController extends BaseController
{
    @Autowired
    private IAdmPsbsProblemService admPsbsProblemService;

    /**
     * 查询问题反馈列表
     */
    @PreAuthorize("@ss.hasPermi('system:adm_problem:list')")
    @GetMapping("/list")
    public TableDataInfo list(AdmPsbsProblem admPsbsProblem)
    {
        startPage();
        List<AdmPsbsProblem> list = admPsbsProblemService.selectAdmPsbsProblemList(admPsbsProblem);
        return getDataTable(list);
    }

    /**
     * 导出问题反馈列表
     */
    @PreAuthorize("@ss.hasPermi('system:adm_problem:export')")
    @Log(title = "问题反馈", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    public void export(HttpServletResponse response, AdmPsbsProblem admPsbsProblem)
    {
        List<AdmPsbsProblem> list = admPsbsProblemService.selectAdmPsbsProblemList(admPsbsProblem);
        ExcelUtil<AdmPsbsProblem> util = new ExcelUtil<AdmPsbsProblem>(AdmPsbsProblem.class);
        util.exportExcel(response, list, "问题反馈数据");
    }

    /**
     * 获取问题反馈详细信息
     */
    @PreAuthorize("@ss.hasPermi('system:adm_problem:query')")
    @GetMapping(value = "/{proid}")
    public AjaxResult getInfo(@PathVariable("proid") Long proid)
    {
        return success(admPsbsProblemService.selectAdmPsbsProblemByProid(proid));
    }

    /**
     * 新增问题反馈
     */
    @PreAuthorize("@ss.hasPermi('system:adm_problem:add')")
    @Log(title = "问题反馈", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody AdmPsbsProblem admPsbsProblem)
    {
        return toAjax(admPsbsProblemService.insertAdmPsbsProblem(admPsbsProblem));
    }

    /**
     * 修改问题反馈
     */
    @PreAuthorize("@ss.hasPermi('system:adm_problem:edit')")
    @Log(title = "问题反馈", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody AdmPsbsProblem admPsbsProblem)
    {
        return toAjax(admPsbsProblemService.updateAdmPsbsProblem(admPsbsProblem));
    }

    /**
     * 删除问题反馈
     */
    @PreAuthorize("@ss.hasPermi('system:adm_problem:remove')")
    @Log(title = "问题反馈", businessType = BusinessType.DELETE)
	@DeleteMapping("/{proids}")
    public AjaxResult remove(@PathVariable Long[] proids)
    {
        return toAjax(admPsbsProblemService.deleteAdmPsbsProblemByProids(proids));
    }
}
