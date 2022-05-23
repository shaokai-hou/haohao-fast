<template>
  <div class="app-container">
    <el-card shadow="hover" class="app-card-top">
      <el-form ref="queryParams" :model="queryParams" :inline="true" label-width="80px">
        <el-form-item label="名称" prop="name">
          <el-input v-model="queryParams.name" size="small" placeholder="请输入名称"/>
        </el-form-item>
        <el-form-item>
          <el-button type="success" size="small" @click="handleQuery">搜索</el-button>
          <el-button type="danger" size="small" @click="resetQuery">重置</el-button>
        </el-form-item>
      </el-form>
    </el-card>
    <el-card shadow="hover" class="app-card">
      <el-table :data="tableData" border @selection-change="handleSelectionChange" style="width: 100%">
        <el-table-column type="selection" align="center" min-width="55"></el-table-column>
        <el-table-column prop="name" align="center" label="名称" min-width="180"></el-table-column>
        <el-table-column prop="description" align="center" label="描述" min-width="180"></el-table-column>
        <el-table-column prop="createTime" align="center" label="创建时间" min-width="150"></el-table-column>
        <el-table-column align="center" label="操作" min-width="180">
          <template slot-scope="scope">
            <el-button @click="handleInfo(scope.row)" type="info" size="mini">查看</el-button>
            <el-button @click="handleEdit(scope.row)" type="primary" size="mini">编辑</el-button>
            <el-button @click="handleDelete(scope.row)" type="danger" size="mini">删除</el-button>
          </template>
        </el-table-column>
      </el-table>
      <pagination :total="total" :page.sync="queryParams.pageNum" :limit.sync="queryParams.pageSize"
                  @pagination="getList"
      />
    </el-card>
  </div>
</template>

<script>
import { getRolePage } from '@/api/system/role'

export default {
  name: 'Index',
  data() {
    return {
      queryParams: {
        pageNum: 1,
        pageSize: 10,
        name: undefined
      },
      tableData: [],
      total: 0,
      selection: []
    }
  },
  mounted() {
    this.getList()
  },
  methods: {
    /** 搜索按钮操作 */
    handleQuery() {
      this.queryParams.pageNum = 1
      this.getList()
    },
    /** 重置按钮操作 */
    resetQuery() {
      this.handleQuery()
    },
    getList() {
      getRolePage(this.queryParams).then(res => {
        this.tableData = res.data.records
      })
    },
    handleAdd() {
    },
    handleSelectionChange(val) {
      this.selection = val
    },
    handleInfo(row) {
      console.log(row)
    },
    handleEdit(row) {
      console.log(row)
    },
    handleDelete(row) {
      console.log(row)
    }
  }
}
</script>

<style scoped>

</style>
