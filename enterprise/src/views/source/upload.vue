<template>
    <div>
        <el-upload
            style="margin-bottom: 10px;"
            class="upload-demo"
            :on-change="handleChange"
            action="Fake Action"
            :data="{attachtype:attachtype}"
            :show-file-list="false"
            :auto-upload="false"
        >
            <el-button size="mini" type="primary">附件上传</el-button>
        </el-upload>
        <el-table :data="showFile" stripe style="width: 100%" border>
            <el-table-column prop="fileName" label="附件名" show-overflow-tooltip></el-table-column>
            <el-table-column label="操作" width="100" align="center">
                <template slot-scope="scope">
                    <el-link
                        type="primary"
                        :underline="false"
                        target="_blank"
                        :href="getlink(scope.row.id)"
                    >下载</el-link>
                    <el-divider direction="vertical"></el-divider>
                    <el-popconfirm title="确定要删除吗？" @onConfirm="del(scope.$index,scope.row.id)">
                        <el-button type="text" size="small" slot="reference">删除</el-button>
                    </el-popconfirm>
                </template>
            </el-table-column>
        </el-table>
    </div>
</template>
<script>
import { getDict } from "@/api/source";
import { uploadFile, fileList, delFile } from "@/api/upload";
export default {
    name: "upload",
    props: {
        refId: {
            type: String
        },
        ref_type: {
            type: String,
            required: true
        },
    },
    data() {
        return {
            attachtype: "",
            fileList: [],
            uploads: [],
        };
    },
    computed:{
        showFile(){
            return this.fileList.concat(this.uploads)
        }
    },
    methods: {
        getlink(id) {
            return `${process.env.VUE_APP_BASE_API}/com/getfile/${id}`;
        },
        del(index,id) {
            if(id){
                delFile(id).then(res=>{
                    if(res.code==200){
                        this.$message({
                        showClose: true,
                        message: res.msg,
                        type: "success"
                        });
                        this.fileList=this.fileList.filter(item=>{
                            return item.id!=id
                        })
                    }
                })
            }else{
                let data=this.showFile.splice(index,1)[0];
                this.uploads=this.uploads.filter(item=>{
                    return item.file!=data.file
                })
            }
        },
        getFile(v){
            if(v.refId===undefined){
                return;
            }else{
                fileList(v).then((res)=>{
                    let data=res.data;
                    
                    data.forEach(item=>{
                        let fileName={
                            fileName:undefined,
                            id:undefined
                        }
                        fileName.fileName=item.showName
                        fileName.id=item.Id
                        this.fileList.push(fileName)
                    });
                })
            }
        },
        sendFile(v) {
            this.uploads.forEach(e => {
                e.refId = v;
                uploadFile(e).then(res => {
                    if (res.code === 200) {
                        this.msgSuccess("附件上传成功");
                    }
                });
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
                        refId: _that.refId,
                        refType: _that.ref_type,
                        attachType: "source_modify"
                    };
                    this.uploads.push(data);
                };
            }
        }
    }
};
</script>