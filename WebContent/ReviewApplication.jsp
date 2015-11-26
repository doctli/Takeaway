<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta charset="utf-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta name="viewport" content="width=device-width, initial-scale=1">
<!-- 上述3个meta标签*必须*放在最前面，任何其他内容都*必须*跟随其后！ -->
<!-- Bootstrap -->
<link href="css/bootstrap.min.css" rel="stylesheet">

<!-- Custom styles for this template -->
<link href="css/mystyle.css" rel="stylesheet">
<link href="css/review.css" rel="stylesheet">
<!-- <script src="../../assets/js/ie-emulation-modes-warning.js"></script> -->
<!-- HTML5 shim and Respond.js for IE8 support of HTML5 elements and media queries -->
<!-- WARNING: Respond.js doesn't work if you view the page via file:// -->
<!--[if lt IE 9]>
      <script src="//cdn.bootcss.com/html5shiv/3.7.2/html5shiv.min.js"></script>
      <script src="//cdn.bootcss.com/respond.js/1.4.2/respond.min.js"></script>
    <![endif]-->
    
<script charset="utf-8" src="js/review.js"></script>
<title>申请审核</title>
</head>
<body>
	<%@ include file='Header.jsp' %>
	<div class="container content">
		<div class="row">
			<div class="col-md-10 col-md-offset-1 shadowed">
				<hr class="space">
				<div class="row">
					<div class="col-md-10 col-md-offset-1 form_div">
						<form class="form-horizontal">
							<div class="form-group">
								<div class="col-md-3">
									<select class="form-control"></select>
								</div>
								<div class="col-md-3">
									<select class="form-control"></select>
								</div>
							</div>
						</form>
					</div>
				</div>
				<hr/>
				<div class="row">
					<div class="col-md-10 col-md-offset-1 ">
						<table class="table table-striped table-hover">
							<tr><th>#</th><th>申请日期</th><th>店铺名</th><th>经营品类</th><th>店铺的详细地址</th><th>操作</th></tr>
							<tr><td><input type="checkbox" /></td><td>申请日期</td><td>店铺名</td><td>经营品类</td><td>店铺的详细地址</td><td><button class="btn btn-success">同意</button><button class="btn btn-default">不同意</button></td></tr>
						</table>
					</div>
				</div>
			</div>
		</div>
	</div>
	<!-- Bootstrap core JavaScript
    ================================================== -->
	<!-- Placed at the end of the document so the pages load faster -->
	<!-- jQuery (necessary for Bootstrap's JavaScript plugins) -->
	<script src="//cdn.bootcss.com/jquery/1.11.3/jquery.min.js"></script>
	<!-- Include all compiled plugins (below), or include individual files as needed -->
	<script src="js/bootstrap.min.js"></script>
</body>
</html>