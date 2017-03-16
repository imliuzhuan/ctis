$.extend($.fn.validatebox.defaults.rules, {
	telephone : {
		validator : function(value, param) {
			// 验证手机
			var rex = /^1[3-8]+\d{9}$/;
			// 验证电话
			var rex2 = /^((0\d{2,3})-)(\d{7,8})(-(\d{3,}))?$/;
			if (rex.test(value) || rex2.test(value)) {
				return true;
			} else {
				return false;
			}

		},
		message : "请输入正确电话或手机格式！"
	},
	safestr : {
		validator : function(value, param) {
			return /^[a-zA-z][a-zA-Z0-9_]{0,}$/.test(value);
		},
		message : "只允许输入以字母开头的字母、数字、下划线组成的字符串！"
	},
	equalTo : {
        validator: function(value, param) {
            return value == $(param[0]).val();
        },
        message: "密码输入不一至！"
    },
    number: {
        validator: function (value, param) {
            return /^\d+$/.test(value);
        },
        message: '请输入数字！'
    },
    userName: {
        validator: function (value, param) {
            return /^[\u0391-\uFFE5\w]+$/.test(value);
        },
        message: '用户名只允许汉字、英文字母、数字及下划线。'
    }
});