<template>
  <div class="app-container">
    <el-pagination
      background
      layout="prev, pager, next"
      :total="total"
      :current-page.sync="currentPage"
      :page-size.sync="pageSize"
      :hide-on-single-page="true"
      :layout="layout"
      @size-change="handleSizeChange"
      @current-change="handleCurrentChange"
    >
    </el-pagination>
  </div>
</template>

<script>
export default {
  name: 'Index',
  props: {
    total: { required: true, type: Number },
    page: { type: Number, default: 1 },
    limit: { type: Number, default: 10 },
    layout: { type: String, default: 'total, sizes, prev, pager, next, jumper' }
  },
  computed: {
    currentPage: {
      get() {
        return this.page
      },
      set(val) {
        this.$emit('update:page', val)
      }
    },
    pageSize: {
      get() {
        return this.limit
      },
      set(val) {
        this.$emit('update:limit', val)
      }
    }
  },
  methods: {
    handleSizeChange(val) {
      this.$emit('pagination', { page: this.currentPage, limit: val })
    },
    handleCurrentChange(val) {
      this.$emit('pagination', { page: val, limit: this.pageSize })
    }
  }
}
</script>

<style scoped>

</style>
