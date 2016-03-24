<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<jsp:include page="../header.jsp" />
<jsp:include page="../sidebar.jsp" />

<div id="page-wrapper">
	<div id="page-inner">
		<div class="panel panel-info">
			<div class="panel-heading">
				<i class="fa fa-bell fa-fw"></i>首页 >> 接待客服 >> 信息上传
			</div>
		</div>
		<div id="addorder" style="display: none;" class="row">
			<div style="width: 100%;" class="col-md-6 col-sm-6 col-xs-12">
				<div class="panel panel-info">
					<div class="panel-heading">填写订单详情</div>
					<div class="panel-body">
						<form id="orderform" role="form">
							<div class="form-group col-md-6">
								<label>旺旺ID:</label> <input id="wwid" name="wwid"
									class="form-control" type="text">
							</div>
							<div class="form-group col-md-6">
								<label>订单金额:</label> <input id="amount" name="amount"
									class="form-control"
									onkeyup="value=value.replace(/[^\d{1,}\.\d{1,}|\d{1,}]/g,'')"
									onafterpaste="this.value=this.value.replace(/\D/g,'')"
									maxlength="9" size="14" type="text">
							</div>
							<div class="form-group col-md-12">
								<label>备 注:</label> <input id="desc" name="desc"
									class="form-control" type="text">
							</div>
							<a href="javascript:subOrder();" style="float: right;background-color: #A600FF;"
								class="btn btn-info">提交订单</a>
						</form>
					</div>
				</div>
			</div>
		</div>

		<div class="row">
			<div style="width: 100%;" class="col-md-6 col-sm-6 col-xs-12">
				<div class="panel panel-info">
					<a id="addorderbtn" href="javascript:showHideAddform();"
						class="btn btn-info btn-block">添加订单</a>
					<div class="panel-footer">
						<div class="input-group">
							<input id="queryparam" type="text" class="form-control"
								placeholder="请输入旺旺ID或抢单客服"> <span
								class="input-group-btn">
								<button onclick="queryOrder()" class="btn btn-success"
									type="button">查询订单</button>
							</span>

						</div>
					</div>
					<div class="panel-body">
						<div class="table-responsive">
							<table class="table table-striped table-bordered table-hover">
								<thead>
									<tr>
										<th>#</th>
										<th>First Name</th>
										<th>Last Name</th>
										<th>Username</th>
									</tr>
								</thead>
								<tbody>
									<tr>
										<td>1</td>
										<td>Mark</td>
										<td>Otto</td>
										<td>@mdo</td>
									</tr>
									<tr>
										<td>2</td>
										<td>Jacob</td>
										<td>Thornton</td>
										<td>@fat</td>
									</tr>
									<tr>
										<td>3</td>
										<td>Larry</td>
										<td>the Bird</td>
										<td>@twitter</td>
									</tr>
								</tbody>
							</table>
							<ul class="pagination">
								<li class="disabled"><a href="#">«</a></li>
								<li class="active"><a href="#">1 <span class="sr-only">(current)</span></a></li>
								<li><a href="#">2</a></li>
								<li><a href="#">3</a></li>
								<li><a href="#">4</a></li>
								<li><a href="#">5</a></li>
								<li><a href="#">»</a></li>
							</ul>
						</div>
					</div>
				</div>
			</div>
		</div>
	</div>
</div>
<jsp:include page="../foot.jsp" />
</body>
<script type="text/javascript">
	var showflag = false;
	function showHideAddform() {
		if (showflag) {
			showflag = false;
			$("#addorder").hide();
			$("#addorderbtn").html('添加订单');
		} else {
			showflag = true;
			$("#addorder").show();
			$("#addorderbtn").html('隐藏');
		}
	}

	function queryOrder() {
		var queryparam = $("#queryparam").val();
		if (!queryparam == '') {
			$.ajax({
				url : "queryorder",
				type : "POST",
				data : {
					'queryparam' : queryparam
				},
				success : function(rs) {
					var data = $.parseJSON(rs);
					if (data.status == 1000) {
						alert("查询订单成功");
					} else {
						alert("查询异常!");
					}
				},
				error : function(error) {
					alert("服务异常!");
				}
			});
		}
	}

	function subOrder() {
		var desc = $("#desc").val();
		var amount = $("#amount").val();
		var wwid = $("#wwid").val();
		if (desc == '' || amount == '' || wwid == '') {
			alert("请输入完整的订单信息");
		} else {
			$.ajax({
				url : "addorder",
				type : "POST",
				data : $("#orderform").serialize(),
				success : function(rs) {
					var data = $.parseJSON(rs);
					if (data.status == 1000) {
						showflag = false;
						$("#addorder").hide();
						alert("添加订单成功");
					} else if (data.status == 1009) {
						alert("订单信息不完整");
					} else if (data.status == 1010) {
						alert("金额异常");
					} else {
						alert("未知异常!");
					}
				},
				error : function(error) {
					alert("服务异常!");
				}
			});
		}
	}
</script>
</html>