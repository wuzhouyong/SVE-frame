import { index_system_config } from "./system";

const config = {
  "1002": {
    ref_type: "gc_sysfile_type",
    template_url: "/static/template.docx"
  },
  "1003": {
    ref_type: "lx_sysfile_type",
    template_url: "/static/template.docx"
  },
  "1004": {
    ref_type: "voc_sysfile_type",
    template_url: "/static/template.docx"
  },
  "1006": {
    ref_type: "yy_sysfile_type",
    template_url: "/static/template.docx"
  },
  "1012": {
    ref_type: "psh_sysfile_type",
    template_url: "/static/pshtem.rar"
  },
  "1013": {
    ref_type: "qx_sysfile_type",
    template_url: "/static/template.docx"
  },
  "1014": {
    ref_type: "jy_sysfile_type",
    template_url: "/static/template.docx"
  },
  "1015": {
    ref_type: "js_sysfile_type",
    template_url: "/static/template.docx"
  },
  "1019": {
    ref_type: "zz_sysfile_type",
    template_url: "/static/template.docx"
  },
  "1017": {
    ref_type: "water_sysfile_type",
    template_url: "/static/template.docx"
  },
  "1021": {
    ref_type: "gas_sysfile_type",
    template_url: "/static/template.docx"
  },
  "1022": {
    ref_type: "gasv_sysfile_type",
    template_url: "/static/template.docx"
  },
  "1023": {
    ref_type: "waterv_sysfile_type",
    template_url: "/static/template.docx"
  },
  "1016": {
    ref_type: "yth_sysfile_type",
    template_url: "/static/template.docx"
  }
};

export const construction_config = Object.keys(index_system_config).reduce((accumulator, current) => {
  // console.log("accumulator",accumulator);
  // console.log("current",current);
  accumulator[current] = Object.assign({}, index_system_config[current], config[current]);
  return accumulator;
}, {});
