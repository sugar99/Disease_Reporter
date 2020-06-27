<template>
  <div>
    <el-container>
      <el-header>
        <el-menu router mode="horizontal">
          <el-menu-item index="/patient">病人管理</el-menu-item>
          <el-menu-item index="/disease">疾病管理</el-menu-item>
          <el-menu-item index="/case">病例管理</el-menu-item>
        </el-menu>
      </el-header>
      <el-main>
        <router-view/>
      </el-main>
    </el-container>
  </div>
</template>

<script>
export default {
  name: 'Home'
}
</script>
