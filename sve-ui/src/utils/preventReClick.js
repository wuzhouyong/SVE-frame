//阻止多次点击重复提交
export default {
  install(Vue) {
    // 防止重复点击
    Vue.directive("preventClick", {
      inserted(el) {
        el.addEventListener("click", () => {
          if (!el.disabled) {
            el.disabled = true;
            setTimeout(() => {
              el.disabled = false;
            }, 4000);
          }
        });
      }
    });
  },
  //防抖
  debounce(func, wait = 1000) {
    let timeout = null
    return function() {
      let context = this
      let args = arguments
      if (timeout) clearTimeout(timeout)
      timeout = setTimeout(() => {
        func.apply(context, args)
      }, wait)
    }
  },
  //节流
  throttle(func, wait = 1000) {
    let timeout = null
    return function() {
      let context = this
      let args = arguments
      if (!timeout) {
        timeout = setTimeout(() => {
          timeout = null
          func.apply(context, args)
        }, wait)
      }
    }
  }
};
