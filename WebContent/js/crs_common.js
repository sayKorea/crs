var g_opts = {
		  lines: 17 // The number of lines to draw
		, length: 13 // The length of each line
		, width: 6 // The line thickness
		, radius: 12 // The radius of the inner circle
		, scale: 1.25 // Scales overall size of the spinner
		, corners: 1 // Corner roundness (0..1)
		, color: '#000' // #rgb or #rrggbb or array of colors
		, opacity: 0.1 // Opacity of the lines
		, rotate: 0 // The rotation offset
		, direction: 1 // 1: clockwise, -1: counterclockwise
		, speed: 1 // Rounds per second
		, trail: 56 // Afterglow percentage
		, fps: 20 // Frames per second when using setTimeout() as a fallback for CSS
		, zIndex: 2e9 // The z-index (defaults to 2000000000)
		, className: 'spinner' // The CSS class to assign to the spinner
		, top: '50%' // Top position relative to parent
		, left: '50%' // Left position relative to parent
		, shadow: true // Whether to render a shadow
		, hwaccel: true // Whether to use hardware acceleration
		, position: 'absolute' // Element positioning
	}
var g_target = document.getElementById('loading');
var g_spinner = null;

function showSpin(t){
	if(t) g_spinner = new Spinner(g_opts).spin(g_target); 
	else g_spinner.stop();	
}


//menubar=no  //메뉴바 없애기
//toolbar=no // 툴바 없애기
//location=no  //주소표시줄 없애기
//status=no //상태표시줄 없애기
//scrollbars=no //스크롤바 없애기
//fullscreen //최대창크기로 열기 =>F11 눌린 크기이긴 하지만, F11과는 다른 기능임
//width //가로크기
//height //세로크기
//top //위에서부터 위치
//left //왼쪽에서부터 위치
//resizable=no //창 크기 조절 금지
// WINDOW POPUP
function com_popup(url, width, height){
	var winPosLeft = (screen.width-width) / 2;
	var winPosTop = (screen.height-height) / 2;
	var dial = window.open(url, "newPopup", "width="+width+",height="+height+",top="+winPosTop+",left="+winPosLeft+", toolbar=no, directories=no,status=no,scrollorbars=no,resizable=no");
	return dial;
}
