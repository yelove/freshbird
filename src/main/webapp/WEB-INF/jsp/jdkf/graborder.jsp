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
		<div id="addorder" style="display: none;" class="row">
			<div style="width: 100%;" class="col-md-6 col-sm-6 col-xs-12">
				<div class="panel panel-info">
					<div class="panel-heading">BASIC FORM</div>
					<div class="panel-body">
						<form role="form">
							<div class="form-group">
								<label>Enter Name</label> <input class="form-control"
									type="text">
								<p class="help-block">Help text here.</p>
							</div>
							<div class="form-group">
								<label>Enter Email</label> <input class="form-control"
									type="text">
								<p class="help-block">Help text here.</p>
							</div>
							<div class="form-group">
								<label>Text area</label>
								<textarea class="form-control" rows="3"></textarea>
							</div>
							<button type="submit" class="btn btn-info">Send Message
							</button>
						</form>
					</div>
				</div>
			</div>
		</div>

		<div class="row">
			<div style="width: 100%;" class="col-md-6 col-sm-6 col-xs-12">
				<div class="panel panel-info">
					<a href="#" class="btn btn-info btn-block">添加订单</a>
					<div class="panel-footer">
						<div class="input-group">
							<input type="text" class="form-control"
								placeholder="请输入旺旺ID或抢单客服"> <span
								class="input-group-btn">
								<button class="btn btn-success" type="button">查询订单</button>
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
</html>