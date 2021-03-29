import { constantRoutes } from "@/router";
import { getRouters } from "@/api/menu";
import Layout from "@/layout/index";
import { getRoutersBySystem } from "../../api/menu";
import store from "@/store";
import Cookies from "js-cookie";

const permission = {
  state: {
    routes: [],
    addRoutes: []
  },
  mutations: {
    SET_ROUTES: (state, routes) => {
      state.addRoutes = routes;
      state.routes = constantRoutes.concat(routes);
    }
  },
  actions: {
    // 生成路由
    GenerateRoutes({ commit }) {
      return new Promise(resolve => {
        // 向后端请求路由数据
        // getRouters().then(res => {
        //   console.log(res);
        //   const accessedRoutes = filterAsyncRouter(res.data)
        //   accessedRoutes.push({ path: '*', redirect: '/404', hidden: true })
        //   commit('SET_ROUTES', accessedRoutes)
        //   resolve(accessedRoutes)
        // })

        // 向后端请求路由数据
        let key = Cookies.get("system-key") ? Cookies.get("system-key") : -1;
        getRoutersBySystem({ parentId: key }).then(res => {
          //console.log(res);
          if (res.code !== 200) return;

          store.state.curos.key = res.key;
          store.state.curos.path = res.path;
          const accessedRoutes = filterAsyncRouter(res.data);
          Cookies.set("system-key", res.key);
          Cookies.set("system-name", res.path);
          accessedRoutes.push({
            path: "*",
            component: resolve => require(["@/views/error/404"], resolve),
            hidden: true
          });
          commit("SET_ROUTES", accessedRoutes);
          resolve(accessedRoutes);
        });
      });
    }
  }
};

// 遍历后台传来的路由字符串，转换为组件对象
function filterAsyncRouter(asyncRouterMap) {
  return asyncRouterMap.filter(route => {
    if (route.component) {
      // Layout组件特殊处理
      if (route.component === "Layout") {
        route.component = Layout;
      } else {
        route.component = loadView(route.component);
      }
    }
    if (route.children != null && route.children && route.children.length) {
      route.children = filterAsyncRouter(route.children);
    }
    return true;
  });
}

export const loadView = view => {
  // 路由懒加载
  return resolve => require([`@/views/${view}`], resolve);
};

export default permission;
