<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@taglib prefix="shiro" uri="http://shiro.apache.org/tags"%>
<!DOCTYPE html>
<html>
<head>
  <meta charset="utf-8">
  <title>菜单管理</title>
  <meta name="renderer" content="webkit">
  <meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1">
  <meta name="viewport" content="width=device-width, initial-scale=1.0, minimum-scale=1.0, maximum-scale=1.0, user-scalable=0">
  <link rel="stylesheet" href="../../resources/js/layuiadmin/layui/css/layui.css" media="all">
  <link rel="stylesheet" href="../../resources/js/layuiadmin/style/admin.css" media="all">
  <link rel="stylesheet" href="../../resources/fonts/fontawesome/font-awesome.min.css" media="all">
  <script src="../../resources/js/jquery/jquery-2.1.4.min.js"  type="text/javascript"></script>
  <script type="text/javascript" src="../../resources/js/jquery/jquery.form.min.js"></script>
  <link type="text/css" rel="stylesheet" href="../../resources/js/jstree/themes/default/style.css"/>
  <script type="text/javascript" src="../../resources/js/jstree/jstree.min.js"></script>   
  <script type="text/javascript" src="../../resources/js/jstree/jstree.checkbox.js"></script> 
  <script type="text/javascript" src="../../resources/js/underscore/underscore-min.js"></script> 
  <!-- 引入angular的js -->
  <script type="text/javascript" src="../../resources/js/angularjs/angular.min.js"></script>
  <script type="text/javascript" src="../../resources/js/customer/base/base.js"></script>
  <script type="text/javascript" src="../../resources/js/customer/menu/menuService.js"></script>
  <script type="text/javascript" src="../../resources/js/customer/menu/menuController.js"></script>
</head>
<style>
	.label-a{
		width:70px
	}
</style>
<body ng-app="stopSimple" ng-controller="menuController" ng-init="">
  <div class="layui-fluid">
    <div class="layui-card">
      <div class="layui-card-body">
        <div style="padding-bottom: 10px;">
          <shiro:hasPermission name="resource:delBatch">
			<button class="layui-btn layuiadmin-btn-list" data-type="batchdel">删除</button>
		  </shiro:hasPermission>
		  <shiro:hasPermission name="resource:add">
			 <button class="layui-btn layuiadmin-btn-list" data-type="add">添加</button>
		  </shiro:hasPermission>
        </div>
        <table id="LAY-app-menu-list" lay-filter="LAY-app-menu-list"></table> 
        <script type="text/html" id="table-content-list">
		  <shiro:hasPermission name="resource:edit">
			<a class="layui-btn layui-btn-normal layui-btn-xs" lay-event="edit"><i class="layui-icon layui-icon-edit"></i>编辑</a>
		  </shiro:hasPermission>
		  <shiro:hasPermission name="resource:del">
			<a class="layui-btn layui-btn-danger layui-btn-xs" lay-event="del"><i class="layui-icon layui-icon-delete"></i>删除</a>
		  </shiro:hasPermission>
        </script>
      </div>
    </div>
  </div>
  
<script src="../../resources/js/layuiadmin/layui/layui.js" charset="utf-8"></script>
<script type="text/javascript">
	layui.config({
    base: '../../resources/js/layuiadmin/' //静态资源所在路径
    }).extend({
    index: 'lib/index' //主入口模块
    }).use(['index','element', 'tree','layer', 'form', 'upload', 'treeGrid','menu'], function(){
   	 	var treeGrid = layui.treeGrid
   	    ,form = layui.form;
   	    
   	    //监听搜索
   	    form.on('submit(LAY-app-platform-account-search)', function(data){
   	      console.log(data.field)
   	      var field = data.field;
   	      //执行重载
   	      table.reload('LAY-app-platform-account-list', {
   	        where: field
   	      });
   	    });
   	    
   	    var $ = layui.$, active = {
   	      batchdel: function(){
   	        var checkStatus = treeGrid.checkStatus('LAY-app-menu-list')
   	        ,checkData = checkStatus.data; //得到选中的数据
   	        if(checkData.length === 0){
   	          return layer.msg('请选择数据');
   	        }
   	      
   	        layer.confirm('确定删除吗？', function(index) {
   	          var ids = [];
   	          for(var i = 0;i<checkData.length;i++){
   	        	  ids[i] = checkData[i].id;
   	          }
   			  scope.del(ids);
   			  treeGrid.reload('LAY-app-menu-list');
   	          layer.msg('已删除');
   	        });
   	      },
   	      add: function(){
   	    	var checkStatus = treeGrid.checkStatus('LAY-app-menu-list')
        	  ,checkData = checkStatus.data; //得到选中的数据
	   	    console.log(checkData);
	     	if(checkData.length>1){
	     		layer.msg('只能选择一个', {icon: 2});
	     	}else{
	   	        layer.open({
	   	          type: 2
	   	          ,title: '添加菜单 '
	   	          ,content: '../../view/app/menu/listform.html'
	   	          ,maxmin: true
	   	          ,area: ["623px", "530px"]
	   	          ,btn: ['确定', '取消']
	   	          ,yes: function(index, layero){
	   	        	  var othis = layero.find('iframe').contents().find("#layuiadmin-app-form-list");
	   	        	  var entity = {};
	   	        	  entity.name = othis.find('input[name="name"]').val();
	   	        	  entity.sourceUrl = othis.find('input[name="sourceUrl"]').val();
	   	        	  entity.type = othis.find("#type option:selected").val();
	   	        	  if(entity.type != -1){
	   	        		if(checkData.length == 1){
		   	 	     		entity.parentId = checkData[0].id;
		   	 	     	}else{
		   	 	     		entity.parentId = null;
		   	 	     	}
	   	        		scope.edit(entity);
	   	        	  }else{
	   	        		    layer.msg('请选择一个资源类型 ', {icon: 2});
	   	        	  }
	   	            }
	   	        });
	     	}
   	      }
   	    }; 

   	    $('.layui-btn.layuiadmin-btn-list').on('click', function(){
   	      var type = $(this).data('type');
   	      active[type] ? active[type].call(this) : '';
   	    });
    })
</script>
</body>
</html>
