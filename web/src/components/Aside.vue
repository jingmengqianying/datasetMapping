<template>
  <div>
    <el-row style="width: 200px; min-height: calc(100vh - 50px)">
      <el-col>
        <el-menu
            active-text-color="#ffd04b"
            background-color="#545c64"
            class="el-menu-vertical-demo"
            router
            :default-active="$router.path"
            text-color="#fff">
          <el-menu-item index="/">
            <span>表查询</span>
          </el-menu-item>
          <el-menu-item index="/mapper">
            <span>映射关系管理</span>
          </el-menu-item>
        </el-menu>
      </el-col>
    </el-row>
  </div>
</template>

<script>

export default {
  name: "AsidePage"
}

</script>

<style scoped>

</style>
