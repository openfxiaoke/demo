/**
 * @author: fangchao
 */
(function($) {
	$(document).ready(function() {
		var export_btn = $('#nad_excel_export');
		if (export_btn) {
			export_btn.click(function() {
				var href = window.location.href;
				var param = 'export=xls';
				href = href.replace('?' + param + '&', '?');
				href = href.replace('?' + param, '');
//				console.log(href);
				var param_prefix = href.indexOf('?') == -1 ? "?" : '&';
				var export_param = param_prefix + param;
				href = href.replace(export_param, '');
//				console.log(href);
				window.location.replace(href + export_param);
			});
		}
	});
})(jQuery);
