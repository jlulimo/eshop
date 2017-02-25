$(function() {
    var categoryTree = {
        initTree: function() {
            $('#treeview').jstree({
                'core ': {
                    'data': [{
                        "text": "Root node",
                        "children": [
                            { "text": "Child node 1" },
                            { "text": "Child node 2" }
                        ]
                    }]
                }
            });
        }
    };
    categoryTree.initTree();
});
