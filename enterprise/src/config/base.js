import {system_config} from "./system";

export const base_config = {
  "/base/collect": {
    systems: Object.keys(system_config).filter(
      p => !["1017", "1021", "1001"].some(e => e === p)
    )
  },
  "/base/outlet": {
    systems: Object.keys(system_config).filter(p =>
      ["1013", "1014"].some(e => e === p)
    )
  },
  "/base/element": {
    systems: Object.keys(system_config).filter(
      p => !["1012", "1017", "1021", "1001"].some(e => e === p)
    )
  },
  "/base/video": {
    systems: Object.keys(system_config).filter(
      p => !["1006", "1012", "1004", "1017", "1021"].some(e => e === p)
    )
  },
  "/warn/process": {
    systems: Object.keys(system_config).filter(
      p => !["1017", "1021", "1001"].some(e => e === p)
    )
  },
  //零星
  "/base/lxfs/tank": {
    systems: Object.keys(system_config).filter(p => p === "1003")
  },
  "/base/lxfs/watermeter": {
    systems: Object.keys(system_config).filter(p => p === "1003")
  },
  "/base/lxfs/transshipment": {
    systems: Object.keys(system_config).filter(p => p === "1003")
  },
  "/base/lxfs/sewagemeter": {
    systems: Object.keys(system_config).filter(p => p === "1003")
  },
  //voc
  "/base/vocs/production": {
    systems: Object.keys(system_config).filter(p => p === "1004")
  },
  "/base/vocs/discharge": {
    systems: Object.keys(system_config).filter(p => p === "1004")
  },
  "/base/vocs/gather": {
    systems: Object.keys(system_config).filter(p => p === "1004")
  },
  "/base/vocs/govern": {
    systems: Object.keys(system_config).filter(p => p === "1004")
  },
  "/base/vocs/disport": {
    systems: Object.keys(system_config).filter(p => p === "1004")
  },
  //油烟
  "/base/yy/port": {
    systems: Object.keys(system_config).filter(p => p === "1006")
  },
  //在线监控
  "/base/om/checkpoint": {
    systems: Object.keys(system_config).filter(p =>
      ["1001", "1017", "1021"].some(e => e === p)
    )
  },
  "/base/om/device": {
    systems: Object.keys(system_config).filter(p =>
      ["1001"].some(e => e === p)
    )
  },
  "/base/om/element": {
    systems: Object.keys(system_config).filter(p =>
      ["1001"].some(e => e === p)
    )
  },
  "/base/om/apply": {
    systems: Object.keys(system_config).filter(p =>
      ["1001"].some(e => e === p)
    )
  },
  "/base/om/report": {
    systems: Object.keys(system_config).filter(p =>
      ["1001"].some(e => e === p)
    )
  },
  "/base/om/operation": {
    systems: Object.keys(system_config).filter(p =>
      ["1001"].some(e => e === p)
    )
  },
  "/base/om/facilities": {
    systems: Object.keys(system_config).filter(p =>
      ["1001"].some(e => e === p)
    )
  },
  "/base/om/task": {
    systems: Object.keys(system_config).filter(p =>
      ["1001"].some(e => e === p)
    )
  },
  "/base/om/acceptance": {
    systems: Object.keys(system_config).filter(p =>
      ["1001"].some(e => e === p)
    )
  }
};
