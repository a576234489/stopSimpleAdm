<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="shiro" uri="http://shiro.apache.org/tags"%>
<script>
	var userId = '${userId}';
	var truckId = '${truckId}';
	var equipTypeId = 0;
	var userName = '${userName}';
	console.log(userId + "===>" + truckId)
</script>
<style>

.modal-backdrop {
	z-index: -1024;
}

.modelStyle {
	z-index: 10;
	height: 1000px;
	overfolw: auto;
}
.modelStyle2 {
	z-index: 100;
	height: 1000px;
	overfolw: auto;
}
.modal-dialog {
	width: 1000px;
}
.modal-dialog2 {
	width: 600px;
	margin-left: 400px;
}

#btnBack {
	float:right
}	

</style>
<script type="text/javascript"
	src="${pageContext.request.contextPath }/resources/js/jquery/jquery-2.1.4.min.js"></script>
<script type="text/javascript"
	src="${pageContext.request.contextPath }/resources/js/bootstrap/bootstrap.min.js"></script>
<link rel="stylesheet" type="text/css"
	href="${pageContext.request.contextPath}/resources/js/dlshouwen.grid.v1.2.1/dlshouwen.grid.min.css" />
<script type="text/javascript"
	src="${pageContext.request.contextPath}/resources/js/dlshouwen.grid.v1.2.1/dlshouwen.grid.treegrid.js"></script>
<script type="text/javascript"
	src="${pageContext.request.contextPath}/resources/js/dlshouwen.grid.v1.2.1/i18n/zh-cn.js"></script>
<script type="text/javascript"
	src="${pageContext.request.contextPath }/resources/js/jquery/jquery.form.min.js"></script>
<script type="text/javascript"
	src="${pageContext.request.contextPath }/resources/js/customer/truck/equip.js"></script>
<!-- layer-->	
<link type="text/css" rel="stylesheet" href="${pageContext.request.contextPath}/resources/js/layer/theme/default/layer.css"/>
<script type="text/javascript" src="${pageContext.request.contextPath}/resources/js/layer/layer.js"></script> 
<!-- jstree-->	
<link type="text/css" rel="stylesheet" href="${pageContext.request.contextPath}/resources/js/jstree/themes/default/style.css"/>
<script type="text/javascript" src="${pageContext.request.contextPath}/resources/js/jstree/jstree.min.js"></script>   
<script type="text/javascript" src="${pageContext.request.contextPath}/resources/js/jstree/jstree.checkbox.js"></script> 
<script type="text/javascript" src="${pageContext.request.contextPath}/resources/js/underscore/underscore-min.js"></script>  
<!-- 引入angular -->
<script type="text/javascript" 
	src="${pageContext.request.contextPath}/resources/js/angularjs/angular.min.js"></script>
<script type="text/javascript" 
	src="${pageContext.request.contextPath}/resources/js/customer/base/base.js"></script>
<script type="text/javascript" 
	src="${pageContext.request.contextPath}/resources/js/customer/truck/userEquipService.js"></script>
<script type="text/javascript" 
	src="${pageContext.request.contextPath}/resources/js/customer/equip/equipService.js"></script>
<script type="text/javascript" 
	src="${pageContext.request.contextPath}/resources/js/customer/truck/userEquipController.js"></script>

<div class="page-header">
	<button id="btnEdit" type="button"
		data-toggle="modal" data-target="#equipModal"
		class="btn btn-success btn-sm">
		<i class="fa fa-pencil-square-o"></i>&nbsp;添加
	</button>
	<button id="btnEdit" type="button"
		onclick="editEquip()"
		class="btn btn-success btn-sm">
		<i class="fa fa-pencil-square-o"></i>&nbsp;编辑
	</button>
	<button id="btnDel" type="button"
		onclick="del()"
		class="btn btn-danger btn-sm">
		<i class="fa fa-trash-o"></i>&nbsp;删除
	</button>
	<button id="btnBack" type="button"
		onclick="webside.common.loadPage('/truckTechn/listUI.html?page=${page.pageNum }&rows=${page.pageSize }&sidx=${page.orderByColumn }&sord=${page.orderByType }&truckId=${truckId }')"
		class="btn btn-success btn-sm">
		<i class="fa fa-pencil-square-o"></i>&nbsp;返回
	</button>
</div>
<div class="input-group">
	<input id="searchKey" type="text" class="input form-control"
		placeholder="消防车分类名称..."> <span class="input-group-btn">
		<button id="btnSearch" class="btn btn-primary btn-sm" type="button">
			<i class="fa fa-search"></i> 搜索
		</button>
	</span>
</div>
<div class="row" style="margin-top: 5px;">
	<div class="col-xs-12 widget-container-col ui-sortable"
		style="min-height: 127px;">
		<!-- #section:custom/widget-box.options.transparent -->
		<div class="widget-box transparent ui-sortable-handle"
			style="opacity: 1; z-index: 0;">
			<div class="widget-header">
				<h4 class="widget-title lighter">随车人员:${userName}</h4>
				<div class="widget-toolbar no-border">
					<a href="#" data-action="fullscreen" class="orange2"> <i
						class="ace-icon fa fa-arrows-alt"></i>
					</a> <a href="#" data-action="collapse" class="green"> <i
						class="ace-icon fa fa-chevron-up"></i>
					</a>
				</div>
			</div>

			<div class="widget-body" style="display: block;">
				<div class="widget-main padding-6 no-padding-left no-padding-right">
					<input id="pageNum" type="hidden" value="${page.pageNum }">
					<input id="pageSize" type="hidden" value="${page.pageSize }">
					<input id="orderByColumn" type="hidden"
						value="${page.orderByColumn }"> <input id="orderByType"
						type="hidden" value="${page.orderByType }">
					<div id="dtGridContainer" class="dlshouwen-grid-container"></div>
					<div id="dtGridToolBarContainer"
						class="dlshouwen-grid-toolbar-container"></div>
				</div>
			</div>
		</div>
	</div>
</div>
<div ng-app="fire" ng-controller="userEquipController" ng-init="initTree()">
	<div class="modal fade modelStyle" tabindex="1" role="dialog"
		id="equipModal" >
		<div class="modal-dialog" role="document">
			<div class="modal-content">
				<div class="modal-header">
					<button type="button" class="close" data-dismiss="modal"
						aria-label="Close">
						<span aria-hidden="true">&times;</span>
					</button>
					<h4 class="modal-title">随车人员添加装备</h4>
				</div>
				<div class="modal-body  form-horizontal">
					<div class="form-group">
						<label class="control-label col-sm-3 no-padding-right"
							for="accountName">装备类型(必选)</label>
						<div class="col-sm-7">
							<div class="clearfix">
								<select class="form-control" id="type">
									<option value="0">随车装备</option>
									<option value="1">个人防护</option>
								</select>
							</div>
						</div>
					</div>
	
					<div class="form-group">
						<div class="control-label col-sm-3 no-padding-right">
							<a style="font-size:14px;" href="#" class="control-label" ng-click = "modelShow()">选择装备</a>
						</div>
						<div class="col-sm-7">
								<div class="clearfix">
									<div class="clearfix truckT" style="font-size:15px;margin-top: 7px">
									
									</div>
								</div>
						</div>
					</div>
					
					<div class="form-group">
						<label class="control-label col-sm-3 no-padding-right"
							for="accountName">装备数量</label>
						<div class="col-sm-7">
							<div class="clearfix">
								<input class="form-control" type="text" id="quantity" />
							</div>
						</div>
					</div>
				</div>
	
				<div class="modal-footer">
					<button type="button" class="btn btn-default" data-dismiss="modal">关闭</button>
					<!-- <button type="submit" class="btn btn-primary">上传</button> -->
					<button type="button" onclick="subEquip()" class="btn btn-primary">确认</button>
				</div>
	
			</div>
			<!-- /.modal-content -->
		</div>
		<!-- /.modal-dialog -->
		<div class="modal fade modelStyle2" tabindex="2" role="dialog"
			id="equipTypeModal">
				<div class="modal-dialog2" role="document">
					<div class="modal-content">	
						<div class="modal-body  form-horizontal">
							<div class="form-group">
								<!-- 资源树 -->
							   <div class="form-group">
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
	</div>
	
</div>
