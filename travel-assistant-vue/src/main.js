import Vue from 'vue'
import ElementUI from 'element-ui'
import 'element-ui/lib/theme-chalk/index.css'
import axios from 'axios'
import qs from 'qs';
import VueRouter from 'vue-router'
import VueCookies from 'vue-cookies'
import VCharts from 'v-charts'

import App from './App.vue'
import Home from './travel-assistant/home.vue'
import Footprint from './travel-assistant/footprint.vue'
import Attractions from './travel-assistant/attractions.vue'

Vue.use(ElementUI)
Vue.use(VueRouter)
Vue.use(VueCookies)
Vue.use(VCharts)

Vue.prototype.$axios = axios
Vue.prototype.$qs = qs;

VueCookies.config('15min')

const routes = [
  { path: '/', component: Home },
  { path: '/home', component: Home },
  { path: '/footprint',component: Footprint },
  { path: '/attractions', component: Attractions },
]

const router = new VueRouter({
  routes
})

new Vue({
  el: '#app',
  router,
  render: h => h(App)
})


