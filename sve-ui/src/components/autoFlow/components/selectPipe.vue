<template>
  <div class="pipe-box">
    <div v-for="(item,index) in pipes" :key="'pipe'+index" class="flow-pipe"
         :class="[item.class,status[index]? '':'stop','pipe_'+item.index]"
         :style="{top:item.top+'px',left:item.left+'px',zIndex:(item.class,status[index]? 1:0)}">
    </div>
  </div>
</template>

<script>
  export default {
    name: "selectPipe",
    props: ["pipe", "where", "status"],
    data() {
      return {
        // status: false,
        pipes: []
      }
    },
    // watch:{
    //   status(){
    //     console.log(this.status);
    //   }
    // },
    mounted() {
      // console.log(this.pipe);
      // console.log(this.where);
      let to = this.pipe.name.split(",");
      let i = 0;
      for (let t of to) {
        //console.log(t);
        let line = this.setLine(t, i);
        i++;
        if (line != null)
          this.pipes.push(line);
      }
      //console.log(this.pipes);
    },
    methods: {
      setLine(t, e) {
        let differ = 50;

        let v = t.split(":");
        //console.log(v);
        t = v[0];
        let count = v.length > 1 ? parseInt(v[1]) : 0;


        if (t == "right" || t == "left") {
          let y_start = (this.where.row * 150 + 70 - this.where.row / 2 * differ) + count * 13;
          let x_start = this.where.col * 150 - 70;
          return {class: t, top: y_start, left: x_start, index: count};
        }
        if (t == "up" || t == "down") {
          let y_start = (this.where.row * 150 - 70 - (this.where.row - 1) / 2 * differ) + count * 13;
          let x_start = (this.where.col * 150 + 80) + count * 13;
          return {class: t, top: y_start, left: x_start, index: count};
        }
        if (t.indexOf("right_") >= 0) {
          let y_start = (this.where.row * 150 + 70 - this.where.row / 2 * differ) + count * 13;
          let x_start = (this.where.col * 150 - 60) + count * 13;
          return {class: t, top: y_start, left: x_start, index: count};
        }
        if (t.indexOf("_right") >= 0) {
          let y_start = (this.where.row * 150 + 70 - this.where.row / 2 * differ) + count * 13;
          let x_start = (this.where.col * 150 + 80) + count * 13;
          return {class: t, top: y_start, left: x_start, index: count};
        }
        return null;
      }
    }
  }
</script>

<style lang="scss" scoped>
  $differ: 50px;

  .pipe-box {
    .flow-pipe.right {
      width: 300px;
      height: 10px;
      position: absolute;
      background-image: url("../../../assets/autoFlow/pipe_right.gif");
      background-color: #42b983;
    }

    .flow-pipe.right_down, .flow-pipe.right_up {
      width: 150px;
      height: 10px;
      position: absolute;
      background-image: url("../../../assets/autoFlow/pipe_right.gif");
      background-color: #42b983;
    }

    .flow-pipe.down_right, .flow-pipe.up_right {
      width: 150px;
      height: 10px;
      position: absolute;
      background-image: url("../../../assets/autoFlow/pipe_right.gif");
      background-color: #42b983;
    }

    .flow-pipe.left {
      width: 300px;
      height: 10px;
      position: absolute;
      background-image: url("../../../assets/autoFlow/pipe_left.gif");
      background-color: #42b983;
    }

    .flow-pipe.up {
      height: 300px - $differ;
      width: 10px;
      position: absolute;
      background-image: url("../../../assets/autoFlow/pipe_top.gif");
      background-color: #42b983;
    }

    .flow-pipe.down {
      height: 300px - $differ;
      width: 10px;
      position: absolute;
      background-image: url("../../../assets/autoFlow/pipe_bottom.gif");
      background-color: #42b983;
    }

    .flow-pipe.stop {
      background-image: none !important;
      background-color: #9e9e9e !important;
    }

    .flow-pipe.stop.pipe_0 {
      background-color: #9e9e9e !important;
    }

    .flow-pipe.stop.pipe_1 {
      background-color: #B2B2B2 !important;
    }

    .flow-pipe.stop.pipe_2 {
      background-color: #d0d0d0 !important;
    }

    .flow-pipe.stop.pipe_3 {
      background-color: #e4e4e4 !important;
    }

    .flow-pipe.pipe_0 {
      background-color: #42b983 !important;
    }

    .flow-pipe.pipe_1 {
      background-color: #50c32d !important;
    }

    .flow-pipe.pipe_2 {
      background-color: #93cc2e !important;
    }

    .flow-pipe.pipe_3 {
      background-color: #a9d800 !important;
    }
  }
</style>
