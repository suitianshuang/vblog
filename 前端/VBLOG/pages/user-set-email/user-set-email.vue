<template>
	<view class="body">
		<input type="text" v-model="email"
		class="uni-input common-input"
		placeholder="输入需要绑定的邮箱" />
		
		<button class="user-set-btn" 
		:loading="loading" :class="{'user-set-btn-disable':disabled || isbind}" 
		type="primary" @tap="submit" :disabled="disabled || isbind">{{isbind ? "已绑定":"立即绑定"}}</button>
		
	</view>
</template>

<script>
	export default {
		data() {
			return {
				isbind:false,
				email:"",
				disabled:true,
				loading:false
			}
		},
		watch:{
			email(val){
				this.change();
			},
		},
		onLoad(e) {
			if (e.email && e.email !== 'false' && e.email !== 'null') {
				this.email = e.email;
				this.disabled = true;
				this.isbind = true;
			}
		},
		methods: {
			// 监听输入框
			change(){
				if(this.email){
					return this.disabled=false;
				}
				this.disabled=true;
			},
			// 验证层
			check(){
				if(!this.email || this.email==""){
					uni.showToast({
						title: '邮箱不能为空',
						icon:"none"
					});
					return false;
				}
				
				// 验证邮箱格式
				let ePattern = /^([A-Za-z0-9_\-\.])+\@([A-Za-z0-9_\-\.])+\.([A-Za-z]{2,4})$/;
				if(!ePattern.test(this.email)){
					uni.showToast({
						title: '请输入正确邮箱格式',
						icon:"none"
					});
					return false;
				}
				
				return true;
			},
			// 提交
			async submit(){
				if(!this.check()) return; 
				this.loading=this.disabled=true;
				let [err,res] =await this.$http.post('/user/bindemail',{
					email:this.email
				},{
					token:true,
					checkToken:true
				});
				if (!this.$http.errorCheck(err,res)){
					return this.loading=this.disabled=false;
				}
				// 绑定成功
				this.isbind = true;
				this.loading = this.disabled = false;
				// 修改状态，缓存
				this.User.userinfo.email = this.email;
				uni.setStorageSync("userinfo",this.User.userinfo);
				return uni.showToast({
					title: '绑定成功！',
					success: () => {
						uni.navigateBack({ delta: 1 });
					}
				});
			}
		}
	}
</script>

<style>
@import "../../common/form.css";
</style>
