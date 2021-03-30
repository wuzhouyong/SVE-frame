<template>
    <span class="change-password">
        <el-link icon="el-icon-key" :underline="false" @click="dialogVisible = true">修改密码</el-link>
        <el-dialog
            title="修改密码"
            :visible.sync="dialogVisible"
            :close-on-click-modal="false"
            width="420px"
            append-to-body
            destroy-on-close
        >
            <el-form ref="form" :model="formData" :rules="rules" label-width="100px">
                <el-form-item prop="oldPassword" label="原始密码">
                    <el-input placeholder="请输入密码" v-model="formData.oldPassword" show-password></el-input>
                </el-form-item>
                <el-form-item prop="newPassword" label="新密码">
                    <el-input placeholder="请输入新密码" v-model="formData.newPassword" show-password></el-input>
                </el-form-item>
                <el-form-item prop="confirmPassword" label="确认密码">
                    <el-input
                        placeholder="请输入确认密码"
                        v-model="formData.confirmPassword"
                        show-password
                    ></el-input>
                </el-form-item>
            </el-form>
            <span slot="footer" class="dialog-footer">
                <el-button type="primary" @click="changePassword">确 定</el-button>
                <el-button @click="dialogVisible = false">取 消</el-button>
            </span>
        </el-dialog>
    </span>
</template>
<script>
import { changePwd } from "@/api/login";

export default {
    name: "change-password",
    data() {
        var validatePassword = (rule, value, callback) => {
            if (value) {
                const reg = /^(?![0-9]+$)(?![a-zA-Z]+$)[^]{6,18}$/;
                if (!reg.test(value)) {
                    callback("密码必须包含字母和数字,且在6~18位之间");
                } else {
                    callback();
                }
            } else {
                callback(new Error(rule.label + "不能为空"));
            }
        };
        var validatePassword2 = (rule, value, callback) => {
            if (!value) {
                callback(new Error(rule.label + "不能为空"));
            } else if (value !== this.formData.newPassword) {
                callback(new Error("两次输入的密码不一致!"));
            } else {
                callback();
            }
        };
        return {
            dialogVisible: false,
            formData: {
                oldPassword: null,
                newPassword: null,
                confirmPassword: null
            },
            rules: {
                oldPassword: [{ label: "原始密码" }],
                newPassword: [{ label: "新密码", validator: validatePassword }],
                confirmPassword: [
                    { label: "确认密码", validator: validatePassword2 }
                ]
            }
        };
    },
    methods: {
        changePassword() {
            this.$refs.form.validate(valid => {
                if (valid) {
                    const data = Object.assign({}, this.formData);
                    data.oldPassword = btoa(data.oldPassword);
                    data.newPassword = btoa(data.newPassword);
                    delete data.confirmPassword;
                    changePwd(data).then(res => {
                        if (res.code === 200) {
                            this.dialogVisible = false;
                            this.msgSuccess("密码修改成功");
                        } else {
                            this.msgError("密码修改失败");
                        }
                    });
                }
            });
        }
    }
};
</script>