package com.xhzb.nursing.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.xhzb.common.core.page.TableDataInfo;
import com.xhzb.nursing.domain.Elder;
import com.xhzb.nursing.domain.NursingTask;
import com.xhzb.nursing.domain.dto.NursingTaskDto;
import com.xhzb.nursing.domain.dto.TaskDto;
import com.xhzb.nursing.domain.vo.NursingTaskVo;

/**
 * 护理任务Service接口
 *
 * @author ruoyi
 * @date 2024-09-27
 */
public interface INursingTaskService extends IService<NursingTask>
{

    /**
     * 查询护理任务
     *
     * @param id 护理任务主键
     * @return 护理任务
     */
    public NursingTaskVo selectNursingTaskById(Long id);

    /**
     * 查询护理任务列表
     *
     * @param nursingTaskDto 护理任务
     * @return 护理任务集合
     */
    public TableDataInfo selectNursingTaskList(NursingTaskDto nursingTaskDto);

    /**
     * 生成护理任务
     * @param elder
     */
    void createMonthTask(Elder elder);

    /**
     * 取消任务
     * @param taskDto
     * @return
     */
    int cancelTask(TaskDto taskDto);

    /**
     * 任务改期
     * @param taskDto
     * @return
     */
    int rescheduleTask(TaskDto taskDto);

    /**
     * 执行任务
     * @param taskDto
     * @return
     */
    int executeTask(TaskDto taskDto);


}
