<%@ page language="java" contentType="text/html; charset=EUC-KR" pageEncoding="EUC-KR"%>
<!DOCTYPE html>
<html ng-app="Main"> <head> <%@include file="/inc/crs_sst.inc"%>
<script type="text/javascript">
	var t = $("#loading");
	var s = null;
	
	/* Angular Initialize */
	var app = angular.module("Main", [])
		.config(function($compileProvider){ $compileProvider.aHrefSanitizationWhitelist(/^\s*(https?|ftp|mailto|file|javascript):/);})
		.controller("MainCtrl", function($scope, $http, $httpParamSerializer, $document) {
/* Grid TotalCount Initialize */
		$scope.totalcnt = 0;

/* Button Valiable*/
		$scope.btn = {add:1, insert:0, update:0, delete:0};
		
/* Make Complete Messgae */	   
		$http({
	    	method	: "POST",
	    	url		: "<cx:wc/>/template.do?method=getComplete",
	    }).success(function(data, status, headers, config) {
	    	if( data ) {/* 정상 데이터 */
	    		//alert(data.resultMap.message);
	    	}else {/* 통신한 URL에서 데이터가 넘어오지 않았을 때 처리 */ console.log("No-Data"); }
	    }).error(function(data, status, headers, config) { /* 서버와의 연결이 정상적이지 않을 때 처리 */ console.log(status); });
	
/* Code Combo List */		
	    $http({
	    	method	: "POST",
	    	url		: "<cx:wc/>/template.do?method=getCodeList"
	    }).success(function(data, status, headers, config) {
	    	if( data ) {/* 정상 데이터 */
	    		$scope.codeLoop = data.resultList;
	    		if($scope.codeLoop.length > 0){
	    			//$scope.cond = {code : $scope.codeLoop[0].code};
	    		}
	    	}else {/* 통신한 URL에서 데이터가 넘어오지 않았을 때 처리 */ console.log("No-Data"); }
	    }).error(function(data, status, headers, config) { /* 서버와의 연결이 정상적이지 않을 때 처리 */ console.log(status); });

/* Grid  */
		$scope.retrive = function() {
			s = new Spinner(g_opts).spin(t);// Loading Bar Start
		    $http({
		    	method: "POST", //방식
		    	url: "<cx:wc/>/template.do?method=getGridList", /* 통신할 URL */
		    	data: $httpParamSerializer( $scope.cond ),//<<정상
		    	headers: { 'Content-Type': 'application/x-www-form-urlencoded; charset=euc-kr' }
		    }).success(function(data, status, headers, config) {
		    	if( data ) {/* 정상 데이터 */
		    		$scope.gridLoop = data.resultList;
		    		if($scope.gridLoop.length > 0)
		    			$scope.totalcnt = $scope.gridLoop.length;
		    		else
		    			$scope.totalcnt = 0;
		    	}else {/* 통신한 URL에서 데이터가 넘어오지 않았을 때 처리 */ console.log("No-Data"); }
		    }).error(function(data, status, headers, config) { /* 서버와의 연결이 정상적이지 않을 때 처리 */ console.log(status); });
		    //s.stop(); // Loading Bar End
		};	 
		
/* Grid Detail */
		$scope.goDetail = function($event, index){
			$event.stopPropagation();
			
			$scope.btn = {add:1, insert:0, update:1, delete:1};
			
			$scope.input = {code : $scope.gridLoop[index].code,name : $scope.gridLoop[index].name};
		};
		
/* Add  */
		$scope.btnAdd = function() {
			$scope.btn = {add:1, insert:1, update:0, delete:0};
			
			$scope.input = {code:"", name:""};
		};	
		
/* Insert  */
		$scope.btnInsert = function() {
			if($.trim($scope.input.code) == ""){alert("코드를 입력하세요"); return false;}
			if($.trim($scope.input.name) == ""){alert("코드명를 입력하세요"); return false;}
			
		    $http({
		    	method: "POST", //방식
		    	url: "<cx:wc/>/template.do?method=insertCode", /* 통신할 URL */
		    	data: $httpParamSerializer( $scope.input ),//<<정상
		    	headers: { 'Content-Type': 'application/x-www-form-urlencoded; charset=euc-kr' }
		    }).success(function(data, status, headers, config) {
		    	if( data ) {/* 정상 데이터 */
		    		$scope.btn = {add:1, insert:0, update:0, delete:0};
		    		$scope.input = {code : "",name : ""};
		    		$scope.retrive();
		    	}else {/* 통신한 URL에서 데이터가 넘어오지 않았을 때 처리 */ console.log("No-Data"); }
		    }).error(function(data, status, headers, config) { /* 서버와의 연결이 정상적이지 않을 때 처리 */ console.log(status); });
		};
		
/* Update  */
		$scope.btnUpdate = function() {
			if($.trim($scope.input.name) == ""){alert("코드명를 입력하세요"); return false;}
			
		    $http({
		    	method: "POST", //방식
		    	url: "<cx:wc/>/template.do?method=updateCode", /* 통신할 URL */
		    	data: $httpParamSerializer( $scope.input ),//<<정상
		    	headers: { 'Content-Type': 'application/x-www-form-urlencoded; charset=euc-kr' }
		    }).success(function(data, status, headers, config) {
		    	if( data ) {/* 정상 데이터 */
		    		$scope.retrive();
		    	}else {/* 통신한 URL에서 데이터가 넘어오지 않았을 때 처리 */ console.log("No-Data"); }
		    }).error(function(data, status, headers, config) { /* 서버와의 연결이 정상적이지 않을 때 처리 */ console.log(status); });
		};	

/* Delete  */
		$scope.btnDelete = function() {
		    $http({
		    	method: "POST", //방식
		    	url: "<cx:wc/>/template.do?method=deleteCode", /* 통신할 URL */
		    	data: $httpParamSerializer( $scope.input ),//<<정상
		    	headers: { 'Content-Type': 'application/x-www-form-urlencoded; charset=euc-kr' }
		    }).success(function(data, status, headers, config) {
		    	if( data ) {/* 정상 데이터 */
		    		$scope.btn = {add:1, insert:0, update:0, delete:0};
		    		$scope.input = {code : "",name : ""};
		    		$scope.retrive();
		    	}else {/* 통신한 URL에서 데이터가 넘어오지 않았을 때 처리 */ console.log("No-Data"); }
		    }).error(function(data, status, headers, config) { /* 서버와의 연결이 정상적이지 않을 때 처리 */ console.log(status); });
		};	
	});

</script>
</head>
<body ng-csp="no-inline-style;no-unsafe-eval">
	<form novalidate onSubmit="return false;">
		<div id="subMain" ng-controller="MainCtrl">
			<div class="top_title">
				<ul>
					<li class="txt">Condition</li>
				</ul>
			</div>
			<div class="sheet_search">
				<div>
					<table>
						<tbody>
							<tr>
								<td><span>코드</span></td>
								<td>
									<select ng-model="cond.code" ng-options="x.code as x.name for x in codeLoop" style="width:85px;">
										<option value="">전체</option>>
									</select> 
								</td>
								<td><span>코드명</span></td>
								<td><input ng-model="cond.name" type="text" style="width:50px;" class="text"></td>
								<td><button ng-click="retrive()" class="button">조회</button></td>
							</tr>
						</tbody>
					</table>
				</div>
			</div>
			<div class="sheet_title">
				<ul>
					<li class="txt"><span>[ 총 {{totalcnt}}건 ]</span></li>
					<li class="btn">
						<button id="exlDown" 			ng-click="exlDown()"	class="button">엑셀다운로드</button>
						<button id="exlUpload" 			ng-click="exlUpLoad()"	class="button">엑셀업로드</button>
					</li>
				</ul>
			</div>
			<table cellspacing="0" class="simple" id="grid">
				<thead>
					<tr>
						<th>번호</th>
						<th>코드</th>
						<th>코드명</th>
					</tr>
				</thead>
				<tbody>
					<tr ng-show="totalcnt < 1" class="empty" style="height:300px;"><td colspan="14">&nbsp;No Data</td></tr>
					<tr ng-repeat="x in gridLoop" ng-class-odd="'odd'" ng-class-even="'even'" ng-click="goDetail($event, $index)">
						<td style="text-align:center;">{{$index}}</td>
						<td style="text-align:center">{{x.code}}</td>
						<td style="text-align:center">{{x.name}}</td>
				  	</tr>
				</tbody>
			</table>
			<span class="pagelinks"><strong>1</strong></span>
			
			<div class="top_title">
				<ul>
					<li class="txt">Detail</li>
				</ul>
			</div>
			<div class="sheet_title">
				<ul>
					<li class="btn">
						{{btn.add}}
						{{btn.insert}}
						{{btn.update}}
						{{btn.delete}}
						<button ng-disabled="btn.add < 1" 		ng-click="btnAdd()"		class="button">add</button>
						<button ng-disabled="btn.insert < 1"	ng-click="btnInsert()"	class="button">insert</button>
						<button ng-disabled="btn.update < 1"	ng-click="btnUpdate()"	class="button">update</button>
						<button ng-disabled="btn.delete < 1"	ng-click="btnDelete()"	class="button">delete</button>
					</li>
				</ul>
			</div>
			<div>
				<div>
					<table>
						<tbody>
							<tr>
								<td><span>코드</span></td>
								<td><input ng-disabled="btn.insert < 1" ng-model="input.code" type="text" style="width:100px;" class="text"></td>
							</tr>
							<tr>
								<td><span>코드명</span></td>
								<td><input ng-disabled="btn.update < 1" ng-model="input.name" type="text" style="width:100px;" class="text"></td>
							</tr>
						</tbody>
					</table>
				</div>
			</div>
			
			
		</div>
	</form>
</body>
</html>
