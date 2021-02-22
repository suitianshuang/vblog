<template>
	<view style="overflow: hidden;">
		

		<!-- 文章标题输入框 -->
		<view class="artile-title">
			<input  placeholder="一个吸引人的话题往往能引起更多的共鸣~"></input>
		</view>
		<!-- 多行输入框 正文部分 -->
		<view class="uni-textarea">
			<textarea  placeholder="为你的话题添加描述~" />
			</view>
		<!-- 上传多图 -->
		<!-- <uploud-images @upload="upload" @del='delImageList' :image-list="imglist"></uploud-images> -->
		
	
		
	</view>
</template>

<script>
	
	export default {
		components:{
		
		},
		data() {
			return {
			
			};
		},
		
		
		
		methods:{
			
			// 返回
			back(){
				uni.navigateBack({ delta: 1 });
			},
			// 发布
			async submit(){
				
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
