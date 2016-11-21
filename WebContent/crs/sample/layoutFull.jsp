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
	    	if( data ) {/* 정상 데이터 */
	    		$scope.baseYmLoop = data.resultList;
	    		$scope.cond = {baseym : $scope.baseYmLoop[0].code};
	    	}else {/* 통신한 URL에서 데이터가 넘어오지 않았을 때 처리 */ console.log("No-Data"); }
	    }).error(function(data, status, headers, config) { /* 서버와의 연결이 정상적이지 않을 때 처리 */ console.log(status); });
	    
/* GRID  */
		$scope.retrive = function() {
			s = new Spinner(g_opts).spin(t);
		    $http({
		    	method: "POST", //방식
		    	url: "<cx:wc/>/sample.do?method=getGridList", /* 통신할 URL */
		    	//params: {"user":"A", "usernm":"KKK"},//dataObject /* 파라메터로 보낼 데이터 */
		    	headers: { 'Content-Type': 'application/x-www-form-urlencoded' },
		    	//data: $httpParamSerializer( $scope.gridLoop1 )//<<정상
		    	data: $httpParamSerializer( $scope.cond ),//<<정상
		    	headers: { 'Content-Type': 'application/x-www-form-urlencoded; charset=euc-kr' },
		    }).success(function(data, status, headers, config) {
		    	if( data ) {/* 정상 데이터 */
		    		$scope.gridLoop1 = data.resultList;
		    		if($scope.gridLoop1.length > 0)
		    			$scope.totalcnt1 = $scope.gridLoop1[0].totalcnt;
		    		else
		    			$scope.totalcnt1 = 0;
		    	}else {/* 통신한 URL에서 데이터가 넘어오지 않았을 때 처리 */ console.log("No-Data"); }
		    }).error(function(data, status, headers, config) { /* 서버와의 연결이 정상적이지 않을 때 처리 */ console.log(status); });
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
								<td><span>기준년월</span></td>
								<td>
									<select ng-model="cond.baseym" ng-options="x.code as x.value for x in baseYmLoop"  style="width:85px;"/></select> 
								</td>
								<td><span>고객명</span></td>
								<td><input ng-model="cond.usernm" type="text" style="width:50px;" class="text"></td>
								<td><span>고객번호</span></td>
								<td><input ng-model="cond.csno" type="text" style="width:150px;" class="text"></td>
								<td><button id="retriveGridList"	ng-click="retrive()"	class="button">조회</button></td>
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
					<li class="txt"><span>[ 총 {{totalcnt1}}건 ]</span></li>
					<li class="btn">
						<button id="exlDown" 			ng-click="exlDown()"	class="button">엑셀다운로드</button>
						<button id="exlUpload" 			ng-click="exlUpLoad()"	class="button">엑셀업로드</button>
					</li>
				</ul>
			</div>
			<table cellspacing="0" class="simple" id="grid">
				<thead>
					<tr>
						<th>영업점<br>코드</th>
						<th>영업점명</th>
						<th>고객번호</th>
						<th>고객명</th>
						<th>실명번호</th>
						<th>실사<br>담당자 </th>
						<th>계좌금액<br>구간구분 </th>
						<th>전산<br>실사결과 </th>
						<th>문서<br>실사결과 </th>
						<th>관리자<br>질의 결과 </th>
						<th>FATCA확인서<br>실사결과 </th>
						<th>보고<br>대상여부 </th>
						<th>실사<br>완료일 </th>
						<th>FATCA<br>고객 유형 </th>
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
