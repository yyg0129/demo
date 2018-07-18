<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>springBoot-mybatis集成</title>
 <link rel="stylesheet" href="css/layui.css">
 <script src="js/layui.js"></script>
  <script type="text/javascript" src="https://cdn.bootcss.com/jquery/1.10.2/jquery.min.js"></script>
<script type="text/javascript">
	var userTable = null;
	var openAdd = null;
	var user = null;
	function queryUser(){
		var userName=$("input[name='userName']").val();
		userTable.reload({
			where:{//设定异步数据接口的额外参数，任意设
				userName:userName
			}
		});
	}
	
	function detailUser(id){
		 $.ajax({
			 url:'/user/detailUser',
			 data:'id='+id,
			 success:function(e){
				layui.layer.open({
					  title: '新增用户',
					  area: ['400px', '400px'],
					  content: '<div>用户姓名:'+e.u_name+'</div><div>用户密码:'+e.psw+'</div>'
				});  
			}
		 })
	}
	function showAddUser(){
		openAdd=layui.layer.open({
			title:'新增用户',
			area:['400px','400px'],
			content: '<iframe src="addUser.jsp" height="97%" frameborder="no" border="0" marginwidth="0" marginheight="0" scrolling="no" allowtransparency="yes"></iframe>'
		
		});
	}
	
	
	function updateUser(id){
		layui.layer.msg(id);
	}
	
	layui.use(['layer','table',"form"],function(){
		//获取到table模块对象
		var table=layui.table;
		//第一个实例
		userTable=table.render({
			elem:'#userList',
			height:315,
			url:'/NoteQueryUser',//数据接口
			page:true,//开启分页
			cols:[[//表头
				{field:'id',title:'ID',width:250,sort:true,fixed:"left"},
				{field:'u_name',title:'用户名',width:250},
				{field:'psw',title:'用户密码',width:250},
				{field:"myControler",title:"操作",width:250,templet:function(d){
					return '<a href=javascript:deleteUser(\"'+d.id+'\")><img src="img/delete.ico"></a>'+
					'&nbsp;&nbsp<a href=javascript:detailUser(\"'+d.id+'\")><img src="img/detailed.ico"></a>'+
					'&nbsp;&nbsp<a href=javascript:updateUser(\"'+d.id+'\")><img src="img/update.ico"></a>'
				}},
			]]
		});
	});
	function deleteUser(id){
		$.ajax({
			url:'/user/deleteUser',
			data:'id='+id,
			success:function(e){
				if(e==1){
					queryUser();
				}else{
					layui.layer.msg("删除失败");
				}
			}
		});
	}
</script>
</head>
<body>
<div class="layui-form-item" style="margin-top:50px;margin-left:100px">
 
  <div class="layui-inline">
    <label class="layui-form-label">用户名:</label>
    <div class="layui-input-inline" style="width: 150px;">
      <input type="text" name="userName"  class="layui-input">
    </div>
   </div>
  
  <div class="layui-inline">
    <div class="layui-input-inline" style="width: 100px;">
     	<button class="layui-btn layui-btn-warm" onclick="queryUser();">查询</button>
     </div>
     <div class="layui-input-inline" style="width: 100px;">
      	<button class="layui-btn" onclick="showAddUser()">新增</button>
    </div>
  </div>
  
</div>
<table id="userList"></table>
</body>
</html>