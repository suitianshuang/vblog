<template>
	<view style="overflow: hidden;">
		<!-- 自定义导航栏 -->
		<uni-nav-bar :statusBar="true" rightText="发布" left-icon="back" @click-left="back" @click-right="submit">
			<view class="u-f-ajc" @tap="changelook">
				{{getType}}
				<view class="icon iconfont icon-xialazhankai"></view>
			</view>
		</uni-nav-bar>
		
		<!-- 文章标题输入框 -->
		<view class="artile-title">
			<input v-model="articleTitle" placeholder="标题~"></input>
		</view>
		<!-- 多行输入框 正文部分 -->
		<view class="uni-textarea">
			<textarea v-model="text" placeholder="正文~" />
		</view>
		<!-- 上传多图 -->
		<uploud-images @upload="upload" @del='delImageList' :image-list="imglist"></uploud-images>
		
		
		<!-- 底部 -->
		<view class="add-input-location u-f-ac u-f-jsb" @tap="choseLocation">
			<view class="icon iconfont icon-dizhi">选择地点</view>
			<view class="icon iconfont icon-yiwen" @tap="hidePopup">{{(kissNum==0)?'选择悬赏金额' : kissNum}}</view>
		</view>
		
		<uni-popup :show="showpopup" position="middle" mode="fixed">
			<view class="gonggao">
				<view class="u-f-ajc">
					<image src="../../static/common/xuanshang.jpg" mode="widthFix"></image>
				</view>
					<view class="add-input-kissNum u-f-ajc" hover-class="add-input-hover-class" @tap="choosekissNum(5)">5贝利</view>
					<view class="add-input-kissNum u-f-ajc" hover-class="add-input-hover-class" @tap="choosekissNum(10)">10贝利</view>
					<view class="add-input-kissNum u-f-ajc" hover-class="add-input-hover-class" @tap="choosekissNum(15)">15贝利</view>
					<view class="add-input-kissNum u-f-ajc" hover-class="add-input-hover-class" @tap="choosekissNum(0)">放弃悬赏</view>
			</view>
		</uni-popup>
		
		<view class="u-f-ac addinput-foot">
			
			<picker class="u-f1 u-f-ajc" mode="selector" :range="postclass.range" @change="changePostClass">
				<view class="u-f1 u-f-ajc">
				{{postclass.title ? postclass.title : "选择分类"}}
				</view>
			</picker>
			
			<view class="u-f1 u-f-ajc" 
			hover-class="addinput-foot-btn"
			@tap="changeTopic">{{topic.title ? topic.title : "选择话题"}}</view>
		</view>
		
	</view>
</template>

<script>
	let changelook= ['仅自己可见','所有人可见'];
	import uniNavBar from "../../components/uni-nav-bar/uni-nav-bar.vue";
	import uploudImages from "../../components/common/uploud-images.vue";
	import uniPopup from "../../components/uni-popup/uni-popup.vue";
	export default {
		components:{
			uniNavBar,
			uploudImages,
			uniPopup
		},
		data() {
			return {
				kissNum:0,
				isget:false,
				showpopup:false,
				yinsi:1,//对应后端的isopen 1 为公开
				articleTitle:"",
				text:"",
				imglist:[],
				imageIdList:[],
				imglistIds:[],
				postclass:{
					id:0,     // 当前选中分类id
					title:"", // 当前选中分类名称
					range:[], // 可选项
					list:[]   // 服务端获取到的分类列表
				},
				titlepicList:[],
				titlepic:"",
				topic:{
					id:0,	  // 当前选中话题id
					title:""  // 当前选中话题名称
				}
			};
		},
		onBackPress() {
			// 如果有值
			if(!this.text && this.imglist.length<1) return;
			if(!this.isget){
				this.baocun();
				return true;
			}
		},
		computed: {
			getType() {
				return changelook[this.yinsi];
			}
		},
		onLoad(e) {
			uni.showToast({
				title: '请勿发布违法信息！',
				icon:"none"
			});
			// 读取缓存
			let res = uni.getStorageSync('addinput');
			if (res) {
				res = JSON.parse(res);
				this.articleTitle = res.articleTitle;
				this.yinsi = res.yinsi;
				this.imglistIds = res.imglistIds || [];
				if (this.imglistIds.length) {
					this.imglist = res.imglist;
				}
				this.text = res.text;
				if (res.postclass) {
					this.postclass = res.postclass;
				}
				if (res.topic) {
					this.topic = res.topic;
				}
			}
			// 获取文章分类
			let postclass = e.postclass ? JSON.parse(e.postclass) : false;
			this.getPostClass(postclass);
			// 监听所属话题选择
			uni.$on('changeTopic',(data)=>{
				this.topic.id = data.id;
				this.topic.title = `#${data.title}#`;
				this.topic.isnewtopic =data.isnewtopic
			})
		},
		methods:{
			choseLocation(){
			
			},
			choosekissNum(kissNum){
				if(kissNum>this.User.counts.kissNum){
					console.log("不足");
					 return uni.showToast({
						title: '贝利不足，多多签到~',
						icon:"none"
					});
				}
				console.log("测试");
				this.kissNum = kissNum;
				this.hidePopup();
			},
			changeTopic(){
				uni.navigateTo({
					url:"/pages/topic-nav/topic-nav?ischange="+true,
				});
			},
			async getPostClass(postclass){
				if (postclass) {
					this.postclass.list = postclass;
					let arr = [];
					for (let i = 0; i < postclass.length; i++) {
						arr.push(postclass[i].name)
					}
					return this.postclass.range = arr;
				}
				try{
					let [err,res] = await this.$http.get('/article/articleClassify');
					console.log(res);
					if (!this.$http.errorCheck(err,res)) return;
					let list = res.data.obj;
					let arr = [] , arr2 = [];
					for (let i = 0; i < list.length; i++) {
						arr.push(list[i].classifyName);
						arr2.push({
							id:list[i].id,
							name:list[i].classifyName
						})
					}
					this.postclass.range = arr;
					// this.postclass.list = list;
					this.postclass.list = arr2;
				}catch(e){
					return;
				}
			},
			changePostClass(e){
				// 当前选中的id
				this.postclass.id = this.postclass.list[e.target.value].id;
				// 当前选中的名称
				this.postclass.title = this.postclass.list[e.target.value].name;
			},
			// 保存为草稿
			baocun(){
				uni.showModal({
					content: '是否要保存为草稿？',
					cancelText: '不保存',
					confirmText: '保存',
					success: res => {
						if(res.confirm){
							// 本地存储
							let obj = {
								articleTitle:this.articleTitle,
								yinsi:this.yinsi,
								text:this.text,
								imglist:this.imglist,
								imglistIds:this.imglistIds,
								postclass:this.postclass,
								topic:this.topic
							};
							uni.setStorageSync('addinput',JSON.stringify(obj))
						}else{
							uni.removeStorage({ key:"addinput" }) // 清除缓存
						}
						this.isget=true;
						uni.navigateBack({
							delta: 1
						});
					},
				});
			},
			// 返回
			back(){
				uni.navigateBack({ delta: 1 });
			},
			// 发布
			async submit(){
				if (!this.postclass.id && !this.topic.title) {
					return uni.showToast({
						title: '文章和话题分类至少选则一个',icon:"none"
					});
				}
				uni.showLoading({ title: '发布中...', mask: true });
				// console.log(JSON.stringify(this.imglistIds));
				// console.log(this.imglistIds.length);
				if(this.imglistIds.length==0){
					  return uni.showToast({
						title: '至少指示一张图片哦~',
						icon:'none'
					});
				}
					for (let i = 0; i <this.imglistIds.length; i++) {
						this.imageIdList[i]=this.imglistIds[i].id;
					}
				
				
				try{
					let [err,res] = await this.$http.post('/article/publish',{
						
						imageIdList: JSON.stringify(this.imageIdList) ,
						userId:this.User.userinfo.id,
						title:this.articleTitle,
						// 找出titlepic数组中第一张图片作为封面图片
						titlepic:this.titlepicList[0].titlepic,
						content:this.text,
						isopen:this.yinsi,
						// 用话题的title进行存储，所以话题的title不可以重复
						topicId:this.topic.id,
						topicTitle:this.topic.title ? this.topic.title : "未选择话题",
						// isNewTopic:this.topic.newtopic? 1 : 0,//如果是新话题就返回1  是就话题就返回0
						articleClassId:this.postclass.id,
						rewardNum:this.kissNum
					},
					{
						header:{
							'Content-Type':'application/x-www-form-urlencoded',
							// 'Content-Type':'application/json;charset=UTF-8',
							token:this.User.userinfo.token
						},
					}
					
					);
					// 发布失败
					if (!this.$http.errorCheck(err,res)) {
						return uni.hideLoading();
					}
					// 发布成功
					uni.hideLoading();
					uni.showToast({
						title: '发布成功！'
					});
					console.log(JSON.stringify(res));
					this.isget = true;
					if(res.data.msg == '话题分类'){
						uni.switchTab({
							url:"../news/news"
						})
					}
					uni.$emit('updateData',{ 
						type:"updateList",
						data:res.data
					});
					uni.$emit('updateData',{
						type:"updatekissNum",
						data:this.kissNum
					});
					uni.navigateBack({ delta: 1 });
					
				}catch(e){
					return;
				}
			},
			// 隐私
			changelook(){
				uni.showActionSheet({
					itemList:changelook,
					success: (res)=> {
						this.yinsi = res.tapIndex
					}
				});
			},
			upload(item){
				console.log(item.url);
				this.imglist.push(item.url);
				this.imglistIds.push({ id:item.id });
				this.titlepicList.push({titlepic:item.url})
			},
			delImageList(index){
				this.imglist.splice(index,1);
				this.imglistIds.splice(index,1);
				this.titlepicList.splice(index,1)
			},
			hidePopup(){
				this.showpopup=!this.showpopup;
			}
		}
	}
</script>

<style>
.artile-title{
	border:0;
	font-size: 20upx;
	/* height: 100upx; */
	padding: 10upx 10upx;
	margin: 10upx 10upx;
}
.uni-textarea{
	border: 1upx solid #EEEEEE;
}
.gonggao{
	width: 500upx;
}
.gonggao image{
	width: 75%;
	margin-bottom: 20upx;
}
.gonggao button{
	margin-top: 20upx;
	background: #FFE934;
	color: #171606;
}
.addinput-foot{
	background: #141414;
	color: #eeeeee;
	position: fixed;bottom: 0;left: 0;right: 0;height: 88upx;border-top:1upx solid #DDDDDD
}
.addinput-foot view{
	height: 100%;
}
.addinput-foot-btn{
	background: #F4F4F4;
}
.add-input-location{
	color: #4a4a4a;
	font-size: 30upx;
	padding: 20upx 20upx;
}
.add-input-kissNum{
	font-size: 30upx;
	padding: 10upx 0;
	border-bottom: 1upx #555048;
	background: #e2d5c1;
	border-radius: 10upx;
	
}
.add-input-hover-class{
	background: #cecece;
	border-radius: 10upx;
}
</style>
