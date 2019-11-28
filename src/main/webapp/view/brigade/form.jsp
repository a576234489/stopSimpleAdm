<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<c:set var="ctx" value="${pageContext.request.contextPath }" />
<link rel="stylesheet"
	href="${ctx }/resources/js/select2/select2.min.css" />
<script type="text/javascript"
	src="${ctx }/resources/js/select2/select2.min.js"></script>
<script type="text/javascript"
		src="${ctx }/resources/js/select2/zh-CN.js"></script>
<script type="text/javascript"
	src="${ctx }/resources/js/address/jsAddress.js"></script>
<script type="text/javascript"
	src="${ctx }/resources/js/WdtePicker/WdatePicker.js"></script>
<h1>
		<c:if test="${empty brigade}">
		消防队添加
		</c:if>
		<c:if test="${!empty brigade}">
		消防队修改
		</c:if>
</h1>
<div class="row" style="margin-top: 5px;">
	<div class="col-xs-12">
		<form id="brigFrom" name="brigFrom" class="form-horizontal"
			role="form" method="post">
			<c:if test="${!empty brigade}">
				<input type="hidden" id="pageNum" name="pageNum"
					value="${page.pageNum }">
				<input type="hidden" id="pageSize" name="pageSize"
					value="${page.pageSize }">
				<input type="hidden" id="orderByColumn" name="orderByColumn"
					value="${page.orderByColumn }">
				<input type="hidden" id="orderByType" name="orderByType"
					value="${page.orderByType }">
				<input type="hidden" name="id"  value="${brigade.id }">	
			</c:if>
			<div class="form-group">
				<label class="control-label col-sm-1 no-padding-right"
					for="name">消防队名称</label>
				<div class="col-sm-10">
					<div class="clearfix">
						<input  class="form-control" type="text" name="fireBrigadeName"
							value="${brigade.fireBrigadeName}" />
					</div>
				</div>
			</div>
				
			<div class="form-group">
				<label class="control-label col-sm-1 no-padding-right"
					for="accountName">上级消防队</label>
				<div class="col-sm-10 noform">
					<select class="form-control  depa" name="parentId">
						<option value="0">无上级消防队</option>
					</select>
				</div>
			</div>
			
			<div class="form-group">
					<label class="control-label col-sm-1 no-padding-right"
						for="accountName">省</label>
					<div class="col-sm-10 noform">
						<select class="form-control" id="provice" name="province">

						</select>
					</div>
			</div>

			<div class="form-group">
				<label class="control-label col-sm-1 no-padding-right"
					for="accountName">市</label>
				<div class="col-sm-10 noform">
					<select class="form-control" id="city" name="city">

					</select>
				</div>
			</div>
			<div class="form-group">
				<label class="control-label col-sm-1 no-padding-right"
					for="accountName">区</label>
				<div class="col-sm-10 noform">
					<select class="form-control" id="area" name="area">

					</select>
				</div>
			</div>
			
			<div class="form-group">
				<label class="control-label col-sm-1 no-padding-right"
					for="name">街道</label>
				<div class="col-sm-10">
					<div class="clearfix">
						<input  class="form-control" type="text" name="street"
							value="${brigade.street }" />
					</div>
				</div>
			</div>
			
			<div class="form-group">
				<label class="control-label col-sm-1 no-padding-right"
					for="name">经纬度</label>
				<div class="col-sm-10">
					<div class="clearfix">
						<input  class="form-control" type="text" name="longitudeLatitude"
							value="${brigade.longitudeLatitude }" />
					</div>
				</div>
			</div>
			
			<div class="form-group">
				<label class="control-label col-sm-1 no-padding-right"
					for="name">打卡范围</label>
				<div class="col-sm-10">
					<div class="clearfix">
						<input  class="form-control" type="text" name="scope"
							value="${brigade.scope }" />
					</div>
				</div>
			</div>
			
			<div class="form-group">
				<label class="control-label col-sm-1 no-padding-right"
					for="name">上午上班时间</label>
				<div class="col-sm-10">
					<div class="clearfix">
						<input type="text" name="morWorkTime" value="${brigade.morWorkTime}" onclick="WdatePicker({dateFmt:'HH:mm:ss'})"/>
					</div>
				</div>
			</div>
			
			<div class="form-group">
				<label class="control-label col-sm-1 no-padding-right"
					for="name">上午下班时间</label>
				<div class="col-sm-10">
					<div class="clearfix">
						<input type="text" name="morOffTime" value="${brigade.morOffTime}" onclick="WdatePicker({dateFmt:'HH:mm:ss'})"/>
					</div>
				</div>
			</div>	
			
			<div class="form-group">
				<label class="control-label col-sm-1 no-padding-right"
					for="name">下午上班时间</label>
				<div class="col-sm-10">
					<div class="clearfix">
						<input type="text" name="aftWorkTime" value="${brigade.aftWorkTime}" onclick="WdatePicker({dateFmt:'HH:mm:ss'})"/>
					</div>
				</div>
			</div>	
			
			<div class="form-group">
				<label class="control-label col-sm-1 no-padding-right"
					for="name">下午下班时间</label>
				<div class="col-sm-10">
					<div class="clearfix">
						<input type="text" name="aftOffTime" value="${brigade.aftOffTime}" onclick="WdatePicker({dateFmt:'HH:mm:ss'})"/>
					</div>
				</div>
			</div>		


		</form>
		<div class="hr hr-dotted"></div>
	</div>
</div>
<div class="center">
	<button id="btnAdd" type="button" onclick="commit()"
		class="btn btn-success btn-sm">
		<i class="fa fa-user-plus"></i>&nbsp; 保存
	</button>
	<button id="btn" type="button"
		onclick="webside.common.loadPage('/brigade/listUI.html?page=${page.pageNum }&rows=${page.pageSize }&sidx=${page.orderByColumn }&sord=${page.orderByType }')"
		class="btn btn-info btn-sm">
		<i class="fa fa-undo"></i>&nbsp;返回
	</button>
</div>
<script>
var list = '${list}';
var pid = '${brigade.parentId}';
var brigs = JSON.parse(list);
brig();
var province = '${brigade.province }';
var city = '${brigade.city}';
var direct = '${brigade.area}';
addressInit('provice', 'city', 'area', province, city, direct);
function brig(){
	console.log(brigs);
	var options = "";
	for (var i = 0; i < brigs.length; i++) {
		var brig = brigs[i];
		if(pid != null && pid == brig.id){
			options += '<option value="'+brig.id+'" selected>'+brig.fireBrigadeName+'</option>';
		}else{
			options += '<option value="'+brig.id+'">'+brig.fireBrigadeName+'</option>';
		}
		
		if(brig.subBrig !=null){
			var spa = "&nbsp;&nbsp;&nbsp;&nbsp;"
			options += brigSubDeal(brig.subBrig,spa);
		}
	}
	console.log(options);
	$(".depa").append(options)
}

function brigSubDeal(subBrigs,spa){
	var options = "";
	for (var i = 0; i < subBrigs.length; i++) {
		var brig = subBrigs[i];
		if(pid != null && pid == brig.id){
			options += '<option value="'+brig.id+'" selected>'+spa+brig.fireBrigadeName+'</option>';
		}else{
			options += '<option value="'+brig.id+'">'+spa+brig.fireBrigadeName+'</option>';
		}
		
		if(brig.subBrig !=null){
			options += brigSubDeal(brig.subBrig,spa+"&nbsp;&nbsp;&nbsp;&nbsp;");
		}
	}
	return options;
}

function commit() {
	webside.common.commit('brigFrom', '/brigade/edit.html',
			'/brigade/listUI.html');
}
</script>