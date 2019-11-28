/** layuiAdmin.std-v1.0.0 LPPL License By http://www.layui.com/admin/ */
;
var plateformAccountElement = document.querySelector("[ng-controller=plateformAccountController]");
var scope = angular.element(plateformAccountElement).scope(); 
layui.define(["table", "form"],
function(t) {
	console.log(t);
    var e = layui.$,
    i = layui.table,
    n = layui.form;
    i.render({
        elem: "#LAY-app-platform-account-list",
        url: layui.setter.baseUrl + "plateformAccount/list",
        cols: [[{
            type: "checkbox",
            fixed: "left",
        },
        {
            field: "accountName",
            title: "账号名",
            width: 170,
            sort: !0
        },
        {
            field: "userName",
            title: "姓名",
            width: 100,
        },
        {
            field: "role",
            title: "所属角色",
            width: 100,
            templet: function(d){
                return d.role.name;
              }
        },
        {
            field: "locked",
            title: "是否锁定",
            width: 100,
            templet: "#buttonIsLocked",
            align: "center"
        },
        {
            field: "creatorName",
            title: "创建者",
            width: 170,
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
        page: !0,
        limit: 10,
        limits: [10, 15, 20, 25, 30],
        text: "对不起，加载出现异常！"
    }),
    i.on("tool(LAY-app-platform-account-list)",
    function(t) {
        var e = t.data;
        if("del" === t.event){
        	if(e.id === 4){
        		layer.msg('超级管理员不可以编辑', {icon: 2});
        	}else{
	        	layer.confirm("确定删除此账号？",
	        	        function(e2) {
	        				var ids = [];
	        				ids[0] = e.id;
	        				scope.del(ids);
	        	        })
        	}
        }else if("edit" === t.event){
        	var temp = e.id;
        	if(e.id === 4){
        		layer.msg('超级管理员不可以编辑', {icon: 2});
        	}else{
        		layer.open({
                    type: 2,
                    title: "平台账号编辑",
                    content: "../../view/app/plateformAccount/listform.html?id=" + e.id,
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
                        	entity.role = {};
                        	entity.role.id = entity.roleId;
                        	scope.edit(entity);
                        	//n.render(),
                            layer.close(e)
                        })
                        //layui.table.reload('plateformTable',{});
                        a.trigger("click")
                    }
                })
        	}
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
    i.render({
        elem: "#LAY-app-content-tags",
        url: layui.setter.base + "json/content/tags.js",
        cols: [[{
            type: "numbers",
            fixed: "left"
        },
        {
            field: "id",
            width: 100,
            title: "ID",
            sort: !0
        },
        {
            field: "tags",
            title: "分类名",
            minWidth: 100
        },
        {
            title: "操作",
            width: 150,
            align: "center",
            fixed: "right",
            toolbar: "#layuiadmin-app-cont-tagsbar"
        }]],
        text: "对不起，加载出现异常！"
    }),
    i.on("tool(LAY-app-content-tags)",
    function(t) {
        var i = t.data;
        if ("del" === t.event) layer.confirm("确定删除此分类？",
        function(e) {
            layer.close(e)
        });
        else if ("edit" === t.event) {
            e(t.tr);
            layer.open({
                type: 2,
                title: "编辑分类",
                content: "../../../views/app/content/tagsform.html?id=" + i.id,
                area: ["450px", "200px"],
                btn: ["确定", "取消"],
                yes: function(e, i) {
                    var n = i.find("iframe").contents().find("#layuiadmin-app-form-tags"),
                    l = n.find('input[name="tags"]').val();
                    l.replace(/\s/g, "") && (t.update({
                        tags: l
                    }), layer.close(e))
                },
                success: function(t, e) {
                    var n = t.find("iframe").contents().find("#layuiadmin-app-form-tags").click();
                    n.find('input[name="tags"]').val(i.tags)
                }
            })
        }
    }),
    i.render({
        elem: "#LAY-app-content-comm",
        url: layui.setter.base + "json/content/comment.js",
        cols: [[{
            type: "checkbox",
            fixed: "left"
        },
        {
            field: "id",
            width: 100,
            title: "ID",
            sort: !0
        },
        {
            field: "reviewers",
            title: "评论者",
            minWidth: 100
        },
        {
            field: "content",
            title: "评论内容",
            minWidth: 100
        },
        {
            field: "commtime",
            title: "评论时间",
            minWidth: 100,
            sort: !0
        },
        {
            title: "操作",
            width: 150,
            align: "center",
            fixed: "right",
            toolbar: "#table-content-com"
        }]],
        page: !0,
        limit: 10,
        limits: [10, 15, 20, 25, 30],
        text: "对不起，加载出现异常！"
    }),
    i.on("tool(LAY-app-content-comm)",
    function(t) {
        t.data;
        "del" === t.event ? layer.confirm("确定删除此条评论？",
        function(e) {
            t.del(),
            layer.close(e)
        }) : "edit" === t.event && layer.open({
            type: 2,
            title: "编辑评论",
            content: "../../../views/app/content/contform.html",
            area: ["450px", "300px"],
            btn: ["确定", "取消"],
            yes: function(t, e) {
                var n = window["layui-layer-iframe" + t],
                l = "layuiadmin-app-comm-submit",
                a = e.find("iframe").contents().find("#" + l);
                n.layui.form.on("submit(" + l + ")",
                function(e) {
                    e.field;
                    i.reload("LAY-app-content-comm"),
                    layer.close(t)
                }),
                a.trigger("click")
            },
            success: function(t, e) {}
        })
    }),
    t("platformAccount", {})
});