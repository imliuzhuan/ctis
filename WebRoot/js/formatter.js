function yesorno(value, rowData, rowIndex) {
	var rtn = "";
	switch (value) {
	case "0":
		rtn = "否";
		break;
	case "1":
		rtn = "是";
		break;
	default:
		rtn = value;
	}
	return rtn;
}

function gender(value, rowData, rowIndex) {
	var rtn = "";
	switch (value) {
	case "0":
		rtn = "女";
		break;
	case "1":
		rtn = "男";
		break;
	default:
		rtn = value;
	}
	return rtn;
}

function haveroles(value, rowData, rowIndex) {
	var rtn = "";
	var valArr;
	if (value) {
		valArr = value.split(",")
	}
	if (valArr && valArr.length > 0) {
		$.ajax({
			url : "role_getRoles",
			type : "get",
			async : false,
			success : function(data) {
				$.each(valArr, function(i, val) {
					$.each(data, function(j, role) {
						if (val == role.id) {
							rtn += role.name + ",";
						}
					});
				});
			}
		});
	}
	if (rtn && rtn.length > 0) {
		rtn = rtn.substring(0, rtn.length - 1);
	}
	return rtn;
}

function firstAndEnd(value, rowData, rowIndex) {
	return rowData.firstStation + "-" + rowData.lastStation;
}

function workTime(value, rowData, rowIndex) {
	var upTime = rowData.firstStation + " " + rowData.firstStartTime + "-" + rowData.firstEndTime;
	var downTime = rowData.lastStation + " " + rowData.lastStartTime + "-" + rowData.lastEndTime;
	if (upTime == downTime) {
		return "(" + upTime + ")";
	}
	return "(" + upTime + "|" + downTime + ")";
}

function topicId(value, rowData, rowIndex){
	if (value) {
		$.ajax({
			url:"../topic/topic_getTopic",
			type:"get",
			data:{"id":value},
			async:false,
			success:function(data){
				if (data&&data.name) {
					value = data.name;
				}
			}
		});
	}
	return value;
}

function commentsState(value, rowData, rowIndex){
	var rtn = "";
	switch (value) {
	case "0":
		rtn = "待审核";
		break;
	case "1":
		rtn = "审核未通过";
		break;
	case "2":
		rtn = "审核通过";
		break;
	default:
		rtn = value;
	}
	return rtn;
}

function topicState(value, rowData, rowIndex){
	var rtn = "";
	switch (value) {
	case "0":
		rtn = "未发布";
		break;
	case "1":
		rtn = "已发布";
		break;
	default:
		rtn = value;
	}
	return rtn;
}

function creator(value, rowData, rowIndex){
	if (value) {
		$.ajax({
			url:"../user/user_getUserById",
			type:"get",
			data:{id:value},
			async:false,
			success:function(data){
				if (data) {
					value = data.userName+"["+data.nickName+"]";
				}
			}
		});
	}
	return value;
}