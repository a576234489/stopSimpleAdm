/** layuiAdmin.std-v1.0.0 LPPL License By http://www.layui.com/admin/ */
;
layui.define(["table", "form"],
function(t) {
	console.log(1111);
    var e = layui.$,
    i = layui.table,
    n = layui.form;
    i.init('LAY-app-day-report-list', {
      url: layui.setter.baseUrl + "report/findReport?dayType="+1,
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
    });
    t("dayReport", {})
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