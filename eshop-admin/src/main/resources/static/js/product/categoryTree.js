$(function() {
    var categoryTree = {
        init: function() {
            categoryTree.initTree();
            categoryTree.initEvent();
            $('#treeLoading').hide();
            $('#alertWin').hide();
        },
        initEvent: function() {
            $('#createNode').on('click', function(event) {
                var ref = $('#treeview').jstree(true),
                    sel = ref.get_selected();
                if (!sel.length) {
                    return false;
                }
                sel = sel[0];
                sel = ref.create_node(sel, { "type": "category" });
                if (sel) {
                    ref.edit(sel, null, function(node, status) {
                        $('#treeLoading').show();
                        $.ajax({
                                contentType: "application/json; charset=utf-8",
                                url: constants.BASE_URL + '/product/add',
                                type: 'POST',
                                dataType: 'json',
                                data: JSON.stringify({
                                    text: node.text,
                                    parent: node.parent,
                                }),
                            })
                            .done(function(result) {
                                if (result.code != 0) {
                                    $('#alert-body').text(result.msg);
                                    $('#alertWin').show();
                                }
                                $('#treeview').jstree(true).refresh('#' + result.data);
                                $('#treeLoading').hide();
                            })
                            .fail(function() {
                                console.log("error");
                            })
                            .always(function() {
                                console.log("complete");
                                $('#treeLoading').hide();
                            });

                    });
                }

            });

            $('#editNode').on('click', function(event) {
                var ref = $('#treeview').jstree(true),
                    sel = ref.get_selected();
                if (!sel.length) {
                    return false;
                }
                sel = sel[0];
                ref.edit(sel, null, function(node, status) {
                    $('#treeLoading').show();
                    $.ajax({
                            contentType: "application/json; charset=utf-8",
                            url: constants.BASE_URL + '/product/edit',
                            type: 'POST',
                            dataType: 'json',
                            data: JSON.stringify({
                                id: node.id,
                                text: node.text,
                                parent: node.parent,
                            }),
                        })
                        .done(function(result) {
                            if (result.code != 0) {
                                $('#alert-body').text(result.msg);
                                $('#alertWin').show();
                            }
                            $('#treeview').jstree(true).refresh('#' + result.data);
                            $('#treeLoading').hide();
                        })
                        .fail(function() {
                            console.log("error");
                        })
                        .always(function() {
                            console.log("complete");
                        });

                });
            });

            $('#deleteNode').on('click', function(event) {
                var ref = $('#treeview').jstree(true),
                    sel = ref.get_selected();
                if (!sel.length) {
                    return false;
                }
                sel = sel[0];
                $('#treeLoading').show();
                $.ajax({
                        contentType: "application/json; charset=utf-8",
                        url: constants.BASE_URL + '/product/delete',
                        type: 'POST',
                        dataType: 'json',
                        data: JSON.stringify({
                            id: sel,
                        }),
                    })
                    .done(function(result) {
                        if (result.code == 0) {
                            console.log("success");
                            ref.delete_node(sel);
                        }
                        $('#treeLoading').hide();
                    })
                    .fail(function() {
                        console.log("error");
                    })
                    .always(function() {
                        console.log("complete");
                    });

            });
        },
        initTree: function() {
            $('#treeview').jstree({
                'core': {
                    "animation": 0,
                    "check_callback": true,
                    'force_text': true,
                    "themes": { "stripes": true },
                    'data': function(obj, callback) {
                        $.ajax({
                                url: 'http://localhost:8080/product/browse?',
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
                "plugins": ["contextmenu", "dnd", "search", "state", "types", "wholerow"]

            });
        }
    };
    categoryTree.init();

});
