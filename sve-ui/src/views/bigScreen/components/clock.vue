<template>
  <div class="clock-box">
    <p class="date">{{ date }}</p>
    <p class="time">{{ time }}</p>
  </div>
</template>

<script>
  export default {
    name: 'clock',
    data() {
      return {
        time: '',
        date: '',
        week: ['星期日', '星期一', '星期二', '星期三', '星期四', '星期五', '星期六'],
        timer: {}
      }
    },
    created() {
      this.updateTime()
      this.timer = setInterval(this.updateTime, 1000)
    },
    methods: {
      updateTime() {
        let cd = new Date()
        this.time = this.zeroPadding(cd.getHours(), 2) + ':' + this.zeroPadding(cd.getMinutes(), 2) + ':' + this.zeroPadding(cd.getSeconds(), 2)
        this.date = this.zeroPadding(cd.getFullYear(), 4) + '-' + this.zeroPadding(cd.getMonth() + 1, 2) + '-' + this.zeroPadding(cd.getDate(), 2) + ' ' + this.week[cd.getDay()]
      },
      zeroPadding(num, digit) {
        let zero = ''
        for (let i = 0; i < digit; i++) {
          zero += '0'
        }
        return (zero + num).slice(-digit)
      }
    }
  }
</script>

<style lang="scss" scoped>

  .clock-box {
    width: 200px;
    font-family: unset;
    color: #ffffff;
    text-align: center;
    color: #daf6ff;
    text-shadow: 0 0 20px #0aafe6, 0 0 20px rgba(10, 175, 230, 0);
  }

  .clock-box .time {
    letter-spacing: 0.05em;
    font-size: 15px;
    margin: 3px;
  }

  .clock-box .date {
    letter-spacing: 0.1em;
    font-size: 12px;
      margin: 3px;
  }

  .clock-box .text {
    letter-spacing: 0.1em;
    font-size: 12px;
  }
</style>
