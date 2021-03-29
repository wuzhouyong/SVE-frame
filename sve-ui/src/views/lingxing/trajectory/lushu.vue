<template>
  <div id="map"></div>
</template>
<script>
import BMap from "BMap";
export default {
  data() {
    return {
      speed: 4000, //速度
      lushu: {},
      mycaiId: "工作单:100158282852",
      myMap: {}, //地图,
      center: { lng: 113.969987, lat: 22.928556 },
    };
  },
  props: {
    gpsArr: {
      type: Array,
      default: () => {
        [];
      },
    },
    sourceldList: {
      type: Array,
      default: () => {
        [];
      },
    },
  },
  mounted() {
    this.initMap();
  },
  methods: {
    initMap(v = 13) {
      var map = new BMap.Map("map", { enableMapClick: false });
      this.myMap = map;
      this.myMap.centerAndZoom(
        new BMap.Point(this.center.lng, this.center.lat),
        v
      );
      this.myMap.enableScrollWheelZoom(true);
    },
    //画轨迹
    painOverlay() {
      this.myMap.clearOverlays();
      this.myMap.centerAndZoom(
        new BMap.Point(this.gpsArr[0].longitude, this.gpsArr[0].latitude),
        13
      );
      var arrPois = [];
      if (this.gpsArr) {
        for (let i = 0; i < this.gpsArr.length; i++) {
          var p0 = this.gpsArr[i].longitude;
          var p1 = this.gpsArr[i].latitude;
          // var checksum = makerArry[i].checksum;
          var point = new BMap.Point(p0, p1); //118.230272,33.482474
          arrPois.push(point);
        }
      }
      //画轨迹
      var polyLine = new BMap.Polyline(arrPois, {
        strokeColor: "#0d86ec", //设置颜色
        strokeWeight: 5, //宽度
        strokeOpacity: 0.8, //透明度
      });

      this.myMap.addOverlay(polyLine);
      let sourceArr = [];
      if (this.sourceldList) {
        var size = new BMap.Size(32, 32);
        for (let i = 0; i < this.sourceldList.length; i++) {
          let myIcon = new BMap.Icon(require("@/assets/image/gc.png"), size);
          let p0 = this.sourceldList[i].longitude;
          let p1 = this.sourceldList[i].latitude;
          let point = new BMap.Point(p0, p1);
          let marker = new BMap.Marker(point, { icon: myIcon });
          this.myMap.addOverlay(marker);

          let label = new BMap.Label(this.sourceldList[i].fullName, {
            offset: new BMap.Size(20, -10),
            display: "block",
          });
          let content = this.sourceldList[i];
          marker.setLabel(label); //添加标签
          this.addClickHandler(content, marker);
        }
      }

      BMapLib.LuShu.prototype._move = function (initPos, targetPos, effect) {
        var pointsArr = [initPos, targetPos]; //点数组
        var me = this,
          //当前的帧数
          currentCount = 0,
          //步长，米/秒
          timer = 10,
          step = this._opts.speed / (1000 / timer),
          //初始坐标
          init_pos = this._projection.lngLatToPoint(initPos),
          //获取结束点的(x,y)坐标
          target_pos = this._projection.lngLatToPoint(targetPos),
          //总的步长
          count = Math.round(me._getDistance(init_pos, target_pos) / step);
        //如果想显示小车走过的痕迹，放开这段代码就行
        // this._map.addOverlay(
        //   new BMap.Polyline(pointsArr, {
        //     strokeColor: "#1fb183",
        //     strokeWeight: 5,
        //     strokeOpacity: 1,
        //   })
        // ); // 画线
        //如果小于1直接移动到下一点
        if (count < 1) {
          me._moveNext(++me.i);
          return;
        }
        me._intervalFlag = setInterval(function () {
          //两点之间当前帧数大于总帧数的时候，则说明已经完成移动
          if (currentCount >= count) {
            clearInterval(me._intervalFlag);
            //移动的点已经超过总的长度
            if (me.i > me._path.length) {
              return;
            }
            //运行下一个点
            me._moveNext(++me.i);
          } else {
            currentCount++;
            var x = effect(init_pos.x, target_pos.x, currentCount, count),
              y = effect(init_pos.y, target_pos.y, currentCount, count),
              pos = me._projection.pointToLngLat(new BMap.Pixel(x, y));
            //设置marker
            if (currentCount == 1) {
              var proPos = null;
              if (me.i - 1 >= 0) {
                proPos = me._path[me.i - 1];
              }
              if (me._opts.enableRotation == true) {
                me.setRotation(proPos, initPos, targetPos);
              }
              if (me._opts.autoView) {
                if (!me._map.getBounds().containsPoint(pos)) {
                  me._map.setCenter(pos);
                }
              }
            }
            //正在移动
            me._marker.setPosition(pos);
            //设置自定义overlay的位置
            me._setInfoWin(pos);
          }
        }, timer);
      };
      this.initLushu(this.myMap, arrPois, this.mycaiId);
      this.lushu.start();
    },
    addClickHandler(c, marker) {
      marker.addEventListener("click", (e) => {
        this.openInfo(c, e);
      });
    },
    openInfo(content, e) {
      let oCon = `<div class="openWindowStyle">企业名称:${content.fullName}</div>
      <div class="openWindowStyle">转运单号:${content.transbillid}</div>
      <div class="openWindowStyle">实际数量:${content.factinamt}</div>
      <div class="openWindowStyle">计算数量:${content.flowinamt}</div>`;
      var p = e.target;
      console.log("openInfo -> p", p);
      var point = new BMap.Point(p.getPosition().lng, p.getPosition().lat);
      var infoWindow = new BMap.InfoWindow(oCon, {
        maxWidth: 280,
        minHeight: 20,
      }); // 创建信息窗口对象
      this.myMap.openInfoWindow(infoWindow, point); //开启信息窗口
    },
    initLushu(map, arrPois, mycaiId) {
      this.lushu = new BMapLib.LuShu(map, arrPois, {
        defaultContent: mycaiId,
        autoView: true, //是否开启自动视野调整，如果开启那么路书在运动过程中会根据视野自动调整
        icon: new BMap.Icon(
          require("@/assets/image/car.png"),
          new BMap.Size(52, 26),
          {
            anchor: new BMap.Size(27, 13),
          }
        ),
        speed: this.speed,
        enableRotation: true, //是否设置marker随着道路的走向进行旋转
        rotation: true,
        landmarkPois: [
          //   {
          //     lng: 113.688037,
          //     lat: 22.820393,
          //     html: `<p style="color:red">????</p>`,
          //     pauseTime: 2,
          //   },
        ],
      });
    },
    // play() {
    //   this.lushu.start();
    // },
  },
};
</script>
<style lang="scss">
.openWindowStyle {
  font-size: 13px;
  margin: 10px 0;
}
</style>