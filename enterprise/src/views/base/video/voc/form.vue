<template>
    <form-dialog
        :title="title"
        :values.sync="form"
        :rules="rules"
        label-width="130px"
        :visible.sync="dialogVisible"
        @submit="submitForm"
    >
        <el-form-item label="视频名称" prop="videoDesc">
            <el-input v-model="form.videoDesc" placeholder="请输入视频名称">
                <!-- <template slot="prepend">{{form.eleTypeId}}</template> -->
            </el-input>
        </el-form-item>
        <el-form-item label="视频类型" prop="videoType">
            <el-select v-model="form.videoType" placeholder="视频类型" style="width:100%">
                <el-option
                    :label="i.dictLabel"
                    :value="i.dictValue"
                    v-for="i in videoTypes"
                    :key="i.dictValue"
                ></el-option>
            </el-select>
        </el-form-item>
        <el-form-item label="视频地址" prop="videoAddress">
            <el-input v-model="form.videoAddress" autocomplete="off" placeholder="视频地址"></el-input>
        </el-form-item>
        <el-form-item label="AppKey" prop="appkey">
            <el-input v-model="form.appkey" autocomplete="off" placeholder="AppKey"></el-input>
        </el-form-item>
        <el-form-item label="AppSecret" prop="appsecret">
            <el-input v-model="form.appsecret" autocomplete="off" placeholder="AppSecret"></el-input>
        </el-form-item>
    </form-dialog>
</template>
<script>
import { add, edit } from "@/api/video";
import FormDialog from "@/views/components/form-dialog";

export default {
    name: "video-form",
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
            //因子类型
            videoTypes: [],
            // 表单参数
            form: {},
            // 表单校验
            rules: {
                videoDesc: [
                    {
                        required: true,
                        message: "视频名称不能为空",
                        trigger: "blur"
                    }
                ],
                videoType: [
                    {
                        required: true,
                        message: "视频类型不能为空",
                        trigger: "blur"
                    },
                    { trigger: "blur" }
                ]
            }
        };
    },
    computed: {
        sys_name() {
            return this.$route.path.substr(
                this.$route.path.lastIndexOf("/") + 1
            );
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
    created() {
        this.getDicts("voc_video_type").then(response => {
            this.videoTypes = response.data;
        });
    },
    methods: {
        submitForm() {
            if (!this.form.videoId) {
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