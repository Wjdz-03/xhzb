package com.xhzb.nursing.controller.member;

import com.xhzb.common.core.controller.BaseController;
import com.xhzb.common.core.domain.AjaxResult;
import com.xhzb.common.core.domain.R;
import com.xhzb.common.utils.UserThreadLocal;
import com.xhzb.nursing.domain.dto.MemberElderDto;
import com.xhzb.nursing.domain.dto.UserLoginRequestDto;
import com.xhzb.nursing.domain.vo.DeviceDataGraphVo;
import com.xhzb.nursing.domain.vo.FamilyMemberElderVo;
import com.xhzb.nursing.domain.vo.LoginVo;
import com.xhzb.nursing.domain.vo.MemberElderVo;
import com.xhzb.nursing.service.IDeviceDataService;
import com.xhzb.nursing.service.IDeviceService;
import com.xhzb.nursing.service.IFamilyMemberElderService;
import com.xhzb.nursing.service.IFamilyMemberService;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * 老人家属Controller
 * 
 * @author ruoyi
 * @date 2026-04-06
 */
@RestController
@RequestMapping("/member/user")
@Tag(name = "老人家属相关接口")
public class FamilyMemberController extends BaseController
{
    @Autowired
    private IFamilyMemberService familyMemberService;

    @PostMapping("/login")
    public AjaxResult login(@RequestBody UserLoginRequestDto dto){
        LoginVo loginVo = familyMemberService.login(dto);
        return success(loginVo);
    }

    @Autowired
    private IFamilyMemberElderService familyMemberElderService;

    /**
     * 新增客户老人关联记录
     *
     * @param memberElderDto 客户老人关联 DTO
     * @return 操作结果
     */
    @PostMapping("/add")
    public AjaxResult add(@RequestBody MemberElderDto memberElderDto) {
        return toAjax(familyMemberElderService.add(memberElderDto));
    }

    /**
     * 我的家人列表
     *
     * @return 客户老人关联实体类
     */
    @GetMapping("/my")
    public R<List<FamilyMemberElderVo>> my() {
        List<FamilyMemberElderVo> memberElders = familyMemberElderService.my();
        return R.ok(memberElders);
    }

    @GetMapping("/list-by-page")
    public R<List<MemberElderVo>> listByPage() {
        startPage();
        Long userId = UserThreadLocal.getUserId();
        List<MemberElderVo> memberElders = familyMemberElderService.listByPage(userId);
        return R.ok(memberElders);
    }

    @DeleteMapping("/deleteById")
    public AjaxResult deleteById(@RequestParam Long id) {
        return toAjax(familyMemberElderService.deleteById(id));
    }

    @Autowired
    private IDeviceService deviceService;

    @GetMapping("/queryServiceProperties/{iotId}")
    public AjaxResult queryDevicePropertyStatus(@PathVariable("iotId") String iotId){
        return deviceService.queryServiceProperties(iotId);
    }

    @Autowired
    private IDeviceDataService deviceDataService;

    @GetMapping("/queryDeviceDataListByDay")
    public AjaxResult queryDeviceDataListByDay(
            String iotId,String functionId,Long startTime,Long endTime
    ){
        List<DeviceDataGraphVo> list = deviceDataService.queryDeviceDataListByDay(iotId,functionId,startTime,endTime);
        return success(list);
    }

    @GetMapping("/queryDeviceDataListByWeek")
    public AjaxResult queryDeviceDataListByWeek(
            String iotId,String functionId,Long startTime,Long endTime
    ){
        List<DeviceDataGraphVo> list = deviceDataService.queryDeviceDataListByWeek(iotId,functionId,startTime,endTime);
        return success(list);
    }


}
