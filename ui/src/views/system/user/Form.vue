<template>
  <div class="app-container">
    <el-dialog :title="title" :visible.sync="dialogVisible" width="50%" :before-close="handleClose">
      <el-form ref="form" :model="form" :rules="rules" :inline="true" label-width="80px">
        <el-row>
          <el-col :span="12">
            <el-form-item label="用户名" prop="username">
              <el-input v-model="form.username" size="small" placeholder="请输入用户名"/>
            </el-form-item>
          </el-col>
          <el-col :span="12">
            <el-form-item label="密码" prop="password" v-if="passwordFlag">
              <el-input v-model="form.password" size="small" placeholder="请输入密码"></el-input>
            </el-form-item>
          </el-col>
        </el-row>

        <el-row>
          <el-col :span="12">
            <el-form-item label="昵称" prop="nickname">
              <el-input v-model="form.nickname" size="small" placeholder="请输入昵称"></el-input>
            </el-form-item>
          </el-col>
          <el-col :span="12">
            <el-form-item label="角色" prop="roleIds">
              <RoleSelect v-model="form.roleIds"/>
            </el-form-item>
          </el-col>
        </el-row>

        <el-row>
          <el-col :span="12">
            <el-form-item label="手机号" prop="phone">
              <el-input v-model="form.phone" size="small" placeholder="请输入手机号"></el-input>
            </el-form-item>
          </el-col>
          <el-col :span="12">
            <el-form-item label="邮箱" prop="email">
              <el-input v-model="form.email" size="small" placeholder="请输入邮箱"></el-input>
            </el-form-item>
          </el-col>
        </el-row>

        <el-row>
          <el-form-item class="form-button">
            <el-button type="primary" size="small" @click="submitForm('ruleForm')">保存</el-button>
            <el-button type="info" size="small" @click="resetForm('ruleForm')">重置</el-button>
            <el-button type="danger" size="small" @click="handleClose">关闭</el-button>
          </el-form-item>
        </el-row>

      </el-form>
    </el-dialog>
  </div>
</template>

<script>
import { saveUser, updateUser } from '@/api/system/user'

export default {
  name: 'Form',
  data() {
    return {
      form: {
        username: undefined,
        password: undefined,
        nickname: undefined,
        phone: undefined,
        email: undefined,
        roleIds: []
      },
      rules: {
        username: [
          { required: true, message: '请输入用户名', trigger: 'blur' }
        ],
        password: [
          { required: true, message: '请输入密码', trigger: 'blur' },
          { min: 6, max: 10, message: '长度在 6 到 10 个字符', trigger: 'blur' }
        ],
        nickname: [
          { required: true, message: '请输入用户昵称', trigger: 'blur' }
        ],
        roleIds: [
          { required: true, message: '请选择用户角色', trigger: 'change' }
        ]
      },
      passwordFlag: true
    }
  },
  props: {
    title: { type: String, default: '表单' },
    dialogVisible: { type: Boolean, default: false },
    list: { type: Function, require: true },
    entity: { type: Object }
  },
  watch: {
    'entity.id'() {
      this.form = this.entity
      this.passwordFlag = false
    }
  },
  methods: {
    handleClose() {
      this.$emit('close')
    },
    submitForm() {
      this.$refs['form'].validate((valid) => {
        if (valid) {
          if (typeof this.form.id !== 'undefined') {
            updateUser(this.form).then(res => {
              console.log('res', res)
              this.handleClose()
              this.list()
            })
          } else {
            saveUser(this.form).then(res => {
              console.log('res', res)
              this.handleClose()
              this.list()
            })
          }
        }
      })
    },
    resetForm() {
      this.$refs['form'].resetFields()
    }
  }
}
</script>

<style scoped>
.el-input ::v-deep {
  width: 288px;
}

.form-button {
  position: relative;
  left: 150px;
}
</style>
