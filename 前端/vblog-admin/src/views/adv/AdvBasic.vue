<template>
	<div>
		<add-adv></add-adv>
		<template v-for="item in advlists">
			<adv-list :item="item" @delateAdv="delateAdv" @updateAdv="updateAdv"></adv-list>
		</template>
	</div>
</template>
<!-- <img src="../../assets/bgimage/bgimage.jpg" /> -->

<script>
	import advList from '../../components/adv/adv-list.vue';
	import addAdv from '../../components/adv/add-adv.vue';

	export default {
		components: {
			advList,
			addAdv
		},
		name: 'AdvBasic',
		mounted() {
			this.getAdvList();
		},
		data() {
			return {
				advlists: [],
			}
		},
		methods: {

			getAdvList() {
				this.getRequest('/adv/basic/').then(res => {
					if (res) {
						this.advlists = res.obj;
					}
				})
			},
			delateAdv(id) {
				this.$confirm('此操作将永久删除该文件, 是否继续?', '提示', {
					confirmButtonText: '确定',
					cancelButtonText: '取消',
					type: 'warning'
				}).then(() => {
					this.deleteRequest("/adv/basic/" + id).then(res => {
						this.getAdvList();
					})
				}).catch(() => {
					this.$message({
						type: 'info',
						message: '已取消删除'
					});
				});
			},
			updateAdv(item) {
				console.log(item);
				this.postRequest('/adv/basic/', item).then(res => {
					this.getAdvList();
				})
			}
		},
	}
</script>

<style>
	
</style>
