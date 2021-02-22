<template>
	<div>
		
		<el-tabs type="border-card">
		  <el-tab-pane label="文章管理"><article-basic></article-basic></el-tab-pane>
		  <el-tab-pane label="文章审核"><checkArticle></checkArticle></el-tab-pane>
	
		</el-tabs>
		
		<router-view></router-view>
		
	</div>
</template>

<script>
	import articleBasic from '../../components/article/article-basic.vue';
	import checkArticle from '../../components/article/checkArticle.vue'
	export default {
		name: 'ArticleBasic',
		components: {
			articleBasic,
			checkArticle
		},
		data() {
			return {
				
			}
		},
		
	}
</script>

<style>
</style>
