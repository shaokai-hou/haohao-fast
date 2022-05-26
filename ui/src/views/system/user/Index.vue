<template>
  <div class="app-container">
    <el-card shadow="hover" class="app-card-top">
      <el-form ref="queryParams" :model="queryParams" :inline="true" label-width="80px">
        <el-form-item label="账号" prop="username">
          <el-input v-model="queryParams.username" size="small" placeholder="请输入账号"/>
        </el-form-item>
        <el-form-item label="用户名" prop="nickname">
          <el-input v-model="queryParams.nickname" size="small" placeholder="请输入用户名"/>
        </el-form-item>
        <el-form-item label="手机号" prop="phone">
          <el-input v-model="queryParams.phone" size="small" placeholder="请输入手机号"/>
        </el-form-item>
        <el-form-item>
          <el-button type="success" size="small" @click="handleQuery">搜索</el-button>
          <el-button type="danger" size="small" @click="resetQuery">重置</el-button>
        </el-form-item>
      </el-form>
    </el-card>
    <el-card shadow="hover" class="app-card">
      <div class="app-operation">
        <el-button type="success" size="small" @click="handleAdd">添加用户</el-button>
        <el-button type="danger" size="small" :disabled="multiple">批量删除用户</el-button>
        <el-button type="info" size="small">导入用户</el-button>
        <el-button type="warning" size="small">导出用户</el-button>
      </div>
      <el-table :data="tableData" border @selection-change="handleSelectionChange" style="width: 100%">
        <el-table-column type="selection" align="center" min-width="55"></el-table-column>
        <el-table-column prop="username" align="center" label="账号" min-width="130"></el-table-column>
        <el-table-column prop="nickname" align="center" label="用户名" min-width="180"></el-table-column>
        <el-table-column prop="phone" align="center" label="手机号" min-width="180"></el-table-column>
        <el-table-column prop="email" align="center" label="邮箱" min-width="180"></el-table-column>
        <el-table-column prop="createTime" align="center" label="创建时间" min-width="180"></el-table-column>
        <el-table-column align="center" label="状态" min-width="180">
          <template slot-scope="props">
            <el-switch v-model="props.row.state" active-color="#ff4949" inactive-color="#13ce66"
                       :active-value="1" :inactive-value="0" @change=handleState(props.row)
                       :disabled="props.row.id ===1"
            />
          </template>
        </el-table-column>
        <el-table-column align="center" label="操作" width="280">
          <template slot-scope="scope">
            <el-button @click="handleInfo(scope.row)" type="info" size="mini">查看</el-button>
            <el-button @click="handleEdit(scope.row)" type="primary" size="mini">编辑</el-button>
            <el-button v-if="scope.row.id !==1" @click="handleDelete(scope.row)" type="danger" size="mini">删除
            </el-button>
          </template>
        </el-table-column>
      </el-table>

      <pagination :total="total" :page.sync="queryParams.pageNum" :limit.sync="queryParams.pageSize"
                  @pagination="getList"
      />
    </el-card>

    <!-- 用户FORM-->
    <Form :title="formDialog.title" :dialog-visible="formDialog.open" @close="formDialog.open = false"
          :entity="formDialog.entity" :list="getList"
    />

    <!-- 用户详情-->
    <Detail :dialog-visible="detailDialog.open" @close="detailDialog.open = false"/>
  </div>
</template>

<script>
import { getUserPage, getUserInfo, updateState, deleteUser } from '@/api/system/user'
import Form from '@/views/system/user/Form'
import Detail from '@/views/system/user/Detail'

export default {
  name: 'Index',
  components: { Detail, Form },
  data() {
    return {
      queryParams: {
        pageNum: 1,
        pageSize: 10,
        username: undefined,
        nickname: undefined,
        phone: undefined
      },
      tableData: [],
      total: 0,
      selection: [],
      formDialog: {
        open: false,
        title: '表单',
        entity: {}
      },
      detailDialog: {
        open: false
      },
      single: true,
      multiple: true
    }
  },
  mounted() {
    this.getList()
  },
  methods: {
    getList() {
      getUserPage(this.queryParams).then(res => {
        this.tableData = res.data.records
        this.total = res.data.total
      })
    },
    handleSelectionChange(val) {
      this.selection = val
      this.single = val.length !== 1
      this.multiple = !val.length
    },
    handleInfo(row) {
      console.log(row)
      this.detailDialog.open = true
    },
    handleEdit(row) {
      getUserInfo(row.id).then(res => {
        this.formDialog.open = true
        this.formDialog.title = '修改用户'
        this.formDialog.entity = res.data
      })
    },
    handleDelete(row) {
      deleteUser({ userIds: [row.id] }).then(() => {
        this.$message.success('删除成功')
        this.getList()
      })
    },
    handleState(row) {
      updateState(row.id).then(res => {
        console.log('修改状态结果', res)
        this.getList()
      })
    },
    handleAdd() {
      this.formDialog.open = true
      this.formDialog.title = '添加用户'
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
