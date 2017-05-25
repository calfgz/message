<%@page contentType="text/html; charset=UTF-8" session="false" 
%><%@include  file="/WEB-INF/jspf/import.jspf"
%><form id="pagerForm" method="get" action="">
<input type="hidden" name="pageNum" value="${pager.pageNum}" />
<input type="hidden" name="numPerPage" value="25" />
</form>
<div class="pageContent">
<div class="panelBar">
<ul class="toolBar">
<li><a class="add"  href="${ctx}/admin/user/create.do" target="dialog" rel="dlg-edit-area" title="添加管理员"><span>添加</span></a></li>
<li class="line">line</li></ul>
</div>
<table class="table" style="width: 100%" layoutH="75">
<thead>
<tr>
   <th width="2%"><input type="checkbox" class="checkboxCtrl" group="ids"></th>
   <th orderField="id" class="${orderField=='id' ? orderDirection : ''}">ID</th>
   <th>账号</th>
   <th>用户名</th>
   <th>密码</th>
   <th>创建时间</th>
   <th>登录时间</th>
</tr>
</thead>
<tbody>
<c:forEach var="entity" items="${pager.list}">
<tr target="id" rel="${entity.id}">
<td><input name="ids" value="${entity.id}" type="checkbox"></td>
<td>${entity.id}</td>
<td>${entity.account}</td>
<td>${entity.name}</td>
<td>${entity.password}</td>
<td><fmt:formatDate value="${entity.createAt}" pattern="yyyy-MM-dd HH:mm:ss" /></td>
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
