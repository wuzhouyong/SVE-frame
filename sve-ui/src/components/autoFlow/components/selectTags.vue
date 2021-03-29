<template>
  <div class="tags-box">
    <div v-for="item in tags" :key="'tag_'+item.id" class="tag-row" :class="tags.length >3? 'big-row':''">
      <div class="tag-name" :title="item.name+':'">{{ item.name + "：" }}</div>
      <div class="tag-value"
           :title='(item.val == null ? "N/A" : item.val) + " " + (item.unit == null ? "" : item.unit)'>
        {{ (item.val == null ? "N/A" : item.val) + " " + (item.unit == null ? "" : item.unit) }}
      </div>
    </div>
  </div>
</template>

<script>
export default {
  name: "selectTags",
  props: ["tags", "pid"],
  data () {
    return {
      facTags: []
    };
  },
  mounted () {
    //console.log(this.tags);
    // this.facTags = this.getTags(this.pid);

  },
  // watch: {
  //   tags() {
  //     if(this.pid == 330)
  //       console.log(this.tags);
  //   }
  // },
  methods: {
    // getTags(key) {
    //   let tags = [];
    //   for (let tag of this.tags) {
    //     if (tag.pid == key) {
    //       tags.push(tag);
    //     }
    //   }
    //   return tags;
    //   //console.log(tags);
    // }
  }
};
</script>

<style lang="scss" scoped>
.tags-box {
  margin-top: 10px;
  position: absolute;
  min-width: 150px;
  max-height: 100px;
  overflow-y: auto;

  &::-webkit-scrollbar {
    width: 4px;
  }

  &::-webkit-scrollbar-thumb {
    border-radius: 10px;
    -webkit-box-shadow: inset 0 0 5px rgba(0, 0, 0, 0.2);
    background: rgba(53, 195, 61, 0.3);
  }

  &::-webkit-scrollbar-track {
    -webkit-box-shadow: inset 0 0 5px rgba(0, 0, 0, 0.2);
    border-radius: 0;
    background: rgba(0, 0, 0, 0.1);
  }

  .tag-row {
    border-radius: 2px;
    background-color: #00bcd417;
    font-size: 13px;
    padding: 3px;
    margin-bottom: 5px;
    display: flex;
    overflow: hidden;

    .tag-name {
      color: #00bcd4;
      //display: inline-block;
      max-width: 180px;
      width: auto;
      text-align: right;
      overflow: hidden;
      white-space: nowrap;
      text-overflow: ellipsis;
    }

    .tag-value {
      color: #03A9F4;
      flex: 1;
      text-align: center;
      //display: inline-block;
      border-bottom: 1px #03a9f473 solid;
      padding-bottom: 2px;
      overflow: hidden;
      white-space: nowrap;
      text-overflow: ellipsis;
      //float: right;
    }
  }

  /*.big-row {*/
  /*  &:nth-child(odd) {*/
  /*    clear: both;*/
  /*  }*/

  /*  float: left;*/
  /*  margin-left: 10px;*/
  /*}*/

}
</style>
