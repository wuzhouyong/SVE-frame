const state = {
  searchData: [],
  assignData: {}
};
const mutations = {
  SAVE_LABEL_DATA: (state, data) => {
    state.searchData = data.searchData;
    state.assignData = data.assignData;
  },
  CLEAR_LABEL_DATA: (state) => {
    state.searchData = [];
    state.assignData = {};
  }
};
const actions = {
  SaveLabelData: ({commit}, data) => {
    commit("SAVE_LABEL_DATA", data);
  },
  ClearLabelData: ({commit}) => {
    commit('CLEAR_LABEL_DATA');
  }
};
export default {
  namespaced: true,
  state,
  mutations,
  actions
};
