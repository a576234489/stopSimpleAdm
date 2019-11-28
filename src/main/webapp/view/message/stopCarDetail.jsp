<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@taglib prefix="shiro" uri="http://shiro.apache.org/tags"%>
<!DOCTYPE html>
<html>
<head>
  <meta charset="utf-8">
  <title>停车明细查询</title>
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
					<td class="w_1">支付方式</td>
					<td class="w_2">
						<ul class="info">
							<li> <input type="checkbox" name="" title="微信" lay-skin="primary" lay-filter="weChat_"></li>
							<input type="hidden"  name="weChat" class="weChat_">
							
							<li> <input type="checkbox" name="" title="支付宝" lay-skin="primary" lay-filter="alipay_"></li>
							<input type="hidden"  name="alipay" class="alipay_">
							
							<li> <input type="checkbox" name="" title="现金" lay-skin="primary" lay-filter="cash_"></li>
							<input type="hidden"  name="cash" class="cash_">
							
							<li> <input type="checkbox" name="" title="银联" lay-skin="primary" lay-filter="unionPay_"></li>
							<input type="hidden"  name="unionPay" class="unionPay_">
						</ul>			</td>
				</tr>	
				<tr>
					<td colspan="2"><input type="submit" name="" class="btn_poss" id="btn_poss" value="查询" data-type="reload" lay-submit lay-filter="LAY-app-stop-car-detail-search"></td>
				</tr>
			</tbody>
		</table>
	    <!--查询结果 star
	    <ul id="search_list"></ul>
	    
		<div class="more"><a href="javascript:void(0)">收起更多选项︽</a></div>-->
	</form>

      <div class="layui-card-body">
      	<div id="tabl_panel_id1">
        	<table id="LAY-app-role-list" lay-filter="LAY-app-stopCarDetail-list"></table>
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
  }).use(['index', 'stopCarDetail', 'table','laydate'], function(){
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
    form.on('submit(LAY-app-stop-car-detail-search)', function(data){
      console.log(111)
      console.log(data.field)
      var field = data.field;
      //执行重载
      table.reload('LAY-app-role-list', {
        where: field
      });
    });
    form.on('checkbox(weChat_)', function(data){
		if(data.elem.checked){//是否被选中，true或者false
			$('.weChat_').val(1);
    	} else{
    		$('.weChat_').val(2);
    	}
	  });
    form.on('checkbox(alipay_)', function(data){
		if(data.elem.checked){//是否被选中，true或者false
			$('.alipay_').val(1);
    	} else{
    		$('.alipay_').val(2);
    	}
	  });
	form.on('checkbox(cash_)', function(data){
		if(data.elem.checked){//是否被选中，true或者false
			$('.cash_').val(1);
    	} else{
    		$('.cash_').val(2);
    	}
	  });
	form.on('checkbox(unionPay_)', function(data){
		if(data.elem.checked){//是否被选中，true或者false
			$('.unionPay_').val(1);
    	} else{
    		$('.unionPay_').val(2);
    	}
	  });
  });
  $('.weChat_').val(2);//初始化复选框值为未选中 
  $('.alipay_').val(2);//初始化复选框值为未选中 
  $('.cash_').val(2);//初始化复选框值为未选中 
  $('.unionPay_').val(2);//初始化复选框值为未选中 
  </script>
</body>
</html>
