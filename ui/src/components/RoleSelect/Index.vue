<template>
  <div>
    <el-select multiple collapse-tags v-model="selected" @change="change"
               :placeholder="placeholder" size="small" :style="{width: width}"
    >
      <el-option v-for="item in roleOptions" :key="item.id" :label="item.name" :value="item.id"/>
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
  created() {
    this.getRoleOptions()
  },
  props: {
    value: { type: Array },
    placeholder: { type: String, default: '请选择角色' },
    width: { type: String, default: '288px' }
  },
  watch: {
    value(){
      this.selected = this.value
    }
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
      console.log("change事件触发")
      this.$emit('input', this.selected)
    }
  }
}
</script>

<style scoped>

</style>
