<template>
<el-menu :default-active="activeIndex" class="el-menu-demo" mode="horizontal" @select="handleSelect">
  <el-menu-item index="1">首页</el-menu-item>
  <el-menu-item index="2">足迹</el-menu-item>
  <el-menu-item index="3">5A景区</el-menu-item>
</el-menu>
</template>

<script>
  export default {
    data() {
      return {
        activeIndex: '1',
      };
    },
    methods: {
      handleSelect(key, keyPath) {
        if (key == 1) {
          this.$router.push('/home');
        } else if (key == 2) {
          this.$router.push('/footprint');
        } else {
          this.$router.push('/attractions');
        }
      }
    }
  }
</script>

<style>
.el-menu-demo {
  width: 100%;
}
</style>

