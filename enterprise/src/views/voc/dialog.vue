<template>
    <div class="form-dialog">
        <el-dialog
            :title="title"
            :visible.sync="formVisible"
            :width="width"
            append-to-body
            :destroy-on-close="true"
            :close-on-click-modal="false"
        >
            <el-form
                ref="form"
                :model="values"
                :rules="rules"
                :label-width="labelWidth"
                @submit.native.prevent="submitForm"
            >
                <slot></slot>
            </el-form>
            <span slot="footer" class="dialog-footer">
                <el-button type="primary" @click="submitForm">提交</el-button>
                <el-button @click="$emit('cancel')">取消</el-button>
            </span>
        </el-dialog>
    </div>
</template>
<script>
export default {
    name: "form-dialog",
    inheritAttrs: false,
    props: {
        title: {
            type: String
        },
        width: {
            type: String,
            default: "500px"
        },
        labelWidth: {
            type: String,
            default: "130px"
        },
        values: {
            type: Object,
            default: () => ({})
        },
        rules: {
            type: Object
        },
        visible: {
            type: Boolean,
            default: false
        }
    },
    computed: {
        sys_name() {
            return this.$route.path.substr(this.$route.path.lastIndexOf("/") + 1);
        },
        formVisible: {
            get() {
                return !!this.visible;
            },
            set(v) {
                this.$emit("update:visible", v);
            }
        }
    },
    methods: {
        submitForm() {
            this.$refs["form"].validate(valid => {
                if (valid) {
                    // this.$emit("update:values", this.values);
                    this.$emit("submit");
                }
            });
        }
    }
};
</script>
