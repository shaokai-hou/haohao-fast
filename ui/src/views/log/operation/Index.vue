<template>
  <div class="app-container">
    <el-card shadow="hover" class="app-card-top">
      搜索
    </el-card>
    <el-card shadow="hover" class="app-card">
      <div class="app-operation">
        <el-button type="success" size="small" @click="handleAdd">新增</el-button>
      </div>
      <el-table :data="tableData" border>
      </el-table>
    </el-card>
  </div>
</template>

<script>
export default {
  name: 'Index',
  data() {
    return {
      tableData: []
    }
  },
  methods: {
    handleAdd() {
    }
  }
}
</script>

<style scoped>

</style>
