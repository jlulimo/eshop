$(function() {
    var pager = {
        init: function() {
            pager.initEvent();
        },

        initEvent: function() {
            $('#addOkBtn').on('click', function(event) {
                var menuName = $('#addMenuName').attr('value');
                var menuUrl = $('#addMenuUrl').attr('value');
                var status = $('#addStatus').attr('value');



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
                            text: node.text,
                            parent: node.parent,
                            name:menuName,
                            url:menuUrl,
                            status:status
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

                // sel = ref.create_node(sel, { "type": "category" });
                // if (sel) {
                // ref.edit(sel, null, function(node, status) {
                //     $('#treeLoading').show();
                //     $.ajax({
                //             contentType: "application/json; charset=utf-8",
                //             url: constants.BASE_URL + '/menu/add',
                //             type: 'POST',
                //             dataType: 'json',
                //             data: JSON.stringify({
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
                //             $('#treeLoading').hide();
                //         });

                // });

                // }

            });
        }
    };

    pager.init();
})
