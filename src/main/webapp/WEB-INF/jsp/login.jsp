<%@ page language="java" contentType="text/html; charset=UTF-8"	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
<head><title>项目全流程</title>
<link rel="icon" href="favicon.ico" type="image/x-icon">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<link href="/static/css/style_new.css" rel="stylesheet" type="text/css">
<link href="/static/css/style.css" rel="stylesheet" type="text/css">
<script type="text/javascript" src="/static/js/jquery/jquery-1.7.2.min.js"></script>
<script type="text/javascript">
$(function(){
    $("#btn-login").click(function(){
       $("#frm").submit();
       alert("提交中");
    });
});
</script>
</head>
<body>
	<form name="frm" id="frm" action="/login" method="post">	
        <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}"/>
		<div class="login-top">
			<div style="float: right; width: 120px; height: 100%;"></div>
		</div>
		<div id="logininner">
			<div class="login-top-content">
				<div id="login-top-content-txt" class="login-top-content-txt">登录</div>
			</div>
			<div class="login-center-content">
				<div class="reg-content">
					<span style="color:red"><c:out value="${message}"/></span>							
					<div class="loginSpan" style="  margin-top: 54px;">
						<div class="login_icon ico_user"></div>
						<input type="text" placeholder="用户" title='用户' name="userName" id="userName" class="loginInput ">
					</div>
					<div class="loginSpan" >
						<div class="login_icon ico_lock "></div>
						<input type="password" title='密码'  placeholder="密码"name="password" id="password" class="loginInput ">
					</div>
					<div class="loginButton">
						<div id="btn-login" class="btn-save">登 录</div>
					</div>
					<div>
						<span class="reg-checkbox">
							<input type="checkbox"name="keepLogin" tabindex="5" id="keepLogin">
						</span> 
						<span class="reg-check_text">在本机记录登录信息</span>
						<div style="clear: both;"></div>
					</div>
				</div>

				<div class="login-content-split">
					<div class="login-content-split-blank"></div>
					<div class="login-content-split-line"></div>
					<div class="login-content-split-blank"></div>
				</div>
				<div class="login-right-content">
					<div style="height: 380px; width: 380px;"class="login-right-contentChild "></div>
				</div>
				<div style="clear: both;"></div>
			</div>
			<div class="reg-copy "></div>
		</div>	
		</form>
</body>
</html>