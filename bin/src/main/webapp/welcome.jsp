<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<script type="text/javascript">
	var img = {
		viewFactor : 0.15,
		duration : 1000,
		distance : "64px",
		scale : 0.8,
		reset : true,
		origin : 'right'
	};
	var config = {
		viewFactor : 0.15,
		duration : 800,
		distance : "5px",
		scale : 0.8,
		reset : true,
		origin : 'bottom'
	};
	scrollreveal.reveal("p", config);
	scrollreveal.reveal('img[alt=""]', img);
</script>
<div data-scroll-reveal class="page-header">
	<h1>
		欢迎页 <small> <i class="ace-icon fa fa-angle-double-right"></i>
			系统说明
		</small>
	</h1>
</div>
<div data-scroll-reveal class="row">
	<p>欢迎使用消防管理平台</p>


</div>
<script type="text/javascript">
	//save old document.write
	var oldDocumentWrite = document.write;
	// change document.write temporary
	document.write = function(node) {
		$("#gitosc").append(node)
	}
	//get script
	$.getScript("//git.oschina.net/wjggwm/webside/widget_preview", function() {
		// replace the temp document.write with the original version
		setTimeout(function() {
			document.write = oldDocumentWrite;
		}, 100);
	});
</script>
<style>
<!--
覆盖osc自带样式 -->.ui.segment.osc_git_box {
	padding: 0px !important;
	border: 1px solid #c1dce3;
}

.pro_name a {
	color: #4183c4;
}

.osc_git_title {
	background-color: #fafafa;
}

.osc_git_box {
	background-color: #e4e4ed;
}

.osc_git_box {
	border-color: #c1dce3;
}

.osc_git_info {
	color: #030303;
}

.osc_git_main a {
	color: #0a0a0a;
}
</style>