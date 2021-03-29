const state = {
  searchCompanyList: [],
  searchCompanyId: "",
  saveTagId: ""
};
const mutations = {
  SAVE_SEARCH_COMPANYDATA: (state, data) => {
    const company = state.searchCompanyList.find(
      p => p.id === data.id
    );
    if (!company) {
      if (state.searchCompanyList.length > 30) {
        state.searchCompanyList.pop();
      }
      state.searchCompanyList.unshift(data);
    } else {
      if (company.order && company.order > 0) {
        company.order++;
      } else {
        company.order = 1;
      }
      state.searchCompanyList = state.searchCompanyList.sort(
        (a, b) => b.order - a.order
      );
    }
  },
  SAVE_SEARCH_COMPANYNAME: (state, data) => {
    state.searchCompanyId = data;
  },
  SAVE_TAG_ID: (state, data) => {
    state.saveTagId = data;
  }
};
const actions = {
  SaveSearchCompany: ({ commit }, data) => {
    commit("SAVE_SEARCH_COMPANYDATA", data);
  },
  SaveSelectCompanyId: ({ commit }, data) => {
    commit("SAVE_SEARCH_COMPANYNAME", data);
  },
  SaveSelectTagId: ({ commit }, data) => {
    commit("SAVE_TAG_ID", data);
  }
};
export default {
  namespaced: true,
  state,
  mutations,
  actions
};
