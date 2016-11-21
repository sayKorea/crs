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
	    	if( data ) {/* ���� ������ */
	    		//alert(data.resultMap.message);
	    	}else {/* ����� URL���� �����Ͱ� �Ѿ���� �ʾ��� �� ó�� */ console.log("No-Data"); }
	    }).error(function(data, status, headers, config) { /* �������� ������ ���������� ���� �� ó�� */ console.log(status); });
	
/* Code Combo List */		
	    $http({
	    	method	: "POST",
	    	url		: "<cx:wc/>/template.do?method=getCodeList"
	    }).success(function(data, status, headers, config) {
	    	if( data ) {/* ���� ������ */
	    		$scope.codeLoop = data.resultList;
	    		if($scope.codeLoop.length > 0){
	    			//$scope.cond = {code : $scope.codeLoop[0].code};
	    		}
	    	}else {/* ����� URL���� �����Ͱ� �Ѿ���� �ʾ��� �� ó�� */ console.log("No-Data"); }
	    }).error(function(data, status, headers, config) { /* �������� ������ ���������� ���� �� ó�� */ console.log(status); });

/* Grid  */
		$scope.retrive = function() {
			s = new Spinner(g_opts).spin(t);// Loading Bar Start
		    $http({
		    	method: "POST", //���
		    	url: "<cx:wc/>/template.do?method=getGridList", /* ����� URL */
		    	data: $httpParamSerializer( $scope.cond ),//<<����
		    	headers: { 'Content-Type': 'application/x-www-form-urlencoded; charset=euc-kr' }
		    }).success(function(data, status, headers, config) {
		    	if( data ) {/* ���� ������ */
		    		$scope.gridLoop = data.resultList;
		    		if($scope.gridLoop.length > 0)
		    			$scope.totalcnt = $scope.gridLoop.length;
		    		else
		    			$scope.totalcnt = 0;
		    	}else {/* ����� URL���� �����Ͱ� �Ѿ���� �ʾ��� �� ó�� */ console.log("No-Data"); }
		    }).error(function(data, status, headers, config) { /* �������� ������ ���������� ���� �� ó�� */ console.log(status); });
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
			if($.trim($scope.input.code) == ""){alert("�ڵ带 �Է��ϼ���"); return false;}
			if($.trim($scope.input.name) == ""){alert("�ڵ�� �Է��ϼ���"); return false;}
			
		    $http({
		    	method: "POST", //���
		    	url: "<cx:wc/>/template.do?method=insertCode", /* ����� URL */
		    	data: $httpParamSerializer( $scope.input ),//<<����
		    	headers: { 'Content-Type': 'application/x-www-form-urlencoded; charset=euc-kr' }
		    }).success(function(data, status, headers, config) {
		    	if( data ) {/* ���� ������ */
		    		$scope.btn = {add:1, insert:0, update:0, delete:0};
		    		$scope.input = {code : "",name : ""};
		    		$scope.retrive();
		    	}else {/* ����� URL���� �����Ͱ� �Ѿ���� �ʾ��� �� ó�� */ console.log("No-Data"); }
		    }).error(function(data, status, headers, config) { /* �������� ������ ���������� ���� �� ó�� */ console.log(status); });
		};
		
/* Update  */
		$scope.btnUpdate = function() {
			if($.trim($scope.input.name) == ""){alert("�ڵ�� �Է��ϼ���"); return false;}
			
		    $http({
		    	method: "POST", //���
		    	url: "<cx:wc/>/template.do?method=updateCode", /* ����� URL */
		    	data: $httpParamSerializer( $scope.input ),//<<����
		    	headers: { 'Content-Type': 'application/x-www-form-urlencoded; charset=euc-kr' }
		    }).success(function(data, status, headers, config) {
		    	if( data ) {/* ���� ������ */
		    		$scope.retrive();
		    	}else {/* ����� URL���� �����Ͱ� �Ѿ���� �ʾ��� �� ó�� */ console.log("No-Data"); }
		    }).error(function(data, status, headers, config) { /* �������� ������ ���������� ���� �� ó�� */ console.log(status); });
		};	

/* Delete  */
		$scope.btnDelete = function() {
		    $http({
		    	method: "POST", //���
		    	url: "<cx:wc/>/template.do?method=deleteCode", /* ����� URL */
		    	data: $httpParamSerializer( $scope.input ),//<<����
		    	headers: { 'Content-Type': 'application/x-www-form-urlencoded; charset=euc-kr' }
		    }).success(function(data, status, headers, config) {
		    	if( data ) {/* ���� ������ */
		    		$scope.btn = {add:1, insert:0, update:0, delete:0};
		    		$scope.input = {code : "",name : ""};
		    		$scope.retrive();
		    	}else {/* ����� URL���� �����Ͱ� �Ѿ���� �ʾ��� �� ó�� */ console.log("No-Data"); }
		    }).error(function(data, status, headers, config) { /* �������� ������ ���������� ���� �� ó�� */ console.log(status); });
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
								<td><span>�ڵ�</span></td>
								<td>
									<select ng-model="cond.code" ng-options="x.code as x.name for x in codeLoop" style="width:85px;">
										<option value="">��ü</option>>
									</select> 
								</td>
								<td><span>�ڵ��</span></td>
								<td><input ng-model="cond.name" type="text" style="width:50px;" class="text"></td>
								<td><button ng-click="retrive()" class="button">��ȸ</button></td>
							</tr>
						</tbody>
					</table>
				</div>
			</div>
			<div class="sheet_title">
				<ul>
					<li class="txt"><span>[ �� {{totalcnt}}�� ]</span></li>
					<li class="btn">
						<button id="exlDown" 			ng-click="exlDown()"	class="button">�����ٿ�ε�</button>
						<button id="exlUpload" 			ng-click="exlUpLoad()"	class="button">�������ε�</button>
					</li>
				</ul>
			</div>
			<table cellspacing="0" class="simple" id="grid">
				<thead>
					<tr>
						<th>��ȣ</th>
						<th>�ڵ�</th>
						<th>�ڵ��</th>
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
								<td><span>�ڵ�</span></td>
								<td><input ng-disabled="btn.insert < 1" ng-model="input.code" type="text" style="width:100px;" class="text"></td>
							</tr>
							<tr>
								<td><span>�ڵ��</span></td>
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
