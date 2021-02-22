<template>
	<div>
		<el-table :data="topicList" stripe border v-loading="loading" element-loading-text="拼命加载中" element-loading-spinner="el-icon-loading"
		 element-loading-background="rgba(0, 0, 0, 0.8)" style="width: 100%">
			<el-table-column prop="title" label="话题名" width="130" fixed="left">
			</el-table-column>
			<el-table-column label="话题图片" width="180">
				<template slot-scope="scope">
					<img :src="scope.row.titlepic" height="40px" align="center" width="40px" style="border-radius: 5px;">
				</template>
			</el-table-column>
			<el-table-column label="创建时间" width="150">
				<template slot-scope="scope">
					<el-tag>{{scope.row.createTime}}</el-tag>
				</template>
			</el-table-column>
			<el-table-column prop="description" label="话题描述" width="250">
			</el-table-column>
			<el-table-column prop="isenable" label="是否可用">
			</el-table-column>
			<el-table-column prop="topicClass.classifyName" label="所属话题分类">
			</el-table-column>
			<el-table-column prop="totalNum" label="总参与数量">
			</el-table-column>
			<el-table-column prop="todayNum" label="今日参与数量">
			</el-table-column>
			<el-table-column fixed="right" label="操作" width="150">
				<template slot-scope="scope">
					<el-button type="danger" style="padding: 3px; margin: 0 10px;" @click="lookDetail">禁用</el-button>
					<el-button type="primary" style="padding: 3px;">编辑</el-button>
				</template>
		
			</el-table-column>
		</el-table>
		<div>
			<div style="display: flex; justify-content:center; margin-top: 0.625rem;">
				<el-pagination background @current-change="currentChange" @size-change="sizeChange" layout="sizes, prev, pager, next, jumper, ->, total, slot "
				 :total="total">
				</el-pagination>
			</div>
		</div>
	</div>
</template>

<script>
	import Time from "../../utils/time.js"
	export default{
		name:"topicList",
		data() {
			return {
				topicList:[],
				loading: false,
				total: 0,
				page: 1,
				size: 10,
			}
		},
		mounted() {
			this.initTopicList();
		},
		
		methods: {
			
			sizeChange(currentSize) {
				this.size = currentSize;
				this.initTopicList();
			},
			currentChange(currentPage) {
				this.page = currentPage;
				this.initTopicList();
			},
			initTopicList() {
				this.loading = true;
				let url = '/topic/basic/?page='+this.page+'&size='+this.size;
				this.getRequest(url).then(res => {
					this.loading = false;
					if (res) {
						this.topicList = res.data;
						this.total = res.total;
					}
				})
			},
			
		},
	}
</script>

<style>
</style>
