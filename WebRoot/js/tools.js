jQuery.fn.extend({
	
	/**
	 * 快速填充表单
	 */
	fillForm:function(data){
		var key,value,tagName,type,arr;
		for(x in data){
		    key = x;
		    value = data[x];
		    this.find("[name='"+key+"'],[name='"+key+"[]']").each(function(){
		        tagName = $(this)[0].tagName;
		        type = $(this).attr('type');
		        if(tagName=='INPUT'){
		            if(type=='radio'){
		            	$(this).attr('checked',$(this).val()==value);
		            } else if(type=='checkbox'){
		                arr = value.split(',');
		                for(var i =0;i<arr.length;i++){
		                	if($(this).val()==arr[i]){
		                		$(this).attr('checked',true);
		                		break;
		                    } 		
		                }
		            } else {
		            	$(this).val(value);
		            }
		        } else if(tagName=='SELECT' || tagName=='TEXTAREA'){
		        	$(this).val(value);
		        }
		      
		    });
		}
	},
	
	/**
	 * 将表单中所有元素置空
	 */
	clearForm:function(){
		this.find(":input").val("");
	},
	
	/**
	 * 将表单转换为一个js对象
	 */
	serializeObject:function(){
		var o = new Object();
		var a = this.serializeArray();
		$.each(a, function() {
		    if (o[this.name] !== undefined) {
		        if (!o[this.name].push) {
		        	o[this.name] = [o[this.name]];
		        }
		        o[this.name].push(this.value || '');
		    } else {
		    	o[this.name] = this.value || '';
		    }
		});
		return o;
	}
});

/**
 * 构建context对象，该对象包含一些项目中常用的方法
 */
var context = function(){
	var context = new Object();
	
	/**
	 * 无侵入的通知方法，适用于无需用户确认的通知信息展示
	 * @param title 可省略，默认“操作提示”
	 * @param msg 提示信息内容
	 */
	context.show = function(){
		var title,msg;
		if(arguments.length > 1) {
			title = arguments[0];
			msg = arguments[1];
		} else {
			title = "操作提示";
			msg = arguments[0];
		}
		$.messager.show({
			title: title,
            msg: msg,
            showType: 'slide',
            timeout: 1500
        });
	};
	
	var $dialog;
	/**
	 * 弹出对话框窗口
	 * @param option 窗口配置
	 */
	context.dialog = function(option){
		if(!$dialog) {
			$dialog = $('<div id="dialog"></div>');
			$dialog.appendTo("body");
		}
		$dialog.dialog({
		    title: option.title,
		    width: option.width ? option.width : 400,
		    height:option.height ? option.height : 200,
		    closed: option.closed ? option.closed : false,
		    cache: option.cache ? option.cache : false,
		    href: option.href,
		    modal: option.modal ? option.modal : true,
		    onLoad: option.onLoad,
		    buttons:option.buttons
		});
	};
	
	/**
	 * 得到对话框窗口的jQuery对象
	 */
	context.get$dialog = function(){
		return $dialog;
	};
	
	/**
	 * 关闭对话框窗口
	 */
	context.closeDialog = function(){
		$dialog.dialog("close");
	};
	
    /**
     * 弹出确认框
     * @param msg 弹出框内容
     * @param fn 点击确认时，调用该函数
     */
    context.confirm = function(msg,fn){
    	if(fn == undefined)
			fn =  function(){};
    	$.messager.confirm("系统提示",msg, function(r){
    		if (r){
    			fn();
    		}
    	});
    };
    
    context.alert = function(msg,fn){
    	if(fn == undefined)
			fn =  function(){};
		$.messager.alert("系统提示",msg,"info",fn);
    };
    
    context.question = function(msg,fn){
    	if(fn == undefined)
			fn =  function(){};
    	$.messager.alert("系统提示",msg,"question",fn);
    };
    
    context.error = function(msg,fn){
    	if(fn == undefined)
			fn =  function(){};
		$.messager.alert("系统提示",msg,"error",fn);
    };
    
    context.warning = function(msg,fn){
    	if(fn == undefined)
			fn =  function(){};
		$.messager.alert("系统提示",msg,"warning",fn);
    };
	
    context.prompt = function(msg,fn){
    	if(fn == undefined)
			fn =  function(){};
		$.messager.prompt("系统提示",msg,function(r){
			if (r) {
				fn();
			}
		});
    }
	
	return context;
}();
