package com.ruoyi.system.clk.controller;

import java.util.Date;
import java.util.List;
import javax.servlet.http.HttpServletResponse;

import com.ruoyi.common.utils.SecurityUtils;
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
import com.ruoyi.system.clk.domain.ClkPsbsProblem;
import com.ruoyi.system.clk.service.IClkPsbsProblemService;
import com.ruoyi.common.utils.poi.ExcelUtil;
import com.ruoyi.common.core.page.TableDataInfo;

/**
 * 问题反馈Controller
 * 
 * @author 御泽
 * @date 2024-05-08
 */
@RestController
@RequestMapping("/system/clk_problem")
public class ClkPsbsProblemController extends BaseController
{
    @Autowired
    private IClkPsbsProblemService clkPsbsProblemService;

    /**
     * 查询问题反馈列表
     */
    @PreAuthorize("@ss.hasPermi('system:clk_problem:list')")
    @GetMapping("/list")
    public TableDataInfo list(ClkPsbsProblem clkPsbsProblem)
    {
        startPage();
        clkPsbsProblem.setUid(SecurityUtils.getUserId());
        List<ClkPsbsProblem> list = clkPsbsProblemService.selectClkPsbsProblemList(clkPsbsProblem);
        return getDataTable(list);
    }

    /**
     * 导出问题反馈列表
     */
    @PreAuthorize("@ss.hasPermi('system:clk_problem:export')")
    @Log(title = "问题反馈", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    public void export(HttpServletResponse response, ClkPsbsProblem clkPsbsProblem)
    {
        List<ClkPsbsProblem> list = clkPsbsProblemService.selectClkPsbsProblemList(clkPsbsProblem);
        ExcelUtil<ClkPsbsProblem> util = new ExcelUtil<ClkPsbsProblem>(ClkPsbsProblem.class);
        util.exportExcel(response, list, "问题反馈数据");
    }

    /**
     * 获取问题反馈详细信息
     */
    @PreAuthorize("@ss.hasPermi('system:clk_problem:query')")
    @GetMapping(value = "/{proid}")
    public AjaxResult getInfo(@PathVariable("proid") Long proid)
    {
        return success(clkPsbsProblemService.selectClkPsbsProblemByProid(proid));
    }

    /**
     * 新增问题反馈
     */
    @PreAuthorize("@ss.hasPermi('system:clk_problem:add')")
    @Log(title = "问题反馈", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody ClkPsbsProblem clkPsbsProblem)
    {
        clkPsbsProblem.setUid(SecurityUtils.getUserId());
        Date nowtime=new Date(System.currentTimeMillis());
        clkPsbsProblem.setProutime(nowtime);
        return toAjax(clkPsbsProblemService.insertClkPsbsProblem(clkPsbsProblem));
    }

    /**
     * 修改问题反馈
     */
    @PreAuthorize("@ss.hasPermi('system:clk_problem:edit')")
    @Log(title = "问题反馈", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody ClkPsbsProblem clkPsbsProblem)
    {
        return toAjax(clkPsbsProblemService.updateClkPsbsProblem(clkPsbsProblem));
    }

    /**
     * 删除问题反馈
     */
    @PreAuthorize("@ss.hasPermi('system:clk_problem:remove')")
    @Log(title = "问题反馈", businessType = BusinessType.DELETE)
	@DeleteMapping("/{proids}")
    public AjaxResult remove(@PathVariable Long[] proids)
    {
        return toAjax(clkPsbsProblemService.deleteClkPsbsProblemByProids(proids));
    }
}
