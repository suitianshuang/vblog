<template>
	<div>
		<el-tabs v-model="activeName" type="card" >
		    <el-tab-pane label="话题分类列表" name="first">
				<topic-class-list></topic-class-list>
			</el-tab-pane>
		    <el-tab-pane label="话题列表" name="second">
				<topic-list></topic-list>
			</el-tab-pane>
		    <el-tab-pane label="待审核话题" name="third">
				<check-topic></check-topic>
			</el-tab-pane>
		  </el-tabs>
	</div>
</template>

<script>
	import topicClassList from '../../components/topic/TopicClassList.vue';
	import topicList  from '../../components/topic/topic-list.vue';
	import checkTopic from '../../components/topic/check-topic.vue';
	export default{
		name:'TopicBasic',
		components: {
			topicClassList,
			topicList,
			checkTopic
		},
		data() {
			return {
				 activeName: 'first'
			}
		},
	}
</script>

<style>
</style>
