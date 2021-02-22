/*
Navicat MySQL Data Transfer

Source Server         : localhost
Source Server Version : 50515
Source Host           : localhost:3306
Source Database       : vblog

Target Server Type    : MYSQL
Target Server Version : 50515
File Encoding         : 65001

Date: 2020-06-10 11:52:36
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for admins
-- ----------------------------
DROP TABLE IF EXISTS `admins`;
CREATE TABLE `admins` (
  `id` int(50) NOT NULL AUTO_INCREMENT,
  `name` varchar(255) COLLATE utf8_unicode_ci DEFAULT NULL,
  `adminpic` varchar(150) COLLATE utf8_unicode_ci DEFAULT NULL,
  `enabled` tinyint(1) DEFAULT '1',
  `username` varchar(20) COLLATE utf8_unicode_ci DEFAULT NULL,
  `password` varchar(255) CHARACTER SET utf8 DEFAULT NULL,
  `phone` varchar(100) COLLATE utf8_unicode_ci DEFAULT NULL,
  `address` varchar(30) COLLATE utf8_unicode_ci DEFAULT NULL,
  `email` varchar(50) COLLATE utf8_unicode_ci DEFAULT NULL,
  `mark` varchar(100) COLLATE utf8_unicode_ci DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=8 DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

-- ----------------------------
-- Records of admins
-- ----------------------------
INSERT INTO `admins` VALUES ('1', 'superAdmin', '詹姆斯1.jpg', '1', 'admin', '$2a$10$0pDO6meRRyICNQk84X5fRuXJiEFTpAfpAznA.T5cUuZxgG6w1.7R6', '138891456532', '辽宁省东港市111', 'tianshuangsui@gmail.com', '我有着本系统给最大权限啊1');
INSERT INTO `admins` VALUES ('2', '文章管理员', '杜兰特.jpg', '1', 'articleAdmin', '$2a$10$A47gWgVXbSxF8c8qNnOw0.5yL/zVpjKJ.AJnhfrNw7NLkbyoMIhFy', '12345678910', '辽宁省东港市', 'tianshuangsui@gmail.com', '我是文章管理员');
INSERT INTO `admins` VALUES ('3', '话题管理员', '库里.jpg', '1', 'topicAdmin', '$2a$10$A47gWgVXbSxF8c8qNnOw0.5yL/zVpjKJ.AJnhfrNw7NLkbyoMIhFy', '12345678910', '辽宁省东港市', 'tianshuangsui@gmail.com', null);
INSERT INTO `admins` VALUES ('4', '数据库管理员', '林书豪.jpg', '1', 'dbAdmin', '$2a$10$A47gWgVXbSxF8c8qNnOw0.5yL/zVpjKJ.AJnhfrNw7NLkbyoMIhFy', '12345678910', '辽宁省东港市', 'tianshuangsui@gmail.com', null);
INSERT INTO `admins` VALUES ('5', '广告管理员', '科比.jpg', '1', 'advAdmin', '$2a$10$A47gWgVXbSxF8c8qNnOw0.5yL/zVpjKJ.AJnhfrNw7NLkbyoMIhFy', '12345678910', '辽宁省东港市', 'tianshuangsui@gmail.com', null);
INSERT INTO `admins` VALUES ('6', '系统管理员', '欧文.jpg', '1', 'sysAdmin', '$2a$10$A47gWgVXbSxF8c8qNnOw0.5yL/zVpjKJ.AJnhfrNw7NLkbyoMIhFy', '12345678910', '辽宁省东港市', 'tianshuangsui@gmail.com', null);
INSERT INTO `admins` VALUES ('7', '用户管理员', '姚明.jpg', '1', 'usersAdmin', '$2a$10$A47gWgVXbSxF8c8qNnOw0.5yL/zVpjKJ.AJnhfrNw7NLkbyoMIhFy', '12345678910', '辽宁省东港市', 'tianshuangsui@gmail.com', null);

-- ----------------------------
-- Table structure for admin_role
-- ----------------------------
DROP TABLE IF EXISTS `admin_role`;
CREATE TABLE `admin_role` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `admin_id` int(11) DEFAULT NULL,
  `rid` int(11) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=91 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of admin_role
-- ----------------------------
INSERT INTO `admin_role` VALUES ('1', '1', '1');
INSERT INTO `admin_role` VALUES ('6', '6', '6');
INSERT INTO `admin_role` VALUES ('7', '1', '2');
INSERT INTO `admin_role` VALUES ('8', '1', '3');
INSERT INTO `admin_role` VALUES ('9', '1', '4');
INSERT INTO `admin_role` VALUES ('10', '1', '5');
INSERT INTO `admin_role` VALUES ('11', '1', '6');
INSERT INTO `admin_role` VALUES ('13', '1', '7');
INSERT INTO `admin_role` VALUES ('14', '7', '5');
INSERT INTO `admin_role` VALUES ('15', '7', '6');
INSERT INTO `admin_role` VALUES ('77', '3', '3');
INSERT INTO `admin_role` VALUES ('85', '5', '5');
INSERT INTO `admin_role` VALUES ('86', '2', '12');
INSERT INTO `admin_role` VALUES ('87', '2', '2');
INSERT INTO `admin_role` VALUES ('88', '2', '6');
INSERT INTO `admin_role` VALUES ('89', '4', '4');
INSERT INTO `admin_role` VALUES ('90', '4', '5');

-- ----------------------------
-- Table structure for advertising
-- ----------------------------
DROP TABLE IF EXISTS `advertising`;
CREATE TABLE `advertising` (
  `id` int(2) NOT NULL AUTO_INCREMENT COMMENT '广告id',
  `picture` varchar(200) COLLATE utf8_unicode_ci DEFAULT NULL COMMENT '广告封面',
  `url` varchar(200) COLLATE utf8_unicode_ci DEFAULT NULL COMMENT '广告跳转地址',
  `type` int(2) DEFAULT NULL COMMENT '广告在页面中存在的类型 0动态轮播图 1个人中心',
  `create_time` date DEFAULT NULL COMMENT '创建时间',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=7 DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci COMMENT='广告表';

-- ----------------------------
-- Records of advertising
-- ----------------------------
INSERT INTO `advertising` VALUES ('4', '广告图1.jpeg', 'https://m.bilibili.com/index.html', '0', '2020-03-31');
INSERT INTO `advertising` VALUES ('5', '绿色黑板.jpg', 'https://m.bilibili.com/index.html', '0', '2020-05-20');
INSERT INTO `advertising` VALUES ('6', '广告图4.jpeg', 'https://m.bilibili.com/index.html', '0', '2020-06-05');

-- ----------------------------
-- Table structure for article
-- ----------------------------
DROP TABLE IF EXISTS `article`;
CREATE TABLE `article` (
  `id` int(10) NOT NULL AUTO_INCREMENT COMMENT '文章表',
  `userid` int(10) DEFAULT NULL COMMENT '关联用户表id',
  `title` varchar(40) COLLATE utf8_unicode_ci DEFAULT NULL COMMENT '文章标题',
  `titlepic` varchar(200) COLLATE utf8_unicode_ci DEFAULT NULL COMMENT '文章封面图片',
  `content` longtext COLLATE utf8_unicode_ci COMMENT '文章内容',
  `sharenum` int(4) DEFAULT '0' COMMENT '文章被分享次数',
  `location` varchar(20) COLLATE utf8_unicode_ci DEFAULT NULL COMMENT '发布文章时用户所位置',
  `type` int(1) DEFAULT '0' COMMENT '类型 0图文 1视频',
  `create_time` int(11) DEFAULT NULL COMMENT '文章创建时间,使用时间戳',
  `pageview_num` int(10) DEFAULT '0' COMMENT '浏览量',
  `article_class_id` int(2) DEFAULT '1' COMMENT '文章分类id',
  `share_id` int(10) DEFAULT '0' COMMENT '关联文章表',
  `isopen` int(1) DEFAULT '1' COMMENT '0仅自己可见 1开放',
  `ding_count` int(5) DEFAULT '0' COMMENT '顶的数量',
  `cai_count` int(5) DEFAULT '0' COMMENT '点踩数',
  `comment_count` int(3) DEFAULT '0' COMMENT '评论数',
  `topic_id` int(10) DEFAULT NULL COMMENT '关联话题表的id',
  `collectionnum` int(10) DEFAULT '0' COMMENT '收藏数',
  `rewardnum` int(5) DEFAULT '0' COMMENT '文章悬赏金额',
  `isaccepted` int(2) DEFAULT '0' COMMENT '是否采纳过别的评论，0未采纳，1采纳过',
  `isenable` int(2) DEFAULT '1',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=190 DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci COMMENT='文章表';

-- ----------------------------
-- Records of article
-- ----------------------------
INSERT INTO `article` VALUES ('124', '20', 'NBA于八月一日复赛', '15913571560269da521a4-bccb-486c-b038-4399f60b89de.jpg', 'NBA紧随CBA开赛', '0', '辽宁-丹东-东港', '0', '1591357157', '0', '0', '0', '1', '0', '0', '0', '25', '0', '0', '0', '1');
INSERT INTO `article` VALUES ('125', '20', '美国种族歧视问题加剧', '1591357762384e88297b5-b9f2-4380-b604-674040f03040.jpeg', '美国已经有22个州进行游行示威活动', '0', '辽宁-丹东-东港', '0', '1591357789', '0', '0', '0', '1', '0', '0', '0', '28', '0', '0', '0', '1');
INSERT INTO `article` VALUES ('126', '1', '非洲大地上的生灵啊', '159151798468607949be4-4fbb-4ffb-b4ed-ce734af2d63b.jpg', '这次我去了非洲一个月的时间..你去过非洲吗', '0', '辽宁-丹东-东港', '0', '1591518080', '0', '8', '0', '1', '2', '0', '1', '34', '0', '5', '0', '1');
INSERT INTO `article` VALUES ('127', '1', '今天去了大连的动物园看到了好多种类的猴子！！！', '159151815222713751fdc-640e-4c21-94cd-beeb15abaca1.jpg', 'today is happy！喜欢动物吗？？？', '0', '辽宁-丹东-东港', '0', '1591518235', '0', '8', '0', '1', '1', '0', '0', '35', '0', '5', '0', '1');
INSERT INTO `article` VALUES ('128', '1', '我要毕业了，建筑大学的时光总是短暂的', '1591518257485875ed599-13c9-41a5-9da9-b9aae4f638bb.jpg', '时光荏苒、岁月如梭，四年时间终于结束了，真的很不舍的这里！', '0', '辽宁-丹东-东港', '0', '1591518346', '0', '2', '0', '1', '1', '0', '2', '36', '0', '15', '0', '1');
INSERT INTO `article` VALUES ('129', '5', '建筑大学的一角', '15915184820549e4d6a2c-b80a-4da7-a9a4-13703cc82861.jpg', '这里是匠人的摇篮，在这里数不尽的心酸往事，但回首看来都是值得经历的！', '0', '辽宁-丹东-东港', '0', '1591518565', '0', '2', '0', '1', '1', '0', '1', '36', '0', '0', '0', '1');
INSERT INTO `article` VALUES ('130', '5', '学长们在做比赛', '1591518578081c38035d8-8547-4849-b757-8ef3af69545e.jpg', '模型真的很奇怪，可惜我不懂！你们明白吗？？？', '0', '辽宁-丹东-东港', '0', '1591518642', '0', '2', '0', '1', '0', '1', '1', '37', '0', '15', '0', '1');
INSERT INTO `article` VALUES ('131', '5', '曾经数次的在这里散步，真希望还能回去走走。', '159151865737855e3db60-1bdd-42bc-ad15-94a28d6d54e4.jpg', '这里是建筑大学最宁静的地方之一，可以放松身心。', '0', '辽宁-丹东-东港', '0', '1591518720', '0', '2', '0', '1', '1', '0', '0', '36', '0', '0', '0', '1');
INSERT INTO `article` VALUES ('132', '5', '文字就是手指上的艺术', '1591519207706eeeecba7-f7b1-4570-9f9f-8545dc8d0b79.jpg', '中华文化博大精深！', '0', '辽宁-丹东-东港', '0', '1591519268', '0', '7', '0', '1', '1', '0', '0', '38', '0', '0', '0', '1');
INSERT INTO `article` VALUES ('133', '5', '这是怎么做到的？', '15915192890016d5ef9a4-6929-4a63-b960-dc7b2ee5a4db.jpg', '什么也不懂的我瑟瑟发抖。', '0', '辽宁-丹东-东港', '0', '1591519379', '0', '6', '0', '1', '0', '0', '0', '39', '0', '0', '0', '1');
INSERT INTO `article` VALUES ('134', '5', '大爱梅西', '1591519404661bda16204-a746-4449-8aac-e4cb497b74ab.jpg', '里奥·梅西(Lionel Messi)，1987年6月24日出生于阿根廷圣菲省罗萨里奥市，阿根廷足球运动员，司职前锋，现效力于巴塞罗那足球俱乐部。', '0', '辽宁-丹东-东港', '0', '1591519446', '0', '5', '0', '1', '0', '0', '0', '40', '0', '0', '0', '1');
INSERT INTO `article` VALUES ('135', '5', '梅西好像被世界杯诅咒了', '15915194796901a4dc71e-fe5a-4c71-8dd1-8de5c2df8a2d.jpg', '2014年，梅西随阿根廷队参加2014年巴西世界杯，最终获得世界杯亚军 [2]  ；12月20日，梅西被IFFHS评为2013年世界职业联赛的最佳射手奖 [3]  。2015年8月，当选欧洲超级杯最佳球员 [4]  ；8月27日，获得欧洲最佳球员 [5]  。2016年1月12日', '0', '辽宁-丹东-东港', '0', '1591519512', '0', '2', '0', '1', '0', '0', '1', '0', '0', '0', '0', '1');
INSERT INTO `article` VALUES ('136', '5', '2017年11月24日，梅西领取了2016-17赛季的欧洲金靴奖', '15915195355160091f2ec-1fec-4dbd-b531-f172f05584c6.jpg', '2017年11月24日，梅西领取了2016-17赛季的欧洲金靴奖；11月25日，巴萨官方和梅西续约到2021年 [7]  。2018年9月，入选国际足联年度最佳阵容 [8-9]  。2019年9月24日，梅西第六次当选世界足球先生 [10]  ；12月，梅西生涯第六次获得金球奖。', '0', '辽宁-丹东-东港', '0', '1591519573', '0', '2', '0', '1', '1', '0', '1', '0', '0', '0', '0', '1');
INSERT INTO `article` VALUES ('137', '5', '世界十大最丑建筑之一', '15915195958501d230eea-7238-41f3-add5-8d9113eb680b.jpg', '没想到...', '0', '辽宁-丹东-东港', '0', '1591519649', '0', '1', '0', '1', '4', '3', '0', '0', '0', '0', '0', '1');
INSERT INTO `article` VALUES ('138', '5', '沈阳奥体中心夜景', '1591519671198905f378e-40bf-41fe-8f03-96706561626d.jpg', '奥体中心综合训练馆一层篮球馆曾是国家体育总局训练基地，国家队的训练场所，交通便利，三片标准篮球场，暖气开放，免费淋浴。场馆可用于球队训练，篮球比赛，各种篮球训练营，小型车展，新闻发布会，见面会，企业年会，各类大型文艺演出等\Z', '0', '丹东市', '0', '1591519713', '0', '1', '0', '1', '8', '2', '0', '0', '0', '0', '0', '1');
INSERT INTO `article` VALUES ('139', '5', '100年前的沈阳', '1591519730439d0c8eab6-b266-4dd3-b251-eaf58729e217.jpg', '沈阳，简称“沈”，别称盛京、奉天，是辽宁省省会、副省级市、特大城市、沈阳都市圈核心城市，国务院批复确定的中国东北地区重要的中心城市、先进装备制造业基地', '0', '丹东市', '0', '1591519783', '0', '1', '0', '1', '2', '0', '0', '0', '0', '0', '0', '1');
INSERT INTO `article` VALUES ('140', '5', '沈阳最高建筑', '1591519809089bcdea025-553a-442a-af05-44e3e42582f2.jpg', '沈阳中街一日游', '0', '丹东市', '0', '1591519846', '0', '1', '0', '1', '2', '0', '2', '0', '0', '0', '0', '1');
INSERT INTO `article` VALUES ('141', '5', 'USA最近乱了', '159151986875829fc4530-9663-4759-a9f0-daf83c624f42.jpg', '弗洛伊德事件持续发酵，“元芳”你怎么看？？？', '0', '丹东市', '0', '1591519933', '0', '1', '0', '1', '3', '0', '0', '28', '0', '0', '0', '1');
INSERT INTO `article` VALUES ('142', '2', '非洲草原', '15915200265259b3d6fce-664c-483c-bebc-031a7bac0561.jpg', '...美', '0', '丹东市', '0', '1591520062', '0', '1', '0', '1', '1', '0', '0', '0', '0', '0', '0', '1');
INSERT INTO `article` VALUES ('143', '2', '呼伦贝尔大草原', '15915201033574d584dfd-0a80-472b-8d7f-3235a9b932d6.jpg', '爱了爱了', '0', '丹东市', '0', '1591520136', '0', '1', '0', '1', '1', '0', '0', '0', '0', '0', '0', '1');
INSERT INTO `article` VALUES ('144', '2', '这才是依山傍水', '15915201595290a8a1254-6b48-49e4-a82c-b6f37b9fa756.jpg', '羡慕遮掩的生活', '0', '丹东市', '0', '1591520209', '0', '1', '0', '1', '1', '0', '0', '0', '0', '0', '0', '1');
INSERT INTO `article` VALUES ('145', '2', '油画', '15915204088203500fd48-d59a-48af-8e64-a08995498807.jpg', '油画（an oil painting；a painting in oils）是以用快干性的植物油（亚麻仁油、罂粟油、核桃油等）调和颜料，在画布亚麻布，纸板或木板上进行制作的一个画种。作画时使用的稀释剂为挥发性的松节油和干性的亚麻仁油等。画面所附着的颜料有较强的硬度，当画面干燥后，', '0', '丹东市', '0', '1591520422', '0', '1', '0', '1', '1', '0', '0', '0', '0', '0', '0', '1');
INSERT INTO `article` VALUES ('146', '2', '油画的发展在19世纪有了新的趋向', '15915204496072e2baa3a-9e0d-4ed6-a02d-143d43e4e24c.jpg', '在20世纪油画中，由不同的艺术观念形成了不同的流派，并制约艺术形式呈现多种倾向，传统油画技法中的某方面因素往往作为艺术观念的形式体现被强化，甚至被推向极端，油画形式语言受到高度重视。例如：忽视色彩而主要作形体自由构造的立体主义；注重色彩强烈', '0', '丹东市', '0', '1591520524', '0', '1', '0', '1', '1', '0', '0', '0', '0', '0', '0', '1');
INSERT INTO `article` VALUES ('147', '2', 'new york city', '159152054981702949157-dad1-4b8d-b391-0105e717bcac.jpg', 'new york city \nNew York panorama', '0', '丹东市', '0', '1591520605', '0', '1', '0', '1', '1', '0', '0', '0', '0', '0', '0', '1');
INSERT INTO `article` VALUES ('148', '2', '每天都走的一条路', '15915206314052a6a06c6-77a5-41a4-a560-b923f2e7b2d0.jpg', '建筑大学一角', '0', '丹东市', '0', '1591520674', '0', '1', '0', '1', '1', '0', '0', '0', '0', '0', '0', '1');
INSERT INTO `article` VALUES ('149', '2', '建大图书馆', '15915207541612f32138b-9b4e-4929-8c1c-6e0622544293.jpg', '期末图书馆人太多了', '0', '丹东市', '0', '1591520809', '0', '1', '0', '1', '1', '0', '0', '36', '0', '0', '0', '1');
INSERT INTO `article` VALUES ('150', '2', '建筑大学的钓鱼台', '159152082718924ffc020-1e03-4f48-b656-ccd16f517914.jpg', '哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈', '0', '丹东市', '0', '1591520864', '0', '1', '0', '1', '1', '0', '0', '0', '0', '0', '0', '1');
INSERT INTO `article` VALUES ('151', '2', '建大稻田', '1591520885041f62dc6c0-9e56-42fa-bff5-caf339909ae7.jpg', '建大稻田米飘香', '0', '丹东市', '0', '1591520933', '0', '1', '0', '1', '0', '1', '0', '0', '0', '0', '0', '1');
INSERT INTO `article` VALUES ('152', '2', '建筑大学B馆', '1591520945436a0a145c3-64f0-414d-a6ad-edebe7b1da3a.jpg', '转角遇到爱？？？', '0', '丹东市', '0', '1591520990', '0', '1', '0', '1', '1', '0', '1', '0', '0', '0', '0', '1');
INSERT INTO `article` VALUES ('153', '2', '雷霆庭院！！', '1591521013399150d9a50-5fe1-4ee6-98cd-2142f16deb79.jpg', 'good！', '0', '丹东市', '0', '1591521037', '0', '1', '0', '1', '0', '1', '2', '0', '0', '0', '0', '1');
INSERT INTO `article` VALUES ('154', '2', '建筑大学小路', '1591521055818844df23d-d74d-4d4c-b63b-23bb80674c04.jpg', '...huibuqile ', '0', '丹东市', '0', '1591521078', '0', '1', '0', '1', '0', '1', '0', '0', '0', '0', '0', '1');
INSERT INTO `article` VALUES ('155', '2', '2020年6月7日17:12:07', '1591521100508a199ff72-0521-41ed-a9b3-7c949553a1d9.jpg', '再见建大', '0', '丹东市', '0', '1591521146', '0', '1', '0', '1', '1', '0', '0', '0', '0', '0', '0', '1');
INSERT INTO `article` VALUES ('156', '2', '青铜器', '15915211934455cf83d64-072d-42d2-898b-479b10e1e0fd.jpg', '青铜器', '0', '丹东市', '0', '1591521218', '0', '1', '0', '1', '1', '0', '0', '0', '0', '0', '0', '1');
INSERT INTO `article` VALUES ('157', '2', '建筑大学欢迎你！	·~', '15915213177741f1f09dd-091d-404c-83f4-511eb0126611.jpg', '建大医院', '0', '丹东市', '0', '1591521340', '0', '1', '0', '1', '1', '0', '1', '0', '1', '0', '0', '1');
INSERT INTO `article` VALUES ('158', '8', '缅怀先烈们', '1591521443209edb5db6c-d2af-4bf9-84f5-84ab8cc20e1e.jpg', '...', '0', '丹东市', '0', '1591521485', '0', '9', '0', '1', '1', '0', '0', '0', '1', '0', '0', '1');
INSERT INTO `article` VALUES ('159', '8', '沈阳棋盘山', '15915218434656c2063d4-6130-4b55-a6ad-dab907dea950.jpg', '。。。', '0', '丹东市', '0', '1591521869', '0', '1', '0', '1', '1', '0', '1', '0', '4', '0', '0', '1');
INSERT INTO `article` VALUES ('160', '8', '\ZLife lies in movemen', '1591531353736a2fe431b-9f69-4948-aa22-723735084a26.jpg', 'Life lies in movemen', '0', '丹东市', '0', '1591531417', '0', '6', '0', '1', '0', '0', '0', '0', '0', '0', '0', '1');
INSERT INTO `article` VALUES ('161', '8', '没有买卖就没有杀害', '159153262018450d32eec-6713-4dbf-aeb6-42d62f106386.jpg', '保护非洲野生动物！！！', '0', '丹东市', '0', '1591532664', '0', '7', '0', '1', '1', '0', '0', '0', '0', '0', '0', '1');
INSERT INTO `article` VALUES ('162', '8', '美国游行示威活动持续进行中', '159153298888644ca93fd-a914-484d-b685-ba2f3fa0688f.jpg', '...', '0', '丹东市', '0', '1591533069', '0', '9', '0', '1', '1', '0', '0', '67', '0', '0', '0', '1');
INSERT INTO `article` VALUES ('163', '8', '动物世界里的母爱', '15915339577777c856c8a-283f-4c7d-a6ce-b43890239092.jpg', '...', '0', '丹东市', '0', '1591533995', '0', '8', '0', '1', '1', '0', '0', '0', '0', '0', '0', '1');
INSERT INTO `article` VALUES ('164', '3', '国家博物馆馆藏', '1591535533397b23ba4e1-f0df-47da-8617-7b9e54acf72a.jpg', '犀牛头', '0', '丹东市', '0', '1591535568', '0', '7', '0', '1', '0', '0', '0', '0', '0', '0', '0', '1');
INSERT INTO `article` VALUES ('165', '3', '怎样的美景怎么能不让人向往', '1591536109978417422e0-eead-42a2-ae52-1e9f82ec7578.jpg', '~~~', '0', '丹东市', '0', '1591536134', '0', '9', '0', '1', '1', '0', '1', '0', '0', '0', '0', '1');
INSERT INTO `article` VALUES ('166', '3', '测试', '欧文.jpg', '测试', '0', '丹东市', '0', '1591580435', '0', '22', '0', '1', '1', '0', '1', '0', '0', '0', '0', '1');
INSERT INTO `article` VALUES ('167', '1', '发一张自己的自拍！！！', '15915859552073b83492e-4000-4e2e-bfe3-f818d1c14f52.jpg', '···', '0', '丹东市', '0', '1591585972', '0', '5', '0', '1', '0', '0', '1', '0', '0', '0', '0', '1');
INSERT INTO `article` VALUES ('168', '1', '推荐部落冲突', '15916070078359b7c8b94-754f-4bb4-92ad-bda13c8473f0.jpg', '部落冲突是一款塔防类型的竞技游戏。', '0', '丹东市', '0', '1591607045', '0', '5', '0', '1', '1', '0', '0', '0', '0', '0', '0', '1');
INSERT INTO `article` VALUES ('169', '31', '雷电巨龙的伤害好高啊！！！', '159162236578692144eb8-171e-45f8-91ec-eaa2b98a1c5d.jpg', 'yes yes ！！！', '0', '丹东市', '0', '1591622400', '0', '0', '0', '1', '0', '0', '0', '74', '0', '0', '0', '1');
INSERT INTO `article` VALUES ('170', '31', '特朗普出大事了！', '1591627432511d01c573a-bebd-4254-bc83-e1de87b501aa.jpg', '···特朗普居然说伊拉克没有恐怖武器', '0', '丹东市', '0', '1591627502', '0', '0', '0', '1', '0', '0', '0', '75', '0', '0', '0', '1');
INSERT INTO `article` VALUES ('171', '31', '战争是永无止境的硝烟', '1591627691542209638de-7c76-48d2-90b5-ea7fd156911d.jpg', '战争是无止境的硝烟', '0', '丹东市', '0', '1591627770', '0', '4', '0', '1', '1', '0', '0', '67', '0', '0', '0', '1');
INSERT INTO `article` VALUES ('172', '31', '测试不参与话题1', '159162807107718345751-359d-4143-b0fa-e059a59d3ca1.jpg', '测试', '0', '丹东市', '0', '1591628102', '0', '3', '0', '1', '0', '0', '0', '0', '0', '0', '0', '1');
INSERT INTO `article` VALUES ('173', '31', '虎式坦克是二战时期德国最恐怖的武器之一！', '159162926485354750904-e298-40a3-b7eb-8dc209226c15.jpg', '虎式坦克重20吨', '0', '丹东市', '0', '1591629326', '0', '17', '0', '1', '0', '0', '0', '0', '0', '0', '0', '1');
INSERT INTO `article` VALUES ('175', '1', '战争是永无止境的硝烟', '1591670193561edae3e7b-3eb2-478c-a0c6-bb1a4aa250a4.jpg', '···', '0', '丹东市', '0', '1591670234', '0', '21', '0', '1', '0', '0', '0', '0', '0', '0', '0', '1');
INSERT INTO `article` VALUES ('176', '1', '美丽沈阳欢迎您！！', '1591678693300b4e1b12a-4863-4ab5-9d72-dbe539aa62b9.jpg', '沈阳市是辽宁省的省会城市！！', '0', '丹东市', '0', '1591678725', '0', '3', '0', '1', '0', '0', '0', '0', '0', '0', '0', '1');
INSERT INTO `article` VALUES ('177', '1', '美国隐形战机，仅生产20架', '1591678740440c94b3043-6ca8-4add-9daf-45506825aaaf.jpg', '20亿美元的价格只有usa能承受了', '0', '丹东市', '0', '1591678787', '0', '4', '0', '1', '0', '0', '0', '0', '0', '0', '0', '1');
INSERT INTO `article` VALUES ('178', '1', '我的自拍', '159167884633062c7d224-faff-450b-b59a-f19dbd207054.jpg', '2020年6月9日13:01:04', '0', '丹东市', '0', '1591678866', '0', '10', '0', '1', '0', '0', '0', '0', '0', '0', '0', '1');
INSERT INTO `article` VALUES ('179', '1', '生命在于运动', '15916788896921d8e1103-9c21-419c-87a6-554a7e91068f.jpg', '...硬拉教学', '0', '丹东市', '0', '1591678914', '0', '11', '0', '1', '0', '0', '0', '0', '0', '0', '0', '1');
INSERT INTO `article` VALUES ('180', '1', '极限运动是我不敢涉足的领域', '1591678969120bfc941bb-58af-40e7-ac57-f2b8b82c8eea.jpg', '太吓人了！！！', '0', '丹东市', '0', '1591678995', '0', '12', '0', '1', '0', '0', '0', '0', '0', '0', '0', '1');
INSERT INTO `article` VALUES ('181', '1', '跑车布加迪太好看了', '1591679037438cfafd7c1-684d-4491-9b34-94d6542ecef0.jpg', '...可是买不起', '0', '丹东市', '0', '1591679066', '0', '13', '0', '1', '0', '0', '0', '0', '0', '0', '0', '1');
INSERT INTO `article` VALUES ('182', '1', '永葆年轻是人们的愿望', '15916790951956224ed92-a4b7-4a15-8dbf-148b251c5821.jpg', '保证睡眠质量和每日饮水最重要！！！', '0', '丹东市', '0', '1591679148', '0', '14', '0', '1', '0', '0', '0', '0', '0', '0', '0', '1');
INSERT INTO `article` VALUES ('183', '1', '科技改变生活！！', '1591679180319b39f47a3-00db-4bf9-89a3-21c14ccac1dd.jpg', '金融领域的科学计算~', '0', '丹东市', '0', '1591679204', '0', '15', '0', '1', '0', '0', '0', '0', '0', '0', '0', '1');
INSERT INTO `article` VALUES ('184', '1', '不学习和咸鱼有什么区别??', '15916792363925cc6e7cc-beed-4f36-8ae7-04cfee83d854.jpg', '生命在于学习', '0', '丹东市', '0', '1591679266', '0', '16', '0', '1', '0', '0', '0', '0', '0', '0', '0', '1');
INSERT INTO `article` VALUES ('185', '1', '我最喜欢的一本书平凡世界推荐给大家！', '1591679316355dd3a151a-6d7a-447a-bd44-7854267b3bfc.jpg', '平凡世界真的不错！', '0', '丹东市', '0', '1591679355', '0', '17', '0', '1', '0', '0', '1', '0', '0', '0', '0', '1');
INSERT INTO `article` VALUES ('186', '1', '潘长江也太逗了', '1591679391885dd9ef052-ed25-4f20-9d7d-2e294a125996.jpg', '长在了笑点上....', '0', '丹东市', '0', '1591679416', '0', '19', '0', '1', '0', '0', '0', '0', '0', '0', '0', '1');
INSERT INTO `article` VALUES ('187', '1', '伊拉克战争是美国发起的不公平战争', '15916794518944003e8d9-8820-483e-9759-2dac6a7f9bde.jpg', '', '0', '丹东市', '0', '1591679474', '0', '21', '0', '1', '0', '0', '2', '0', '0', '0', '0', '1');
INSERT INTO `article` VALUES ('188', '1', '测试', '1591682562296874bd68e-24f7-4c83-9d63-ed82e26fa1c8.jpg', '', '0', null, '0', '1591682578', '0', '2', '0', '1', '0', '0', '0', '0', '0', '0', '0', '0');
INSERT INTO `article` VALUES ('189', '5', '很喜欢极限运动！！', '1591755407526f7ab90a6-31c6-4dad-80ad-1f3a6fdd857f.jpg', '喜欢极限运动的联系我.', '0', null, '0', '1591755522', '0', '12', '0', '1', '0', '0', '0', '77', '0', '0', '0', '0');

-- ----------------------------
-- Table structure for article_class
-- ----------------------------
DROP TABLE IF EXISTS `article_class`;
CREATE TABLE `article_class` (
  `id` int(2) NOT NULL AUTO_INCREMENT COMMENT '文章分类id',
  `classify_name` varchar(10) COLLATE utf8_unicode_ci DEFAULT NULL COMMENT '分类名称',
  `create_time` date DEFAULT NULL COMMENT '创建时间',
  `isenable` int(1) DEFAULT NULL COMMENT '是否可用',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=23 DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci COMMENT='文章分类表';

-- ----------------------------
-- Records of article_class
-- ----------------------------
INSERT INTO `article_class` VALUES ('1', '推荐', '2020-03-11', '1');
INSERT INTO `article_class` VALUES ('2', '大学', '2020-03-05', '1');
INSERT INTO `article_class` VALUES ('3', '沈阳', '2020-03-04', '1');
INSERT INTO `article_class` VALUES ('4', '国外', '2020-03-11', '1');
INSERT INTO `article_class` VALUES ('5', '人物', '2020-03-10', '1');
INSERT INTO `article_class` VALUES ('6', '油画', '2020-03-19', '1');
INSERT INTO `article_class` VALUES ('7', '艺术', '2020-06-07', '1');
INSERT INTO `article_class` VALUES ('8', '动物', '2020-06-07', '1');
INSERT INTO `article_class` VALUES ('9', '风景', '2020-06-07', '1');
INSERT INTO `article_class` VALUES ('10', '自拍', '2020-06-07', '1');
INSERT INTO `article_class` VALUES ('11', '健身', '2020-06-07', '1');
INSERT INTO `article_class` VALUES ('12', '极限', '2020-06-07', '1');
INSERT INTO `article_class` VALUES ('13', '汽车', '2020-06-07', '1');
INSERT INTO `article_class` VALUES ('14', '美容', '2020-06-07', '1');
INSERT INTO `article_class` VALUES ('15', '科技', '2020-06-07', '1');
INSERT INTO `article_class` VALUES ('16', '学习', '2020-06-07', '1');
INSERT INTO `article_class` VALUES ('17', '读书', '2020-06-07', '1');
INSERT INTO `article_class` VALUES ('18', '趣闻', '2020-06-07', '1');
INSERT INTO `article_class` VALUES ('19', '明星', '2020-06-07', '1');
INSERT INTO `article_class` VALUES ('20', '游戏', '2020-06-08', '1');
INSERT INTO `article_class` VALUES ('21', '战争', '2020-06-09', '1');
INSERT INTO `article_class` VALUES ('22', '测试', '2020-06-09', '1');

-- ----------------------------
-- Table structure for blacklist
-- ----------------------------
DROP TABLE IF EXISTS `blacklist`;
CREATE TABLE `blacklist` (
  `id` int(10) NOT NULL AUTO_INCREMENT COMMENT '黑名单',
  `black_id` int(10) DEFAULT NULL COMMENT '被拉黑用户的id',
  `user_id` int(10) DEFAULT NULL COMMENT '用户id',
  `create_time` varchar(100) COLLATE utf8_unicode_ci DEFAULT NULL COMMENT '时间',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci COMMENT='黑名单表';

-- ----------------------------
-- Records of blacklist
-- ----------------------------

-- ----------------------------
-- Table structure for chatmsg
-- ----------------------------
DROP TABLE IF EXISTS `chatmsg`;
CREATE TABLE `chatmsg` (
  `id` int(10) NOT NULL AUTO_INCREMENT COMMENT 'id',
  `from_id` int(10) DEFAULT NULL COMMENT '消息的发送人id，对应users表中的id',
  `from_username` varchar(20) COLLATE utf8_unicode_ci DEFAULT NULL COMMENT '消息发送人的用户名',
  `to_id` int(10) DEFAULT NULL COMMENT '接收人id',
  `to_name` varchar(20) COLLATE utf8_unicode_ci DEFAULT NULL COMMENT '要发给的人，对应users表中的username',
  `from_userpic` varchar(150) COLLATE utf8_unicode_ci DEFAULT NULL COMMENT '发送人的头像',
  `type` varchar(10) COLLATE utf8_unicode_ci DEFAULT NULL COMMENT '发送数据的类型',
  `data` varchar(200) COLLATE utf8_unicode_ci DEFAULT NULL COMMENT '发送的数据',
  `time` int(11) DEFAULT NULL COMMENT '发送时间的时间戳',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=34 DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci COMMENT='未读消息存储';

-- ----------------------------
-- Records of chatmsg
-- ----------------------------
INSERT INTO `chatmsg` VALUES ('8', '5', '瓦基丽武神', '21', '测试用户', 'http://10.0.2.2:8081/1586169663364f39a7ea9-5daa-4038-88e2-6be2d3cc222a.jpeg', 'text', 'what  is your name？', '1589101795');
INSERT INTO `chatmsg` VALUES ('17', '1', '李秀丽', '8', '皮卡超人', 'http://10.0.2.2:8081/eaa9ecf5-0524-4a8b-a6ed-420a3f164788.jpg', 'text', '在吗', '1591606308');
INSERT INTO `chatmsg` VALUES ('18', '1', '李秀丽', '8', '皮卡超人', 'http://10.0.2.2:8081/eaa9ecf5-0524-4a8b-a6ed-420a3f164788.jpg', 'text', '你好皮卡超人', '1591606379');
INSERT INTO `chatmsg` VALUES ('21', '1', '李秀丽', '29', '系统超级管理员', 'http://10.0.2.2:8081/eaa9ecf5-0524-4a8b-a6ed-420a3f164788.jpg', 'text', '你好', '1591606764');
INSERT INTO `chatmsg` VALUES ('25', '1', '李秀丽', '3', '野蛮人之王', 'http://10.0.2.2:8081/eaa9ecf5-0524-4a8b-a6ed-420a3f164788.jpg', 'text', '你的毕业论文写完了吗就开始上网？', '1591680292');
INSERT INTO `chatmsg` VALUES ('26', '1', '李秀丽', '8', '皮卡超人', 'http://10.0.2.2:8081/eaa9ecf5-0524-4a8b-a6ed-420a3f164788.jpg', 'text', '你到底是男的还是女的！！！', '1591680311');
INSERT INTO `chatmsg` VALUES ('27', '1', '李秀丽', '29', '系统超级管理员', 'http://10.0.2.2:8081/eaa9ecf5-0524-4a8b-a6ed-420a3f164788.jpg', 'text', '超级管理员你好！', '1591680341');
INSERT INTO `chatmsg` VALUES ('32', '1', '李秀丽', '27', '系统管理员', 'http://10.0.2.2:8081/eaa9ecf5-0524-4a8b-a6ed-420a3f164788.jpg', 'text', '你好', '1591682636');

-- ----------------------------
-- Table structure for collection
-- ----------------------------
DROP TABLE IF EXISTS `collection`;
CREATE TABLE `collection` (
  `id` int(10) NOT NULL AUTO_INCREMENT COMMENT '收藏表id',
  `article_id` int(10) DEFAULT NULL COMMENT '收藏文章id',
  `user_id` int(10) DEFAULT NULL COMMENT '收藏人id',
  `isenable` int(1) DEFAULT '1' COMMENT '是否可用,1可用，0 不可用',
  `create_time` varchar(11) COLLATE utf8_unicode_ci DEFAULT NULL COMMENT '创建时间',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=12 DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci COMMENT='收藏表';

-- ----------------------------
-- Records of collection
-- ----------------------------
INSERT INTO `collection` VALUES ('1', '93', '20', '1', '1585964649');
INSERT INTO `collection` VALUES ('2', '94', '20', '1', '1585965260');
INSERT INTO `collection` VALUES ('3', '95', '1', '1', '1585965321');
INSERT INTO `collection` VALUES ('4', '97', '1', '1', '1585965739');
INSERT INTO `collection` VALUES ('5', '98', '3', '1', '1586869558');
INSERT INTO `collection` VALUES ('6', '159', '3', '1', '1591536234');
INSERT INTO `collection` VALUES ('7', '159', '3', '1', '1591537973');
INSERT INTO `collection` VALUES ('8', '158', '3', '1', '1591540277');
INSERT INTO `collection` VALUES ('9', '159', '1', '1', '1591669982');
INSERT INTO `collection` VALUES ('10', '159', '1', '1', '1591676764');
INSERT INTO `collection` VALUES ('11', '157', '1', '1', '1591676776');

-- ----------------------------
-- Table structure for comment
-- ----------------------------
DROP TABLE IF EXISTS `comment`;
CREATE TABLE `comment` (
  `id` int(10) NOT NULL AUTO_INCREMENT COMMENT '评论表id',
  `user_id` int(10) DEFAULT NULL COMMENT '评论人的id',
  `parent_id` int(2) DEFAULT '0' COMMENT '父评论的id  如果是0 就说明是一级评论',
  `comment_num` int(3) DEFAULT '0' COMMENT '这条评论的回复数',
  `message` varchar(30) COLLATE utf8_unicode_ci DEFAULT NULL COMMENT '评论信息',
  `create_time` int(11) DEFAULT NULL COMMENT '评论时间',
  `article_id` int(10) DEFAULT NULL COMMENT '关联文章id',
  `uuid` varchar(50) COLLATE utf8_unicode_ci DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=58 DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci COMMENT='评论信息表';

-- ----------------------------
-- Records of comment
-- ----------------------------
INSERT INTO `comment` VALUES ('37', '5', '0', '0', 'beautiful ！！', '1591516446', '94', null);
INSERT INTO `comment` VALUES ('38', '1', '0', '0', '2020年6月7日16:22:05我去了非洲', '1591518131', '126', null);
INSERT INTO `comment` VALUES ('39', '1', '0', '0', '你们是什么大学的？？？', '1591518374', '128', null);
INSERT INTO `comment` VALUES ('40', '1', '0', '0', '你喜欢你学校吗', '1591518391', '128', null);
INSERT INTO `comment` VALUES ('41', '1', '0', '0', '我就是标准日本语的女主角！！！', '1591591950', '167', null);
INSERT INTO `comment` VALUES ('42', '1', '0', '0', '沈阳的棋盘山真的很美啊', '1591606667', '159', null);
INSERT INTO `comment` VALUES ('43', '1', '0', '0', '测试一条回复数据', '1591606724', '166', null);
INSERT INTO `comment` VALUES ('44', '5', '0', '0', '建大的医院真的很复古', '1591668786', '157', null);
INSERT INTO `comment` VALUES ('45', '5', '0', '1', '我也想去这里，一直没有时间！', '1591668819', '140', null);
INSERT INTO `comment` VALUES ('46', '5', '45', '0', '哈哈哈哈', '1591668843', '140', null);
INSERT INTO `comment` VALUES ('47', '5', '0', '1', '哈喽啊', '1591669023', '153', null);
INSERT INTO `comment` VALUES ('48', '5', '47', '0', 'hello', '1591669034', '153', null);
INSERT INTO `comment` VALUES ('49', '5', '0', '0', '建筑大学太美了', '1591669048', '152', null);
INSERT INTO `comment` VALUES ('50', '1', '0', '0', '你好', '1591669097', '165', null);
INSERT INTO `comment` VALUES ('51', '2', '0', '0', '是啊', '1591753652', '187', null);
INSERT INTO `comment` VALUES ('52', '2', '0', '0', '太对了', '1591753656', '187', null);
INSERT INTO `comment` VALUES ('53', '2', '0', '0', '路遥知马力日久见人心', '1591753784', '185', null);
INSERT INTO `comment` VALUES ('54', '1', '0', '0', '我也不懂', '1591755207', '130', null);
INSERT INTO `comment` VALUES ('55', '1', '0', '0', '啊', '1591755236', '129', null);
INSERT INTO `comment` VALUES ('56', '1', '0', '0', '哈哈哈', '1591755252', '136', null);
INSERT INTO `comment` VALUES ('57', '5', '0', '0', '太帅了', '1591755279', '135', null);

-- ----------------------------
-- Table structure for fans
-- ----------------------------
DROP TABLE IF EXISTS `fans`;
CREATE TABLE `fans` (
  `id` int(10) NOT NULL AUTO_INCREMENT COMMENT '粉丝id',
  `follow_id` int(10) DEFAULT NULL COMMENT '被关注人的id',
  `user_id` int(10) DEFAULT NULL COMMENT '关注人的id',
  `create_time` mediumtext COLLATE utf8_unicode_ci COMMENT '关注的时间',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=116 DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci COMMENT='粉丝表';

-- ----------------------------
-- Records of fans
-- ----------------------------
INSERT INTO `fans` VALUES ('69', '20', '20', '2020-04-01 13:19:52');
INSERT INTO `fans` VALUES ('75', '1', '4', '2020-04-06 19:54:44');
INSERT INTO `fans` VALUES ('77', '1', '1', '2020-04-12 11:02:27');
INSERT INTO `fans` VALUES ('89', '5', '5', '2020-04-12 13:20:45');
INSERT INTO `fans` VALUES ('91', '1', '3', '2020-04-13 21:16:13');
INSERT INTO `fans` VALUES ('95', '20', '3', '2020-04-16 13:46:24');
INSERT INTO `fans` VALUES ('98', '3', '1', '2020-04-16 13:55:41');
INSERT INTO `fans` VALUES ('101', '5', '1', '2020-05-09 10:15:31');
INSERT INTO `fans` VALUES ('102', '2', '1', '2020-05-10 19:35:02');
INSERT INTO `fans` VALUES ('104', '20', null, '2020-05-10 20:15:02');
INSERT INTO `fans` VALUES ('105', '2', null, '2020-05-10 20:15:30');
INSERT INTO `fans` VALUES ('107', '2', null, '2020-05-10 20:23:44');
INSERT INTO `fans` VALUES ('108', '1', '5', '2020-05-11 16:26:32');
INSERT INTO `fans` VALUES ('110', '20', '5', '2020-06-07 15:52:25');
INSERT INTO `fans` VALUES ('111', '2', '2', '2020-06-07 16:54:30');
INSERT INTO `fans` VALUES ('112', '8', '8', '2020-06-07 17:18:31');
INSERT INTO `fans` VALUES ('113', '2', '8', '2020-06-07 17:47:39');
INSERT INTO `fans` VALUES ('114', '8', '3', '2020-06-08 11:04:35');
INSERT INTO `fans` VALUES ('115', '8', '1', '2020-06-09 12:29:36');

-- ----------------------------
-- Table structure for image
-- ----------------------------
DROP TABLE IF EXISTS `image`;
CREATE TABLE `image` (
  `id` int(10) NOT NULL AUTO_INCREMENT COMMENT '图片表id',
  `url` varchar(100) COLLATE utf8_unicode_ci DEFAULT NULL COMMENT '图片地址',
  `user_id` int(10) DEFAULT NULL COMMENT '用户id（关联用户表）',
  `create_time` date DEFAULT NULL COMMENT '创建时间',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=347 DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci COMMENT='图片表';

-- ----------------------------
-- Records of image
-- ----------------------------
INSERT INTO `image` VALUES ('192', '15857122770933cacc721-c106-42ac-a2c1-662474045657.jpg', '20', '2020-04-01');
INSERT INTO `image` VALUES ('193', '1585717719594bd8bf9d5-fed6-49d7-8753-18012a0c48e7.png', '20', '2020-04-01');
INSERT INTO `image` VALUES ('194', '1585717745835909f6034-4138-425e-bb1d-93bd6a3cb99e.jpg', '20', '2020-04-01');
INSERT INTO `image` VALUES ('195', '15858029589330e2d5829-aa7b-476b-b8b6-a8266109d3fe.jpeg', '20', '2020-04-02');
INSERT INTO `image` VALUES ('196', '1585802958934228be894-ffc0-48a3-a888-848679fd698d.jpeg', '20', '2020-04-02');
INSERT INTO `image` VALUES ('197', '15858029589345d88dc3a-d52a-4be2-a01b-6e53bd459bc3.jpeg', '20', '2020-04-02');
INSERT INTO `image` VALUES ('198', '1585802997447b41b518a-1dc8-4620-bf43-05b7dae14807.jpeg', '20', '2020-04-02');
INSERT INTO `image` VALUES ('199', '15858039708922bc3c048-1976-411a-ad31-925e9155d721.jpeg', '20', '2020-04-02');
INSERT INTO `image` VALUES ('200', '15858041100259d38eec0-2fb1-427c-a5dc-1d5a073f0e57.jpeg', '20', '2020-04-02');
INSERT INTO `image` VALUES ('201', '1585804844541749a85bb-ac14-48c0-b87b-3a0351e12288.jpg', '20', '2020-04-02');
INSERT INTO `image` VALUES ('202', '1585804882783a3ee5d78-d55c-4805-bc35-a241128d54f1.jpg', '20', '2020-04-02');
INSERT INTO `image` VALUES ('203', '1586169663364f39a7ea9-5daa-4038-88e2-6be2d3cc222a.jpeg', '1', '2020-04-06');
INSERT INTO `image` VALUES ('204', '15862613437860b2a98ea-da7d-40b1-be30-ba4487962f61.jpg', '5', '2020-04-07');
INSERT INTO `image` VALUES ('205', '158667020209772296756-5997-4d5c-aff8-757214d94acf.jpeg', '5', '2020-04-12');
INSERT INTO `image` VALUES ('206', '15866801948336525ddce-1932-4ede-be60-191fcf609a11.jpg', '1', '2020-04-12');
INSERT INTO `image` VALUES ('207', '15866817923775f051ab6-4ad3-42e9-b808-3e48b37f8bd5.jpg', '1', '2020-04-12');
INSERT INTO `image` VALUES ('208', '158676484602983d8c554-90ce-4a14-8c49-90b8bfc9ea22.jpg', '1', '2020-04-13');
INSERT INTO `image` VALUES ('209', '15867657833655467895d-e159-45f8-9aed-7403686dd771.jpeg', '1', '2020-04-13');
INSERT INTO `image` VALUES ('210', '15867676170393e06171e-bd3c-40d5-a94d-6e8c9d5436d8.jpg', '3', '2020-04-13');
INSERT INTO `image` VALUES ('211', '1586849339465a7afd3df-3545-407d-9f28-1f7f5fcba49e.jpeg', '1', '2020-04-14');
INSERT INTO `image` VALUES ('212', '158684969665797682eb3-5b1d-4f4f-869f-12bc314ad6a7.jpeg', '1', '2020-04-14');
INSERT INTO `image` VALUES ('213', '1586852661835b31203d8-f241-4269-9914-b1595fb945c2.jpg', '4', '2020-04-14');
INSERT INTO `image` VALUES ('214', '158685275092950a4569d-12ac-44bc-b76f-fe0973d75897.jpeg', '4', '2020-04-14');
INSERT INTO `image` VALUES ('215', '15868536748063ed08a57-473c-4ad2-9dcc-ea44d18f8671.jpeg', '2', '2020-04-14');
INSERT INTO `image` VALUES ('216', '15868538875882cc83696-3b93-491c-ab6d-e948e7d9ffe0.jpeg', '2', '2020-04-14');
INSERT INTO `image` VALUES ('217', '158685398492178224f18-8b7a-4518-abe1-f47bef1e1a9d.jpeg', '2', '2020-04-14');
INSERT INTO `image` VALUES ('218', '1586863629785c4db9787-f902-4944-9cd7-7e1583f5637a.jpeg', '4', '2020-04-14');
INSERT INTO `image` VALUES ('219', '15890112285408a4a3880-1a4d-465c-8abe-b94c9ffcdc90.jpg', '5', '2020-05-09');
INSERT INTO `image` VALUES ('220', '158909897386673a47a69-78c6-43cb-8aaa-4dd685b20d1e.jpg', '1', '2020-05-10');
INSERT INTO `image` VALUES ('221', '1589098982174aff320fb-600f-4095-9a57-eef378aff30b.jpg', '1', '2020-05-10');
INSERT INTO `image` VALUES ('222', '1589098982135eec0ab8f-36e7-4fcf-a56c-f5df5fb75824.jpg', '1', '2020-05-10');
INSERT INTO `image` VALUES ('223', '1589101408572338c1aa9-4a71-4c50-af94-9b7bd21ac3a4.jpeg', '5', '2020-05-10');
INSERT INTO `image` VALUES ('224', '158910154577664d8003a-273e-4876-b5da-90164b11cfe1.jpg', '5', '2020-05-10');
INSERT INTO `image` VALUES ('225', '15891015522892b81436c-9046-4583-8636-a52a86fb8aee.jpeg', '5', '2020-05-10');
INSERT INTO `image` VALUES ('226', '15891021442647a011bf1-1936-455f-992b-909de3ecce14.png', '2', '2020-05-10');
INSERT INTO `image` VALUES ('227', '15891039044006227fb3c-d9af-4782-a7cc-fcb515e7b057.jpeg', '1', '2020-05-10');
INSERT INTO `image` VALUES ('228', '15891105495869f017619-f063-45fa-beaa-3404065973e4.jpeg', '1', '2020-05-10');
INSERT INTO `image` VALUES ('229', '15891107320092bb7d45f-8935-47fe-ba93-be419f16bb3e.jpg', '1', '2020-05-10');
INSERT INTO `image` VALUES ('230', '158911073973368ecf25a-581f-4db5-a18b-e21f7a23a162.jpeg', '1', '2020-05-10');
INSERT INTO `image` VALUES ('231', '1589110739638141d9e07-4f00-40b0-bf25-b7af1c4d10d3.jpeg', '1', '2020-05-10');
INSERT INTO `image` VALUES ('232', '1589110817601ba187e04-aaa6-4385-bd52-c7fa8f2991a2.jpeg', '1', '2020-05-10');
INSERT INTO `image` VALUES ('233', '1589110817562af44af71-3039-4054-99bd-6d90ee0d41de.jpeg', '1', '2020-05-10');
INSERT INTO `image` VALUES ('234', '1589110817563c3a38973-4df3-4d3a-af75-483ea3ff846a.jpeg', '1', '2020-05-10');
INSERT INTO `image` VALUES ('235', '1589110892052d8641192-2756-4f95-bf44-710e91a6e000.jpeg', '1', '2020-05-10');
INSERT INTO `image` VALUES ('236', '15891108921430b108cfa-d5f1-4c17-91f2-ddbdd7ddb09f.jpeg', '1', '2020-05-10');
INSERT INTO `image` VALUES ('237', '1589112792491e39694b7-528e-4659-8668-3d4627edee88.png', '1', '2020-05-10');
INSERT INTO `image` VALUES ('238', '1589113324638bc924e62-e9ba-4e31-bbd3-f6e7cf0f1323.jpg', '5', '2020-05-10');
INSERT INTO `image` VALUES ('239', '15891136699529de67e9c-f0c3-4f28-928e-d7e4834b92e8.png', '5', '2020-05-10');
INSERT INTO `image` VALUES ('240', '15891151641540b96563d-a6fb-4d49-b0c1-30e507f69298.png', '5', '2020-05-10');
INSERT INTO `image` VALUES ('241', '1589184521111101ea4da-6fe1-4eed-ab3d-6a278b8c1756.jpg', '1', '2020-05-11');
INSERT INTO `image` VALUES ('242', '15891845210479bc0e6fd-16fe-431b-b175-b63cb4e68779.png', '1', '2020-05-11');
INSERT INTO `image` VALUES ('243', '159023811075224cd8c51-8c9a-4ab1-b3bb-193824deec32.jpg', '1', '2020-05-23');
INSERT INTO `image` VALUES ('244', '1590238110800e407cecf-ba10-4a08-9357-fdf2b3411c7e.jpg', '1', '2020-05-23');
INSERT INTO `image` VALUES ('245', '1590238110750e6f7d29d-0752-4cee-b8bf-335ce5cf2943.jpg', '1', '2020-05-23');
INSERT INTO `image` VALUES ('246', '15902381221532eb74924-8508-4529-9314-44a64132e675.jpeg', '1', '2020-05-23');
INSERT INTO `image` VALUES ('247', '15902381221945c6d35cf-f2c9-4e57-8441-8f0a00438d85.jpeg', '1', '2020-05-23');
INSERT INTO `image` VALUES ('248', '1590238122247497f43d4-fa10-4ef5-9c11-b41c39874f7a.jpeg', '1', '2020-05-23');
INSERT INTO `image` VALUES ('249', '1590238122248b20a7f35-a474-4dfa-b233-a163215c7357.jpeg', '1', '2020-05-23');
INSERT INTO `image` VALUES ('250', '1590238122290d83f2d19-958b-4c21-afbb-4a4d98065290.jpeg', '1', '2020-05-23');
INSERT INTO `image` VALUES ('251', '159023812227953b09478-e3a3-4862-a719-bd065c0c968a.jpeg', '1', '2020-05-23');
INSERT INTO `image` VALUES ('252', '15913571560269da521a4-bccb-486c-b038-4399f60b89de.jpg', '20', '2020-06-05');
INSERT INTO `image` VALUES ('253', '1591357762384e88297b5-b9f2-4380-b604-674040f03040.jpeg', '20', '2020-06-05');
INSERT INTO `image` VALUES ('254', '159151798468607949be4-4fbb-4ffb-b4ed-ce734af2d63b.jpg', '1', '2020-06-07');
INSERT INTO `image` VALUES ('255', '1591517984686783417f0-23b5-4b41-bdec-4bd4266b9034.jpg', '1', '2020-06-07');
INSERT INTO `image` VALUES ('256', '159151815222713751fdc-640e-4c21-94cd-beeb15abaca1.jpg', '1', '2020-06-07');
INSERT INTO `image` VALUES ('257', '1591518257485875ed599-13c9-41a5-9da9-b9aae4f638bb.jpg', '1', '2020-06-07');
INSERT INTO `image` VALUES ('258', '15915184820549e4d6a2c-b80a-4da7-a9a4-13703cc82861.jpg', '5', '2020-06-07');
INSERT INTO `image` VALUES ('259', '1591518578081c38035d8-8547-4849-b757-8ef3af69545e.jpg', '5', '2020-06-07');
INSERT INTO `image` VALUES ('260', '159151865737855e3db60-1bdd-42bc-ad15-94a28d6d54e4.jpg', '5', '2020-06-07');
INSERT INTO `image` VALUES ('261', '1591519207706eeeecba7-f7b1-4570-9f9f-8545dc8d0b79.jpg', '5', '2020-06-07');
INSERT INTO `image` VALUES ('262', '15915192890016d5ef9a4-6929-4a63-b960-dc7b2ee5a4db.jpg', '5', '2020-06-07');
INSERT INTO `image` VALUES ('263', '1591519404661bda16204-a746-4449-8aac-e4cb497b74ab.jpg', '5', '2020-06-07');
INSERT INTO `image` VALUES ('264', '1591519404754cb1cf0f5-c943-486b-99e0-453733d4e9df.jpg', '5', '2020-06-07');
INSERT INTO `image` VALUES ('265', '15915194796901a4dc71e-fe5a-4c71-8dd1-8de5c2df8a2d.jpg', '5', '2020-06-07');
INSERT INTO `image` VALUES ('266', '15915195355160091f2ec-1fec-4dbd-b531-f172f05584c6.jpg', '5', '2020-06-07');
INSERT INTO `image` VALUES ('267', '15915195356351efd5dbc-17ca-4875-87e7-5ff513d6c3d2.jpg', '5', '2020-06-07');
INSERT INTO `image` VALUES ('268', '15915195958501d230eea-7238-41f3-add5-8d9113eb680b.jpg', '5', '2020-06-07');
INSERT INTO `image` VALUES ('269', '1591519671198905f378e-40bf-41fe-8f03-96706561626d.jpg', '5', '2020-06-07');
INSERT INTO `image` VALUES ('270', '1591519730439d0c8eab6-b266-4dd3-b251-eaf58729e217.jpg', '5', '2020-06-07');
INSERT INTO `image` VALUES ('271', '1591519809089bcdea025-553a-442a-af05-44e3e42582f2.jpg', '5', '2020-06-07');
INSERT INTO `image` VALUES ('272', '1591519809089966d8b5c-58f8-4e53-aa63-b0b1471ff213.jpg', '5', '2020-06-07');
INSERT INTO `image` VALUES ('273', '159151986875829fc4530-9663-4759-a9f0-daf83c624f42.jpg', '5', '2020-06-07');
INSERT INTO `image` VALUES ('274', '1591519868800e0a847ff-f34e-43cd-afbe-021ad9423c1d.jpg', '5', '2020-06-07');
INSERT INTO `image` VALUES ('275', '15915200265259b3d6fce-664c-483c-bebc-031a7bac0561.jpg', '2', '2020-06-07');
INSERT INTO `image` VALUES ('276', '15915201033574d584dfd-0a80-472b-8d7f-3235a9b932d6.jpg', '2', '2020-06-07');
INSERT INTO `image` VALUES ('277', '15915201595290a8a1254-6b48-49e4-a82c-b6f37b9fa756.jpg', '2', '2020-06-07');
INSERT INTO `image` VALUES ('278', '1591520230254fa6b3807-9bec-4c75-b69e-57a09780c52b.jpg', '2', '2020-06-07');
INSERT INTO `image` VALUES ('279', '15915202302817db2f8bb-b8d8-43a9-8047-017508ad4c65.jpg', '2', '2020-06-07');
INSERT INTO `image` VALUES ('280', '15915204088203500fd48-d59a-48af-8e64-a08995498807.jpg', '2', '2020-06-07');
INSERT INTO `image` VALUES ('281', '15915204496072e2baa3a-9e0d-4ed6-a02d-143d43e4e24c.jpg', '2', '2020-06-07');
INSERT INTO `image` VALUES ('282', '159152046011306c3c41f-4964-446e-b83c-85afec8e7491.jpg', '2', '2020-06-07');
INSERT INTO `image` VALUES ('283', '159152054981702949157-dad1-4b8d-b391-0105e717bcac.jpg', '2', '2020-06-07');
INSERT INTO `image` VALUES ('284', '15915206314052a6a06c6-77a5-41a4-a560-b923f2e7b2d0.jpg', '2', '2020-06-07');
INSERT INTO `image` VALUES ('285', '15915207541612f32138b-9b4e-4929-8c1c-6e0622544293.jpg', '2', '2020-06-07');
INSERT INTO `image` VALUES ('286', '159152082718924ffc020-1e03-4f48-b656-ccd16f517914.jpg', '2', '2020-06-07');
INSERT INTO `image` VALUES ('287', '1591520885041f62dc6c0-9e56-42fa-bff5-caf339909ae7.jpg', '2', '2020-06-07');
INSERT INTO `image` VALUES ('288', '1591520945436a0a145c3-64f0-414d-a6ad-edebe7b1da3a.jpg', '2', '2020-06-07');
INSERT INTO `image` VALUES ('289', '1591521013399150d9a50-5fe1-4ee6-98cd-2142f16deb79.jpg', '2', '2020-06-07');
INSERT INTO `image` VALUES ('290', '1591521055818844df23d-d74d-4d4c-b63b-23bb80674c04.jpg', '2', '2020-06-07');
INSERT INTO `image` VALUES ('291', '1591521100508a199ff72-0521-41ed-a9b3-7c949553a1d9.jpg', '2', '2020-06-07');
INSERT INTO `image` VALUES ('292', '15915211934455cf83d64-072d-42d2-898b-479b10e1e0fd.jpg', '2', '2020-06-07');
INSERT INTO `image` VALUES ('293', '15915213177741f1f09dd-091d-404c-83f4-511eb0126611.jpg', '2', '2020-06-07');
INSERT INTO `image` VALUES ('294', '1591521443209edb5db6c-d2af-4bf9-84f5-84ab8cc20e1e.jpg', '8', '2020-06-07');
INSERT INTO `image` VALUES ('295', '15915218434656c2063d4-6130-4b55-a6ad-dab907dea950.jpg', '8', '2020-06-07');
INSERT INTO `image` VALUES ('296', '1591531353736a2fe431b-9f69-4948-aa22-723735084a26.jpg', '8', '2020-06-07');
INSERT INTO `image` VALUES ('297', '15915319790985bef664a-373b-432a-8ff2-a6cd44d2e705.jpg', '8', '2020-06-07');
INSERT INTO `image` VALUES ('298', '159153262018450d32eec-6713-4dbf-aeb6-42d62f106386.jpg', '8', '2020-06-07');
INSERT INTO `image` VALUES ('299', '159153298888644ca93fd-a914-484d-b685-ba2f3fa0688f.jpg', '8', '2020-06-07');
INSERT INTO `image` VALUES ('300', '15915339577777c856c8a-283f-4c7d-a6ce-b43890239092.jpg', '8', '2020-06-07');
INSERT INTO `image` VALUES ('301', '1591535533397b23ba4e1-f0df-47da-8617-7b9e54acf72a.jpg', '3', '2020-06-07');
INSERT INTO `image` VALUES ('302', '1591536109978417422e0-eead-42a2-ae52-1e9f82ec7578.jpg', '3', '2020-06-07');
INSERT INTO `image` VALUES ('303', '159158039583323bd44c3-8b36-4157-98c0-18eacb623ee9.jpg', '3', '2020-06-08');
INSERT INTO `image` VALUES ('304', '1591580591081d8dfa950-3c49-4cf5-9c31-28f7d8f9c35b.jpg', '3', '2020-06-08');
INSERT INTO `image` VALUES ('305', '1591580677932933e9145-158d-4d7c-a4aa-76d174545b49.jpg', '3', '2020-06-08');
INSERT INTO `image` VALUES ('306', '1591580736751ff06e645-7fb6-4dfa-84ca-79d95663814e.jpg', '3', '2020-06-08');
INSERT INTO `image` VALUES ('307', '1591580895785a7287556-556b-4cbe-89c3-97c5e781262b.jpg', '3', '2020-06-08');
INSERT INTO `image` VALUES ('308', '159158121154912759b12-9d2b-4d91-af5d-4ac859e45130.jpg', '3', '2020-06-08');
INSERT INTO `image` VALUES ('309', '1591581286982bf74aa5f-8251-44cd-b49b-bfb306971b52.jpg', '3', '2020-06-08');
INSERT INTO `image` VALUES ('310', '1591581452410328329b7-7e27-4649-b241-47e53661541a.jpg', '3', '2020-06-08');
INSERT INTO `image` VALUES ('311', '159158148361485e330e2-f035-4dc4-9be0-550f23980fe9.jpg', '3', '2020-06-08');
INSERT INTO `image` VALUES ('312', '159158148361572397a65-d6d1-46c1-826f-7354735c184a.jpg', '3', '2020-06-08');
INSERT INTO `image` VALUES ('313', '159158166102740321729-2f62-4f36-ab0e-b4ec618d3e73.jpg', '3', '2020-06-08');
INSERT INTO `image` VALUES ('314', '1591582342095285d7106-cba3-4595-b812-4efb364a0c27.jpg', '3', '2020-06-08');
INSERT INTO `image` VALUES ('315', '15915859552073b83492e-4000-4e2e-bfe3-f818d1c14f52.jpg', '1', '2020-06-08');
INSERT INTO `image` VALUES ('316', '15916070078359b7c8b94-754f-4bb4-92ad-bda13c8473f0.jpg', '1', '2020-06-08');
INSERT INTO `image` VALUES ('317', '1591607007828728ce669-f1c7-4277-b52c-ebdf689d38c7.jpg', '1', '2020-06-08');
INSERT INTO `image` VALUES ('318', '1591607007841baa57100-3f86-405f-a0a7-a390db5e95c2.jpg', '1', '2020-06-08');
INSERT INTO `image` VALUES ('319', '15916070078294c3c0cdf-87e9-4fde-8009-a030fcda7a18.jpg', '1', '2020-06-08');
INSERT INTO `image` VALUES ('320', '15916070078182b9a74a0-ea77-4afe-8b1c-1d55b77b279a.jpg', '1', '2020-06-08');
INSERT INTO `image` VALUES ('321', '1591607007969ecab44bf-d8c8-4563-8513-9761401e076d.jpg', '1', '2020-06-08');
INSERT INTO `image` VALUES ('322', '159160700802016774340-d675-47d8-9942-0ef9bcd3ee56.jpg', '1', '2020-06-08');
INSERT INTO `image` VALUES ('323', '159160700801034c9c5f4-e64f-40ae-86c5-06bd4a040625.jpg', '1', '2020-06-08');
INSERT INTO `image` VALUES ('324', '1591607008019cb60370c-8ab2-41b3-bb70-e6d001199da8.jpg', '1', '2020-06-08');
INSERT INTO `image` VALUES ('325', '159162236578692144eb8-171e-45f8-91ec-eaa2b98a1c5d.jpg', '31', '2020-06-08');
INSERT INTO `image` VALUES ('326', '1591627432511d01c573a-bebd-4254-bc83-e1de87b501aa.jpg', '31', '2020-06-08');
INSERT INTO `image` VALUES ('327', '1591627691542209638de-7c76-48d2-90b5-ea7fd156911d.jpg', '31', '2020-06-08');
INSERT INTO `image` VALUES ('328', '159162807107718345751-359d-4143-b0fa-e059a59d3ca1.jpg', '31', '2020-06-08');
INSERT INTO `image` VALUES ('329', '159162926485354750904-e298-40a3-b7eb-8dc209226c15.jpg', '31', '2020-06-08');
INSERT INTO `image` VALUES ('330', '1591670193561edae3e7b-3eb2-478c-a0c6-bb1a4aa250a4.jpg', '1', '2020-06-09');
INSERT INTO `image` VALUES ('331', '1591678693300b4e1b12a-4863-4ab5-9d72-dbe539aa62b9.jpg', '1', '2020-06-09');
INSERT INTO `image` VALUES ('332', '1591678740440c94b3043-6ca8-4add-9daf-45506825aaaf.jpg', '1', '2020-06-09');
INSERT INTO `image` VALUES ('333', '159167884633062c7d224-faff-450b-b59a-f19dbd207054.jpg', '1', '2020-06-09');
INSERT INTO `image` VALUES ('334', '15916788896921d8e1103-9c21-419c-87a6-554a7e91068f.jpg', '1', '2020-06-09');
INSERT INTO `image` VALUES ('335', '1591678969120bfc941bb-58af-40e7-ac57-f2b8b82c8eea.jpg', '1', '2020-06-09');
INSERT INTO `image` VALUES ('336', '1591679037438cfafd7c1-684d-4491-9b34-94d6542ecef0.jpg', '1', '2020-06-09');
INSERT INTO `image` VALUES ('337', '15916790951956224ed92-a4b7-4a15-8dbf-148b251c5821.jpg', '1', '2020-06-09');
INSERT INTO `image` VALUES ('338', '1591679180319b39f47a3-00db-4bf9-89a3-21c14ccac1dd.jpg', '1', '2020-06-09');
INSERT INTO `image` VALUES ('339', '15916792363925cc6e7cc-beed-4f36-8ae7-04cfee83d854.jpg', '1', '2020-06-09');
INSERT INTO `image` VALUES ('340', '1591679316355dd3a151a-6d7a-447a-bd44-7854267b3bfc.jpg', '1', '2020-06-09');
INSERT INTO `image` VALUES ('341', '1591679391885dd9ef052-ed25-4f20-9d7d-2e294a125996.jpg', '1', '2020-06-09');
INSERT INTO `image` VALUES ('342', '15916794518944003e8d9-8820-483e-9759-2dac6a7f9bde.jpg', '1', '2020-06-09');
INSERT INTO `image` VALUES ('343', '1591682562296874bd68e-24f7-4c83-9d63-ed82e26fa1c8.jpg', '1', '2020-06-09');
INSERT INTO `image` VALUES ('344', '1591755407526eb995e33-777b-45cf-8a73-f92bc77ffdcd.jpg', '5', '2020-06-10');
INSERT INTO `image` VALUES ('345', '1591755407526792de2f6-93d3-45de-b746-4e16833089bf.jpg', '5', '2020-06-10');
INSERT INTO `image` VALUES ('346', '1591755407526f7ab90a6-31c6-4dad-80ad-1f3a6fdd857f.jpg', '5', '2020-06-10');

-- ----------------------------
-- Table structure for image_article
-- ----------------------------
DROP TABLE IF EXISTS `image_article`;
CREATE TABLE `image_article` (
  `id` int(10) NOT NULL AUTO_INCREMENT COMMENT '文章图片关联表id',
  `image_id` int(10) DEFAULT NULL COMMENT '关联图片表id',
  `article_id` int(10) DEFAULT NULL COMMENT '关联文章表id',
  `create_time` date DEFAULT NULL COMMENT '创建时间',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=206 DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci COMMENT='文章图片关联表';

-- ----------------------------
-- Records of image_article
-- ----------------------------
INSERT INTO `image_article` VALUES ('84', '192', '92', '2020-04-01');
INSERT INTO `image_article` VALUES ('85', '193', '93', '2020-04-01');
INSERT INTO `image_article` VALUES ('86', '194', '93', '2020-04-01');
INSERT INTO `image_article` VALUES ('87', '197', '94', '2020-04-02');
INSERT INTO `image_article` VALUES ('88', '196', '94', '2020-04-02');
INSERT INTO `image_article` VALUES ('89', '195', '94', '2020-04-02');
INSERT INTO `image_article` VALUES ('90', '198', '94', '2020-04-02');
INSERT INTO `image_article` VALUES ('91', '199', '95', '2020-04-02');
INSERT INTO `image_article` VALUES ('92', '200', '96', '2020-04-02');
INSERT INTO `image_article` VALUES ('93', '202', '97', '2020-04-02');
INSERT INTO `image_article` VALUES ('94', '203', '98', '2020-04-06');
INSERT INTO `image_article` VALUES ('95', '204', '99', '2020-04-07');
INSERT INTO `image_article` VALUES ('96', '205', '100', '2020-04-12');
INSERT INTO `image_article` VALUES ('97', '206', '101', '2020-04-12');
INSERT INTO `image_article` VALUES ('98', '207', '102', '2020-04-12');
INSERT INTO `image_article` VALUES ('99', '208', '103', '2020-04-13');
INSERT INTO `image_article` VALUES ('100', '209', '104', '2020-04-13');
INSERT INTO `image_article` VALUES ('101', '210', '105', '2020-04-13');
INSERT INTO `image_article` VALUES ('102', '213', '106', '2020-04-14');
INSERT INTO `image_article` VALUES ('103', '214', '107', '2020-04-14');
INSERT INTO `image_article` VALUES ('104', '215', '108', '2020-04-14');
INSERT INTO `image_article` VALUES ('105', '216', '109', '2020-04-14');
INSERT INTO `image_article` VALUES ('106', '217', '110', '2020-04-14');
INSERT INTO `image_article` VALUES ('107', '218', '111', '2020-04-14');
INSERT INTO `image_article` VALUES ('108', '223', '112', '2020-05-10');
INSERT INTO `image_article` VALUES ('109', '224', '113', '2020-05-10');
INSERT INTO `image_article` VALUES ('110', '225', '113', '2020-05-10');
INSERT INTO `image_article` VALUES ('111', '226', '114', '2020-05-10');
INSERT INTO `image_article` VALUES ('112', '227', '115', '2020-05-10');
INSERT INTO `image_article` VALUES ('113', '228', '116', '2020-05-10');
INSERT INTO `image_article` VALUES ('114', '232', '117', '2020-05-10');
INSERT INTO `image_article` VALUES ('115', '233', '117', '2020-05-10');
INSERT INTO `image_article` VALUES ('116', '234', '117', '2020-05-10');
INSERT INTO `image_article` VALUES ('117', '235', '118', '2020-05-10');
INSERT INTO `image_article` VALUES ('118', '236', '118', '2020-05-10');
INSERT INTO `image_article` VALUES ('119', '237', '119', '2020-05-10');
INSERT INTO `image_article` VALUES ('120', '238', '120', '2020-05-10');
INSERT INTO `image_article` VALUES ('121', '239', '121', '2020-05-10');
INSERT INTO `image_article` VALUES ('122', '240', '122', '2020-05-10');
INSERT INTO `image_article` VALUES ('123', '241', '123', '2020-05-11');
INSERT INTO `image_article` VALUES ('124', '242', '123', '2020-05-11');
INSERT INTO `image_article` VALUES ('125', '252', '124', '2020-06-05');
INSERT INTO `image_article` VALUES ('126', '253', '125', '2020-06-05');
INSERT INTO `image_article` VALUES ('127', '254', '126', '2020-06-07');
INSERT INTO `image_article` VALUES ('128', '255', '126', '2020-06-07');
INSERT INTO `image_article` VALUES ('129', '256', '127', '2020-06-07');
INSERT INTO `image_article` VALUES ('130', '257', '128', '2020-06-07');
INSERT INTO `image_article` VALUES ('131', '258', '129', '2020-06-07');
INSERT INTO `image_article` VALUES ('132', '259', '130', '2020-06-07');
INSERT INTO `image_article` VALUES ('133', '260', '131', '2020-06-07');
INSERT INTO `image_article` VALUES ('134', '261', '132', '2020-06-07');
INSERT INTO `image_article` VALUES ('135', '262', '133', '2020-06-07');
INSERT INTO `image_article` VALUES ('136', '263', '134', '2020-06-07');
INSERT INTO `image_article` VALUES ('137', '264', '134', '2020-06-07');
INSERT INTO `image_article` VALUES ('138', '265', '135', '2020-06-07');
INSERT INTO `image_article` VALUES ('139', '266', '136', '2020-06-07');
INSERT INTO `image_article` VALUES ('140', '267', '136', '2020-06-07');
INSERT INTO `image_article` VALUES ('141', '268', '137', '2020-06-07');
INSERT INTO `image_article` VALUES ('142', '269', '138', '2020-06-07');
INSERT INTO `image_article` VALUES ('143', '270', '139', '2020-06-07');
INSERT INTO `image_article` VALUES ('144', '271', '140', '2020-06-07');
INSERT INTO `image_article` VALUES ('145', '272', '140', '2020-06-07');
INSERT INTO `image_article` VALUES ('146', '273', '141', '2020-06-07');
INSERT INTO `image_article` VALUES ('147', '274', '141', '2020-06-07');
INSERT INTO `image_article` VALUES ('148', '275', '142', '2020-06-07');
INSERT INTO `image_article` VALUES ('149', '276', '143', '2020-06-07');
INSERT INTO `image_article` VALUES ('150', '277', '144', '2020-06-07');
INSERT INTO `image_article` VALUES ('151', '280', '145', '2020-06-07');
INSERT INTO `image_article` VALUES ('152', '281', '146', '2020-06-07');
INSERT INTO `image_article` VALUES ('153', '282', '146', '2020-06-07');
INSERT INTO `image_article` VALUES ('154', '283', '147', '2020-06-07');
INSERT INTO `image_article` VALUES ('155', '284', '148', '2020-06-07');
INSERT INTO `image_article` VALUES ('156', '285', '149', '2020-06-07');
INSERT INTO `image_article` VALUES ('157', '286', '150', '2020-06-07');
INSERT INTO `image_article` VALUES ('158', '287', '151', '2020-06-07');
INSERT INTO `image_article` VALUES ('159', '288', '152', '2020-06-07');
INSERT INTO `image_article` VALUES ('160', '289', '153', '2020-06-07');
INSERT INTO `image_article` VALUES ('161', '290', '154', '2020-06-07');
INSERT INTO `image_article` VALUES ('162', '291', '155', '2020-06-07');
INSERT INTO `image_article` VALUES ('163', '292', '156', '2020-06-07');
INSERT INTO `image_article` VALUES ('164', '293', '157', '2020-06-07');
INSERT INTO `image_article` VALUES ('165', '294', '158', '2020-06-07');
INSERT INTO `image_article` VALUES ('166', '295', '159', '2020-06-07');
INSERT INTO `image_article` VALUES ('167', '296', '160', '2020-06-07');
INSERT INTO `image_article` VALUES ('168', '298', '161', '2020-06-07');
INSERT INTO `image_article` VALUES ('169', '299', '162', '2020-06-07');
INSERT INTO `image_article` VALUES ('170', '300', '163', '2020-06-07');
INSERT INTO `image_article` VALUES ('171', '301', '164', '2020-06-07');
INSERT INTO `image_article` VALUES ('172', '302', '165', '2020-06-07');
INSERT INTO `image_article` VALUES ('173', '303', '166', '2020-06-08');
INSERT INTO `image_article` VALUES ('174', '315', '167', '2020-06-08');
INSERT INTO `image_article` VALUES ('175', '316', '168', '2020-06-08');
INSERT INTO `image_article` VALUES ('176', '317', '168', '2020-06-08');
INSERT INTO `image_article` VALUES ('177', '318', '168', '2020-06-08');
INSERT INTO `image_article` VALUES ('178', '319', '168', '2020-06-08');
INSERT INTO `image_article` VALUES ('179', '320', '168', '2020-06-08');
INSERT INTO `image_article` VALUES ('180', '321', '168', '2020-06-08');
INSERT INTO `image_article` VALUES ('181', '322', '168', '2020-06-08');
INSERT INTO `image_article` VALUES ('182', '323', '168', '2020-06-08');
INSERT INTO `image_article` VALUES ('183', '324', '168', '2020-06-08');
INSERT INTO `image_article` VALUES ('184', '325', '169', '2020-06-08');
INSERT INTO `image_article` VALUES ('185', '326', '170', '2020-06-08');
INSERT INTO `image_article` VALUES ('186', '327', '171', '2020-06-08');
INSERT INTO `image_article` VALUES ('187', '328', '172', '2020-06-08');
INSERT INTO `image_article` VALUES ('188', '329', '173', '2020-06-08');
INSERT INTO `image_article` VALUES ('189', '330', '175', '2020-06-09');
INSERT INTO `image_article` VALUES ('190', '331', '176', '2020-06-09');
INSERT INTO `image_article` VALUES ('191', '332', '177', '2020-06-09');
INSERT INTO `image_article` VALUES ('192', '333', '178', '2020-06-09');
INSERT INTO `image_article` VALUES ('193', '334', '179', '2020-06-09');
INSERT INTO `image_article` VALUES ('194', '335', '180', '2020-06-09');
INSERT INTO `image_article` VALUES ('195', '336', '181', '2020-06-09');
INSERT INTO `image_article` VALUES ('196', '337', '182', '2020-06-09');
INSERT INTO `image_article` VALUES ('197', '338', '183', '2020-06-09');
INSERT INTO `image_article` VALUES ('198', '339', '184', '2020-06-09');
INSERT INTO `image_article` VALUES ('199', '340', '185', '2020-06-09');
INSERT INTO `image_article` VALUES ('200', '341', '186', '2020-06-09');
INSERT INTO `image_article` VALUES ('201', '342', '187', '2020-06-09');
INSERT INTO `image_article` VALUES ('202', '343', '188', '2020-06-09');
INSERT INTO `image_article` VALUES ('203', '346', '189', '2020-06-10');
INSERT INTO `image_article` VALUES ('204', '345', '189', '2020-06-10');
INSERT INTO `image_article` VALUES ('205', '344', '189', '2020-06-10');

-- ----------------------------
-- Table structure for menu
-- ----------------------------
DROP TABLE IF EXISTS `menu`;
CREATE TABLE `menu` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `url` varchar(64) DEFAULT NULL,
  `path` varchar(64) DEFAULT NULL,
  `component` varchar(64) DEFAULT NULL,
  `name` varchar(64) DEFAULT NULL,
  `iconCls` varchar(64) DEFAULT NULL,
  `keepAlive` tinyint(1) DEFAULT NULL,
  `requireAuth` tinyint(1) DEFAULT NULL,
  `parentId` int(11) DEFAULT NULL,
  `enabled` tinyint(1) DEFAULT '1',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=23 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of menu
-- ----------------------------
INSERT INTO `menu` VALUES ('1', '/', '', null, '所有', null, null, null, null, '1');
INSERT INTO `menu` VALUES ('2', '/', '/home', 'Home', '用户管理', 'icon iconfont icon-user_icon', null, '1', '1', '1');
INSERT INTO `menu` VALUES ('3', '/', '/home', 'Home', '文章管理', 'icon iconfont icon-keyboard1', null, '1', '1', '1');
INSERT INTO `menu` VALUES ('4', '/', '/home', 'Home', '话题管理', 'icon iconfont icon-xiaoxi', null, '1', '1', '1');
INSERT INTO `menu` VALUES ('5', '/', '/home', 'Home', '广告管理', 'icon iconfont icon-faxian', null, '1', '1', '1');
INSERT INTO `menu` VALUES ('6', '/', '/home', 'Home', '系统管理', 'icon iconfont icon-shezhi', null, '1', '1', '1');
INSERT INTO `menu` VALUES ('7', '/', '/home', 'Home', '数据库管理', 'icon iconfont icon-geren', null, '1', '1', '1');
INSERT INTO `menu` VALUES ('8', '/users/basic/**', '/users', 'UsersBasic', '用户基本管理', '', null, '1', '2', '1');
INSERT INTO `menu` VALUES ('9', '/article/basic/**', '/article', 'ArticleBasic', '文章基本设置', '', null, '1', '3', '1');
INSERT INTO `menu` VALUES ('10', '/topic/basic/**', '/topic', 'TopicBasic', '话题基本设置', '', null, '1', '4', '1');
INSERT INTO `menu` VALUES ('11', '/adv/basic/**', '/adv', 'AdvBasic', '广告基本设置', '', null, '1', '5', '1');
INSERT INTO `menu` VALUES ('12', '/sys/basic/**', '/sys', 'SysBasic', '系统基本管理', '', null, '1', '6', '1');
INSERT INTO `menu` VALUES ('13', '/sys/admin/**', '/sys/admin', 'SysAdmin', 'admin管理', '', null, '1', '6', '1');
INSERT INTO `menu` VALUES ('14', '/db/basic/**', '/db', 'DbBasic', '数据库备份', '', null, '1', '7', '1');

-- ----------------------------
-- Table structure for menu_role
-- ----------------------------
DROP TABLE IF EXISTS `menu_role`;
CREATE TABLE `menu_role` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `mid` int(11) DEFAULT NULL,
  `rid` int(11) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=399 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of menu_role
-- ----------------------------
INSERT INTO `menu_role` VALUES ('377', '8', '1');
INSERT INTO `menu_role` VALUES ('378', '9', '1');
INSERT INTO `menu_role` VALUES ('379', '10', '1');
INSERT INTO `menu_role` VALUES ('380', '11', '1');
INSERT INTO `menu_role` VALUES ('381', '12', '1');
INSERT INTO `menu_role` VALUES ('382', '13', '1');
INSERT INTO `menu_role` VALUES ('383', '14', '1');
INSERT INTO `menu_role` VALUES ('384', '8', '4');
INSERT INTO `menu_role` VALUES ('386', '9', '2');
INSERT INTO `menu_role` VALUES ('387', '10', '3');
INSERT INTO `menu_role` VALUES ('388', '14', '4');
INSERT INTO `menu_role` VALUES ('389', '11', '5');
INSERT INTO `menu_role` VALUES ('390', '12', '6');
INSERT INTO `menu_role` VALUES ('391', '8', '7');
INSERT INTO `menu_role` VALUES ('392', '13', '6');
INSERT INTO `menu_role` VALUES ('393', '8', '9');
INSERT INTO `menu_role` VALUES ('394', '9', '9');
INSERT INTO `menu_role` VALUES ('395', '10', '9');
INSERT INTO `menu_role` VALUES ('396', '11', '9');
INSERT INTO `menu_role` VALUES ('397', '12', '9');
INSERT INTO `menu_role` VALUES ('398', '13', '9');

-- ----------------------------
-- Table structure for register
-- ----------------------------
DROP TABLE IF EXISTS `register`;
CREATE TABLE `register` (
  `id` int(10) NOT NULL AUTO_INCREMENT COMMENT '验证表id',
  `email` varchar(30) COLLATE utf8_unicode_ci DEFAULT NULL COMMENT 'youxiang',
  `code` varchar(6) COLLATE utf8_unicode_ci DEFAULT NULL COMMENT '验证码',
  `create_time` int(11) DEFAULT NULL COMMENT '验证码发送时间',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=8 DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci COMMENT='验证码表';

-- ----------------------------
-- Records of register
-- ----------------------------
INSERT INTO `register` VALUES ('1', 'suitianshuang@163.com', '3352', null);
INSERT INTO `register` VALUES ('2', '1005733094@qq.com', '7040', null);
INSERT INTO `register` VALUES ('3', '666666@qq.com', '5342', '1591599923');
INSERT INTO `register` VALUES ('4', 'tianshuangsui@Gmail.com', '3276', '1591601996');
INSERT INTO `register` VALUES ('5', 'yujin@163.com', '5666', '1591602444');
INSERT INTO `register` VALUES ('6', 'javaBoy', '1234', null);
INSERT INTO `register` VALUES ('7', '1834506522@qq.com', '10713', '1591620145');

-- ----------------------------
-- Table structure for role_admin
-- ----------------------------
DROP TABLE IF EXISTS `role_admin`;
CREATE TABLE `role_admin` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(64) DEFAULT NULL,
  `name_zh` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=13 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of role_admin
-- ----------------------------
INSERT INTO `role_admin` VALUES ('1', 'ROLE_manager', '超级管理员');
INSERT INTO `role_admin` VALUES ('2', 'ROLE_article', '文章管理员');
INSERT INTO `role_admin` VALUES ('3', 'ROLE_topic', '话题管理员');
INSERT INTO `role_admin` VALUES ('4', 'ROLE_db', '数据库管理员');
INSERT INTO `role_admin` VALUES ('5', 'ROLE_adv', '广告管理员');
INSERT INTO `role_admin` VALUES ('6', 'ROLE_sys', '系统管理员');
INSERT INTO `role_admin` VALUES ('7', 'ROLE_users', '用户管理员');
INSERT INTO `role_admin` VALUES ('12', 'ROLE_test3', '测试身份3');

-- ----------------------------
-- Table structure for sign
-- ----------------------------
DROP TABLE IF EXISTS `sign`;
CREATE TABLE `sign` (
  `id` int(10) NOT NULL AUTO_INCREMENT COMMENT '签到表id',
  `user_id` int(10) DEFAULT NULL COMMENT '用户id',
  `istoday_sign` int(1) DEFAULT '0' COMMENT '今天签到否 1签到了，0没有签到',
  `continue_sign_day` int(4) DEFAULT '0' COMMENT '持续签到天数',
  `sign_time` int(11) DEFAULT NULL COMMENT '最近一次的签到时间，时间戳格式',
  `kissnum` int(5) DEFAULT '0' COMMENT '总飞吻数',
  `isenable` int(1) DEFAULT '0' COMMENT '是否可用',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=19 DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci COMMENT='签到表';

-- ----------------------------
-- Records of sign
-- ----------------------------
INSERT INTO `sign` VALUES ('7', '1', '1', '5', '1591752531', '128', '0');
INSERT INTO `sign` VALUES ('12', '5', '1', '2', '1591515963', '1', '0');
INSERT INTO `sign` VALUES ('13', '20', '1', '1', '1591350472', '44', '0');
INSERT INTO `sign` VALUES ('14', '3', '1', '3', '1591582317', '112', '0');
INSERT INTO `sign` VALUES ('15', '4', '1', '1', '1586852175', '0', '0');
INSERT INTO `sign` VALUES ('16', '2', '1', '1', '1591515963', '2', '0');
INSERT INTO `sign` VALUES ('17', '8', '1', '1', '1591515963', '10', '0');
INSERT INTO `sign` VALUES ('18', '31', '1', '1', '1591654081', '10', '0');

-- ----------------------------
-- Table structure for support
-- ----------------------------
DROP TABLE IF EXISTS `support`;
CREATE TABLE `support` (
  `id` int(10) NOT NULL AUTO_INCREMENT COMMENT '顶踩表id',
  `article_id` int(10) DEFAULT NULL,
  `user_id` int(10) DEFAULT NULL COMMENT '用户表id',
  `type` int(1) DEFAULT NULL COMMENT '0 顶 1踩',
  `create_time` int(11) DEFAULT NULL COMMENT '时间',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=195 DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci COMMENT='顶踩表';

-- ----------------------------
-- Records of support
-- ----------------------------
INSERT INTO `support` VALUES ('153', '141', '5', '0', '1591519942');
INSERT INTO `support` VALUES ('154', '140', '2', '0', '1591519990');
INSERT INTO `support` VALUES ('155', '139', '2', '0', '1591519993');
INSERT INTO `support` VALUES ('156', '141', '3', '0', '1591535502');
INSERT INTO `support` VALUES ('157', '159', '5', '0', '1591668732');
INSERT INTO `support` VALUES ('158', '157', '5', '0', '1591668734');
INSERT INTO `support` VALUES ('159', '156', '5', '0', '1591668735');
INSERT INTO `support` VALUES ('160', '155', '5', '0', '1591668738');
INSERT INTO `support` VALUES ('161', '154', '5', '1', '1591668739');
INSERT INTO `support` VALUES ('162', '153', '5', '1', '1591668741');
INSERT INTO `support` VALUES ('163', '152', '1', '0', '1591671133');
INSERT INTO `support` VALUES ('164', '151', '1', '1', '1591671135');
INSERT INTO `support` VALUES ('165', '150', '1', '0', '1591671137');
INSERT INTO `support` VALUES ('166', '149', '1', '0', '1591671139');
INSERT INTO `support` VALUES ('167', '148', '1', '0', '1591671141');
INSERT INTO `support` VALUES ('168', '147', '1', '0', '1591671142');
INSERT INTO `support` VALUES ('169', '146', '1', '0', '1591671144');
INSERT INTO `support` VALUES ('170', '145', '1', '0', '1591671146');
INSERT INTO `support` VALUES ('171', '144', '1', '0', '1591671148');
INSERT INTO `support` VALUES ('172', '143', '1', '0', '1591671150');
INSERT INTO `support` VALUES ('173', '142', '1', '0', '1591671152');
INSERT INTO `support` VALUES ('174', '141', '1', '0', '1591671154');
INSERT INTO `support` VALUES ('175', '140', '1', '0', '1591671156');
INSERT INTO `support` VALUES ('176', '139', '1', '0', '1591671158');
INSERT INTO `support` VALUES ('177', '138', '1', '0', '1591671160');
INSERT INTO `support` VALUES ('178', '137', '1', '1', '1591671162');
INSERT INTO `support` VALUES ('179', '136', '1', '0', '1591671209');
INSERT INTO `support` VALUES ('180', '131', '1', '0', '1591671211');
INSERT INTO `support` VALUES ('181', '130', '1', '1', '1591671213');
INSERT INTO `support` VALUES ('182', '129', '1', '0', '1591671215');
INSERT INTO `support` VALUES ('183', '128', '1', '0', '1591671217');
INSERT INTO `support` VALUES ('184', '161', '1', '0', '1591671223');
INSERT INTO `support` VALUES ('185', '132', '1', '0', '1591671226');
INSERT INTO `support` VALUES ('186', '163', '1', '0', '1591671229');
INSERT INTO `support` VALUES ('187', '127', '1', '0', '1591671231');
INSERT INTO `support` VALUES ('188', '126', '1', '0', '1591671233');
INSERT INTO `support` VALUES ('189', '165', '1', '0', '1591671237');
INSERT INTO `support` VALUES ('190', '162', '1', '0', '1591671240');
INSERT INTO `support` VALUES ('191', '158', '1', '0', '1591671242');
INSERT INTO `support` VALUES ('192', '171', '1', '0', '1591671254');
INSERT INTO `support` VALUES ('193', '168', '1', '0', '1591671260');
INSERT INTO `support` VALUES ('194', '166', '1', '0', '1591671262');

-- ----------------------------
-- Table structure for topic
-- ----------------------------
DROP TABLE IF EXISTS `topic`;
CREATE TABLE `topic` (
  `id` int(10) NOT NULL AUTO_INCREMENT COMMENT '话题id',
  `title` varchar(30) COLLATE utf8_unicode_ci DEFAULT NULL COMMENT '话题名称',
  `titlepic` varchar(200) COLLATE utf8_unicode_ci DEFAULT NULL COMMENT '话题封面图',
  `description` varchar(30) COLLATE utf8_unicode_ci DEFAULT NULL COMMENT '话题描述',
  `isenable` int(2) DEFAULT '1' COMMENT '是否可用 1可用 0不可用',
  `create_time` int(11) DEFAULT NULL COMMENT '创建时间 时间戳格式',
  `topic_class_id` int(2) DEFAULT NULL COMMENT '话题的分类id',
  `total_num` int(10) DEFAULT '0' COMMENT '话题总计对应的文章个数',
  `today_num` int(10) DEFAULT '0' COMMENT '话题今天的文章个数',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=78 DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci COMMENT='话题表';

-- ----------------------------
-- Records of topic
-- ----------------------------
INSERT INTO `topic` VALUES ('1', '勒布朗.詹姆斯', '詹姆斯1.jpg', 'NBA超级巨星勒布朗.詹姆斯', '0', '1585916004', '1', '0', '0');
INSERT INTO `topic` VALUES ('2', '凯文.杜兰特', '杜兰特.jpg', 'NBA球星杜兰特', '0', '1585916005', '1', '0', '0');
INSERT INTO `topic` VALUES ('3', '库里', '库里.jpg', 'NBA球星库里', '0', '1585916006', '1', '0', '0');
INSERT INTO `topic` VALUES ('19', '#日语学习#', '日语.jpg', '记录学习日语的点滴', '0', '1589113345', '7', '0', '0');
INSERT INTO `topic` VALUES ('20', 'English Study', '英语学习.jpg', '记录学习英语的点滴', '0', '1589184556', '3', '0', '0');
INSERT INTO `topic` VALUES ('25', '#NBA即将开赛！#', 'topic2.jpg', '湖人总冠军', '1', '1591357157', '5', '1', '0');
INSERT INTO `topic` VALUES ('28', '#美国黑人被当街杀死#', '弗洛伊德.jpg', '弗洛伊德事件持续发酵中', '1', '1591357789', '5', '2', '0');
INSERT INTO `topic` VALUES ('29', '英国、加拿大也进行示威游行', '英国游行.jpg', '英国和加拿大也跟随美国进行示威游行', '1', '1591357913', '5', '0', '0');
INSERT INTO `topic` VALUES ('30', 'SpringbOot新版本', 'SpringBoot.jpg', 'springboot 2.1.3更新', '1', '1591357913', '7', '0', '0');
INSERT INTO `topic` VALUES ('34', '#非洲大地上的生灵#', '动物2.jpg', null, '1', '1591518080', '7', '1', '0');
INSERT INTO `topic` VALUES ('35', '#动物园里的动物#', '动物1.jpg', '井冈山动物园', '1', '1591518235', '7', '1', '0');
INSERT INTO `topic` VALUES ('36', '#我在沈阳建筑大学的日子#', '建筑大学1.jpg', null, '1', '1591518346', '7', '5', '0');
INSERT INTO `topic` VALUES ('37', '艺术源于生活', 'topic2.jpg', '艺术源于生活！！！', '1', '1591518641', '2', '1', '0');
INSERT INTO `topic` VALUES ('38', '#书法吧#', '书法1.jpg', '一起谈谈唐诗宋词', '1', '1591519268', '2', '1', '0');
INSERT INTO `topic` VALUES ('39', '又是一年毕业季！', '建筑大学2.jpg', '毕业了', '1', '1591519379', '2', '1', '0');
INSERT INTO `topic` VALUES ('40', '#梅西#', '梅西2.jpg', '球王梅西', '0', '1591519446', '7', '1', '0');
INSERT INTO `topic` VALUES ('63', '欧洲最大科比壁画', '科比.jpg', '...', '1', '1591521817', '1', '0', '0');
INSERT INTO `topic` VALUES ('67', '美国22个州游行示威', '美国1.jpg', null, '1', '1591533069', '4', '2', '0');
INSERT INTO `topic` VALUES ('74', '部落冲突', 'topic2.jpg', '最受欢迎的塔防游戏', '1', '1591622400', '7', '1', '0');
INSERT INTO `topic` VALUES ('75', '硝烟不断的游行示威活动', 'topic1.jpg', '战争的硝烟从未停歇', '1', '1591627502', '7', '1', '0');
INSERT INTO `topic` VALUES ('77', '极限运动', '/topic1.jpg', null, '0', '1591755522', '7', '1', '1');

-- ----------------------------
-- Table structure for topic_class
-- ----------------------------
DROP TABLE IF EXISTS `topic_class`;
CREATE TABLE `topic_class` (
  `id` int(2) NOT NULL AUTO_INCREMENT COMMENT '话题分类id',
  `classify_name` varchar(10) COLLATE utf8_unicode_ci DEFAULT NULL COMMENT '分类名称',
  `create_time` date DEFAULT NULL COMMENT '创建时间',
  `isenable` int(1) DEFAULT NULL COMMENT '是否可用',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=11 DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci COMMENT='话题分类表';

-- ----------------------------
-- Records of topic_class
-- ----------------------------
INSERT INTO `topic_class` VALUES ('1', '推荐', '2020-03-09', '1');
INSERT INTO `topic_class` VALUES ('2', '生活', '2020-03-12', '1');
INSERT INTO `topic_class` VALUES ('3', '学习', '2020-03-17', '1');
INSERT INTO `topic_class` VALUES ('4', '军事', '2020-03-17', '1');
INSERT INTO `topic_class` VALUES ('5', '时事', '2020-03-17', '1');
INSERT INTO `topic_class` VALUES ('7', '用户', '2020-03-14', '0');
INSERT INTO `topic_class` VALUES ('8', '追星', '2020-06-09', '1');
INSERT INTO `topic_class` VALUES ('9', 'NBA', '2020-06-09', '1');
INSERT INTO `topic_class` VALUES ('10', '编程', '2020-06-09', '1');

-- ----------------------------
-- Table structure for userinfo
-- ----------------------------
DROP TABLE IF EXISTS `userinfo`;
CREATE TABLE `userinfo` (
  `id` int(10) NOT NULL AUTO_INCREMENT COMMENT '用户资料表id',
  `user_id` int(10) DEFAULT NULL COMMENT '关联用户登录表',
  `sex` int(1) DEFAULT '0' COMMENT '性别 0代表未知 1代表女 2代表男',
  `age` int(3) DEFAULT NULL COMMENT '年龄',
  `emotion` int(1) DEFAULT '2' COMMENT '0未婚 1已婚 2未知',
  `job` varchar(15) COLLATE utf8_unicode_ci DEFAULT NULL COMMENT '工作',
  `birthday` date DEFAULT NULL COMMENT '生日',
  `hometown` varchar(20) COLLATE utf8_unicode_ci DEFAULT NULL COMMENT '故乡',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=17 DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci COMMENT='用户基本新表';

-- ----------------------------
-- Records of userinfo
-- ----------------------------
INSERT INTO `userinfo` VALUES ('1', '1', '0', '22', '0', '工程师', '1997-12-29', '请填写');
INSERT INTO `userinfo` VALUES ('2', '2', '0', '36', '1', 'php工程师', '1984-01-31', '北京');
INSERT INTO `userinfo` VALUES ('3', '8', '1', '21', '1', '老师', '1999-01-05', '山西省-太原市-小店区');
INSERT INTO `userinfo` VALUES ('4', '3', '0', '21', '1', '理发师', '1999-01-05', '陕西');
INSERT INTO `userinfo` VALUES ('5', '4', '0', '22', '1', '工程师', '1999-01-06', '辽宁葫芦岛');
INSERT INTO `userinfo` VALUES ('6', '5', '0', '21', '1', '电气工程师', '1998-12-30', '四川九寨沟');
INSERT INTO `userinfo` VALUES ('7', '20', '1', '20', '0', 'IT', '1999-04-27', '四川九寨沟');
INSERT INTO `userinfo` VALUES ('8', '21', '1', '22', '2', 'IT', '1999-04-27', '四川九寨沟');
INSERT INTO `userinfo` VALUES ('9', '22', '0', '22', '2', '电气工程师', '1999-04-27', '辽宁丹东东港');
INSERT INTO `userinfo` VALUES ('10', '23', '0', '22', '2', '电气工程师', '1999-04-27', '辽宁丹东东港');
INSERT INTO `userinfo` VALUES ('11', '24', '0', '22', '2', '电气工程师', '1999-04-27', '辽宁丹东东港');
INSERT INTO `userinfo` VALUES ('12', '25', '0', '22', '2', '电气工程师', '1999-04-27', '辽宁丹东东港');
INSERT INTO `userinfo` VALUES ('13', '27', '0', null, '2', null, null, null);
INSERT INTO `userinfo` VALUES ('14', '29', '0', null, '2', null, null, null);
INSERT INTO `userinfo` VALUES ('15', '30', '0', null, '2', null, null, null);
INSERT INTO `userinfo` VALUES ('16', '31', '0', null, '2', null, null, null);

-- ----------------------------
-- Table structure for users
-- ----------------------------
DROP TABLE IF EXISTS `users`;
CREATE TABLE `users` (
  `id` int(10) NOT NULL AUTO_INCREMENT COMMENT '用户登录表的id',
  `username` varchar(20) COLLATE utf8_unicode_ci DEFAULT NULL COMMENT '用户名称',
  `userpic` varchar(100) COLLATE utf8_unicode_ci DEFAULT NULL COMMENT '用户头像',
  `password` varchar(100) COLLATE utf8_unicode_ci DEFAULT NULL COMMENT '用户密码',
  `email` varchar(30) COLLATE utf8_unicode_ci DEFAULT NULL COMMENT 'youxiang',
  `role_id` varchar(10) COLLATE utf8_unicode_ci DEFAULT '1' COMMENT '角色表中的id',
  `create_time` varchar(40) COLLATE utf8_unicode_ci DEFAULT NULL COMMENT '创建账号的时间',
  `isenable` int(2) DEFAULT '1' COMMENT '账号是否可用 1 为真 可用 0不可用',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=32 DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci COMMENT='用户登录的基础信息表';

-- ----------------------------
-- Records of users
-- ----------------------------
INSERT INTO `users` VALUES ('1', '李秀丽', 'eaa9ecf5-0524-4a8b-a6ed-420a3f164788.jpg', 'a58ec2a157bc6fa3850c3f9a81c41fcf', '1208944060@qq.com', '1', '1585377681', '1');
INSERT INTO `users` VALUES ('2', '弓箭女皇', '322a7382-80cc-48ae-9fb1-161baa1d4f2a.jpg', '95f0e0affa77d987b63515c87b8c7223', '1422780320@qq.com', '1', '1585377681', '1');
INSERT INTO `users` VALUES ('3', '野蛮人之王', 'e26ca7fb-70c6-4a65-99f1-332db3ff5fb5.jpg', 'b98e16c459e5f95fa7bba7edc110d2be', '1415936767@qq.com', '1', '1585377681', '1');
INSERT INTO `users` VALUES ('4', '雷电巨龙', '044a6941-a806-42f9-8614-068821c03e71.jpg', '756ad79b2c07efa26062a203c4d9afe6', '12234356789@qq.com', '1', '1585377681', '1');
INSERT INTO `users` VALUES ('5', '瓦基丽武神', 'f86c945d-72a4-41fa-be30-9cffc5a0f726.jpg', '59eae66fcffcec6fa280220f3a5654da', '3242456789@qq.com', '1', '1585377681', '1');
INSERT INTO `users` VALUES ('8', '皮卡超人', '62ecfdfa-76c8-4843-862c-fa3cadf2a27e.jpg', 'f001600f627f5e40b57338867547601e', 'suitianshuang@163.com', '1', '1585377681', '1');
INSERT INTO `users` VALUES ('20', '我是余锦', '1586169663364f39a7ea9-5daa-4038-88e2-6be2d3cc222a.jpeg', 'ae6d2a926ede88c963247363127f8c60', '1005733094@qq.com', '1', '1585377681', '1');
INSERT INTO `users` VALUES ('21', '测试用户', '1586169663364f39a7ea9-5daa-4038-88e2-6be2d3cc222a.jpeg', 'ae6d2a926ede88c963247363127f8c60', '343324223@qq.com', '1', '1585377681', '0');
INSERT INTO `users` VALUES ('22', '测试用户2', '1586169663364f39a7ea9-5daa-4038-88e2-6be2d3cc222a.jpeg', 'ae6d2a926ede88c963247363127f8c60', '97346333@qq.com', '1', '1585377681', '1');
INSERT INTO `users` VALUES ('23', '测试用户三', '1586169663364f39a7ea9-5daa-4038-88e2-6be2d3cc222a.jpeg', 'ae6d2a926ede88c963247363127f8c60', '97346333@qq.com', '1', '1585377681', '1');
INSERT INTO `users` VALUES ('24', '测试用户4', '1586169663364f39a7ea9-5daa-4038-88e2-6be2d3cc222a.jpeg', 'ae6d2a926ede88c963247363127f8c60', '97346333@qq.com', '1', '1585377681', '1');
INSERT INTO `users` VALUES ('25', '测试用户5', '1586169663364f39a7ea9-5daa-4038-88e2-6be2d3cc222a.jpeg', 'ae6d2a926ede88c963247363127f8c60', '97346333@qq.com', '1', '1585377681', '1');
INSERT INTO `users` VALUES ('27', '系统管理员', '管理员1.jpg', '4442fb7e88cce0e523e513563f7094ac', '666666@qq.com', '1', '1591601900', '1');
INSERT INTO `users` VALUES ('29', '系统超级管理员', '管理员2.jpg', 'f83eb8778e418d271f0d2a181d900c69', 'tianshuangsui@Gmail.com', '1', '1591602022', '1');
INSERT INTO `users` VALUES ('30', 'WomanAdmin', '管理员3.jpg', '985f8141ca567d44b90187dd3e7a3efd', 'yujin@163.com', '1', '1591602491', '1');
INSERT INTO `users` VALUES ('31', '隋天双', '管理员.jpg', 'ee1f398e20b320e384a27f41921e05cb', '1834506522@qq.com', '1', '1591620181', '1');
