<template>
  <div class="app-container">
    <el-dialog title="用户详情" :visible.sync="dialogVisible" width="50%" :before-close="handleClose">
    </el-dialog>
  </div>
</template>

<script>
export default {
  name: 'Detail',
  props: {
    dialogVisible: { type: Boolean, default: false }
  },
  methods: {
    handleClose() {
      this.$emit('close')
    }
  }
}
</script>

<style scoped>

</style>
