<template>
	<div>
		<el-input placeholder="输入关键字查询用户" v-model="keywords" style="width: 300px; margin-right: 10px;" size="mini"
		 @keydown.enter.native="searchUser">
			<i slot="prefix" class="el-input__icon el-icon-search"></i>
		</el-input>
		<el-button type="primary" size="mini" @click="searchUser">主要按钮</el-button>
		
		<el-table 
		 v-loading="loading"
		    element-loading-text="拼命加载中"
		    element-loading-spinner="el-icon-loading"
		    element-loading-background="rgba(0, 0, 0, 0.8)"
		:data="userinfo" style="width: 80%">
			<el-table-column type="expand">
				<template slot-scope="props">
					<el-form label-position="left" inline class="demo-table-expand">
						<el-form-item label="头像">
							<img :src="props.row.userpic" height="30px" width="30px" style="border-radius: 100%;" />
						</el-form-item>
						<el-form-item label="用户id:">
							<span>{{ props.row.id }}</span>
						</el-form-item>
						<el-form-item label="用户名:">
							<span>{{ props.row.username }}</span>
						</el-form-item>
						<el-form-item label="性别:">
							<span>{{ props.row.sex }}</span>
						</el-form-item>
						<el-form-item label="年龄:">
							<span>{{ props.row.age}}</span>
						</el-form-item>
						<el-form-item label="情感:">
							<span>{{ props.row.emotion }}</span>
						</el-form-item>
						<el-form-item label="工作:">
							<span>{{ props.row.job }}</span>
						</el-form-item>
						<el-form-item label="生日:">
							<span>{{ props.row.birthday }}</span>
						</el-form-item>
						<el-form-item label="家乡:">
							<span>{{ props.row.hometown }}</span>
						</el-form-item>
						<el-form-item label="创建时间:">
							<span>{{ props.row.createTime }}</span>
						</el-form-item>
						<el-form-item label="是否可用:">
							<span>{{ props.row.isenable }}</span>
						</el-form-item>
						
					</el-form>
				</template>
			</el-table-column>
			<el-table-column label="用户 ID" prop="id">
			</el-table-column>
			<el-table-column label="用户名" prop="username">
			</el-table-column>
			<el-table-column label="用户邮箱" prop="email">
			</el-table-column>
			<!-- <el-table-column label="用户头像" prop="email">
				<img src="../../assets/bgimage/bgimage.jpg" height="30px" width="30px" style="border-radius: 100%;" />
			</el-table-column> -->
		</el-table>

	</div>

</template>

<script>
	export default {
		name: "search",
		data() {
			return {
				keywords: '',
				userinfo: [],
				loading:false,
			}
		},
		methods: {
			
			searchUser() {
				this.loading=true;
				this.userinfo = [];
				if (!this.keywords) {
					this.loading=false;
					return this.$message({
						message: '查询条件为空',
						type: 'warning'
					});
				}
				this.getRequest('/users/basic/search/' + this.keywords).then(resp => {
					if (resp) {
						this.loading=false;
						let list = resp.obj;
						for (let i = 0; i < list.length; i++) {
							this.userinfo.push(this.User.__formatUserinfo(list[i]));
						}
					}
				})
			}
		},
	}
</script>

<style>
</style>
