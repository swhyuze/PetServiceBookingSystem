package com.ruoyi.system.man.controller;

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
import com.ruoyi.system.man.domain.ManPsbsClkser;
import com.ruoyi.system.man.service.IManPsbsClkserService;
import com.ruoyi.common.utils.poi.ExcelUtil;
import com.ruoyi.common.core.page.TableDataInfo;

/**
 * 店员服务Controller
 * 
 * @author 御泽
 * @date 2024-05-08
 */
@RestController
@RequestMapping("/system/man_clkser")
public class ManPsbsClkserController extends BaseController
{
    @Autowired
    private IManPsbsClkserService manPsbsClkserService;

    /**
     * 查询店员服务列表
     */
    @PreAuthorize("@ss.hasPermi('system:man_clkser:list')")
    @GetMapping("/list")
    public TableDataInfo list(ManPsbsClkser manPsbsClkser)
    {
        startPage();
        List<ManPsbsClkser> list = manPsbsClkserService.selectManPsbsClkserList(manPsbsClkser);
        return getDataTable(list);
    }

    /**
     * 导出店员服务列表
     */
    @PreAuthorize("@ss.hasPermi('system:man_clkser:export')")
    @Log(title = "店员服务", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    public void export(HttpServletResponse response, ManPsbsClkser manPsbsClkser)
    {
        List<ManPsbsClkser> list = manPsbsClkserService.selectManPsbsClkserList(manPsbsClkser);
        ExcelUtil<ManPsbsClkser> util = new ExcelUtil<ManPsbsClkser>(ManPsbsClkser.class);
        util.exportExcel(response, list, "店员服务数据");
    }

    /**
     * 获取店员服务详细信息
     */
    @PreAuthorize("@ss.hasPermi('system:man_clkser:query')")
    @GetMapping(value = "/{clser}")
    public AjaxResult getInfo(@PathVariable("clser") Long clser)
    {
        return success(manPsbsClkserService.selectManPsbsClkserByClser(clser));
    }

    /**
     * 新增店员服务
     */
    @PreAuthorize("@ss.hasPermi('system:man_clkser:add')")
    @Log(title = "店员服务", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody ManPsbsClkser manPsbsClkser)
    {
        return toAjax(manPsbsClkserService.insertManPsbsClkser(manPsbsClkser));
    }

    /**
     * 修改店员服务
     */
    @PreAuthorize("@ss.hasPermi('system:man_clkser:edit')")
    @Log(title = "店员服务", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody ManPsbsClkser manPsbsClkser)
    {
        return toAjax(manPsbsClkserService.updateManPsbsClkser(manPsbsClkser));
    }

    /**
     * 删除店员服务
     */
    @PreAuthorize("@ss.hasPermi('system:man_clkser:remove')")
    @Log(title = "店员服务", businessType = BusinessType.DELETE)
	@DeleteMapping("/{clsers}")
    public AjaxResult remove(@PathVariable Long[] clsers)
    {
        return toAjax(manPsbsClkserService.deleteManPsbsClkserByClsers(clsers));
    }
}
