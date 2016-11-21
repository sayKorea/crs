<%@ page language="java" contentType="text/html; charset=EUC-KR" pageEncoding="EUC-KR"%>
<!DOCTYPE html>
<html>
<head>
<%@include file="/inc/crs_sst.inc"%>
<script type="text/javascript">
	var t = document.getElementById('loading');
	var s = null;
	
	/* Angular Initialize */
	var app = angular.module("grid1", []).config(function($compileProvider){
		$compileProvider.aHrefSanitizationWhitelist(/^\s*(https?|ftp|mailto|file|javascript):/);
	}).controller("customersCtrl1", function($scope, $http) {
		/* TotalCount Initialize */
		$scope.totalcnt1 = 0;
		$scope.totalcnt2 = 0;
		
		/* GRID 1 */
		$("#retriveGridList1").click(function() {
			s = new Spinner(g_opts).spin(t);
		    $http({
		    	method: "POST", //���
		    	url: "<cx:wc/>/sample.do?method=getGridList", /* ����� URL */
		    	params: null//dataObject /* �Ķ���ͷ� ���� ������ */
		    	//headers: {'Content-Type': 'application/json; charset=utf-8'} //���
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
		});
		
		/* popup  */
		$scope.personalPopup = function($event,idx){
			$event.stopPropagation();
			$("#csno").val($scope.gridLoop1[idx].csno);
			var rtn = com_popup("<cx:wc/>/sample.do?method=popupView", 1280, 700);
		};
// 		$scope.documentPopup = function($event,idx){
// 			$event.stopPropagation();
// 			var rtn = window.open("<cx:wc/>/acipResult.do?method=question&crym="+$scope.gridLoop1[idx].crym+"&csno="+$scope.gridLoop1[idx].csno+"&custNm="+$scope.gridLoop1[idx].custnm+"&mode=popup","popQuestion","width=1080,height=560,top=200,left=200, resizable=yes");
// 		};
// 		$scope.questionPopup = function($event,idx){
// 			$event.stopPropagation();
// 			var rtn = window.open("<cx:wc/>/acipResult.do?method=document&crym="+$scope.gridLoop1[idx].crym+"&csno="+$scope.gridLoop1[idx].csno+"&custNm="+$scope.gridLoop1[idx].custnm+"&mode=popup","popQuestion","width=1080,height=560,top=200,left=200, resizable=yes");
// 		};
		
		/* go detail */
		$scope.goDetail = function($event, index){
			$event.stopPropagation();
			$http({
		    	method: "POST", //���
		    	url: "<cx:wc/>/sample.do?method=getGridList", /* ����� URL */
		    	params:{csno:$scope.gridLoop1[index].csno}//dataObject /* �Ķ���ͷ� ���� ������ */
		    }).success(function(data, status, headers, config) {
		    	if( data ) {
		    		$scope.gridLoop2 = data.resultList;
		    		if($scope.gridLoop2.length > 0){
		    			$scope.totalcnt2 = $scope.gridLoop2[0].totalcnt;
		    		}else {$scope.totalcnt2 = 0;}
		    	}else {/* ����� URL���� �����Ͱ� �Ѿ���� �ʾ��� �� ó�� */ console.log("No-Data"); }
		    }).error(function(data, status, headers, config) { /* �������� ������ ���������� ���� �� ó�� */ console.log(status); });
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
					<li class="txt"><span>[ �� {{totalcnt1}}�� ]</span></li>
					<li class="btn">
						<button id="retriveGridList1" 	class="button">��ȸ</button>
						<button id="exlDown1" 			class="button">�����ٿ�ε�</button>
						<button id="exlUpload1" 		class="button">�������ε�</button>
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
					<tr ng-show="gridempty('1')" class="empty" style="height:290px;"><td colspan="14">&nbsp;No Data</td></tr>
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

<!--------------------------------------------------------------------------------->

			<div class="top_title">
				<ul>
					<li class="txt">DETAIL GRID</li>
				</ul>
			</div>
			<div class="sheet_title">
				<ul>
					<li class="txt"><span>[ �� {{totalcnt2}}�� ]</span></li>
					<li class="btn">
						<button id="exlDown2" 			class="button">�����ٿ�ε�</button>
						<button id="exlUpload2" 		class="button">�������ε�</button>
					</li>
				</ul>
			</div>
			<table cellspacing="0" class="simple" id="grid">
				<thead>
					<tr>
						<th colspan="5" class="groupedHead">���ΰ�����</th>
						<th colspan="6" class="groupedHead">�ǻ�����</th>
						<th colspan="3" class="groupedHead">�������</th>
					</tr>
					<tr>
						<th class="sorted order1">������<br>�ڵ�</th>
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
						</th>
					</tr>
				</thead>
				<tbody>
					<tr ng-show="gridempty('2')" class="empty" style="height:290px;"><td colspan="14">&nbsp;No Data</td></tr>
					<tr  ng-repeat="x in gridLoop2" ng-class-odd="'odd'" ng-class-even="'even'" >
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
		</div>
	</form>
</body>
</html>
