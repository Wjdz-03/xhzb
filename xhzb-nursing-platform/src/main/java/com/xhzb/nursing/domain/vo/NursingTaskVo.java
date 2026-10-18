package com.xhzb.nursing.domain.vo;

import com.xhzb.nursing.domain.NursingTask;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import java.util.List;

@Data
@Schema(description = "护理任务VO")
public class NursingTaskVo extends NursingTask {

    /**
     * 护理等级名称
     */
    @Schema(title = "护理等级名称")
    private String nursingLevelName;
    /**
     * 年龄
     */
    @Schema(title = "年龄")
    private Integer age;
    /**
     * 护理名称
     */
    @Schema(title = "护理名称")
    private List<String> nursingName;

    @Schema(title = "性别")
    private String sex;
    @Schema(title = "执行人")
    private String updater;

}
