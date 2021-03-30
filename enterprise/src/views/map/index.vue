/*  * @Date: yyyy-08-Th 10:34:36  * @Last Modified time: yyyy-08-Th 10:34:36  *//*  * @Date: yyyy-08-Th 10:34:33  * @Last Modified time: yyyy-08-Th 10:34:33  */<template>
  <div>
    <el-form :inline="true">
      <el-form-item label="车牌号" prop="licensePlateNumber" label-width="68px">
        <el-select
          v-model="carInfo.licensePlateNumber"
          placeholder="请选择车牌号"
          icon="el-icon-plus"
          size="mini"
          clearable
        >
          <el-option label="110" value="1"></el-option>
          <el-option label="120" value="2"></el-option>
          <el-option label="119" value="3"></el-option>
        </el-select>
      </el-form-item>
      <el-form-item label="日期选择" prop="dataTime">
        <el-date-picker
          v-model="carInfo.dataTime"
          type="datetime"
          placeholder="选择结束日期"
          format="yyyy-MM-dd HH:mm:ss"
          value-format="yyyy-MM-dd HH:mm:ss"
        ></el-date-picker>
      </el-form-item>
      <el-form-item label="工作单" prop="wkOrder" label-width="68px">
        <el-select
          v-model="carInfo.wkOrder"
          placeholder="请选择车牌号"
          icon="el-icon-plus"
          size="mini"
          clearable
        >
          <el-option label="110" value="1"></el-option>
          <el-option label="120" value="2"></el-option>
          <el-option label="119" value="3"></el-option>
        </el-select>
      </el-form-item>
      <el-form-item>
        <el-button size="mini" icon="el-icon-search" type="primary" @click="run">轨迹查询</el-button>
      </el-form-item>
    </el-form>
    <div id="map" style="width:100%;height:800px;"></div>
  </div>
</template>
<script>
import BMap from "BMap";
import jsonData from "./data";
export default {
  data() {
    return {
      center: { lng: 116.404, lat: 39.915 },
      carInfo: {
        //车辆信息
        licensePlateNumber: "",
        wkOrder: "",
        dataTime: "",
      },
      LCPlateNumber: [],
      wkOrderData: [],
      lushu: null,
      map: null,
      pointList: null,
    };
  },
  created(){
    this.getLCPlateNumber();
  },
  mounted() {
    this.initMap();
  },

  methods: {
    getLCPlateNumber(){//获取车牌号
      setTimeout(()=>{
        console.log("略略略略略略略略略略")
      })
    },
    getwkOrderData(){//获取工作单信息
    (this.carInfo.licensePlateNumber!==""&&this.carInfo.dataTime==="")?
      setTimeout(()=>{
        console.log("略略略略略略略略略略")
      }):console.log("输入车牌号或者日期出错!")
    },
    initMap() {
      //地图初始化
      var map = new BMap.Map("map"); // 创建Map实例
      this.map = map;
      this.map.centerAndZoom(
        new BMap.Point(this.center.lng, this.center.lat),
        14
      ); // 初始化地图,设置中心点坐标和地图级别
      //添加地图类型控件
      this.map.addControl(
        new BMap.MapTypeControl({
          mapTypes: [BMAP_NORMAL_MAP, BMAP_HYBRID_MAP],
        })
      );
      this.map.enableScrollWheelZoom(true);
    },

    run() {
      //启动路书
      this.search();
      setTimeout(() => {
        this.lushu.start();
      }, 1000);
    },

    search() {
      //初始化路书及marker
      if (this.carInfo.licensePlateNumber==="") {
        alert("查无车辆");
        return
      }
      if(this.carInfo.dataTime===''){
        alert("输入日期为空");
        return
      }
      let P = new Promise((resolve, reject) => {
        setTimeout(() => {
          resolve(jsonData);
        }, 1000);
      });
      P.then((res) => {
        if (true) {
          let list = res.points;
          // this.lineList = []
          this.pointList = [];
          list.reduce((arr, current) => {
            let point = new BMap.Point(current.lng, current.lat);
            arr.push(point);
            return arr;
          }, this.pointList);
          this.map.setViewport(this.pointList);
          this.lushu = new BMapLib.LuShu(this.map, this.pointList, {
            speed: 500,
            icon: new BMap.Icon(
              require("@/assets/track/car.png"),
              new BMap.Size(56, 26),
              { anchor: new BMap.Size(27, 13) }
            ),
            autoView: true,
            enableRotation: true,
            rotation: true,
            landmarkPois: [],
          });
          this.createMaker(this.pointList);
        }
      });
    },
    createMaker(pl) {
      this.map.clearOverlays();
      for (let i = 0; i < pl.length; i++) {
        let pointIcon = new BMap.Icon(
          require("@/assets/track/car.png"),
          new BMap.Size(10, 10)
        );
        let startIcon = new BMap.Icon(
          require("@/assets/track/car.png"),
          new BMap.Size(10, 10)
        );
        let endIcon = new BMap.Icon(
          require("@/assets/track/car.png"),
          new BMap.Size(10, 10)
        );
        let point = new BMap.Point(pl[i].lng, pl[i].lat);
        let marker;
        if (i == 0) {
          marker = new BMap.Marker(point, { icon: startIcon });
        } else if (i == pl.length - 1) {
          marker = new BMap.Marker(point, { icon: endIcon });
        } else {
          marker = new BMap.Marker(point, { icon: pointIcon });
        }
        this.map.addOverlay(marker);
        let lng = pl[i].lng;
        let lat = pl[i].lat;
        // let time = p[i].time;
        let address = "";
        let myGeocoder = new BMap.Geocoder();
        myGeocoder.getLocation(point, (res) => {
          address = res.address;
        });
        marker.addEventListener("click", function (e) {
          let s =
            " <p>经度：" +
            lng +
            "</p>\n" +
            "<p>纬度：" +
            lat +
            "</p>\n" +
            "<p>地址：" +
            address +
            "</p>";
          let opts = {
            enableAutoPan: true,
          };
          let infoWindow = new BMap.InfoWindow(s, opts);
          this.map.openInfoWindow(infoWindow, point);
        });
      }
      this.map.addOverlay(
        new BMap.Polyline(this.pointList, {
          strokeColor: "blue",
          strokeOpacity: 0.5,
          strokeWeight: 3,
        })
      );
      // this.map.centerAndZoom(new BMap.Point(pointList[0].lng,pointList[0].lat), 12);
    },
  },
};
</script>
