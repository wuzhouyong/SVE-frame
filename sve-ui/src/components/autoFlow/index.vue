<template>
  <div>
    <flow-panel></flow-panel>
  </div>
</template>

<script>
  import FlowPanel from "./components/flowPanel";
  export default {
    name: "index",
    components: {FlowPanel},
    data() {
      return {
        flows: [
          {
            "facilityId": 1,
            "facilityType": "yy_yc_1",
            "facilityName": "油烟排放口口1",
            "list": [
              {
                "facilityId": 11,
                "facilityType": "yy_fj_11",
                "facilityName": "油烟风机1",
                "list": [
                  {
                    "facilityId": 21,
                    "facilityType": "yy_jhq_21",
                    "facilityName": "油烟净化器1"
                  }
                ]
              }
            ]
          },
          {
            "facilityId": 2,
            "facilityType": "yy_yc_2",
            "facilityName": "油烟排放口口2",
            "list": [
              {
                "facilityId": 12,
                "facilityType": "yy_fj_12",
                "facilityName": "油烟风机2",
                "list": [
                  {
                    "facilityId": 22,
                    "facilityType": "yy_jhq_22",
                    "facilityName": "油烟净化器2"
                  }
                ]
              },
              {
                "facilityId": 13,
                "facilityType": "yy_fj_13",
                "facilityName": "油烟风机3",
                "list": [
                  {
                    "facilityId": 23,
                    "facilityType": "yy_jhq_23",
                    "facilityName": "油烟净化器3"
                  }
                ]
              }
            ]
          },
          {
            "facilityId": 3,
            "facilityType": "yy_yc_3",
            "facilityName": "油烟排放口口3",
            "list": [
              {
                "facilityId": 14,
                "facilityType": "yy_fj_14",
                "facilityName": "油烟风机4",
                "list": [
                  {
                    "facilityId": 24,
                    "facilityType": "yy_jhq_24",
                    "facilityName": "油烟净化器4"
                  },
                  {
                    "facilityId": 25,
                    "facilityType": "yy_jhq_25",
                    "facilityName": "油烟净化器5"
                  }
                ]
              }
            ]
          },
          {
            "facilityId": 4,
            "facilityType": "yy_yc_4",
            "facilityName": "油烟排放口口4",
            "list": [
              {
                "facilityId": 15,
                "facilityType": "yy_fj_15",
                "facilityName": "油烟风机5",
                "list": [
                  {
                    "facilityId": 26,
                    "facilityType": "yy_jhq_26",
                    "facilityName": "油烟净化器6",
                    "list": [
                      {
                        "facilityId": 16,
                        "facilityType": "yy_fj_16",
                        "facilityName": "油烟风机6",
                        "list": [
                          {
                            "facilityId": 27,
                            "facilityType": "yy_jhq_27",
                            "facilityName": "油烟净化器7"
                          }
                        ]
                      }
                    ]
                  }
                ]
              }
            ]
          }
        ]
      }
    },
    mounted() {

    }
  }
</script>

<style lang="scss" scoped>

</style>
