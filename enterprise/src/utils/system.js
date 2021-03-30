
export function getQueryVariable(variable) {
  let query = window.location.search.substring(1);
  let vars = query.split("&");
  for (let i = 0; i < vars.length; i++) {
    let pair = vars[i].split("=");
    if (pair[0] === variable) {
      return pair[1];
    }
  }
  return false;
}

export function isFrame() {
  let k = getQueryVariable("k");
  // console.log('getQueryVariable:'+k);
  return k!==false;
}
