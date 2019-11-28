// 定义模块:
var app = angular.module("stopSimple",[]);
app.config(['$locationProvider', function($locationProvider) {
	 
	$locationProvider.html5Mode(true);
	 
}]);	 
app.directive('wdatePicker',function(){
    return{
        restrict:"A",
        link:function(scope,element,attr){
            element.bind('click',function(){
                window.WdatePicker({
                    onpicked: function(){element.change()},
                    oncleared:function(){element.change()}
                })
            });
        }
    }
});
