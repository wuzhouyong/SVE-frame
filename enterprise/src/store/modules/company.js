const state = {
  searchCompanyList: []
};
const mutations = {
  SAVE_SEARCH_COMPANYDATA: (state, data) => {
    const company = state.searchCompanyList.find(p => p.source_id === data.source_id);
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
  }
};
const actions = {
  SaveSearchCompany: ({ commit }, data) => {
    commit("SAVE_SEARCH_COMPANYDATA", data);
  }
};
export default {
  namespaced: true,
  state,
  mutations,
  actions
};
