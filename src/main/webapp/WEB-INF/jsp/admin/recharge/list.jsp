<%@page contentType="text/html; charset=UTF-8" session="false" 
%><%@include  file="/WEB-INF/jspf/import.jspf"
%><form id="pagerForm" method="get" action="">
<input type="hidden" name="pageNum" value="${pager.pageNum}" />
<input type="hidden" name="numPerPage" value="25" />
<input type="hidden" name="orderField" value="${orderField}" />
<input type="hidden" name="orderDirection" value="${orderDirection}" />
</form>
<div class="pageHeader">
	<form rel="pagerForm" onsubmit="return navTabSearch(this);" action="${ctx}/admin/recharge/list.do" method="get">
	<div class="searchBar">
		<table class="searchContent"> 
				<tbody>
					<tr>
						<td>商户Id：<input type="text" name="agentIdd" value="${param.agentId}" class="textInput"/>
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
<li class="line">line</li></ul>
</div>
<table class="table" style="width: 100%" layoutH="138">
<thead>
<tr>
   <th width="2%"><input type="checkbox" class="checkboxCtrl" group="ids"></th>
   <th>ID</th>
   <th>商户Id</th>
   <th>充值金额</th>
   <th>赠送金额</th>
   <th>充值用户</th>
   <th>说明</th>
   <th>充值时间</th>
</tr>
</thead>
<tbody>
<c:forEach var="entity" items="${pager.list}">
<tr target="id" rel="${entity.id}">
<td><input name="ids" value="${entity.id}" type="checkbox"></td>
<td>${entity.id}</td>
<td>${entity.agentId}</td>
<td>${entity.balance}</td>
<td>${entity.givenBalance}</td>
<td>${entity.userId}</td>
<td>${entity.intro}</td>
<td><fmt:formatDate value="${entity.createAt}" pattern="yyyy-MM-dd HH:mm:ss" /></td>
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
