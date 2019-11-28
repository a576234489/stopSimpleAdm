/** layuiAdmin.std-v1.0.0 LPPL License By http://www.layui.com/admin/ */
;
var packingLotElement = document.querySelector("[ng-controller=packingLotController]");
var scope = angular.element(packingLotElement).scope(); 
layui.define(["table", "form"],
function(t) {
	console.log(t);
    var e = layui.$,
    i = layui.table,
    n = layui.form;
    i.render({
        elem: "#LAY-app-packinglot-list",
        url: layui.setter.baseUrl + "parkingLot/list",
        cols: [[{
            type: "checkbox",
            fixed: "left",
        },
        {
            type: "numbers",
            title: "序号",
        },
        {
        	field: "parkingLotNumber",
            title: "编号",
            align: "center",
            width: 160,
        },
        {
        	field: "name",
            title: "名称",
            align: "center",
            width: 130,
        },
        {
        	field: "address",
            title: "地址",
            align: "center",
            width: 130,
        },
        {
        	field: "appointPrice",
            title: "预约价格",
            align: "center",
            width: 130,
        },
        {
        	field: "price",
            title: "停车价格",
            align: "center",
            width: 130,
        },
        {
        	field: "openTime",
            title: "营业时间",
            align: "center",
            width: 130,
        },
        {
        	field: "endTime",
            title: "结业时间",
            align: "center",
            width: 130,
        },
        {
        	field: "isChargingPile",
            title: "是否有充电桩",
            align: "center",
            width: 130,
            templet: function(d){
            	if(d.isChargingPile == 1){
            		return "有";
            	}else{
            		return "无";
            	}
            }
        },
        {
        	field: "isLongrent",
            title: "是否支持长租办理",
            align: "center",
            width: 150,
            templet: function(d){
            	if(d.isLongrent == 1){
            		return "支持";
            	}else{
            		return "不支持";
            	}
            }
        },
        {
        	field: "totalChargingPileNumber",
            title: "总充电桩数量",
            align: "center",
            width: 130,
        },
        {
        	field: "totalParkNumber",
            title: "总车位数量",
            align: "center",
            width: 130,
        },
        {
        	field: "parkingLotTypes",
            title: "类型",
            align: "center",
            width: 300,
            templet: function(d){
            	var parkingLotType = ""
            	d.parkingLotTypes.forEach(function(value,i){
            		parkingLotType+=value.type + ",";
            	})
            	parkingLotType = parkingLotType.substring(0,parkingLotType.length-1)
                return parkingLotType;
              }
        },
        {
        	field: "paymentWayEntitys",
            title: "支付方式",
            align: "center",
            width: 300,
            templet: function(d){
            	var paymentWayEntity = ""
            	d.paymentWayEntitys.forEach(function(value,i){
            		paymentWayEntity+=value.paymentName + ",";
            	})
            	paymentWayEntity = paymentWayEntity.substring(0,paymentWayEntity.length-1)
                return paymentWayEntity;
              }
        },
        {
        	field: "province",
            title: "省",
            align: "center",
            width: 130,
        },
        {
        	field: "city",
            title: "市",
            align: "center",
            width: 130,
        },
        {
        	field: "area",
            title: "区",
            align: "center",
            width: 130,
        },
        {
        	field: "remark",
            title: "备注",
            align: "center",
            width: 130,
        },
        {
        	field: "createTime",
            title: "创建时间",
            align: "center",
            width: 230,
        },
        {
            title: "操作",
            align: "center",
            width: 150,
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
                content: "../../view/app/packinglot/listform.html?id=" + e.id,
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
                    	entity.area = entity.area.replace("? string:","").replace(" ?","");
                    	entity.city = entity.city.replace("? string:","").replace(" ?","");
                    	entity.province = entity.provice;
                    	var imgs = [];
                    	var child = $(temp_i).find("iframe")[0].contentWindow;//通过该对象可以获取iframe中的变量，调用iframe中的方法
                    	console.log(child.formdata);
                    	for(let data of child.formdata.entries()){
                    		var a = data[1].indexOf("http");
                    		imgs.push(data[1]);
                		}
                    	entity.imgs = imgs;
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
    t("packingLot", {})
});