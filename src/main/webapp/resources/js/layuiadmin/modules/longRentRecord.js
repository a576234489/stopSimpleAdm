/** layuiAdmin.std-v1.0.0 LPPL License By http://www.layui.com/admin/ */
;
layui.define(["table", "form"],
function(t) {
	console.log(t);
    var e = layui.$,
    i = layui.table,
    n = layui.form;
    i.render({
        elem: "#LAY-app-long-rent-card-list",
        url: layui.setter.baseUrl + "message/findUserLongRentRecord",
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
        	field: "carParkName",
            title: "车场名称",
            align: "center",
            width: 170,
            templet: function(d){
            	if(d.id == null){
            		console.log(d);
            		return "合计";
            	}else{
            		return d.carParkName;
            	}
            }
        },{
        	field: "carParkNumber",
            title: "车场编号",
            align: "center",
            width: 170,
        },{
        	field: "nickName",
            title: "姓名",
            align: "center",
            width: 150,
        },{
        	field: "carNumber",
            title: "车牌号码",
            align: "center",
            width: 230,
        },{
        	field: "telphone",
            title: "联系电话",
            align: "center",
            width: 230,
        },{
        	field: "type",
            title: "长租卡类型",
            align: "center",
            width: 150,
            templet: function(d){
            	if(d.type == 1){
            		return "月卡";
            	}else if(d.type == 2){
            		return "季卡";
            	}else if(d.type == 3){
            		return "年卡";
            	}else{
            		return "";
            	}
            }
        },{
        	field: "handlingExpenses",
            title: "消费金额",
            align: "center",
            width: 150,
            templet: function(d){
            	if(d.id != null){
            		return d.handlingExpenses
            	}else{
            		return d.totalHandlingExpenses
            	}
            }
        },{
        	field: "effectiveStartTime",
            title: "有效开始日期",
            align: "center",
            width: 200,
        },{
        	field: "expireTime",
            title: "截止日期",
            align: "center",
            width: 200,
        },{
        	field: "handleTime",
            title: "办理日期",
            align: "center",
            width: 200,
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
    t("longRentRecord", {})
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
