<template>
  <div class="tags-box">
    <div v-for="item in tags" :key="'tag_'+item.id" class="tag-row" :class="tags.length >3? 'big-row':''">
      <div class="tag-name">{{item.name+"："}}</div>
      <div class="tag-value">{{item.val+" "+item.unit}}</div>
    </div>
  </div>
</template>

<script>
  export default {
    name: "selectTags",
    props: ["tags", "pid"],
    data() {
      return {
        facTags: []
      }
    },
    mounted() {
      //console.log(this.tags);
      // this.facTags = this.getTags(this.pid);

    },
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
  }
</script>

<style lang="scss" scoped>
  .tags-box {
    margin-top: 10px;
    position: absolute;

    .tag-row {
      border-radius: 2px;
      background-color: #00bcd417;
      font-size: 13px;
      padding: 4px;
      margin-bottom: 6px;

      .tag-name {
        color: #009688;
        display: inline-block;
      }

      .tag-value {
        color: #03A9F4;
        display: inline-block;
        border-bottom: 1px #03a9f473 solid;
        padding-bottom: 2px;
        float: right;
      }
    }

    .big-row {
      &:nth-child(odd) {
        clear: both;
      }

      float: left;
      margin-left: 10px;
    }

  }
</style>
