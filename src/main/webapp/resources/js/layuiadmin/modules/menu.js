/** layuiAdmin.std-v1.0.0 LPPL License By http://www.layui.com/admin/ */
;
var plateformAccountElement = document.querySelector("[ng-controller=menuController]");
var scope = angular.element(plateformAccountElement).scope(); 
layui.define(
function(t) {
	var $ = layui.jquery, tree = layui.tree;
	var treeGrid = layui.treeGrid; //很重要
    treeGrid.render({
    	id : 'LAY-app-menu-list',
        elem: "#LAY-app-menu-list",
        url: layui.setter.baseUrl + "menu/list",
        treeId : 'id',//树形id字段名称
		treeUpId : 'parentId',//树形父id字段名称
		treeShowName : 'name',//以树形式显示的字段
		page : false,
        cols: [[
        {
			field : 'id',
			title : ' ',
			type: 'checkbox',
			unresize : true
		},
        {
            field: "name",
            title: "菜单名",
            width: 150,
        },
        {
            field: "sourceUrl",
            title: "链接",
            width: 250,
        },
        {
            field: "createTime",
            title: "创建时间",
            sort: !0
        },
        {
            field: "updateTime",
            title: "更新时间",
            sort: !0
        },
        {
            title: "操作",
            align: "center",
            width: 250,
            fixed: "right",
            toolbar: "#table-content-list"
        }]],
    }),
    treeGrid.on("tool(LAY-app-menu-list)",
    function(t) {
        var e = t.data;
        if("del" === t.event){
        	layer.confirm("确定删除此账号？",
        	        function(e2) {
        				var ids = [];
        				ids[0] = e.id;
        				scope.del(ids);
        	        })
        }else if("edit" === t.event){
        	var temp = e.id;
    		layer.open({
                type: 2,
                title: "编辑菜单",
                content: "../../view/app/menu/listform.html?id=" + e.id,
                maxmin: !0,
                area: ["623px", "530px"],
                btn: ["确定", "取消"],
                yes: function(e, i) {
                    var l = window["layui-layer-iframe" + e],
                    a = i.find("iframe").contents().find("#layuiadmin-app-form-edit");
                    l.layui.form.on("submit(layuiadmin-app-form-edit)",
                    function(i) {
                    	var entity = i.field;
                    	entity.id = temp;
                    	scope.edit(entity,treeGrid);
                    	//n.render(),
                    	console.log(entity);
                        layer.close(e)
                    })
                    //layui.table.reload('plateformTable',{});
                    a.trigger("click")
                }
            })
        }else if("lock" === t.event){
        	if(e.id === 4){
        		layer.msg('超级管理员不可以锁定', {icon: 2});
        	}else{
	        	layer.confirm("确定锁定该账户吗？",
	        	        function(e2) {
	        			   scope.lock(e.id);
	        	        })
        	}
        }else if("unlock" === t.event){
        	layer.confirm("确定解锁该账户吗？",
        	        function(e2) {
        		       scope.unlock(e.id);
        	        })
        }else if("reset" === t.event){
        	layer.confirm("确定重置密码吗？",
        	        function(e2) {
        		       scope.reset(e.id);
        	        })
        }
    }),
    t("menu", {})
});