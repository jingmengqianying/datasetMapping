<template>
  <div><el-button class="mt-4" @click="addData">增加表映射数据</el-button></div>
  <el-table :data="tableData" stripe style="width: 100%" height="250" v-loading="loading">
    <el-table-column v-for="(item, index) in columnList" :key="index"
                     :label="item.desc" :prop="item.name"/>
  </el-table>
</template>
<script>
export default {
  name: 'MapperPage',
  components: {},
  data() {
    return {}
  }
}
</script>

