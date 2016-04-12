<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
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
			<div style="width: 100%;" class="col-md-6 col-sm-6 col-xs-12">
				<div id= "grabodtb" class="panel-body">
				<c:if test="${orderlist !=null}">
					<div class="table-responsive">
						<table class="table table-striped table-bordered table-hover">
							<thead>
								<tr>
									<th>ID</th>
									<th>旺旺ID</th>
									<th>金额</th>
									<th>提单人</th>
									<th>提单时间</th>
									<th>备注</th>
									<th>操作</th>
								</tr>
							</thead>
							<tbody id="orderrs">
							<c:forEach  items="${orderlist}" var ="order">
								<tr>
										<td>${order.id}</td>
										<td>${order.wwid}</td>
										<td>${order.amount}</td>
										<td>${order.subusername}</td>
										<td>${order.ctstr}</td>
										<td>${order.desc}</td>
										<td>${order.desc}</td>
									</tr>
							</c:forEach>
							</tbody>
						</table>
					</div>
					</c:if>
				</div>
				<div class="col-md-4"
					style="margin-left: auto; margin-right: auto; float: none; margin-top: 36px;">
					<div class="main-box mb-red">
						<a href="javascript:graborder(0)"> <i class="fa fa-bolt fa-5x"></i>
							<h5>
								金额大于30元的订单数目为<font id="bigt" color="red">0</font>个
							</h5>
							<h5>
								金额小于30元的订单数目为<font id="litt" color="red">0</font>个
							</h5>
						</a>
					</div>
				</div>
			</div>

		</div>
	</div>
</div>
<jsp:include page="../foot.jsp" />
<script type="text/javascript">
	$('#jdkfli').addClass("active");
	$('#graborderli').addClass("active-menu");
	function graborder(odid) {
		if (!odid) {
			odid = 0;
		}
		$.ajax({
			url : "graborder/" + odid,
			type : "GET",
			success : function(rs) {
				var data = $.parseJSON(rs);
				if (data.status == 1000) {
					alert("抢单成功！");
					location.reload();
				} else {
					alert("手慢啦!");
				}
			},
			error : function(error) {
				alert("服务异常!");
			}
		});
	}
	<!--
//-->
</script>
</body>
</html>