app.controller('searchController',function($scope,$location,searchService){
	
	//定义搜索对象的结构
	$scope.searchMap={'keywords':'','category':'','brand':'','spec':{},'price':'','pageNo':1,'pageSize':40,'sort':'','sortField':''}
	
	//搜索
	$scope.search=function(){
		$scope.searchMap.pageNo= parseInt($scope.searchMap.pageNo);//转换为数字
		searchService.search($scope.searchMap).success(
			function(response){
				$scope.resultMap=response;
				$scope.searchMap.pageNo=1;
				buildPageLable();//构建分页
			}
		);		
	}
	
	buildPageLable=function() {
		//构建分页标签
		$scope.pageLable=[];
		var firstPage = 1;//开始页码
		var lastPage = $scope.resultMap.totalPages;//截止页码
		$scope.firstDot=true;//开始有点
		$scope.lastDot=true;//后面有点
		
		if($scope.resultMap.totalPages>5){	//如果页码数量大于5
			if($scope.resultMap.totalPages> 5){  //如果总页数大于5页,显示部分页码		
				if($scope.searchMap.pageNo<=3){//如果当前页小于等于3
					lastPage=5; //前5页
					$scope.firstDot=false;//前面没点
				}else if( $scope.searchMap.pageNo>=lastPage-2  ){//如果当前页大于等于最大页码-2
					firstPage= $scope.resultMap.totalPages-4;		 //后5页
					$scope.lastDot=false;//后面没点
				}else{ //显示当前页为中心的5页
					firstPage=$scope.searchMap.pageNo-2;
					lastPage=$scope.searchMap.pageNo+2;			
				}
			}
		} else {
			$scope.firstDot=false;//前面无点
			$scope.lastDot=false;//后边无点
		}
		for(var i=1;i<=lastPage;i++) {
			$scope.pageLable.push(i);
		}
	}
	
	//添加搜索项
	$scope.addSearchItem=function(key,value){
		if(key=='category' || key=='brand' || key=='price'){//如果点击的是分类或者是品牌
			$scope.searchMap[key]=value;
		}else{
			$scope.searchMap.spec[key]=value;
		}
		$scope.search();//查询
	}
	
	//移除复合搜索条件
	$scope.removeSearchItem=function(key){
		if(key=="category" ||  key=="brand" || key=='price'){//如果是分类或品牌
			$scope.searchMap[key]="";		
		}else{//否则是规格
			delete $scope.searchMap.spec[key];//移除此属性
		}
		$scope.search();//查询
	}
	
	//分页查询
	$scope.queryByPage=function(pageNo){
		if(pageNo<1 || pageNo>$scope.resultMap.totalPages){
			return ;
		}		
		$scope.searchMap.pageNo=pageNo;
		$scope.search();//查询
	}
	
	//判断当前页是否为第一页
	$scope.isTopPage=function(){
		if($scope.searchMap.pageNo==1){
			return true;
		}else{
			return false;
		}		
	}
	
	//判断当前页是否为最后一页
	$scope.isEndPage=function(){
		if($scope.searchMap.pageNo==$scope.resultMap.totalPages){
			return true;
		}else{
			return false;
		}	
	}
	
	//排序查询
	$scope.sortSearch=function(sortField,sort){
		$scope.searchMap.sortField=sortField;
		$scope.searchMap.sort=sort;
		
		$scope.search();//查询
	}
	
	//判断关键字是否是品牌
	$scope.keywordsIsBrand=function(){
		for(var i=0;i< $scope.resultMap.brandList.length;i++){			
			if( $scope.searchMap.keywords.indexOf( $scope.resultMap.brandList[i].text )>=0  ){
				return true;				
			}			
		}
		return false;
	}
	
	//加载关键字
	$scope.loadkeywords=function(){
		$scope.searchMap.keywords= $location.search()['keywords'];
		$scope.search();//查询
	}
});