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
		<c:if test="${empty positionEntity}">
		职位添加
		</c:if>
		<c:if test="${!empty positionEntity}">
		职位修改
		</c:if>
</h1>
<div class="row" style="margin-top: 5px;">
	<div class="col-xs-12">
		<form id="staffFrom" name="brigFrom" class="form-horizontal"
			role="form" method="post">
			<c:if test="${!empty positionEntity}">
				<input type="hidden" id="pageNum" name="pageNum"
					value="${page.pageNum }">
				<input type="hidden" id="pageSize" name="pageSize"
					value="${page.pageSize }">
				<input type="hidden" id="orderByColumn" name="orderByColumn"
					value="${page.orderByColumn }">
				<input type="hidden" id="orderByType" name="orderByType"
					value="${page.orderByType }">
				<input type="hidden" name="id"  value="${positionEntity.id }">	
			</c:if>		
			<div class="form-group">
				<label class="control-label col-sm-1 no-padding-right"
					for="accountName">所属消防队</label>
				<div class="col-sm-10 noform">
					<select class="form-control  brig" name="brigadeId">
						<option style='display: none'></option>
					</select>
				</div>
			</div>
			<div class="form-group">
				<label class="control-label col-sm-1 no-padding-right"
					for="name">上级名称</label>
				<div class="col-sm-10 noform">
					<select id = "posi" class="form-control  posi" name="parentId">
						<option style='display: none'></option>
						<option value="0">无上级职位</option>
					</select>
				</div>
			</div>
			<div class="form-group">
				<label class="control-label col-sm-1 no-padding-right"
					for="name">职位名称</label>
				<div class="col-sm-10">
					<div class="clearfix">
						<input  class="form-control" type="text" name="name"
							value="${positionEntity.name}" />
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
		onclick="webside.common.loadPage('/position/listUI.html?page=${page.pageNum }&rows=${page.pageSize }&sidx=${page.orderByColumn }&sord=${page.orderByType }')"
		class="btn btn-info btn-sm">
		<i class="fa fa-undo"></i>&nbsp;返回
	</button>
</div>
<script>
var positions = '${positions}';
if(positions != ''){
	var posis = JSON.parse(positions);
	var posiId = '${positionEntity.parentId}';
	posi();
}else{
	console.log(112);
	document.getElementById("posi").options.remove(1);   
}
var list = '${brigades}';
var brigs = JSON.parse(list);
var pid = '${positionEntity.brigadeId}';
brig();
function posi(){
	console.log(posis);
	var options = "";
	for (var i = 0; i < posis.length; i++) {
		var position = posis[i];
		if(posiId != null && posiId == position.id){
			options += '<option value="'+position.id+'" selected>'+position.name+'</option>';
		}else{
			options += '<option value="'+position.id+'">'+position.name+'</option>';
		}
		
		if(position.subPosi !=null){
			var spa = "&nbsp;&nbsp;&nbsp;&nbsp;"
			options += posiSubDeal(position.subPosi,spa);
		}
	}
	console.log(options);
	$(".posi").append(options)
}

function posiSubDeal(subPosi,spa){
	var options = "";
	for (var i = 0; i < subPosi.length; i++) {
		var posi = subPosi[i];
		if(posiId != null && posiId == posi.id){
			options += '<option value="'+posi.id+'" selected>'+spa+posi.name+'</option>';
		}else{
			options += '<option value="'+posi.id+'">'+spa+posi.name+'</option>';
		}
		
		if(posi.subPosi !=null){
			options += posiSubDeal(posi.subPosi,spa+"&nbsp;&nbsp;&nbsp;&nbsp;");
		}
	}
	return options;
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
	webside.common.commit('staffFrom', '/position/edit.html',
			'/position/listUI.html');
}
</script>