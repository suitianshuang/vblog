import Vue from 'vue'
import Vuex from 'vuex'
import {
	getRequest
} from '../utils/api.js'
import {
	Notification
} from 'element-ui';
import SockJS from 'sockjs-client'
import Stomp from 'stompjs'
const now = new Date();
Vue.use(Vuex)


const store = new Vuex.Store({
	state: {
		routes: [],
		currentSession: null,
		filterKey: '',
		sessions: {

		},
		admins: [],
		currentAdmin: JSON.parse(window.sessionStorage.getItem("user")),
		stomp: null,
		isDot: {}
	},
	mutations: {
		INIT_CURRENTADMIN(state, currentAdmin) {
			state.currentAdmin = currentAdmin;
		},
		initRoutes(state, data) {
			state.routes = data;
		},

		changeCurrentSession(state, currentSession) {
			Vue.set(state.isDot, state.currentAdmin.username + '#' + currentSession.username, false);
			state.currentSession = currentSession;
		},

		addMessage(state, msg) {
			let mss = state.sessions[state.currentAdmin.username + '#' + msg.to];
			if (!mss) {
				// state.sessions[state.currentAdmin.username+'#'+msg.to] = [];
				Vue.set(state.sessions, state.currentAdmin.username + '#' + msg.to, []);
			}
			state.sessions[state.currentAdmin.username + '#' + msg.to].push({
				content: msg.content,
				date: new Date(),
				self: !msg.notSelf
			})
		},
		INIT_DATA(state) {
			// 在这里处理聊天记录
			let data = localStorage.getItem('vue-chat-session');
			if (data) {
				state.sessions = JSON.parse(data);
			}
		},
		INIT_ADMINS(state, data) {
			state.admins = data;
		}

	},
	actions: {
		// 连接websocket
		connect(context) {
			context.state.stomp = Stomp.over(new SockJS('/ws/ep'));
			context.state.stomp.connect({}, success => {
				context.state.stomp.subscribe('/user/queue/chat', msg => {
					let receiveMsg = JSON.parse(msg.body);
					if (!context.state.currentSession || receiveMsg.from != context.state.currentSession.username) {
						Notification.info({
							title: '【' + receiveMsg.fromNickname + '】发来一条消息',
							message: receiveMsg.content.length > 10 ,
							? receiveMsg.content.substr(0, 10) + '...' : receiveMsg.content,
							duration: 1000 * 10
						})
						Vue.set(context.state.isDot, context.state.currentAdmin.username + '#' + receiveMsg.from, true);
					}
					receiveMsg.notSelf = true;
					receiveMsg.to = receiveMsg.from;
					context.commit('addMessage', receiveMsg);
				})
			}, error => {})
		},

		// 初始化聊天(admins)列表
		initData(context) {
			context.commit('INIT_DATA');
			getRequest("/chat/admin").then(res => {
				if (res) {
					context.commit('INIT_ADMINS', res);
				}
			})
		}
	}
})

store.watch(function(state) {
	return state.sessions
}, function(val) {
	localStorage.setItem('vue-chat-session', JSON.stringify(val));
}, {
	deep: true /*这个貌似是开启watch监测的判断,官方说明也比较模糊*/
})


export default store;
