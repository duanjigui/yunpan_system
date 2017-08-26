var indexdata = 
[
    { text: '用户管理',isexpand:false, children: [ 
 		{url:"manager/user/list",text:"用户管理"},
 	]
    },
    { text: '角色管理',isexpand:false, children: [ 
   		{url:"/manager/role/list",text:"角色管理"},
   		{url:"/manager/role/dispatch",text:"角色分配"}
   		]
    },
    { text: '组织管理',isexpand:false, children: [ 
 		{url:"manager/orianzation/list",text:"组织管理"},
 		]
    },
    { text: '权限管理',isexpand:false, children: [ 
   		{url:"/manager/auth/list",text:"权限管理"},
   		{url:"/manager/auth/dispatch/index",text:"权限分配"}
   	]
                                          }
];


var indexdata2 =
[
    { isexpand: "true", text: "表格", children: [
        { isexpand: "true", text: "可排序", children: [
		    { url: "dotnetdemos/grid/sortable/client.aspx", text: "客户端" },
            { url: "dotnetdemos/grid/sortable/server.aspx", text: "服务器" }
	    ]
        },
        { isexpand: "true", text: "可分页", children: [
		    { url: "dotnetdemos/grid/pager/client.aspx", text: "客户端" },
            { url: "dotnetdemos/grid/pager/server.aspx", text: "服务器" },
            { url: "dotnetdemos/grid/pager/server_scroll.aspx", text: "滚动分页" }
	    ]
        },
        { isexpand: "true", text: "树表格", children: [
		    { url: "dotnetdemos/grid/treegrid/tree.aspx", text: "树表格" }, 
		    { url: "dotnetdemos/grid/treegrid/tree2.aspx", text: "树表格2" }
	    ]
        }
    ]
    }
];
