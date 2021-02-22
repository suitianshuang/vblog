<template>
	<div>
		<el-table :data="articleList" stripe border v-loading="loading" element-loading-text="拼命加载中" element-loading-spinner="el-icon-loading"
		 element-loading-background="rgba(0, 0, 0, 0.8)" style="width: 100%">
			<el-table-column prop="title" label="文章标题" width="130" fixed="left">
			</el-table-column>
			<el-table-column prop="users.username" label="作者名" width="180">
			</el-table-column>
			<el-table-column prop="createTime" label="创建时间">
			</el-table-column>

			<el-table-column prop="articleClass.classifyName" label="所属分类">
			</el-table-column>
			<el-table-column prop="content" label="正文预览">
			</el-table-column>
			<el-table-column fixed="right" label="操作" width="150">
				<template slot-scope="scope">
					<el-button type="danger" style="padding: 3px;">通过审核</el-button>
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
	export default {
		name: 'CheckArticle',
		data() {
			return {
				articleList: [],
				loading: false,
				total: 0,
				page: 1,
				size: 10,
				
			}
		},
		mounted() {
			this.initArticle();
		},
		methods: {
			lookDetail(){
				this.$router.push('/detail');
			},
			sizeChange(currentSize) {
				this.size = currentSize;
				this.initArticle();
			},
			currentChange(currentPage) {
				this.page = currentPage;
				this.initArticle();
			},
			initArticle() {
				this.loading = true;
				let url = '/article/basic/?page='+this.page+'&size='+this.size;
				this.getRequest(url).then(res => {
					this.loading = false;
					if (res) {
						this.articleList = res.data;
						this.total = res.total;
					}
				})
			}
		},
	}
</script>

<style>
</style>
