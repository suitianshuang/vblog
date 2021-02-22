import Vue from 'vue'
import App from './App'

Vue.config.productionTip = false

// 挂载全局方法
import config from "./common/config.js"
Vue.prototype.config=config

import lib from "./common/lib.js"
Vue.prototype.lib=lib

import request from "./common/request.js";
Vue.prototype.$http = request;

import chat from "./common/chat.js";
Vue.prototype.$chat =chat ;


import User from "./common/user.js"
Vue.prototype.User = User

// import animated from 'animate.css' // npm install animate.css --save
 
// Vue.use(animated)
App.mpType = 'app'

const app = new Vue({
    ...App,
	config,
	lib,
})
app.$mount()
