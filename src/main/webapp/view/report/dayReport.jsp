<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@taglib prefix="shiro" uri="http://shiro.apache.org/tags"%>
<!DOCTYPE html>
<html>
<head>
  <meta charset="utf-8">
  <title>日报表</title>
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
					<td class="w_2"><span class="cue_1">车场名称</span><input type="text" name="carParkName" class="text_1 searchkey" value="" id="key" autocomplete="off"><span class="cue_1">车场编号</span><input type="text" name="carParkNumber" class="text_4" value=""></td>
				</tr>
				<tr>
					<td class="w_1">查询日期</td>
					<td class="w_2"> <div class="layui-input-inline"><input style = "height:28px" type="text" class="layui-input" id="open-time" name = "openTime" placeholder="yyyy-MM-dd"></div></td>
				</tr>
				<tr>
					<td colspan="2"><input type="submit" name="" class="btn_poss" id="btn_poss" value="查询" data-type="reload" lay-submit lay-filter="LAY-app-day-report-search"></td>
				</tr>
			</tbody>
		</table>
	    <!--查询结果 star
	    <ul id="search_list"></ul>
	    
		<div class="more"><a href="javascript:void(0)">收起更多选项︽</a></div>-->
	</form>

      <div class="layui-card-body">
      	<div id="tabl_panel_id1">
	      	 <table class="layui-table" id="LAY-app-day-report-list" lay-data="{cellMinWidth: 80, page: true}" lay-filter="LAY-app-day-report-list">
              <thead>
                <tr>
                  <th lay-data="{type:'numbers'}" rowspan="2">序号</th>
                  <th lay-data="{field:'name',align:'center',width:160,templet: function(d){
					            	if(d.id != null){
					            		return d.name
					            	}else{
					            		return '合计'
					            	}
			            		}}" rowspan="2">车场名称</th>
                  <th lay-data="{field:'parkingLotNumber',width:160}" rowspan="2">车场编号</th>
                  <th lay-data="{field:'time',width:120}" rowspan="2">时间</th>
                  <th lay-data="{
                  				field:'totalBill',
                  				width:160,
                  				templet: function(d){
					            	if(d.id != null){
					            		return d.totalBill
					            	}else{
					            		return d.totalBillAll
					            	}
			            		}}" rowspan="2">临停账单总数</th>
                  <th lay-data="{align:'center'}" colspan="3">总收入</th>
                  <th lay-data="{align:'center'}" colspan="2">临停收入</th>
                  <th lay-data="{align:'center'}" colspan="2">长租卡收入</th>
                </tr>
                <tr>
                  <th lay-data="{field:'totalReceivables',width:120,templet: function(d){
					            	if(d.id != null){
					            		return d.totalReceivables
					            	}else{
					            		return d.totalReceivablesAll
					            	}
			            		}}" >总应收</th>
                  <th lay-data="{field:'totalAmount',width:120,templet: function(d){
					            	if(d.id != null){
					            		return d.totalAmount
					            	}else{
					            		return d.totalAmountAll
					            	}
			            		}}" >总实收</th>
                  <th lay-data="{field:'totalfavourable',width:120,templet: function(d){
					            	if(d.id != null){
					            		return d.totalfavourable
					            	}else{
					            		return d.totalfavourableAll
					            	}
			            		}}" >总优惠</th>
                  <th lay-data="{field:'totalCashAmount',width:120,templet: function(d){
					            	if(d.id != null){
					            		return d.totalCashAmount
					            	}else{
					            		return d.totalCashAmountAll
					            	}
			            		}}" >现金收入</th>
                  <th lay-data="{field:'totalUnCashAmount',width:120,templet: function(d){
					            	if(d.id != null){
					            		return d.totalUnCashAmount
					            	}else{
					            		return d.totalUnCashAmountAll
					            	}
			            		}}" >非现金收入</th>
                  <th lay-data="{field:'cardQantity',width:120,templet: function(d){
					            	if(d.id != null){
					            		return d.cardQantity
					            	}else{
					            		return d.cardQantityAll
					            	}
			            		}}" >开卡数量</th>
                  <th lay-data="{field:'tatolCardMoney',width:120,templet: function(d){
					            	if(d.id != null){
					            		return d.tatolCardMoney
					            	}else{
					            		return d.tatolCardMoneyAll
					            	}
			            		}}" >开卡金额</th>
                </tr>
                
              </thead>
            </table>
        	
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
  }).use(['index', 'dayReport', 'table','laydate'], function(){
    var table = layui.table
    ,form = layui.form;
    var laydate = layui.laydate;
    var d = new Date();
    laydate.render({
    	 elem: '#open-time'
    	 ,type: 'date'
    	 ,format: 'yyyy-MM-dd'
         ,trigger: 'click'
         ,value: d.getFullYear()+"-"+(d.getMonth()+1)+"-"+d.getDate()
      });
    //监听搜索
    form.on('submit(LAY-app-day-report-search)', function(data){
      console.log(111)
      console.log(data.field)
      var field = data.field;
      //执行重载
      table.reload('LAY-app-day-report-list', {
        where: field
      });
    });
  })
  function Layui_SetDataTableRowColor(TabDivId,RowIndex, ColorString)
  {
      try
      {
          var div = document.getElementById(TabDivId);
          if(div != null) //找到对象了
          {
              var table_main = div.getElementsByClassName('layui-table-main');   //通过class获取table_main
              if (table_main != null && table_main.length > 0)
              {
                  var table = table_main[0].getElementsByClassName('layui-table');   //通过class获取table
                  if (table != null && table.length > 0) {
                      var trs = table[0].querySelectorAll("tr");
                      if (trs != null && trs.length > 0) {
                          trs[RowIndex].style.color = ColorString;
                      }
                  }
              }
              
          }
      }
      catch(e)
      {
          console.log(e.message);
      }
  }
  </script>
</body>
</html>
