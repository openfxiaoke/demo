/**
 * @author: CY4705 jinguangguo
 * @date: 2012-11
 * @email: jinguangguo@cyou-inc.com
 */

//限制只输入正整数
jQuery.fn.inputNumber = function () {

	var reg = /^\d+$/,
		value = $(this).val();

	if (reg.test(value) === false) {
		$(this).val(value.replace(/\D/, ''));
	}

	if (value === '0') {
		$(this).val(value.replace(/0/, ''));
	}

};

//可以输入0或正整数
jQuery.fn.inputNumber1 = function () {

	var reg = /^\d+$/,
		value = $(this).val();
	if (reg.test(value) === false) {
		$(this).val(value.replace(/\D/, ''));
	}

};

jQuery.fn.inputNumber2 = function () {

	var reg = /^\d+\.?\d*$/,
		value = $(this).val();
	if (reg.test(value) === false) {
		$(this).val(value.replace(/\D/, ''));
	}

};

jQuery.fn.len = function () {
	return $(this).val().replace(/[^\x00-\xff]/g, "**" ).length;
};

String.prototype.startWith = function (str) {
	if (this.indexOf(str) === 0) {
		return true;
	}
	return false;
};

//判断是否是整数
jQuery.fn.isDigits = function () {

	var reg = /^\d+$/,
		value = $.trim($(this).val());

	if (reg.test(value) === false) {
		return false;
	}

	return true;
};

//上传文件
jQuery.ajaxUpload = function (opt) {

//	opt = {
//		uploadRequestUrl:,
//		z:
//		$img:,	//图片展示
//		$file:,	//上传的文件
//		fileId:,	//上传的文件id，确保页面的唯一性
//		$imgHidden:	//图片的隐藏路径地址
//	};

	opt = opt || {};

	if (opt.$file && opt.$file.val() === '') {
		jQuery.showTips("请选择文件。");
		return;
	}

	if (opt.fileId === null || opt.fileId === 'undefined') {
		jQuery.showTips("请为图片赋予id属性。");
		return;
	}

	$.ajaxFileUpload({
		url: opt.uploadRequestUrl,
		type: 'post',
		secureuri: false,
		fileElementId: opt.fileId,
		dataType: 'text',
		success: function (data, status){
			
			if (data.indexOf("error") === 0) {
				jQuery.showTips(data);
				return;
			} 
			
			opt.$img.attr("src", data);
			opt.$imgHidden.attr('value', data);

			if (typeof opt.callback === 'function') {
				opt.callback();
			}

		},
		error: function (data, status, e){
			jQuery.showTips(status);
		}
	});

};

//input变成json串
jQuery.inputsToJson = function ($rows) {
	var array = [];
	$rows.each(function () {
		var obj = {};
		$(this).find('input').each(function () {
			var value = $.trim($(this).val());
			if (/^\d$/.test(value) === true) {
				value = parseInt(value, 10);
			}
			obj[$(this).attr('name')] = value;
		});
		array.push(obj);
	});
	return JSON.stringify(array);
};

//input变成json串（附有id属性）
jQuery.inputsToJson2 = function ($rows) {
	var array = [];
	var count = 1;
	$rows.each(function () {
		var obj = {};
		obj['id'] = count;
		$(this).find('input').each(function () {
			var value = $.trim($(this).val());
			if (/^\d$/.test(value) === true) {
				value = parseInt(value, 10);
			}
			obj[$(this).attr('name')] = value;
		});
		array.push(obj);
		count++;
	});
	count = 1;
	return JSON.stringify(array);
};

//生成图表
jQuery.fn.ajaxDataForCharts = function (opt) {
	var that = this;

	var dataObj = {};
	dataObj['days'] = opt.days || 10;
	if (opt.goodsId !== undefined) {
		dataObj['goodsId'] = opt.goodsId;
	}

	$.ajax({
		url: opt.ajaxurl,
		type: 'get',
		data: dataObj,
		context: this,
		async: false,
		dataType: 'json',
		success: function (jsonObj) {
			//X轴
			var xAxisArray = jsonObj.time;
			//数据
			var dataArray = jsonObj.data;

			opt.xAxisArray = xAxisArray;
			opt.dataArray = dataArray;

			constructCharts(that, opt);
		}
	});

	//重新生成图表
	function constructCharts(element, opt) {

		$(element).empty().highcharts({
		    chart: {
		        type: 'line',			//图表类型
		        marginBottom: 75,		//图标底间距
		        //backgroundColor: 'orange',
		        //plotBackgroundColor: '#ccc'
		    },
		    title: {	//标题选项
		        text: opt.title || '统计',	//图标正标题
		        align: 'left',			//正标题水平对齐方式
		        verticalAlign: 'top',	//正标题垂直对齐方式
		        x: 50,
		        y: -20
		    },
		    xAxis: {	//X轴选项
		        categories: opt.xAxisArray,	//X轴标示数组
		        labels: {
		        	rotation: -60,
		        	style: {
		        		fontFamily: 'Microsoft Yahei',
		        		fontWeight: 'bold'
		        	}
		        }
		    },
		    yAxis: {	//Y轴选项
		        title: {
		            text: (opt.yname || '数量') + '(' + (opt.ydanwei || '个') + ')'		//Y轴单位
		        },
		        plotLines: [{
		            value: 0,
		            width: 1,
		            color: '#808080'
		        }]
		    },
		    plotOptions: {
                line: {
                    dataLabels: {
                        enabled: true
                    },
                    enableMouseTracking: true
                }
            },
		    tooltip: {	//数据点提示框
		        valueSuffix: opt.ydanwei || '个'
		    },
		    legend: {	//图例
		        layout: 'horizontal',		//'horizontal','vertical'
		        align: 'center',			//'left'、'center'
		        verticalAlign: 'top',
		    },
		    series: opt.dataArray			//资金数据数组
		});

		$('.statis').each(function () {
			$(this).find('.highcharts-axis-labels').first().find('text').attr('text-anchor', 'end');
		});

	}

};

//清理form
jQuery.fn.clearform = function () {
	return this.each(function() {
		$(this).find('input[type=text], select, checkbox, radio, textarea').val('');
	});
};

/**
 * 判断图片格式及其大小
 * @param this: type="file" 的输入框
 * @param maxsize: KB单位
 */
jQuery.fn.extend({

  	checkImg: function(maxsize, callback) {

  		//确保数字
  		maxsize && (maxsize = /\d/.test(maxsize) === false ? parseInt(maxsize, 10) : maxsize);

  		//判断图片格式 - png
  		var reg = /\.png$/;

  		$(this).change(function () {

  			var filepath = $(this).val();

  			if (reg.test(filepath.toLowerCase()) === false) {
  				jQuery.showTips('图片格式目前仅支持png');
  				$(this).val('');
  				if (typeof callback === 'function') {
  	  				callback();
  	  			}
  				return;
  			}

  			//判断图片大小
  			if (this.files[0].size > maxsize * 1024) {
  				jQuery.showTips('图片大小不能超过' + maxsize + 'KB');
  				$(this).val('');
  				if (typeof callback === 'function') {
  	  				callback();
  	  			}
  				return;
  			}

  		});

  	},

  	checkImgForJpgPng: function (maxsize, callback) {

  		//确保数字
  		maxsize && (maxsize = /\d/.test(maxsize) === false ? parseInt(maxsize, 10) : maxsize);

  		//判断图片格式 - png
  		var reg = /\.png|\.jpg|\.jpeg$/;

  		$(this).change(function () {

  			var filepath = $(this).val();

  			if (reg.test(filepath.toLowerCase()) === false) {
  				jQuery.showTips('图片格式目前仅支持png、jpg');
  				$(this).val('');
  				if (typeof callback === 'function') {
  	  				callback();
  	  			}
  				return;
  			}

  			//判断图片大小
  			if (this.files[0].size > maxsize * 1024) {
  				jQuery.showTips('图片大小不能超过' + maxsize + 'KB');
  				$(this).val('');
  				if (typeof callback === 'function') {
  	  				callback();
  	  			}
  				return;
  			}

  		});

  	}
});

/**
 * 将选中的复选框的值转换成数组列表
 * @param name:复选框的name属性值
 * @return 已选中复选框的值列表
 */
$.extend({
	checkedToArray: function ($chks, attrName) {
		var len = $chks.size(),
			chksArray = [];
		for (var i = 0; i < len; i++) {
			if ($chks[i].checked === true) {
				chksArray.push($chks.eq(i).attr(attrName));
			}
		}
		return chksArray;
	},
	format: function (msg) {
		return msg.split(':')[1];
	}
});


/* 表格全选插件 */
/* 使用方法 */
/*
    $("#table").checkAll({
    	valueField: "txtTest",
    	attrName: "Id",
    	chkId: "chkall",
    	splitExp: "|",
    	valueIndex: 0
    });
*/
$.fn.extend({
    checkAll: function (options) {
        var defaults = {
            valueField: null,   //默认选中后的赋值字段
            chkId: "c_all",     //全选框的Id
            attrName: "id",     //取值属性
            splitExp: "_",      //字段分隔符号
            valueIndex: 1       //分割后取值的索引下标
        };
        var options = $.extend(defaults, options);
        var obj = $(this); //默认为table
        function _getSelectedValue() {
            var values = "";
            obj.find(":checked").each(function () {
                //排除下拉菜单中的选中项和全选框
                if (!$(this).is("option") && $(this).attr("id") != options.chkId) {
                    values += $(this).attr(options.attrName).split(options.splitExp)[options.valueIndex] + ",";
                }
            });
            if (values.length > 0)
                return values.substring(0, values.length - 1);
            return values;
        }
        return this.each(function () {
            if (obj.is("table")) {
                var subExp = "#" + obj.attr("id") + " :checkbox";
                var $subExp = $(subExp + ":eq(0)");
                var chks = $(subExp + ":gt(0)");
                var checkedCount = 0;
                chks.each(function () {
                    $(this).click(function () {
                        if ($(this).is(":checked")) {
                            checkedCount += 1;
                        } else {
                            checkedCount -= 1;
                        }
                        if (checkedCount < chks.length) {
							$subExp.prop("checked", false);
                        } else {
                            $subExp.prop("checked", true);
                        }
                        // $("#" + options.valueField).val(_getSelectedValue());
                    });
                });
                $("#" + options.chkId).click(function () {
                    if ($(this).is(":checked")) {

                    	chks.prop("checked", true);
                    	// chks.each(function () {
                    		// $(this).prop("checked", true);
                    	// });
                        checkedCount = chks.length;
                    } else {
                        $(subExp).prop("checked", false);
                        checkedCount = 0;
                    }
                    // $("#" + options.valueField).val(_getSelectedValue());
                });
            }
        });
    }
});

//字符串截取，截取处用...取代
(function ($) {

	$.util = $.util || {};

	var widthCheck = function ($name, maxLength){

		$name.each(function () {

			var name = $(this).text();

			$(this).prop('title', name);

			if(!maxLength){
				maxLength = 20;
			}
			if(name==null||name.length<1){
				return "";
			}
			var w = 0;//字符串长度，一个汉字长度为2
			var s = 0;//汉字个数
			var p = false;//判断字符串当前循环的前一个字符是否为汉字
			var b = false;//判断字符串当前循环的字符是否为汉字
			var nameSub;
			for (var i=0; i<name.length; i++) {
			   if(i>1 && b==false){
			   		p = false;
			   }
			   if(i>1 && b==true){
			   		p = true;
			   }
			   var c = name.charCodeAt(i);
			   //单字节加1
			   if ((c >= 0x0001 && c <= 0x007e) || (0xff60<=c && c<=0xff9f)) {
			    	w++;
			    	b = false;
			   }else {
			    	w+=2;
			    	s++;
			    	b = true;
			   }
			   if(w>maxLength && i<=name.length-1){
			   		if(b==true && p==true){
			   			nameSub = name.substring(0,i)+"...";
			   		}
			   		if(b==false && p==false){
			   			nameSub = name.substring(0,i-1)+"...";
			   		}
			   		if(b==true && p==false){
			   			nameSub = name.substring(0,i)+"...";
			   		}
			   		if(p==true){
			   			nameSub = name.substring(0,i)+"...";
			   		}
			   		break;
			   }
			}
			if(w<=maxLength){
				return name;
			}
			$(this).text(nameSub);

		});

	};

	$.util.substring = widthCheck;

})(jQuery);
