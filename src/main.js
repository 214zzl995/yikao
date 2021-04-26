import Vue from 'vue'
import App from './App.vue'
import router from './router'
import store from './store'
import ViewUI from 'view-design';
import VueRouter from 'vue-router';
import 'view-design/dist/styles/iview.css';
import less from 'less'
import VueParticles from 'vue-particles'
import axios from 'axios'
import permission from './directive/permission'
import './permission'
import {
  parseTime,
  resetForm,
  addDateRange,
  selectDictLabel,
  selectDictLabels,
  download,
  handleTree,
  deepCompare
} from "@/utils/ruoyi";
import animated from 'animate.css'


import websocket from './store/websocket'
Vue.use(animated)

Vue.prototype.$websocket = websocket;
Vue.prototype.parseTime = parseTime;
Vue.prototype.resetForm = resetForm;
Vue.prototype.addDateRange = addDateRange;
Vue.prototype.selectDictLabel = selectDictLabel;
Vue.prototype.selectDictLabels = selectDictLabels;
Vue.prototype.download = download;
Vue.prototype.handleTree = handleTree;
Vue.prototype.deepCompare = deepCompare;


Vue.use(VueParticles);
Vue.use(less);

Vue.use(VueRouter);
Vue.use(ViewUI);
Vue.use(permission)

Vue.prototype.$axios = axios;

Vue.config.productionTip = false;

Date.prototype.format = function (fmt) {
  var o = {
    "M+": this.getMonth() + 1, // 月份
    "d+": this.getDate(), // 日
    "h+": this.getHours() % 12 == 0 ? 12 : this.getHours() % 12, // 小时
    "H+": this.getHours(), // 小时
    "m+": this.getMinutes(), // 分
    "s+": this.getSeconds(), // 秒
    "q+": Math.floor((this.getMonth() + 3) / 3), // 季度
    "S": this.getMilliseconds() // 毫秒
  };
  if (/(y+)/.test(fmt))
    fmt = fmt.replace(RegExp.$1, (this.getFullYear() + "").substr(4 - RegExp.$1.length));
  for (var k in o)
    if (new RegExp("(" + k + ")").test(fmt))
      fmt = fmt.replace(RegExp.$1, (RegExp.$1.length == 1) ? (o[k]) : (("00" + o[k]).substr(("" + o[k]).length)));
  return fmt;
}

const RouterConfig = {
  routes: router
};

Vue.prototype.$praseStrEmpty = function (str) {
  if (typeof str === 'undefined' || str === null) {
    return "";
  }
  return str;
}

new Vue({
  router,
  store,
  render: h => h(App)
}).$mount('#app')