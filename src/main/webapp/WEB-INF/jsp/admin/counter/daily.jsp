<%@page contentType="text/html; charset=UTF-8" session="false" 
%><%@include  file="/WEB-INF/jspf/import.jspf"
%><form id="pagerForm" method="get" action="">
<input type="hidden" name="numPerPage" value="25" />
</form>
<div class="pageHeader">
	<form rel="pagerForm" onsubmit="return navTabSearch(this);" action="${ctx}/admin/counter/daily.do" method="get">
	<div class="searchBar">
		<table class="searchContent"> 
				<tbody>
					<tr>
						<td>日期：<input type="text" name="date" value="<fmt:formatDate value='${date}' pattern='yyyy-MM-dd' />" class="date" readonly="true"/>
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
				<div id="daily-cahtContainer" style="min-width: 310px; height: 400px; margin: 0 auto"></div>
			</div>			
			<div>
				<table class="table" style="width: 100%" layoutH="138">
				<thead>
				<tr>
				   <th width="2%"><input type="checkbox" class="checkboxCtrl" group="ids"></th>
				   <th>Id</th>
				   <th>商户id</th>
				   <th>日期</th>
				   <th>成功数</th>
				   <th>失败数</th>
				   <th>统计时间</th>
				</tr>
				</thead>
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
    $('#daily-cahtContainer').highcharts({
        chart: {
            type: 'column'
        },
        title: {
            text: '日计柱状图'
        },
        xAxis: {
            categories: ${xAxis}
        },
        yAxis: {
            min: 0,
            title: {
                text: '单位 (条)'
            }
        },
        exporting:{
            enabled:false
        },
        credits: {
            enabled: false
        }, 
        tooltip: {
            headerFormat: '<span style="font-size:10px">{point.key}</span><table>',
            pointFormat: '<tr><td style="color:{series.color};padding:0">{series.name}: </td>' +
                '<td style="padding:0"><b>{point.y} 条</b></td></tr>',
            footerFormat: '</table>',
            shared: true,
            useHTML: true
        },
        plotOptions: {
            column: {
                pointPadding: 0.2,
                borderWidth: 0
            }
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
