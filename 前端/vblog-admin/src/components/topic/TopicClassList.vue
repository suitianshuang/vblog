<template>
	<div>
		<el-table :data="topicClassList" style="width: 80%">
			<el-table-column label="日期" width="180">
				<template slot-scope="scope">
					<i class="el-icon-time"></i>
					<span style="margin-left: 10px">{{ scope.row.createTime }}</span>
				</template>
			</el-table-column>
			<el-table-column label="分类名" width="180">
				<template slot-scope="scope">
					<el-popover trigger="hover" placement="top">
						<p>是否可用: {{ scope.row.isenable==1?'可用':'已禁用'}}</p>
						<div slot="reference" class="name-wrapper">
							<el-tag size="medium">{{ scope.row.classifyName }}</el-tag>
						</div>
					</el-popover>
				</template>
			</el-table-column>
			<el-table-column label="操作">
				<template slot-scope="scope">
					<el-button size="mini" style="margin-right: 10px;" @click="editTopicClass(scope.$index, scope.row)">编辑</el-button>
					<el-dialog title="话题分类" :visible.sync="flag" >
						<el-form>
							<el-form-item label="话题分类名" label-width="100px">
								<el-input v-model="editText" autocomplete="off"></el-input>
							</el-form-item>

						</el-form>
						<div slot="footer" class="dialog-footer">
							<el-button @click="dialogFormVisible">取 消</el-button>
							<el-button type="primary" @click="updateTopicClass(scope.$index, scope.row)">确 定</el-button>
						</div>
					</el-dialog>
					<el-popconfirm confirmButtonText='是的' cancelButtonText='算了' icon="el-icon-info" iconColor="red" title="危险操作,确定删除吗？"
					 @onConfirm="handleDelete(scope.$index, scope.row)">
						<el-button style="margin-right: 10px;" size="mini" type="danger" slot="reference">删除 </el-button>
					</el-popconfirm>
					<el-button size="mini" type="warning" @click="changeIsEnable(scope.$index, scope.row)">{{scope.row.isenable==1?'禁用':'解禁'}}</el-button>
				</template>
			</el-table-column>
		</el-table>


	</div>
</template>

<script>
	export default {
		name: 'topicClassList',
		data() {
			return {
				flag: false,
				editText: '',
				topicClassList: []
			}
		},
		mounted() {
			this.initTopicClass();
		},
		methods: {
		 async initTopicClass(){
			 await this.getRequest("/topic/basic/topicclass").then(res=>{
					if(res){
						for (let i = 0; i < res.obj.length; i++) {
							this.topicClassList.push(this.__formData(res.obj[i]));
						}
					}
				})
				
			},
			dialogFormVisible() {
				this.flag = !this.flag;
			},
			editTopicClass(index, data) {
				this.flag = true;
				this.editText = data.classifyName;
			},
			// updateTopicClass(index,data){
			// 	console.log(data);
			// 	// this.postRequest('/topic/basic/',)
			// },
			handleDelete(index, data) {
				this.deleteRequest("/topic/basic/"+data.id).then(res=>{
					this.topicClassList=[],
					this.initTopicClass();
				})
			},
			changeIsEnable(index, data) {
				data.isenable==1?data.isenable=0 : data.isenable =1;
				
				this.postRequest("/topic/basic/",data).then(res=>{
					this.topicClassList=[],
					this.initTopicClass();
				})
			},
			__formData(item){
				return{
					id:item.id,
					createTime:item.createTime,
					classifyName:item.classifyName,
					isenable:item.isenable,
				}
			}
		},

	}
</script>

<style>
</style>
