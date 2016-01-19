/**
 * 提示框
 *
 *  $.showTips('提示内容', function () {		//点击确定之后的回调函数
 *
 * 		//do something
 *
 * 		//隐藏对话框
 *		$(this).data('dialog').hide();
 *	});
 */
(function ($) {

	//私有属性及方法 - 静态
	var tipDom = '<div id="TIPS" class="modal hide fade tips" aria-hidden="true">'
				+  	'<div class="modal-header">'
				+   	'<!--<button type="button" class="close" data-dismiss="modal" aria-hidden="true">X</button>-->'
				+    	'<h3>提示</h3>'
				+  	'</div>'
				+  	'<div class="modal-body">'
				+		'<strong></strong>'
				+  	'</div>'
				+  	'<div class="modal-footer">'
				+  		'<button type="button" class="btn btn-primary J-sure">确定</button>'
				+  	'</div>'
				+'</div>';

	var dialog,
		//私有方法
		appendTipTxt = function ($tipDom, txt) {
			$tipDom.find('.modal-body strong').html(txt);
		},
		addCancelButton = function ($tipDom) {
			var dom = '<button class="btn J-cancel" data-dismiss="modal" aria-hidden="true">取消</button>';
			$tipDom.find('.modal-footer').append(dom);
			return $tipDom;
		},
		removeButtons = function ($tipDom) {
			$tipDom.find('button').remove();
			return $tipDom;
		},
		//公有方法 - 确定按钮
		showTips = function (txt, sureCallback, cancelCallback) {
			var $tipDom = $(tipDom);
			appendTipTxt($tipDom, txt);

			// 之前的dialog进行摧毁
			// dialog && dialog.hide();

			//重新组建一个dialog
			dialog = $tipDom.dialog({
				onSure: typeof sureCallback === 'function' ? sureCallback : this.onSure
			});
			dialog.open();
		},
		//共有方法 - 确定和取消按钮
		showTipsWithCancel = function (txt, sureCallback, cancelCallback) {
			var $tipDom = $(tipDom);
			appendTipTxt($tipDom, txt);
			$tipDom = addCancelButton($tipDom);

			dialog = $tipDom.dialog({
				onSure: typeof sureCallback === 'function' ? sureCallback : null,
				onCancel: typeof cancelCallback === 'function' ? cancelCallback : null
			});
			dialog.open();
		},
		//共有方法 - 没有按钮
		showTipsWithNoButtons = function (txt, sureCallback, cancelCallback) {
			var $tipDom = $(tipDom);
			appendTipTxt($tipDom, txt);
			$tipDom = removeButtons($tipDom);

			dialog = $tipDom.dialog({
				onSure: typeof sureCallback === 'function' ? sureCallback : null,
				onCancel: typeof cancelCallback === 'function' ? cancelCallback : null
			});
			dialog.open();
		};


	$.extend({
		showTips: showTips,
		alert: showTips,
		showTipsWithCancel: showTipsWithCancel,
		showTipsWithNoButtons: showTipsWithNoButtons
	});

})(jQuery);