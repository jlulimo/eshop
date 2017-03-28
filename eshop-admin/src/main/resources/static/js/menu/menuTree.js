$(function() {
    var pager = {
        init: function() {
            pager.initEvent();
            pager.initTree();
            $('#treeLoading').hide();
            $('#alertWin').hide();
            $('#addMenuWin').modal('hide');
        },

        initEvent: function() {
            $('#createNode').on('click', function(event) {
                $('#addMenuWin').modal('show');
            });

            $('#addOkBtn').on('click', function(event) {
                var menuName = $('#addMenuName').val();
                var menuUrl = $('#addMenuUrl').val();
                var status = $('#addStatus').val();
                var ref = $('#treeview').jstree(true),
                    sel = ref.get_selected(true);
                if (!sel.length) {
                    return false;
                }
                sel = sel[0];
                $.ajax({
                        contentType: "application/json; charset=utf-8",
                        url: constants.BASE_URL + '/menu/add',
                        type: 'POST',
                        dataType: 'json',
                        data: JSON.stringify({
                            parent: sel.id,
                            text: menuName,
                            url: menuUrl,
                            status: status
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
                        $('#addMenuWin').modal('hide');
                        $('#treeLoading').hide();
                    });
            });

            $('#saveBtn').on('click', function(event) {
                $('#treeLoading').show();
                var ref = $('#treeview').jstree(true),
                    sel = ref.get_selected(true);
                if (!sel.length) {
                    return false;
                }
                sel = sel[0];
                var menuName = $('#menuName').val();
                var menuUrl = $('#menuUrl').val();
                var status = $('#status').val();
                $.ajax({
                        contentType: "application/json; charset=utf-8",
                        url: constants.BASE_URL + '/menu/edit',
                        type: 'POST',
                        dataType: 'json',
                        data: JSON.stringify({
                            id: sel.id,
                            text: menuName,
                            parent: sel.parent,
                            url: menuUrl,
                            status: status
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




                // ref.edit(sel, null, function(node, status) {
                //     $('#treeLoading').show();
                //     $.ajax({
                //             contentType: "application/json; charset=utf-8",
                //             url: constants.BASE_URL + '/menu/edit',
                //             type: 'POST',
                //             dataType: 'json',
                //             data: JSON.stringify({
                //                 id: node.id,
                //                 text: node.text,
                //                 parent: node.parent,
                //             }),
                //         })
                //         .done(function(result) {
                //             if (result.code != 0) {
                //                 $('#alert-body').text(result.msg);
                //                 $('#alertWin').show();
                //             }
                //             $('#treeview').jstree(true).refresh('#' + result.data);
                //             $('#treeLoading').hide();
                //         })
                //         .fail(function() {
                //             console.log("error");
                //         })
                //         .always(function() {
                //             console.log("complete");
                //         });

                // });

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
                        url: constants.BASE_URL + '/menu/delete',
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

            $('#treeview').on("click.jstree", function(e) {
                var ref = $('#treeview').jstree(true),
                    sel = ref.get_selected(true);
                if (!sel.length) {
                    return false;
                }
                sel = sel[0];
                $.ajax({
                        url: constants.BASE_URL + '/menu/' + sel.id,
                        type: 'GET',

                    })
                    .done(function(result) {
                        if (result.code == 0) {
                            var menuInfo = result.data;
                            $('#menuName').val(menuInfo.text);
                            $('#menuUrl').val(menuInfo.url);
                            $('#status').val(menuInfo.status);
                        }
                        console.log("success");
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
                                url: constants.BASE_URL + '/menu/browse?',
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



    pager.init();
});
