<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/webpage/include/taglib.jsp"%>
<html>
<head>
    <%@ page import="java.util.*"%>
    <%
        Calendar calendar=Calendar.getInstance();
        int year=calendar.get(Calendar.YEAR);
        int month=calendar.get(Calendar.MONTH)+1;
        int day=calendar.get(Calendar.DATE);
        String date=year+"-"+month+"-"+day;
    %>
    <title>新增订单</title>
    <meta name="decorator" content="default"/>
    <script type="text/javascript">
        var validateForm;
        function doSubmit(){//回调函数，在编辑和保存动作时，供openDialog调用提交表单。
            if(validateForm.form()){
                $("#inputForm").submit();
                return true;
            }

            return false;
        }
        $(document).ready(function() {
            validateForm = $("#inputForm").validate({
                submitHandler: function(form){
                    loading('正在提交，请稍等...');
                    form.submit();
                },
                errorContainer: "#messageBox",
                errorPlacement: function(error, element) {
                    $("#messageBox").text("输入有误，请先更正。");
                    if (element.is(":checkbox")||element.is(":radio")||element.parent().is(".input-append")){
                        error.appendTo(element.parent().parent());
                    } else {
                        error.insertAfter(element);
                    }
                }
            });

        });
    </script>
</head>
<body>
<form:form id="inputForm" modelAttribute="market" action="${ctx}/salesorder/market/doadd" method="post" class="form-horizontal">
    <%--<form:hidden path="id"/>--%>
    <sys:message content="${message}"/>
    <table class="table table-bordered  table-condensed dataTables-example dataTable no-footer">
        <tbody>
        <tr>
            <td class="width-15 active"><label class="pull-right">单据日期：</label></td>
            <td class="width-35">
                <input type="text" name=""  value="<%=date%>"/>
            </td>
            <td class="width-15 active"><label class="pull-right">单据号：</label></td>
            <td class="width-35">
                <input type="text"  readonly  value="自动编码">
            </td>
            <td class="width-15 active"><label class="pull-right">客户：</label></td>
            <td class="width-35">
                <select name="customerid">
                    <option value="0">&nbsp;&nbsp;&nbsp;&nbsp;</option>
                    <c:forEach var="item" items="${list}">
                        <option value="${item.customerid}">${item.customerName}</option>
                    </c:forEach>
                </select>
            </td>
        </tr>
        <tr>
            <td class="width-15 active"><label class="pull-right">备注信息：</label></td>

            <td class="width-15 active"></td>
            <td class="width-35" ></td>
        </tr>
        </tbody>
    </table>
</form:form>
</body>
</html>