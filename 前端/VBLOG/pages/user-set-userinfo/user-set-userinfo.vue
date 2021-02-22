<template>
	<view class="body">
		
		<view class="user-set-userinfo-list u-f-ac u-f-jsb">
			<view>头像</view>
			<view class="u-f-ac" @tap="changeimg">
				<image  :src="userpic" mode="aspectFill" lazy-load></image>
				<view class="icon iconfont icon-bianji1"></view>
			</view>
		</view>
		<view class="user-set-userinfo-list u-f-ac u-f-jsb">
			<view>用户名</view>
			<view class="u-f-ac">
				<input type="text" v-model="username" disabled />
				<!-- <view class="icon iconfont icon-bianji1"></view> -->
			</view>
		</view>
		<view class="user-set-userinfo-list u-f-ac u-f-jsb">
			<view>性别</view>
			<view class="u-f-ac" @tap="changeOne('sex')">
				<view>{{sexArr[sex]}}</view>
				<view class="icon iconfont icon-bianji1"></view>
			</view>
		</view>
		<view class="user-set-userinfo-list u-f-ac u-f-jsb">
			<view>生日</view>
			<picker mode="date" :value="birthday" :start="startDate" :end="endDate" @change="bindDateChange">
			<view class="u-f-ac">
				<view>{{birthday}}</view>
				<view class="icon iconfont icon-bianji1"></view>
			</view>
			</picker>
		</view>
		<view class="user-set-userinfo-list u-f-ac u-f-jsb">
			<view>情感</view>
			<view class="u-f-ac" @tap="changeOne('qg')">
				<view>{{qgArr[qg]}}</view>
				<view class="icon iconfont icon-bianji1"></view>
			</view>
		</view>
		<view class="user-set-userinfo-list u-f-ac u-f-jsb">
			<view>职业</view>
			<view class="u-f-ac" @tap="changeOne('job')" style="font-size: 25upx;">
				<view>{{job}}</view>
				<view class="icon iconfont icon-bianji1"></view>
			</view>
		</view>
		<view class="user-set-userinfo-list u-f-ac u-f-jsb">
			<view>家乡</view>
			<view class="u-f-ac" @tap="showMulLinkageThreePicker">
				<view>{{pickerText}}</view>
				<view class="icon iconfont icon-bianji1"></view>
			</view>
		</view>
		
		<button class="user-set-btn" 
		type="primary" @tap="submit">完成</button>
		
		
		<mpvue-city-picker themeColor="#007AFF" ref="mpvueCityPicker" :pickerValueDefault="cityPickerValueDefault" @onConfirm="onConfirm"></mpvue-city-picker>
		
	</view>
</template>

<script>
	let sexArr=['保密','女','男'];
	let qgArr=['秘密','未婚','已婚','单身贵族'];
	let job=['秘密','IT','老师','医生','工程师','画家'];
	import mpvueCityPicker from "../../components/mpvue-citypicker/mpvueCityPicker.vue";
	import Time from '../../common/time.js';
	export default {
		components:{
			mpvueCityPicker
		},
		data() {
			return {
				userid:"",
				sexArr:sexArr,
				qgArr:qgArr,
				userpic:"",
				username:"",
				sex:0,
				qg:0,
				job:"",
				birthday:"",
				cityPickerValueDefault: [0, 0, 1],
				pickerText: '',
			}
		},
		onLoad() {
			this.userid = this.User.userinfo.id;
			this.userpic =this.User.userinfo.userpic;
			this.username = this.User.userinfo.username;
			this.sex = this.User.userinfo.userinfo.sex || 0;
			this.qg = this.User.userinfo.userinfo.qg || 0;
			this.job = this.User.userinfo.userinfo.job || "请填写";
			this.birthday = this.User.userinfo.userinfo.birthday || "请填写";
			this.pickerText = this.User.userinfo.userinfo.path || "请填写";
			// console.log(this.User.userinfo.userinfo.path);
		},
		onBackPress() {
		  if (this.$refs.mpvueCityPicker.showPicker) {
		  	this.$refs.mpvueCityPicker.pickerCancel();
		    return true;
		  }
		},
		onUnload() {
			if (this.$refs.mpvueCityPicker.showPicker) {
				this.$refs.mpvueCityPicker.pickerCancel()
			}
		},
		computed: {
			startDate() {
				return this.getDate('start');
			},
			endDate() {
				return this.getDate('end');
			}
		},
		methods: {
			// 三级联动
			showMulLinkageThreePicker() {
				this.$refs.mpvueCityPicker.show()
			},
			onConfirm(e) {
				this.pickerText = e.label;
			},
			// 修改生日
			bindDateChange(e) {
				this.birthday = e.target.value
			},
			// 修改头像
			async changeimg(){
				let [err,res] =await uni.chooseImage({
					count:1,
					sizeType:['compressed']
				});
				// 选择失败就return
				if (!res) return;
				// 选择 成功就自动上传 数据库执行的是update方法 因为我们会默认给用户一个头像
				console.log(this.userid);
				uni.showLoading({ title: '上传中...', mask: false });
				let [err2,res2] = await this.$http.upload('/uploadFile',{
					name: 'file',
					filePath: res.tempFilePaths[0],
					formData:{
						id:this.userid
					}
				},{
					token:true,
				});
				
				let data = JSON.parse(res2.data);
				// 上传失败
				if (err2 || data.errorMsg) {
					uni.showToast({ title: data.errorMsg,icon:"none" });
					uni.hideLoading();
					return false;
				}
				// 成功
				uni.hideLoading();
				uni.showToast({ title: '修改头像成功!' });
				this.userpic = data.msg;
				// 修改状态，存储
				this.User.userinfo.userpic = this.userpic;
				uni.setStorageSync("userinfo",this.User.userinfo);
			},
			// 单列选择
			changeOne(val){
				let arr=[];
				switch (val){
					case 'sex':
					arr=sexArr;
						break;
					case 'qg':
					arr=qgArr;
						break;
					case 'job':
					arr=job;
						break;
				}
				uni.showActionSheet({
					itemList: arr,
					success: res => {
						switch (val){
							case 'sex':
							this.sex=res.tapIndex;
								break;
							case 'qg':
							this.qg=res.tapIndex;
								break;
							case 'job':
							this.job=arr[res.tapIndex];
								break;
						}
					},
				});
			},
			// 完成
			async submit(){
				uni.showLoading({ title: '修改中...', mask: false });
				let data = {
					userId:this.userid,
					username:this.username,
					sex:this.sex,
					emotion:this.qg,
					job:this.job,
					birthday:this.birthday,
					hometown:this.pickerText,
					age:Time.gettime.getAgeByBirthday(this.birthday),
				};
				let [err,res] = await this.$http.post('/userinfo/updateMessage',data,{
					token:true,
					checkToken:true
				});
				// 请求失败处理
				if (!this.$http.errorCheck(err,res)) return;
				// 成功
				uni.hideLoading();
				uni.showToast({ title: '修改成功！' });
				// 修改状态，缓存
				this.User.userinfo.userinfo = data;
				uni.setStorageSync('userinfo',this.User.userinfo);
				uni.switchTab({
					url:"../home/home"
				})
			},
			getDate(type) {
				const date = new Date();
				let year = date.getFullYear();
				let month = date.getMonth() + 1;
				let day = date.getDate();

				if (type === 'start') {
					year = year - 60;
				} else if (type === 'end') {
					year = year + 2;
				}
				month = month > 9 ? month : '0' + month;;
				day = day > 9 ? day : '0' + day;
				return `${year}-${month}-${day}`;
			}
		}
	}
</script>

<style>
@import "../../common/form.css";
.user-set-userinfo-list{
	padding: 20upx;
	border-bottom: 1upx solid #F4F4F4;
}
.user-set-userinfo-list>view:first-child{
	font-size: 32upx;
	font-weight: bold;
	color: #9B9B9B;
}
.user-set-userinfo-list>view:last-child>image{
	width: 80upx;
	height: 80upx;
	border-radius: 100%;
}
.user-set-userinfo-list>view:last-child>input{
	text-align: right;
}
.user-set-userinfo-list>view:last-child>view:last-of-type{
	margin-left: 20upx;
	color: #EEEEEE;
}
</style>
