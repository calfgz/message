<%@page contentType="text/html; charset=UTF-8" session="false" 
%><%@include  file="/WEB-INF/jspf/import.jspf"
%><div class="pageContent">
<div class="pageFormContent nowrap" layoutH="97">
<dl>
<dt>返回码: </dt><dd>${json.respCode}</dd>
</dl>
<dl>
<dt>开发者账号Id: </dt><dd>${json.accountSid}</dd>
</dl>
<dl>
<dt>开发者昵称: </dt><dd>${json.developerName}</dd>
</dl>
<dl>
<dt>创建时间: </dt><dd>${json.createTime}</dd>
</dl>
<dl>
<dt>更新时间: </dt><dd>${json.updateTime}</dd>
</dl>
<dl>
<dt>邮箱: </dt><dd>${json.email}</dd>
</dl>
<dl>
<dt>手机号: </dt><dd>${json.mobile}</dd>
</dl>
<dl>
<dt>开发者账号状态: </dt><dd>${json.activationStatus}</dd>
</dl>
<dl>
<dt>开发者认证状态: </dt><dd>${json.status}</dd>
</dl>
<dl>
<dt>余额: </dt><dd>${json.balance}</dd>
</dl>
<dl>
<dt>赠送余额: </dt><dd>${json.giftBlance}</dd>
</dl>
</div>
</div>