import router from './router'
import store from './store'
import {
  Message,
  LoadingBar
} from 'view-design'
import {
  getToken
} from '@/utils/auth'



LoadingBar.config({
  color: '#5cb85c',
  failedColor: '#f0ad4e',
  height: 3
})

const whiteList = ['/login', '/Regist']

router.beforeEach((to, from, next) => {
  LoadingBar.start()
  if (getToken()) {
    /* has token*/
    if (to.path === '/login') {
      next({
        path: '/'
      })
      LoadingBar.finish()
    } else if (to.matched.length === 0) {
      next('/404')
    } else {
      if (store.getters.roles.length === 0) {
        store.dispatch('GetInfo').then(res => {
          if (permission(to)) {
            next();
          } else {
            next({
              path: '/401'
            })
          }
        }).catch(err => {
          store.dispatch('LogOut').then(() => {
            Message.error(err)
            next({
              path: '/'
            })
          })
        })
      } else {
        if (permission(to)) {
          next();
        } else {
          next({
            path: '/401'
          })
        }
      }
    }
  } else {
    // 没有token
    if (whiteList.indexOf(to.path) !== -1) {
      // 在免登录白名单，直接进入
      next()
    } else {
      next(`/login?redirect=${to.fullPath}`) // 否则全部重定向到登录页
      LoadingBar.finish()
    }
  }
})

function permission(to) {
  if (to.matched.some(res => res.meta.requireAuth)) {
    var role = store.getters.roles;

    if (roleHas(role, 'admin')) {
      return true;
    } else if (roleHas(role, 'teacher')) {

      var course = JSON.parse(store.getters.userDetails.myCourse)
      console.log(course)
      var courseId = to.query.CourseId;
      if (courseId == undefined) {
        return false;
      } else {
        return (roleHas(course, parseInt(courseId)));
      }
    } else {
      return false;
    }
  } else {
    return true;
  }
}


function roleHas(array, str) {
  // var index = array.findIndex(item => item.id === str);
  var index = array.indexOf(str) // 当数组里的对象为字符串时用这个方法更简单
  return index == -1 ? false : true // -1 说明 array 中不存在 id 为 str 的对象
}

router.afterEach(() => {
  LoadingBar.finish()
})