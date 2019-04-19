<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/webpage/include/taglib.jsp"%>
<%@page isELIgnored="false" %>
<html>
<head>
    <title>主页</title>
    <meta name="decorator" content="default"/>
    <script type="text/javascript">
        $(document).ready(function() {
        });
    </script>
</head>

<body class="gray-bg">
<%@ include file="/webpage/include/echarts.jsp"%>
<div class="wrapper wrapper-content">

    <div class="ibox">

    </div>

    <div class="ibox-content">
        <!--查询条件-->
        <div class="row">
            <div class="col-sm-12">
                <form:form id="searchForm" modelAttribute="market" action="${ctx}/salesorder/market/list" method="post" class="form-inline">
                    <input id="pageNo" name="pageNo" type="hidden" value="${page.pageNo}"/>
                    <input id="pageSize" name="pageSize" type="hidden" value="${page.pageSize}"/>
                    <table:sortColumn id="orderBy" name="orderBy" value="${page.orderBy}" callback="sortOrRefresh();"/><!-- 支持排序 -->

                </form:form>
                <br/>
            </div>
        </div>

        <!-- 工具栏 -->
        <div class="row">
            <div class="col-sm-12">
                <div class="pull-left">
                    <shiro:hasPermission name="salesorder:sales:add">
                        <table:addRow url="${ctx}/salesorder/sales/add" title="订单"></table:addRow><!-- 增加按钮 -->
                    </shiro:hasPermission>
                    <shiro:hasPermission name="echarts:pieClass:edit">
                        <table:editRow url="${ctx}/echarts/pieClass/form" title="订单" id="contentTable"></table:editRow><!-- 编辑按钮 -->
                    </shiro:hasPermission>
                    <shiro:hasPermission name="echarts:pieClass:del">
                        <table:delRow url="${ctx}/echarts/pieClass/deleteAll" id="contentTable"></table:delRow><!-- 删除按钮 -->
                    </shiro:hasPermission>

                    <button class="btn btn-white btn-sm " data-toggle="tooltip" data-placement="left" onclick="sortOrRefresh()" title="刷新"><i class="glyphicon glyphicon-repeat"></i> 刷新</button>

                </div>
                <div class="pull-right">
                    <button  class="btn btn-primary btn-rounded btn-outline btn-sm " onclick="search()" ><i class="fa fa-search"></i> 查询</button>
                    <button  class="btn btn-primary btn-rounded btn-outline btn-sm " onclick="reset()" ><i class="fa fa-refresh"></i> 重置</button>
                </div>
            </div>
        </div>

        <!-- 表格 -->
        <table id="contentTable" class="table table-striped table-bordered table-hover table-condensed dataTables-example dataTable">
            <thead>
            <tr>
                <th> <input type="checkbox" class="i-checks"></th>
                <th>单据号</th>
                <th>单据日期</th>
                <th>客户</th>
                <th>销售部门</th>
                <th>销售员</th>
                <th>销售金额</th>
                <th>制单人</th>
                <th>创建时间</th>
                <th>审核人</th>
                <th>作废人</th>

                <th>操作</th>
            </tr>
            </thead>
            <tbody>
            <c:forEach var="item"  items="${list}" >
                <tr>
                    <td> <input type="checkbox" id="${item.id}" class="i-checks"></td>
                    <td><a  href="#" onclick="openDialogView('查看班级', '${ctx}/salesorder/market/form?id=${item.id}','800px', '500px')">
                            ${item.id}
                    </a></td>
                    <td>
                         <fmt:formatDate value="${item.listTime}" pattern="yyyy-MM-dd"></fmt:formatDate>
                    </td>
                    <td>${item.customerName} </td>
                    <td>${item.officeName}</td>
                    <td>${item.roleName}</td>
                    <td>
                        <shiro:hasPermission name="echarts:pieClass:view">
                            <a href="#" onclick="openDialogView('查看班级', '${ctx}/echarts/pieClass/form?id=${pieClass.id}','800px', '500px')" class="btn btn-info btn-xs" ><i class="fa fa-search-plus"></i> 查看</a>
                        </shiro:hasPermission>
                        <shiro:hasPermission name="echarts:pieClass:edit">
                            <a href="#" onclick="openDialog('修改班级', '${ctx}/echarts/pieClass/form?id=${pieClass.id}','800px', '500px')" class="btn btn-success btn-xs" ><i class="fa fa-edit"></i> 修改</a>
                        </shiro:hasPermission>
                        <shiro:hasPermission name="echarts:pieClass:del">
                            <a href="${ctx}/echarts/pieClass/delete?id=${pieClass.id}" onclick="return confirmx('确认要删除该班级吗？', this.href)"   class="btn btn-danger btn-xs"><i class="fa fa-trash"></i> 删除</a>
                        </shiro:hasPermission>
                    </td>
                </tr>
            </c:forEach>
            </tbody>
        </table>

        <!-- 分页代码 -->
        <table:page page="${page}"></table:page>

    </div>
</div>
</div>
</body>
</html>