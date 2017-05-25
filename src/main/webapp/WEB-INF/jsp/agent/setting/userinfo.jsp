<%@page contentType="text/html; charset=UTF-8" session="false" 
%><%@include  file="/WEB-INF/jspf/import.jspf"
%><div class="pageContent">
<div class="pageFormContent nowrap" layoutH="97">
<dl>
<dt>Id: </dt><dd>${agent.id}</dd>
</dl>
<dl>
<dt>accId: </dt><dd>${agent.accid}</dd>
</dl>
<dl>
<dt>账号: </dt><dd>${agent.account}</dd>
</dl>
<dl>
<dt>昵称: </dt><dd>${agent.name}</dd>
</dl>
<dl>
<dt>appKey: </dt><dd>${agent.keyValue}</dd>
</dl>
<dl>
<dt>邮箱: </dt><dd>${agent.email}</dd>
</dl>
<dl>
<dt>手机: </dt><dd>${agent.mobile}</dd>
</dl>
<dl>
<dt>状态: </dt><dd>${agent.status}</dd>
</dl>
<dl>
<dt>余额: </dt><dd>${agent.balance}</dd>
</dl>
<dl>
<dt>赠送余额: </dt><dd>${agent.givenBalance}</dd>
</dl>
<dl>
<dt>创建时间: </dt><dd><fmt:formatDate value="${agent.regAt}" pattern="yyyy-MM-dd HH:mm:ss" /></dd>
</dl>
<dl>
<dt>更新时间: </dt><dd><fmt:formatDate value="${agent.updateAt}" pattern="yyyy-MM-dd HH:mm:ss" /></dd>
</dl>
<dl>
<dt>登录时间: </dt><dd><fmt:formatDate value="${agent.loginAt}" pattern="yyyy-MM-dd HH:mm:ss" /></dd>
</dl>
</div>
</div>