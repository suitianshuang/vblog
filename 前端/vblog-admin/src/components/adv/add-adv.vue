<template>
	<div>
		<div class="adv-el-button">
			<el-button size="medium" type="primary" @click="dialogVisible"><i class="el-icon-plus"></i>
				<span style="margin-right: 3px;">增加一条广告</span>
			</el-button>
		</div>

		<el-dialog title="增加一条广告" :visible.sync="flag">
			<el-form>
				<el-form-item label="广告指向的url" label-width="100px">
					<el-input autocomplete="off"></el-input>
				</el-form-item>
			</el-form>
			<el-upload class="upload-demo" style="margin-left: 100px;" 
			  drag :action="action" >
				<i class="el-icon-upload"></i>
				<div class="el-upload__text">将文件拖到此处，或<em>点击上传</em></div>
				<div class="el-upload__tip" slot="tip">只能上传jpg/png文件，且不超过500kb</div>
			</el-upload>
			<div slot="footer" class="dialog-footer">
				<el-button @click="dialogVisible">取 消</el-button>
				<el-button type="primary" @click="insertAdv">确 定</el-button>
			</div>
		</el-dialog>
	</div>
</template>

<script>
	export default {
		name: 'add-adv',
		data() {
			return {
				flag: false,
				action:"http://localhost:8079"
			}
		},
		methods: {
			
			
			dialogVisible() {
				this.flag = !this.flag;
			},
			insertAdv(){
				this.flage = false;
				console.log(this.fileList);
			}
		},

	}
</script>

<style scoped>
	.adv-el-button {
		width: 100%;
		display: flex;
		justify-content: center;
		margin-bottom: 40px;
	}
</style>
