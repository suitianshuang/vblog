<template>
	<div>
		<el-table :data="userList" style="width: 100%" stripe>
			<el-table-column prop="username" label="用户名" width="180">
			</el-table-column>
			<el-table-column prop="userpic" label="头像" width="180">
			</el-table-column>
			<el-table-column prop="email" label="邮箱">
			</el-table-column>
			<el-table-column prop="sex" label="性别">
			</el-table-column>
			<el-table-column prop="age" label="年龄">
			</el-table-column>
			<el-table-column prop="createTime" label="创建时间">
			</el-table-column>

			<el-table-column>
				<template slot-scope="scope">
					<el-button size="mini" type="danger" @click="handleUser(scope.$index, scope.row)">恢复</el-button>
				</template>
			</el-table-column>
		</el-table>
	</div>
</template>

<script>
	export default {
		name: 'black-list',
		data() {
			return {
				userList: [{
						username: '鸡蛋仔',
						userpic: '',
						email: '1124142@qq.com',
						sex: '男',
						age: '22',
						createTime: '2019-10-10',
					},
					{
						username: '鸡蛋仔',
						userpic: '',
						email: '1124142@qq.com',
						sex: '男',
						age: '22',
						createTime: '2019-10-10',
					},
					{
						username: '鸡蛋仔',
						userpic: '',
						email: '1124142@qq.com',
						sex: '男',
						age: '22',
						createTime: '2019-10-10',
					},
					{
						username: '鸡蛋仔',
						userpic: '',
						email: '1124142@qq.com',
						sex: '男',
						age: '22',
						createTime: '2019-10-10',
					}
				],
			}
		},
		methods: {
			handleUser(index,data) {
				console.log("恢复");
			}
		},
	}
</script>

<style>
</style>
