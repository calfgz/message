<%@page contentType="text/html" pageEncoding="utf-8" session="false"
%><!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<meta http-equiv="X-UA-Compatible" content="IE=7" />
<title>华宽科技短信平台管理系统</title>

<link href="../themes/default/style.css" rel="stylesheet" type="text/css" media="screen"/>
<link href="../themes/css/core.css" rel="stylesheet" type="text/css" media="screen"/>
<link href="../themes/css/print.css" rel="stylesheet" type="text/css" media="print"/>
<!--[if IE]>
<link href="../themes/css/ieHack.css" rel="stylesheet" type="text/css" media="screen"/>
<![endif]-->

<script src="../js/speedup.js" type="text/javascript"></script>
<script src="../js/jquery-2.1.4.js" type="text/javascript"></script>
<script src="../js/jquery.cookie.js" type="text/javascript"></script>
<script src="../js/jquery.validate.js" type="text/javascript"></script>
<script src="../js/jquery.bgiframe.js" type="text/javascript"></script>

<script src="../js/dwz.core.js" type="text/javascript"></script>
<script src="../js/dwz.util.date.js" type="text/javascript"></script>
<script src="../js/dwz.validate.method.js" type="text/javascript"></script>
<script src="../js/dwz.regional.zh.js" type="text/javascript"></script>
<script src="../js/dwz.barDrag.js" type="text/javascript"></script>
<script src="../js/dwz.drag.js" type="text/javascript"></script>
<script src="../js/dwz.tree.js" type="text/javascript"></script>
<script src="../js/dwz.accordion.js" type="text/javascript"></script>
<script src="../js/dwz.ui.js" type="text/javascript"></script>
<script src="../js/dwz.theme.js" type="text/javascript"></script>
<script src="../js/dwz.switchEnv.js" type="text/javascript"></script>
<script src="../js/dwz.alertMsg.js" type="text/javascript"></script>
<script src="../js/dwz.contextmenu.js" type="text/javascript"></script>
<script src="../js/dwz.navTab.js" type="text/javascript"></script>
<script src="../js/dwz.tab.js" type="text/javascript"></script>
<script src="../js/dwz.resize.js" type="text/javascript"></script>
<script src="../js/dwz.dialog.js" type="text/javascript"></script>
<script src="../js/dwz.dialogDrag.js" type="text/javascript"></script>
<script src="../js/dwz.sortDrag.js" type="text/javascript"></script>
<script src="../js/dwz.cssTable.js" type="text/javascript"></script>
<script src="../js/dwz.stable.js" type="text/javascript"></script>
<script src="../js/dwz.taskBar.js" type="text/javascript"></script>
<script src="../js/dwz.ajax.js" type="text/javascript"></script>
<script src="../js/dwz.pagination.js" type="text/javascript"></script>
<script src="../js/dwz.database.js" type="text/javascript"></script>
<script src="../js/dwz.datepicker.js" type="text/javascript"></script>
<script src="../js/dwz.effects.js" type="text/javascript"></script>
<script src="../js/dwz.panel.js" type="text/javascript"></script>
<script src="../js/dwz.checkbox.js" type="text/javascript"></script>
<script src="../js/dwz.history.js" type="text/javascript"></script>
<script src="../js/dwz.combox.js" type="text/javascript"></script>
<script src="../js/dwz.print.js" type="text/javascript"></script>
<!--
<script src="../bin/dwz.min.js" type="text/javascript"></script>
-->
<script src="../js/dwz.regional.zh.js" type="text/javascript"></script>

<script src="http://cdn.hcharts.cn/highcharts/highcharts.js"></script>
<script src="http://cdn.hcharts.cn/highcharts/modules/exporting.js"></script>

<script type="text/javascript">
$(function(){
	DWZ.init("../dwz.frag.xml", {
		loginUrl:"login.jsp",
		statusCode:{ok:200, error:300, timeout:301},
		pageInfo:{pageNum:"pageNum", numPerPage:"numPerPage", orderField:"orderField", orderDirection:"orderDirection"},
		debug:false,
		callback:function(){
			initEnv();
			$("#themeList").theme({themeBase:"../themes"});
		}
	});
});

</script>
</head>

<body scroll="no">
	<div id="layout">
		<div id="header">
			<div class="headerNav">
                <%-- <a class="logo" href="${ctx}/admin/index.jsp" style="width: 100px;">logo</a> --%>
                <div style="float: left; color: white; font-size: 28px; padding-top: 15px;">华宽科技短信平台管理系统</div>
				<ul class="nav">
					<li><a href="javascript:;">welcome, ${_USER_.name}!</a></li>
					<li><a href="${ctx}/admin/logout.do">logout</a></li>
				</ul>
				<ul class="themeList" id="themeList">
					<li theme="default"><div class="selected"></div></li>
					<li theme="green"><div></div></li>
					<li theme="purple"><div></div></li>
					<li theme="silver"><div></div></li>
					<li theme="azure"><div></div></li>
				</ul>
			</div>
		</div>

		<div id="leftside">
			<div id="sidebar_s">
				<div class="collapse">
					<div class="toggleCollapse"><div></div></div>
				</div>
			</div>
			<div id="sidebar">
				<div class="toggleCollapse"><h2>主菜单</h2><div>-</div></div>

				<div class="accordion" fillSpace="sidebar">
					<div class="accordionHeader">
						<h2><span>Folder</span>个人设置</h2>
					</div>
					<div class="accordionContent">
						<ul class="tree treeFolder">
							<li><a href="${ctx}/admin/password.do" target="navTab" rel="list-edit-password" title="修改密码">修改密码</a></li>
							<li><a href="${ctx}/admin/send.do" target="navTab" rel="list-edit-send" title="短信发送"><span>短信发送</span></a></li>
							<li><a href="${ctx}/admin/userinfo.do" target="navTab" rel="list-edit-userinfo" title="账号信息"><span>查询账号信息</span></a></li>
						</ul>
					</div>
					<div class="accordionHeader">
						<h2><span>Folder</span>商户数据</h2>
					</div>
					<div class="accordionContent">
						<ul class="tree treeFolder">
							<li><a href="${ctx}/admin/agent/list.do" target="navTab" rel="list-agent">商户列表</a></li>
							<li><a href="${ctx}/admin/recharge/list.do" target="navTab" rel="list-recharge">充值记录</a></li>
							<li><a href="${ctx}/admin/agentlog/list.do" target="navTab" rel="list-agentlog">客户日志</a></li>
						</ul>
					</div>
					<div class="accordionHeader">
						<h2><span>Folder</span>模板管理</h2>
					</div>
					<div class="accordionContent">
						<ul class="tree treeFolder">
							<li><a href="${ctx}/admin/template/list.do" target="navTab" rel="list-template">模板列表</a></li>
							<li><a href="${ctx}/admin/template/send.do" target="navTab" rel="send-template">模板短信发送</a></li>
						</ul>
					</div>
					<div class="accordionHeader">
						<h2><span>Folder</span>上行短信</h2>
					</div>
					<div class="accordionContent">
						<ul class="tree treeFolder">
							<li><a href="${ctx}/admin/notice/list.do" target="navTab" rel="list-template">上行短信</a></li>
						</ul>
					</div>
					<div class="accordionHeader">
						<h2><span>Folder</span>短信数据</h2>
					</div>
					<div class="accordionContent">
						<ul class="tree treeFolder">
							<li><a href="${ctx}/admin/sendrecord/list.do" target="navTab" rel="list-sendrecord">已发送列表</a></li>
							<li><a href="${ctx}/admin/recvrecord/list.do" target="navTab" rel="list-recvrecord">待发送列表</a></li>
							<li><a href="${ctx}/admin/failrecord/list.do" target="navTab" rel="list-failrecord">发送失败列表</a></li>
						</ul>
					</div>
					<div class="accordionHeader">
						<h2><span>Folder</span>统计报表</h2>
					</div>
					<div class="accordionContent">
						<ul class="tree treeFolder">
							<li><a href="${ctx}/admin/counter/dailychat.do" target="navTab" rel="list-dailycounter">总日记报表</a></li>
							<li><a href="${ctx}/admin/counter/daily.do" target="navTab" rel="list-daily">每日报表</a></li>
							<li><a href="${ctx}/admin/counter/monthlychat.do" target="navTab" rel="list-monthlycounter">总月记报表</a></li>
							<li><a href="${ctx}/admin/counter/monthly.do" target="navTab" rel="list-monthly">每月报表</a></li>
						</ul>
					</div>
                    <div class="accordionHeader">
						<h2><span>Folder</span>权限设置</h2>
					</div>
                    <div class="accordionContent">
						<ul class="tree treeFolder">
							<li><a href="${ctx}/admin/user/list.do" target="navTab" rel="list-user">用户列表</a></li>
							<li><a href="${ctx}/admin/log/list.do" target="navTab" rel="list-log">操作日志</a></li>
							<li><a href="${ctx}/admin/passageway/list.do" target="navTab" rel="list-passageway">通道列表</a></li>
						</ul>
					</div>
				</div>
			</div>
		</div>

		<div id="container">
			<div id="navTab" class="tabsPage">
				<div class="tabsPageHeader">
					<div class="tabsPageHeaderContent">
						<ul class="navTab-tab">
							<li tabid="main" class="main"><a href="javascript:;"><span><span class="home_icon">我的主页</span></span></a></li>							
						</ul>
					</div>
					<div class="tabsLeft">left</div>
					<div class="tabsRight">right</div>
					<div class="tabsMore">more</div>
				</div>
				<ul class="tabsMoreList">
					<li><a href="javascript:;">more</a></li>
				</ul>
				<div class="navTab-panel tabsPageContent layoutBox">
					<div class="page unitBox">
						<div class="pageFormContent" layoutH="80" style="margin-right:230px">
                            这里缺应用的个人主页内容，请补充。<br></br>
                        </div>
					</div>
				</div>
			</div>
		</div>

	</div>
</body>
</html>
