<template>
  <div class="app-container">
    <el-card shadow="hover" class="app-card-top">
      <el-form ref="queryParams" :model="queryParams" :inline="true" label-width="80px">
        <el-form-item label="字典编码" prop="dictCode">
          <el-input v-model="queryParams.dictCode" size="small" placeholder="请输入字典编码"/>
        </el-form-item>
        <el-form-item label="字典名称" prop="dictName">
          <el-input v-model="queryParams.dictName" size="small" placeholder="请输入字典名称"/>
        </el-form-item>
        <el-form-item>
          <el-button type="success" size="small" @click="handleQuery">搜索</el-button>
          <el-button type="danger" size="small" @click="resetQuery">重置</el-button>
        </el-form-item>
      </el-form>
    </el-card>
    <el-card shadow="hover" class="app-card">
      <div class="app-operation">
        <el-button type="success" size="small" @click="handleAdd">新增</el-button>
      </div>
      <el-table :data="tableData" border @selection-change="handleSelectionChange" style="width: 100%">
        <el-table-column type="selection" align="center" min-width="55"></el-table-column>
        <el-table-column prop="dictCode" align="center" label="字典编码" min-width="130"></el-table-column>
        <el-table-column prop="dictName" align="center" label="字典名称" min-width="180"></el-table-column>
        <el-table-column prop="createTime" align="center" label="创建时间" min-width="180"></el-table-column>
        <el-table-column align="center" label="操作" width="380">
          <template slot-scope="scope">
            <el-button @click="handleInfo(scope.row)" type="info" size="mini">查看字典项</el-button>
            <el-button @click="handleInfo(scope.row)" type="info" size="mini">查看</el-button>
            <el-button @click="handleEdit(scope.row)" type="primary" size="mini">编辑</el-button>
            <el-button @click="handleDelete(scope.row)" type="danger" size="mini">删除
            </el-button>
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
import { getDictPage } from '@/api/system/dict'

export default {
  name: 'Index',
  data() {
    return {
      queryParams: {
        pageNum: 1,
        pageSize: 10,
        dictCode: undefined,
        dictName: undefined
      },
      tableData: [],
      total: 0,
      selection: [],
      single: true,
      multiple: true
    }
  },
  mounted() {
    this.getList()
  },
  methods: {
    getList() {
      getDictPage(this.queryParams).then(res => {
        this.tableData = res.data.records
        this.total = res.data.total
      })
    },
    handleSelectionChange(val) {
      this.selection = val
      this.single = val.length !== 1
      this.multiple = !val.length
    },
    handleAdd() {
    },
    handleInfo(row) {
    },
    handleEdit(row) {
    },
    handleDelete(row) {
    },
    /** 搜索按钮操作 */
    handleQuery() {
      this.queryParams.pageNum = 1
      this.getList()
    },
    /** 重置按钮操作 */
    resetQuery() {
      this.handleQuery()
    }
  }
}
</script>

<style scoped>

</style>
