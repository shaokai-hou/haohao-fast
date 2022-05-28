<template>
  <div class="app-container">
    <!-- search-->
    <el-card shadow="hover" class="app-card-top">
      <el-form ref="queryForm" :model="queryParams" :inline="true" label-width="80px">
        <el-form-item label="名称" prop="name">
          <el-input v-model="queryParams.name" size="small" placeholder="请输入名称" style="width: 218px"/>
        </el-form-item>
        <el-form-item label="描述" prop="description">
          <el-input v-model="queryParams.description" size="small" placeholder="请输入描述" style="width: 218px"/>
        </el-form-item>
        <el-form-item>
          <el-button type="success" size="small" @click="handleQuery">搜索</el-button>
          <el-button type="danger" size="small" @click="resetQuery">重置</el-button>
        </el-form-item>
      </el-form>
    </el-card>
    <!-- table-->
    <el-card shadow="hover" class="app-card">
      <div class="app-operation">
        <el-button type="success" size="small" @click="handleAdd">添加角色</el-button>
      </div>
      <el-table :data="tableData" border @selection-change="handleSelectionChange" style="width: 100%">
        <el-table-column type="selection" align="center" min-width="55"></el-table-column>
        <el-table-column prop="name" align="center" label="名称" min-width="180"></el-table-column>
        <el-table-column prop="description" align="center" label="描述" min-width="180"></el-table-column>
        <el-table-column prop="remark" align="center" label="备注" min-width="180"></el-table-column>
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
    <!-- form-->
    <el-dialog :title="title" :visible.sync="open" append-to-body width="25%">
      <el-form ref="form" :model="form" :rules="rules" :inline="true" label-width="80px">
        <el-row>
          <el-col :span="24">
            <el-form-item label="角色名称" prop="name">
              <el-input v-model="form.name" size="small" placeholder="请输入角色名称" style="width: 288px"/>
            </el-form-item>
          </el-col>
        </el-row>
        <el-row>
          <el-col :span="24">
            <el-form-item label="角色描述" prop="description">
              <el-input v-model="form.description" size="small" placeholder="请输入角色描述" style="width: 288px"/>
            </el-form-item>
          </el-col>
        </el-row>
        <el-row>
          <el-col :span="24">
            <el-form-item label="备注" prop="remark">
              <el-input v-model="form.remark" type="textarea" placeholder="请输入备注" style="width: 288px"/>
            </el-form-item>
          </el-col>
        </el-row>
        <el-row>
          <el-form-item class="form-button">
            <el-button type="primary" size="small" @click="submitForm">保存</el-button>
            <el-button type="info" size="small" @click="reset">重置</el-button>
            <el-button type="danger" size="small" @click="open = false">关闭</el-button>
          </el-form-item>
        </el-row>
      </el-form>
    </el-dialog>
  </div>
</template>

<script>
import { getRolePage, updateRole, saveRole, getRoleInfo, deleteRole } from '@/api/system/role'

export default {
  name: 'Index',
  data() {
    return {
      queryParams: {
        pageNum: 1,
        pageSize: 10,
        name: undefined,
        description: undefined
      },
      tableData: [],
      total: 0,
      selection: [],
      form: {},
      rules: {
        name: [
          { required: true, message: '请输入角色名称', trigger: 'blur' }
        ],
        description: [
          { required: true, message: '请输入角色描述', trigger: 'blur' }
        ]
      },
      title: 'FORM表单',
      open: false
    }
  },
  mounted() {
    this.getList()
  },
  methods: {
    getList() {
      getRolePage(this.queryParams).then(res => {
        this.tableData = res.data.records
      })
    },
    reset() {
      this.form = {
        name: undefined,
        description: undefined,
        remark: undefined
      }
      if (this.$refs.form !== undefined) {
        this.$refs.form.resetFields()
      }
    },
    handleAdd() {
      this.reset()
      this.title = '添加角色'
      this.open = true
    },
    handleEdit(row) {
      this.reset()
      getRoleInfo(row.id).then(res => {
        this.form = res.data
        this.title = '编辑角色'
        this.open = true
      })
    },
    handleDelete(row) {
      console.log(row)
      
    },
    handleInfo(row) {
      console.log(row)
    },
    handleSelectionChange(val) {
      this.selection = val
    },
    handleQuery() {
      this.queryParams.pageNum = 1
      this.getList()
    },
    resetQuery() {
      if (this.$refs.form !== undefined) {
        this.$refs.queryForm.resetFields()
      }
      this.handleQuery()
    },
    submitForm() {
      this.$refs['form'].validate((valid) => {
        if (valid) {
          if (typeof this.form.id !== 'undefined') {
            updateRole(this.form).then(() => {
              this.open = false
              this.getList()
              this.$message.success('更新成功')
            })
          } else {
            saveRole(this.form).then(() => {
              this.open = false
              this.getList()
              this.$message.success('保存成功')
            })
          }
        }
      })
    }
  }
}
</script>

<style scoped>
.form-button {
  position: relative;
  left: 100px;
}
</style>
