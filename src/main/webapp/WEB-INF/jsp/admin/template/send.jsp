<%@page contentType="text/html; charset=UTF-8" session="false" 
%><%@include  file="/WEB-INF/jspf/import.jspf"
%><div class="pageContent">
<form method="post" action="${ctx}/admin/template/send.do" class="pageForm required-validate" onsubmit="return validateCallback(this, navTabAjaxDone);">
<div class="pageFormContent nowrap" layoutH="97">
			<dl class="nowrap">
				<dt>手机号：</dt>
				<dd>
				<textarea name="to" class="required" cols="40" rows="2"></textarea>				
				<span class="info">多个用英文逗号分隔</span>
				</dd>
			</dl>
			<dl class="nowrap">
				<dt>模板ID：</dt>
				<dd>
				<input type="text" class="required" name="templateId" size="30">
				<span class="info">模板id</span>
				</dd>
			</dl>
			<dl class="nowrap">
				<dt>短信内容：</dt>
				<dd>				
				<input type="text" class="required" name="param" size="30">
				<span class="info">内容数据。用于替换模板中{数字}，若有多个替换内容，用|分隔</span>
				</dd>
			</dl>
			<dl>
				<dt>短信类型：</dt>
				<dd>
				<select class="combox" name="type">
					<option value="1">行业邮件短信</option>
					<option value="2">营销邮件短信</option>
					<option value="3">行业短信</option>
					<option value="4">营销短信</option>
				</select>
				</dd>				
			</dl>
		</div>
<div class="formBar">
<ul>
<li>
<div class="buttonActive">
<div class="buttonContent"><button type="submit">发送</button></div>
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