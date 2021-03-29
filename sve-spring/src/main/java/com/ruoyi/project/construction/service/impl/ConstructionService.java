package com.ruoyi.project.construction.service.impl;

import com.ruoyi.common.domain.ResultEntity;
import com.ruoyi.common.utils.HashMapUtils;
import com.ruoyi.common.utils.SecurityUtils;
import com.ruoyi.common.utils.StringUtils;
import com.ruoyi.framework.aspectj.lang.annotation.DataSource;
import com.ruoyi.framework.aspectj.lang.enums.DataSourceType;
import com.ruoyi.project.construction.domain.*;
import com.ruoyi.project.construction.mapper.ConstructionMapper;
import com.ruoyi.project.construction.service.IConstructionElementService;
import com.ruoyi.project.construction.service.IConstructionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;
import java.util.Optional;

import static com.ruoyi.project.common.domain.TaskPrefix.getTypePrefix;

@Service
@DataSource(value = DataSourceType.MASTER)
public class ConstructionService implements IConstructionService {
    @Autowired
    private ConstructionMapper mapper;

    @Autowired
    private IConstructionElementService elementService;

    @Override
    public List<ConstructionResult> getConstructionList(ConstructionParam param) {
        return mapper.getConstructionList(param);
    }

    @Override
    public ResultEntity getConstructionModel(int tagHeadId, String sourceId) {
        if (!StringUtils.isEmpty(SecurityUtils.getSourceId()))
            sourceId = SecurityUtils.getSourceId();
        if (sourceId == null) {
            return ResultEntity.error("无效的企业账号");
        }
        ConstructionEntity entity = mapper.getConstructionModel(String.valueOf(tagHeadId), sourceId);
        if (entity != null) {
            return ResultEntity.success(entity);
        }
        return ResultEntity.error("施工登记信息不存在");
    }

    @Override
    public List<Map> getAcceptReturnList(String tagHeadId, String sourceId) {
        return HashMapUtils.toLower(mapper.getAcceptReturnList(String.valueOf(tagHeadId), sourceId));
    }

    @Override
    public List<Map> getAttachCountList(String sourceId, String refType) {
        return HashMapUtils.toLower(mapper.getAttachCountList(sourceId, refType));
    }

    @Override
    public ResultEntity mergeConstruction(ConstructionEntity entity) {
        String sourceId = SecurityUtils.getSourceId();
        if (sourceId == null) {
            return ResultEntity.error("无效的企业账号");
        }
        entity.setSourceId(sourceId);
        if (StringUtils.isEmpty(entity.getShortName()) || StringUtils.isEmpty(entity.getConstructionUnit()))
            return ResultEntity.error("必填项不能为空");
        int res = mapper.mergeConstruction(entity);
        if (res > 0) {
            return ResultEntity.success("施工登记修改成功");
        } else {
            return ResultEntity.error("施工登记修改失败");
        }
    }

    @Override
    public ResultEntity completeConstruction(ConstructionEntity entity) {
        String sourceId = SecurityUtils.getSourceId();
        if (sourceId == null) {
            return ResultEntity.error("无效的企业账号");
        }
        entity.setSourceId(sourceId);
        ConstructionEntity model = mapper.getConstructionModel(String.valueOf(entity.getTagHeadId()), sourceId);
        int status = model.getConstructionStatus();
        if (status >= 2 && status < 4) {
            ResultEntity result = checkElement(entity.getTagHeadId(), entity.getSourceId());
            if (result != null) {
                return result;
            }
            int res = mapper.completeConstruction(entity);
            if (res > 0) {
                return ResultEntity.success("联网完成登记成功");
            } else {
                return ResultEntity.error("联网完成登记失败");
            }
        } else {
            return ResultEntity.error("施工状态为已联网时才可进行完成登记");
        }
    }

    @Override
    public ResultEntity acceptanceConstruction(ConstructionEntity entity) {
        String sourceId = SecurityUtils.getSourceId();
        if (sourceId == null) {
            return ResultEntity.error("无效的企业账号");
        }
        entity.setSourceId(sourceId);
        ConstructionEntity model = mapper.getConstructionModel(String.valueOf(entity.getTagHeadId()), sourceId);
        int status = model.getConstructionStatus();
        if (status != 4) {
            return ResultEntity.error("待验收后才可进行验收登记");
        }
        ResultEntity result = checkElement(entity.getTagHeadId(), entity.getSourceId());
        if (result != null) {
            return result;
        }
        List<Map> attaches = getAttachCountList(sourceId, getTypePrefix(entity.getTagHeadId()) + "_sysfile_type");
        if (attaches.stream().anyMatch(m -> Integer.parseInt(m.get("attach_cnt").toString()) == 0)) {
            return ResultEntity.error("请上传所有附件后提交");
        }
        int res = mapper.acceptanceConstruction(entity);
        if (res > 0) {
            return ResultEntity.success("自主验收登记成功");
        } else {
            return ResultEntity.error("自主验收登记失败");
        }
    }

    @Override
    public ResultEntity confirmConstruction(AcceptParam param, AcceptRecordEntity entity) {
        ConstructionEntity model = mapper.getConstructionModel(String.valueOf(param.getTagHeadId()), param.getSourceId());
        int status = model.getConstructionStatus();
        if (status < 4) {
            return ResultEntity.error("待验收后才可进行验收确认");
        }
        param.setConstructionStatus(6);
        param.setAcceptResult(1);
        int res = mapper.acceptConstruction(param);
        if (res > 0) {
            entity.setConstructionStatus(status);
            entity.setReturnResult(1);
            mapper.addAcceptRecord(entity);
            return ResultEntity.success("施工登记验收确认成功");
        } else {
            return ResultEntity.error("施工登记验收确认失败");
        }
    }

    @Override
    public ResultEntity retreatConstruction(AcceptParam param, AcceptRecordEntity entity) {
        ConstructionEntity model = mapper.getConstructionModel(String.valueOf(param.getTagHeadId()), param.getSourceId());
        int status = model.getConstructionStatus();
        if (status < 6) {
            return ResultEntity.error("企业未验收,不允许操作");
        }
        param.setConstructionStatus(4);
        param.setAcceptResult(0);
        int res = mapper.acceptConstruction(param);
        if (res > 0) {
            entity.setConstructionStatus(status);
            entity.setReturnResult(0);
            mapper.addAcceptRecord(entity);
            return ResultEntity.success(entity.getReturnId(), "施工登记退回成功");
        } else {
            return ResultEntity.error("施工登记退回失败");
        }
    }

    @Override
    public boolean hasContract(int tagHeadId) {
        String sourceId = SecurityUtils.getSourceId();
        if (sourceId == null) {
            ResultEntity.error("无效的企业账号");
        }
        List<Map> attaches = getAttachCountList(sourceId, getTypePrefix(tagHeadId) + "_sysfile_type");
        Optional<Map> optional = attaches.stream().filter(p -> p.get("dict_value").toString().equals(getTypePrefix(tagHeadId) + "_sbcght")).findFirst();
        if (optional.isPresent()) {
            Map map = optional.get();
            return Integer.parseInt(map.get("attach_cnt").toString()) > 0;
        }
        return false;
    }

    private ResultEntity checkElement(int tagHeadId, String sourceId) {
        Map map;
        switch (tagHeadId) {
//            case 1002:
//                map = HashMapUtils.toLower(elementService.getNoDataElementForGuoCheng(String.valueOf(tagHeadId), sourceId));
//                if (Integer.parseInt(map.get("cnt").toString()) > 0) {
//                    return ResultEntity.error(StringUtils.format("因子类型{}未联网", map.get("ele_type_ids")));
//                }
//                break;
            case 1003:
                map = HashMapUtils.toLower(elementService.getNoFacilityElementForLingXing(String.valueOf(tagHeadId), sourceId));
                if (Integer.parseInt(map.get("cnt").toString()) > 0) {
                    return ResultEntity.error(StringUtils.format("因子类型{}未关联设施", map.get("ele_type_ids")));
                }
                map = HashMapUtils.toLower(elementService.getNoDataElementForLingXing(String.valueOf(tagHeadId), sourceId));
                if (Integer.parseInt(map.get("cnt").toString()) > 0) {
                    return ResultEntity.error(StringUtils.format("因子类型{}未联网", map.get("ele_type_ids")));
                }
                break;
            case 1004:
                map = HashMapUtils.toLower(elementService.getNoFacilityElementForVOC(String.valueOf(tagHeadId), sourceId));
                if (Integer.parseInt(map.get("cnt").toString()) > 0) {
                    return ResultEntity.error(StringUtils.format("因子类型{}未关联设施", map.get("ele_type_ids")));
                }
                map = HashMapUtils.toLower(elementService.getNoDataElementForVOC(String.valueOf(tagHeadId), sourceId));
                if (Integer.parseInt(map.get("cnt").toString()) > 0) {
                    return ResultEntity.error(StringUtils.format("因子类型{}未联网", map.get("ele_type_ids")));
                }
                break;
            case 1006:
                map = HashMapUtils.toLower(elementService.getNoFacilityElementForYouYan(String.valueOf(tagHeadId), sourceId));
                if (Integer.parseInt(map.get("cnt").toString()) > 0) {
                    return ResultEntity.error(StringUtils.format("因子类型{}未关联设施", map.get("ele_type_ids")));
                }
                map = HashMapUtils.toLower(elementService.getNoDataElementForYouYan(String.valueOf(tagHeadId), sourceId));
                if (Integer.parseInt(map.get("cnt").toString()) > 0) {
                    return ResultEntity.error(StringUtils.format("因子类型{}未联网", map.get("ele_type_ids")));
                }
                break;
            case 1012:
                // 可能需要根据监测井验证
                map = HashMapUtils.toLower(elementService.getHasDataElementForPaiShuiHu(String.valueOf(tagHeadId), sourceId));
                if (Integer.parseInt(map.get("cnt").toString()) < 2) {
                    return ResultEntity.error(StringUtils.format("请保证至少有一组(污水/雨水)井联网"));
                }
                break;
            case 1013:
                map = HashMapUtils.toLower(elementService.getNoFacilityElementForQiXiu(String.valueOf(tagHeadId), sourceId));
                if (Integer.parseInt(map.get("cnt").toString()) > 0) {
                    return ResultEntity.error(StringUtils.format("因子类型{}未关联设施", map.get("ele_type_ids")));
                }
                map = HashMapUtils.toLower(elementService.getNoDataElementForQiXiu(String.valueOf(tagHeadId), sourceId));
                if (Integer.parseInt(map.get("cnt").toString()) > 0) {
                    return ResultEntity.error(StringUtils.format("因子类型{}未联网", map.get("ele_type_ids")));
                }
                break;
//            case 1014:
//                map = HashMapUtils.toLower(elementService.getNoDataElementForJiaYou(String.valueOf(tagHeadId), sourceId));
//                if (Integer.parseInt(map.get("cnt").toString()) > 0) {
//                    return ResultEntity.error(StringUtils.format("因子类型{}未联网", map.get("ele_type_ids")));
//                }
//                break;
//            case 1015:
//                map = HashMapUtils.toLower(elementService.getNoDataElementForJinShu(String.valueOf(tagHeadId), sourceId));
//                if (Integer.parseInt(map.get("cnt").toString()) > 0) {
//                    return ResultEntity.error(StringUtils.format("因子类型{}未联网", map.get("ele_type_ids")));
//                }
//                break;
            case 1019:
                // 判断因子是否在线
                map = HashMapUtils.toLower(elementService.getNoDataElementForZaoZhi(String.valueOf(tagHeadId), sourceId));
                if (Integer.parseInt(map.get("cnt").toString()) > 0) {
                    return ResultEntity.error(StringUtils.format("因子类型{}未联网", map.get("ele_type_ids")));
                }
                break;
        }
        return null;
    }
}
