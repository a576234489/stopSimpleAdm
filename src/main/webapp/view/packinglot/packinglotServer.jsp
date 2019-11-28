<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@taglib prefix="shiro" uri="http://shiro.apache.org/tags"%>
<!DOCTYPE html>
<html>
<head>
  <meta charset="utf-8">
  <title>停车场列表服务查询</title>
  <meta name="renderer" content="webkit">
  <meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1">
  <meta name="viewport" content="width=device-width, initial-scale=1.0, minimum-scale=1.0, maximum-scale=1.0, user-scalable=0">
  <link rel="stylesheet" href="../../resources/js/layuiadmin/layui/css/layui.css" media="all">
   <link rel="stylesheet" href="../../resources/css/truck.css" media="all">
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
  <script type="text/javascript" src="../../resources/js/customer/packinglotServer/packingLotServerService.js"></script>
  <script type="text/javascript"
	src="../../resources/js/customer/packinglot/packingLotService.js"></script>
  <script type="text/javascript" src="../../resources/js/customer/packinglotServer/packingLotServerController.js"></script>
</head>
<style>
	.label-a{
		width:70px
	}
    .layui-table-cell {
           height: auto;
           line-height: 87px;
       }
</style>
<body ng-app="stopSimple" ng-controller="packingLotServerController" ng-init="packingLotInit()">
  <div class="layui-fluid">
    <div class="layui-card truck_content">
      <div class="layui-form layui-card-header layuiadmin-card-header-auto">
        <div class="layui-form-item">
          <div class="layui-inline">
            <label class="layui-form-label label-a">停车场名称</label>
            <div class="layui-input-inline" style="width:160px">
               <select lay-verify="required" id="packing_lot_first_select" name = "parkingLotId">
	          
	           </select>
            </div>
          </div>
          
          <div class="layui-inline">
            <button class="layui-btn layuiadmin-btn-list" data-type="reload" lay-submit lay-filter="LAY-app-packinglot-search">
              <i class="layui-icon layui-icon-search layuiadmin-button-btn"></i>
            </button>
          </div>
        </div>
      </div>

      <div class="layui-card-body">
        <div style="padding-bottom: 10px;">
          <shiro:hasPermission name="role:delBatch">
			<button class="layui-btn layuiadmin-btn-list" data-type="batchdel">删除</button>
		  </shiro:hasPermission>
		  <shiro:hasPermission name="role:add">
			<button class="layui-btn layuiadmin-btn-list" data-type="add">添加</button>
		  </shiro:hasPermission>
        </div>
        <table id="LAY-app-packinglot-list" lay-filter="LAY-app-packinglot-list"></table> 
        <script type="text/html" id="buttonIsLocked">
          {{#  if(d.locked==0){ }}
            <button class="layui-btn layui-btn-xs" lay-event="lock">正常</button>
          {{#  } else { }}
            <button class="layui-btn layui-btn-danger layui-btn-xs" lay-event="unlock">锁定</button>
          {{#  } }}
        </script>
        <script type="text/html" id="table-content-list">
          <shiro:hasPermission name="role:edit">
			<a class="layui-btn layui-btn-normal layui-btn-xs" lay-event="edit"><i class="layui-icon layui-icon-edit"></i>编辑</a>
		  </shiro:hasPermission>
	      <shiro:hasPermission name="role:del">
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
  }).use(['index', 'packingLotServer', 'table'], function(){
    var table = layui.table
    ,form = layui.form;
    
    //监听搜索
    form.on('submit(LAY-app-packinglot-search)', function(data){
      console.log(data.field)
      var field = data.field;
      //执行重载
      table.reload('LAY-app-packinglot-list', {
        where: field
      });
    });
    
    var $ = layui.$, active = {
      batchdel: function(){
        var checkStatus = table.checkStatus('LAY-app-packinglot-list')
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
          table.reload('LAY-app-packinglot-list');
          layer.msg('已删除');
        });
      },
      add: function(){
        layer.open({
          type: 2
          ,title: '添加停车场服务  '
          ,content: '../../view/app/packinglotServer/listform.html'
          ,maxmin: true
          ,area: ["670px", "565px"]
          ,btn: ['确定', '取消']
          ,yes: function(index, layero){
        	  var entity = {imgs:[]};
        	  var othis = layero.find('iframe').contents().find("#layuiadmin-app-form-list");
        	  entity.parkingLotId  = othis.find('#packing_lot_select option:selected').val();
        	  entity.servName  = othis.find('input[name="servName"]').val();
        	  var child = $(layero).find("iframe")[0].contentWindow;//通过该对象可以获取iframe中的变量，调用iframe中的方法
        	  for(let data of child.formdata.entries()){
        		  entity.imgs.push(data[1]);
      		  }
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
