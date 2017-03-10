$(function() {
    var menu = {
        
        init_menu: function(result) {
            var menuHtml = '<ul class="sidebar-menu"><li class="header">菜单列表</li>';
            for (var i = 0; i < count; i++) {
                menuHtml += '<li class="active treeview">';
          		menuHtml += '<a href="#">';
            	menuHtml += '<i class="fa fa-dashboard"></i> <span>'+Dashboard +'</span>';
            	menuHtml += '<span class="pull-right-container">';
              	menuHtml += '<i class="fa fa-angle-left pull-right"></i>';
            	menuHtml += '</span>';
          		menuHtml += '</a>';
          		menuHtml += '<ul class="treeview-menu">';
            	menuHtml += '<li class="active"><a href="index.html"><i class="fa fa-circle-o"></i> '+Dashboard v1+'</a></li>';
            	menuHtml += '<li><a href="index2.html"><i class="fa fa-circle-o"></i>'+ Dashboard v2+'</a></li>';
          		menuHtml += '</ul>';
        		menuHtml += '</li>';
        		$('#menuList').append(menuHtml);
            }
        },
        init: function() {

            $.ajax({
                    url: constants.BASE_URL + "/menuInfo",
                    type: 'GET',
                })
                .done(function(result) {
                    menu.init_menu(result.data);
                    console.log("success");
                })
                .fail(function() {
                    console.log("error");
                })
                .always(function() {
                    console.log("complete");
                });

        }
    };
    menu.init();
});
