<template>
  <div class="mapWK">
    <baidu-map
      ak="yLhY4KWHxyL2kbt7qzQIPEVtwM57BPii"
      class="map"
      :center="addressPoint"
      :mapStyle="mapStyle"
      :map-type="mapType"
      :mapClick="false"
      :double-click-zoom="false"
      :scroll-wheel-zoom="true"
      @ready="handler"
      :zoom="zoom"
      :min-zoom="12"
    >
      <bm-boundary
        name="东莞市"
        :strokeWeight="5"
        strokeColor="#fffc00"
        :fillColor="''"
        strokeStyle="dashed"
      ></bm-boundary>
      <div v-for="(marker, i) of sourceList" :key="i">
        <MyOverlay
          :position="{lng: marker.longitude, lat: marker.latitude}"
          :mk="marker"
          @mouseover.native="active = true"
          @mouseleave.native="active = false"
          @clickDoinb="clickDoinb"
        ></MyOverlay>
      </div>
      <bm-control>
      <el-button
          type="danger"
          icon="el-icon-search"
          @click="mapType = (mapType === 'BMAP_HYBRID_MAP'? 'BMAP_NORMAL_MAP':'BMAP_HYBRID_MAP')"
        >{{mapType === 'BMAP_HYBRID_MAP'?'切换普通地图':'切换卫星地图'}}</el-button>
        <el-button
          style="margin-left:10px"
          title="还原"
          @click="replyZoom"
          type="danger"
          icon="el-icon-picture-outline-round"
        >还原地图</el-button>
      </bm-control>
    </baidu-map>
  </div>
</template>
<script>
import BaiduMap from "vue-baidu-map/components/map/Map.vue";
import BmBoundary from "vue-baidu-map/components/others/Boundary";
import BmControl from "vue-baidu-map/components/controls/Control";
import MyOverlay from "./component/MyOverlay";
import mapMethods from "@/utils/map";
const map_style = [
  {
    featureType: "land",
    elementType: "all",
    stylers: {
      color: "#0a112bff"
    }
  },
  {
    featureType: "building",
    elementType: "all",
    stylers: {
      color: "#2b2b2b"
    }
  },
  {
    featureType: "poilabel",
    elementType: "all",
    stylers: {
      visibility: "off"
    }
  },
  {
    featureType: "highway",
    elementType: "all",
    stylers: {
      lightness: -42,
      saturation: -91,
      visibility: "off"
    }
  },
  {
    featureType: "arterial",
    elementType: "geometry",
    stylers: {
      lightness: -77,
      saturation: -94
    }
  },
  {
    featureType: "green",
    elementType: "geometry",
    stylers: {
      color: "#0a112bff"
    }
  },
  {
    featureType: "water",
    elementType: "geometry",
    stylers: {
      color: "#043c59"
    }
  },
  {
    featureType: "all",
    elementType: "labels.text.stroke",
    stylers: {
      color: "#313131"
    }
  },
  {
    featureType: "all",
    elementType: "labels.text.fill",
    stylers: {
      color: "#ffffffff"
    }
  },
  {
    featureType: "manmade",
    elementType: "geometry",
    stylers: {
      color: "#0a112bff"
    }
  },
  {
    featureType: "local",
    elementType: "geometry",
    stylers: {
      color: "#181818ff",
      lightness: -75,
      saturation: -91
    }
  },
  {
    featureType: "subway",
    elementType: "all",
    stylers: {
      visibility: "off"
    }
  },
  {
    featureType: "railway",
    elementType: "all",
    stylers: {
      visibility: "off"
    }
  }
];
export default {
  components: {
    BaiduMap,
    BmBoundary,
    MyOverlay,
    BmControl
  },
  props: ["sourceList", "companyDetails"],
  data() {
    return {
      mapStyle: {
        styleJson: map_style
      },
      zoom: 12,
      addressPoint: { lng: 113.969987, lat: 22.928556 },
      map: null,
      mapType: "BMAP_NORMAL_MAP"
    };
  },
  watch: {
    companyDetails(v) {
      if (v) {
        if (v.longitude !== 0 && v.latitude !== 0) {
          this.addressPoint.lng = v.longitude;
          this.addressPoint.lat = v.latitude;
          this.zoom = 14;
        } else {
          this.replyZoom();
        }
      }
    }
  },
  methods: {
    handler({ BMap, map }) {
      this.map = map;
      this.address = "东莞市";
      map.setMapType(BMAP_NORMAL_MAP);
      mapMethods.setDgShade(map);
      mapMethods.setAreOverlay(map, "");
    },
    clickDoinb(v) {
      this.$emit("cKey", v.val);
    },
    replyZoom() {
      this.map.setCenter(new BMap.Point(113.969987, 22.928556));
      this.map.setZoom(12);
    }
  }
};
</script>
<style lang="scss">
.mapWK {
  width: 100%;
  height: 100%;
  .map {
    width: 100%;
    height: 100%;
  }
}
.BMap_noprint {
  top: 96% !important;
}
.BMap_cpyCtrl,
.anchorBL {
  display: none;
}
.changeMapType {
  position: fixed;
  left: 10px;
  bottom: 10px;
  font-size: 12px;
  z-index: 99;
  opacity: 0.9;
}
</style>
