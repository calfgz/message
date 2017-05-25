<%@page contentType="text/html; charset=UTF-8" session="false" 
%><%@include  file="/WEB-INF/jspf/import.jspf"
%><form id="pagerForm" method="get" action="">
<input type="hidden" name="pageNum" value="${pager.pageNum}" />
<input type="hidden" name="numPerPage" value="25" />
</form>
<div class="pageHeader">
	<form rel="pagerForm" onsubmit="return navTabSearch(this);" action="${ctx}/admin/sendrecord/list.do" method="get">
	<div class="searchBar">
		<table class="searchContent"> 
				<tbody>
					<tr>
						<td>ID：<input type="text" name="smsid"
							class="textInput" value="${param.smsid}">
						</td>
						<td>商户id：<input type="text" name="agentId" value="${param.agentId}" class="textInput"/>
						</td>
					</tr>
				</tbody>
			</table>
		<div class="subBar">
			<ul>
				<li><div class="buttonActive"><div class="buttonContent"><button type="submit">检索</button></div></div></li>				
			</ul>
		</div>
	</div>
	</form>
</div>
<div class="pageContent">
<div class="panelBar">
<ul class="toolBar">
<li><a class="icon" href="${ctx}/admin/sendrecord/detail.do?id={id}"  target="navTab" rel="detail-sendrecord" title="短信详情"><span>查看详情</span></a></li> 
<li class="line">line</li></ul>
</div>
<table class="table" style="width: 100%" layoutH="138">
<thead>
<tr>
   <th width="2%"><input type="checkbox" class="checkboxCtrl" group="ids"></th>
   <th>Id</th>
   <th>smsId</th>
   <th>商户id</th>
   <th>模板id</th>
   <th>类型</th>
   <th>查询状态</th>
   <th>手机号</th>
   <th>短信内容</th>
   <th>成功数</th>
   <th>失败数</th>
   <th>回执状态</th>
   <th>回执信息</th>
   <th>发送时间</th>
</tr>
</thead>
<tbody>
<c:forEach var="entity" items="${pager.list}">
<tr target="id" rel="${entity.id}">
<td><input name="ids" value="${entity.id}" type="checkbox"></td>
<td>${entity.id}</td>
<td>${entity.smsid}</td>
<td>${entity.agentId}</td>
<td>${entity.templateId}</td>
<td>${entity.typeDesc}</td>
<td>${entity.queryStatusDesc}</td>
<td>${fn:substring(entity.phones, 0, 23)}</td>
<td>${fn:substring(entity.content, 0, 40)}</td>
<td>${entity.count}</td>
<td>${entity.failCount}</td>
<td>${entity.respStatusDesc}</td>
<td>${entity.respMessage}</td>
<td><fmt:formatDate value="${entity.sendAt}" pattern="yyyy-MM-dd HH:mm:ss" /></td>
</tr>
</c:forEach> 
</tbody>
</table>
</div>

<div class="panelBar">
<div class="pages">
        <span>显示</span>
        <select class="combox" name="numPerPage" onchange="navTabPageBreak({numPerPage: this.value});">
            <option value="25" ${25 == param.numPerPage ? "selected" : ""}>25</option>
            <option value="50" ${50 == param.numPerPage ? "selected" : ""}>50</option>
            <option value="100" ${100 == param.numPerPage ? "selected" : ""}>100</option>
        </select>
        <span>共 ${pager.total} 条</span>
</div>
<div class="pagination" targetType="navTab" totalCount="${pager.total}" numPerPage="${pager.pageSize}" pageNumShown="10" currentPage="${pager.pageNum}"></div>
</div>
