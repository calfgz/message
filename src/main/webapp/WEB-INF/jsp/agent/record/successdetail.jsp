<%@page contentType="text/html; charset=UTF-8" session="false" 
%><%@include  file="/WEB-INF/jspf/import.jspf"
%><div class="pageContent">
<div class="pageFormContent nowrap" layoutH="97">
			<dl class="nowrap">
				<dt>客户Id：</dt>
				<dd>${entity.agentId }</dd>
			</dl>
			<dl class="nowrap">
				<dt>smsId：</dt>
				<dd>${entity.smsid }</dd>
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
				<dt>成功数：</dt>
				<dd>${entity.count }</dd>				
			</dl>
			<dl>
				<dt>失败数：</dt>
				<dd>${entity.failCount }</dd>				
			</dl>
			<dl>
				<dt>查询状态：</dt>
				<dd>${entity.queryStatusDesc}</dd>				
			</dl>
			<dl>
				<dt>回执状态：</dt>
				<dd>${entity.respStatusDesc}</dd>				
			</dl>
			<dl>
				<dt>回执信息：</dt>
				<dd>${entity.respMessage }</dd>				
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