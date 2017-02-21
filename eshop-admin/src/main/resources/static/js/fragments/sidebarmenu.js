$(function(){
	var menu = {
		menu_html:'<ul class="sidebar-menu">'+
        		  '<li class="header">MAIN NAVIGATION</li>'+
				  '<li class="active treeview">'+
				  '<a href="#"><i class="fa fa-dashboard"></i> <span>Dashboard</span>'+
            	  '<span class="pull-right-container">'+
                  '<i class="fa fa-angle-left pull-right"></i>'+
            	  '</span></a>'+
                  '<ul class="treeview-menu">'+
                  '<li class="active"><a href="index.html"><i class="fa fa-circle-o"></i> Dashboard v1</a></li>'+
                  '<li><a href="index2.html"><i class="fa fa-circle-o"></i> Dashboard v2</a></li>'+
                  '</ul></li>',
		init_menu:function(count){
			for (var i = 0; i < count; i++) {
				$('#menuList').append(menu_html);
			}
		},
		init:function(){

			// $.ajax({
			// 	url: constants.BASE_URL + "/menuInfo",
			// 	type: 'GET',
			// })
			// .done(function(result) {
			// 	menu.init_menu(result);
			// 	console.log("success");
			// })
			// .fail(function() {
			// 	console.log("error");
			// })
			// .always(function() {
			// 	console.log("complete");
			// });
			alert(1111);
			
		}
	};
	// menu.init();
	alert(123);
});