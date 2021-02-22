import time from './time.js'
export default {

	baseUrl :'http://localhost:8079/',
	// userinfo格式转换
	__formatUserinfo(item) {
		if (item) {
			return {
				
				id: item.id,
				username: item.username,
				userpic: this.baseUrl+item.userpic,
				email: item.email,
				createTime: time.gettime.gettime(item.createTime),
				isenable: item.isenable,
				age: item.userinfo.age,
				sex: item.userinfo.sex==1? '男' : '女',
				emotion: item.userinfo.emotion==1?'单身':'未知',
				job: item.userinfo.job,
				hometown: item.userinfo.hometown,
				birthday: item.userinfo.birthday,
			}
		}
	},
	__Articleformat(item) {
		// 在support对象数组中找到当前用户的type
		// 如果找到了就把type给前端
		console.log(item);
		let supportType = 0;
		if (item.support !== null) {
			for (let i = 0; i < item.support.length; i++) {
				if (item.support[i].userId == this.userinfo.id) {
					supportType = item.support[i].type + 1;
				}
			}
		}

		// 在fens对象数组中找当前用户的id
		// 判断当前用户有没有关注遍历的文章作者
		let isguanzhu = false;
		if (item.users !== null) {
			for (let i = 0; i < item.users.fans.length; i++) {
				if (item.users.fans[i].userId == this.userinfo.id) {
					isguanzhu = true;
				}
			}
		}

		let arr3 = [];
		for (let i = 0; i < item.images.length; i++) {
			arr3[i] = item.images[i]
		}


		return {
			userid: item.users.id,
			userpic: item.users.userpic,
			username: item.users.username,
			isguanzhu: isguanzhu,
			id: item.id,
			title: item.title,
			pageviewNum: item.pageviewNum,
			type: "img", // img:图文,video:视频
			titlepic: item.titlepic,
			content: item.content,
			image: arr3,
			video: false,
			path: item.location,
			kissNum: item.rewardNum,
			// share:!!item.share,
			isaccepted: item.isAccepted,
			infonum: {
				index: (item.support.length > 0) ? supportType : 0, //0:没有操作，1:顶,2:踩；            
				dingnum: item.dingCount,
				cainum: item.caiCount,
			},
			userinfo: {
				id: item.users.userinfo.id,
				userid: item.users.userinfo.userid,
				sex: item.users.userinfo.sex,
				age: item.users.userinfo.age,
				job: item.users.userinfo.job,
				emotion: item.users.userinfo.emotion,
				birthday: item.users.userinfo.birthday,
				hometown: item.users.userinfo.hometown,
			},
			createTime: time.gettime.gettime(item.createTime),
			commentnum: item.commentCount,
			sharenum: item.sharenum,
		}
	},
}
