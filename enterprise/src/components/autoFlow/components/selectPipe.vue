<template>
  <div class="pipe-box">
    <div v-for="(item,index) in pipes" :key="'pipe'+index" class="flow-pipe" :class="[item.class,status? '':'stop']"
         :style="{top:item.top+'px',left:item.left+'px'}">
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
    mounted() {
      //console.log(this.pipe);
      //console.log(this.where);
      let to = this.pipe.name.split(",");
      for (let t of to) {
        //console.log(t);
        let line = this.setLine(t);
        if (line != null)
          this.pipes.push(line);
      }
      //console.log(this.pipes);
    },
    methods: {
      setLine(t) {
        let differ = 50;
        //console.log(t);
        if (t == "right" || t == "left") {
          let y_start = this.where.row * 150 + 80 - this.where.row / 2 * differ;
          let x_start = this.where.col * 150 - 70;
          return {class: t, top: y_start, left: x_start};
        }
        if (t == "up" || t == "down") {
          let y_start = this.where.row * 150 - 70 - (this.where.row - 1) / 2 * differ;
          let x_start = this.where.col * 150 + 80;
          return {class: t, top: y_start, left: x_start};
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

  }
</style>
