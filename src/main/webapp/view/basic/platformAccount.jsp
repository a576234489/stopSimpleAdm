<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@taglib prefix="shiro" uri="http://shiro.apache.org/tags"%>
<!DOCTYPE html>
<html>
<head>
  <meta charset="utf-8">
  <title>平台账号管理</title>
  <meta name="renderer" content="webkit">
  <meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1">
  <meta name="viewport" content="width=device-width, initial-scale=1.0, minimum-scale=1.0, maximum-scale=1.0, user-scalable=0">
  <link rel="stylesheet" href="../../resources/js/layuiadmin/layui/css/layui.css" media="all">
  <link rel="stylesheet" href="../../resources/js/layuiadmin/style/admin.css" media="all">
  <script src="../../resources/js/jquery/jquery-2.1.4.min.js"  type="text/javascript"></script>
  <script type="text/javascript" src="../../resources/js/jquery/jquery.form.min.js"></script>
  <!-- 引入angular的js -->
  <script type="text/javascript" src="../../resources/js/angularjs/angular.min.js"></script>
  <script type="text/javascript" src="../../resources/js/customer/base/base.js"></script>
  <script type="text/javascript" src="../../resources/js/customer/platformAccount/plateformAccountService.js"></script>
  <script type="text/javascript" src="../../resources/js/customer/role/roleService.js"></script>
  <script type="text/javascript" src="../../resources/js/customer/platformAccount/plateformAccountController.js"></script>
</head>
<style>
	.label-a{
		width:70px
	}
</style>
<body ng-app="stopSimple" ng-controller="plateformAccountController" ng-init="initData()">
  <div class="layui-fluid">
    <div class="layui-card">
      <div class="layui-form layui-card-header layuiadmin-card-header-auto">
        <div class="layui-form-item">
          <div class="layui-inline">
            <label class="layui-form-label label-a">账户名</label>
            <div class="layui-input-inline" style="width:160px">
              <input type="text" name="accountName" placeholder="请输入" autocomplete="off" class="layui-input">
            </div>
          </div>
          <div class="layui-inline">
            <label class="layui-form-label label-a">姓名</label>
            <div class="layui-input-inline" style="width:160px">
              <input type="text" name="userName" placeholder="请输入" autocomplete="off" class="layui-input">
            </div>
          </div>
          <div class="layui-inline">
            <label class="layui-form-label label-a">是否锁定</label>
            <div class="layui-input-inline" style="width:160px">
              <select id = "is_locked" name="locked" ng-model="entity.locked">
              </select>
            </div>
          </div>
          <div class="layui-inline">
            <label class="layui-form-label" label-a>所属角色</label>
            <div class="layui-input-inline" style="width:160px">
              <select id="role_id" name="roleId" lay-verify="required" ng-model="entity.role.id">
		         
		      </select>
            </div>
          </div>
          <div class="layui-inline">
            <button class="layui-btn layuiadmin-btn-list" data-type="reload" lay-submit lay-filter="LAY-app-platform-account-search">
              <i class="layui-icon layui-icon-search layuiadmin-button-btn"></i>
            </button>
          </div>
        </div>
      </div>

      <div class="layui-card-body">
        <div style="padding-bottom: 10px;">
          <shiro:hasPermission name="platformAccount:delBatch">
			<button class="layui-btn layuiadmin-btn-list" data-type="batchdel">删除</button>
		  </shiro:hasPermission>
		  <shiro:hasPermission name="platformAccount:add">
			 <button class="layui-btn layuiadmin-btn-list" data-type="add">添加</button>
		  </shiro:hasPermission>
        </div>
        <table id="LAY-app-platform-account-list" lay-filter="LAY-app-platform-account-list"></table> 
        <script type="text/html" id="buttonIsLocked">
          {{#  if(d.locked==0){ }}
			<shiro:hasPermission name="platformAccount:lock">
		     <button class="layui-btn layui-btn-xs" lay-event="lock">正常</button>
		    </shiro:hasPermission>
          {{#  } else { }}
			<shiro:hasPermission name="platformAccount:unlock">
		     <button class="layui-btn layui-btn-danger layui-btn-xs" lay-event="unlock">锁定</button>
		    </shiro:hasPermission>
          {{#  } }}
        </script>
        <script type="text/html" id="table-content-list">
		  <shiro:hasPermission name="platformAccount:reset">
		     <a class="layui-btn layui-btn-normal layui-btn-xs" lay-event="reset"><i class="layui-icon layui-icon-edit"></i>重置密码</a>
		  </shiro:hasPermission>
		  <shiro:hasPermission name="platformAccount:edit">
		     <a class="layui-btn layui-btn-normal layui-btn-xs" lay-event="edit"><i class="layui-icon layui-icon-edit"></i>编辑</a>
		  </shiro:hasPermission>
		  <shiro:hasPermission name="platformAccount:del">
		     <a class="layui-btn layui-btn-danger layui-btn-xs" lay-event="del"><i class="layui-icon layui-icon-delete"></i>删除</a>
		  </shiro:hasPermission>
        </script>
      </div>
    </div>
  </div>
  
  <script src="../../resources/js/layuiadmin/layui/layui.js"></script>  
  <script>
  layui.config({
    base: '../../resources/js/layuiadmin/' //静态资源所在路径
  }).extend({
    index: 'lib/index' //主入口模块
  }).use(['index', 'platformAccount', 'table'], function(){
    var table = layui.table
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
        var checkStatus = table.checkStatus('LAY-app-platform-account-list')
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
          table.reload('LAY-app-platform-account-list');
          layer.msg('已删除');
        });
      },
      add: function(){
        layer.open({
          type: 2
          ,title: '添加平台账号'
          ,content: '../../view/app/plateformAccount/listform.html'
          ,maxmin: true
          ,area: ["623px", "530px"]
          ,btn: ['确定', '取消']
          ,yes: function(index, layero){
        	  var othis = layero.find('iframe').contents().find("#layuiadmin-app-form-list");
        	  var entity = {role:{}};
        	  console.log(othis);
        	  entity.accountName = othis.find('input[name="accountName"]').val();
        	  entity.userName = othis.find('input[name="userName"]').val();
        	  entity.role.id = othis.find("#role_id option:selected").val()
        	  entity.description = othis.find('input[name="description"]').val();
        	  entity.password  = othis.find('input[name="password"]').val();
        	  entity.repassword  = othis.find('input[name="repassword"]').val();
        	  scope.edit(entity);
        	  console.log(entity);
            }
        }); 
      }
    }; 

    $('.layui-btn.layuiadmin-btn-list').on('click', function(){
      var type = $(this).data('type');
      active[type] ? active[type].call(this) : '';
    });

  });
  </script>
</body>
</html>
