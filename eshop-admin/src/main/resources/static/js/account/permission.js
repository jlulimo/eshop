$(function(){
	var page = {
		initTree: function() {
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
                                callback.call(this, result.data);
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
                "plugins": ["checkbox","wholerow"]

            });
        }
	};
})