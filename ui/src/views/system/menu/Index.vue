<template>
  <div class="app-container">
    <el-card shadow="hover" class="app-card-top">
      搜索
    </el-card>
    <el-card shadow="hover" class="app-card">
      <div class="app-operation">
        <el-button type="success" size="small" @click="handleAdd">新增</el-button>
        <el-button type="info" size="small" @click="handleExpand">展开/折叠</el-button>
      </div>
      <el-table v-if="refreshTable" :data="tableData" row-key="id" border :default-expand-all="expandFlag"
                :tree-props="{children: 'children', hasChildren: 'hasChildren'}"
      >
        <el-table-column prop="name" align="center" label="名称" min-width="180"></el-table-column>
        <el-table-column prop="path" align="center" label="路由地址" min-width="180"></el-table-column>
        <el-table-column prop="component" align="center" label="组件名称" min-width="180"></el-table-column>
        <el-table-column prop="menuType" align="center" label="菜单类型" min-width="120"></el-table-column>
        <el-table-column prop="permission" align="center" label="权限标识" min-width="180"></el-table-column>
      </el-table>
    </el-card>
  </div>
</template>

<script>
import { getMenuTree } from '@/api/system/menu'

export default {
  name: 'Index',
  data() {
    return {
      queryParams: {},
      tableData: [],
      refreshTable: true,
      expandFlag: false
    }
  },
  mounted() {
    this.getList()
  },
  methods: {
    getList() {
      getMenuTree(this.queryParams).then(res => {
        this.tableData = res.data
      })
    },
    handleExpand() {
      this.refreshTable = false
      this.expandFlag = !this.expandFlag
      this.$nextTick(() => this.refreshTable = true)
    },
    handleAdd() {

    }
  }
}
</script>

<style scoped>

</style>
