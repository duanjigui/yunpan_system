###
用户登录：
	用户类型： 1  表示超级管理员  
		   0 表示普通用户[只能登录前台系统]
		   2 表示部门管理员
##
分页插件：
PageHelper.startPage(页数,每页的条数)
下面紧跟的查询语句，就是用来分页查询的
eg:  List list= a.selectAll()
注意：该分页插件只能对紧挨着的查询语句进行分页显示，若果想多次分页查询，则需要书写上面的语句多次
	最后查询的结果返回来的实际上是page类型的，或者你也可以将list结果包装到PageInfo类中。
PageInfo pageinfo=new PageInfo)_


BaseService将基本的增、删、改、查部分进行了简单抽象的封装，更能减少代码量。

权限管理：
	




