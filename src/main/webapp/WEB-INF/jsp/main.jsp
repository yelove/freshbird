<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<jsp:include page="header.jsp" />
<jsp:include page="sidebar.jsp" />

<div id="page-wrapper">
	<div id="page-inner">
		<div class="row">
			<div class="col-md-12">
				<h1 class="page-head-line">首页</h1>
				<h1 class="page-subhead-line">游戏是生活的一部分。点卡是游戏的一部分！</h1>
			</div>
		</div>
		<div class="row">
			<div class="col-md-8">
				<div class="row">
					<div class="col-md-12">
						<div id="reviews" class="carousel slide" data-ride="carousel">
							<div class="carousel-inner">
								<div class="item active">
									<div class="col-md-10 col-md-offset-1">
										<h4>
											<i class="fa fa-quote-left"></i>梦想还是要有的，万一实现了呢. <i class="fa fa-quote-right"></i>
										</h4>
										<h5 class="pull-right">
											<strong class="c-black">马老师</strong>
										</h5>
									</div>
								</div>
								<div class="item">
									<div class="col-md-10 col-md-offset-1">
										<h4>
											<i class="fa fa-quote-left"></i>一个人如果连梦想都没有，跟咸鱼有什么区别. <i class="fa fa-quote-right"></i>
										</h4>
										<h5 class="pull-right">
											<strong class="c-black">周星星</strong>
										</h5>
									</div>
								</div>
								<div class="item">
									<div class="col-md-10 col-md-offset-1">
										<h4>
											<i class="fa fa-quote-left"></i>梦想是好的，现实是残酷的，想过，努力过，就一定成功过？ <i class="fa fa-quote-right"></i>
										</h4>
										<h5 class="pull-right">
											<strong class="c-black">...</strong>
										</h5>
									</div>
								</div>
							</div>
							<ol class="carousel-indicators">
								<li data-target="#reviews" data-slide-to="0" class="active"></li>
								<li data-target="#reviews" data-slide-to="1"></li>
								<li data-target="#reviews" data-slide-to="2"></li>
							</ol>
						</div>
					</div>
				</div>
				<hr />
			</div>
		</div>
		<div class="row" style="padding-bottom: 100px;">
			<div class="col-md-6">
				<div class="panel panel-back noti-box">
					<span class="icon-box bg-color-black"> <i
						class="fa fa-bicycle"></i>
					</span>
					<div class="text-box">
						<p class="main-text">今天的任务是</p>
						<p>努力</p>
						<p>期限：一天</p>
						<hr />
						<p>
							<span class=" color-bottom-txt"><i class="fa fa-edit"></i>
								熟悉现有业务流程 </span>

						</p>
						<hr />
						发现 and 创建新的业务线。
					</div>
				</div>
			</div>
		</div>
	</div>
</div>
<jsp:include page="foot.jsp" />
<<script type="text/javascript">
$('#mainmenu').addClass("active-menu");
</script>
</body>
</html>