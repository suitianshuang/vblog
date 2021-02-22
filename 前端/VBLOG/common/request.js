// 引入配置文件
import config from "./config.js";
import User from "./user.js";
export default{
	config:{
		baseUrl:config.webUrl,
		header:{
			'Content-Type':'application/x-www-form-urlencoded',
			'Content-Type':'application/json;charset=UTF-8',
			
		},
		data: {},
		method: "GET",
		dataType: "json",
	},
	request(options = {}){
		options.header = options.header || this.config.header;
		options.method = options.method || this.config.method;
		options.dataType = options.dataType || this.config.dataType;
		options.url = this.config.baseUrl+options.url;
		// TODO：token增加等操作
		if (options.token) {
			// 验证用户是否登录
			if (!this.checkToken(options.checkToken)) return;
			options.header.token = User.token;
		}
		
		return uni.request(options);
	},
	get(url,data,options={}){
		options.url = url;
		options.data = data;
		options.method = 'GET';
		return this.request(options);
	},
	post(url,data,options={}){
		options.url = url;
		options.data = data;
		options.method = 'POST';
		return this.request(options);
	},
	// 上传图片
	upload(url,options = {}){
		options.url = this.config.baseUrl+url;
		// options.header = options.header;
		options.fileType = options.fileType || "image";
		options.formData = options.formData || {};
		options.filePath = options.filePath;
		options.name = options.name;
		
		if (options.token) {
			// 验证是否登录
			if (!this.checkToken(options.checkToken)) return;
			options.header.token = User.token;
		}

		return uni.uploadFile(options);
	},
	// 错误处理
	errorCheck(err,res,errfun = false,resfun = false){
		if (err) {
			typeof errfun === 'function' && errfun();
			uni.showToast({ title: '加载失败',icon:"none" });
			return false;
		}
		if (res.data.errorMsg) {
			typeof errfun === 'function' && resfun();
			uni.showToast({ title: res.data.errorMsg,icon:"none" });
			return false;
		}
		return true;
	},
	// 验证用户是否登录
	checkToken(checkToken){
		if (checkToken && !User.token) {
			uni.showToast({ title: '请先登录', icon:"none" })
			uni.navigateTo({
				url: '/pages/login/login'
			});
			return false;
		}
		return true;
	},
	
	
	
	
} 