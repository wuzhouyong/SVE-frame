$(function(){
	$(".flow-canvas div").each(function(index, el) {
		var xy = $(this).attr("xy");
		if(xy)
		{
			// console.log($(this).attr("id")+xy);
			var point = xy.split(',');
			if(point.length==2)
			{
				$(this).animate({top:point[1]+"px",left: point[0]+"px"});
			}
		}
		var ename = $(this).attr("ename");
		// console.log(ename);
		if(ename)
		{
			$(this).append('<span class="flow-name">'+ename+'</span>');
		}
		var pwidth = $(this).attr("pwidth");
		if(pwidth)
		{
			$(this).css({
				width: pwidth+'px'
			});
		}
		var pheight = $(this).attr("pheight");
		if(pheight)
		{
			$(this).css({
				height: pheight+'px'
			});
		}
		var format = $(this).attr("format");
		if(format)
		{
			$(this).html(format);
		}
	});

	//setMove();
});

//更新大小
function flowResize(sizePercent) {
    $(".flow-canvas").css({
        "transform": "scale(" + sizePercent + ", " + sizePercent + ")",
        "-ms-transform": "scale(" + sizePercent + ", " + sizePercent + ")",
        "-webkit-transform": "scale(" + sizePercent + ", " + sizePercent + ")"
    });
    //var top = $(".flow-canvas").height() * (sizePercent - 1) / 2;
    //$(".flow-canvas").css("top", top);
    //var left = $(".flow-canvas").width() * (sizePercent - 1) / 2;
    //$(".flow-canvas").css("left", left);
    $(".flow-canvas").css("left", ($(".flow-canvas").parent().width() - $(".flow-canvas").width()) / 2);
    $(".flow-canvas").css("top", ($(".flow-canvas").parent().height() - $(".flow-canvas").height()) / 2);
}
//更新数据
function flowRefresh(data)
{
	$.each(data, function(name, val) {
		 // console.log(name);
        var ele = $(".flow-canvas").find("#" + name);
        if (ele.length>0)
        {            
		 	var classtype = ele.prop('class');
		 	if (classtype.indexOf("label")>=0) {
                ele.html(ele.attr('format').replace("{}", val));
                //ele.attr("eval", val);
            }
            if (name.indexOf("SW") == 0) {
                $("[groups='" + ele.attr('groups') + "']").each(function (index, el) {
                    // console.log(el);
                    var ptype = $(el).parent().prop('class');
                    if (ptype.indexOf("flow-element") >= 0) {
                        if (val) {
                            $(el).addClass('flow-start');
                            $(el).removeClass('flow-stop');
                        }
                        else {
                            $(el).addClass('flow-stop');
                            $(el).removeClass('flow-start');
                        }
                    }
                    if (ptype.indexOf("flow-pipeline") >= 0) {
                        if (val) {
                            $(el).addClass('pipe-start');
                            $(el).removeClass('pipe-stop');
                        }
                        else {
                            $(el).addClass('pipe-stop');
                            $(el).removeClass('pipe-start');
                        }
                    }
                });
            }
        }
    });

    $("[tgroups]").each(function (index, el) {
        var ids = $(el).attr("tgroups").split(',');
        //console.log(ids);
        var isgo = false;
        $.each(ids, function (index, val) {
            //var eval = $("#" + val).attr("eval");
            //if (eval > 0) {
            //    isgo = true;
            //    return;
            //}
            if ($("#" + val).attr("class").indexOf("-start")>=0) {
                isgo = true;
                return;
            }
        });
        if ($(el).prop('class').indexOf("flow-pipe") >= 0) {
            if (isgo) {
                $(el).addClass('pipe-start');
                $(el).removeClass('pipe-stop');
            }
            else {
                $(el).addClass('pipe-stop');
                $(el).removeClass('pipe-start');
            }
        }
    })
}

function setMove()
{
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

function dragPanelMove(moveDiv){
    $(moveDiv).mousedown(function (e) {
            var isMove = true;
            var div_x = e.pageX - $(moveDiv).offset().left;
            var div_y = e.pageY - $(moveDiv).offset().top;
            $(document).mousemove(function (e) {
                if (isMove) {
                    var obj = $(moveDiv);
                    obj.css({"left":e.pageX - div_x, "top":e.pageY - div_y});
                }
            }).mouseup(
                function () {
                    isMove = false;
                    var point = $(moveDiv).css("left").replace("px", "") + "," + $(moveDiv).css("top").replace("px", "");
                    $("#xylabel").html(point);
                    console.log(point);
            });
    });

}
