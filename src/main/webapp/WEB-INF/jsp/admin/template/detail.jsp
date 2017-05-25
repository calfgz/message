<%@page contentType="text/html; charset=UTF-8" session="false" 
%><%@include  file="/WEB-INF/jspf/import.jspf"
%><div class="pageContent">
<div class="pageFormContent" layoutH="56">
<p>
<label>id: </label>${entity.id}
</p>
<p>
<label>帐号: </label>${entity.account}
</p>
<p>
<label>密码: </label>${entity.password}
</p>
<p>
<label>商户名: </label>${entity.name}
</p>
<p>
<label>accId: </label>${entity.accid}
</p>
<p>
<label>appKey: </label>${entity.keyValue}
</p>
<p>
<label>邮箱: </label>${entity.email}
</p>
<p>
<label>手机号: </label>${entity.mobile}
</p>
<p>
<label>应用ID: </label>${entity.appId}
</p>
<p>
<label>端口号: </label>${entity.port}
</p>
<p>
<label>余额: </label>${entity.balance}
</p>
<p>
<label>赠送余额: </label>${entity.givenBalance}
</p>
<p>
<label>状态: </label>${entity.statusDesc}
</p>
<p>
<label>创建时间: </label><fmt:formatDate value="${entity.regAt}" pattern="yyyy-MM-dd HH:mm:ss" />
</p>
<p>
<label>登录时间: </label><fmt:formatDate value="${entity.loginAt}" pattern="yyyy-MM-dd HH:mm:ss" />
</p>
<p>
<label>更新时间: </label><fmt:formatDate value="${entity.updateAt}" pattern="yyyy-MM-dd HH:mm:ss" />
</p>
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