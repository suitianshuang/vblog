<template>
	<view>
		
		<scroll-view id="scrollview" scroll-y :scroll-top="scrollTop" 
		:scroll-with-animation="true"
		:style="{height:style.contentH+'px'}">
			<view class="u-f-ajc chat-load-more" 
			hover-class="chat-load-more-hover"
			@tap="loadmore">{{loadtext}}</view>
			<!-- 聊天列表 -->
			<block v-for="(item,index) in list" :key="index">
				<view class="chat-item">
				<user-chat-list :item="item" :index="index"></user-chat-list>
				</view>
			</block>
		</scroll-view>
		
		<!-- 输入框 -->
		<user-chat-bottom @submit="submit"></user-chat-bottom>
	</view>
</template>

<script>
	import userChatBottom from "../../components/user-chat/user-chat-bottom.vue";
	import time from "../../common/time.js";
	import userChatList from "../../components/user-chat/user-chat-list.vue";
	export default {
		components:{
			userChatBottom,
			userChatList
		},
		data() {
			return {
				scrollTop:0,
				style:{
					contentH:0,
					itemH:0
				},
				list:[],
				page:1,
				loadtext:"点击加载更多"
			};
		},
		onReady() {
			this.getdata();
			this.initdata();
		},
		methods:{
			// 初始化参数
			initdata(){
				try {
					const res = uni.getSystemInfoSync();
					this.style.contentH=res.windowHeight - uni.upx2px(120);
				} catch (e) { }
			},
			// 加载更多
			loadmore(){
				if(this.loadtext!="点击加载更多") return;
				// 修改状态
				this.loadtext="加载中...";
				this.page++;
				this.getdata();
			},
			pageToBottom(isfirst = false){
				let q=uni.createSelectorQuery().in(this);
				let itemH = q.selectAll('.chat-item');
				this.$nextTick(()=>{
					itemH.fields({
						size:true
					},data => {
						if (data) {
							if (isfirst) {
								for (let i = 0; i < data.length; i++) {
									this.style.itemH += data[i].height;
								}
							}else{
								this.style.itemH += data[data.length-1].height;
							}
							this.scrollTop = (this.style.itemH > this.style.contentH) ? this.style.itemH : 0;
						}
					}).exec();
				})
			},
			// 获取用户反馈记录
			async getdata(){
				let url = `/feedbacklist/${this.page}`;
				let [err,res] = await this.$http.get(url,{},{
					token:true,
					checkToken:true
				});
				// 错误处理
				if (!this.$http.errorCheck(err,res)) return;
				// 成功
				let list = res.data.data.list;
				let arr = [];
				for (let i = 0; i < list.length; i++) {
					if (list[i].from_id == this.User.userinfo.id || list[i].to_id == this.User.userinfo.id) {
						let isme = list[i].from_id == this.User.userinfo.id;
						arr.push({
							isme: isme,
							userpic: isme ? this.User.userinfo.userpic :"../../static/demo/userpic/11.jpg",
							type:"text",
							data:list[i].data,
							time:list[i].create_time,
							gstime:time.gettime.getChatTime(list[i].create_time,i>0 ? list[i-1].create_time:0)
						});
					}
				}
				this.loadtext = list.length < 10 ? "没有更多数据了" :"点击加载更多";
				this.list = [...arr,...this.list];
				
				if (this.page === 1 && this.list.length>0) {
					this.pageToBottom(true);
				}
			},
			async submit(data){
				let [err,res] = await this.$http.post('/feedback',{
					data:data
				},{
					token:true,
					checkToken:true
				});
				// 错误处理
				if (!this.$http.errorCheck(err,res)) return;
				// 成功处理
				// 构建数据
				let now=new Date().getTime();
				let gstime = time.gettime.getChatTime(now,this.list.length>0 ? this.list[this.list.length-1].time : 0);
				this.list.push({
					isme:true,
					userpic:this.User.userinfo.userpic,
					type:"text",
					data:data,
					time:now,
					gstime:gstime
				});
				this.pageToBottom();
				uni.showToast({ title: '反馈成功' });
			}
		}
	}
</script>

<style>
.chat-load-more{
	padding: 15upx 0;color: #BBBBBB;
}
.chat-load-more-hover{
	background: #F4F4F4;
}
</style>
