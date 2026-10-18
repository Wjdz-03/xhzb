package com.xhzb.nursing.domain.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import java.time.LocalDateTime;

@Data
@Schema(description = "护理任务DTO")
public class TaskDto {

    @Schema(title = "护理任务ID",required = true)
    private Long taskId;

    @Schema(title = "取消理由",required = true)
    private String reason;

    @Schema(title = "执行时间",required = true)
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss",timezone = "GMT+8")
    private LocalDateTime estimatedServerTime;

    @Schema(title = "任务图片",required = true)
    private String taskImage;

    @Schema(title = "执行记录",required = true)
    private String mark;



}
