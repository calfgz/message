<%@page contentType="text/html; charset=UTF-8" session="false" 
%><%@include  file="/WEB-INF/jspf/import.jspf"
%><div class="pageContent">
<form method="post" action="${ctx}/admin/password.do" class="pageForm required-validate" onsubmit="return validateCallback(this, navTabAjaxDone);">
<div class="pageFormContent nowrap" layoutH="97">
<dl>
<dt>旧密码: </dt><dd><input type="password" class="required alphanumeric" minlength="6" maxlength="20" name="oldPassword" /></dd>
</dl>
<dl>
<dt>新密码: </dt><dd><input id="w_validation_pwd" type="password" class="required alphanumeric" minlength="6" maxlength="20" name="password" /></dd>
</dl>
<dl>
<dt>确认密码: </dt><dd><input type="password" class="required" equalto="#w_validation_pwd" minlength="6" maxlength="20" name="repassword" /></dd>
</dl>
</div>
<div class="formBar">
<ul>
<li>
<div class="buttonActive">
<div class="buttonContent"><button type="submit">提交</button></div>
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