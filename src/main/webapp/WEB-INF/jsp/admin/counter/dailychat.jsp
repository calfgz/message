<%@page contentType="text/html; charset=UTF-8" session="false" 
%><%@include  file="/WEB-INF/jspf/import.jspf"
%><form id="pagerForm" method="get" action="">
<input type="hidden" name="numPerPage" value="25" />
</form>
<div class="pageHeader">
	<form rel="pagerForm" onsubmit="return navTabSearch(this);" action="${ctx}/admin/counter/dailychat.do" method="get">
	<div class="searchBar">
		<table class="searchContent"> 
				<tbody>
					<tr>
						<td>开始日期：<input type="text" name="startDate" value="<fmt:formatDate value='${startDate}' pattern='yyyy-MM-dd' />" class="date" readonly="true"/>
						结束日期：<input type="text" name="endDate" value="<fmt:formatDate value='${endDate}' pattern='yyyy-MM-dd' />" class="date" readonly="true"/>
						客户ID：<input type="text" name="agentId" value="${agentId}" class="text"/>
						</td>
					</tr>
				</tbody>
			</table>
		<div class="subBar">
			<ul>
				<li><div class="buttonActive"><div class="buttonContent"><button type="submit">检索</button></div></div></li>				
			</ul>
		</div>
	</div>
	</form>
</div>
<div class="pageContent">

	<div class="tabs" currentIndex="0" eventType="click">
		<div class="tabsHeader">
			<div class="tabsHeaderContent">
				<ul>
					<li><a href="javascript:;"><span>图形报表</span></a></li>
					<li><a href="javascript:;"><span>表格报表</span></a></li>
				</ul>
			</div>
		</div>
		<div class="tabsContent" style="height:650px;">
			<div>
				<div id="dailychat-cahtContainer" style="min-width: 310px; height: 400px;"></div>
			</div>			
			<div>
				<table class="table" style="width: 100%" layoutH="138">
				<tbody>
				<c:forEach var="entity" items="${list}">
				<tr target="id" rel="${entity.id}">
				<td><input name="ids" value="${entity.id}" type="checkbox"></td>
				<td>${entity.id}</td>
				<td>${entity.agentId}</td>
				<td><fmt:formatDate value="${entity.recDate}" pattern="yyyy-MM-dd" /></td>
				<td>${entity.success}</td>
				<td>${entity.fail}</td>
				<td><fmt:formatDate value="${entity.createAt}" pattern="yyyy-MM-dd HH:mm:ss" /></td>
				</tr>
				</c:forEach> 
				</tbody>
				</table>
			</div>
		</div>
		<div class="tabsFooter">
			<div class="tabsFooterContent"></div>
		</div>
	</div>

</div>

<div class="panelBar">
</div>
<script type="text/javascript">
$(function () {
    $('#dailychat-cahtContainer').highcharts({
        title: {
            text: '${agent.name}',
            x: -20 //center
        },
        subtitle: {
            text: '日计报表',
            x: -20
        },
        xAxis: {
            categories: ${xAxis}
        },
        yAxis: {
            title: {
                text: '单位 (条)'
            },
            plotLines: [{
                value: 0,
                width: 1,
                color: '#808080'
            }]
        },
        tooltip: {
            valueSuffix: '条'
        },
        legend: {
            layout: 'vertical',
            align: 'right',
            verticalAlign: 'middle',
            borderWidth: 1
        },
        exporting:{
            enabled:false
        },
        credits: {
            enabled: false
        }, 
        series: [{
            name: '成功',
            data: ${succSeries}
        }, {
            name: '失败',
            data: ${failSeries}
        }]
    });
});
</script>
