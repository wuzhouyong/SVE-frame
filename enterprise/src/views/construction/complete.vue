<template>
    <form-dialog
        :title="title"
        :values.sync="form"
        :rules="rules"
        label-width="150px"
        :visible.sync="dialogVisible"
        @submit="submitForm"
    >
        <el-form-item label="施工完成日期" prop="completeDate">
            <el-date-picker
                type="date"
                value-format="yyyy-MM-dd"
                v-model="form.completeDate"
                :picker-options="pickerOptions"
                placeholder="请输入施工完成日期"
                style="width: 100%"
            ></el-date-picker>
        </el-form-item>
        <el-form-item label="备注" prop="completeMemo">
            <el-input type="textarea" :rows="4" v-model="form.completeMemo" placeholder="请输入备注" />
        </el-form-item>
    </form-dialog>
</template>
<script>
import { constructionComplete } from "@/api/construction";
import FormDialog from "@/views/components/form-dialog";

export default {
    components: {FormDialog},
    props: {
        title: {
            type: String
        },
        values: {
            type: Object,
            default: () => ({})
        },
        active_sys: {
            type: String
        },
        visible: {
            type: Boolean,
            default: false
        }
    },
    data() {
        return {
            form: Object.assign({}, this.values),
            rules: {
                completeDate: [
                    {
                        required: true,
                        message: "施工完成日期不能为空",
                        trigger: "blur"
                    }
                ]
            },
            pickerOptions: {
                disabledDate: time => time.getTime() > Date.now() - 8.64e6
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
        submitForm() {
            constructionComplete(this.active_sys, this.form).then(res => {
                if (res.code === 200) {
                    this.msgSuccess(res.message || "联网完成登记成功");
                    this.dialogVisible = false;
                    this.$emit("completed");
                } else {
                    this.msgError(res.message || "联网完成登记失败");
                }
            });
        }
    }
};
</script>