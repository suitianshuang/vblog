// 网络监听
const NetWork = {
	// 网络状态
	isConnect:false,
	// 监听网络状态
	On(){
		// 获取当前网络状态
		uni.getNetworkType({
			success: (res) => {
				if(res.networkType!=='none'){ this.isConnect=true; return;}
				uni.showToast({
					icon:"none",
					title: '请先连接网络',
				});
			}
		})
		// 监听网络状态变化
		uni.onNetworkStatusChange((res)=>{
			this.isConnect = res.isConnected;
			if(!res.isConnected){
				uni.showToast({
					icon:"none",
					title: '您目前处于断网状态',
				});
			}
		})
	}
}




import config from "./config.js"
export default {
	NetWork
}