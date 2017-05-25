<%@page contentType="text/html; charset=UTF-8" session="false" 
%><%@include  file="/WEB-INF/jspf/import.jspf"
%><form id="pagerForm" method="get" action="">
<input type="hidden" name="pageNum" value="${pager.pageNum}" />
<input type="hidden" name="numPerPage" value="25" />
<input type="hidden" name="orderField" value="${orderField}" />
<input type="hidden" name="orderDirection" value="${orderDirection}" />
</form>
<div class="pageContent">
<div class="panelBar">
<ul class="toolBar">
<c:if test="${isAdmin}">
<li><a class="add"  href="${ctx}/admin/passageway/create.do" target="dialog" rel="dlg-edit-area" title="添加商户"><span>添加</span></a></li>
<li><a class="edit" href="${ctx}/admin/passageway/update.do?id={id}"  target="dialog" rel="dlg-edit-area" title="修改通道"><span>修改</span></a></li>
</c:if> 
<li><a class="icon" href="${ctx}/admin/passageway/status.do?status=1" rel="ids" target="selectedTodo" title="确定启用该通道吗?"><span>启用</span></a></li>
<li><a class="icon" href="${ctx}/admin/passageway/status.do?status=0" rel="ids" target="selectedTodo" title="确定锁定该该通吗?"><span>锁定</span></a></li>
<li class="line">line</li></ul>
</div>
<table class="table" style="width: 100%" layoutH="75">
<thead>
<tr>
   <th width="2%"><input type="checkbox" class="checkboxCtrl" group="ids"></th>
   <th orderField="id" class="${orderField=='id' ? orderDirection : ''}">通道ID</th>
   <th>通道名</th>
   <th>移动</th>
   <th>联通</th>
   <th>电信</th>
   <th>状态</th>
   <th>创建时间</th>
   <th>更新时间</th>
</tr>
</thead>
<tbody>
<c:forEach var="entity" items="${pager.list}">
<tr target="id" rel="${entity.id}">
<td><input name="ids" value="${entity.id}" type="checkbox"></td>
<td>${entity.id}</td>
<td>${entity.name}</td>
<td>${entity.cmcc}</td>
<td>${entity.cucc}</td>
<td>${entity.ctcc}</td>
<td>${entity.status}</td>
<td><fmt:formatDate value="${entity.createAt}" pattern="yyyy-MM-dd HH:mm:ss" /></td>
<td><fmt:formatDate value="${entity.updateAt}" pattern="yyyy-MM-dd HH:mm:ss" /></td>
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
