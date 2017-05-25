<%@page contentType="text/html; charset=UTF-8" session="false" 
%><%@include  file="/WEB-INF/jspf/import.jspf"
%><div class="pageContent">
<form method="post" action="${ctx}/agent/edit.do" class="pageForm required-validate" onsubmit="return validateCallback(this, navTabAjaxDone);">
<div class="pageFormContent nowrap" layoutH="97">
<dl>
<dt>账号: </dt><dd><input class="required textInput" readonly size="30" maxlength="50" value="${agent.account}" /></dd>
</dl>
<dl>
<dt>昵称: </dt><dd><input class="required textInput" size="30" maxlength="50" value="${agent.name}" name="name" /></dd>
</dl>
<dl>
<dt>邮箱: </dt><dd><input class="required email" size="30" maxlength="50" value="${agent.email}" name="email" /></dd>
</dl>
<dl>
<dt>手机号: </dt><dd><input class="required mobile" size="30" maxlength="50" value="${agent.mobile}" name="mobile" /></dd>
</dl>
<%-- <dl>
<dt>端口号: </dt><dd><input class="required textInput" size="30" maxlength="50" value="${geli:display(agent, 'port')}" name="port" /></dd>
</dl> --%>
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