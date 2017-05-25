<%@page contentType="text/html; charset=UTF-8" session="false" 
%><%@include  file="/WEB-INF/jspf/import.jspf"
%><form id="pagerForm" method="get" action="">
<input type="hidden" name="pageNum" value="${pager.pageNum}" />
<input type="hidden" name="numPerPage" value="25" />
<input type="hidden" name="orderField" value="${orderField}" />
<input type="hidden" name="orderDirection" value="${orderDirection}" />
</form>
<div class="pageHeader">
	<form rel="pagerForm" onsubmit="return navTabSearch(this);" action="${ctx}/admin/template/list.do" method="get">
	<div class="searchBar">
		<table class="searchContent"> 
				<tbody>
					<tr>
						<td>ID：<input type="text" name="templateId"
							class="textInput digits" value="${param.id}">
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
<li><a class="add"  href="${ctx}/admin/template/create.do" target="dialog" rel="dlg-edit-area" title="添加短信模板"><span>添加</span></a></li>
<li><a class="edit" href="${ctx}/admin/template/update.do?id={id}"  target="dialog" rel="dlg-edit-area" title="修改短信模板"><span>修改</span></a></li> 
<li><a class="icon" href="${ctx}/admin/template/query.do?id={id}" target="ajaxTodo" title="确定查询吗?"><span>查询</span></a></li>
<li><a class="icon" href="${ctx}/admin/template/delete.do?id={id}" target="ajaxTodo" title="确定删除吗?"><span>删除</span></a></li>
<li class="line">line</li></ul>
</div>
<table class="table" style="width: 100%" layoutH="138">
<thead>
<tr>
   <th width="2%"><input type="checkbox" class="checkboxCtrl" group="ids"></th>
   <th orderField="id" class="${orderField=='id' ? orderDirection : ''}">ID</th>
   <th>模板Id</th>
   <th>商户ID</th>
   <th>模板类型</th>
   <th>签名</th>
   <th>内容</th>
   <th>审核状态</th>
   <th>启用状态</th>
   <th>邮箱</th>
   <th>昵称</th>
   <th>创建时间</th>
</tr>
</thead>
<tbody>
<c:forEach var="entity" items="${pager.list}">
<tr target="id" rel="${entity.id}">
<td><input name="ids" value="${entity.id}" type="checkbox"></td>
<td>${entity.id}</td>
<td>${entity.templateId}</td>
<td>${entity.agentId}</td>
<td>${entity.typeDesc}</td>
<td>${entity.signature}</td>
<td>${entity.content}</td>
<td>${entity.auditDesc}</td>
<td>${entity.enableDesc}</td>
<td>${entity.senderMailbox}</td>
<td>${entity.senderNickname}</td>
<td><fmt:formatDate value="${entity.createTime}" pattern="yyyy-MM-dd HH:mm:ss" /></td>
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
