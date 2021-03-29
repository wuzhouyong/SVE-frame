package com.ruoyi.project.system.service.impl;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
import java.util.stream.Collectors;

import com.ruoyi.common.constant.UserConstants;
import com.ruoyi.common.exception.CustomException;
import com.ruoyi.common.utils.StringUtils;
import com.ruoyi.framework.aspectj.lang.annotation.DataScope;
import com.ruoyi.framework.aspectj.lang.annotation.DataSource;
import com.ruoyi.framework.aspectj.lang.enums.DataSourceType;
import com.ruoyi.framework.web.domain.TreeSelect;
import com.ruoyi.project.system.domain.SysDept;
import com.ruoyi.project.system.domain.SysUser;
import com.ruoyi.project.system.domain.ext.SysDeptRegion;
import com.ruoyi.project.system.domain.ext.SysDeptUser;
import com.ruoyi.project.system.mapper.SysDeptMapper;
import com.ruoyi.project.system.service.ISysConfigService;
import com.ruoyi.project.system.service.ISysDeptService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * 部门管理 服务实现
 *
 * @author ruoyi
 */
@Service
@DataSource(value = DataSourceType.MASTER)
public class SysDeptServiceImpl implements ISysDeptService {
    @Autowired
    private SysDeptMapper deptMapper;

    @Autowired
    private ISysConfigService configService;

    /**
     * 查询部门管理数据
     *
     * @param dept 部门信息
     * @return 部门信息集合
     */
    @Override
    @DataScope(deptAlias = "d")
    public List<SysDept> selectDeptList(SysDept dept) {
        return deptMapper.selectDeptList(dept);
    }

    /**
     * 构建前端所需要树结构
     *
     * @param depts 部门列表
     * @return 树结构列表
     */
    @Override
    public List<SysDept> buildDeptTree(List<SysDept> depts) {
        List<SysDept> returnList = new ArrayList<SysDept>();
        List<Long> tempList = new ArrayList<Long>();
        for (SysDept dept : depts) {
            tempList.add(dept.getDeptId());
        }
        for (Iterator<SysDept> iterator = depts.iterator(); iterator.hasNext(); ) {
            SysDept dept = (SysDept) iterator.next();
            // 如果是顶级节点, 遍历该父节点的所有子节点
            if (!tempList.contains(dept.getParentId())) {
                recursionFn(depts, dept);
                returnList.add(dept);
            }
        }
        if (returnList.isEmpty()) {
            returnList = depts;
        }
        return returnList;
    }


    /**
     * 构建前端所需要下拉树结构
     *
     * @param depts 部门列表
     * @return 下拉树结构列表
     */
    @Override
    public List<TreeSelect> buildDeptTreeSelect(List<SysDept> depts) {
        List<SysDept> deptTrees = buildDeptTree(depts);
        return deptTrees.stream().map(TreeSelect::new).collect(Collectors.toList());
    }

    /**
     * 构建前端所需要下拉树用户结构树
     *
     * @param depts 部门列表
     * @param users 用户列表
     * @return 下拉树结构列表
     */
    public List<TreeSelect> buildDeptUserTreeSelect(List<SysDept> depts, List<SysUser> users) {
        List<SysDept> deptTrees = buildDeptTree(depts);
        List<TreeSelect> res = deptTrees.stream().map(TreeSelect::new).collect(Collectors.toList());
        buildDeptUserTree(res, users);
        return res;
    }

    public void buildDeptUserTree(List<TreeSelect> deptUserTree, List<SysUser> users) {
        for (TreeSelect d : deptUserTree) {
            d.setType("2");
            if (!d.getChildren().isEmpty()) {
                buildDeptUserTree(d.getChildren(), users);
            }

            Long deptId = d.getId();
            List<TreeSelect> child = new ArrayList<>();
            for (SysUser us : users) {
                if (us.getDeptId().equals(deptId)) {
                    TreeSelect user = new TreeSelect();
                    user.setId(us.getUserId());
                    user.setLabel(us.getNickName());
                    user.setType("1");
                    child.add(user);
                }
            }
            if (!child.isEmpty()) {
                d.setAddChildren(child);
            }
        }
    }

    /**
     * 生成部门列表，并计算部门下所有子部门
     */
    public List<SysDept> setAllDeptChild() {
        List<SysDept> depts = buildDeptTree(deptMapper.selectDeptList(new SysDept()));
        getDeptAllChild(depts);
        return depts;
    }

    public String getDeptAllChild(List<SysDept> depts) {
        String keys = "";
        for (SysDept dp : depts) {
            if (!dp.getChildren().isEmpty()) {
                String oldkeys = dp.getChildDept();
                String newkeys = getDeptAllChild(dp.getChildren());
                if (newkeys.indexOf(",") == 0)
                    newkeys = newkeys.substring(1, newkeys.length());
                if ( oldkeys == null || !oldkeys.equals(newkeys)) {
                    SysDept newdp = new SysDept();
                    newdp.setDeptId(dp.getDeptId());
                    newdp.setChildDept(newkeys);
                    deptMapper.updateDept(newdp);
                }
                dp.setChildDept(newkeys);
                keys += dp.getChildDept();
            }
            keys += "," + dp.getDeptId().toString();

        }
        return keys;
    }

    /**
     * 根据角色ID查询部门树信息
     *
     * @param roleId 角色ID
     * @return 选中部门列表
     */
    @Override
    public List<Integer> selectDeptListByRoleId(Long roleId) {
        return deptMapper.selectDeptListByRoleId(roleId);
    }

    /**
     * 根据部门ID查询信息
     *
     * @param deptId 部门ID
     * @return 部门信息
     */
    @Override
    public SysDept selectDeptById(Long deptId) {
        SysDept dept = deptMapper.selectDeptById(deptId);
        String regions = deptMapper.selectDeptRegions(deptId);
        if (StringUtils.isNotEmpty(regions))
            dept.setRegions(Arrays.asList(regions.split(",")));

        return dept;
    }

    /**
     * 是否存在子节点
     *
     * @param deptId 部门ID
     * @return 结果
     */
    @Override
    public boolean hasChildByDeptId(Long deptId) {
        int result = deptMapper.hasChildByDeptId(deptId);
        return result > 0 ? true : false;
    }

    /**
     * 查询部门是否存在用户
     *
     * @param deptId 部门ID
     * @return 结果 true 存在 false 不存在
     */
    @Override
    public boolean checkDeptExistUser(Long deptId) {
        int result = deptMapper.checkDeptExistUser(deptId);
        return result > 0 ? true : false;
    }

    /**
     * 校验部门名称是否唯一
     *
     * @param dept 部门信息
     * @return 结果
     */
    @Override
    public String checkDeptNameUnique(SysDept dept) {
        Long deptId = StringUtils.isNull(dept.getDeptId()) ? -1L : dept.getDeptId();
        SysDept info = deptMapper.checkDeptNameUnique(dept.getDeptName(), dept.getParentId());
        if (StringUtils.isNotNull(info) && info.getDeptId().longValue() != deptId.longValue()) {
            return UserConstants.NOT_UNIQUE;
        }
        return UserConstants.UNIQUE;
    }

    /**
     * 新增保存部门信息
     *
     * @param dept 部门信息
     * @return 结果
     */
    @Override
    public int insertDept(SysDept dept) {
        SysDept info = deptMapper.selectDeptById(dept.getParentId());
        // 如果父节点不为正常状态,则不允许新增子节点
        if (!UserConstants.DEPT_NORMAL.equals(info.getStatus())) {
            throw new CustomException("部门停用，不允许新增");
        }
        dept.setAncestors(info.getAncestors() + "," + dept.getParentId());
        Long key = configService.getNextSEQ("seq_sys_dept");
        dept.setDeptId(key);
        int res = deptMapper.insertDept(dept);
        if (res > 0) {
            resetDeptRegions(dept.getRegions(), key);
        }
//        if(res>0)
//            setDeptChildren(dept.getParentId(),dept.getDeptId());
        return res;
    }

    public void resetDeptRegions(List<String> regs, Long deptId) {
        deptMapper.deleteDeptRegions(deptId);
        if (regs != null) {
            if (regs.contains("4419")) {
                SysDeptRegion reg = new SysDeptRegion();
                reg.setDeptId(deptId);
                reg.setRegionCode("4419");
                deptMapper.insertDeptRegion(reg);
            } else {
                for (String region : regs) {
                    SysDeptRegion reg = new SysDeptRegion();
                    reg.setDeptId(deptId);
                    reg.setRegionCode(region);
                    deptMapper.insertDeptRegion(reg);

                }
            }
        }
    }

    public void setDeptChildren(Long parentID, Long deptID) {
        SysDept dpts = deptMapper.selectDeptById(parentID);
        String keys = dpts.getChildDept();
        keys += keys == null ? deptID : "," + deptID;
        dpts.setChildDept(keys);
        dpts.setDateUpdated(new Date());
        deptMapper.updateDept(dpts);
        Long parID = dpts.getParentId();
        if (parID == null || parID == 0)
            return;
        else
            setDeptChildren(dpts.getParentId(), dpts.getDeptId());
    }

    /**
     * 修改保存部门信息
     *
     * @param dept 部门信息
     * @return 结果
     */
    @Override
    public int updateDept(SysDept dept) {
        SysDept newParentDept = deptMapper.selectDeptById(dept.getParentId());
        SysDept oldDept = deptMapper.selectDeptById(dept.getDeptId());
        if (StringUtils.isNotNull(newParentDept) && StringUtils.isNotNull(oldDept)) {
            String newAncestors = newParentDept.getAncestors() + "," + newParentDept.getDeptId();
            String oldAncestors = oldDept.getAncestors();
            dept.setAncestors(newAncestors);
            updateDeptChildren(dept.getDeptId(), newAncestors, oldAncestors);
        }
        int result = deptMapper.updateDept(dept);
        if (UserConstants.DEPT_NORMAL.equals(dept.getStatus())) {
            // 如果该部门是启用状态，则启用该部门的所有上级部门
            updateParentDeptStatus(dept);
        }
        //更新部门权限区域代码
        if (result > 0) {
            resetDeptRegions(dept.getRegions(), dept.getDeptId());
        }
        return result;
    }

    /**
     * 修改该部门的父级部门状态
     *
     * @param dept 当前部门
     */
    private void updateParentDeptStatus(SysDept dept) {
        String updateBy = dept.getUpdateBy();
        dept = deptMapper.selectDeptById(dept.getDeptId());
        dept.setUpdateBy(updateBy);
        deptMapper.updateDeptStatus(dept);
    }

    /**
     * 修改子元素关系
     *
     * @param deptId       被修改的部门ID
     * @param newAncestors 新的父ID集合
     * @param oldAncestors 旧的父ID集合
     */
    public void updateDeptChildren(Long deptId, String newAncestors, String oldAncestors) {
        List<SysDept> children = deptMapper.selectChildrenDeptById(deptId);
        for (SysDept child : children) {
            child.setAncestors(child.getAncestors().replace(oldAncestors, newAncestors));
        }
        if (children.size() > 0) {
            deptMapper.updateDeptChildren(children);
        }
    }

    /**
     * 删除部门管理信息
     *
     * @param deptId 部门ID
     * @return 结果
     */
    @Override
    public int deleteDeptById(Long deptId) {
        return deptMapper.deleteDeptById(deptId);
    }

    /**
     * 递归列表
     */
    private void recursionFn(List<SysDept> list, SysDept t) {
        // 得到子节点列表
        List<SysDept> childList = getChildList(list, t);
        t.setChildren(childList);
        for (SysDept tChild : childList) {
            if (hasChild(list, tChild)) {
                // 判断是否有子节点
                Iterator<SysDept> it = childList.iterator();
                while (it.hasNext()) {
                    SysDept n = (SysDept) it.next();
                    recursionFn(list, n);
                }
            }
        }
    }

    /**
     * 得到子节点列表
     */
    private List<SysDept> getChildList(List<SysDept> list, SysDept t) {
        List<SysDept> tlist = new ArrayList<SysDept>();
        Iterator<SysDept> it = list.iterator();
        while (it.hasNext()) {
            SysDept n = (SysDept) it.next();
            if (StringUtils.isNotNull(n.getParentId()) && n.getParentId().longValue() == t.getDeptId().longValue()) {
                tlist.add(n);
            }
        }
        return tlist;
    }

    /**
     * 判断是否有子节点
     */
    private boolean hasChild(List<SysDept> list, SysDept t) {
        return getChildList(list, t).size() > 0 ? true : false;
    }


    /**
     * 插入部门用户关联表
     *
     * @param deptUser
     * @return
     */
    public int insertDeptUser(SysDeptUser deptUser) {
        return deptMapper.insertDeptUser(deptUser);
    }

    /**
     * 标记删除用户所有关联企业
     *
     * @param userId
     * @return
     */
    public int deleteSysDeptByUserId(Long userId) {
        return deptMapper.deleteSysDeptByUserId(userId);
    }


    /**
     * 获取部门区域
     *
     * @param detpId
     * @return
     */
    public String selectDeptRegions(Long detpId) {
        return deptMapper.selectDeptRegions(detpId);
    }
}
