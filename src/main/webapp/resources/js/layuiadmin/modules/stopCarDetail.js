/** layuiAdmin.std-v1.0.0 LPPL License By http://www.layui.com/admin/ */
;
layui.define(["table", "form"],
function(t) {
	console.log(t);
    var e = layui.$,
    i = layui.table,
    n = layui.form;
    i.render({
        elem: "#LAY-app-role-list",
        url: layui.setter.baseUrl + "message/findStopCarDetail",
        cols: [[{
            type: "checkbox",
            fixed: "left",
        },
        {
            type: "numbers",
            title: "序号",
            templet: function(d){
            	console.log(d)
            }
        },
        {
        	field: "orderNumber",
            title: "订单号",
            align: "center",
            width: 150,
            templet: function(d){
            	if(d.id == null){
            		console.log(d);
            		return "合计";
            	}else{
            		return d.orderNumber;
            	}
            }
        },{
        	field: "carParkName",
            title: "车场名称",
            align: "center",
            width: 170,
        },{
        	field: "carParkNumber",
            title: "车场编号",
            align: "center",
            width: 170,
        },{
        	field: "carNumber",
            title: "车牌号",
            align: "center",
            width: 150,
        },{
        	field: "admissionTime",
            title: "入场时间",
            align: "center",
            width: 230,
        },{
        	field: "exitTime",
            title: "支付时间",
            align: "center",
            width: 230,
        },{
        	field: "stopDuration",
            title: "停车时长",
            align: "center",
            width: 150,
            templet: function(d){
            	if(d.id != null){
	            	var stopDuration_ = d.stopDuration.split(',');
	            	var str = ""
	            	stopDuration_.forEach(function(value,i){
	            		if(i == 0){
	            			if(value != 0){
	            				str += value+"天";
	            			}
	            		}else if(i == 1){
	            			if(value != 0){
	            				str += value+"小时";
	            			}
	            		}else if(i == 2){
	            			if(value != 0){
	            				str += value+"分钟";
	            			}
	            		}
	            	})
            	}else{
            		return "";
            	}
            	return str;
            }
        },{
        	field: "",
            title: "停车状态",
            align: "center",
            width: 150,
            templet: function(d){
            	if(d.id != null){
	            	if(d.exitTime != null){
	            		return "已出场"
	            	}else{
	            		return "停车中"
	            	}
            	}else{
            		return "";
            	}
            }
        },{
        	field: "carNumber",
            title: "付款状态",
            align: "center",
            width: 150,
            templet: function(d){
            	if(d.id != null){
	            	if(d.exitTime != null){
	            		return "已付费"
	            	}else{
	            		return "未付费"
	            	}
            	}else{
            		return "";
            	}
            }
        },{
        	field: "totalReceivables",
            title: "总应收",
            align: "center",
            width: 150,
        },{
        	field: "amount",
            title: "总实收",
            align: "center",
            width: 150,
            templet: function(d){
            	if(d.id != null){
            		return d.amount
            	}else{
            		return d.totalAmount
            	}
            }
        },{
        	field: "totalfavourable",
            title: "总优惠",
            align: "center",
            width: 150,
        },{
        	field: "paymentWayName",
            title: "支付方式",
            align: "center",
            width: 150,
        },{
        	field: "longRentType",
            title: "车辆类型",
            align: "center",
            width: 150,
            templet: function(d){
            	if(d.expireTime != null){
	            	var temp = d.expireTime.split(" ");
	            	var expireTime = stringToDate(temp[0],"-")
	            	console.log(expireTime)
	            	if(expireTime.getTime() < new Date().getTime()){
	            		return "临时卡"
	            	}else{
	            		return "长租卡"
	            	}
            	}else{
            		return "临时卡"
            	}
            }
        },
        ]],
        done: function (res, curr, count) {
            //如果是异步请求数据方式，res即为你接口返回的信息。
            //如果是直接赋值的方式，res即为：{data: [], count: 99} data为当前页数据、count为数据总长度
            console.log(res);
            //得到当前页码
            console.log(curr);
            //得到数据总量
            console.log(count);
            var index = 0;
            res.data.forEach(function(value,i){
            	if(value.id == null){
            		console.log("++++");
            		index = value.LAY_TABLE_INDEX;
            		console.log(index);
            	}
            })
            Layui_SetDataTableRowColor("tabl_panel_id1",index,'#FF0000');
          },
        page: !0,
        limit: 10,
        limits: [10, 15, 20, 25, 30],
        text: "对不起，加载出现异常！"
    }),
    t("stopCarDetail", {})
});

//设置layui datatable的某一行的颜色
//TabDivId:tab父div id;RowIndex:行序列号，从0开始；ColorString：颜色字符串，如'#123456'
function Layui_SetDataTableRowColor(TabDivId,RowIndex, ColorString)
{
    try
    {
        var div = document.getElementById(TabDivId);
        if(div != null) //找到对象了
        {
            var table_main = div.getElementsByClassName('layui-table-main');   //通过class获取table_main
            if (table_main != null && table_main.length > 0)
            {
                var table = table_main[0].getElementsByClassName('layui-table');   //通过class获取table
                if (table != null && table.length > 0) {
                    var trs = table[0].querySelectorAll("tr");
                    if (trs != null && trs.length > 0) {
                        trs[RowIndex].style.color = ColorString;
                    }
                }
            }
            
        }
    }
    catch(e)
    {
        console.log(e.message);
    }
}
function stringToDate (dateStr,separator){
    if(!separator){
           separator="-";
    }
    var dateArr = dateStr.split(separator);
    var year = parseInt(dateArr[0]);
    var month;
    //处理月份为04这样的情况                         
    if(dateArr[1].indexOf("0") == 0){
        month = parseInt(dateArr[1].substring(1));
    }else{
         month = parseInt(dateArr[1]);
    }
    var day = parseInt(dateArr[2]);
    var date = new Date(year,month -1,day);
    return date;
}