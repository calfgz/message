<%@page contentType="text/html; charset=UTF-8" session="false" 
%><%@include  file="/WEB-INF/jspf/import.jspf"
%><div class="pageContent">
<div class="pageFormContent nowrap" layoutH="97">
			<dl class="nowrap">
				<dt>客户Id：</dt>
				<dd>${entity.agentId }</dd>
			</dl>
			<dl class="nowrap">
				<dt>手机号：</dt>
				<dd>${entity.phones }</dd>
			</dl>
			<dl class="nowrap">
				<dt>短信内容：</dt>
				<dd>${entity.content}</dd>
			</dl>
			<dl>
				<dt>数量：</dt>
				<dd>${entity.count }</dd>				
			</dl>
			<dl>
				<dt>错误编号：</dt>
				<dd>${entity.respCode }</dd>				
			</dl>
			<dl>
				<dt>错误说明：</dt>
				<dd>${entity.codeDesc}</dd>				
			</dl>
			<dl>
				<dt>发送时间：</dt>
				<dd><fmt:formatDate value="${entity.sendAt}" pattern="yyyy-MM-dd HH:mm:ss" /></dd>				
			</dl>
		</div>
<div class="formBar">
<ul>
<li>
<div class="button">
<div class="buttonContent"><button type="button" class="close">取消</button></div>
</div>
</li>
</ul>
</div>
</div>