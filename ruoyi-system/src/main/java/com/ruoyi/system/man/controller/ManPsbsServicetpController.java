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
import com.ruoyi.system.man.domain.ManPsbsServicetp;
import com.ruoyi.system.man.service.IManPsbsServicetpService;
import com.ruoyi.common.utils.poi.ExcelUtil;
import com.ruoyi.common.core.page.TableDataInfo;

/**
 * 服务种类Controller
 * 
 * @author ruoyi
 * @date 2024-05-08
 */
@RestController
@RequestMapping("/system/man_servicetp")
public class ManPsbsServicetpController extends BaseController
{
    @Autowired
    private IManPsbsServicetpService manPsbsServicetpService;

    /**
     * 查询服务种类列表
     */
    @PreAuthorize("@ss.hasPermi('system:man_servicetp:list')")
    @GetMapping("/list")
    public TableDataInfo list(ManPsbsServicetp manPsbsServicetp)
    {
        startPage();
        List<ManPsbsServicetp> list = manPsbsServicetpService.selectManPsbsServicetpList(manPsbsServicetp);
        return getDataTable(list);
    }

    /**
     * 导出服务种类列表
     */
    @PreAuthorize("@ss.hasPermi('system:man_servicetp:export')")
    @Log(title = "服务种类", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    public void export(HttpServletResponse response, ManPsbsServicetp manPsbsServicetp)
    {
        List<ManPsbsServicetp> list = manPsbsServicetpService.selectManPsbsServicetpList(manPsbsServicetp);
        ExcelUtil<ManPsbsServicetp> util = new ExcelUtil<ManPsbsServicetp>(ManPsbsServicetp.class);
        util.exportExcel(response, list, "服务种类数据");
    }

    /**
     * 获取服务种类详细信息
     */
    @PreAuthorize("@ss.hasPermi('system:man_servicetp:query')")
    @GetMapping(value = "/{stid}")
    public AjaxResult getInfo(@PathVariable("stid") Long stid)
    {
        return success(manPsbsServicetpService.selectManPsbsServicetpByStid(stid));
    }

    /**
     * 新增服务种类
     */
    @PreAuthorize("@ss.hasPermi('system:man_servicetp:add')")
    @Log(title = "服务种类", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody ManPsbsServicetp manPsbsServicetp)
    {
        return toAjax(manPsbsServicetpService.insertManPsbsServicetp(manPsbsServicetp));
    }

    /**
     * 修改服务种类
     */
    @PreAuthorize("@ss.hasPermi('system:man_servicetp:edit')")
    @Log(title = "服务种类", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody ManPsbsServicetp manPsbsServicetp)
    {
        return toAjax(manPsbsServicetpService.updateManPsbsServicetp(manPsbsServicetp));
    }

    /**
     * 删除服务种类
     */
    @PreAuthorize("@ss.hasPermi('system:man_servicetp:remove')")
    @Log(title = "服务种类", businessType = BusinessType.DELETE)
	@DeleteMapping("/{stids}")
    public AjaxResult remove(@PathVariable Long[] stids)
    {
        return toAjax(manPsbsServicetpService.deleteManPsbsServicetpByStids(stids));
    }
}
