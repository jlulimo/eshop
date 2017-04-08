$(function() {
    var page = {
        initEvent: function() {
            $('.nav.nav-tabs li').click(function(event) {
                /* Act on the event */
                alert('click: ' + event);
                var menuId = event.target.id;
                $('#permissionTree').jstree({
                    'core': {
                        "animation": 0,
                        "check_callback": true,
                        'force_text': true,
                        "themes": { "stripes": true },
                        'data': function(obj, callback) {
                            $.ajax({
                                    url: constants.BASE_URL + '/permission/browse?',
                                    type: 'GET',
                                    dataType: 'json',
                                    data: { nodeId: obj.id },
                                })
                                .done(function(result) {
                                    console.log("success");
                                    if (result.code == 0) {
                                        if (result.data.id == 'root') {
                                            result.data.id = menuId;
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
            });
        }
    };

    page.initEvent();
    $('.nav.nav-tabs li')[0].click();
})
