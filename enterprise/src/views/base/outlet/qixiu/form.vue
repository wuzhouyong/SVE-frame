<template>
    <form-dialog
        :title="title"
        :values.sync="form"
        :rules="rules"
        label-width="130px"
        :visible.sync="dialogVisible"
        @submit="submitForm"
    >
        <el-form-item label="排放口名称" prop="outletName">
            <el-input v-model="form.outletName" placeholder="请输入排放口名称" />
        </el-form-item>
        <el-form-item label="备注" prop="memo">
            <el-input v-model="form.memo" placeholder="请输入备注" />
        </el-form-item>
    </form-dialog>
</template>
<script>
import { add, edit } from "@/api/outlet";
import FormDialog from "@/views/components/form-dialog";
export default {
    name: "outlet-form",
    components: { FormDialog },
    props: {
        title: {
            type: String
        },
        values: {
            type: Object,
            default: () => ({})
        },
        visible: {
            type: Boolean,
            default: false
        }
    },
    data() {
        return {
            // 表单参数
            form: {
                outletName: undefined,
                memo: undefined
            },
            // 表单校验
            rules: {
                outletName: [
                    {
                        required: true,
                        message: "排放口名称不能为空",
                        trigger: "blur"
                    }
                ]
            }
        };
    },
    computed: {
        sys_name() {
            return this.$route.path.substr(this.$route.path.lastIndexOf("/") + 1);
        },
        dialogVisible: {
            get() {
                return this.visible || false;
            },
            set(v) {
                this.$emit("update:visible", v);
            }
        }
    },
    watch: {
        values() {
            this.form = Object.assign({}, this.values);
        }
    },
    methods: {
        submitForm(v) {
            if (!this.form.outletId) {
                add(this.sys_name, this.form).then(res => {
                    if (res.code === 200) {
                        this.msgSuccess("添加成功");
                        this.$emit("completed");
                    } else {
                        this.msgError(res.message);
                    }
                });
            } else {
                edit(this.sys_name, this.form).then(res => {
                    if (res.code === 200) {
                        this.msgSuccess("修改成功");
                        this.$emit("completed");
                    } else {
                        this.msgError(res.message);
                    }
                });
            }
        }
    }
};
</script>