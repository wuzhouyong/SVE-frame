import Vue from "vue";
import Vuex from "vuex";
import app from "./modules/app";
import user from "./modules/user";
import tagsView from "./modules/tagsView";
import permission from "./modules/permission";
import company from "./modules/company";
import settings from "./modules/settings";
import getters from "./getters";
import createPersistedState from "vuex-persistedstate";

Vue.use(Vuex);

const store = new Vuex.Store({
  modules: {
    app,
    user,
    tagsView,
    permission,
    settings,
    company
  },
  getters,
  state: {
    curos: {
      key: 0,
      path: ""
    }
  },
  plugins: [
    createPersistedState({
      storage: window.sessionStorage,
      reducer(val) {
        return {
          company: val.company
        };
      }
    })
  ]
});

export default store;
