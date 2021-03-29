import router from './router'
import store from './store'
import {Message} from 'element-ui'
import NProgress from 'nprogress'
import 'nprogress/nprogress.css'
import {getToken, setToken} from '@/utils/auth'
import {getQueryVariable} from '@/utils/system'
import {getRoutersSystem} from "@/api/menu";
import Layout from '@/layout/index'
import Cookies from "js-cookie"

NProgress.configure({showSpinner: false})

const whiteList = ['/app_login', '/login', '/auth-redirect', '/bind', '/register']
let indexRoute = [{
  path: '',
  component: Layout,
  redirect: '',
  children: []
}];

router.beforeEach(async (to, from, next) => {
  NProgress.start()
  if (getToken()) {
    /* has token*/
    if (to.path === '/login') {
      next({path: '/'})
      NProgress.done()
    } else {
      // console.log(JSON.stringify(router.options.routes));
      if (store.getters.roles.length === 0) {
        // 判断当前用户是否已拉取完user_info信息
        const user = await store.dispatch('GetInfo');
        // console.log("user", user);
        const roles = user.roles;
        const sys_routes = await getRoutersSystem();
        // console.log("sys_routes", sys_routes);
        sys_routes.data.forEach(item => {
          //系统主页
          if (!store.state.curos.systems.includes(-1)) {
            if (item.path !== null && item.component !== null) {
              indexRoute[0].children.push({
                path: item.path,
                component: (resolve) => require([`@/views/${item.component}`], resolve),
                name: item.menuName,
                meta: {title: '首页', icon: 'dashboard', noCache: true, affix: true}
              });
            }
          }
        });

        if (!store.state.curos.systems.includes(-1)) {
          router.addRoutes(indexRoute);
          store.state.curos.systems.push(-1);
        }
        const access_routes = await store.dispatch('GenerateRoutes', {roles});
        // console.log("access_routes", access_routes);
        store.state.curos.systems.push(store.state.curos.key);
        router.addRoutes(access_routes) // 动态添加可访问路由表
        next({...to, replace: true}) // hack方法 确保addRoutes已完成
      } else {
        next()
        // 没有动态改变权限的需求可直接next() 删除下方权限判断 ↓
        // if (hasPermission(store.getters.roles, to.meta.roles)) {
        //   next()
        // } else {
        //   next({ path: '/401', replace: true, query: { noGoBack: true }})
        // }
        // 可删 ↑
      }
    }
  } else {
    // 没有token
    let k = getQueryVariable("k");
    if (k) {
      setToken(k);
      next();
    }
    if (whiteList.indexOf(to.path) !== -1) {
      // 在免登录白名单，直接进入
      next()
    } else {
      next(`/login?redirect=${to.path}`) // 否则全部重定向到登录页
      NProgress.done()
    }
  }
})

router.afterEach(() => {
  NProgress.done()
})
