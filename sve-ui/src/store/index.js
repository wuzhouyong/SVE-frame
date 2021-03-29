import Vue from "vue";
import Vuex from "vuex";
import app from "./modules/app";
import user from "./modules/user";
import tagsView from "./modules/tagsView";
import company from "./modules/company";
import label from './modules/label'
import permission from "./modules/permission";
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
    company,
    label
  },
  getters,
  state: {
    curos: {
      key: -1,
      path: "",
      systems: []
    }
  },
  plugins: [
    createPersistedState({
      storage: window.sessionStorage,
      reducer(val) {
        return {
          company: val.company,
          label: val.label
        };
      }
    })
  ]
});

export default store;
