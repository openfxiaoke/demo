
//验证工号
jQuery.validator.addMethod("userNoCheck", function(value, element) {
	return this.optional(element) || /^[a-zA-Z]{1,6}[0-9]{2,6}$/.test(value);
});

//验证IP
jQuery.validator.addMethod("ipCheck", function(value, element) {
	return this.optional(element) || /^((0[0-9]|1[0-9]\d{1,2})|(2[0-5][0-5])|(2[0-4][0-9])|(\d{1,2}))\.((0[0-9]|1[0-9]\d{1,2})|(2[0-5][0-5])|(2[0-4][0-9])|(\d{1,2}))\.((0[0-9]|1[0-9]\d{1,2})|(2[0-4][0-9])|(2[0-5][0-5])|(\d{1,2}))\.((0[0-9]|1[0-9]\d{1,2})|(2[0-4][0-9])|(2[0-5][0-5])|(\d{1,2}))$/.test(value);
});