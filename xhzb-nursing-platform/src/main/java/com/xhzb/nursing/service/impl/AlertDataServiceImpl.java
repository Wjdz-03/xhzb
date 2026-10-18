package com.xhzb.nursing.service.impl;

import java.time.LocalDateTime;
import java.util.List;

import cn.hutool.core.util.ObjectUtil;
import com.baomidou.mybatisplus.core.conditions.update.LambdaUpdateWrapper;
import com.xhzb.common.core.domain.model.LoginUser;
import com.xhzb.common.exception.base.BaseException;
import com.xhzb.common.utils.DateUtils;
import com.xhzb.common.utils.SecurityUtils;
import com.xhzb.nursing.domain.dto.AlertDataHandleDto;
import com.xhzb.nursing.util.DateTimeZoneConverter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.xhzb.nursing.mapper.AlertDataMapper;
import com.xhzb.nursing.domain.AlertData;
import com.xhzb.nursing.service.IAlertDataService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import java.util.Arrays;

/**
 * 报警数据Service业务层处理
 * 
 * @author ruoyi
 * @date 2026-04-10
 */
@Service
public class AlertDataServiceImpl extends ServiceImpl<AlertDataMapper, AlertData> implements IAlertDataService
{
    @Autowired
    private AlertDataMapper alertDataMapper;

    /**
     * 处理设备报警数据
     * @param alertDataHandleDto
     */
    @Override
    public boolean handleAlertData(AlertDataHandleDto alertDataHandleDto) {
        //查询报警数据
        AlertData alertData = getById(alertDataHandleDto.getId());
        if (ObjectUtil.isEmpty(alertData) || alertData.getStatus().equals(1)) {
            throw new BaseException("该报警数据已被处理");
        }

        //从当前线程中获取用户数据，更新报警数据
        LoginUser loginUser = SecurityUtils.getLoginUser();
        //获取当前登录人ID
        Long userId = loginUser.getUserId();
        // 获取当前登录人名称
        String username = loginUser.getUsername();

        //日期时区转换
        LocalDateTime processingTime = DateTimeZoneConverter.utcToShanghai(alertDataHandleDto.getProcessingTime());

        //更新报警数据
        LambdaUpdateWrapper<AlertData> alertDataWrapper = new LambdaUpdateWrapper();
        alertDataWrapper.set(AlertData::getProcessingResult, alertDataHandleDto.getProcessingResult())
                .set(AlertData::getProcessingTime, processingTime)
                .set(AlertData::getProcessorName, username)
                .set(AlertData::getUpdateBy, userId)
                .set(AlertData::getStatus, 1)
                .eq(AlertData::getIotId, alertData.getIotId())
                .eq(AlertData::getFunctionId, alertData.getFunctionId())
                .eq(AlertData::getAlertRuleId, alertData.getAlertRuleId());
        return update(alertDataWrapper);
    }

    /**
     * 查询报警数据
     * 
     * @param id 报警数据主键
     * @return 报警数据
     */
    @Override
    public AlertData selectAlertDataById(Long id)
    {
        return getById(id);
    }

    /**
     * 查询报警数据列表
     * 
     * @param alertData 报警数据
     * @return 报警数据
     */
    @Override
    public List<AlertData> selectAlertDataList(AlertData alertData)
    {
        alertData.setUserId(SecurityUtils.getUserId());
        return alertDataMapper.selectAlertDataList(alertData);
    }

    /**
     * 新增报警数据
     * 
     * @param alertData 报警数据
     * @return 结果
     */
    @Override
    public int insertAlertData(AlertData alertData)
    {
        return save(alertData)? 1 : 0;
    }

    /**
     * 修改报警数据
     * 
     * @param alertData 报警数据
     * @return 结果
     */
    @Override
    public int updateAlertData(AlertData alertData)
    {
        return updateById(alertData)? 1 : 0;
    }

    /**
     * 批量删除报警数据
     * 
     * @param ids 需要删除的报警数据主键
     * @return 结果
     */
    @Override
    public int deleteAlertDataByIds(Long[] ids)
    {
        return removeByIds(Arrays.asList(ids))? 1 : 0;
    }

    /**
     * 删除报警数据信息
     * 
     * @param id 报警数据主键
     * @return 结果
     */
    @Override
    public int deleteAlertDataById(Long id)
    {
        return removeById(id)? 1 : 0;
    }
}
