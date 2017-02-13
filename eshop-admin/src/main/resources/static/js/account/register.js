$(function() {
    var pager = {
        initEvent: function() {
            $('input').iCheck({
                checkboxClass: 'icheckbox_square-blue',
                radioClass: 'iradio_square-blue',
                increaseArea: '20%' // optional
            });

            $('#registerBtn').on('click',  function(event) {
            	var username = $('#username').val();
            	var email = $('#email').val();
            	var password = $('#password').val();
            	var confirmPwd = $('#confirmPwd').val();

            	$.ajax({
            		url: constants.BASE_URL + "/register",
            		type: 'POST',
            		dataType: 'json',
            		data: {username: username,
            			email:email,
            			password:password,
            			confirmPwd:confirmPwd
            		},
            	})
            	.done(function() {
            		console.log("success");
            	})
            	.fail(function() {
            		console.log("error");
            	})
            	.always(function() {
            		console.log("complete");
            	});
            	
            });
        }
    }

    pager.initEvent();
});
