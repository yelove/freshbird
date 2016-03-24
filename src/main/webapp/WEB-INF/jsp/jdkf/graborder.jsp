<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<jsp:include page="../header.jsp" />
<jsp:include page="../sidebar.jsp" />

<div id="page-wrapper">
	<div id="page-inner">
		<div class="panel panel-info">
			<div class="panel-heading">
				<i class="fa fa-bell fa-fw"></i>首页 >> 接待客服 >> 抢单页面
			</div>
		</div>

		<div class="row">
			<div class="col-md-4">
				<div class="main-box mb-red">
					<a href="graborder/0"> <i class="fa fa-bolt fa-5x"></i>
						<h5>金额大于30元的订单数目为<font id="bigt" color="red">0</font>个     金额小于30元的订单数目为<font id="litt" color="red">0</font> 个</h5>
					</a>
				</div>
			</div>
		</div>
	</div>
</div>
<jsp:include page="../foot.jsp" />
</body>
</html>