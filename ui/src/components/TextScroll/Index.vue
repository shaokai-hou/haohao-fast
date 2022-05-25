<template>
  <div class="text-container">
    <transition name="slide" mode="out-in">
      <p class="text" :key="text.id">{{ text.val }}</p>
    </transition>
  </div>
</template>

<script>
export default {
  name: 'Index',
  props: {
    dataList: {
      type: Array, default() {
        return []
      }
    }
  },
  data() {
    return {
      count: 0, // 当前索引
      intervalId: null, // 定时器ID
      playTime: 5000 // 定时器执行间隔
    }
  },
  computed: {
    text() {
      return {
        id: this.count,
        val: this.dataList[this.count]
      }
    }
  },
  created() {
    this.intervalId = setInterval(() => { // 定义定时器
      this.getText()
    }, this.playTime)
  },
  methods: {
    getText() {
      const len = this.dataList.length // 获取数组长度
      if (len === 0) {
        return // 当数组为空时，直接返回
      }
      if (this.count === len - 1) { // 当前为最后一个时
        this.count = 0 // 从第一个开始
      } else {
        this.count++ // 自增
      }
    }
  },
  destroyed() {
    clearInterval(this.intervalId) // 清除定时器
  }
}
</script>

<style scoped>
.text-container {
  font-size: 16px;
  color: #000000;
  margin-right: 20px;
  height: 60px;
  text-decoration: underline;
}

.text {
  text-align: right;
  margin: auto 0;
}

.slide-enter-active, .slide-leave-active {
  transition: all 1s;
}

.slide-enter {
  transform: translateY(40px);
}

.slide-leave-to {
  transform: translateY(-40px);
}
</style>
