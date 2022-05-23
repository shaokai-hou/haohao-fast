<template>
  <div class="app-container">
    <el-card shadow="hover" class="app-card-top">
      搜索
    </el-card>
    <el-card shadow="hover" class="app-card">
      <el-table :data="tableData" row-key="id" border :default-expand-all="false"
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
      tableData: []
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
    }
  }
}
</script>

<style scoped>

</style>
