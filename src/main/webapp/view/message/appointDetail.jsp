<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@taglib prefix="shiro" uri="http://shiro.apache.org/tags"%>
<!DOCTYPE html>
<html>
<head>
  <meta charset="utf-8">
  <title>预约明细查询</title>
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
  <script type="text/javascript" src="../../resources/js/customer/role/roleService.js"></script>
  <script type="text/javascript" src="../../resources/js/customer/role/roleController.js"></script>
</head>
<style>
	.label-a{
		width:70px
	}
</style>
<body ng-app="stopSimple" ng-controller="roleController" ng-init="">
  <div class="layui-fluid">
    <div class="layui-card truck_content">
      <form class="layui-form">
		<table cellpadding="0" cellspacing="0" class="screenBox">
			<tbody>
				<tr>
					<td class="w_1">查询条件</td>
					<td class="w_2"><span class="cue_1">车场名称</span><input type="text" name="carParkName" class="text_1 searchkey" value="" id="key" autocomplete="off"><span class="cue_1">车场编号</span><input type="text" name="carParkNumber" class="text_4" value=""><span class="cue_1">车牌号</span><input type="text" name="carNumber" class="text_4" value=""></td>
				</tr>
				<tr>
					<td class="w_1">支付时间</td>
					<td class="w_2"> <div class="layui-input-inline"><input style = "height:28px" type="text" class="layui-input" id="open-time" name = "openTime" placeholder="yyyy-MM-dd"></div>&nbsp;至 &nbsp;<div class="layui-input-inline"><input style = "height:28px" id="end-time" name = "endTime" type="text" class="layui-input" placeholder="yyyy-MM-dd" value=""></div></td>
				</tr>
				<tr>
					<td class="w_1">预约状态</td>
					<td class="w_2">
						<ul class="info">
							<li> <input type="checkbox" name="" title="预约中" lay-skin="primary" lay-filter="appointing_"></li>
							<input type="hidden"  name="appointing" class="appointing_">
							
							<li> <input type="checkbox" name="" title="取消预约" lay-skin="primary" lay-filter="cancelAppoint_"></li>
							<input type="hidden"  name="cancelAppoint" class="cancelAppoint_">
							
							<li> <input type="checkbox" name="" title="延长预约中" lay-skin="primary" lay-filter="extensionAppointing_"></li>
							<input type="hidden"  name="extensionAppointing" class="extensionAppointing_">
							
							<li> <input type="checkbox" name="" title="按时预约已完成" lay-skin="primary" lay-filter="appointFinish_"></li>
							<input type="hidden"  name="appointFinish" class="appointFinish_">
							
							<li> <input type="checkbox" name="" title="延长预约已完成" lay-skin="primary" lay-filter="extensionAppointFinish_"></li>
							<input type="hidden"  name="extensionAppointFinish" class="extensionAppointFinish_">
						</ul>			</td>
				</tr>	
				<tr>
					<td colspan="2"><input type="submit" name="" class="btn_poss" id="btn_poss" value="查询" data-type="reload" lay-submit lay-filter="LAY-app-appoint-detail-detail-search"></td>
				</tr>
			</tbody>
		</table>
	    <!--查询结果 star
	    <ul id="search_list"></ul>
	    
		<div class="more"><a href="javascript:void(0)">收起更多选项︽</a></div>-->
	</form>

      <div class="layui-card-body">
      	<div id="tabl_panel_id1">
        	<table id="LAY-app-appoint-detail-list" lay-filter="LAY-app-appoint-detail-list"></table>
        </div> 
        <script type="text/html" id="buttonIsLocked">
          {{#  if(d.locked==0){ }}
            <button class="layui-btn layui-btn-xs" lay-event="lock">正常</button>
          {{#  } else { }}
            <button class="layui-btn layui-btn-danger layui-btn-xs" lay-event="unlock">锁定</button>
          {{#  } }}
        </script>
        <script type="text/html" id="table-content-list">
		  <shiro:hasPermission name="role:permission">
			<a class="layui-btn layui-btn-normal layui-btn-xs" lay-event="permission"><i class="layui-icon layui-icon-edit"></i>分配权限</a>
		  </shiro:hasPermission>
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
  }).use(['index', 'appointDetail', 'table','laydate'], function(){
    var table = layui.table
    ,form = layui.form;
    var laydate = layui.laydate;
    var d = new Date();
    laydate.render({
    	 elem: '#open-time'
    	 ,type: 'datetime'
    	 ,format: 'yyyy-MM-dd HH:mm'
         ,trigger: 'click'
         ,value: d.getFullYear()+"-"+(d.getMonth()+1)+"-"+d.getDate()+" "+"00"+":"+"00"
      });
    laydate.render({
   	 elem: '#end-time'
   	 ,type: 'datetime'
   	 ,format: 'yyyy-MM-dd HH:mm'
     ,trigger: 'click'
     ,value: ''
     });
    //监听搜索
    form.on('submit(LAY-app-appoint-detail-detail-search)', function(data){
      console.log(111)
      console.log(data.field)
      var field = data.field;
      //执行重载
      table.reload('LAY-app-appoint-detail-list', {
        where: field
      });
    });
    form.on('checkbox(appointing_)', function(data){
		if(data.elem.checked){//是否被选中，true或者false
			$('.appointing_').val(1);
    	} else{
    		$('.appointing_').val(2);
    	}
	  });
    form.on('checkbox(cancelAppoint_)', function(data){
		if(data.elem.checked){//是否被选中，true或者false
			$('.cancelAppoint_').val(1);
    	} else{
    		$('.cancelAppoint_').val(2);
    	}
	  });
	form.on('checkbox(extensionAppointing_)', function(data){
		if(data.elem.checked){//是否被选中，true或者false
			$('.extensionAppointing_').val(1);
    	} else{
    		$('.extensionAppointing_').val(2);
    	}
	  });
	form.on('checkbox(appointFinish_)', function(data){
		if(data.elem.checked){//是否被选中，true或者false
			$('.appointFinish_').val(1);
    	} else{
    		$('.appointFinish_').val(2);
    	}
	  });
	form.on('checkbox(extensionAppointFinish_)', function(data){
		if(data.elem.checked){//是否被选中，true或者false
			$('.extensionAppointFinish_').val(1);
    	} else{
    		$('.extensionAppointFinish_').val(2);
    	}
	  });
  });
  $('.appointing_').val(2);//初始化复选框值为未选中 
  $('.cancelAppoint_').val(2);//初始化复选框值为未选中 
  $('.extensionAppointing_').val(2);//初始化复选框值为未选中 
  $('.appointFinish_').val(2);//初始化复选框值为未选中 
  $('.extensionAppointFinish_').val(2);//初始化复选框值为未选中 
  </script>
</body>
</html>
