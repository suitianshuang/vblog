<template>
	<div>
		<div class="block">
			<!-- <img :src="item.prcture" height="200px" width="300px" style="margin-bottom: 10px; border-radius: 10px;" /> -->
			<img src="../../assets/bgimage/bgimage.jpg" height="200px" width="300px" style="margin-bottom: 10px; border-radius: 10px;" />
			<div>
				<!-- <ul>创建时间{{item.createTime}}</ul>
					<ul>指向url{{item.url}}</ul> -->
				<thead>
					<tr>
						<th>创建时间</th>
						<th>指向url</th>
					</tr>
				</thead>
				<tbody>
					<tr>
						<td>{{item.createTime}}</td>
						<td>{{item.url}}</td>
						<td>
							<el-button size="mini" type="warning" @click="dialogFormVisible">修改</el-button>
						</td>
						<td>
							<el-button size="mini" type="danger" @click="delateAdv">删除</el-button>
						</td>
					</tr>

					<el-dialog title="修改广告信息" :visible.sync="flag">
						<el-form>
							<el-form-item label="创建时间" label-width="100px">
								<!-- <el-input v-model="item.createTime" autocomplete="off"></el-input> -->
								<el-date-picker v-model="item.createTime" type="datetime" placeholder="选择日期时间" align="right" :picker-options="pickerOptions">
								</el-date-picker>
							</el-form-item>
							<el-form-item label="指向url" label-width="100px">
								<el-input v-model="item.url" autocomplete="off"></el-input>
							</el-form-item>
						</el-form>
						<div slot="footer" class="dialog-footer">
							<el-button @click="dialogFormVisible">取 消</el-button>
							<el-button type="primary" @click="updateAdv">确 定</el-button>
						</div>
					</el-dialog>
				</tbody>
			</div>
		</div>
	</div>
</template>

<script>
	export default {
		name: 'adv-list',
		props: {
			item: Object,
		},
		data() {
			return {
				flag: false,
				pickerOptions: {
					shortcuts: [{
						text: '今天',
						onClick(picker) {
							picker.$emit('pick', new Date());
						}
					}, {
						text: '昨天',
						onClick(picker) {
							const date = new Date();
							date.setTime(date.getTime() - 3600 * 1000 * 24);
							picker.$emit('pick', date);
						}
					}, {
						text: '一周前',
						onClick(picker) {
							const date = new Date();
							date.setTime(date.getTime() - 3600 * 1000 * 24 * 7);
							picker.$emit('pick', date);
						}
					}]
				},

			}
		},
		methods: {
			dialogFormVisible() {
				this.flag = !this.flag;
			},
			delateAdv(){
				this.$emit("delateAdv",this.item.id)
			},
			updateAdv(){
				this.flag = false;
				this.$emit("updateAdv",this.item);
			}
			
		},
	}
</script>

<style scoped>
	.block {
		display: flex;
		align-items: center;
		justify-content: center;
		flex-direction: column
	}

	td {
		text-align: right;
		padding: 9px 9px 20px 20px;
	}

	.dialog-input {
		margin: 0 10px;
		width: 100px;
	}
	
	/* table {
	    table-layout: fixed;
	    white-space: nowrap;
	}
	
	td {
	    overflow: hidden;
	    text-overflow: ellipsis;
	} */
</style>
