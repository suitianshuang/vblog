import Vue from 'vue'
import ElementUI from 'element-ui';
import 'element-ui/lib/theme-chalk/index.css';
import App from './App.vue'
import router from './router'

import store  from './store/index.js'
import {initMenu} from './utils/menus.js'
import {postKeyValueRequest} from './utils/api.js'
import {postRequest} from './utils/api.js'
import {putRequest} from './utils/api.js'
import {getRequest} from './utils/api.js'
import {deleteRequest} from './utils/api.js'
import './assets/font/iconfont.css'

import User from "./utils/user.js"
Vue.prototype.User = User
// import Time from './utils/time.js'
// Vue.prototype.Time = Time

Vue.prototype.postKeyValueRequest = postKeyValueRequest;
Vue.prototype.postRequest = postRequest;
Vue.prototype.putRequest = putRequest;
Vue.prototype.getRequest = getRequest;
Vue.prototype.deleteRequest = deleteRequest;

Vue.config.productionTip = false

Vue.use(ElementUI);

// 全局导航守卫
router.beforeEach((to, from, next) => {
    //to去哪  from 从哪来  next相当于过滤器链的放行

	if (to.path == '/') {
	    next();
	}else {
	    if (window.sessionStorage.getItem("user")) {
	        initMenu(router, store);
	        next();
	    }else{
	        next('/?redirect='+to.path);
	    }
	}
})

new Vue({
  router,
  store,
  render: h => h(App)
}).$mount('#app')
