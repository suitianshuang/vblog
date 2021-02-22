<template>
	<view class="body">
		<input type="text" v-model="newPassword"
		class="uni-input common-input" 
		placeholder="输入新密码" />
		
		<input type="text" v-model="renewPassword"
		class="uni-input common-input" 
		placeholder="输入确认密码" />
		
		<button class="user-set-btn" 
		:loading="loading" :class="{'user-set-btn-disable':disabled}" 
		type="primary" @tap="submit" :disabled="disabled">完成</button>
		
	</view>
</template>

<script>
	export default {
		data() {
			return {
				hasPassword:false,
				newPassword:"",
				renewPassword:"",
				disabled:true,
				loading:false,
				email:""
			}
		},
		onLoad(e) {
			this.hasPassword = !!(e.password && e.password !== "false");
			console.log(this.User.userinfo.email);
			this.email = this.User.userinfo.email;
		},
		watch:{
			
			newPassword(val){
				this.change();
			},
			renewPassword(val){
				this.change();
			}
		},
		methods: {
			// 监听输入框
			change(){
				if(this.newPassword && this.renewPassword){
					return this.disabled=false;
				}
				this.disabled=true;
			},
			// 验证层
			check(){
				
				if(!this.newPassword || this.newPassword==""){
					uni.showToast({ title: '新密码不能为空', icon:"none" });
					return false;
				}
				if(!this.renewPassword || this.renewPassword==""){
					uni.showToast({ title: '确认密码不能为空', icon:"none" });
					return false;
				}
				if(this.newPassword !== this.renewPassword){
					uni.showToast({ title: '确认密码和新密码不一致', icon:"none" });
					return false;
				}
				return true;
			},
			// 提交
			async submit(){
				console.log(this.newPassword);
				console.log(this.email);
				if(!this.check()) return;
				this.loading = this.disabled = true;
				let [err,res] = await this.$http.post('/users/updatePassword',{
					
					password:this.newPassword,
					email:this.email
				},{
					token:true,
					checkToken:true
				})
				if(!this.$http.errorCheck(err,res)){
					this.loading = this.disabled = false;
					return;
				}
				// 修改状态，缓存
				this.User.userinfo.password = true;
				uni.setStorageSync('userinfo',this.User.userinfo);
				this.loading = this.disabled = false;
				return uni.showToast({
					title: '修改密码成功！',
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
