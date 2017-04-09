$(function() {
    var page = {
        initTree: function(treeId) {
            $('#' + treeId + 'tree').jstree({
                'core': {
                    "animation": 0,
                    "check_callback": true,
                    'force_text': true,
                    "themes": { "stripes": true },
                    "cache": false,
                    'data': function(obj, callback) {
                        $.ajax({
                                url: constants.BASE_URL + '/permission/browse?',
                                type: 'GET',
                                dataType: 'json',
                                data: { nodeId: obj.id },
                                cache: false,
                            })
                            .done(function(result) {
                                console.log("success");
                                if (result.code == 0) {
                                    if (result.data.id == 'root') {
                                        result.data.id = treeId;
                                    }
                                    callback.call(this, result.data);
                                }

                            })
                            .fail(function() {
                                console.log("error");
                            })
                            .always(function() {
                                console.log("complete");
                            });

                    }
                },
                "types": {
                    "0": { "icon": "glyphicon glyphicon-cloud" },
                    "root": { "icon": "glyphicon glyphicon-cloud" },
                    "category": { "icon": "glyphicon glyphicon-file" }
                },
                "plugins": ["checkbox", "wholerow"]

            });
        },
        initEvent: function() {
            $('.nav.nav-tabs li').click(function(event) {
                var href = event.target.href;
                var temp = href.split('#');
                var menuId = temp[temp.length - 1];
                if (menuId != "") {
                    page.initTree(menuId);
                }

            });
        },

        init: function() {
            page.initEvent();
            var lis = $('.nav.nav-tabs li');
            for (var i = 0; i < lis.length; i++) {
                var href = lis[i].children[0].href;
                var temp = href.split('#');
                var menuId = temp[temp.length - 1];
                if (menuId != "") {
                    page.initTree(menuId);
                }
                // alert(menuId);
            }
        }
    };

    page.init();
})
