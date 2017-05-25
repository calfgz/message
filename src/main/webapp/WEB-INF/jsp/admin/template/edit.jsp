<%@page contentType="text/html; charset=UTF-8" session="false" 
%><%@include  file="/WEB-INF/jspf/import.jspf"
%><div class="pageContent">
<form method="post" action="${ctx}/admin/template/${method}.do" class="pageForm required-validate" onsubmit="return validateCallback(this, dialogAjaxDone);">
<input type="hidden" value="${entity.id}" name="id"/>
<div class="pageFormContent" layoutH="56">
<c:if test='${method eq "update"}'>
<p>
<label>模板ID: </label><input class="required alphanumeric" size="30" maxlength="50"  readonly value="${entity.templateId}" name="templateId" />
</p>
</c:if>
<p>
<label>模板类型: </label><input class="required" size="30" maxlength="50" value="${entity.templateType}" name="template_type" />
</p>
<p>
<label>标题: </label><input class="required" size="30" maxlength="50" value="${entity.title}" name="title" />
</p>
<p>
<label>签名: </label><input class="required" size="30" maxlength="50" value="${entity.signature}" name="signature" />
</p>
<p>
<label>内容: </label><input class="required textInput" size="30" maxlength="50" value="${entity.content}" name="content" />
</p>
<p>
<label>邮箱: </label><input class="required email" size="30" maxlength="50" value="${entity.senderMailbox}" name="mailbox" />
</p>
<p>
<label>昵称: </label><input class="required" size="30" maxlength="50" value="${entity.senderNickname}" name="nickname" />
</p>
<p>
<label>邮件内容: </label><input class="required textInput" size="30" maxlength="50" value="${entity.emailContent}" name="email_content" />
</p>
<p>
<label>客户号: </label><input class="required" size="30" maxlength="50" value="${entity.agentId}" name="agentId" />
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