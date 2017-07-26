<%@ page language="java" contentType="text/html; charset=UTF-8"  pageEncoding="UTF-8"%>
<%@ page import= "com.zq.commons.utils.UIUtils" %>
<%
	String pageTitle = request.getParameter("pageTitle");
	String appid = request.getParameter("appid");
	String pid = request.getParameter("pid");
	String url = request.getParameter("url");
%>
<body>
	<%-- 输出系统顶级模块的菜单 --%>
	<jsp:include page="../common/TopMenu.jsp">
		<jsp:param name="appid" value="<%= appid %>" />
		<jsp:param name="url" value="<%= url %>" />
		<jsp:param name="pid" value="<%= pid %>" />
	</jsp:include>
	<div class="container" id="bodyStart__container">
	<%-- 输出侧边栏菜单--%>
	<jsp:include page="../common/LeftMenu.jsp" >	
		<jsp:param name="appid" value="<%= appid %>" />
		<jsp:param name="pid" value="<%= pid %>" />
		<jsp:param name="url" value="<%= url %>" />
	</jsp:include>	
		<div class="content-wrap" id="bodyStart__content_Wrap">
			<div class="content-empty"></div>
			<div class="newTitleDIV"><%= pageTitle %>

			<!-- 以下为内容部分 -->