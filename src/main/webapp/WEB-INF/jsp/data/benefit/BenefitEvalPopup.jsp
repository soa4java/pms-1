<%@page import="org.apache.commons.io.filefilter.FalseFileFilter"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"  pageEncoding="UTF-8" import="java.util.*"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import= "com.zq.commons.utils.UIUtils" %>
<%@ page import= "com.zq.commons.result.PageInfo" %>
<%
	String path = request.getContextPath();
	String pageTitle = (String)request.getAttribute("pageTitle");
	Integer appid = (Integer)request.getAttribute("appid");
	Long pid = (Long)request.getAttribute("pid");
	String url = (String)request.getAttribute("url");
	PageInfo pageInfo = (PageInfo)request.getAttribute("pageInfo");
	
	int nameWidth = (760-30-180-80-50-100-(true?160:0)-10); //是否是后评估，这里还没有变量，先写成true
%>

<script type="text/javascript" src="/static/js/firefox/FloatAndDialog.js"></script>
<script type="text/javascript" src="<%=path%>/js/firefox/DataTableAll.js"></script>
<script type="text/javascript" src="<%=path%>/js/firefox/contentDIV.js"></script>
<script type="text/javascript">
//效益评估
function openFormScorecard() {
	document.frm.action = "<%=path%>/datamap/simpleFromBenefit";
	etSubmit(document.frm);
};

//更多
function showMoreButton (obj){
	var listMenu = new Array();
	listMenu.items = new Array();
	var i=0;
	listMenu.items[i] = new Array();
	listMenu.items[i]["type"] 		= 'item';
	listMenu.items[i]["function"]	= 'logFun()';
	listMenu.items[i]["label"]		= '日志';
	i++;
	listMenu.items[i] = new Array();
	listMenu.items[i]["type"] 		= 'item';
	listMenu.items[i]["function"]	= 'exportExcel()';
	listMenu.items[i]["label"]		= '导出到 MS Excel';
	popupMenu(obj, listMenu, "");
}

//日志
function logFun(){
	var src= "<%=path%>/datamap/logPopup?pageTitle=<%= pageTitle %>&url=<%= url%>"+"&_id="+Math.random();
	var arg = {};
	arg.src=src;
	arg.title='日志';
	arg.width = 840;
	arg.height = top.document.body.clientHeight - 160;
	top.ET.showModalWindow(arg)
}

//编辑
function edit() {	
	/* SimpleFormAction.checkModified(document.frm.schemaID.value, document.frm.formID.value, document.frm.updateTime.value, function(data) {
		var errNo = data.substring(0,1);
		if(errNo == "0"){
			alert(data.substring(1));
			parent.ET.closeModalWindow("1");
		} else if(errNo == "1"){
			alert(data.substring(1));		
			document.frm.operation.value="open";
			etSubmit(document.frm);
		}else if(errNo == "2"){
			alert(data.substring(1));		
		} else {
			document.frm.operation.value="edit";
			etSubmit(document.frm);
		}
	}); */
	document.frm.operation.value="edit";
	etSubmit(document.frm);
}
//解锁
function unlock() {
	console.log('点击了...');
	/* treeSelectUtils.showUserSelect(this.children[2],this.children[3],role,role_id,true); */
	var arg = new Array();
	if(isMulti){
		arg.width = 630;
		arg.height =430;
	}else{
		arg.width = 630;
		arg.height = 430;
	}
	parent.ET.showModalWindow(arg, function (oRet){
		/* if (oRet!=null) {
			if(typeof idField!='undefined' && idField!=null){
				idField.value=oRet.userIds;
			}
			if(typeof nameField!='undefined' && nameField!=null){
				nameField.value=oRet.userNames;
			}
		}
		if(typeof(parent.onSelectUserOKFun)!='undefined'&&typeof(funtion)=='undefined'){
			parent.onSelectUserOKFun(idField);
		}else if(typeof(funtion)!='undefined'){
			funtion(idField);
		} */
	});
}

<%-- function showMoreButton (obj){
	var listMenu = new Array();
	listMenu.items = new Array();
	var i=0;
	<%if (!isEdit) {%>
		<%if(isVersionSupport){%>
			<%if(lineList.size()>0){ %>
				listMenu.items[i] = new Array();
				listMenu.items[i]["type"] 		= 'item';
				listMenu.items[i]["function"]	= 'compareVersionFun()';
				listMenu.items[i]["label"]		= '版本比较';
				i++;
			<%} %>
		<%}else if(schema.getId()==CMCCConstant.FEE_DETAIL||schema.getId()==CMCCConstant.ZICHAN_DETAIL){%>
			listMenu.items[i] = new Array();
			listMenu.items[i]["type"] 		= 'item';
			listMenu.items[i]["function"]	= 'compareFeedVersionFun()';
			listMenu.items[i]["label"]		= '跟原始版本比较';
			i++;
		<%}%>
		
		<%if(isLixiang&&wfSchema!=null){%>
			
			listMenu.items[i] = new Array();
			listMenu.items[i]["type"] 		= 'item';
			listMenu.items[i]["function"]	= 'logFun()';
			listMenu.items[i]["label"]		= '日志';
			i++;
		<%}%>
		listMenu.items[i] = new Array();
		listMenu.items[i]["type"] 		= 'item';
		listMenu.items[i]["function"]	= 'exportExcel()';
		listMenu.items[i]["label"]		= '<bean:message key="outAsExcelFun"/>';
	<%} %>
	popupMenu(obj, listMenu, "");
} --%>
</script>

<%-- 输出Head1模块 --%>
<jsp:include page="../../common/Head1.jsp" />

<!-- 内容主体 -->
<form name="frm" action="<%=path%>/datamap/benefitEvalPopup" method="post">
<input type="hidden" name="pageTitle" value="<%= pageTitle %>">
<input type="hidden" name="url" value="<%= url %>">
<input type="hidden" name="operation" value="edit">

<%=UIUtils.toolbarStart(request)%>
<%=UIUtils.toolbarButton(true, "javascript:edit(\"1\")", "编辑", "save.gif", false, false, request)%>
<%=UIUtils.toolbarButton(true, "javascript:unlock();", "解锁", "back.gif", false, false, request)%>

<%=UIUtils.toolbarFloatRight(request)%>
	<%=UIUtils.toolbarButton(true, "javascript:showMoreButton(this);","更多", null, false, false, request)%>
<%=UIUtils.toolbarEnd(request)%>

<%=UIUtils.formBodyStart(request) %>
<table style="border:0;align:center;cellpadding:0;cellspacing:0" class='formTable' id="fieldTable">
	
</table>
<!-- 效益评估 -->
<jsp:include page="./BenefitInclude.jsp"/>
<!--附件 -->
<jsp:include page="./AttachInclude.jsp"/>
<!--工作流 -->
<jsp:include page="./WorkFlowInclude.jsp"/>
<%=UIUtils.formBodyEnd(request) %> 

<!-- 以上为表单 -->
</form>

<%-- 输出公共BodyEnd模块 --%>
<jsp:include page="../../common/BodyEnd.jsp" />
