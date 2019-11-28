<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="shiro" uri="http://shiro.apache.org/tags"%>
<script>
	var status = '${status}';
	var typeId = ${typeId};
</script>
<script type="text/javascript"
	src="${pageContext.request.contextPath }/resources/js/jquery/jquery-2.1.4.min.js"></script>
<link rel="stylesheet" type="text/css"
	href="${pageContext.request.contextPath}/resources/js/dlshouwen.grid.v1.2.1/dlshouwen.grid.min.css" />
<script type="text/javascript"
	src="${pageContext.request.contextPath}/resources/js/dlshouwen.grid.v1.2.1/dlshouwen.grid.treegrid.js"></script>
<script type="text/javascript"
	src="${pageContext.request.contextPath}/resources/js/dlshouwen.grid.v1.2.1/i18n/zh-cn.js"></script>
<script type="text/javascript"
	src="${pageContext.request.contextPath }/resources/js/jquery/jquery.form.min.js"></script>
<script type="text/javascript"
	src="${pageContext.request.contextPath }/resources/js/customer/truck/list.js"></script>
	<style>
		#btnBack {
			float:right
		}
	</style>

<div class="page-header">

	<button id="btnEdit" type="button"
		onclick="webside.common.addModel('/truck/addUI.html')"
		class="btn btn-success btn-sm">
		<i class="fa fa-pencil-square-o"></i>&nbsp;添加
	</button>

	<button id="btnEdit" type="button"
		onclick="edit()"
		class="btn btn-success btn-sm">
		<i class="fa fa-pencil-square-o"></i>&nbsp;编辑
	</button>

	<button id="btnDel" type="button"
		onclick="del"
		class="btn btn-danger btn-sm">
		<i class="fa fa-trash-o"></i>&nbsp;删除
	</button>
	<button id="btnBack" type="button"
		onclick="webside.common.loadPage('/truck/listUI.html?page=${page.pageNum }&rows=${page.pageSize }&sidx=${page.orderByColumn }&sord=${page.orderByType }')"
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
				<h4 class="widget-title lighter">消防车详情列表</h4>
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
