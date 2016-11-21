<%@ page language="java" contentType="text/html; charset=EUC-KR" pageEncoding="EUC-KR"%>
<!DOCTYPE html>
<html ng-app="Main"><head><%@include file="/inc/crs_sst.inc"%>
<script type="text/javascript">
	var t = $("#loading");
	var s = null;

	/* Angular Initialize */
	var app = angular.module("Main", []).config(function($compileProvider){ $compileProvider.aHrefSanitizationWhitelist(/^\s*(https?|ftp|mailto|file|javascript):/);
	}).controller("MainCtrl", function($scope, $http, $httpParamSerializer, $document) {
/* GRID TotalCount Initialize */
		$scope.totalcnt1 = 0;
		
/* COMBO BASE YYYYMM */		
	    $http({
	    	method	: "POST",
	    	url		: "<cx:wc/>/sample.do?method=getBaseYmList",
	    	params	: ""
	    }).success(function(data, status, headers, config) {
	    	if( data ) {/* ���� ������ */
	    		$scope.baseYmLoop = data.resultList;
	    		$scope.cond = {baseym : $scope.baseYmLoop[0].code};
	    	}else {/* ����� URL���� �����Ͱ� �Ѿ���� �ʾ��� �� ó�� */ console.log("No-Data"); }
	    }).error(function(data, status, headers, config) { /* �������� ������ ���������� ���� �� ó�� */ console.log(status); });
	    
/* GRID  */
		$scope.retrive = function() {
			s = new Spinner(g_opts).spin(t);
		    $http({
		    	method: "POST", //���
		    	url: "<cx:wc/>/sample.do?method=getGridList", /* ����� URL */
		    	//params: {"user":"A", "usernm":"KKK"},//dataObject /* �Ķ���ͷ� ���� ������ */
		    	headers: { 'Content-Type': 'application/x-www-form-urlencoded' },
		    	//data: $httpParamSerializer( $scope.gridLoop1 )//<<����
		    	data: $httpParamSerializer( $scope.cond ),//<<����
		    	headers: { 'Content-Type': 'application/x-www-form-urlencoded; charset=euc-kr' },
		    }).success(function(data, status, headers, config) {
		    	if( data ) {/* ���� ������ */
		    		$scope.gridLoop1 = data.resultList;
		    		if($scope.gridLoop1.length > 0)
		    			$scope.totalcnt1 = $scope.gridLoop1[0].totalcnt;
		    		else
		    			$scope.totalcnt1 = 0;
		    	}else {/* ����� URL���� �����Ͱ� �Ѿ���� �ʾ��� �� ó�� */ console.log("No-Data"); }
		    }).error(function(data, status, headers, config) { /* �������� ������ ���������� ���� �� ó�� */ console.log(status); });
		    s.stop();
		};
		
/* POPUP  */
		$scope.personalPopup = function($event,idx){
			$event.stopPropagation();
			$("#csno").val($scope.gridLoop1[idx].csno);
			var rtn = com_popup("<cx:wc/>/sample.do?method=popupView", 1280, 700);
		};
	});
	
	/*Initialize Variable*/
	/* Jquery OnLoad */
	$(function(){
//       var regx = /INPUT|SELECT|TEXTAREA/i;
	}); 

</script>
</head>
<body ng-csp="no-inline-style;no-unsafe-eval">
	<input type="hidden" id="csno">
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
								<td><span>���س��</span></td>
								<td>
									<select ng-model="cond.baseym" ng-options="x.code as x.value for x in baseYmLoop"  style="width:85px;"/></select> 
								</td>
								<td><span>����</span></td>
								<td><input ng-model="cond.usernm" type="text" style="width:50px;" class="text"></td>
								<td><span>����ȣ</span></td>
								<td><input ng-model="cond.csno" type="text" style="width:150px;" class="text"></td>
								<td><button id="retriveGridList"	ng-click="retrive()"	class="button">��ȸ</button></td>
							</tr>
						</tbody>
					</table>
				</div>
			</div>
			<br/><br/>
			<div class="top_title">
				<ul>
					<li class="txt">GRID</li>
				</ul>
			</div>
			<div class="sheet_title">
				<ul>
					<li class="txt"><span>[ �� {{totalcnt1}}�� ]</span></li>
					<li class="btn">
						<button id="exlDown" 			ng-click="exlDown()"	class="button">�����ٿ�ε�</button>
						<button id="exlUpload" 			ng-click="exlUpLoad()"	class="button">�������ε�</button>
					</li>
				</ul>
			</div>
			<table cellspacing="0" class="simple" id="grid">
				<thead>
					<tr>
						<th>������<br>�ڵ�</th>
						<th>��������</th>
						<th>����ȣ</th>
						<th>����</th>
						<th>�Ǹ��ȣ</th>
						<th>�ǻ�<br>����� </th>
						<th>���±ݾ�<br>�������� </th>
						<th>����<br>�ǻ��� </th>
						<th>����<br>�ǻ��� </th>
						<th>������<br>���� ��� </th>
						<th>FATCAȮ�μ�<br>�ǻ��� </th>
						<th>����<br>��󿩺� </th>
						<th>�ǻ�<br>�Ϸ��� </th>
						<th>FATCA<br>�� ���� </th>
					</tr>
				</thead>
				<tbody>
					<tr ng-show="totalcnt1 < 1" class="empty" style="height:300px;"><td colspan="14">&nbsp;No Data</td></tr>
					<tr ng-repeat="x in gridLoop1" ng-class-odd="'odd'" ng-class-even="'even'" ng-click="goDetail($event, $index)">
						<td style="text-align:center;">{{x.slsbrno}}</td>
						<td style="text-align:center">{{x.slsbrnonm}}</td>
						<td style="text-align:center">{{x.csno}}</td>
						<td style="text-align:center">
							<a href="" ng-click="personalPopup($event,$index);" target="_self">{{x.custnm}}</a>
						</td> 
						<td style="text-align:center">{{x.rnnofmt}}</td>
						<td style="text-align:center">{{x.fatcaacipcgpennm}}</td>
						<td style="text-align:center">{{x.fatcaamtsctclacdnm}}</td>
						<td style="text-align:center">{{x.fatcaczaciprscdnm}}</td>
						<td style="text-align:center">{{x.fatcadocaciprscdnm}}</td>
						<td style="text-align:center">{{x.fatcaiqrrscdnm}}</td>
						<td style="text-align:center">{{x.fatcacfrrptaciprscdnm}}</td>
						<td style="text-align:center">{{x.fatcarepttrgetdvcdnm}}</td>
						<td style="text-align:center">{{x.fatcaacipcomptdt}}</td>
						<td style="text-align:center">{{x.fatcacustdvcdnm}}</td>
				  	</tr>
				</tbody>
			</table>
			<span class="pagelinks"><strong>1</strong></span>
		</div>
	</form>
</body>
</html>
