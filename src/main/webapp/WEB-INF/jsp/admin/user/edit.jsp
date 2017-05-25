<%@page contentType="text/html; charset=UTF-8" session="false" 
%><%@include  file="/WEB-INF/jspf/import.jspf"
%><div class="pageContent">
<form method="post" action="${ctx}/admin/user/${method}.do" class="pageForm required-validate" onsubmit="return validateCallback(this, dialogAjaxDone);">
<input type="hidden" value="${entity.id}" name="id"/>
<div class="pageFormContent" layoutH="56">
<c:if test='${method eq "update"}'>
<p>
<label>帐号: </label><input class="required alphanumeric" size="30" maxlength="50"  readonly value="${entity.account}" name="account" />
</p>
</c:if>
<c:if test='${method eq "create"}'>
<p>
<label>帐号: </label><input class="required alphanumeric" size="30" maxlength="50" name="account" />
</p>
<p>
<label>密码: </label><input type="password" id="password" class="required alphanumeric" size="30" maxlength="50" name="password" />
</p>
<p>
<label>确认密码: </label><input type="password" class="required" equalto="#password" size="30" maxlength="50" name="repassword" />
</p>
</c:if>
<p>
<label>用户名: </label><input class="required textInput" size="30" maxlength="50" value="${entity.name}" name="name" />
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