/** layuiAdmin.std-v1.0.0 LPPL License By http://www.layui.com/admin/ */
;
var plateformAccountElement = document.querySelector("[ng-controller=presentCarController]");
var scope = angular.element(plateformAccountElement).scope(); 
layui.define(["table", "form"],
function(t) {
	console.log(t);
    var e = layui.$,
    i = layui.table,
    n = layui.form;
    i.render({
        elem: "#LAY-app-role-list",
        url: layui.setter.baseUrl + "message/findPresentCar",
        cols: [[{
            type: "checkbox",
            fixed: "left",
        },
        {
            type: "numbers",
            title: "序号",
        },{
        	field: "carParkName",
            title: "车场名称",
            align: "center",
            width: 150,
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
        	field: "parkingLotTypes",
            title: "车场类型",
            align: "center",
            width: 230,
            templet: function(d){
            	var parkingLotTypes_ = "";
            	d.parkingLotTypes.forEach(function(value,i){
            		parkingLotTypes_ += value.type + ",";
            	})
            	return parkingLotTypes_.substr(0,parkingLotTypes_.length-1);
            }
        },{
        	field: "admissionTime",
            title: "入场时间",
            align: "center",
            width: 230,
        },{
        	field: "stopDuration",
            title: "停车时长",
            align: "center",
            width: 150,
            templet: function(d){
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
            	return str;
            }
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
        page: !0,
        limit: 10,
        limits: [10, 15, 20, 25, 30],
        text: "对不起，加载出现异常！"
    }),
    t("presentCar", {})
});
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