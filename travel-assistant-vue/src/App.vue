<template>
  <div id="app">
    <el-dialog
      title="请先登录"
      :visible.sync="showLogin"
      width="30%"
      center>
    <el-input
      v-model="inputUserName"
      placeholder="请输入用户名">
    </el-input>
    <div style="margin: 20px 0;"></div>
    <el-input 
      v-model="inputPassword" 
      type="password" 
      placeholder="请输入密码">
    </el-input>
    <div style="margin: 20px 0;"></div>
    <el-input
      v-model="inputConfirmPassword"
      v-if="signUp"
      type="password" 
      placeholder="请再次输入密码">
    </el-input>
    <el-tag v-if="message" type="danger">{{message}}</el-tag>
    <div id="loginBtn" align=center>
      <el-button type="primary" v-if="signUp === false" @click="handleSignIn">登录</el-button>
      <el-button type="text" @click="handleSignUp">注册</el-button>
    </div>
  </el-dialog>
    <el-container>
      <el-header><Header></Header></el-header>
      <router-view></router-view>
    </el-container>
  </div>
</template>

<script>

import Header from './travel-assistant/header.vue'
import Home from './travel-assistant/home.vue'

export default {
  data() {
    return {
      api: 'http://localhost:8080/travel-assistant/api/',
      showLogin: this.$cookies.isKey('userName') ? false : true,
      inputUserName: '',
      inputPassword: '',
      inputConfirmPassword: '',
      message: '',
      signUp: false
    }
  },
  components: {
    Header,
    Home,
  },
  methods: {
    handleSignIn() {
      this.$axios.get(this.api + 'signIn', {
        params: {
          userName: this.inputUserName,
          password: this.inputPassword
        }
      }).then((response) => {
        if (response.data === 'OK') {
          this.$cookies.set('userName', this.inputUserName);
          this.showLogin = false;
          location.reload();
        } else {
          this.message = response.data;
        }
      })
    },
    handleSignUp() {
      if (this.signUp === true) {
        this.$axios.post(this.api + 'signUp', {
          userName: this.inputUserName,
          password: this.inputPassword,
          confirmPassword: this.inputConfirmPassword
        }).then((response) => {
          if (response.data === 'OK') {
            this.$cookies.set('userName', this.inputUserName);
          } else {
            this.message = response.data;
          }
        })
      } else {
        this.signUp = true;
      }
    }
  }
}
</script>

<style>
#app {
  font-family: Helvetica, sans-serif;
  text-align: center; 
}
router-view {
  height:545px;
}
</style>
