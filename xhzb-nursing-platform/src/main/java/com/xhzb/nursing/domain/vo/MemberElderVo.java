package com.xhzb.nursing.domain.vo;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

/**
 * 客户老人关联实体类
 */
@Data
@Schema(description = "客户老人关联实体类")
public class MemberElderVo {

    @Schema(title = "客户id")
    private String mid;

    @Schema(title = "客户对老人的称谓")
    private String mremark;

    @Schema(title = "老人ID")
    private String elderId;

    @Schema(title = "老人姓名")
    private String name;

    @Schema(title = "老人头像地址")
    private String image;

    @Schema(title = "床位编号")
    private String bedNumber;

    @Schema(title = "房间名称")
    private String typeName;

    @Schema(title = "设备ID")
    private String iotId;

    @Schema(title = "设备名称")
    private String deviceName;

    @Schema(title = "产品Key")
    private String productKey;

}


