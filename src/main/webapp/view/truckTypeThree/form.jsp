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
		<c:if test="${empty truckTypeEntity}">
		消防车三级分类添加
		</c:if>
		<c:if test="${!empty truckTypeEntity}">
		消防车三级分类修改
		</c:if>
</h1>
<div class="row" style="margin-top: 5px;">
	<div class="col-xs-12">
		<form id="truckTypeThreeFrom" name="brigFrom" class="form-horizontal"
			role="form" method="post">
			<c:if test="${!empty truckTypeEntity}">
				<input type="hidden" id="pageNum" name="pageNum"
					value="${page.pageNum }">
				<input type="hidden" id="pageSize" name="pageSize"
					value="${page.pageSize }">
				<input type="hidden" id="orderByColumn" name="orderByColumn"
					value="${page.orderByColumn }">
				<input type="hidden" id="orderByType" name="orderByType"
					value="${page.orderByType }">
				<input type="hidden" name="id"  value="${truckTypeEntity.id }">	
			</c:if>	
			<div class="form-group">
				<label class="control-label col-sm-1 no-padding-right"
					for="name">消防队名称</label>
				<div class="col-sm-10 noform">
					<select class="form-control  brig" name="brigadeId">
					</select>
				</div>
			</div>	
			<div class="form-group">
				<label class="control-label col-sm-1 no-padding-right"
					for="name">上级分类名称</label>
				<div class="col-sm-10 noform">
					<select class="form-control  posi" name="parentId">
					</select>
				</div>
			</div>
			<div class="form-group">
				<label class="control-label col-sm-1 no-padding-right"
					for="name">消防车名称</label>
				<div class="col-sm-10">
					<div class="clearfix">
						<input  class="form-control" type="text" name="name"
							value="${truckTypeEntity.name}" />
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
		onclick="webside.common.loadPage('/truckType/listUI.html?page=${page.pageNum }&rows=${page.pageSize }&sidx=${page.orderByColumn }&sord=${page.orderByType }')"
		class="btn btn-info btn-sm">
		<i class="fa fa-undo"></i>&nbsp;返回
	</button>
</div>
<script>

var truckTypes = '${truckTypes}';
var truckTs = JSON.parse(truckTypes)
var truckTd = '${truckTypeEntity.parentId}';
truckType();
var list = '${brigades}';
var brigs = JSON.parse(list);
var pid = '${truckEntity.brigadeId}';
brig()
function truckType(){
	var options = "";
	for (var i = 0; i < truckTs.length; i++) {
		var truckT = truckTs[i];
		if(truckTd != null && truckTd == truckT.id){
			options += '<option value="'+truckT.id+'" selected>'+truckT.name+'</option>';
		}else{
			options += '<option value="'+truckT.id+'">'+truckT.name+'</option>';
		}
	}
	console.log(options);
	$(".posi").append(options)
}
function brig(){
	console.log(123);
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
	$(".brig").append(options)
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
	webside.common.commit('truckTypeThreeFrom', '/truckTypeThree/edit.html',
			'/truckTypeThree/listUI.html');
}
</script>