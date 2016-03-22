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
<
<script type="text/javascript">
function longPolling() {
	$.ajax({
		url : "<%=request.getContextPath()%>/ajax",
			data : {
				"timed" : new Date().getTime()
			},
			dataType : "text",
			type : "GET",
			timeout : 8000,
			error : function(XMLHttpRequest, textStatus, errorThrown) {
				console.log(XMLHttpRequest.status, textStatus);
				if(XMLHttpRequest.status==0){
					longPolling();
				}else{
					setTimeout(function() {
						longPolling();
					}, 5000);
				}
			},
			success : function(data, textStatus) {
				if (textStatus == "success") { // 请求成功
					console.log(textStatus, data);
				}
				longPolling();
			}
		});
	}
	$(document).ready(function() {
		longPolling();

	});
</script>
