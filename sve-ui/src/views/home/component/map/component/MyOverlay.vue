<template>
  <bm-overlay ref="customOverlay" class="circle" pane="labelPane" @draw="draw">
    <div @click.stop="handleClick(mk.id)" class="h_circle" :id="mk.id" :title="mk.sourceName">
      <div class="circle1" :class="'pointBgColor'+mk.warnLevel">&nbsp;</div>
      <div class="circle2" :class="'pointBgColor'+mk.warnLevel">&nbsp;</div>
      <div class="center" :class="'centerBgColor'+mk.warnLevel"></div>
      <div class="pointName">{{ mk.sourceName }}</div>
    </div>
  </bm-overlay>
</template>

<script>
import BaiduMap from "vue-baidu-map/components/map/Map.vue";
import BmOverlay from "vue-baidu-map/components/overlays/Overlay.vue";
import BmInfoWindow from "vue-baidu-map/components/overlays/InfoWindow";

export default {
  components: {
    BmOverlay,
    BmInfoWindow
  },
  props: ["position", "mk"],
  watch: {
    position: {
      handler () {
        this.$refs.customOverlay.reload();
      },
      deep: true
    }
  },
  methods: {
    handleClick (ids) {
      this.$emit("clickDoinb", {ids: ids, val: this.mk});
    },
    draw ({el, BMap, map}) {
      const {lng, lat} = this.position;
      const pixel = map.pointToOverlayPixel(new BMap.Point(lng, lat));
      el.style.left = pixel.x + "px";
      el.style.top = pixel.y + "px";
    }
  }
};
</script>
<style lang="scss">
$statusColor0: #63d59d;
$statusColor1: #db1f35;
$statusColor2: #f63;
$statusColor3: #edc414;
$statusColor4: #48D1CC;
.circle {
  width: 40px;
  height: 40px;
  position: absolute;
  top: 50%;
  left: 50%;
  margin: -20px 0 0 -20px;
}

.h_circle {
  width: 40px;
  height: 40px;
  position: relative;
}

.circle1,
.circle2 {
  position: absolute;
  left: 50%;
  top: 50%;
  margin: -14px 0 0 -14px;
  width: 28px;
  height: 28px;
  border-radius: 50%;
  border-width: 1px;
  border-style: solid;
}

.pointBgColor0 {
  border-color: $statusColor0;
}

.pointBgColor1 {
  border-color: $statusColor1;
}

.pointBgColor2 {
  border-color: $statusColor2;
}

.pointBgColor3 {
  border-color: $statusColor3;
}

.pointBgColor4 {
  border-color: $statusColor4;
}

.center {
  position: absolute;
  left: 50%;
  top: 50%;
  margin: -4px 0 0 -4px;
  width: 8px;
  height: 8px;
  border-radius: 50%;
  background: #04cfda;
  text-align: center;
  line-height: 70px;
  color: #eaeaea;
  font-size: 16px;
  font-family: "";
}

.centerBgColor0 {
  background-color: $statusColor0;
}

.centerBgColor1 {
  background-color: $statusColor1;
}

.centerBgColor2 {
  background-color: $statusColor2;
}

.centerBgColor3 {
  background-color: $statusColor3;
}

.centerBgColor4 {
  background-color: $statusColor4;
}

.circle1 {
  -webkit-animation: circle 3s linear infinite;
  animation: circle 3s linear infinite;
}

.circle2 {
  -webkit-animation: circle 3s linear 0.8s infinite;
  animation: circle 3s linear 0.8s infinite;
}

// .circle3 {
//   -webkit-animation: circle 3s linear 1.6s infinite;
//   /* Safari and Chrome */
//   animation: circle 3s linear 1.6s infinite;
// }
.pointName {
  padding: 3px 5px;
  background-color: rgba(23, 32, 47, 0.6);
  border: 1px solid #797979;
  position: absolute;
  top: -14px;
  left: 24px;
  font-size: 12px;
  width: auto;
  max-width: 170px;
  color: #fff;
  display: block;
  overflow: hidden;
  white-space: nowrap;
  text-overflow: ellipsis;
}

@-webkit-keyframes circle {
  /* Safari and Chrome */
  from {
    opacity: 1;
    -webkit-transform: scale(0);
  }

  to {
    opacity: 0;
    -webkit-transform: scale(3);
  }
}
</style>
