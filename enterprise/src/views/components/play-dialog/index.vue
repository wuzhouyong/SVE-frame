<template>
    <el-dialog
        custom-class="video-dialog"
        :title="title"
        append-to-body
        :visible.sync="videoVisible"
        width="600px"
        @close="flv_destroy"
    >
        <div style="margin:0 auto;width:600px;height:400px;">
            <div id="ezviz" v-if="isCloud"></div>
            <video
                id="twAgent"
                v-else
                autoplay
                style="width:100%; height:100%; object-fit: fill"
            ></video>
        </div>
    </el-dialog>
</template>

<script>
import flvjs from "flv.js";

export default {
    name: "play-dialog",
    props: {
        title: {
            type: String
        },
        url: {
            type: String
        },
        token: {
            type: String
        },
        videoType: {
            type: String
        },
        visible: {
            type: Boolean,
            default: false
        },
        isCloud: {
            type: Boolean,
            default: false
        }
    },
    data() {
        return {
            plays: null
        };
    },
    computed: {
        sys_name() {
            return this.$route.path.substr(this.$route.path.lastIndexOf("/") + 1);
        },
        videoVisible: {
            get() {
                return !!this.visible;
            },
            set(v) {
                this.$emit("update:visible", v);
            }
        }
    },
    watch: {
        visible() {
            if (this.visible) {
                this.$nextTick(() => this.playVideo());
            }
        }
    },
    methods: {
        playVideo() {
            this.isCloud ? this.ezvizPlay() : this.twAgentPlay();
        },
        ezvizPlay() {
            const option = {
                id: "ezviz",
                url: this.url,
                autoplay: true,
                accessToken: this.token,
                decoderPath: "/static",
                width: 600,
                height: 400
            };
            this.player = new EZUIPlayer(option);
        },
        twAgentPlay() {
            const option = {
                type: "flv",
                isLive: true,
                hasVideo: true,
                hasAudio: false,
                withCredentials: false,
                isLive: true,
                url: this.url
            };
            this.flv_load_mds(option);
        },
        flv_load_mds(option) {
            var element = document.getElementById("twAgent");
            this.plays = flvjs.createPlayer(option, {
                enableWorker: false,
                lazyLoadMaxDuration: 3 * 60,
                enableStashBuffer: false,
                lazyLoad: false,
                lazyLoadRecoverDuration: 1,
                deferLoadAfterSourceOpen: false,
                statisticsInfoReportInterval: 1,
                fixAudioTimestampGap: false,
                stashInitialSize: 128
            });
            this.$nextTick(() => {
                this.plays.attachMediaElement(element);
                this.plays.load();
            });
            window.onerror = errorMessage => {
                this.flv_destroy();
                this.msgError("连接视频服务器失败!");
                return true;
            };
        },
        flv_destroy() {
            if (this.plays) {
                this.plays.pause();
                this.plays.unload();
                this.plays.detachMediaElement();
                this.plays.destroy();
                this.plays = null;
            }
        }
    }
};
</script>

<style lang="scss">
.video-dialog {
    .el-dialog__body {
        padding: 0;
    }
}
</style>