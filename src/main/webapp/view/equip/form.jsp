<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<c:set var="ctx" value="${pageContext.request.contextPath }" />
<link rel="stylesheet"
	href="${ctx }/resources/js/editor/themes/default/default.css" />
<script type="text/javascript" charset="UTF-8"
	src="${ctx }/resources/js/editor/kindeditor-all.js"></script>
<script type="text/javascript"
	src="${ctx }/resources/js/jquery/jquery-2.1.4.min.js"></script>
<script type="text/javascript"
	src="${ctx }/resources/js/jquery/jquery.form.min.js"></script>
<script type="text/javascript"
	src="${ctx }/resources/js/bootstrap/bootstrap.min.js"></script>
<!-- layer-->	
<link type="text/css" rel="stylesheet" href="${ctx }/resources/js/layer/theme/default/layer.css"/>
<script type="text/javascript" src="${ctx }/resources/js/layer/layer.js"></script> 
<!-- jstree-->	
<link type="text/css" rel="stylesheet" href="${ctx }/resources/js/jstree/themes/default/style.css"/>
<script type="text/javascript" src="${ctx }/resources/js/jstree/jstree.min.js"></script>   
<script type="text/javascript" src="${ctx }/resources/js/jstree/jstree.checkbox.js"></script> 
<script type="text/javascript" src="${ctx }/resources/js/underscore/underscore-min.js"></script>  
<!-- 引入angular -->
<script type="text/javascript" 
	src="${pageContext.request.contextPath}/resources/js/angularjs/angular.min.js"></script>
<script type="text/javascript" 
	src="${pageContext.request.contextPath}/resources/js/customer/base/base.js"></script>
<script type="text/javascript" 
	src="${pageContext.request.contextPath}/resources/js/customer/equip/equipService.js"></script>
<script type="text/javascript" 
	src="${pageContext.request.contextPath}/resources/js/customer/equip/equipController.js"></script>
<style>
.params {
	display: block;
	width: 1005px;
}
#btn-scroll-up{
	background-color: #F4F5F3;
}
.modal-backdrop {
	z-index: -1024;
}

.modelStyle {
	z-index: 1111;
	height: 1000px;
	overfolw: auto;
}
a{font-size:12px;color:blue}
a:hover{color:red}
.modal-open{overflow:visible !important;}
</style>
<h1>
		<c:if test="${tag==0}">
		装备添加
		</c:if>
		<c:if test="${tag==1}">
		装备修改
		</c:if>
</h1>
<div class="row" style="margin-top: 5px;"  ng-app="fire" ng-controller="equipController" ng-init="initTree();editInit()">
	<div class="col-xs-12">
		<form id="equipFrom" name="equipFrom" class="form-horizontal"
			role="form" method="post" enctype="multipart/form-data">
			<c:if test="${!empty equipEntity}">
				<input type="hidden" id="pageNum" name="pageNum"
					value="${page.pageNum }">
				<input type="hidden" id="pageSize" name="pageSize"
					value="${page.pageSize }">
				<input type="hidden" id="orderByColumn" name="orderByColumn"
					value="${page.orderByColumn }">
				<input type="hidden" id="orderByType" name="orderByType"
					value="${page.orderByType }">
				<input type="hidden" name="id"  value="${equipEntity.id }">	
				<input type="hidden" name="equipParamEntity.id"  value="${equipEntity.equipParamEntity.id }">	
			</c:if>	
			<div class="form-group">
				<label class="control-label col-sm-1 no-padding-right"
					for="name">装备数量</label>
				<div class="col-sm-10">
					<div class="clearfix">
						<input  class="form-control" type="text" ng-model="entity.equipTypeEntity.equipEntity.quantity"
							 />
					</div>
				</div>
			</div>
			<div class="form-group">
				<c:choose>
				<c:when test="${tag == 0}">
					<a style="font-size:14px;" href="#" class="control-label col-sm-1 no-padding-right" ng-click = "modelShow()">选择型号</a>
				</c:when>
				<c:otherwise>
					<div class="control-label col-sm-1 no-padding-right" style="font-size:14px">
						选择型号
					</div>
				</c:otherwise>
				</c:choose>
				
				<div class="col-sm-10">
					<div class="clearfix">
						<div class="clearfix equipT" style="font-size:15px;margin-top: 7px">
						
						</div>
					</div>
				</div>
			</div>
			<div id = "basicData" style="display:none;">
				<div class="form-group">
					<label class="control-label col-sm-1 no-padding-right"
						for="name">生产企业</label>
					<div class="col-sm-10">
						<div class="clearfix">
							<input  class="form-control manufacturer" type="text" 
							value="" disabled="disabled"/>
						</div>
					</div>
				</div>
				<div class="form-group">
					<label class="control-label col-sm-1 no-padding-right"
						for="name">保养周期</label>
					<div class="col-sm-10">
						<div class="clearfix">
							<input  class="form-control maintenanceCycle" type="text" 
							value="" disabled="disabled"/>
						</div>
					</div>
				</div>
				<div class="form-group">
					<label class="control-label col-sm-1 no-padding-right"
						for="name">装备照片</label>
					<div class="col-sm-10">
						<div class="clearfix">
							<img id ="modelImg" height="250" width="300" />
						</div>
					</div>
				</div>
				<div class="form-group">
					<label class="control-label col-sm-1 no-padding-right"
						for="name">装备属性</label>
					<div class="col-sm-10">
						<div class="clearfix param params">
							
						</div>
					</div>
				</div>
				<div class="form-group">
					<label class="control-label col-sm-1 no-padding-right"
						for="name">保养手册</label>
					<div class="col-sm-10">
						<div class="clearfix maintenanceManual params">
							
						</div>
					</div>
				</div>
				<div class="form-group">
					<label class="control-label col-sm-1 no-padding-right"
						for="name">操作说明</label>
					<div class="col-sm-10">
						<div class="clearfix operationInstructions params">
							
						</div>
					</div>
				</div>
			</div>			
			
		</form>
		<div class="hr hr-dotted"></div>
	</div>
	<div class="center">
	<button id="btnAdd" type="button" ng-click="add()"
		class="btn btn-success btn-sm">
		<i class="fa fa-user-plus"></i>&nbsp; 保存	
	</button>
	<button id="btn" type="button"
		onclick="webside.common.loadPage('/equip/listUI.html?page=${page.pageNum }&rows=${page.pageSize }&sidx=${page.orderByColumn }&sord=${page.orderByType }')"
		class="btn btn-info btn-sm">
		<i class="fa fa-undo"></i>&nbsp;返回
	</button>
</div>
</div>
<div class="modal fade modelStyle" tabindex="1" role="dialog"
	id="equipModal">
	<div class="modal-dialog" role="document">
		<div class="modal-content">
			<div class="modal-header">
				<button type="button" class="close" data-dismiss="modal"
					aria-label="Close">
					<span aria-hidden="true">&times;</span>
				</button>
				<h4 class="modal-title">装备分类列表</h4>
			</div>

			<div class="modal-body  form-horizontal">
				<div class="form-group">
					<!-- 资源树 -->
				   <div class="form-group">
				      <label class="control-label col-sm-1 no-padding-right" for="description">资源</label>
				      <div class="col-sm-10">
				         <div id="tree"></div>
				      </div>
		   		   </div>
				</div>
			</div>
		</div>
		<!-- /.modal-content -->
	</div>
	<!-- /.modal-dialog -->
</div>

<script>
var tag = ${tag};
console.log(tag);
var equipTypeEntity = '${equipTypeEntity}';
var equipTypeEntity2 = equipTypeEntity.replace(/\\/g,"\\'").replace(/\s+/g,"");
var equipTypeInfo = eval('(' + equipTypeEntity2 + ')');
</script>