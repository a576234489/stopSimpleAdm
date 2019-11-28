<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="shiro" uri="http://shiro.apache.org/tags"%>
<script>
	var status = '${status}';
    var userId = '${userId}';
</script>
<script type="text/javascript"
	src="${pageContext.request.contextPath }/resources/js/jquery/jquery-2.1.4.min.js"></script>
<script type="text/javascript"
	src="${pageContext.request.contextPath }/resources/js/jquery/jquery.form.min.js"></script>
<script type="text/javascript"
	src="${pageContext.request.contextPath }/resources/js/bootstrap/bootstrap.min.js"></script>
<link rel="stylesheet" type="text/css"
	href="${pageContext.request.contextPath}/resources/js/dlshouwen.grid.v1.2.1/dlshouwen.grid.min.css" />
<script type="text/javascript"
	src="${pageContext.request.contextPath}/resources/js/dlshouwen.grid.v1.2.1/dlshouwen.grid.treegrid.js"></script>
<script type="text/javascript"
	src="${pageContext.request.contextPath}/resources/js/dlshouwen.grid.v1.2.1/i18n/zh-cn.js"></script>
<script type="text/javascript"
	src="${pageContext.request.contextPath }/resources/js/customer/staff/list.js"></script>
<!-- 引入angular -->
<script type="text/javascript" 
	src="${pageContext.request.contextPath}/resources/js/angularjs/angular.min.js"></script>
<script type="text/javascript" 
	src="${pageContext.request.contextPath}/resources/js/customer/base/base.js"></script>
<script type="text/javascript" 
	src="${pageContext.request.contextPath}/resources/js/customer/staff/staffService.js"></script>
<script type="text/javascript" 
	src="${pageContext.request.contextPath}/resources/js/customer/staff/staffController.js"></script>
<script type="text/javascript"
		src="${pageContext.request.contextPath}/resources/js/WdtePicker/WdatePicker.js"></script>
<style>
.modal-backdrop {
	z-index: -1024;
}
.modal-dialog{
	width: 800px;
}
</style>
	
<div class="row" style="margin-top: 5px;"  ng-app="fire" ng-controller="staffController" ng-init="isConditionShow();findPosionListInit()">
	<div class="col-xs-12 widget-container-col ui-sortable"
		style="min-height: 127px;">
		<!-- #section:custom/widget-box.options.transparent -->
		<div class="widget-box transparent ui-sortable-handle"
			style="opacity: 1; z-index: 0;">
			<div class="widget-header">
				<form class="form-inline" role="form">
					<button type="button" class="btn btn-primary btn-sm" ng-click="positionModalAdd()">添加职工</button>
					<button type="button" class="btn btn-primary btn-sm" ng-click="changeConditonStatus()">高级筛选</button>
					<input id="searchKey" type="text" class="input form-control" placeholder="职位名称..." ng-model="name"> 
					<button id="btnSearch" ng-click="search()" class="btn btn-primary btn-sm" type="button"><i class="fa fa-search" ></i> 搜索</button>
				</form>
				<div style="margin:5px" id= "sxcondition">
				  	筛选条件:
					<select ng-model="condition" ng-options="condition.id as condition.name for condition in conditionList">
					</select>
					条件值:
					<c:choose>
						<c:when test="${userId == 4}">
							<select ng-model="brigade" ng-options="brigade.id as brigade.fireBrigadeName for brigade in brigadeList">
							</select>
						</c:when>
						<c:otherwise>
							<select ng-model="position" ng-options="position.id as position.name for position in positionList">
							</select>
						</c:otherwise>
					</c:choose>
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
	<!-- 模态框（Modal） -->
	<div class="modal fade" tabindex="1" role="dialog" id="staffModal">
		<div class="modal-dialog" role="document">
			<div class="modal-content">
				<div class="modal-header">
					<button type="button" class="close" data-dismiss="modal" 
							aria-hidden="true">×
					</button>
					<h4 class="modal-title" id="myModalLabel">
						{{modelTitle}}
					</h4>
				</div>
				<div class="modal-body form-horizontal">
					<div class="form-group">
						<label class="control-label col-sm-2 no-padding-right"
							   for="accountName">用户名</label>
						<div class="col-sm-3">
							<div class="clearfix">
								<input class="form-control" type="text" id="content" ng-model="staffEntity.userName"/>
							</div>
						</div>
						<label class="control-label col-sm-2 no-padding-right"
							   for="accountName">平台备注</label>
						<div class="col-sm-3">
							<div class="clearfix">
								<input class="form-control" type="text" id="content" ng-model="staffEntity.remark"/>
							</div>
						</div>
					</div>
					<div class="form-group">
						<label class="control-label col-sm-2 no-padding-right"
							   for="accountName">姓名</label>
						<div class="col-sm-3">
							<div class="clearfix">
								<input class="form-control" type="text" id="content" ng-model="staffEntity.starffinfo.name"/>
							</div>
						</div>
						<label class="control-label col-sm-2 no-padding-right"
							   for="accountName">名族</label>
						<div class="col-sm-3">
							<div class="clearfix">
								<input class="form-control" type="text" id="content" ng-model="staffEntity.starffinfo.nation"/>
							</div>
						</div>
					</div>
					<div class="form-group">
						<label class="control-label col-sm-2 no-padding-right"
							   for="accountName">婚姻状况</label>
						<div class="col-sm-3">
							<div class="clearfix">
								<select ng-model="staffEntity.starffinfo.maritalStatus" ng-options="maritalStatus.id as maritalStatus.name for maritalStatus in maritalStatusList">
								</select>
							</div>
						</div>
						<label class="control-label col-sm-2 no-padding-right"
							   for="accountName">身份证</label>
						<div class="col-sm-3">
							<div class="clearfix">
								<input class="form-control" type="text" id="content" ng-model="staffEntity.starffinfo.idCard"/>
							</div>
						</div>
					</div>
					<div class="form-group">
						<label class="control-label col-sm-2 no-padding-right"
							   for="accountName">性别</label>
						<div class="col-sm-3">
							<div class="clearfix">
								<select ng-model="staffEntity.starffinfo.sex" ng-options="sex.id as sex.name for sex in sexList">
								</select>
							</div>
						</div>
						<label class="control-label col-sm-2 no-padding-right"
							   for="accountName">出身年月</label>
						<div class="col-sm-3">
							<div class="clearfix">
								<input type="text" ng-model="staffEntity.starffinfo.dateBirth" wdate-picker/>
							</div>
						</div>
					</div>
					<div class="form-group">
						<label class="control-label col-sm-2 no-padding-right"
							   for="accountName">年龄</label>
						<div class="col-sm-3">
							<div class="clearfix">
								<input class="form-control" type="text" id="content" ng-model="staffEntity.starffinfo.age"/>
							</div>
						</div>
						<label class="control-label col-sm-2 no-padding-right"
							   for="accountName">籍贯</label>
						<div class="col-sm-3">
							<div class="clearfix">
								<input class="form-control" type="text" id="content" ng-model="staffEntity.starffinfo.nativePlace"/>
							</div>
						</div>
					</div>
					<div class="form-group">
						<label class="control-label col-sm-2 no-padding-right"
							   for="accountName">身高</label>
						<div class="col-sm-3">
							<div class="clearfix">
								<input class="form-control" type="text" id="content" ng-model="staffEntity.starffinfo.stature"/>
							</div>
						</div>
						<label class="control-label col-sm-2 no-padding-right"
							   for="accountName">体重</label>
						<div class="col-sm-3">
							<div class="clearfix">
								<input class="form-control" type="text" id="content" ng-model="staffEntity.starffinfo.weight"/>
							</div>
						</div>
					</div>
					<div class="form-group">
						<label class="control-label col-sm-2 no-padding-right"
							   for="accountName">身份证地址</label>
						<div class="col-sm-3">
							<div class="clearfix">
								<input class="form-control" type="text" id="content" ng-model="staffEntity.starffinfo.idCardAddress"/>
							</div>
						</div>
						<label class="control-label col-sm-2 no-padding-right"
							   for="accountName">电话</label>
						<div class="col-sm-3">
							<div class="clearfix">
								<input class="form-control" type="text" id="content" ng-model="staffEntity.starffinfo.tel"/>
							</div>
						</div>
					</div>
					<div class="form-group">
						<label class="control-label col-sm-2 no-padding-right"
							   for="accountName">办公室电话</label>
						<div class="col-sm-3">
							<div class="clearfix">
								<input class="form-control" type="text" id="content" ng-model="staffEntity.starffinfo.officeTel"/>
							</div>
						</div>
						<label class="control-label col-sm-2 no-padding-right"
							   for="accountName">所属职位</label>
						<div class="col-sm-3">
							<div class="clearfix">
												<select ng-model="staffEntity.starffinfo.positionId" ng-options="position.id as position.name for position in positionListInit">
								</select>
							</div>
						</div>
					</div>
					<div class="form-group">
						<label class="control-label col-sm-2 no-padding-right"
							   for="accountName">员工住址</label>
						<div class="col-sm-3">
							<div class="clearfix">
								<input class="form-control" type="text" id="content" ng-model="staffEntity.starffinfo.address"/>
							</div>
						</div>
					</div>
				</div>
				<div class="modal-footer">
					<button type="button" class="btn btn-default" 
							data-dismiss="modal">关闭
					</button>
					<button type="button" class="btn btn-primary" ng-click="save()">
						保存
					</button>
				</div>
			</div><!-- /.modal-content -->
		</div><!-- /.modal-dialog -->
	</div><!-- /.modal -->

</div>

