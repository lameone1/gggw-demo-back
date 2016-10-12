<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<c:set var="contextPath" value="${pageContext.request.contextPath}" />
<!DOCTYPE html> 
<html>
	<head>
		<meta charset="utf-8" />
		<meta http-equiv="X-UA-Compatible" content="IE=edge">
		<meta name="viewport" content="width=device-width, initial-scale=1.0" />
		<title>登录</title>
		<!-- basic styles -->
		<link rel="stylesheet"  href="${contextPath }/static/css/back/bootstrap.min.css"  />		
		<link rel="stylesheet"  href="${contextPath }/static/css/back/font-awesome.min.css" />
		<!--[if IE 7]>
		  <link rel="stylesheet" href="assets/css/font-awesome-ie7.min.css" />
		<![endif]-->

		<!-- ace styles -->
		<link rel="stylesheet"  href="${contextPath }/static/css/back/ace.min.css" >
		<link rel="stylesheet"  href="${contextPath }/static/css/back/ace-rtl.min.css"/>

		<!--[if lte IE 8]>
		  <link rel="stylesheet" href="assets/css/ace-ie.min.css" />
		<![endif]-->

		<!-- inline styles related to this page -->

		<!-- HTML5 shim and Respond.js IE8 support of HTML5 elements and media queries -->

		<!--[if lt IE 9]>
		<script src="assets/js/html5shiv.js"></script>
		<script src="assets/js/respond.min.js"></script>
		<![endif]-->
	</head>

	<body class="login-layout">
		<div class="main-container">
			<div class="main-content">
				<div class="row">
					<div class="col-sm-10 col-sm-offset-1">
						<div class="login-container">
							<div class="center">
								<h1>
									<i class="icon-laba blue"></i>
									<span class="blue">Go</span>
									<span class="blue">Login</span>
								</h1>
								<h4 class="blue">Copyright (c) 2016, 502269006@qq.com .</h4>
							</div>

							<div class="space-6"></div>

							<div class="position-relative">
								<!-- 登录模块开始 -->
								<div id="login-box" class="login-box visible widget-box no-border">
									<div class="widget-body">
										<div class="widget-main">
											<h4 class="header blue lighter bigger">
												<i class="icon-coffee green"></i>
												请输入你的账号密码
											</h4>

											<div class="space-6"></div>

											<form>
												<fieldset>
													<label class="block clearfix">
														<span class="block input-icon input-icon-right">
															<input type="text" class="form-control" placeholder="用户名" id="loginUserNo"/>
															<i class="icon-user"></i>
														</span>
													</label>

													<label class="block clearfix">
														<span class="block input-icon input-icon-right">
															<input type="password" class="form-control" placeholder="密码" id="loginUserPwd"/>
															<i class="icon-lock"></i>
														</span>
													</label>

													<div class="space"></div>

													<div class="clearfix">
														<label class="inline">
															<input type="checkbox" class="ace" />
															<span class="lbl"> 记住我</span>
														</label>

														<button type="button" class="width-35 pull-right btn btn-sm btn-primary" id="loginBtn">
															<i class="icon-key"></i>
															登录
														</button>
													</div>

													<div class="space-4"></div>
												</fieldset>
											</form>

											<div class="social-or-login center">
												<span class="bigger-110">第三方账号登录</span>
											</div>

											<div class="social-login center">
												<a class="btn btn-primary">
													<i class="icon-weibo"></i>
												</a>

												<a class="btn btn-info">
													<i class="icon-weibo"></i>
												</a>

												<a class="btn btn-danger">
													<i class="icon-weibo"></i>
												</a>
											</div>
										</div><!-- /widget-main -->

										<div class="toolbar clearfix">
											<div>
												<a href="#" onclick="show_box('forgot-box'); return false;" class="forgot-password-link">
													<i class="icon-arrow-left"></i>
													忘记密码
												</a>
											</div>

											<div>
												<a href="#" onclick="show_box('signup-box'); return false;" class="user-signup-link">
													现在注册
													<i class="icon-arrow-right"></i>
												</a>
											</div>
										</div>
									</div>
								</div>
								<!-- 登录模块结束 -->
								
								<!-- 忘记密码模块开始 -->
								<div id="forgot-box" class="forgot-box widget-box no-border">
									<div class="widget-body">
										<div class="widget-main">
											<h4 class="header red lighter bigger">
												<i class="icon-key"></i>
												忘记密码
											</h4>

											<div class="space-6"></div>
											<p>
												请输入你的邮箱地址
											</p>

											<form>
												<fieldset>
													<label class="block clearfix">
														<span class="block input-icon input-icon-right">
															<input type="email" class="form-control" name="forget_email" id="forget_email"  placeholder="邮箱" />
															<i class="icon-envelope"></i>
														</span>
													</label>

													<div class="clearfix">
														
														<label class="inline">
															<img id="imageCode" src="${contextPath }/static/image/code.png">
														</label>
														<label class="inline">
															<input type="text"  name="verify_code" id="verify_code"  placeholder="验证码" />
														</label>
														
														<button type="button" id="check_code_button" class="width-35 pull-right btn btn-sm btn-danger">
															<i class="icon-lightbulb"></i>
															验证
														</button>
													</div>
													<div class="clearfix">
														<button type="button" id="forget_button" class="width-35 pull-right btn btn-sm btn-danger">
															<i class="icon-lightbulb"></i>
															发送!
														</button>
													</div>
												</fieldset>
											</form>
										</div><!-- /widget-main -->

										<div class="toolbar center">
											<a href="#" onclick="show_box('login-box'); return false;" class="back-to-login-link">
												返回登录
												<i class="icon-arrow-right"></i>
											</a>
										</div>
									</div>
								</div>
								<!-- 忘记密码模块结束 -->

								<!-- 注册模块开始 -->
								<div id="signup-box" class="signup-box widget-box no-border">
									<div class="widget-body">
										<div class="widget-main">
											<h4 class="header green lighter bigger">
												<i class="icon-group blue"></i>
												新用户注册
											</h4>

											<div class="space-6"></div>
											<p> 请输入你的信息: </p>

											<form>
												<fieldset>
													<label class="block clearfix">
														<span class="block input-icon input-icon-right">
															<input type="email" class="form-control" name="regist_email" id="regist_email" placeholder="邮箱" />
															<i class="icon-envelope"></i>
														</span>
													</label>

													<label class="block clearfix">
														<span class="block input-icon input-icon-right">
															<input type="text" class="form-control" name="regist_user_no" id="regist_user_no" placeholder="用户名" />
															<i class="icon-user"></i>
														</span>
													</label>

													<label class="block clearfix">
														<span class="block input-icon input-icon-right">
															<input type="password" class="form-control" name="regist_user_pwd" id="regist_user_pwd" placeholder="密码" />
															<i class="icon-lock"></i>
														</span>
													</label>

													<label class="block clearfix">
														<span class="block input-icon input-icon-right">
															<input type="password" class="form-control" name="repeat_regist_user_pwd" id="repeat_regist_user_pwd" placeholder="重复密码" />
															<i class="icon-retweet"></i>
														</span>
													</label>

													<label class="block">
														<input type="checkbox" class="ace" />
														<span class="lbl">
															我同意
															<a href="#">注册协议</a>
														</span>
													</label>

													<div class="space-24"></div>

													<div class="clearfix">
														<button type="reset" class="width-30 pull-left btn btn-sm">
															<i class="icon-refresh"></i>
															重置
														</button>

														<button type="button" id="regist_button" class="width-65 pull-right btn btn-sm btn-success">
															注册
															<i class="icon-arrow-right icon-on-right"></i>
														</button>
													</div>
												</fieldset>
											</form>
										</div>

										<div class="toolbar center">
											<a href="#" onclick="show_box('login-box'); return false;" class="back-to-login-link">
												<i class="icon-arrow-left"></i>
												返回登录
											</a>
										</div>
									</div>
								</div>
								<!-- 注册模块结束 -->
							</div><!-- /position-relative -->
						</div>
					</div><!-- /.col -->
				</div><!-- /.row -->
			</div>
		</div><!-- /.main-container -->

		<!--JQuery -->
		<script type="text/javascript" src="${contextPath }/static/js/bootstrap/jquery.js"></script>

		<!-- inline scripts related to this page -->

		<script type="text/javascript">
			function show_box(id) {
			 jQuery('.widget-box.visible').removeClass('visible');
			 jQuery('#'+id).addClass('visible');
			}
			
			$("#loginBtn").click(function() {
			
				var userNo = $("#loginUserNo").val();
				var usrPwd = $("#loginUserPwd").val();
								
				var url = "login_login";
				var params = {
					USER_NO : userNo,
					usrPwd : usrPwd
				};
				$.getJSON(url, params, function(data) {
					alert(data.USER_PWD);
				
				});
			
			});
			
			$("#regist_button").click(function() {
			
				var registUserEmail = $("#regist_email").val();
				var registUserNo = $("#regist_user_no").val();
								
				var url = "redisTest";
				var params = {
					regist_user_email : registUserEmail,
					regist_user_no : registUserNo
				};
				$.getJSON(url, params, function(data) {
					alert(data.regist_user_no);
				
				});
			
			});
			
			$("#forget_button").click(function() {
			
				var forgetUserEmail = $("#forget_email").val();
								
				var url = "cookieTest";
				var params = {
					forget_user_email : forgetUserEmail,
				};
				$.getJSON(url, params, function(data) {
					alert(data.cookie_string);				
				});
			
			});	
			
			$("#imageCode").click(function() {
				$("#imageCode").attr("src", "imageCode.img?d=" + new Date().getTime());
			}); 
			
			$("#check_code_button").click(function() {
				var verifyCode = $("#verify_code").val();
				var url = "checkCookie";
				var params = {
					verify_code : verifyCode
				}
				$.getJSON(url, params, function(data) {
					alert(data.result_info);				
				});
			});
		</script>
		
		
</body>
</html>
