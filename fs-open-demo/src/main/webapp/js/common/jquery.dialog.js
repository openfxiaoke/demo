/**
 * @author: CY4705 jinguangguo
 * @date: 2012-11
 * @email: jinguangguo@cyou-inc.com
 */

/**

	jquery.dialog.js对话框插件调用说明文档

	条件：
		1> 该组建库依赖jquery.js和bootstrap.js，需要首先引入jquery.js，再引入bootstrap.js
		2> 引入对话框插件库：jquery.dialog.js
		3> 调用 - 出现对话框
			$('#dialogDomId').dialog({
				onSure: function () {},		//点击 确定 后要做的事情
				onCancel: function () {},	//点击 取消 后要做的事情
				onShow: function () {},		//对话框 刚刚出来 要做的事情
				onShown: function () {},	//对话框 出来之后 要做的事情
				onHide: function () {},		//对话框 刚刚消失 要做的事情
				onHidden: function () {}	//对话框 消失之后 要做的事情
			});
		4> 调用 - 显示和隐藏 对话框
			第一种方式：
				$('#dialogDomId').data('dialog').show();	//前提：对话框初始化完毕
			第二种方式：
				var dialog =  $('#dialogDomId').dialog({...});
				dialog.show();	//显示
				dialog.hide();	//隐藏

 */
(function ($) {

	//对话框插件
	var Dialog = function (element, options) {

		this.srcElement = options.srcElement;
		this.$element = $(element);
		//首先生成一个Modal对象，并缓存于这个DOM对象中
		this.$element.modal(options);
		this.options = options;
		this.modal = this.$element.data('modal');

		options['height']
			&& this.modal.$element.find('.modal-body').css('min-height', options['height'] + 'px');

		//绑定事件 - 触发对话框生成
		options['triggerElement'] &&
			$(options['triggerElement']).on('click', $.proxy(this.modal.show, this.modal));

		//绑定事件 - 对话框动画
		options['onShow']
			&& this.$element.unbind('show')
			&& this.$element.on('show', $.proxy(options['onShow'], this.srcElement || this.$element));
		options['onShown']
			&& this.$element.unbind('shown')
			&& this.$element.on('shown', options['onShown']);
		options['onHide']
			&& this.$element.unbind('hide')
			&& this.$element.on('hide', options['onHide']);
		options['onHidden']
			&& this.$element.unbind('hidden')
			&& this.$element.on('hidden', options['onHidden']);

		//绑定事件 - 确定和取消按钮
		options['onSure'] && //确定
		this
			.$element
			.find(options['sureClass'])
			.unbind('click')
			.on('click', $.proxy(options['onSure'], this.$element[0]));

		options['onCancel'] && 	//取消
		this
			.$element
			.find(options['cancelClass'])
			.unbind('click')
			.on('click', $.proxy(options['onCancel'], this.$element[0]));

	};

	Dialog.prototype = {
		constructor: Dialog,
		open: function (opt) {

			if (opt && opt['clearform'] && this.$element.find('form')[0]) {
				this.clearform(this.$element.find('form')[0]);
				this.modal['show']();
				return;
			} else if (opt && (opt['clearform'] === false) && this.$element.find('form')[0]) {
				this.modal['show']();
				return;
			}

			this.options.clearform && this.clearform(this.$element.find('form')[0]);
			this.modal['show']();
		},
		hide: function () {
			this.modal['hide']();
		},
		clearform: function (element) {
			$(element).find('input[type=text], select, checkbox, radio, textarea').val('');
		}
	};

	$.fn.dialog = function (option) {

		var $this = $(this),
			data = $this.data('dialog'),
			//接收所有参数
			options = $.extend({}, $.fn.dialog.defaults, $this.data(), typeof option == 'object' && option);

		options['isNew'] && $this.removeData('dialog');

		if (!$this.data('dialog')) {
			$this.data('dialog', (data = new Dialog(this, options)));
		}
		if (typeof option === 'string') {
			data[option]();
		} else if (options.open) {
			data.open();
		}
		return data;

	};

	$.fn.dialog.defaults = {
		width: 560,
		maxHeight: 600,
		open: false,
		onShow: null,
		onShown: null,
		onHide: null,
		onHidden: null,
		onSure: function () {
			$(this).data('dialog').hide();
		},
		onCancel: null,
		sureClass: '.J-sure',
		cancelClass: '.J-cancel',
		srcElement: null,
		isNew: false,
		clearform: false
	};

})(jQuery);