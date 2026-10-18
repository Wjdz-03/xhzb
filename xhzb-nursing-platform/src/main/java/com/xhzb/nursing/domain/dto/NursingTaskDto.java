package com.xhzb.nursing.domain.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDateTime;

@Data
@Schema(description = "护理任务DTO")
public class NursingTaskDto {

    @Schema(title = "页码")
    private Integer pageNum;

    @Schema(title = "每页显示条数")
    private Integer pageSize;

    @Schema(title = "老人姓名")
    private String elderName;

    @Schema(title = "护理员ID")
    private Long nurseId;

    @Schema(title = "护理项目ID")
    private Long projectId;

    @Schema(title = "开始时间")
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime startTime;

    @Schema(title = "结束时间")
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime endTime;

    @Schema(title = "状态 (1待执行 2已执行 3已关闭)")
    private Integer status;
}
