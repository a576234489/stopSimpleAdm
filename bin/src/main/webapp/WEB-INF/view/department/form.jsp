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
<script type="text/javascript">
	
</script>
<div class="page-header">
	<h1>部门编辑</h1>
</div>
<div class="row" style="margin-top: 5px;">
	<div class="col-xs-12">
		<form id="depaFrom" name="depaFrom" class="form-horizontal"
			role="form" method="post">

			<c:if test="${!empty department}">
				<input type="hidden" id="pageNum" name="pageNum"
					value="${page.pageNum }">
				<input type="hidden" id="pageSize" name="pageSize"
					value="${page.pageSize }">
				<input type="hidden" id="orderByColumn" name="orderByColumn"
					value="${page.orderByColumn }">
				<input type="hidden" id="orderByType" name="orderByType"
					value="${page.orderByType }">
				<input type="hidden" name="id"  value="${department.id }">	
			</c:if>

			<div class="form-group">
				<label class="control-label col-sm-1 no-padding-right"
					for="accountName">上级部门</label>
				<div class="col-sm-10 noform">
					<select class="form-control  depa" name="parentId">
						<option value="0">无上级部门</option>
						
					</select>
				</div>
			</div>

			<div class="form-group">
				<label class="control-label col-sm-1 no-padding-right"
					for="name">部门名称</label>
				<div class="col-sm-10">
					<div class="clearfix">
						<input  class="form-control" type="text" name="name"
							value="${department.name }" />
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
		onclick="webside.common.loadPage('/department/listUI.html<c:if test="${!empty department}">?page=${page.pageNum }&rows=${page.pageSize }&sidx=${page.orderByColumn }&sord=${page.orderByType }</c:if>')"
		class="btn btn-info btn-sm">
		<i class="fa fa-undo"></i>&nbsp;返回
	</button>
</div>
<script>
var list = '${list}';
var pid = '${department.parentId}';
var depas = JSON.parse(list);
depa()
function depa(){
	console.log(depas);
	var options = "";
	for (var i = 0; i < depas.length; i++) {
		var depa = depas[i];
		if(pid != null && pid == depa.id){
			options += '<option value="'+depa.id+'" selected>'+depa.name+'</option>';
		}else{
			options += '<option value="'+depa.id+'">'+depa.name+'</option>';
		}
		
		if(depa.subDepas !=null){
			var spa = "&nbsp;&nbsp;&nbsp;&nbsp;"
			options += depaSubDeal(depa.subDepas,spa);
		}
	}
	console.log(options);
	$(".depa").append(options)
}

function depaSubDeal(subDepas,spa){
	var options = "";
	for (var i = 0; i < subDepas.length; i++) {
		var depa = subDepas[i];
		if(pid != null && pid == depa.id){
			options += '<option value="'+depa.id+'" selected>'+spa+depa.name+'</option>';
		}else{
			options += '<option value="'+depa.id+'">'+spa+depa.name+'</option>';
		}
		
		if(depa.subDepas !=null){
			options += depaSubDeal(depa.subDepas,spa+"&nbsp;&nbsp;&nbsp;&nbsp;");
		}
	}
	return options;
}

	function commit() {
		webside.common.commit('depaFrom', '/department/edit.html',
				'/department/listUI.html');
	}
</script>