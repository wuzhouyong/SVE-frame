const getters = {
  sidebar: state => state.app.sidebar,
  size: state => state.app.size,
  device: state => state.app.device,
  visitedViews: state => state.tagsView.visitedViews,
  cachedViews: state => state.tagsView.cachedViews,
  token: state => state.user.token,
  avatar: state => state.user.avatar,
  name: state => state.user.name,
  introduction: state => state.user.introduction,
  sourceId: state => state.user.sourceId,
  permissions: state => state.user.permissions,
  permission_routes: state => state.permission.routes,
  isBinding:state => state.user.isBinding,
  mobile:state => state.user.mobile,
  clickStatus:state=>state.user.clickStatus
}
export default getters
