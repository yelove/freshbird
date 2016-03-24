<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%-- js 引用  --%>
<div id="footer-sec">Copyright &copy; 2016.台卡网络科技有限公司 All rights
	reserved.</div>
<script src="<%=request.getContextPath()%>/assets/js/jquery-1.10.2.js"></script>
<script src="<%=request.getContextPath()%>/assets/js/bootstrap.js"></script>
<script
	src="<%=request.getContextPath()%>/assets/js/jquery.metisMenu.js"></script>
<script src="<%=request.getContextPath()%>/assets/js/custom.js"></script>
<script type="text/javascript">
function longPolling() {
	$.ajax({
		url : "<%=request.getContextPath()%>/ajax",
			data : {
				"timed" : new Date().getTime()
			},
			dataType : "text",
			type : "GET",
			timeout : 15000,
			error : function(XMLHttpRequest, textStatus, errorThrown) {
				console.log(XMLHttpRequest.status, textStatus);
				if(textStatus == 'timeout'){
					longPolling();
				}else if(XMLHttpRequest.status==0){
					setTimeout(function() {
						longPolling();
					}, 10000);
				}else{
					setTimeout(function() {
						longPolling();
					}, 12000);
				}
			},
			success : function(data, textStatus) {
				if (textStatus == "success") { // 请求成功
					console.log(textStatus, data);
				}
				setTimeout(function() {
					longPolling();
				}, 2000);
			}
		});
	}
	$(document).ready(function() {
		longPolling();
	});
</script>
