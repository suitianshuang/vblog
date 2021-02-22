<template>
	<div>

		<div style="display: flex; justify-content:space-around">
			<div>
				<el-input :disabled="showSearchView" v-model="keyWords" size="mini" prefix-icon="el-icon-search" placeholder="按Enter搜索用户名..." @clear="doSearch"
				 clearable @keydown.enter.native="doSearch" style="width: 300px; margin-right: 10px;"></el-input>
				<el-button size="mini" type="primary" @click="doSearch" :disabled="showSearchView">搜索</el-button>
				<el-button size="mini" type="primary" :icon="showSearchView?'el-icon-sort-up':'el-icon-sort-down'" @click="showSearchView = !showSearchView">高级搜索</el-button>
			</div>
			<div>
				<!-- <el-button size="mini" type="success" icon="el-icon-document-add">导入</el-button> -->
				<el-button size="mini" type="success" icon="el-icon-printer" @click="exportData">导出</el-button>
			</div>
		</div>
		 <transition name="slide-fade">
			<div v-show="showSearchView" style="width: 90%; border-radius: 10px; border: 1px solid;border-color: #007cba; margin-top: 10px;">
				<div style="margin: 10px; display: flex;justify-content: space-around;">
					<el-row>
						用户名：<el-input size="mini" style="width: 250px;" placeholder="用户名..." v-model="searchValue.username"></el-input>
					</el-row>
					<el-row>
						邮箱：<el-input size="mini" style="width: 250px;" placeholder="邮箱..." @blur="checkSearch" v-model="searchValue.email"></el-input>
					</el-row>
					<el-row>
						<el-radio v-model="searchValue.sex" label="1">男</el-radio>
						<el-radio v-model="searchValue.sex" label="0">女</el-radio>
						<el-radio v-model="searchValue.sex" label="2">未知</el-radio>
					</el-row>
					<el-row>
						<el-switch style="display: block" v-model="searchValue.isenable" active-color="#13ce66" inactive-color="#ff4949"
						 active-text="未禁用" inactive-text="已禁用">
						</el-switch>
					</el-row>
				</div>
				<div>
					<div style="margin: 10px; display: flex; justify-content:space-between">
						<el-row style="margin: 0 10px;">
							情感状态:
							<el-select size="mini" v-model="searchValue.emotion" placeholder="请选择">
								<el-option v-for="item in searchValue.emotionGroup" :key="item.value" :label="item.label" :value="item.value">
								</el-option>
							</el-select>
						</el-row>
						<el-row>
							创建时间范围:
							<el-date-picker size="mini" value-format="yyyy-MM-dd" v-model="searchValue.createTime" type="daterange" align="right" unlink-panels
							 range-separator="至" start-placeholder="开始日期" end-placeholder="结束日期" :picker-options="pickerOptions">
							</el-date-picker>
						</el-row>
						
						<el-row>
							年龄：<el-input size="mini" style="width: 250px;" placeholder="年龄..." @blur="checkSearch" v-model="searchValue.age"></el-input>
						</el-row>
						<el-row>
							<el-button size="mini" @click="cancelSearch" >取消</el-button>
							<el-button size="mini" type="primary" icon="el-icon-search" @click="initUsers('moreSearch')">搜索</el-button>
						</el-row>


					</div>
				</div>

			</div>

		</transition>
		<div style="margin-top: 20px;">
			<el-table :data="users" stripe border style="width: 100%" v-loading="loading" element-loading-text="拼命加载中"
			 element-loading-spinner="el-icon-loading" element-loading-background="rgba(0, 0, 0, 0.8)">
				<el-table-column type="selection" width="55">
				</el-table-column>
				<el-table-column prop="username" label="用户名" width="180" fixed="left">
				</el-table-column>
				<el-table-column>
					<template slot-scope="scope">
						<img :src="scope.row.userpic" height="20px" align="center" width="20px" style="border-radius: 20px;">
					</template>
				</el-table-column>
				<el-table-column prop="email" label="邮箱" width="190">
				</el-table-column>
				<el-table-column prop="createTime" label="创建时间" width="180">
				</el-table-column>
				<el-table-column label="性别">
					<template slot-scope="scope">
						<el-tag>
							{{scope.row.sex}}
						</el-tag>
					</template>
				</el-table-column>
				<el-table-column prop="age" label="年龄">
				</el-table-column>
				<el-table-column prop="emotion" label="情感">
				</el-table-column>
				<el-table-column prop="job" label="工作" width="180">
				</el-table-column>
				<el-table-column prop="birthday" label="生日" width="180">
				</el-table-column>
				<el-table-column prop="hometown" label="家乡" width="180">
				</el-table-column>
				<el-table-column fixed="right" label="操作" width="250">

					<template slot-scope="scope">
						<el-button type="primary" style="padding: 3px; margin: 0 10px;">更多</el-button>
						<el-button type="primary" style="padding: 3px;">备注</el-button>
						<template v-if="scope.row.isenable==1">
							<el-popconfirm title="禁用后该账号将不能使用,确认禁用？" @onConfirm="updateEnable(scope.row.id,0)">
								<el-button slot="reference" type="danger" style="padding: 3px; margin-left: 10px;">禁用</el-button>
							</el-popconfirm>
						</template>
						<template v-if="scope.row.isenable==0">
							<el-popconfirm title="解除禁用后该账号可以继续使用,确认解除？" @onConfirm="updateEnable(scope.row.id,1)">
								<el-button slot="reference" type="success" style="padding: 3px; margin-left: 10px;">解除禁用</el-button>
							</el-popconfirm>
						</template>

					</template>

				</el-table-column>
			</el-table>
			<div style="display: flex; justify-content:center; margin-top: 0.625rem;">
				<el-pagination background @current-change="currentChange" @size-change="sizeChange" layout="sizes, prev, pager, next, jumper, ->, total, slot "
				 :total="total">
				</el-pagination>
			</div>
		</div>
	</div>
</template>
，
<script>
	export default {
		components: {

		},
		name: 'UsersBasic',
		data() {
			return {
				pickerOptions: {
					disabledDate(time) {
						return time.getTime() > Date.now();
					},
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
				showSearchView: false,
				searchValue: {
					username:null,
					email:null,
					age: null,
					sex: null,
					isenable: true,
					createTime: '',
					emotion: null,
					emotionGroup: [{
						value: '0',
						label: '单身'
					}, {
						value: '1',
						label: '已婚'
					}, {
						value: '2',
						label: '未知'
					},
					{
						value: null,
						label: '不选择'
					}],
				},
				radio: "",
				users: [],
				loading: false,
				total: 0,
				page: 1,
				size: 10,
				keyWords: '',
			}
		},
		mounted() {
			this.initUsers();
		},
		methods: {
			checkSearch(){
				//Email正则
				let ePattern = /^([A-Za-z0-9_\-\.])+\@([A-Za-z0-9_\-\.])+\.([A-Za-z]{2,4})$/;
				//正整数正则
				let posPattern = /^\d+$/;
				//输出 true
				if(!ePattern.test(this.searchValue.email)&&this.searchValue.email!=null){
					this.$message.warning("这不是一个正确的邮箱!");
				}
				if(!posPattern.test(this.searchValue.age)&&this.searchValue.age){
					this.$message.warning("这不是一个正确的年龄!");
				}
				
			},
			cancelSearch(){
				this.showSearchView = !this.showSearchView;
				return this.searchValue ={
					age: '',
					sex: "",
					isenable: true,
					email:'',
					createTime: '',
					emotion: "",
					emotionGroup: [{
						value: '0',
						label: '单身'
					}, {
						value: '1',
						label: '已婚'
					}, {
						value: '2',
						label: '未知'
					}],
				}
			},
			exportData() {
				window.open('/users/basic/export', '_parent');
			},
			doSearch() {
				this.page = 1;
				this.initUsers();
			},
			sizeChange(currentSize) {
				this.size = currentSize;
				this.initUsers();
			},
			currentChange(currentPage) {
				this.page = currentPage;
				this.initUsers();
			},
			initUsers(type) {
				this.users = [];
				this.loading = true;
				console.log(this.searchValue.isenable);
				let enabled = 1;
				if(!this.searchValue.isenable){
					enabled = 0;
				}
			
				
				let url = '/users/basic/?page=' + this.page + '&size=' + this.size;
				if(type =='moreSearch'){
					if(this.searchValue.age){
						url = url+'&age='+this.searchValue.age
					}
					if(this.searchValue.sex){
						url = url+'&sex='+this.searchValue.sex
					}
					
						url = url+'&isenable='+enabled;
					
					if(this.searchValue.emotion){
						url = url+'&emotion='+this.searchValue.emotion
					}
					if(this.searchValue.username){
						url = url+'&username='+this.searchValue.username
					}
					if(this.searchValue.createTime){
						url = url+'&createTime='+this.searchValue.createTime
					}
				}else{
					url += '&username=' + this.keyWords;
				}
				console.log(url);
				this.getRequest(url).then(res => {
					this.loading = false;
					if (res) {
						this.total = res.total;
						let list = [];
						list = res.data;
						for (let i = 0; i < list.length; i++) {
							this.users.push(this.User.__formatUserinfo(list[i]));
						}
						// this.users = res.data;
					}
				})
			},
			updateEnable(id, isenable) {
				console.log(id + ">>>" + isenable);
				this.putRequest("/users/basic/?id=" + id + "&isenable=" + isenable).then(res => {
					if (res) {
						this.initUsers();
					}
				})
			}
		},
	}
</script>

<style>
	/* 可以设置不同的进入和离开动画 */
	/* 设置持续时间和动画函数 */
	.slide-fade-enter-active {
	  transition: all .8s ease;
	}
	.slide-fade-leave-active {
	  transition: all .8s cubic-bezier(1.0, 0.5, 0.8, 1.0);
	}
	.slide-fade-enter, .slide-fade-leave-to
	/* .slide-fade-leave-active for below version 2.1.8 */ {
	  transform: translateX(10px);
	  opacity: 0;
	}
</style>
