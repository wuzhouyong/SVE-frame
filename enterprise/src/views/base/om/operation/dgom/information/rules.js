
// const validateElement = (rule, value, callback) => {
//     if (value.substring(0, 4) !== this.form.eleTypeId) {
//         callback(new Error("因子编码前四位必须和因子类型对应!"));
//     } else if (value.length !== 6) {
//         callback(new Error("因子编码长度为6位!"));
//     } else {
//         callback();
//     }
// };
export default  {
    checkpointid: [
        {
            required: true,
            message: "监测点不能为空",
            trigger: "change",
        },
    ],
    sourcename: [
        {
            required: true,
            message: "排污单位不能为空",
            trigger: "blur",
        },
    ],
 
    // sourceenvhead: [
    //     {
    //         required: true,
    //         message: "环保联系人不能为空",
    //         trigger: "blur",
    //     },
    // ],
    // sourceenvheadinfo: [
    //     {
    //         required: true,
    //         message: "联系方式不能为空",
    //         trigger: "blur",

    //     },
    //     // { validator: validateElement }
    // ],
    // opcompanyname: [
    //     {
    //         required: true,
    //         message: "运营单位不能为空",
    //         trigger: "blur",
    //     },

    // ],
    // opcompanyhead: [
    //     {
    //         required: true,
    //         message: "单位负责人不能为空",
    //         trigger: "blur",
    //     },

    // ],
    // opcompanyheadinfo: [
    //     {
    //         required: true,
    //         message: "联系方式不能为空",
    //         trigger: "blur",
    //     },

    // ],
    // checkpointname: [
    //     {
    //         required: true,
    //         message: "排污口名称不能为空",
    //         trigger: "blur",
    //     },

    // ],
    // oppersonname: [
    //     {
    //         required: true,
    //         message: "运维姓名不能为空",
    //         trigger: "blur",
    //     },

    // ],
    // oppersontel: [
    //     {
    //         required: true,
    //         message: "联系电话不能为空",
    //         trigger: "blur",
    //     },

    // ],
    // opstartdate: [
    //     {
    //         required: true,
    //         message: "运维开始时间不能为空",
    //         trigger: "change",
      
    //     },

    // ],
    // openddate: [
    //     {
    //         required: true,
    //         message: "运维结束时间不能为空",
    //         trigger: "change",
 
    //     },

    // ],
    // oplicensenum: [
    //     {
    //         required: true,
    //         message: "运营证编号不能为空",
    //         trigger: "blur",
    //     },

    // ],
    // oplicissueunit: [
    //     {
    //         required: true,
    //         message: "发证单位不能为空",
    //         trigger: "blur",
    //     },

    // ],
    // oplicissuedate: [
    //     {
         
    //         required: true,
    //         message: "发证时间不能为空",
    //         trigger: "change",
    //     },

    // ],
    // opnolicreason: [
    //     {
    //         required: true,
    //         message: "无证原因不能为空",
    //         trigger: "blur",
    //     },

    // ],
}
