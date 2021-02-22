(global["webpackJsonp"] = global["webpackJsonp"] || []).push([["pages/user-chat/user-chat"],{

/***/ "./node_modules/babel-loader/lib/index.js!./node_modules/@dcloudio/vue-cli-plugin-uni/packages/webpack-preprocess-loader/index.js?!./node_modules/@dcloudio/webpack-uni-mp-loader/lib/script.js!./node_modules/vue-loader/lib/index.js?!E:\\笔记\\HBuilderProjects\\【前端完整代码】仿糗事百科\\pages\\user-chat\\user-chat.vue?vue&type=script&lang=js&":
/*!********************************************************************************************************************************************************************************************************************************************************************************************************************************************!*\
  !*** ./node_modules/babel-loader/lib!./node_modules/@dcloudio/vue-cli-plugin-uni/packages/webpack-preprocess-loader??ref--12-1!./node_modules/@dcloudio/webpack-uni-mp-loader/lib/script.js!./node_modules/vue-loader/lib??vue-loader-options!E:/笔记/HBuilderProjects/【前端完整代码】仿糗事百科/pages/user-chat/user-chat.vue?vue&type=script&lang=js& ***!
  \********************************************************************************************************************************************************************************************************************************************************************************************************************************************/
/*! no static exports found */
/***/ (function(module, exports, __webpack_require__) {

"use strict";
/* WEBPACK VAR INJECTION */(function(uni) {Object.defineProperty(exports, "__esModule", { value: true });exports.default = void 0;




















var _time = _interopRequireDefault(__webpack_require__(/*! ../../common/time.js */ "E:\\笔记\\HBuilderProjects\\【前端完整代码】仿糗事百科\\common\\time.js"));function _interopRequireDefault(obj) {return obj && obj.__esModule ? obj : { default: obj };}var userChatBottom = function userChatBottom() {return __webpack_require__.e(/*! import() | components/user-chat/user-chat-bottom */ "components/user-chat/user-chat-bottom").then(__webpack_require__.bind(null, /*! ../../components/user-chat/user-chat-bottom.vue */ "E:\\笔记\\HBuilderProjects\\【前端完整代码】仿糗事百科\\components\\user-chat\\user-chat-bottom.vue"));};var userChatList = function userChatList() {return __webpack_require__.e(/*! import() | components/user-chat/user-chat-list */ "components/user-chat/user-chat-list").then(__webpack_require__.bind(null, /*! ../../components/user-chat/user-chat-list.vue */ "E:\\笔记\\HBuilderProjects\\【前端完整代码】仿糗事百科\\components\\user-chat\\user-chat-list.vue"));};var _default =

{
  components: {
    userChatBottom: userChatBottom,
    userChatList: userChatList },

  data: function data() {
    return {
      scrollTop: 0,
      style: {
        contentH: 0,
        itemH: 0 },

      list: [] };

  },
  onReady: function onReady() {
    this.getdata();
    this.initdata();
    this.pageToBottom(true);
  },
  methods: {
    // 初始化参数
    initdata: function initdata() {
      try {
        var res = uni.getSystemInfoSync();
        this.style.contentH = res.windowHeight - uni.upx2px(120);
      } catch (e) {}
    },
    pageToBottom: function pageToBottom() {var _this = this;var isfirst = arguments.length > 0 && arguments[0] !== undefined ? arguments[0] : false;
      var q = uni.createSelectorQuery().in(this);
      var itemH = q.selectAll('.chat-item');
      this.$nextTick(function () {
        itemH.fields({
          size: true },
        function (data) {
          if (data) {
            if (isfirst) {
              for (var i = 0; i < data.length; i++) {
                _this.style.itemH += data[i].height;
              }
            } else {
              _this.style.itemH += data[data.length - 1].height;
            }
            _this.scrollTop = _this.style.itemH > _this.style.contentH ? _this.style.itemH : 0;
          }
        }).exec();
      });
    },
    // 获取聊天数据
    getdata: function getdata() {
      // 从服务器获取到的数据
      var arr = [
      {
        isme: false,
        userpic: "../../static/demo/userpic/11.jpg",
        type: "text",
        data: "哈哈哈",
        time: "1555146412" },

      {
        isme: true,
        userpic: "../../static/demo/userpic/10.jpg",
        type: "img",
        data: "../../static/demo/3.jpg",
        time: "1555146414" },

      {
        isme: false,
        userpic: "../../static/demo/userpic/11.jpg",
        type: "text",
        data: "哈哈哈",
        time: "1555146412" },

      {
        isme: true,
        userpic: "../../static/demo/userpic/10.jpg",
        type: "img",
        data: "../../static/demo/3.jpg",
        time: "1555146414" },

      {
        isme: false,
        userpic: "../../static/demo/userpic/11.jpg",
        type: "text",
        data: "哈哈哈",
        time: "1555146412" },

      {
        isme: true,
        userpic: "../../static/demo/userpic/10.jpg",
        type: "img",
        data: "../../static/demo/3.jpg",
        time: "1555146414" },

      {
        isme: false,
        userpic: "../../static/demo/userpic/11.jpg",
        type: "text",
        data: "哈哈哈",
        time: "1555146412" },

      {
        isme: true,
        userpic: "../../static/demo/userpic/10.jpg",
        type: "img",
        data: "../../static/demo/3.jpg",
        time: "1555146414" }];


      for (var i = 0; i < arr.length; i++) {
        arr[i].gstime = _time.default.gettime.getChatTime(arr[i].time, i > 0 ? arr[i - 1].time : 0);
      }
      this.list = arr;
    },
    submit: function submit(data) {
      // 构建数据
      var now = new Date().getTime();
      var obj = {
        isme: true,
        userpic: "../../static/demo/userpic/10.jpg",
        type: "text",
        data: data,
        time: now,
        gstime: _time.default.gettime.getChatTime(now, this.list[this.list.length - 1].time) };

      this.list.push(obj);
      this.pageToBottom();
    } } };exports.default = _default;
/* WEBPACK VAR INJECTION */}.call(this, __webpack_require__(/*! ./node_modules/@dcloudio/uni-mp-weixin/dist/index.js */ "./node_modules/@dcloudio/uni-mp-weixin/dist/index.js")["default"]))

/***/ }),

/***/ "./node_modules/vue-loader/lib/loaders/templateLoader.js?!./node_modules/@dcloudio/vue-cli-plugin-uni/packages/webpack-preprocess-loader/index.js?!./node_modules/@dcloudio/webpack-uni-mp-loader/lib/template.js!./node_modules/vue-loader/lib/index.js?!E:\\笔记\\HBuilderProjects\\【前端完整代码】仿糗事百科\\pages\\user-chat\\user-chat.vue?vue&type=template&id=74b8ff5d&":
/*!************************************************************************************************************************************************************************************************************************************************************************************************************************************************************************************************!*\
  !*** ./node_modules/vue-loader/lib/loaders/templateLoader.js??vue-loader-options!./node_modules/@dcloudio/vue-cli-plugin-uni/packages/webpack-preprocess-loader??ref--17-0!./node_modules/@dcloudio/webpack-uni-mp-loader/lib/template.js!./node_modules/vue-loader/lib??vue-loader-options!E:/笔记/HBuilderProjects/【前端完整代码】仿糗事百科/pages/user-chat/user-chat.vue?vue&type=template&id=74b8ff5d& ***!
  \************************************************************************************************************************************************************************************************************************************************************************************************************************************************************************************************/
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

/***/ "E:\\笔记\\HBuilderProjects\\【前端完整代码】仿糗事百科\\pages\\user-chat\\user-chat.vue":
/*!**************************************************************************!*\
  !*** E:/笔记/HBuilderProjects/【前端完整代码】仿糗事百科/pages/user-chat/user-chat.vue ***!
  \**************************************************************************/
/*! no static exports found */
/***/ (function(module, __webpack_exports__, __webpack_require__) {

"use strict";
__webpack_require__.r(__webpack_exports__);
/* harmony import */ var _user_chat_vue_vue_type_template_id_74b8ff5d___WEBPACK_IMPORTED_MODULE_0__ = __webpack_require__(/*! ./user-chat.vue?vue&type=template&id=74b8ff5d& */ "E:\\笔记\\HBuilderProjects\\【前端完整代码】仿糗事百科\\pages\\user-chat\\user-chat.vue?vue&type=template&id=74b8ff5d&");
/* harmony import */ var _user_chat_vue_vue_type_script_lang_js___WEBPACK_IMPORTED_MODULE_1__ = __webpack_require__(/*! ./user-chat.vue?vue&type=script&lang=js& */ "E:\\笔记\\HBuilderProjects\\【前端完整代码】仿糗事百科\\pages\\user-chat\\user-chat.vue?vue&type=script&lang=js&");
/* harmony reexport (unknown) */ for(var __WEBPACK_IMPORT_KEY__ in _user_chat_vue_vue_type_script_lang_js___WEBPACK_IMPORTED_MODULE_1__) if(__WEBPACK_IMPORT_KEY__ !== 'default') (function(key) { __webpack_require__.d(__webpack_exports__, key, function() { return _user_chat_vue_vue_type_script_lang_js___WEBPACK_IMPORTED_MODULE_1__[key]; }) }(__WEBPACK_IMPORT_KEY__));
/* harmony import */ var _D_HBuilderX_full_alpha_HBuilderX_plugins_uniapp_cli_node_modules_vue_loader_lib_runtime_componentNormalizer_js__WEBPACK_IMPORTED_MODULE_2__ = __webpack_require__(/*! ./node_modules/vue-loader/lib/runtime/componentNormalizer.js */ "./node_modules/vue-loader/lib/runtime/componentNormalizer.js");





/* normalize component */

var component = Object(_D_HBuilderX_full_alpha_HBuilderX_plugins_uniapp_cli_node_modules_vue_loader_lib_runtime_componentNormalizer_js__WEBPACK_IMPORTED_MODULE_2__["default"])(
  _user_chat_vue_vue_type_script_lang_js___WEBPACK_IMPORTED_MODULE_1__["default"],
  _user_chat_vue_vue_type_template_id_74b8ff5d___WEBPACK_IMPORTED_MODULE_0__["render"],
  _user_chat_vue_vue_type_template_id_74b8ff5d___WEBPACK_IMPORTED_MODULE_0__["staticRenderFns"],
  false,
  null,
  null,
  null
  
)

/* hot reload */
if (false) { var api; }
component.options.__file = "E:/笔记/HBuilderProjects/【前端完整代码】仿糗事百科/pages/user-chat/user-chat.vue"
/* harmony default export */ __webpack_exports__["default"] = (component.exports);

/***/ }),

/***/ "E:\\笔记\\HBuilderProjects\\【前端完整代码】仿糗事百科\\pages\\user-chat\\user-chat.vue?vue&type=script&lang=js&":
/*!***************************************************************************************************!*\
  !*** E:/笔记/HBuilderProjects/【前端完整代码】仿糗事百科/pages/user-chat/user-chat.vue?vue&type=script&lang=js& ***!
  \***************************************************************************************************/
/*! no static exports found */
/***/ (function(module, __webpack_exports__, __webpack_require__) {

"use strict";
__webpack_require__.r(__webpack_exports__);
/* harmony import */ var _D_HBuilderX_full_alpha_HBuilderX_plugins_uniapp_cli_node_modules_babel_loader_lib_index_js_D_HBuilderX_full_alpha_HBuilderX_plugins_uniapp_cli_node_modules_dcloudio_vue_cli_plugin_uni_packages_webpack_preprocess_loader_index_js_ref_12_1_D_HBuilderX_full_alpha_HBuilderX_plugins_uniapp_cli_node_modules_dcloudio_webpack_uni_mp_loader_lib_script_js_D_HBuilderX_full_alpha_HBuilderX_plugins_uniapp_cli_node_modules_vue_loader_lib_index_js_vue_loader_options_user_chat_vue_vue_type_script_lang_js___WEBPACK_IMPORTED_MODULE_0__ = __webpack_require__(/*! -!./node_modules/babel-loader/lib!./node_modules/@dcloudio/vue-cli-plugin-uni/packages/webpack-preprocess-loader??ref--12-1!./node_modules/@dcloudio/webpack-uni-mp-loader/lib/script.js!./node_modules/vue-loader/lib??vue-loader-options!./user-chat.vue?vue&type=script&lang=js& */ "./node_modules/babel-loader/lib/index.js!./node_modules/@dcloudio/vue-cli-plugin-uni/packages/webpack-preprocess-loader/index.js?!./node_modules/@dcloudio/webpack-uni-mp-loader/lib/script.js!./node_modules/vue-loader/lib/index.js?!E:\\笔记\\HBuilderProjects\\【前端完整代码】仿糗事百科\\pages\\user-chat\\user-chat.vue?vue&type=script&lang=js&");
/* harmony import */ var _D_HBuilderX_full_alpha_HBuilderX_plugins_uniapp_cli_node_modules_babel_loader_lib_index_js_D_HBuilderX_full_alpha_HBuilderX_plugins_uniapp_cli_node_modules_dcloudio_vue_cli_plugin_uni_packages_webpack_preprocess_loader_index_js_ref_12_1_D_HBuilderX_full_alpha_HBuilderX_plugins_uniapp_cli_node_modules_dcloudio_webpack_uni_mp_loader_lib_script_js_D_HBuilderX_full_alpha_HBuilderX_plugins_uniapp_cli_node_modules_vue_loader_lib_index_js_vue_loader_options_user_chat_vue_vue_type_script_lang_js___WEBPACK_IMPORTED_MODULE_0___default = /*#__PURE__*/__webpack_require__.n(_D_HBuilderX_full_alpha_HBuilderX_plugins_uniapp_cli_node_modules_babel_loader_lib_index_js_D_HBuilderX_full_alpha_HBuilderX_plugins_uniapp_cli_node_modules_dcloudio_vue_cli_plugin_uni_packages_webpack_preprocess_loader_index_js_ref_12_1_D_HBuilderX_full_alpha_HBuilderX_plugins_uniapp_cli_node_modules_dcloudio_webpack_uni_mp_loader_lib_script_js_D_HBuilderX_full_alpha_HBuilderX_plugins_uniapp_cli_node_modules_vue_loader_lib_index_js_vue_loader_options_user_chat_vue_vue_type_script_lang_js___WEBPACK_IMPORTED_MODULE_0__);
/* harmony reexport (unknown) */ for(var __WEBPACK_IMPORT_KEY__ in _D_HBuilderX_full_alpha_HBuilderX_plugins_uniapp_cli_node_modules_babel_loader_lib_index_js_D_HBuilderX_full_alpha_HBuilderX_plugins_uniapp_cli_node_modules_dcloudio_vue_cli_plugin_uni_packages_webpack_preprocess_loader_index_js_ref_12_1_D_HBuilderX_full_alpha_HBuilderX_plugins_uniapp_cli_node_modules_dcloudio_webpack_uni_mp_loader_lib_script_js_D_HBuilderX_full_alpha_HBuilderX_plugins_uniapp_cli_node_modules_vue_loader_lib_index_js_vue_loader_options_user_chat_vue_vue_type_script_lang_js___WEBPACK_IMPORTED_MODULE_0__) if(__WEBPACK_IMPORT_KEY__ !== 'default') (function(key) { __webpack_require__.d(__webpack_exports__, key, function() { return _D_HBuilderX_full_alpha_HBuilderX_plugins_uniapp_cli_node_modules_babel_loader_lib_index_js_D_HBuilderX_full_alpha_HBuilderX_plugins_uniapp_cli_node_modules_dcloudio_vue_cli_plugin_uni_packages_webpack_preprocess_loader_index_js_ref_12_1_D_HBuilderX_full_alpha_HBuilderX_plugins_uniapp_cli_node_modules_dcloudio_webpack_uni_mp_loader_lib_script_js_D_HBuilderX_full_alpha_HBuilderX_plugins_uniapp_cli_node_modules_vue_loader_lib_index_js_vue_loader_options_user_chat_vue_vue_type_script_lang_js___WEBPACK_IMPORTED_MODULE_0__[key]; }) }(__WEBPACK_IMPORT_KEY__));
 /* harmony default export */ __webpack_exports__["default"] = (_D_HBuilderX_full_alpha_HBuilderX_plugins_uniapp_cli_node_modules_babel_loader_lib_index_js_D_HBuilderX_full_alpha_HBuilderX_plugins_uniapp_cli_node_modules_dcloudio_vue_cli_plugin_uni_packages_webpack_preprocess_loader_index_js_ref_12_1_D_HBuilderX_full_alpha_HBuilderX_plugins_uniapp_cli_node_modules_dcloudio_webpack_uni_mp_loader_lib_script_js_D_HBuilderX_full_alpha_HBuilderX_plugins_uniapp_cli_node_modules_vue_loader_lib_index_js_vue_loader_options_user_chat_vue_vue_type_script_lang_js___WEBPACK_IMPORTED_MODULE_0___default.a); 

/***/ }),

/***/ "E:\\笔记\\HBuilderProjects\\【前端完整代码】仿糗事百科\\pages\\user-chat\\user-chat.vue?vue&type=template&id=74b8ff5d&":
/*!*********************************************************************************************************!*\
  !*** E:/笔记/HBuilderProjects/【前端完整代码】仿糗事百科/pages/user-chat/user-chat.vue?vue&type=template&id=74b8ff5d& ***!
  \*********************************************************************************************************/
/*! exports provided: render, staticRenderFns */
/***/ (function(module, __webpack_exports__, __webpack_require__) {

"use strict";
__webpack_require__.r(__webpack_exports__);
/* harmony import */ var _D_HBuilderX_full_alpha_HBuilderX_plugins_uniapp_cli_node_modules_vue_loader_lib_loaders_templateLoader_js_vue_loader_options_D_HBuilderX_full_alpha_HBuilderX_plugins_uniapp_cli_node_modules_dcloudio_vue_cli_plugin_uni_packages_webpack_preprocess_loader_index_js_ref_17_0_D_HBuilderX_full_alpha_HBuilderX_plugins_uniapp_cli_node_modules_dcloudio_webpack_uni_mp_loader_lib_template_js_D_HBuilderX_full_alpha_HBuilderX_plugins_uniapp_cli_node_modules_vue_loader_lib_index_js_vue_loader_options_user_chat_vue_vue_type_template_id_74b8ff5d___WEBPACK_IMPORTED_MODULE_0__ = __webpack_require__(/*! -!./node_modules/vue-loader/lib/loaders/templateLoader.js??vue-loader-options!./node_modules/@dcloudio/vue-cli-plugin-uni/packages/webpack-preprocess-loader??ref--17-0!./node_modules/@dcloudio/webpack-uni-mp-loader/lib/template.js!./node_modules/vue-loader/lib??vue-loader-options!./user-chat.vue?vue&type=template&id=74b8ff5d& */ "./node_modules/vue-loader/lib/loaders/templateLoader.js?!./node_modules/@dcloudio/vue-cli-plugin-uni/packages/webpack-preprocess-loader/index.js?!./node_modules/@dcloudio/webpack-uni-mp-loader/lib/template.js!./node_modules/vue-loader/lib/index.js?!E:\\笔记\\HBuilderProjects\\【前端完整代码】仿糗事百科\\pages\\user-chat\\user-chat.vue?vue&type=template&id=74b8ff5d&");
/* harmony reexport (safe) */ __webpack_require__.d(__webpack_exports__, "render", function() { return _D_HBuilderX_full_alpha_HBuilderX_plugins_uniapp_cli_node_modules_vue_loader_lib_loaders_templateLoader_js_vue_loader_options_D_HBuilderX_full_alpha_HBuilderX_plugins_uniapp_cli_node_modules_dcloudio_vue_cli_plugin_uni_packages_webpack_preprocess_loader_index_js_ref_17_0_D_HBuilderX_full_alpha_HBuilderX_plugins_uniapp_cli_node_modules_dcloudio_webpack_uni_mp_loader_lib_template_js_D_HBuilderX_full_alpha_HBuilderX_plugins_uniapp_cli_node_modules_vue_loader_lib_index_js_vue_loader_options_user_chat_vue_vue_type_template_id_74b8ff5d___WEBPACK_IMPORTED_MODULE_0__["render"]; });

/* harmony reexport (safe) */ __webpack_require__.d(__webpack_exports__, "staticRenderFns", function() { return _D_HBuilderX_full_alpha_HBuilderX_plugins_uniapp_cli_node_modules_vue_loader_lib_loaders_templateLoader_js_vue_loader_options_D_HBuilderX_full_alpha_HBuilderX_plugins_uniapp_cli_node_modules_dcloudio_vue_cli_plugin_uni_packages_webpack_preprocess_loader_index_js_ref_17_0_D_HBuilderX_full_alpha_HBuilderX_plugins_uniapp_cli_node_modules_dcloudio_webpack_uni_mp_loader_lib_template_js_D_HBuilderX_full_alpha_HBuilderX_plugins_uniapp_cli_node_modules_vue_loader_lib_index_js_vue_loader_options_user_chat_vue_vue_type_template_id_74b8ff5d___WEBPACK_IMPORTED_MODULE_0__["staticRenderFns"]; });



/***/ })

},[["E:\\笔记\\HBuilderProjects\\【前端完整代码】仿糗事百科\\main.js?{\"page\":\"pages%2Fuser-chat%2Fuser-chat\"}","common/runtime","common/vendor"]]]);
//# sourceMappingURL=../../../.sourcemap/mp-weixin/pages/user-chat/user-chat.js.map