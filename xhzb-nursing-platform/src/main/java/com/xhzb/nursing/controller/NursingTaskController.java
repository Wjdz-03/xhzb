package com.xhzb.nursing.controller;

import com.xhzb.common.annotation.Log;
import com.xhzb.common.core.controller.BaseController;
import com.xhzb.common.core.domain.AjaxResult;
import com.xhzb.common.core.domain.R;
import com.xhzb.common.core.page.TableDataInfo;
import com.xhzb.common.enums.BusinessType;
import com.xhzb.nursing.domain.NursingTask;
import com.xhzb.nursing.domain.dto.NursingTaskDto;
import com.xhzb.nursing.domain.dto.TaskDto;
import com.xhzb.nursing.domain.vo.NursingTaskVo;
import com.xhzb.nursing.service.INursingTaskService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

/**
 * 护理任务Controller
 *
 * @author ruoyi
 */
@RestController
@RequestMapping("/nursing/nursingTask")
public class NursingTaskController extends BaseController
{
    @Autowired
    private INursingTaskService nursingTaskService;

    /**
     * 查询护理任务列表
     */
    @GetMapping("/list")
    public TableDataInfo list(NursingTaskDto nursingTaskDto)
    {
        TableDataInfo tableDataInfo = nursingTaskService.selectNursingTaskList(nursingTaskDto);
        return tableDataInfo;
    }

    /**
     * 获取护理任务详细信息
     */
    @GetMapping(value = "/{id}")
    public R<NursingTaskVo> getInfo(@PathVariable("id") Long id)
    {
        NursingTaskVo nursingTaskVo = nursingTaskService.selectNursingTaskById(id);
        return R.ok(nursingTaskVo);
    }

    @PutMapping("/cancel")
    public AjaxResult cancelTask(@RequestBody TaskDto taskDto) {
        return toAjax(nursingTaskService.cancelTask(taskDto));
    }

    @PutMapping("/updateTime")
    public AjaxResult rescheduleTask(@RequestBody TaskDto taskDto) {
        return toAjax(nursingTaskService.rescheduleTask(taskDto));
    }

    @PutMapping("/do")
    public AjaxResult executeTask(@RequestBody TaskDto taskDto) {
        return toAjax(nursingTaskService.executeTask(taskDto));
    }

}
