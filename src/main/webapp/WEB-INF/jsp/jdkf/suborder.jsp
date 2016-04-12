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
								<button onclick="queryOrder(1)" class="btn btn-success"
									type="button">查询订单</button>
							</span>

						</div>
					</div>
					<div class="panel-body">
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
										<th>抢单人</th>
										<th>抢单单时间</th>
									</tr>
								</thead>
								<tbody id = "orderrs">
								</tbody>
							</table>
							<p id="noval" style="display:none">没有数据！</p>
						</div>
						<ul class="pagination"></ul>
					</div>
				</div>
			</div>
		</div>
	</div>
</div>
<jsp:include page="../foot.jsp" />
</body>
<script src="<%=request.getContextPath()%>/assets/js/jBootstrapPage.js"></script>
<script type="text/javascript">
$('#jdkfli').addClass("active");
$('#suborderli').addClass("active-menu");
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
	
	function createRsOd(rsod){
		if(rsod&&rsod.length>0){
			$("#noval").hide();
			var html = "";
			for(var i=0;i<rsod.length;i++ ){
				html +="<tr><td>"+rsod[i].id+"</td><td>"+rsod[i].wwid+"</td><td>"+rsod[i].amount+"</td><td>"+rsod[i].subusername+"</td><td>"+rsod[i].ctstr+"</td><td>"+rsod[i].desc+"</td><td>"+rsod[i].grabusername+"</td><td>"+rsod[i].upstr+"</td></tr>"
			}
			$("#orderrs").html(html);
		}else{
			$("#orderrs").html('');
			$("#noval").show();
			return;
		}
	}

	function queryOrder(ctpg) {
		var queryparam = $("#queryparam").val();
		if (!queryparam == '') {
			$.ajax({
				url : "queryod",
				type : "POST",
				data : {
					'qstr' : queryparam,
					'ctpg':ctpg
				},
				success : function(rs) {
					var data = $.parseJSON(rs);
					if (data.status == 1000) {
						alert("查询订单成功");
						createRsOd(data.orderlist);
						if(ctpg==1){
							$(".pagination").jBootstrapPage({
					            pageSize : 20,
					            total : data.maxrow,
					            selectedIndex : data.ctpg,
					            maxPageButton:6,
					            onPageClicked: function(obj, pageIndex) {
					            	queryOrder(pageIndex+1);
					            }
					        });
						}
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