
$(function(){

/*var _buses = {
		"num":["1","2","3"],
		"chinese":["A","B","C","D"],
		"list":[
			{"anchor":"1","buses":[{"id":"1","busCode":"101路"},{"id":"1","busCode":"101路"},{"id":"1","busCode":"101路"},{"id":"1","busCode":"101路"}]},
			{"anchor":"2","buses":[{"id":"1","busCode":"201路"},{"id":"1","busCode":"201路"},{"id":"1","busCode":"201路"},{"id":"1","busCode":"201路"},{"id":"1","busCode":"201路"}]},
			{"anchor":"3","buses":[{"id":"1","busCode":"301路"},{"id":"1","busCode":"301路"},{"id":"1","busCode":"301路"},{"id":"1","busCode":"301路"},{"id":"1","busCode":"301路"},{"id":"1","busCode":"301路"}]},
			{"anchor":"A","buses":[{"id":"1","busCode":"大治1路"},{"id":"1","busCode":"大治1路"},{"id":"1","busCode":"大治1路"},{"id":"1","busCode":"大治1路"},{"id":"1","busCode":"大治1路"},{"id":"1","busCode":"大治1路"},{"id":"1","busCode":"大治1路"}]},
			{"anchor":"B","buses":[{"id":"1","busCode":"巴士2路"},{"id":"1","busCode":"巴士2路"},{"id":"1","busCode":"巴士2路"},{"id":"1","busCode":"巴士2路"},{"id":"1","busCode":"巴士2路"}]},
			{"anchor":"C","buses":[{"id":"1","busCode":"草木101路"},{"id":"1","busCode":"草木101路"},{"id":"1","busCode":"草木101路"},{"id":"1","busCode":"草木101路"}]},
			{"anchor":"D","buses":[{"id":"1","busCode":"东方红8路"},{"id":"1","busCode":"东方红8路"},{"id":"1","busCode":"东方红8路"},{"id":"1","busCode":"东方红8路"},{"id":"1","busCode":"东方红8路"}]}
		]
	}*/
var _buses = {"num":[],"chinese":[],"list":[{"anchor":"","buses":[]}]};
$.ajax({
	url:"bus_getBusListArea",
	type:"get",
	async:false,
	success:function(data){
		if (data) {
			_buses = $.parseJSON(data);
		} else {
			context.warning("获取公交车数据失败！");
		}
	},
	error:function(data){
		context.warning("系统异常，请联系管理员！");
	}
});
var _html = '<div class="queryform">\
				<div class="busquery">\
			 		<h3>黄石公交基本信息查询</h3>\
					<div class="fline">\
				 		<label class="lbl1">以数字开头的公交线路：</label>\
			    		<ul class="lbllist" id="nl"></ul>\
			    		<div class="clear"></div>\
					</div>\
					<div class="fline">\
				 		<label class="lbl1">以汉字开头的公交线路：</label>\
			    		<ul class="lbllist" id="cl"></ul>\
			    		<div class="clear"></div>\
					</div>\
		  		</div>\
			</div>\
			<div class="querylist">\
				<div class="buslist" id="bl"></div>\
			</div>';
var _nl_html = '',_cl_html = '',_bl_html = '';
var $html = $(_html);
$.each(_buses.num,function(i,value){
	_nl_html += '<li><a href="#'+value+'">'+value+'</a></li>';
});
$.each(_buses.chinese,function(i,value){
	_cl_html += '<li><a href="#'+value+'">'+value+'</a></li>';
});
if (_nl_html) {
	$html.find("#nl").append($(_nl_html));
}
if (_cl_html) {
	$html.find("#cl").append($(_cl_html));
}
$.each(_buses.list,function(i,value){
	_bl_html += '<dl><dt><a id="'+value["anchor"]+'">'+value["anchor"]+'</a></dt><dd>';
	$.each(value["buses"],function(j,val){
		_bl_html += '<a href="javascript:getBusInfo(\''+val["id"]+'\')">'+val["busCode"]+'</a>';
	});
	_bl_html += '</dd></dl>';
});
_bl_html += '<div class="clear"></div>';
$html.find("#bl").append($(_bl_html));
$("#busListArea").append($html);
	var interval = setInterval(function(){
		if($("#busListArea").scrollTop() > 0){
			$("#busListArea").scrollTop(0);	
			clearInterval(interval);
		}
	},10);
});

function getBusInfo(id){
	$.ajax({
		url:"bus_getBusInfo",
		type:"get",
		data:{"id":id},
		success:function(data){
			if (data) {
				$("#busInfoForm").fillForm(data);
				$("#firstStartTime").timespinner("setValue",data.firstStartTime);
				$("#firstEndTime").timespinner("setValue",data.firstEndTime);
				$("#lastStartTime").timespinner("setValue",data.lastStartTime);
				$("#lastEndTime").timespinner("setValue",data.lastEndTime);
			} else {
				context.warning("获取公交车信息失败！");
			}
		},
		error:function(data){
			context.warning("系统异常，请联系管理员！");
		}
	});
}