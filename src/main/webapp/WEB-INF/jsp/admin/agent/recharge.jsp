<%@page contentType="text/html; charset=UTF-8" session="false" 
%><%@include  file="/WEB-INF/jspf/import.jspf"
%><div class="pageContent">
<form method="post" action="${ctx}/admin/agent/recharge.do" class="pageForm required-validate" onsubmit="return validateCallback(this, dialogAjaxDone);">
<input type="hidden" value="${agent.id}" name="agentId"/>
<div class="pageFormContent" layoutH="56">
<p>
<label>商户账号: </label><input class="required textInput" size="30" maxlength="50" readonly value="${agent.account}" />
</p>
<p>
<label>商户名: </label><input class="required textInput" size="30" maxlength="50" readonly value="${agent.name}" />
</p>
<p>
<label>充值金额: </label><input class="required digits" size="30" maxlength="50" value="" name="balance" />
</p>
<p>
<label>赠送金额: </label><input class="required digits" size="30" maxlength="50" value="" name="givenBalance" />
</p>
<p class="nowrap">
	<label>充值说明：</label>
	<textarea name="intro" class="required textInput" cols="30" rows="2"></textarea>
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