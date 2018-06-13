<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<link rel="stylesheet" href="css/layui.css">
 <script src="js/layui.js"></script>
  <script type="text/javascript" src="https://cdn.bootcss.com/jquery/1.10.2/jquery.min.js"></script>
<title>修改用户8</title>
<script>
layui.use('form', function(){
	  var form = layui.form;
	  
	  //监听提交
	 form.on('submit(formDemo)',function(data){
		
		//layer.msg(JSON.stringify(data.field));
		console.log(data.field);
		$.ajax({
			url:'/user/addUser',
			data:data.field,
			success:function(e){
				if(e==1){
					layui.layer.msg("新增成功");
					parent.layui.layer.close(parent.openAdd);
					parent.queryUser();
				}else{
					layui.layer.msg("新增失败");
				}
				
			}
			
		});
		return false;
	});
	
	
	});
</script>
</head>
<body>
	<form class="layui-form" action="">
  <div class="layui-form-item">
    <label class="layui-form-label">用户名</label>
    <div class="layui-input-block">
      <input type="text" name="name" required  lay-verify="required" placeholder="请输入用户名" autocomplete="off" class="layui-input">
    </div>
  </div>
  <div class="layui-form-item">
    <label class="layui-form-label">密码</label>
    <div class="layui-input-inline">
      <input type="password" name="password" required lay-verify="required" placeholder="请输入密码" autocomplete="off" class="layui-input">
    </div>
  </div>


 <div class="layui-form-item">
    <div class="layui-input-block">
      <button class="layui-btn" lay-submit lay-filter="formDemo">立即提交</button>
      <button type="reset" class="layui-btn layui-btn-primary">重置</button>
    </div>
  </div>
</form>

</body>
</html>