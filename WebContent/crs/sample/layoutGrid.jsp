<%@ page language="java" contentType="text/html; charset=EUC-KR" pageEncoding="EUC-KR"%>
<!DOCTYPE html>
<html>
<head>
<%@include file="/inc/crs_sst.inc"%>
<script type="text/javascript">
	
	var app = angular.module("module", []).config(function($compileProvider){
		$compileProvider.aHrefSanitizationWhitelist(/^\s*(https?|ftp|mailto|file|javascript):/);
	}).controller("customersCtrl1", function($scope, $http) {
		/**********/
		/* GRID 1 */
		/**********/
		$scope.totalcnt1 = 0;
		$("#retriveGridList1").click(function() {
		    $http.post("<cx:wc/>/sample.do?method=getGridList").success(function (data) {
	    		$scope.gridLoop1 = data.resultList;
	    		$scope.totalcnt1 = $scope.gridLoop1[0].totalcnt;
	    	});
		    
		});
		/**********/
		/* GRID 2 */
		/**********/
		$scope.totalcnt2 = 0;
		$("#retriveGridList2").click(function() {
		    $http.post("<cx:wc/>/sample.do?method=getGridList").success(function (data) {
	    		$scope.gridLoop2 = data.resultList;
	    		$scope.totalcnt2 = $scope.gridLoop2[0].totalcnt;
	    	});
		});
	});

	/*Initialize Variable*/
	
	/* Jquery OnLoad */
	$(function() {
		//ȭ���ʱ�ȭ
		init();
	});

	function linkPersonal(objcrym, objcsno){
		window.open("<cx:wc/>/acipTarget.do?method=personalView&crym="+objcrym+"&csno="+objcsno+"&mode=popup","popPersonal","width=1180,height=730,top=200,left=200, resizable=yes");
	}	
	function linkQuestion(objcrym, objcsno, objcustnm){
		window.open("<cx:wc/>/acipResult.do?method=question&crym="+objcrym+"&csno="+objcsno+"&custNm="+objcustnm+"&mode=popup","popQuestion","width=1080,height=560,top=200,left=200, resizable=yes");
	}	
	function linkDocument(objcrym, objcsno, objcustnm){
		window.open("<cx:wc/>/acipResult.do?method=document&crym="+objcrym+"&csno="+objcsno+"&custNm="+objcustnm+"&mode=popup","popDocument","width=1080,height=520,top=200,left=200, resizable=yes");
	}
</script>
</head>
<body ng-csp="no-inline-style;no-unsafe-eval">
	<form method="post" onSubmit="return false;">
		<div id="subMain" ng-app="module" ng-controller="customersCtrl1">
			<div class="top_title">
				<ul>
					<li class="txt">�׸��� 1�� ��� </li>
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
			<table cellspacing="0" class="simple">
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
					<tr ng-hide="gridLoop1.length > 0" ng-show="gridLoop1.length === 0" class='empty'><td colspan='14'>&nbsp;No Data</td></tr>
					<tr ng-repeat="x in gridLoop1" ng-class-odd="'odd'" ng-class-even="'even'">
						<td style="text-align:center">{{x.slsbrno}}</td>
						<td style="text-align:center">{{x.slsbrnonm}}</td>
						<td style="text-align:center">{{x.csno}}</td>
						<td style="text-align:center">
							<a href="javascript:linkPersonal('{{x.crym}}' , '{{x.csno}}' )">{{x.custnm}}</a>
						</td> 
						<td style="text-align:center">{{x.rnnofmt}}</td>
						<td style="text-align:center">{{x.fatcaacipcgpennm}}</td>
						<td style="text-align:center">{{x.fatcaamtsctclacdnm}}</td>
						<td style="text-align:center">{{x.fatcaczaciprscdnm}}</td>
						<td style="text-align:center">
							<a href="javascript:linkDocument('{{x.crym}}','{{x.csno}}','{{x.custnm}}');">{{x.fatcadocaciprscdnm}}</a>
						</td>
						<td style="text-align:center">
							<a href="javascript:linkQuestion('{{x.crym}}','{{x.csno}}','{{x.custnm}}');">{{x.fatcaiqrrscdnm}}</a>
						</td>
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
					<li class="txt">�׸��� 2�� ���</li>
				</ul>
			</div>
			<div class="sheet_title">
				<ul>
					<li class="txt"><span>[ �� {{totalcnt2}}�� ]</span></li>
					<li class="btn">
						<button id="retriveGridList2" 	class="button">��ȸ</button>
						<button id="exlDown2" 			class="button">�����ٿ�ε�</button>
						<button id="exlUpload2" 		class="button">�������ε�</button>
					</li>
				</ul>
			</div>
			<table cellspacing="0" class="simple">
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
					<tr ng-hide="gridLoop2.length > 0" ng-show="gridLoop2.length === 0" class='empty'><td colspan='14'>&nbsp;No Data</td></tr>
					<tr  ng-repeat="x in gridLoop2" ng-class-odd="'odd'" ng-class-even="'even'">
						<td style="text-align:center">{{x.slsbrno}}</td>
						<td style="text-align:center">{{x.slsbrnonm}}</td>
						<td style="text-align:center">{{x.csno}}</td>
						<td style="text-align:center">
							<a href="javascript:linkPersonal('{{x.crym}}' , '{{x.csno}}' )">{{x.custnm}}</a>
						</td> 
						<td style="text-align:center">{{x.rnnofmt}}</td>
						<td style="text-align:center">{{x.fatcaacipcgpennm}}</td>
						<td style="text-align:center">{{x.fatcaamtsctclacdnm}}</td>
						<td style="text-align:center">{{x.fatcaczaciprscdnm}}</td>
						<td style="text-align:center">
							<a href="javascript:linkDocument('{{x.crym}}','{{x.csno}}','{{x.custnm}}');">{{x.fatcadocaciprscdnm}}</a>
						</td>
						<td style="text-align:center">
							<a href="javascript:linkQuestion('{{x.crym}}','{{x.csno}}','{{x.custnm}}');">{{x.fatcaiqrrscdnm}}</a>
						</td>
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
