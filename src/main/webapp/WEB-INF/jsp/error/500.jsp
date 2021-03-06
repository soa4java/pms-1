<%--标签 --%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ page trimDirectiveWhitespaces="true" isELIgnored="false"
	session="false"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="shiro" uri="http://shiro.apache.org/tags"%>
<%--ctxPath--%>
<c:set var="ctxPath" value="${pageContext.request.contextPath}" />
<%--项目路径 --%>
<c:set var="path" value="${ctxPath}" />
<%--静态文件目录 --%>
<c:set var="staticPath" value="${ctxPath}" />
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>500-系统内部错误</title>
</head>
<body>
	<div>错误代码：500</div>
	<div>错误描述：系统内部错误</div>
	<div>${error }</div>
</body>
</html>