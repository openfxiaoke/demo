/**
 * @author: CY4705 jinguangguo
 * @date: 2013-07-24
 * @email: jinguangguo@cyou-inc.com
 */

(function ($) {

	/**
	 * 绑定数字输入(不可以输入0)
	 * 绑定该功能只需要让对应input元素具有input-number类即可
	 */
	$(document).delegate('.input-number', 'keydown keypress keyup', function () {
		$(this).inputNumber();
	});

	/**
	 * 绑定数字输入(可以输入0)
	 * 绑定该功能只需要让对应input元素具有input-number1类即可
	 */
	$(document).delegate('.input-number1', 'keydown keypress keyup', function () {
		$(this).inputNumber1();
	});
	
	/**
	 * 绑定数字输入(可以输入0)及小数
	 * 绑定该功能只需要让对应input元素具有input-number2类即可
	 */
	$(document).delegate('.input-number2', 'keydown keypress keyup', function () {
		$(this).inputNumber2();
	});

	/**
	 * 关闭提示框
	 * 耦合性比较强，需要让对应dom结构具有ID名称alertInfo
	 */
	$(document).delegate('#alertInfo .close', 'click', function () {
		$('#alertInfo').hide('slow');
		$('#alertInfo').find('txt').empty();
	});

})(jQuery);

//页面加载后执行的事件绑定
$(function () {
	
	$('[type=file]').each(function () {
		$(this).checkImgForJpgPng($(this).attr('data-maxsize'));
	});
	
	//点击图片上传
	$('.btn-upload')[0] &&
	$('.btn-upload').click(function(){
		that = $(this);
		$.ajaxUpload({
			//图片上传请求
			uploadRequestUrl: wwwPath + '/imageUpload/ajaxUpload.do',
			$img: $(this).parents('.controls').find('img'),	//图片展示
			$file: $(this).siblings('[type=file]'),				//上传的文件
			fileId: $(this).siblings('[type=file]').attr('id'),	//上传的文件id，确保页面的唯一性
			$imgHidden: $(this).siblings('[type=file]').siblings('input'),
			callback: function () {
				that.siblings('[type=file]').checkImgForJpgPng(that.siblings('[type=file]').attr('data-maxsize'));
			}
		});
		return false;
	});
	
	//全选
	$("#table")[0] &&
	$("#table").checkAll({
    	valueField: "txtTest",
    	attrName: "Id",
    	chkId: "J-checkAll",
    	splitExp: "",
    	valueIndex: 0
    });
});