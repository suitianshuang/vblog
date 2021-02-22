(global["webpackJsonp"] = global["webpackJsonp"] || []).push([["pages/topic-detail/topic-detail"],{

/***/ "./node_modules/babel-loader/lib/index.js!./node_modules/@dcloudio/vue-cli-plugin-uni/packages/webpack-preprocess-loader/index.js?!./node_modules/@dcloudio/webpack-uni-mp-loader/lib/script.js!./node_modules/vue-loader/lib/index.js?!E:\\笔记\\HBuilderProjects\\【前端完整代码】仿糗事百科\\pages\\topic-detail\\topic-detail.vue?vue&type=script&lang=js&":
/*!**************************************************************************************************************************************************************************************************************************************************************************************************************************************************!*\
  !*** ./node_modules/babel-loader/lib!./node_modules/@dcloudio/vue-cli-plugin-uni/packages/webpack-preprocess-loader??ref--12-1!./node_modules/@dcloudio/webpack-uni-mp-loader/lib/script.js!./node_modules/vue-loader/lib??vue-loader-options!E:/笔记/HBuilderProjects/【前端完整代码】仿糗事百科/pages/topic-detail/topic-detail.vue?vue&type=script&lang=js& ***!
  \**************************************************************************************************************************************************************************************************************************************************************************************************************************************************/
/*! no static exports found */
/***/ (function(module, exports, __webpack_require__) {

"use strict";
/* WEBPACK VAR INJECTION */(function(uni) {Object.defineProperty(exports, "__esModule", { value: true });exports.default = void 0;var _regenerator = _interopRequireDefault(__webpack_require__(/*! ./node_modules/@babel/runtime/regenerator */ "./node_modules/@babel/runtime/regenerator/index.js"));function _interopRequireDefault(obj) {return obj && obj.__esModule ? obj : { default: obj };}function _slicedToArray(arr, i) {return _arrayWithHoles(arr) || _iterableToArrayLimit(arr, i) || _nonIterableRest();}function _nonIterableRest() {throw new TypeError("Invalid attempt to destructure non-iterable instance");}function _iterableToArrayLimit(arr, i) {var _arr = [];var _n = true;var _d = false;var _e = undefined;try {for (var _i = arr[Symbol.iterator](), _s; !(_n = (_s = _i.next()).done); _n = true) {_arr.push(_s.value);if (i && _arr.length === i) break;}} catch (err) {_d = true;_e = err;} finally {try {if (!_n && _i["return"] != null) _i["return"]();} finally {if (_d) throw _e;}}return _arr;}function _arrayWithHoles(arr) {if (Array.isArray(arr)) return arr;}function asyncGeneratorStep(gen, resolve, reject, _next, _throw, key, arg) {try {var info = gen[key](arg);var value = info.value;} catch (error) {reject(error);return;}if (info.done) {resolve(value);} else {Promise.resolve(value).then(_next, _throw);}}function _asyncToGenerator(fn) {return function () {var self = this,args = arguments;return new Promise(function (resolve, reject) {var gen = fn.apply(self, args);function _next(value) {asyncGeneratorStep(gen, resolve, reject, _next, _throw, "next", value);}function _throw(err) {asyncGeneratorStep(gen, resolve, reject, _next, _throw, "throw", err);}_next(undefined);});};}var topicInfo = function topicInfo() {return __webpack_require__.e(/*! import() | components/topic/topic-info */ "components/topic/topic-info").then(__webpack_require__.bind(null, /*! ../../components/topic/topic-info.vue */ "E:\\笔记\\HBuilderProjects\\【前端完整代码】仿糗事百科\\components\\topic\\topic-info.vue"));};var swiperTabHead = function swiperTabHead() {return __webpack_require__.e(/*! import() | components/index/swiper-tab-head */ "components/index/swiper-tab-head").then(__webpack_require__.bind(null, /*! ../../components/index/swiper-tab-head.vue */ "E:\\笔记\\HBuilderProjects\\【前端完整代码】仿糗事百科\\components\\index\\swiper-tab-head.vue"));};var commonList = function commonList() {return Promise.all(/*! import() | components/common/common-list */[__webpack_require__.e("common/vendor"), __webpack_require__.e("components/common/common-list")]).then(__webpack_require__.bind(null, /*! ../../components/common/common-list.vue */ "E:\\笔记\\HBuilderProjects\\【前端完整代码】仿糗事百科\\components\\common\\common-list.vue"));};var loadMore = function loadMore() {return __webpack_require__.e(/*! import() | components/common/load-more */ "components/common/load-more").then(__webpack_require__.bind(null, /*! ../../components/common/load-more.vue */ "E:\\笔记\\HBuilderProjects\\【前端完整代码】仿糗事百科\\components\\common\\load-more.vue"));};var _default =

































{
  components: {
    topicInfo: topicInfo,
    swiperTabHead: swiperTabHead,
    commonList: commonList,
    loadMore: loadMore },

  data: function data() {
    return {
      topicInfo: {
        titlepic: "../../static/demo/topicpic/13.jpeg",
        title: "忆往事，敬余生",
        desc: "我是描述",
        totalnum: 1000,
        todaynum: 1000 },

      tabIndex: 0,
      tabBars: [
      { name: "默认", id: "moren" },
      { name: "最新", id: "zuixin" }],

      tablist: [
      { loadtext: "上拉加载更多", list: [], firstload: false, page: 1 },
      { loadtext: "上拉加载更多", list: [], firstload: false, page: 1 }] };


  },
  // 上拉触底事件
  onReachBottom: function onReachBottom() {
    // 上拉加载
    this.loadmore();
  },
  // 监听下拉刷新 
  onPullDownRefresh: function onPullDownRefresh() {
    this.getList();
  },
  onLoad: function onLoad(e) {
    this.__init(JSON.parse(e.detail));
    // 开启监听
    uni.$on('updateData', this.updateData);
  },
  methods: {
    updateData: function updateData(data) {
      switch (data.type) {
        case "guanzhu":
          this.updateGuanZhu(data);
          break;
        case "support":
          this.updateSupport(data);
          break;}

    },
    // 更新关注信息
    updateGuanZhu: function updateGuanZhu(data) {
      this.tablist[this.tabIndex].list.forEach(function (item, index) {
        if (item.userid === data.userid) {
          item.isguanzhu = data.data;
        }
      });
    },
    updateSupport: function updateSupport(data) {
      var obj = this.tablist[this.tabIndex].list.find(function (item) {
        return item.id === data.post_id;
      });
      if (!obj || obj.infonum.index === 1) return;
      if (data.do == 'ding') {
        obj.infonum.index = 1;
        obj.goodnum++;
      }
    },
    // 初始化
    __init: function __init(obj) {
      // 修改标题
      uni.setNavigationBarTitle({ title: obj.title });
      this.topicInfo = obj;
      // 获取列表数据
      this.getList();
    },
    // 获取数据
    getList: function () {var _getList = _asyncToGenerator( /*#__PURE__*/_regenerator.default.mark(function _callee() {var url, _ref, _ref2, err, res, arr, list, i;return _regenerator.default.wrap(function _callee$(_context) {while (1) {switch (_context.prev = _context.next) {case 0:
                url = "/topic/".concat(this.topicInfo.id, "/post/").concat(this.tablist[this.tabIndex].page);_context.next = 3;return (
                  this.$http.get(url, {}, { token: true }));case 3:_ref = _context.sent;_ref2 = _slicedToArray(_ref, 2);err = _ref2[0];res = _ref2[1];if (

                this.$http.errorCheck(err, res)) {_context.next = 9;break;}return _context.abrupt("return",
                this.tablist[this.tabIndex].loadtext = "上拉加载更多");case 9:

                arr = [];
                list = res.data.data.list;
                for (i = 0; i < list.length; i++) {
                  arr.push(this.__format(list[i]));
                }
                this.tablist[this.tabIndex].list = this.tablist[this.tabIndex].page > 1 ? this.tablist[this.tabIndex].list.concat(arr) : arr;
                this.tablist[this.tabIndex].firstload = true;
                uni.stopPullDownRefresh();return _context.abrupt("return",
                this.tablist[this.tabIndex].loadtext = list.length < 10 ? "没有更多数据了" : "上拉加载更多");case 16:case "end":return _context.stop();}}}, _callee, this);}));function getList() {return _getList.apply(this, arguments);}return getList;}(),

    // 转换格式
    __format: function __format(item) {
      return {
        userid: item.user.id,
        userpic: item.user.userpic,
        username: item.user.username,
        isguanzhu: !!item.user.fens.length,
        id: item.id,
        title: item.title,
        type: "img", // img:图文,video:视频
        titlepic: item.titlepic,
        video: false,
        path: item.path,
        share: !!item.share,
        infonum: {
          index: item.support.length > 0 ? item.support[0].type + 1 : 0,
          dingnum: item.ding_count,
          cainum: item.cai_count },

        goodnum: item.ding_count,
        commentnum: item.comment_count,
        sharenum: item.sharenum };

    },
    // 上拉加载
    loadmore: function loadmore() {
      if (this.tablist[this.tabIndex].loadtext != "上拉加载更多") return;
      // 修改状态
      this.tablist[this.tabIndex].loadtext = "加载中...";
      // 页数+1
      this.tablist[this.tabIndex].page++;
      // 获取数据
      this.getList();
    },
    // tabbar点击事件
    tabtap: function tabtap(index) {
      this.tabIndex = index;
      this.tablist[this.tabIndex].page = 1;
      this.getList();
    } } };exports.default = _default;
/* WEBPACK VAR INJECTION */}.call(this, __webpack_require__(/*! ./node_modules/@dcloudio/uni-mp-weixin/dist/index.js */ "./node_modules/@dcloudio/uni-mp-weixin/dist/index.js")["default"]))

/***/ }),

/***/ "./node_modules/vue-loader/lib/loaders/templateLoader.js?!./node_modules/@dcloudio/vue-cli-plugin-uni/packages/webpack-preprocess-loader/index.js?!./node_modules/@dcloudio/webpack-uni-mp-loader/lib/template.js!./node_modules/vue-loader/lib/index.js?!E:\\笔记\\HBuilderProjects\\【前端完整代码】仿糗事百科\\pages\\topic-detail\\topic-detail.vue?vue&type=template&id=36bdec07&":
/*!******************************************************************************************************************************************************************************************************************************************************************************************************************************************************************************************************!*\
  !*** ./node_modules/vue-loader/lib/loaders/templateLoader.js??vue-loader-options!./node_modules/@dcloudio/vue-cli-plugin-uni/packages/webpack-preprocess-loader??ref--17-0!./node_modules/@dcloudio/webpack-uni-mp-loader/lib/template.js!./node_modules/vue-loader/lib??vue-loader-options!E:/笔记/HBuilderProjects/【前端完整代码】仿糗事百科/pages/topic-detail/topic-detail.vue?vue&type=template&id=36bdec07& ***!
  \******************************************************************************************************************************************************************************************************************************************************************************************************************************************************************************************************/
/*! exports provided: render, staticRenderFns */
/***/ (function(module, __webpack_exports__, __webpack_require__) {

"use strict";
__webpack_require__.r(__webpack_exports__);
/* harmony export (binding) */ __webpack_require__.d(__webpack_exports__, "render", function() { return render; });
/* harmony export (binding) */ __webpack_require__.d(__webpack_exports__, "staticRenderFns", function() { return staticRenderFns; });
var render = function() {
  var _vm = this
  var _h = _vm.$createElement
  var _c = _vm._self._c || _h
}
var staticRenderFns = []
render._withStripped = true



/***/ }),

/***/ "E:\\笔记\\HBuilderProjects\\【前端完整代码】仿糗事百科\\pages\\topic-detail\\topic-detail.vue":
/*!********************************************************************************!*\
  !*** E:/笔记/HBuilderProjects/【前端完整代码】仿糗事百科/pages/topic-detail/topic-detail.vue ***!
  \********************************************************************************/
/*! no static exports found */
/***/ (function(module, __webpack_exports__, __webpack_require__) {

"use strict";
__webpack_require__.r(__webpack_exports__);
/* harmony import */ var _topic_detail_vue_vue_type_template_id_36bdec07___WEBPACK_IMPORTED_MODULE_0__ = __webpack_require__(/*! ./topic-detail.vue?vue&type=template&id=36bdec07& */ "E:\\笔记\\HBuilderProjects\\【前端完整代码】仿糗事百科\\pages\\topic-detail\\topic-detail.vue?vue&type=template&id=36bdec07&");
/* harmony import */ var _topic_detail_vue_vue_type_script_lang_js___WEBPACK_IMPORTED_MODULE_1__ = __webpack_require__(/*! ./topic-detail.vue?vue&type=script&lang=js& */ "E:\\笔记\\HBuilderProjects\\【前端完整代码】仿糗事百科\\pages\\topic-detail\\topic-detail.vue?vue&type=script&lang=js&");
/* harmony reexport (unknown) */ for(var __WEBPACK_IMPORT_KEY__ in _topic_detail_vue_vue_type_script_lang_js___WEBPACK_IMPORTED_MODULE_1__) if(__WEBPACK_IMPORT_KEY__ !== 'default') (function(key) { __webpack_require__.d(__webpack_exports__, key, function() { return _topic_detail_vue_vue_type_script_lang_js___WEBPACK_IMPORTED_MODULE_1__[key]; }) }(__WEBPACK_IMPORT_KEY__));
/* harmony import */ var _D_HBuilderX_full_alpha_HBuilderX_plugins_uniapp_cli_node_modules_vue_loader_lib_runtime_componentNormalizer_js__WEBPACK_IMPORTED_MODULE_2__ = __webpack_require__(/*! ./node_modules/vue-loader/lib/runtime/componentNormalizer.js */ "./node_modules/vue-loader/lib/runtime/componentNormalizer.js");





/* normalize component */

var component = Object(_D_HBuilderX_full_alpha_HBuilderX_plugins_uniapp_cli_node_modules_vue_loader_lib_runtime_componentNormalizer_js__WEBPACK_IMPORTED_MODULE_2__["default"])(
  _topic_detail_vue_vue_type_script_lang_js___WEBPACK_IMPORTED_MODULE_1__["default"],
  _topic_detail_vue_vue_type_template_id_36bdec07___WEBPACK_IMPORTED_MODULE_0__["render"],
  _topic_detail_vue_vue_type_template_id_36bdec07___WEBPACK_IMPORTED_MODULE_0__["staticRenderFns"],
  false,
  null,
  null,
  null
  
)

/* hot reload */
if (false) { var api; }
component.options.__file = "E:/笔记/HBuilderProjects/【前端完整代码】仿糗事百科/pages/topic-detail/topic-detail.vue"
/* harmony default export */ __webpack_exports__["default"] = (component.exports);

/***/ }),

/***/ "E:\\笔记\\HBuilderProjects\\【前端完整代码】仿糗事百科\\pages\\topic-detail\\topic-detail.vue?vue&type=script&lang=js&":
/*!*********************************************************************************************************!*\
  !*** E:/笔记/HBuilderProjects/【前端完整代码】仿糗事百科/pages/topic-detail/topic-detail.vue?vue&type=script&lang=js& ***!
  \*********************************************************************************************************/
/*! no static exports found */
/***/ (function(module, __webpack_exports__, __webpack_require__) {

"use strict";
__webpack_require__.r(__webpack_exports__);
/* harmony import */ var _D_HBuilderX_full_alpha_HBuilderX_plugins_uniapp_cli_node_modules_babel_loader_lib_index_js_D_HBuilderX_full_alpha_HBuilderX_plugins_uniapp_cli_node_modules_dcloudio_vue_cli_plugin_uni_packages_webpack_preprocess_loader_index_js_ref_12_1_D_HBuilderX_full_alpha_HBuilderX_plugins_uniapp_cli_node_modules_dcloudio_webpack_uni_mp_loader_lib_script_js_D_HBuilderX_full_alpha_HBuilderX_plugins_uniapp_cli_node_modules_vue_loader_lib_index_js_vue_loader_options_topic_detail_vue_vue_type_script_lang_js___WEBPACK_IMPORTED_MODULE_0__ = __webpack_require__(/*! -!./node_modules/babel-loader/lib!./node_modules/@dcloudio/vue-cli-plugin-uni/packages/webpack-preprocess-loader??ref--12-1!./node_modules/@dcloudio/webpack-uni-mp-loader/lib/script.js!./node_modules/vue-loader/lib??vue-loader-options!./topic-detail.vue?vue&type=script&lang=js& */ "./node_modules/babel-loader/lib/index.js!./node_modules/@dcloudio/vue-cli-plugin-uni/packages/webpack-preprocess-loader/index.js?!./node_modules/@dcloudio/webpack-uni-mp-loader/lib/script.js!./node_modules/vue-loader/lib/index.js?!E:\\笔记\\HBuilderProjects\\【前端完整代码】仿糗事百科\\pages\\topic-detail\\topic-detail.vue?vue&type=script&lang=js&");
/* harmony import */ var _D_HBuilderX_full_alpha_HBuilderX_plugins_uniapp_cli_node_modules_babel_loader_lib_index_js_D_HBuilderX_full_alpha_HBuilderX_plugins_uniapp_cli_node_modules_dcloudio_vue_cli_plugin_uni_packages_webpack_preprocess_loader_index_js_ref_12_1_D_HBuilderX_full_alpha_HBuilderX_plugins_uniapp_cli_node_modules_dcloudio_webpack_uni_mp_loader_lib_script_js_D_HBuilderX_full_alpha_HBuilderX_plugins_uniapp_cli_node_modules_vue_loader_lib_index_js_vue_loader_options_topic_detail_vue_vue_type_script_lang_js___WEBPACK_IMPORTED_MODULE_0___default = /*#__PURE__*/__webpack_require__.n(_D_HBuilderX_full_alpha_HBuilderX_plugins_uniapp_cli_node_modules_babel_loader_lib_index_js_D_HBuilderX_full_alpha_HBuilderX_plugins_uniapp_cli_node_modules_dcloudio_vue_cli_plugin_uni_packages_webpack_preprocess_loader_index_js_ref_12_1_D_HBuilderX_full_alpha_HBuilderX_plugins_uniapp_cli_node_modules_dcloudio_webpack_uni_mp_loader_lib_script_js_D_HBuilderX_full_alpha_HBuilderX_plugins_uniapp_cli_node_modules_vue_loader_lib_index_js_vue_loader_options_topic_detail_vue_vue_type_script_lang_js___WEBPACK_IMPORTED_MODULE_0__);
/* harmony reexport (unknown) */ for(var __WEBPACK_IMPORT_KEY__ in _D_HBuilderX_full_alpha_HBuilderX_plugins_uniapp_cli_node_modules_babel_loader_lib_index_js_D_HBuilderX_full_alpha_HBuilderX_plugins_uniapp_cli_node_modules_dcloudio_vue_cli_plugin_uni_packages_webpack_preprocess_loader_index_js_ref_12_1_D_HBuilderX_full_alpha_HBuilderX_plugins_uniapp_cli_node_modules_dcloudio_webpack_uni_mp_loader_lib_script_js_D_HBuilderX_full_alpha_HBuilderX_plugins_uniapp_cli_node_modules_vue_loader_lib_index_js_vue_loader_options_topic_detail_vue_vue_type_script_lang_js___WEBPACK_IMPORTED_MODULE_0__) if(__WEBPACK_IMPORT_KEY__ !== 'default') (function(key) { __webpack_require__.d(__webpack_exports__, key, function() { return _D_HBuilderX_full_alpha_HBuilderX_plugins_uniapp_cli_node_modules_babel_loader_lib_index_js_D_HBuilderX_full_alpha_HBuilderX_plugins_uniapp_cli_node_modules_dcloudio_vue_cli_plugin_uni_packages_webpack_preprocess_loader_index_js_ref_12_1_D_HBuilderX_full_alpha_HBuilderX_plugins_uniapp_cli_node_modules_dcloudio_webpack_uni_mp_loader_lib_script_js_D_HBuilderX_full_alpha_HBuilderX_plugins_uniapp_cli_node_modules_vue_loader_lib_index_js_vue_loader_options_topic_detail_vue_vue_type_script_lang_js___WEBPACK_IMPORTED_MODULE_0__[key]; }) }(__WEBPACK_IMPORT_KEY__));
 /* harmony default export */ __webpack_exports__["default"] = (_D_HBuilderX_full_alpha_HBuilderX_plugins_uniapp_cli_node_modules_babel_loader_lib_index_js_D_HBuilderX_full_alpha_HBuilderX_plugins_uniapp_cli_node_modules_dcloudio_vue_cli_plugin_uni_packages_webpack_preprocess_loader_index_js_ref_12_1_D_HBuilderX_full_alpha_HBuilderX_plugins_uniapp_cli_node_modules_dcloudio_webpack_uni_mp_loader_lib_script_js_D_HBuilderX_full_alpha_HBuilderX_plugins_uniapp_cli_node_modules_vue_loader_lib_index_js_vue_loader_options_topic_detail_vue_vue_type_script_lang_js___WEBPACK_IMPORTED_MODULE_0___default.a); 

/***/ }),

/***/ "E:\\笔记\\HBuilderProjects\\【前端完整代码】仿糗事百科\\pages\\topic-detail\\topic-detail.vue?vue&type=template&id=36bdec07&":
/*!***************************************************************************************************************!*\
  !*** E:/笔记/HBuilderProjects/【前端完整代码】仿糗事百科/pages/topic-detail/topic-detail.vue?vue&type=template&id=36bdec07& ***!
  \***************************************************************************************************************/
/*! exports provided: render, staticRenderFns */
/***/ (function(module, __webpack_exports__, __webpack_require__) {

"use strict";
__webpack_require__.r(__webpack_exports__);
/* harmony import */ var _D_HBuilderX_full_alpha_HBuilderX_plugins_uniapp_cli_node_modules_vue_loader_lib_loaders_templateLoader_js_vue_loader_options_D_HBuilderX_full_alpha_HBuilderX_plugins_uniapp_cli_node_modules_dcloudio_vue_cli_plugin_uni_packages_webpack_preprocess_loader_index_js_ref_17_0_D_HBuilderX_full_alpha_HBuilderX_plugins_uniapp_cli_node_modules_dcloudio_webpack_uni_mp_loader_lib_template_js_D_HBuilderX_full_alpha_HBuilderX_plugins_uniapp_cli_node_modules_vue_loader_lib_index_js_vue_loader_options_topic_detail_vue_vue_type_template_id_36bdec07___WEBPACK_IMPORTED_MODULE_0__ = __webpack_require__(/*! -!./node_modules/vue-loader/lib/loaders/templateLoader.js??vue-loader-options!./node_modules/@dcloudio/vue-cli-plugin-uni/packages/webpack-preprocess-loader??ref--17-0!./node_modules/@dcloudio/webpack-uni-mp-loader/lib/template.js!./node_modules/vue-loader/lib??vue-loader-options!./topic-detail.vue?vue&type=template&id=36bdec07& */ "./node_modules/vue-loader/lib/loaders/templateLoader.js?!./node_modules/@dcloudio/vue-cli-plugin-uni/packages/webpack-preprocess-loader/index.js?!./node_modules/@dcloudio/webpack-uni-mp-loader/lib/template.js!./node_modules/vue-loader/lib/index.js?!E:\\笔记\\HBuilderProjects\\【前端完整代码】仿糗事百科\\pages\\topic-detail\\topic-detail.vue?vue&type=template&id=36bdec07&");
/* harmony reexport (safe) */ __webpack_require__.d(__webpack_exports__, "render", function() { return _D_HBuilderX_full_alpha_HBuilderX_plugins_uniapp_cli_node_modules_vue_loader_lib_loaders_templateLoader_js_vue_loader_options_D_HBuilderX_full_alpha_HBuilderX_plugins_uniapp_cli_node_modules_dcloudio_vue_cli_plugin_uni_packages_webpack_preprocess_loader_index_js_ref_17_0_D_HBuilderX_full_alpha_HBuilderX_plugins_uniapp_cli_node_modules_dcloudio_webpack_uni_mp_loader_lib_template_js_D_HBuilderX_full_alpha_HBuilderX_plugins_uniapp_cli_node_modules_vue_loader_lib_index_js_vue_loader_options_topic_detail_vue_vue_type_template_id_36bdec07___WEBPACK_IMPORTED_MODULE_0__["render"]; });

/* harmony reexport (safe) */ __webpack_require__.d(__webpack_exports__, "staticRenderFns", function() { return _D_HBuilderX_full_alpha_HBuilderX_plugins_uniapp_cli_node_modules_vue_loader_lib_loaders_templateLoader_js_vue_loader_options_D_HBuilderX_full_alpha_HBuilderX_plugins_uniapp_cli_node_modules_dcloudio_vue_cli_plugin_uni_packages_webpack_preprocess_loader_index_js_ref_17_0_D_HBuilderX_full_alpha_HBuilderX_plugins_uniapp_cli_node_modules_dcloudio_webpack_uni_mp_loader_lib_template_js_D_HBuilderX_full_alpha_HBuilderX_plugins_uniapp_cli_node_modules_vue_loader_lib_index_js_vue_loader_options_topic_detail_vue_vue_type_template_id_36bdec07___WEBPACK_IMPORTED_MODULE_0__["staticRenderFns"]; });



/***/ })

},[["E:\\笔记\\HBuilderProjects\\【前端完整代码】仿糗事百科\\main.js?{\"page\":\"pages%2Ftopic-detail%2Ftopic-detail\"}","common/runtime","common/vendor"]]]);
//# sourceMappingURL=../../../.sourcemap/mp-weixin/pages/topic-detail/topic-detail.js.map