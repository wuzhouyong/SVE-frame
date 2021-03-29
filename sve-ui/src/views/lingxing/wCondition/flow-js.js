//更新大小
export function flowResize(sizePercent) {
  $(".flow-canvas").css({
    transform: "scale(" + sizePercent + ", " + sizePercent + ")",
    "-ms-transform": "scale(" + sizePercent + ", " + sizePercent + ")",
    "-webkit-transform": "scale(" + sizePercent + ", " + sizePercent + ")"
  });
  //var top = $(".flow-canvas").height() * (sizePercent - 1) / 2;
  //$(".flow-canvas").css("top", top);
  //var left = $(".flow-canvas").width() * (sizePercent - 1) / 2;
  //$(".flow-canvas").css("left", left);
  $(".flow-canvas").css(
    "left",
    ($(".flow-canvas")
        .parent()
        .width() -
      $(".flow-canvas").width()) /
    4
  );
  $(".flow-canvas").css(
    "top",
    ($(".flow-canvas")
        .parent()
        .height() -
      $(".flow-canvas").height()) /
    2 +
    10
  );
}

//更新数据
export function flowRefresh(data) {
  $.each(data, function(name, val) {
    var ele = $(".flow-canvas").find("#" + name);
    if (ele.length > 0) {
      var classtype = ele.prop("class");
      if (classtype.indexOf("label") >= 0) {
        ele.html(ele.attr("format").replace("{}", val));
        //ele.attr("eval", val);
      }
      if (name.indexOf("SW") == 0) {
        $("[groups='" + ele.attr("groups") + "']").each(function(index, el) {
          // console.log(el);
          var ptype = $(el)
            .parent()
            .prop("class");
          if (ptype.indexOf("flow-element") >= 0) {
            if (val) {
              $(el).addClass("flow-start");
              $(el).removeClass("flow-stop");
            } else {
              $(el).addClass("flow-stop");
              $(el).removeClass("flow-start");
            }
          }
          if (ptype.indexOf("flow-pipeline") >= 0) {
            if (val) {
              $(el).addClass("pipe-start");
              $(el).removeClass("pipe-stop");
            } else {
              $(el).addClass("pipe-stop");
              $(el).removeClass("pipe-start");
            }
          }
        });
      }
    }
  });
}

export function setMove() {
  $(".flow-element div").each(function(index, el) {
    console.log($(el).attr("ename"));
    dragPanelMove(el);
  });
  $(".flow-pipeline div").each(function(index, el) {
    console.log($(el).attr("ename"));
    dragPanelMove(el);
  });
  $(".flow-label div").each(function(index, el) {
    console.log($(el).attr("ename"));
    dragPanelMove(el);
  });
  $(".flow-other div").each(function(index, el) {
    console.log($(el).attr("ename"));
    dragPanelMove(el);
  });
}

export function dragPanelMove(moveDiv) {
  $(moveDiv).mousedown(function(e) {
    var isMove = true;
    var div_x = e.pageX - $(moveDiv).offset().left;
    var div_y = e.pageY - $(moveDiv).offset().top;
    $(document)
      .mousemove(function(e) {
        if (isMove) {
          var obj = $(moveDiv);
          obj.css({ left: e.pageX - div_x, top: e.pageY - div_y });
        }
      })
      .mouseup(function() {
        isMove = false;
        var point =
          $(moveDiv)
            .css("left")
            .replace("px", "") +
          "," +
          $(moveDiv)
            .css("top")
            .replace("px", "");
        $("#xylabel").html(point);
        console.log(point);
      });
  });
}
