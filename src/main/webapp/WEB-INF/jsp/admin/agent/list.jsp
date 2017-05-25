<%@page contentType="text/html; charset=UTF-8" session="false" 
%><%@include  file="/WEB-INF/jspf/import.jspf"
%><form id="pagerForm" method="get" action="">
<input type="hidden" name="pageNum" value="${pager.pageNum}" />
<input type="hidden" name="numPerPage" value="25" />
<input type="hidden" name="orderField" value="${orderField}" />
<input type="hidden" name="orderDirection" value="${orderDirection}" />
</form>
<div class="pageHeader">
	<form rel="pagerForm" onsubmit="return navTabSearch(this);" action="${ctx}/admin/agent/list.do" method="get">
	<div class="searchBar">
		<table class="searchContent"> 
				<tbody>
					<tr>
						<td>ID：<input type="text" name="agentId"
							class="textInput digits" value="${param.agentId}">
						</td>
						<td>商户名：<input type="text" name="account" value="${param.account}" class="textInput"/>
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
<li><a class="add"  href="${ctx}/admin/agent/create.do" target="dialog" rel="dlg-edit-area" title="添加商户"><span>添加</span></a></li>
<li><a class="edit" href="${ctx}/admin/agent/update.do?id={id}"  target="dialog" rel="dlg-edit-area" title="修改商户"><span>修改</span></a></li> 
<li><a class="icon" href="${ctx}/admin/agent/status.do?status=0" rel="ids" target="selectedTodo" title="确定设为正常吗?"><span>通过</span></a></li>
<li><a class="icon" href="${ctx}/admin/agent/status.do?status=2" rel="ids" target="selectedTodo" title="确定锁定该商户吗?"><span>锁定</span></a></li>
<li><a class="icon" href="${ctx}/admin/agent/recharge.do?id={id}"  target="dialog" rel="dlg-edit-recharge" title="商户充值"><span>商户充值</span></a></li> 
<li><a class="icon" href="${ctx}/admin/agent/detail.do?id={id}"  target="navTab" rel="detail-agent" title="客户详情"><span>查看详情</span></a></li> 
<li class="line">line</li></ul>
</div>
<table class="table" style="width: 100%" layoutH="138">
<thead>
<tr>
   <th width="2%"><input type="checkbox" class="checkboxCtrl" group="ids"></th>
   <th orderField="id" class="${orderField=='id' ? orderDirection : ''}">ID</th>
   <th>accId</th>
   <th orderField="account" class="${orderField=='account' ? orderDirection : ''}">商户账号</th>
   <th>商户名</th>
   <th>邮箱</th>
   <th>手机号</th>
   <th>状态</th>
   <th>余额</th>
   <th>赠送余额</th>
   <th>更新时间</th>
   <th>登录时间</th>
</tr>
</thead>
<tbody>
<c:forEach var="entity" items="${pager.list}">
<tr target="id" rel="${entity.id}">
<td><input name="ids" value="${entity.id}" type="checkbox"></td>
<td>${entity.id}</td>
<td>${entity.accid}</td>
<td>${entity.account}</td>
<td>${entity.name}</td>
<td>${entity.email}</td>
<td>${entity.mobile}</td>
<td>${entity.statusDesc}</td>
<td>${entity.balance}</td>
<td>${entity.givenBalance}</td>
<td><fmt:formatDate value="${entity.updateAt}" pattern="yyyy-MM-dd HH:mm:ss" /></td>
<td><fmt:formatDate value="${entity.loginAt}" pattern="yyyy-MM-dd HH:mm:ss" /></td>
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
