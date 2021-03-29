import dg from "@/assets/data/dongguan";

export default {
    setAreOverlay: function (map, val, click) {
        let res = dg.features;
        for (let j = 0; j < res.length; j++) {
            let pointArray = res[j].geometry.coordinates[0];
            let arename = res[j].properties.name;
            let center = res[j].properties.center;
            //let convertor = new BMap.Convertor();
            let realPointArray = [];

            for (let i = 0; i < pointArray.length; i++) {
                let obj = pointArray[i];
                let mapPoint = new BMap.Point(obj[0], obj[1]);
                realPointArray.push(mapPoint);
            }

            let area = new BMap.Polygon(realPointArray, {
                strokeColor: "#f9941c",
                strokeWeight: 1,
                strokeOpacity: 0.5,
                fillOpacity: 0.01,
                strokeStyle: "solid",
                fillColor: "red",
            });
            //area.setPath(realPointArray);
            //console.log(click);
            if (click) {
                area.addEventListener("click", function () {
                    //area.setFillColor("blue");
                    click(arename, center);
                });
            }
            map.addOverlay(area);
        }
    },
    setDgShade: function (map) {
        let bdary = new BMap.Boundary();
        bdary.get("东莞", function (rs) {//获取行政区域
            //map.clearOverlays();//清除地图覆盖物
            let strs = new Array();
            strs = rs.boundaries[0].split(";");
            let ENWS = "";
            for (let i = 0; i < strs.length; i++) {
                ENWS += strs[i] + ";"
            }
            //蒙版
            let E_JW = "170.672126, 70.291804;";        //东
            let EN_JW = "170.672126, 81.291804;";       //东北角
            let N_JW = "105.913641, 81.291804;";        //北
            let NW_JW = "-169.604276,  81.291804;";     //西北角
            let W_JW = "-169.604276, 38.244136;";       //西
            let WS_JW = "-169.604276, -68.045308;";     //西南角
            let S_JW = "114.15563, -68.045308;";        //南
            let SE_JW = "170.672126, -68.045308 ;";     //东南角
            let ply1 = new BMap.Polygon(ENWS + E_JW + SE_JW + S_JW + WS_JW + W_JW + NW_JW + N_JW + EN_JW + E_JW, {
                strokeColor: "none",
                strokeOpacity: 0,
                fillColor: "#000",
                fillOpacity: "0.5"
            });
            map.addOverlay(ply1);
        });
    },
    getRegionCenter: function (val) {
        let res = dg.features;
        for (let j = 0; j < res.length; j++) {
            let arename = res[j].properties.name;
            let center = res[j].properties.center;
            if (arename == val)
                return center;
        }
        return null;
    },
    setMarkInView: function (map, markers, click) {
        if (map.getZoom() >= 14) {
            this.clearMarkers(map);
            //let marlabels = [];
            for (let i = 0; i < markers.length; i++) {
                let newpoint = new BMap.Point(markers[i].lon, markers[i].lat);
                let marker = new BMap.Marker(newpoint);
                if (this.isPointInRect(newpoint, map.getBounds())) {
                    marker.name = "m_" + markers[i].id;
                    let label = new BMap.Label(markers[i].name, {offset: new BMap.Size(20, -10)});
                    label.setStyle({
                        color: "rgba(255,255,255)",
                        fontSize: "10px",
                        backgroundColor: "rgba(14,20,55,0.51)",
                        padding: "2px",
                        border: "0",
                        borderRadius: "3px",
                        cursor: "pointer"
                    });
                    if (map.getZoom() > 15) {
                        marker.setLabel(label);
                    } else {
                        marker.addEventListener("mouseover", function () {
                            marker.setLabel(label);
                        });
                        marker.addEventListener("mouseout", function () {
                            label.hide();
                        });
                    }
                    if (click) {
                        marker.addEventListener("click", function () {
                            click(markers[i].id, markers[i].name);
                        });
                    }
                    map.addOverlay(marker);
                }
            }
        } else
            this.clearMarkers(map);
    },
    setMarkInViewFast: function (map, markers, click) {
        const iocP = new BMap.Icon(iocPlane, new BMap.Size(40, 40));

        if (map.getZoom() >= 14) {
            //console.log("change map marks");
            let onPoints = this.clearMarkersNotInView(map);
            //console.log(onPoints.length);
            let inMarks = [];
            for (let i = 0; i < markers.length; i++) {
                let mtype = markers[i].type;
                if (mtype !== 1) {
                    if (map.getZoom() < 15)
                        continue;
                }

                let newpoint = new BMap.Point(markers[i].lon, markers[i].lat);
                if (this.isPointInRect(newpoint, map.getBounds())) {
                    let marker;
                    if (mtype === 1)
                        marker = new BMap.Marker(newpoint, {icon: iocP});
                    else
                        marker = new BMap.Marker(newpoint);

                    marker.name = "m_" + markers[i].id;
                    if (onPoints.indexOf(marker.name) === -1) {
                        let label = new BMap.Label(markers[i].name, {offset: new BMap.Size(20, 0)});
                        label.setStyle({
                            color: "rgba(255,255,255)",
                            fontSize: "10px",
                            backgroundColor: "rgba(14,20,55,0.51)",
                            padding: "2px",
                            border: "0",
                            borderRadius: "3px",
                            cursor: "pointer"
                        });
                        if (map.getZoom() > 16) {
                            marker.setLabel(label);
                        } else {
                            marker.addEventListener("mouseover", function () {
                                marker.setLabel(label);
                            });
                            marker.addEventListener("mouseout", function () {
                                label.hide();
                            });
                        }
                        if (click) {
                            marker.addEventListener("click", function () {
                                click(markers[i].id, markers[i].name, markers[i].type);
                            });
                        }
                        inMarks.push(marker);
                    }
                }
            }
            inMarks.forEach(function (v) {
                map.addOverlay(v);
            })
        }

        this.clearMarkersOnZoom(map);
    },
    clearMarkersNotInView: function (map) {
        let allmap = map.getOverlays();
        let bound = map.getBounds();
        let inPoints = [];
        for (let i = 0; i < allmap.length; i++) {
            let markview = allmap[i];
            if (markview.toString() === "[object Marker]") {
                if (!this.isPointInRect(markview.point, bound))
                    map.removeOverlay(markview);
                else
                    inPoints.push(markview.name);
            }
        }
        // console.log(inPoints);
        return inPoints;
    },
    setMarkPoint: function (map) {
        let point = new BMap.Point(
            113.86505126953125,
            22.99948365856307
        );
        let marker = new BMap.Marker(point);
        map.addOverlay(marker);
    },
    isPointInRect: function (point, bounds) {
        let sw = bounds.getSouthWest();
        let ne = bounds.getNorthEast();
        return (point.lng >= sw.lng && point.lng <= ne.lng && point.lat >= sw.lat && point.lat <= ne.lat);
    },
    isPointInPoly: function (point, polygonPatch) {
        //console.log(point);
        let pts = polygonPatch;//获取多边形点
        let N = pts.length;
        let boundOrVertex = true; //如果点位于多边形的顶点或边上，也算做点在多边形内，直接返回true
        let intersectCount = 0;//cross points count of x
        let precision = 2e-10; //浮点类型计算时候与0比较时候的容差
        let p1, p2;//neighbour bound vertices
        let p = point; //测试点

        p1 = pts[0];//left vertex
        for (let i = 1; i <= N; ++i) {//check all rays
            if (p.equals(p1)) {
                return boundOrVertex;//p is an vertex
            }

            p2 = pts[i % N];//right vertex
            if (p.lat < Math.min(p1.lat, p2.lat) || p.lat > Math.max(p1.lat, p2.lat)) {//ray is outside of our interests
                p1 = p2;
                continue;//next ray left point
            }

            if (p.lat > Math.min(p1.lat, p2.lat) && p.lat < Math.max(p1.lat, p2.lat)) {//ray is crossing over by the algorithm (common part of)
                if (p.lng <= Math.max(p1.lng, p2.lng)) {//x is before of ray
                    if (p1.lat === p2.lat && p.lng >= Math.min(p1.lng, p2.lng)) {//overlies on a horizontal ray
                        return boundOrVertex;
                    }

                    if (p1.lng === p2.lng) {//ray is vertical
                        if (p1.lng === p.lng) {//overlies on a vertical ray
                            return boundOrVertex;
                        } else {//before ray
                            ++intersectCount;
                        }
                    } else {//cross point on the left side
                        let xinters = (p.lat - p1.lat) * (p2.lng - p1.lng) / (p2.lat - p1.lat) + p1.lng;//cross point of lng
                        if (Math.abs(p.lng - xinters) < precision) {//overlies on a ray
                            return boundOrVertex;
                        }

                        if (p.lng < xinters) {//before ray
                            ++intersectCount;
                        }
                    }
                }
            } else {//special case when ray is crossing through the vertex
                if (p.lat === p2.lat && p.lng <= p2.lng) {//p crossing over p2
                    let p3 = pts[(i + 1) % N]; //next vertex
                    if (p.lat >= Math.min(p1.lat, p3.lat) && p.lat <= Math.max(p1.lat, p3.lat)) {//p.lat lies between p1.lat & p3.lat
                        ++intersectCount;
                    } else {
                        intersectCount += 2;
                    }
                }
            }
            p1 = p2;//next ray left point
        }

        return intersectCount % 2 !== 0;
    },
    clearMarkers: function (map) {
        let allmap = map.getOverlays();
        for (let i = 0; i < allmap.length; i++) {
            let mapview = allmap[i];
            if (mapview.toString() === "[object Marker]") {
                map.removeOverlay(mapview);
            }
        }

    },
    clearMarkersOnZoom: function (map) {
        let room = map.getZoom();
        let allmap = map.getOverlays();
        if (room <= 14) {
            for (let i = 0; i < allmap.length; i++) {
                let mapview = allmap[i];
                if (mapview.toString() === "[object Marker]") {
                    if (mapview.name.indexOf('m_c_') < 0 && room < 15)
                        map.removeOverlay(mapview);
                    if (mapview.name.indexOf('m_c_') >= 0 && room < 14)
                        map.removeOverlay(mapview);

                }
            }
        }
    },
    hideMarkersRect(overlay, bounds) {
        let allmap = map.getOverlays();
        for (let i = 0; i < overlay.length; i++) {
            let mapview = allmap[i];
            if (allmap[i].toString() == "[object Label]") {
                mapview.show();
            }
        }
        for (let i = 0; i < points.length; i++) {
            let newpoint = new BMap.Point(points[i].lon, points[i].lat);
            if (this.isPointInRect(newpoint, bounds)) {
                points[i].show();
            } else {
                points[i].hide();
            }
        }

    }
}
