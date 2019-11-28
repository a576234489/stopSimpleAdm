<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="shiro" uri="http://shiro.apache.org/tags"%>
<script>
	var status = '${status}';
</script>
<script type="text/javascript"
	src="${pageContext.request.contextPath }/resources/js/jquery/jquery-2.1.4.min.js"></script>
<script type="text/javascript"
	src="${pageContext.request.contextPath }/resources/js/jquery/jquery.form.min.js"></script>
<link rel="stylesheet"
	  href="${pageContext.request.contextPath }/resources/js/editor/themes/default/default.css" />
<script type="text/javascript" charset="UTF-8"
		src="${pageContext.request.contextPath }/resources/js/editor/kindeditor-all.js"></script>
<%--<link rel="stylesheet"--%>
	  <%--href="https://fe.faisys.com/ueditor_1_1/css/ueditor.min.css?v=201809071801" />--%>
<%--<link rel="stylesheet"--%>
	  <%--href="https://2.ss.faisys.com/css/comm/ueditor/ueditor_1_0/ueditor_site.min.css?v=201905221254"/>--%>
<%--<script type="text/javascript" charset="UTF-8"--%>
		<%--src="https://1.ss.faisys.com/js/comm/ueditor/ueditor_1_0/ueditor_site.min.js?v=201906051525"></script>--%>
<%--<script type="text/javascript" charset="UTF-8"--%>
		<%--src="https://fe.faisys.com/ueditor_1_1/js/ueditor_1_1.min.js?v=201905291151"></script>--%>

<script type="text/javascript"
		src="${pageContext.request.contextPath }/resources/js/bootstrap/bootstrap.min.js"></script>
<link rel="stylesheet" type="text/css"
	href="${pageContext.request.contextPath}/resources/js/dlshouwen.grid.v1.2.1/dlshouwen.grid.min.css" />
<script type="text/javascript"
	src="${pageContext.request.contextPath}/resources/js/dlshouwen.grid.v1.2.1/dlshouwen.grid.treegrid.js"></script>
<script type="text/javascript"
	src="${pageContext.request.contextPath}/resources/js/dlshouwen.grid.v1.2.1/i18n/zh-cn.js"></script>
<script type="text/javascript"
	src="${pageContext.request.contextPath }/resources/js/customer/position/list.js"></script>
<!-- 引入angular -->
<script type="text/javascript" 
	src="${pageContext.request.contextPath}/resources/js/angularjs/angular.min.js"></script>
<script type="text/javascript" 
	src="${pageContext.request.contextPath}/resources/js/customer/base/base.js"></script>
<script type="text/javascript" 
	src="${pageContext.request.contextPath}/resources/js/customer/position/positionService.js"></script>
<script type="text/javascript" 
	src="${pageContext.request.contextPath}/resources/js/customer/position/positionController.js"></script>
<style>
.modal-backdrop {
	z-index: -1024;
}
.modal-bg {
	background-color: rgba(0, 0, 0, .4);
}
.clearfix:after {
	content: '';
	display: block;
	clear: both;
}
</style>

<div class="row" style="margin-top: 5px;"  ng-app="fire" ng-controller="positionController" ng-init="isConditionShow()">
	<div class="col-xs-12 widget-container-col ui-sortable"
		style="min-height: 127px;">
		<!-- #section:custom/widget-box.options.transparent -->
		<div class="widget-box transparent ui-sortable-handle"
			style="opacity: 1; z-index: 0;">
			<div class="widget-header">
				<form class="form-inline" role="form">
					<button type="button" class="btn btn-primary btn-sm" ng-click="positionModalAdd()">添加职位</button>
					<button type="button" class="btn btn-primary btn-sm" ng-click="changeConditonStatus()">高级筛选</button>
					<input id="searchKey" type="text" class="input form-control" placeholder="职位名称..." ng-model="name"> 
					<button id="btnSearch" ng-click="search()" class="btn btn-primary btn-sm" type="button"><i class="fa fa-search" ></i> 搜索</button>
				</form>
				<div style="margin:5px" id= "sxcondition">
				  	筛选条件:
					<select ng-model="condition" ng-options="condition.id as condition.name for condition in conditionList">
					</select>
					条件值:
					<select id="addstaff" ng-model="unitLevel" ng-options="unitLevel.unitLevel as unitLevel.unitLevelName for unitLevel in unitLevelList">
					</select>
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
	<div class="modal fade modal-bg" tabindex="1" role="dialog" id="positionModal">
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
						<label class="control-label col-sm-3 no-padding-right"
							for="accountName">单位级别</label>
						<div class="col-sm-7">
							<div class="clearfix">
								<select ng-model="position.unitLevel" ng-options="unitLevel.unitLevel as unitLevel.unitLevelName for unitLevel in unitLevelList2">
								</select>
							</div>
						</div>
					</div>
					<div class="form-group">
						<label class="control-label col-sm-3 no-padding-right"
							for="accountName">上级名称</label>
						<div class="col-sm-7">
							<div class="clearfix">
								<select ng-model="position.parentId" ng-options="position.id as position.name for position in positionList">
								</select>
							</div>
						</div>
					</div>
					<div class="form-group">
						<label class="control-label col-sm-3 no-padding-right"
							for="accountName">职位名称</label>
						<div class="col-sm-7">
							<div class="clearfix">
								<input class="form-control" type="text" id="content" ng-model="position.name"/>
							</div>
						</div>
					</div>
					<div class="form-group">
						<label class="control-label col-sm-3 no-padding-right"
							   for="accountName">车辆属性</label>
						<div class="col-sm-7 box">
							<div id="editor"></div>
							<textarea name="truckParamEntity.param" id="param" style="width: 345px;" class="displays">${truckTypeEntity.truckParamEntity.param }</textarea>
							<%-- <input type="hidden" name="newImg" id="newImg"
                                value="${discover.newImg }" /> --%>
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
<script>
    /*layui.use('element', function(){
        var $ = layui.jquery
            ,element = layui.element; //Tab的切换功能，切换事件监听等，需要依赖element模块

        //触发事件
        var active = {
            tabAdd: function(){
                //新增一个Tab项
                element.tabAdd('demo', {
                    title: '新选项'+ (Math.random()*1000|0) //用于演示
                    ,content: '内容'+ (Math.random()*1000|0)
                    ,id: new Date().getTime() //实际使用一般是规定好的id，这里以时间戳模拟下
                })
            }
            ,tabDelete: function(othis){
                //删除指定Tab项
                element.tabDelete('demo', '44'); //删除：“商品管理”


                othis.addClass('layui-btn-disabled');
            }
            ,tabChange: function(){
                //切换到指定Tab项
                element.tabChange('demo', '22'); //切换到：用户管理
            }
        };

    });*/
    param = KindEditor.create('textarea[name="truckParamEntity.param"]', {
        resizeType : 0,
        allowPreviewEmoticons : false,
        allowImageUpload : true,
        minWidth: 345,
        //allowFileManager : true, //允许对上传图片进行管理
        items : [ 'source', '|', 'undo', 'redo', '|', 'preview', 'print',
            'template', 'code', 'cut', 'copy', 'paste', 'plainpaste',
            'wordpaste', '|', 'justifyleft', 'justifycenter',
            'justifyright', 'justifyfull', 'insertorderedlist',
            'insertunorderedlist', 'indent', 'outdent', 'subscript',
            'superscript', 'clearhtml', 'quickformat', 'selectall',
            '|', 'fullscreen', '/', 'formatblock', 'fontname',
            'fontsize', '|', 'forecolor', 'hilitecolor', 'bold',
            'italic', 'underline', 'strikethrough', 'lineheight',
            'removeformat', '|', 'image', 'media', 'insertfile',
            'table', 'hr', 'emoticons', 'baidumap', 'pagebreak',
            'anchor', 'link', 'unlink', '|', 'about' ],
        allowFileManager : true,
        uploadJson : 'discover/upload.html',
        filePostName : 'branndUrl',
        //	fileManagerJson : '../resources/js/editor/file_manager_json.jsp',
        afterBlur : function() {
            this.sync();
        }
    });
</script>
