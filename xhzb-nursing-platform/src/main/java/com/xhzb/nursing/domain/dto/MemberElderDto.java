package com.xhzb.nursing.domain.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

/**
 * 客户老人关联实体类
 */
@Data
@Schema(description = "客户老人关联实体类")
public class MemberElderDto {

    /**
     * 姓名
     */
    @Schema(title = "姓名")
    private String name;

    /**
     * 身份证号
     */
    @Schema(title = "身份证号")
    private String idCard;

    @Schema(title = "备注")
    private String remark;

}


