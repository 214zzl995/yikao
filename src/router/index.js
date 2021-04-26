import Vue from 'vue'
import VueRouter from 'vue-router'
import Index from '../views/Index.vue'
import Login from '../views/Login.vue'


const originalPush = VueRouter.prototype.push
VueRouter.prototype.push = function push(location, onResolve, onReject) {
  if (onResolve || onReject)
    return originalPush.call(this, location, onResolve, onReject)
  return originalPush.call(this, location).catch((err) => {
    if (VueRouter.isNavigationFailure(err)) {
      // resolve err
      return err
    }
    // rethrow error
    return Promise.reject(err)
  })
}

VueRouter.prototype.push = function push(location, onResolve, onReject) {
  if (onResolve || onReject) return originalPush.call(this, location, onResolve, onReject)
  return originalPush.call(this, location).catch(err => err)
}

Vue.use(VueRouter)

const routes = [{
    path: '/',
    name: 'Index',
    redirect: {
      name: 'index'
    },
    component: Index,
    children: [{
        path: '/about',
        component: () => import('../views/About.vue')
      },
      {
        path: "/UserDetails",
        name: "UserDetails",
        component: () => import('../views/UserDetails.vue')
      },
      {
        path: '/myindex',
        name: 'myindex',
        component: () => import('../views/Index_v1.vue')
      },
      {
        path: '/index',
        name: 'index',
        component: () => import('../views/Index_v2.vue')
      },
      {
        path: '/home',
        name: 'home',
        component: () => import('../views/Home.vue')
      },
      {
        path: "/history/:historyId",
        name: "History",
        component: () => import('../views/PaperHistory.vue')
      },
      {
        path: "/wrongQuestion",
        name: "WrongQuestion",
        component: () => import('../views/WrongQuestion.vue')

      },
      {
        path: '/CourseInquire',
        name: 'CourseInquire',
        component: () => import('../views/CourseInquire.vue')
      },
      {
        path: '/CourseStu',
        name: 'CourseStu',
        redirect: {
          name: 'courseStu'
        },
        component: () => import('../views/CourseStu.vue'),
        children: [{
          path: '/CourseStu/Main',
          name: 'courseStu',
          components: {
            default: () => import('../views/PaperList.vue'),
            QuestionList: () => import('../views/QuestionList.vue'),
            CourseSetting: () => import('../views/CourseSetting.vue'),
            UserList: () => import('../views/UserList.vue'),
            CourseComment: () => import('../views/CourseComment.vue'),
            TaskEdit: () => import('../views/TaskEdit.vue')
          }
        }]
      },
      {
        path: '/CourseTea',
        name: 'CourseTea',
        component: () => import('../views/CourseTea.vue'),
        redirect: {
          name: 'courseTea'
        },
        meta: {
          title: '我的课程',
          requireAuth: true // 只要此字段为true，必须做鉴权处理
        },
        children: [{
          path: '/CourseTea/Main',
          name: 'courseTea',
          components: {
            default: () => import('../views/PaperList.vue'),
            QuestionList: () => import('../views/QuestionList.vue'),
            CourseSetting: () => import('../views/CourseSetting.vue'),
            UserList: () => import('../views/UserList.vue'),
            CourseComment: () => import('../views/CourseComment.vue'),
            TaskEdit: () => import('../views/TaskEdit.vue')
          }
        }]
      }, {
        path: '/CourseTea/PaperEdit',
        name: 'PaperEdit',
        component: () => import('../views/PaperEdit.vue')

      }
    ]
  },
  {
    path: "/login",
    name: "Login",
    component: Login
  },
  {
    path: "/Examination/:paperId",
    name: "examination",
    component: () => import('../views/Examination.vue')
  },
  {
    path: "/Regist",
    name: "Regist",
    component: () => import('../views/Regist.vue')
  },
  {
    path: '/about',
    name: 'About',
    component: () => import('../views/About.vue')
  },
  {
    path: '/404',
    component: (resolve) => require(['@/views/error/404'], resolve),
    hidden: true
  },
  {
    path: '/401',
    component: (resolve) => require(['@/views/error/401'], resolve),
    hidden: true
  },
]

const router = new VueRouter({
  mode: 'history',
  base: process.env.BASE_URL,
  scrollBehavior: () => ({
    y: 0
  }),
  routes
})

export default router