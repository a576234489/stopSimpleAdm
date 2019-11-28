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
<script type="text/javascript"
		src="${pageContext.request.contextPath }/resources/js/bootstrap/bootstrap.min.js"></script>
<link rel="stylesheet"
	href="${pageContext.request.contextPath }/resources/js/editor/themes/default/default.css" />
<script type="text/javascript" charset="UTF-8"
	src="${pageContext.request.contextPath }/resources/js/editor/kindeditor-all.js"></script>
<!-- 表格 -->
<link rel="stylesheet" type="text/css"
	href="${pageContext.request.contextPath}/resources/js/dlshouwen.grid.v1.2.1/dlshouwen.grid.min.css" />
<script type="text/javascript"
	src="${pageContext.request.contextPath}/resources/js/dlshouwen.grid.v1.2.1/dlshouwen.grid.treegrid.js"></script>
<script type="text/javascript"
	src="${pageContext.request.contextPath}/resources/js/dlshouwen.grid.v1.2.1/i18n/zh-cn.js"></script>
<script type="text/javascript"
	src="${pageContext.request.contextPath }/resources/js/customer/truckType/list.js"></script>
<!-- 引入angular -->
<script type="text/javascript"
		src="${pageContext.request.contextPath}/resources/js/angularjs/angular.min.js"></script>
<script type="text/javascript"
		src="${pageContext.request.contextPath}/resources/js/customer/base/base.js"></script>
<script type="text/javascript"
		src="${pageContext.request.contextPath}/resources/js/customer/truckType/truckTypeService.js"></script>
<script type="text/javascript"
		src="${pageContext.request.contextPath}/resources/js/customer/truckType/truckTypeController.js"></script>
<style>
	#truckTypeModalLast .modal-content .form-group {
		margin-top: 10px
	}
	#truckTypeModalLast .modal-content .form-control{
		margin-left: 18px;
	}
.modelStyle {
	z-index: 1111;
}
textarea {
width:700px;height:300px;
}

.modal-content {
	margin-top: 10px;
}

.proClass {
	margin-left: 20px !important;
	font-size: 16px;
}

.warningClass {
	margin-left: 20px !important;
	font-size: 16px;
}
</style>
<div class="page-header">

</div>

<div class="row" style="margin-top: 5px;" ng-app="fire" ng-controller="truckTypeController" >
	<div class="col-xs-12 widget-container-col ui-sortable"
		style="min-height: 127px;">
		<!-- #section:custom/widget-box.options.transparent -->
		<div class="widget-box transparent ui-sortable-handle"
			style="opacity: 1; z-index: 0;">
			<form class="form-inline" role="form">
				<button type="button" class="btn btn-primary btn-sm" ng-click="truckTypeModalAdd()">添加分类</button>
			</form>
			<ol class="breadcrumb">	                        	
              <li>
              		<a href="javascript:void(0)" onclick="olClick(0,1,'顶级分类列表')">顶级分类列表</a> 
              </li>
              <li>
             		<a id="navigation2" onclick="" href="javascript:void(0)" ></a>
              </li>
              <li>
              		<a id="navigation3" onclick="" href="javascript:void(0)"></a>
              </li>
               <li>
              		<a id="navigation4" onclick="" href="javascript:void(0)"></a>
              </li>
	        </ol>


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
	<div class="modal fade modal-bg" tabindex="1" role="dialog" id="truckTypeModalLast">
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
					<div class='tab-container'>
						<div class="tab-content">
							<div class="active">
								<div class="form-group">
									<label class="control-label col-sm-3 no-padding-right"
										   for="name">车辆型号</label>
									<div class="col-sm-6">
										<div class="clearfix">
											<input  class="form-control" type="text" name="name"
													value="${truckTypeEntity.name}" />
										</div>
									</div>
								</div>
								<div class="form-group">
									<label class="control-label col-sm-3 no-padding-right"
										   for="name">生产厂家</label>
									<div class="col-sm-6">
										<div class="clearfix">
											<input  class="form-control" type="text" name="truckParamEntity.manufacturer"
													value="${truckTypeEntity.truckParamEntity.manufacturer}" />
										</div>
									</div>
								</div>
								<div class="form-group">
									<label class="control-label col-sm-3 no-padding-right"
										   for="accountName">车辆照片</label>
									<div class="col-sm-6">
										<div class="clearfix">
											<div class="img-box">
												<input type="file"  style="display:none" id="uploadfile" accept="image/*"/>
												<div id="image" style="width: 100px;height: 40px;line-height: 40px;text-align: center;background-color: #00b7ee;margin-left: 18px">选择图片</div>
											</div>
										</div>
									</div>

								</div>
								<div style="margin-left: 38px;margin-top: 12px">
									<img id="image1" style="width: 200px;height: 200px">
								</div>
							</div>
							<div>
								<textarea name="truckParamEntity.param" id="param" style="width: 400px;height: 400px;"  class="displays">${truckTypeEntity.truckParamEntity.param }</textarea>
							</div>
							<div>
								<textarea name="truckParamEntity.maintenanceManual" style="width: 400px;height: 400px;" id="maintenanceManual" class="displays">${truckTypeEntity.truckParamEntity.maintenanceManual }</textarea>
							</div>
							<div>
								<textarea name="truckParamEntity.operationInstructions" style="width: 400px;height: 400px;"  id="operationInstructions" class="displays">${truckTypeEntity.truckParamEntity.operationInstructions }</textarea>
							</div>
						</div>
						<ul class='tab-nav'>
							<li class='active'>基本数据</li>
							<li >车辆属性</li>
							<li >保养手册</li>
							<li >操作说明</li>
						</ul>
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
	<!-- 模态框（Modal） -->
	<div class="modal fade modal-bg" tabindex="1" role="dialog" id="truckTypeModal">
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
							   for="accountName">{{name}}</label>
						<div class="col-sm-7">
							<div class="clearfix">
								<input class="form-control" type="text" id="content" ng-model="position.name"/>
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
<style type="text/css">
	.tab-container * {
		margin: 0;
		padding: 0;
		-webkit-box-sizing: border-box;
		-moz-box-sizing: border-box;
		box-sizing: border-box;
	}

	.tab-container {
		margin: 0 auto;
		width: 500px;
		overflow: hidden;
	}
	.tab-nav {
		height: 400px;
		width: 100px;
		border-right: 1px solid lightgrey;
	}
	.tab-nav li {
		border-right: 1px solid lightgrey;
		height: 35px;
		line-height: 35px;
		list-style: none;
		text-align: center;
		width: 100px;
	}

	.tab-nav li.active {
		border: 1px solid lightgrey;
		border-right: 1px solid #fff;
	}

	.tab-content {
		float: right;
		width: 400px;
		overflow: hidden;
		height: 400px;
		border: 1px solid lightgrey;
		border-left: none;
	}

	.tab-content > div {
		display: none;
	}
	.tab-content > .active {
		display: block;
	}
</style>

<script type="text/javascript">
    var tabNav = document.querySelector('.tab-nav');
    var content = document.querySelector('.tab-content');
    tabNav.onclick = function(e) {

        var target = e.target || e.srcElement;
        var index = 0;
        if(target.tagName.toLocaleLowerCase() === 'li') {
            this.querySelectorAll('li').forEach(function(i, j) {
                if(target === i) {
                    index = j;
                    i.className = 'active';
                } else {
                    i.className = '';
                }
            });
            var children = content.children;
            for(var i=0; i<children.length; i++) {
                children[i].className = index === i ? 'active' : ''
            }
        }
    }

</script>
<script type="text/javascript">

$(function() {
	console.log(parentId);
	var olDateTrucks = "${olDateTruck}";
	console.log(olDateTrucks);
	if(olDateTrucks != null && olDateTrucks != ""){
		var temp =[];
		olDateTrucks = olDateTrucks.split(',olClick');
		var olDateTruck = "";
		for (var i = 0; i < olDateTrucks.length; i++) {
			var olDateTruck = olDateTrucks[i];
			if(i == 0){
				olDateTruck = olDateTruck.replace("olClick(","").replace(")","").replace("'","").replace("'","");
				temp = olDateTruck.split(",");
				olClickRefresh(temp[0],temp[1],temp[2]);
				if(i == olDateTrucks.length-1){
				    console.log(temp[1]);
					grade == temp[1];
					parentId = temp[0];
					console.log(temp[0])
				}
			}else{
				olDateTruck = olDateTruck.replace("(","").replace(")","").replace("'","").replace("'","");
				temp = olDateTruck.split(",");
				olClickRefresh(temp[0],temp[1],temp[2]);
				if(i == olDateTrucks.length-1){
                    console.log(temp[1]);
					grade == temp[1];
					parentId = temp[0];
					console.log(temp[0])
				}
			}
		}
		
	}
    console.log(grade);
    if(null != $("#orderByColumn").val() && '' != $("#orderByColumn").val())
    {
        grid.sortParameter.columnId = $("#orderByColumn").val();
        grid.sortParameter.sortType = $("#orderByType").val();
    }
    grid.parameters = new Object();
    grid.parameters['parentId'] = parentId;
    grid.load(nowPage);
    $("#btnSearch").click(customSearch);
   
    //注册回车键事件
    document.onkeypress = function(e){
    var ev = document.all ? window.event : e;
        if(ev.keyCode==13) {
            customSearch();
        }
    };
    $("#image").click(function(){
        $("#uploadfile").click();
    })
    $("#uploadfile").change(function(){

        var files=$(this)[0].files[0];    //获取文件信息
        if(files)
        {
            var reader=new FileReader();  //调用FileReader
            reader.onload=function(evt){   //读取操作完成时触发。
                $("#image1").attr('src',evt.target.result)  //将img标签的src绑定为DataURL
            }
            reader.readAsDataURL(files); //将文件读取为 DataURL(base64)
        }
        else{
            alert("上传失败");
        }
    })
    param = KindEditor.create('textarea[name="truckParamEntity.param"]', {
        resizeType : 0,
        allowPreviewEmoticons : false,
        allowImageUpload : true,
        minWidth: 400,
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
    maintenanceManual = KindEditor.create('textarea[name="truckParamEntity.maintenanceManual"]', {
        resizeType : 0,
        allowPreviewEmoticons : false,
        allowImageUpload : true,
        minWidth: 400,
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
    operationInstructions = KindEditor.create('textarea[name="truckParamEntity.operationInstructions"]', {
        resizeType : 0,
        allowPreviewEmoticons : false,
        allowImageUpload : true,
        minWidth: 400,
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

});
</script>
