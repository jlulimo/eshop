$(function() {
    var categoryManager = {
    	initUI:function(){
    		$('#level2BtnGroup,#level3BtnGroup,#level4BtnGroup').hide();
    		$('#level2,#level3,#level4').selectpicker('hide');
    	},
        initEvent: function() {
            $('#search').keyup(function() {
                var current_query = $('#search').val();
                if (current_query !== "") {
                    $(".list-group li").hide();
                    $(".list-group li").each(function() {
                        var current_keyword = $(this).text();
                        if (current_keyword.indexOf(current_query) >= 0) {
                            $(this).show();
                        };
                    });
                } else {
                    $(".list-group li").show();
                };
            });
            $('#level1').change(function() {
                alert(123);
            });
        },
        init:function(){
        	categoryManager.initUI();
        	categoryManager.initEvent();
        }
    };

    categoryManager.init();
});

