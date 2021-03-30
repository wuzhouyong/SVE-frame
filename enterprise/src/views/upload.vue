<template>
    <div class="upload">
        <el-divider content-position="left">附件列表</el-divider>
        <el-upload
            style="margin-bottom: 10px;"
            class="upload-demo"
            :on-change="handleChange"
            action="Fake Action"
            :data="{attachtype:attachtype}"
            :show-file-list="false"
            :auto-upload="false"
        >
            <el-button
                size="mini"
                type="primary"
                v-for="i in fileType"
                :key="i.dictValue"
                @click="uploadFile(i.dictValue)"
            >{{i.dictLabel}}</el-button>
        </el-upload>
        <div style="height: calc(100% - 110px);">
            <el-table :data="fileList" stripe style="width: 100%" height="100%" border>
                <el-table-column prop="showName" label="附件名" show-overflow-tooltip></el-table-column>
                <el-table-column prop="TypeName" label="附件类型" width="220"></el-table-column>
                <el-table-column prop="address" label="操作" width="100" align="center">
                    <template slot-scope="scope">
                        <el-link
                            type="primary"
                            :underline="false"
                            target="_blank"
                            :href="getlink(scope.row.Id)"
                        >下载</el-link>
                        <!-- <el-divider direction="vertical"></el-divider>
                        <el-popconfirm title="确定要删除吗？" @onConfirm="del(scope.row.Id)">
                            <el-button type="text" size="small" slot="reference">删除</el-button>
                        </el-popconfirm> -->
                    </template>
                </el-table-column>
            </el-table>
        </div>
    </div>
</template>
<script>
import { getDict } from "../api/source";
import { uploadFile, fileList, delFile } from "@/api/upload";
export default {
    props: {
        dict_code: {
            type: String,
            required: true
        },
        ref_type: {
            type: String,
            required: true
        }
    },
    data() {
        return {
            fileType: [],
            attachtype: "",
            fileList: []
        };
    },
    computed: {
        sourceId() {
            return this.$store.getters.sourceId;
        }
    },
    watch: {
        dict_code: {
            immediate: true,
            handler(v) {
                if (v) {
                    getDict(v).then(res => {
                        if (res.code) this.fileType = res.rows || res.data;
                    });
                    this.getList();
                }
            }
        }
    },
    methods: {
        getList() {
            let ref = {
                refId: this.sourceId,
                refType: this.ref_type
            };
            fileList(ref).then(res => {
                if (res.code === 200) {
                    this.fileList = res.rows || res.data;
                }
            });
        },
        getlink(id) {
            return `${process.env.VUE_APP_BASE_API}/com/getfile/${id}`;
        },
        //附件类型
        uploadFile(v) {
            this.attachtype = v;
        },
        del(id) {
            delFile(id).then(res => {
                if (res.code === 200) {
                    this.msgSuccess("附件删除成功");
                    this.getList();
                }
            });
        },
        handleChange(file) {
            if (file.size / (1024 * 1024) > 100) {
                this.$notification["warning"]({
                    message: "提示",
                    description: "附件大小不超过100Mb！",
                    duration: 2
                });
                return false;
            } else {
                let _that = this;
                var reader = new FileReader();
                reader.readAsDataURL(file.raw);
                reader.onload = e => {
                    var imgcode = e.target.result;
                    const data = {
                        fileName: file.name,
                        file: imgcode,
                        refId: _that.sourceId,
                        refType: _that.ref_type,
                        attachType: _that.attachtype
                    };
                    uploadFile(data).then(res => {
                        if (res.code === 200) {
                            this.msgSuccess("附件上传成功");
                            this.getList();
                        }
                    });
                };
            }
        }
    }
};
</script>
<style lang="scss">
.upload {
    height: 100%;
    overflow: hidden;
}
</style>