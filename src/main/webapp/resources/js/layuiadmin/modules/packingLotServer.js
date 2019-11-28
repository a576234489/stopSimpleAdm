/** layuiAdmin.std-v1.0.0 LPPL License By http://www.layui.com/admin/ */
;
var packingLotServerElement = document.querySelector("[ng-controller=packingLotServerController]");
var scope = angular.element(packingLotServerElement).scope(); 
layui.define(["table", "form"],
function(t) {
	console.log(t);
    var e = layui.$,
    i = layui.table,
    n = layui.form;
    i.render({
        elem: "#LAY-app-packinglot-list",
        url: layui.setter.baseUrl + "parkingLotServer/list",
        cols: [[{
            type: "checkbox",
            fixed: "left",
        },
        {
            type: "numbers",
            title: "序号",
        },
        {
        	field: "servName",
            title: "服务名称",
            align: "center",
            width: 150,
        },
        {
        	field: "imgUrl",
            title: "图片",
            align: "center",
            width: 150,
            templet: function(d){
            	return '<div><img src='+d.imgUrl+'></div>'
            }
        },
        {
        	field: "parkingLotName",
            title: "所属停车场",
            align: "center",
        },
        {
        	field: "createTime",
            title: "创建时间",
            align: "center",
            width: 230,
        },
        {
        	field: "updateTime",
            title: "修改时间",
            align: "center",
            width: 230,
        },
        {
            title: "操作",
            align: "center",
            fixed: "right",
            toolbar: "#table-content-list"
        }
        ]],
        page: !0,
        limit: 10,
        limits: [10, 15, 20, 25, 30],
        text: "对不起，加载出现异常！"
    }),
    i.on("tool(LAY-app-packinglot-list)",
    	function(t) {
        var e = t.data;
        var temp = e.id;
        if("del" === t.event){
        	layer.confirm("确定删除此账号？",
        	        function(e2) {
        				var ids = [];
        				ids[0] = e.id;
        				scope.del(ids);
        	        })
        }else if("edit" === t.event){
    		layer.open({
                type: 2,
                title: "停车场编辑",
                content: "../../view/app/packinglotServer/listform.html?id=" + e.id,
                maxmin: !0,
                area: ["670px", "565px"],
                btn: ["确定", "取消"],
                yes: function(e, i) {
                	var temp_i = i;
                    var l = window["layui-layer-iframe" + e],
                    a = i.find("iframe").contents().find("#layuiadmin-app-form-edit");
                    l.layui.form.on("submit(layuiadmin-app-form-edit)",
                    function(i) {
                    	var entity = i.field;
                    	entity.id = temp;
                    	var imgs = [];
                    	var child = $(temp_i).find("iframe")[0].contentWindow;//通过该对象可以获取iframe中的变量，调用iframe中的方法
                    	console.log(child.formdata);
                    	for(let data of child.formdata.entries()){
                    		imgs.push(data[1]);
                		}
                    	entity.imgs = imgs;
                    	console.log(entity)
                    	scope.edit(entity);
                    	layer.close(e)
                    })
                    a.trigger("click")
                }
            })
        }else if("permission" === t.event){
        	layer.open({
                type: 2,
                title: "权限编辑",
                content: "../../view/app/role/permission.html?roleId=" + e.id,
                maxmin: !0,
                area: ["770px", "530px"],
                btn: ["确定", "取消"],
                yes: function(e, i) {
                   var iframeWin = window[i.find('iframe')[0]['name']]; 
                   iframeWin.grantPermission(temp);
                   setTimeout(function(){
                	   layer.close(e);
                   }, 1000);
                }
            })
        }
    }),
    t("packingLotServer", {})
});