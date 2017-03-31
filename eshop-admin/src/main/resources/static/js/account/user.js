$(function() {
    var pager = {
        inint: function() {
            $('#userTable').DataTable({
                "serverSide": true,
                "processing": true,
                "sAjaxSource": constants.BASE_URL + '/account/users',
                "aoColumns": [
                    { "mDataProp": "name" },
                    { "mDataProp": "groupName" }
                ],
                "fnServerData": function(sSource, aoData, fnCallback, oSettings) {
                    $.ajax({
                            contentType: "application/json; charset=utf-8",
                            url: sSource,
                            type: 'GET',
                            dataType: 'json',
                            data: { "aoData": JSON.stringify(aoData) },
                        })
                        .done(function(result) {
                            console.log("success");
                            if (result.code == 0) {
                                fnCallback(result)
                            } else {
                                fnCallback({ "data": [] });
                            }
                        })
                        .fail(function() {
                            console.log("error");
                            fnCallback({ "data": [] });
                        })
                        .always(function() {
                            console.log("complete");
                        });

                },
            })

        },
        initEvent:function(){
            
        }
    }

    pager.inint();
})
