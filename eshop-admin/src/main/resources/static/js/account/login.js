$(function() {
    var pager = {
        initEvent: function() {
            $('input').iCheck({
                checkboxClass: 'icheckbox_square-blue',
                radioClass: 'iradio_square-blue',
                increaseArea: '20%' // optional
            });

            $("#loginBtn").on('click', function(event) {
            	var username = $('#username').val();
            	var password = $('#password').val();
				$.ajax({
				        	url: constants.BASE_URL + "/login",
				        	type: 'POST',
				        	dataType: 'json',
				        	data: {
				        		username: username,
				        		password:password,
				        		rememberMe:false
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
