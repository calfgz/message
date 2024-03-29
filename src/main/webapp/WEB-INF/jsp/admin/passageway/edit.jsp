<%@page contentType="text/html; charset=UTF-8" session="false" 
%><%@include  file="/WEB-INF/jspf/import.jspf"
%><div class="pageContent">
<form method="post" action="${ctx}/admin/passageway/${method}.do" class="pageForm required-validate" onsubmit="return validateCallback(this, dialogAjaxDone);">
<div class="pageFormContent" layoutH="56">
<c:if test='${method eq "create"}'>
<p>
<label>通道ID: </label><input class="required digits" size="30" maxlength="50" name="id" />
</p>
</c:if>
<p>
<label>通道名: </label><input class="required textInput" size="30" maxlength="50" value="${entity.name}" name="name" />
</p>
<p>
<label><input type="checkbox" name="cmcc" value="1" ${entity.cmcc == 1 ? 'checked':''}/>移动</label>
</p>
<p>
<label><input type="checkbox" name="cucc" value="1" ${entity.cucc == 1 ? 'checked':''}/>联通</label>
</p>
<p>
<label><input type="checkbox" name="ctcc" value="1" ${entity.ctcc == 1 ? 'checked':''}/>电信</label>
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