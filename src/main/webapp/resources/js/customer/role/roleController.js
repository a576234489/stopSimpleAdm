//控制层 
app.controller('roleController', function($scope, $controller,
		$location, roleService) {
	//初始化权限树
	$scope.initTree = function() {
		var roleId = $location.search()['roleId'];
		console.log(roleId)
		$('#tree').jstree({
                        "core" : {
                            'data' : {
                                "url" : "../../../role/resourceTree?roleId="+roleId,
                                "dataType" : "json"
                            },
                            "themes" : {
                                "responsive" : true
                            },
                            "multiple" : true,
                            "animation" : 200,
                            "dblclick_toggle" : true,
                            "expand_selected_onload" : true
                        },
                        "checkbox" : {
                            "keep_selected_style" : true,
                            "three_state" : false,
                            "cascade" : "up"
                        },
                        "plugins" : ["checkbox"]
         });
		
	}
	var ids = [];
	$scope.grantPermission = function(roleId,layer) {
		var tree = $('#tree').jstree();
		//获取所有选中节点id
		var selectedIds = tree.get_checked();
		//获取所有选中节点
		var selected = tree.get_checked(true);
		//遍历节点，获取选中节点的所有父节点
        for (var i = 0; i < selected.length; i++) {
        	getParentsNode(tree, selected[i]);
        }
        selectedIds = selectedIds.concat(ids);
        selectedIds = new Set(selectedIds); 
        selectedIds = Array.from(selectedIds);
        selectedIds = selectedIds.join(',');
        roleService.grantPermission(roleId,selectedIds).success(function(response) {
			if(response.success){
				layer.msg(response.message, {icon: 1});
				layer.close();
			}else{
				layer.msg(response.message, {icon: 2});
			}
		});
	}
	function getParentsNode(treeObj, nodeObj){
		var parentId = treeObj.get_parent(nodeObj);
        if (parentId != "#") {
            ids.push(parentId);
            getParentsNode(treeObj, treeObj.get_node(parentId));
        }
	}
	// 更具id查询角色
	$scope.findOne = function() {
		var id = $location.search()['id'];
		//当前页为修改页面
		if(id != undefined){
			// 更具id查询平台账号
			roleService.findById(id).success(function(response) {
				$scope.entity = response;
			});
		//当前页为添加页面
		}else{
			$("#accountNameId").attr("disabled",false);
			$scope.isShow = true;
			$scope.entity = {role:{id:0}};
		}
	}
	//角色编辑
	$scope.edit = function(entity) {
		console.log(entity);
		if(entity.id != null){
			//更新
			roleService.update(entity).success(function(response) {
				if(response.success){
					layer.alert(response.message, {icon: 1},function () {
                        window.location.reload();
                    });
				}else{
					layer.msg(response.message, {icon: 2});
				}
			});
		}else{
			//添加
			roleService.add(entity).success(function(response) {
				if(response.success){
					layer.alert(response.message, {icon: 1},function () {
                        window.location.reload();
                    });
				}else{
					layer.msg(response.message, {icon: 2});
				}
			});
							
		}
	}
	$scope.del = function(ids) {
		roleService.del(ids).success(function(response) {
			if(response.success){
				layer.alert(response.message, {icon: 1},function () {
                    window.location.reload();
                });
			}else{
				layer.msg(response.message, {icon: 2});
			}
		});
	}
});
