<%@ page language="java" contentType="text/html; charset=EUC-KR" pageEncoding="EUC-KR"%>
<!DOCTYPE html>
<html>
<head>
<%@include file="/inc/crs_sst.inc"%>
<script type="text/javascript">
	/* Angular Initialize */
	var app = angular.module("grid1", []).config(function($compileProvider){
		$compileProvider.aHrefSanitizationWhitelist(/^\s*(https?|ftp|mailto|file|javascript):/);
	}).controller("customersCtrl1", function($scope, $http) {
		/* TotalCount Initialize */
		$scope.totalcnt1 = 0;
		$scope.totalcnt2 = 0;
		
		/* GRID 1 */
		$("#retriveGridList1").click(function() {
		    $http({
		    	method: "POST", //방식
		    	url: "<cx:wc/>/sample.do?method=getGridList", /* 통신할 URL */
		    	params: null//dataObject /* 파라메터로 보낼 데이터 */
		    	//headers: {'Content-Type': 'application/json; charset=utf-8'} //헤더
		    }).success(function(data, status, headers, config) {
		    	if( data ) {/* 정상 데이터 */
		    		$scope.gridLoop1 = data.resultList;
		    		if($scope.gridLoop1.length > 0)
		    			$scope.totalcnt1 = $scope.gridLoop1[0].totalcnt;
		    		else
		    			$scope.totalcnt1 = 0;
		    	}else {/* 통신한 URL에서 데이터가 넘어오지 않았을 때 처리 */ console.log("No-Data"); }
		    }).error(function(data, status, headers, config) { /* 서버와의 연결이 정상적이지 않을 때 처리 */ console.log(status); });
		});
		
		/* popup  */
		$scope.personalPopup = function($event,idx){
			$event.stopPropagation();
			$("#csno").val($scope.gridLoop1[idx].csno);
			var rtn = com_popup("<cx:wc/>/sample.do?method=popupView", 1280, 700);
		};
		
		/* go detail */
		$scope.goDetail = function($event, index){
			$event.stopPropagation();
			$scope.csno 				= $scope.gridLoop1[index].csno
			$scope.custnm 				= $scope.gridLoop1[index].custnm
			$scope.rnnofmt 				= $scope.gridLoop1[index].rnnofmt
			$scope.fatcaacipcgpennm 	= $scope.gridLoop1[index].fatcaacipcgpennm
			$scope.fatcaamtsctclacdnm 	= $scope.gridLoop1[index].fatcaamtsctclacdnm
			$scope.fatcaczaciprscdnm 	= $scope.gridLoop1[index].fatcaczaciprscdnm
		};
		
		/* Grid No Data Bar */
		$scope.gridempty = function(gNum){
			if(gNum=="1"){
				return $scope.totalcnt1>0?false:true;
			}else if(gNum=="2"){
				return $scope.totalcnt2>0?false:true;
			}
		};
	});

	/*Initialize Variable*/
	/* Jquery OnLoad */
	$(function() {
	});
</script>
</head>
<body ng-csp="no-inline-style;no-unsafe-eval">
	<input type="hidden" id="csno">
	<form method="post" onSubmit="return false;">
		<div id="subMain" ng-app="grid1" ng-controller="customersCtrl1">
			<div class="top_title">
				<ul>
					<li class="txt">MASTER GRID</li>
				</ul>
			</div>
			<div class="sheet_title">
				<ul>
					<li class="txt"><span>[ 총 {{totalcnt1}}건 ]</span></li>
					<li class="btn">
						<button id="retriveGridList1" 	class="button">조회</button>
						<button id="exlDown1" 			class="button">엑셀다운로드</button>
						<button id="exlUpload1" 		class="button">엑셀업로드</button>
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
					<tr ng-show="gridempty('1')" class="empty" style="height:300px;"><td colspan="14">&nbsp;No Data</td></tr>
					<tr ng-repeat="x in gridLoop1" ng-class-odd="'odd'" ng-class-even="'even'" ng-click="goDetail($event, $index)">
						<td style="text-align:center; height:7px;">{{x.slsbrno}}</td>
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

<!--------------------------------------------------------------------------------->
			<div class="top_title">
				<ul>
					<li class="txt">DETAIL</li>
				</ul>
			</div>
			<table class="type05">
				<tr>
					<th scope="row">고객번호</th><td ng-bind="csno"></td>
			    	<th scope="row">고객명</th><td ng-bind="custnm"></td>
				</tr>
				<tr>
					<th scope="row">실명번호</th> <td ng-bind="rnnofmt"></td>
				</tr>
				<tr>
					<th scope="row">실사 담당자</th> <td ng-bind="fatcaacipcgpennm"></td>
				</tr>
				<tr>
					<th scope="row">전산실사결과</th> <td ng-bind="fatcaamtsctclacdnm"></td>
				</tr>
				<tr>
					<th scope="row">문서실사결과</th> <td ng-bind="fatcaczaciprscdnm"></td>
				</tr>
			</table>

		</div>
	</form>
</body>
</html>
