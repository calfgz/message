<%@page contentType="text/html; charset=UTF-8" session="false" 
%><%@include  file="/WEB-INF/jspf/import.jspf"
%><div class="pageContent">
<form method="post" action="${ctx}/admin/agent/${method}.do" class="pageForm required-validate" onsubmit="return validateCallback(this, dialogAjaxDone);">
<input type="hidden" value="${entity.id}" name="id"/>
<div class="pageFormContent" layoutH="56">
<c:if test='${method eq "update"}'>
<p>
<label>商户帐号: </label><input class="required alphanumeric" size="30" maxlength="50"  readonly value="${entity.account}" name="account" />
</p>
</c:if>
<c:if test='${method eq "create"}'>
<p>
<label>商户帐号: </label><input class="required alphanumeric" size="30" maxlength="50" name="account" />
</p>
<p>
<label>商户密码: </label><input type="password" id="password" class="required alphanumeric" size="30" maxlength="50" name="password" />
</p>
<p>
<label>确认密码: </label><input type="password" class="required" equalto="#password" size="30" maxlength="50" name="repassword" />
</p>
</c:if>
<p>
<label>商户名: </label><input class="required textInput" size="30" maxlength="50" value="${entity.name}" name="name" />
</p>
<p>
<label>邮箱: </label><input class="required email" size="30" maxlength="50" value="${entity.email}" name="email" />
</p>
<p>
<label>手机号: </label><input class="required mobile" size="30" maxlength="50" value="${entity.mobile}" name="mobile" />
</p>
<p>
<label>应用ID: </label><input class="required textInput" size="30" maxlength="50" value="${entity.appId}" name="appId" />
</p>
<p>
<label>端口号: </label><input class="required textInput" size="30" maxlength="50" value="${entity.port}" name="port" />
</p>
</div>
<div class="formBar">
<ul>
<li>
<div class="buttonActive">
<div class="buttonContent"><button type="submit">保存</button></div>
</div>
</li>
<li>
<div class="button">
<div class="buttonContent"><button type="button" class="close">取消</button></div>
</div>
</li>
</ul>
</div>
</form> 
</div>