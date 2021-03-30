import request from "@/utils/request";

export function procstate(prefix) {
    return request({
        url: `/${prefix}/transfer/procstate`,
        method: "get"
    });
}

export function chart(prefix) {
    return request({
        url: `/${prefix}/transfer/chart`,
        method: "get"
    });
}

export function real(prefix) {
    return request({
        url: `/${prefix}/transfer/real`,
        method: "get"
    });
}

export function minute(prefix) {
    return request({
        url: `/${prefix}/transfer/minute`,
        method: "get"
    });
}

export function hour(prefix) {
    return request({
        url: `/${prefix}/transfer/hour`,
        method: "get"
    });
}

export function day(prefix) {
    return request({
        url: `/${prefix}/transfer/day`,
        method: "get"
    });
}