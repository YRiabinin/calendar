<%--
  Created by IntelliJ IDEA.
  User: ZGoryn
  Date: 09.03.15
  Time: 20:05
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <script type="text/javascript" src="js/script.js"></script>
    <link rel="stylesheet" type="text/css" href="css/style.css">
</head>
<body onload="loadCalendar();">
    <br/>
    <br/>
    <table cellspacing="0" cellpadding="0">
        <tr>
            <td id="currentDate">

            </td>
        </tr>
        <tr><td>
        <table class="month-names-table" cellspacing="0" cellpadding="0">
            <tr>
                <td id="month_1" onclick="clickMonth(this.id);"></td>
                <td id="month_0" onclick="clickMonth(this.id);"></td>
                <td id="month_2" onclick="clickMonth(this.id);"></td>
            </tr>
        </table>
            </td>
        </tr>
    <%--</div>--%>
    <tr id="month_0-box" class="hide">
        <td colspan="3">
        <table class="calendar-table" cellspacing="0" cellpadding="0">
            <tr>
                <td id="month_0-1" onclick="clickDay(this.id);"></td>
                <td id="month_0-2" onclick="clickDay(this.id);"></td>
                <td id="month_0-3" onclick="clickDay(this.id);"></td>
                <td id="month_0-4" onclick="clickDay(this.id);"></td>
                <td id="month_0-5" onclick="clickDay(this.id);"></td>
                <td id="month_0-6" onclick="clickDay(this.id);"></td>
                <td id="month_0-7" onclick="clickDay(this.id);"></td>
            </tr>
            <tr>
                <td id="month_0-8" onclick="clickDay(this.id);"></td>
                <td id="month_0-9" onclick="clickDay(this.id);"></td>
                <td id="month_0-10" onclick="clickDay(this.id);"></td>
                <td id="month_0-11" onclick="clickDay(this.id);"></td>
                <td id="month_0-12" onclick="clickDay(this.id);"></td>
                <td id="month_0-13" onclick="clickDay(this.id);"></td>
                <td id="month_0-14" onclick="clickDay(this.id);"></td>
            </tr>
            <tr>
                <td id="month_0-15" onclick="clickDay(this.id);"></td>
                <td id="month_0-16" onclick="clickDay(this.id);"></td>
                <td id="month_0-17" onclick="clickDay(this.id);"></td>
                <td id="month_0-18" onclick="clickDay(this.id);"></td>
                <td id="month_0-19" onclick="clickDay(this.id);"></td>
                <td id="month_0-20" onclick="clickDay(this.id);"></td>
                <td id="month_0-21" onclick="clickDay(this.id);"></td>
            </tr>
            <tr>
                <td id="month_0-22" onclick="clickDay(this.id);"></td>
                <td id="month_0-23" onclick="clickDay(this.id);"></td>
                <td id="month_0-24" onclick="clickDay(this.id);"></td>
                <td id="month_0-25" onclick="clickDay(this.id);"></td>
                <td id="month_0-26" onclick="clickDay(this.id);"></td>
                <td id="month_0-27" onclick="clickDay(this.id);"></td>
                <td id="month_0-28" onclick="clickDay(this.id);"></td>
            </tr>
            <tr>
                <td id="month_0-29" onclick="clickDay(this.id);"></td>
                <td id="month_0-30" onclick="clickDay(this.id);"></td>
                <td id="month_0-31" onclick="clickDay(this.id);"></td>
                <td id="month_0-32" onclick="clickDay(this.id);"></td>
                <td id="month_0-33" onclick="clickDay(this.id);"></td>
                <td id="month_0-34" onclick="clickDay(this.id);"></td>
                <td id="month_0-35" onclick="clickDay(this.id);"></td>
            </tr>
        </table>
        </td>
    </tr>
    <tr id="month_1-box" class="hide"><td colspan="3">
        <table class="calendar-table" cellspacing="0" cellpadding="0">
            <tr>
                <td id="month_1-1" onclick="clickDay(this.id);"></td>
                <td id="month_1-2" onclick="clickDay(this.id);"></td>
                <td id="month_1-3" onclick="clickDay(this.id);"></td>
                <td id="month_1-4" onclick="clickDay(this.id);"></td>
                <td id="month_1-5" onclick="clickDay(this.id);"></td>
                <td id="month_1-6" onclick="clickDay(this.id);"></td>
                <td id="month_1-7" onclick="clickDay(this.id);"></td>
            </tr>
            <tr>
                <td id="month_1-8" onclick="clickDay(this.id);"></td>
                <td id="month_1-9" onclick="clickDay(this.id);"></td>
                <td id="month_1-10" onclick="clickDay(this.id);"></td>
                <td id="month_1-11" onclick="clickDay(this.id);"></td>
                <td id="month_1-12" onclick="clickDay(this.id);"></td>
                <td id="month_1-13" onclick="clickDay(this.id);"></td>
                <td id="month_1-14" onclick="clickDay(this.id);"></td>
            </tr>
            <tr>
                <td id="month_1-15" onclick="clickDay(this.id);"></td>
                <td id="month_1-16" onclick="clickDay(this.id);"></td>
                <td id="month_1-17" onclick="clickDay(this.id);"></td>
                <td id="month_1-18" onclick="clickDay(this.id);"></td>
                <td id="month_1-19" onclick="clickDay(this.id);"></td>
                <td id="month_1-20" onclick="clickDay(this.id);"></td>
                <td id="month_1-21" onclick="clickDay(this.id);"></td>
            </tr>
            <tr>
                <td id="month_1-22" onclick="clickDay(this.id);"></td>
                <td id="month_1-23" onclick="clickDay(this.id);"></td>
                <td id="month_1-24" onclick="clickDay(this.id);"></td>
                <td id="month_1-25" onclick="clickDay(this.id);"></td>
                <td id="month_1-26" onclick="clickDay(this.id);"></td>
                <td id="month_1-27" onclick="clickDay(this.id);"></td>
                <td id="month_1-28" onclick="clickDay(this.id);"></td>
            </tr>
            <tr>
                <td id="month_1-29" onclick="clickDay(this.id);"></td>
                <td id="month_1-30" onclick="clickDay(this.id);"></td>
                <td id="month_1-31" onclick="clickDay(this.id);"></td>
                <td id="month_1-32" onclick="clickDay(this.id);"></td>
                <td id="month_1-33" onclick="clickDay(this.id);"></td>
                <td id="month_1-34" onclick="clickDay(this.id);"></td>
                <td id="month_1-35" onclick="clickDay(this.id);"></td>
            </tr>
        </table>
        </td>
    </tr>
    <tr id="month_2-box" class="hide"><td colspan="3">
        <table class="calendar-table" cellspacing="0" cellpadding="0">
            <tr>
                <td id="month_2-1" onclick="clickDay(this.id);"></td>
                <td id="month_2-2" onclick="clickDay(this.id);"></td>
                <td id="month_2-3" onclick="clickDay(this.id);"></td>
                <td id="month_2-4" onclick="clickDay(this.id);"></td>
                <td id="month_2-5" onclick="clickDay(this.id);"></td>
                <td id="month_2-6" onclick="clickDay(this.id);"></td>
                <td id="month_2-7" onclick="clickDay(this.id);"></td>
            </tr>
            <tr>
                <td id="month_2-8" onclick="clickDay(this.id);"></td>
                <td id="month_2-9" onclick="clickDay(this.id);"></td>
                <td id="month_2-10" onclick="clickDay(this.id);"></td>
                <td id="month_2-11" onclick="clickDay(this.id);"></td>
                <td id="month_2-12" onclick="clickDay(this.id);"></td>
                <td id="month_2-13" onclick="clickDay(this.id);"></td>
                <td id="month_2-14" onclick="clickDay(this.id);"></td>
            </tr>
            <tr>
                <td id="month_2-15" onclick="clickDay(this.id);"></td>
                <td id="month_2-16" onclick="clickDay(this.id);"></td>
                <td id="month_2-17" onclick="clickDay(this.id);"></td>
                <td id="month_2-18" onclick="clickDay(this.id);"></td>
                <td id="month_2-19" onclick="clickDay(this.id);"></td>
                <td id="month_2-20" onclick="clickDay(this.id);"></td>
                <td id="month_2-21" onclick="clickDay(this.id);"></td>
            </tr>
            <tr>
                <td id="month_2-22" onclick="clickDay(this.id);"></td>
                <td id="month_2-23" onclick="clickDay(this.id);"></td>
                <td id="month_2-24" onclick="clickDay(this.id);"></td>
                <td id="month_2-25" onclick="clickDay(this.id);"></td>
                <td id="month_2-26" onclick="clickDay(this.id);"></td>
                <td id="month_2-27" onclick="clickDay(this.id);"></td>
                <td id="month_2-28" onclick="clickDay(this.id);"></td>
            </tr>
            <tr>
                <td id="month_2-29" onclick="clickDay(this.id);"></td>
                <td id="month_2-30" onclick="clickDay(this.id);"></td>
                <td id="month_2-31" onclick="clickDay(this.id);"></td>
                <td id="month_2-32" onclick="clickDay(this.id);"></td>
                <td id="month_2-33" onclick="clickDay(this.id);"></td>
                <td id="month_2-34" onclick="clickDay(this.id);"></td>
                <td id="month_2-35" onclick="clickDay(this.id);"></td>
            </tr>
        </table>
        </td>
    </tr>
        </table>
</body>
</html>