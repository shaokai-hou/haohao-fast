<template>
  <div>
    <el-select multiple collapse-tags v-model="selected" @change="change"
               :placeholder="placeholder" size="small"
    >
      <el-option v-for="item in roleOptions" :key="item.id" :label="item.name" :value="item.id"></el-option>
    </el-select>
  </div>
</template>

<script>
import { getRoleList } from '@/api/system/role'

export default {
  name: 'Index',
  data() {
    return {
      selected: [],
      roleOptions: []
    }
  },
  mounted() {
    this.getRoleOptions()
  },
  props: {
    placeholder: { type: String, default: '请选择角色' }
  },
  methods: {
    /** 查询角色侯选项*/
    getRoleOptions() {
      getRoleList().then(res => {
        this.roleOptions = res.data
      })
    },
    /** change事件*/
    change() {
      console.log('this.selected', this.selected)
      this.$emit('input', this.selected)
    }
  }
}
</script>

<style scoped>

</style>
