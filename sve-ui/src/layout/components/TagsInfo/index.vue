<template>
  <div class="tags-info">
    <breadcrumb id="breadcrumb-container" class="breadcrumb-container"/>

    <div class="right-menu">
      <div class="home-index">
        <el-popover
          placement="bottom-start"
          width="800"
          popper-class="head-label"
          trigger="click">
          <div class="glob-tree">
            <el-form :model="labelForm" ref="labelForm">
              <el-form-item>
                <n-label-wk :treeData="treeData" v-model="labelForm.labelCondition" ref="isLabelWk"></n-label-wk>
              </el-form-item>
            </el-form>
            <div style="padding:5px;width:100%;text-align: right;">
              <router-link to="/label/labelAdmin">
                <el-button type="primary" icon="el-icon-s-promotion">
                  标签管理
                </el-button>
              </router-link>
              <el-button type="warning" icon="el-icon-refresh" @click="isclear">
                清空标签
              </el-button>
            </div>
          </div>
          <el-link slot="reference" style="font-size:12px;" icon="el-icon-star-on"
                   :underline="false">
            标签标记
          </el-link>
        </el-popover>
      </div>
      <div class="home-index">
        <span class="downLoadSC" title="下载操作手册">
          <el-link icon="el-icon-notebook-2" style="font-size:12px;" href="/static/操作手册.pdf" :underline="false"
                   target="_blank">操作手册</el-link>
        </span>
      </div>
      <div class="home-index" title="个人中心">
        <router-link to="/user/profile" style="font-size:12px;">
          <i class="el-icon-user"></i>
          <span style="padding-left:5px;">{{ user.userName }}</span>
        </router-link>
      </div>
      <div class="home-index" style="margin-right:0">
        <span class="logout-style" @click="logout" title="退出系统">
             <el-link icon="el-icon-s-promotion" style="font-size:12px;" :underline="false"
                      target="_blank">退出</el-link>
        </span>
      </div>
    </div>
  </div>
</template>

<script>
import Breadcrumb from "@/components/Breadcrumb";
import {mapState, mapGetters, mapActions} from "vuex";

import nLabelWk from '@/layout/components/nLabelWk'
import Cookies from "js-cookie";
import {getUserProfile} from "@/api/system/user";
import {labelByUser} from "@/api/system/label";

export default {
  name: "TagsInfo",
  components: {Breadcrumb, nLabelWk},
  data() {
    return {
      user: {},
      treeData: [],
      labelForm: {
        labelCondition: {}
      }
    }
  },
  watch: {
    labelForm: {
      handler(v) {
        let newObj = v.labelCondition;
        let oArr = [];
        for (let i in v.labelCondition) {
          if (v.labelCondition[i].length > 0) {
            oArr.push(v.labelCondition[i])
          }
        }
        this.SaveLabelData({
          searchData: oArr,
          assignData: newObj
        })
      },
      deep: true
    }
  },
  computed: {
    ...mapState(["curos"])
  },
  created() {
    this.getUser()
    this.getTree()
  },
  methods: {
    ...mapActions("label", ["SaveLabelData", "ClearLabelData"]),
    async logout() {
      this.$confirm("确定注销并退出系统吗？", "提示", {
        confirmButtonText: "确定",
        cancelButtonText: "取消",
        type: "warning"
      }).then(() => {
        this.$store.dispatch("LogOut").then(() => {
          Cookies.remove("system-key");
          location.reload();
        });
      });
    },
    getTree() {
      labelByUser().then(res => {
        if (res.code === 200) {
          let oRes = JSON.parse(JSON.stringify(res.data));
          this.treeData = res.data;
        }
      })
    },
    isclear() {
      this.ClearLabelData();
      this.$refs.isLabelWk.clearLabel();
    },
    getUser() {
      getUserProfile().then(response => {
        this.user = response.data;
        this.roleGroup = response.roleGroup;
        this.postGroup = response.postGroup;
      });
    },
  }

}
</script>

<style lang="scss" scoped>
.tags-info {
  border-bottom: 1px solid #E5E5E5;
  /*background-color: #F5F5F5;*/
  min-height: 41px;
  line-height: 40px;
  padding: 0 12px 0 0;
  margin-top: 60px;
  height: 35px;

  .right-menu {
    float: right;
    height: 100%;
    line-height: 40px;
    display: flex;
    align-items: center;

    &:focus {
      outline: none;
    }

    .home-index {
      height: 100%;
      font-size: 12px;
      margin-right: 20px;

      &:hover {
        a,
        i {
          color: #42b983;
        }
      }
    }

    .logout-style {
      cursor: pointer;
      font-size: 12px;
      margin-right: 20px;

      &:hover {
        color: #42b983;

        i {
          color: #42b983;
        }
      }
    }

    .right-menu-item {
      display: inline-block;
      padding: 0 8px;
      height: 100%;
      font-size: 18px;
      color: #5a5e66;
      vertical-align: text-bottom;

      &.hover-effect {
        cursor: pointer;
        transition: background 0.3s;

        &:hover {
          background: rgba(0, 0, 0, 0.025);
        }
      }
    }

    .avatar-container {
      margin-right: 30px;

      .avatar-wrapper {
        margin-top: 5px;
        position: relative;

        .user-avatar {
          cursor: pointer;
          width: 40px;
          height: 40px;
          border-radius: 10px;
        }

        .el-icon-caret-bottom {
          cursor: pointer;
          position: absolute;
          right: -20px;
          top: 25px;
          font-size: 12px;
        }
      }
    }
  }
}
</style>
