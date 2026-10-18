package com.xhzb.nursing.domain.vo;

import com.xhzb.nursing.domain.FamilyMemberElder;
import lombok.Data;

@Data
public class FamilyMemberElderVo extends FamilyMemberElder {

    /**
     * 老人姓名
     */
    private String elderName;
}
