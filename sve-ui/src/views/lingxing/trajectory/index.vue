<template>
  <div class="traject-wk">
    <div class="traject-form">
      <el-form :inline="true">
        <el-form-item label="处置企业">
          <el-select
            v-model="sourceId"
            placeholder="请选择处置企业"
            style="width:100%"
            @change="sourceChange"
          >
            <el-option
              :label="i.fullName"
              :value="i.sourceId"
              v-for="i in sourceList"
              :key="i.sourceId"
            ></el-option>
          </el-select>
        </el-form-item>
      </el-form>
      <el-form :inline="true" v-if="sourceId">
        <el-form-item label="车牌号" label-width="68px">
          <el-select v-model="carId" placeholder="请选择车牌号" icon="el-icon-plus" size="mini" clearable>
            <el-option :label="o.carTag" :value="o.carId" v-for="o in tagCarList" :key="o.carId"></el-option>
          </el-select>
        </el-form-item>
        <el-form-item label="日期选择">
          <el-date-picker
            v-model="dataTime"
            type="date"
            placeholder="选择日期"
            format="yyyy-MM-dd"
            value-format="yyyy-MM-dd"
          ></el-date-picker>
        </el-form-item>
      </el-form>
      <el-form :inline="true" v-if="carId&&dataTime">
        <el-form-item label="工作单" label-width="68px">
          <el-select
            v-model="wkOrder"
            placeholder="请选择工作单"
            icon="el-icon-plus"
            size="mini"
            clearable
            @change="trailChange"
          >
            <el-option :label="i" :value="i" v-for="i in jointList" :key="i"></el-option>
          </el-select>
        </el-form-item>
        <el-form-item>
          <el-button
            size="mini"
            icon="el-icon-search"
            type="primary"
            @click="$refs.lushuTem.painOverlay()"
          >轨迹查询</el-button>
        </el-form-item>
      </el-form>
      <!-- 
      </el-form>-->
    </div>
    <!-- <div id="map"></div> -->
    <LushuTem ref="lushuTem" :gpsArr="oPath" :sourceldList="sourceldList"></LushuTem>
  </div>
</template>
<script>
import LushuTem from "./lushu";
import {
  tankcarEnterprise,
  getTankCar,
  getTankCarTrans,
  getTrail,
} from "@/api/lingxing";
export default {
  components: { LushuTem },
  data() {
    return {
      center: { lng: 113.969987, lat: 22.928556 },
      sourceId: "",
      dataTime: "",
      carId: "",
      LCPlateNumber: [],
      wkOrderData: [],
      lushu: null,
      map: null,
      pointList: null,
      sourceList: [],
      wkOrder: "",
      tagCarList: [],
      jointList: [],
      trailList: [],
      sourceldList: [],
      oPath: [],
    };
  },
  computed: {
    carChoose() {
      const { carId, dataTime } = this;
      return {
        carId,
        dataTime,
      };
    },
  },
  watch: {
    carChoose(v) {
      if (v.dataTime && v.carId) {
        let oVal = {
          carId: this.carId,
          receiveDate: this.dataTime,
        };
        this.getTankCarTransList(oVal);
      }
    },
  },
  created() {
    this.getSourceList();
  },
  methods: {
    sourceChange(v) {
      this.sourceId = v;
      getTankCar(v).then((res) => {
        if (res.code === 200) {
          this.tagCarList = res.data || res.rows;
        }
      });
    },
    getTankCarTransList(v) {
      getTankCarTrans(v).then((res) => {
        if (res.code === 200) {
          this.jointList = res.data;
        }
      });
    },
    getSourceList() {
      tankcarEnterprise().then((res) => {
        if (res.code === 200) {
          this.sourceList = res.data || res.rows;
        }
      });
    },
    trailChange(v) {
      if (v) {
        getTrail(v).then((res) => {
          if (res.code === 200) {
            this.sourceldList = res.data.firm;
            this.oPath = res.data.path;
          }
        });
      }
    },
  },
};
</script>
<style lang="scss">
.traject-wk {
  width: 100%;
  height: 100%;
  overflow: hidden;
  display: flex;
  flex-direction: column;
  .traject-form {
    padding: 10px 15px;
    display: flex;
    .el-form-item {
      margin-bottom: 0px;
    }
  }
  #map {
    width: 100%;
    flex: 1;
  }
}

.anchorBL {
  display: none;
}
</style>
