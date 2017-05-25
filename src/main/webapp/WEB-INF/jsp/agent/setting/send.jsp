<%@page contentType="text/html; charset=UTF-8" session="false" 
%><%@include  file="/WEB-INF/jspf/import.jspf"
%><div class="pageContent">
<form method="post" action="${ctx}/agent/send.do" class="pageForm required-validate" onsubmit="return validateCallback(this, navTabAjaxDone);">
<div class="pageFormContent nowrap" layoutH="97">
			<dl class="nowrap">
				<dt>手机号：</dt>
				<dd>
				<textarea name="phones" class="required" cols="40" rows="2"></textarea>				
				<span class="info">多个用英文逗号分隔</span>
				</dd>
			</dl>
			<dl class="nowrap">
				<dt>短信内容：</dt>
				<dd>
				<textarea name="content" class="required" cols="40" rows="2"></textarea>
				<span class="info">必须包含验证码、密码、校验码、注册码、激活码、效验码、认证码、取件码、授权码等关键字</span>
				</dd>
			</dl>
			<dl>
				<dt>签名：</dt>
				<dd>
				<input type="text" class="required" name="sign" size="30">
				<span class="info">短信后面大括号内容</span>
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