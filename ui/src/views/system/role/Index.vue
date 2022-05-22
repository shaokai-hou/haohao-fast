<template>
  <div class="app-container">
    <el-card shadow="hover" class="app-card-top">
      搜索
    </el-card>
    <el-card shadow="hover" class="app-card">
      <el-table :data="tableData" border style="width: 100%">
        <el-table-column prop="username" align="center" label="账号" width="180"></el-table-column>
        <el-table-column prop="nickname" align="center" label="用户名" width="180"></el-table-column>
        <el-table-column prop="phone" align="center" label="手机号"></el-table-column>
        <el-table-column prop="createTime" align="center" label="地址"></el-table-column>
      </el-table>
    </el-card>
  </div>
</template>

<script>
import { getUserPage } from '@/api/system/user'

export default {
  name: 'Index',
  data() {
    return {
      queryParams: {},
      tableData: []
    }
  },
  mounted() {
    this.getList()
  },
  methods: {
    getList() {
      getUserPage(this.queryParams).then(res => {
        this.tableData = res.data.records
      })
    }
  }
}
</script>

<style scoped>

</style>
