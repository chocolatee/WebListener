<%@ page import="java.util.List" %>
<%@ page import="java.util.Map" %>
<%@ page language="java" contentType="text/html; charset=utf-8"
         pageEncoding="utf-8" %>
<!DOCTYPE html>
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=gb2312"/>
    <meta name="viewport" content="width=device-width, initial-scale=1.0"/>
    <title>发表试卷</title>
    <link href="/public/stylesheets/bootstrap.min.css" rel="stylesheet"/>
    <link rel="stylesheet" href="/public/stylesheets/font-awesome.min.css"/>
    <link rel="stylesheet" href="/public/stylesheets/ace.css"/>
    <script type="text/javascript" src="/public/javascripts/checkSubmit.js"></script>
</head>
<%List<Map<String, Object>> result = (List<Map<String, Object>>) request.getAttribute("result");%>
<body class="no-skin">
<div class="navbar navbar-default" id="navbar">
    <script type="text/javascript">
        try {
            ace.settings.check('navbar', 'fixed')
        } catch (e) {
        }
    </script>

    <div class="navbar-container container" id="navbar-container">
        <div class="navbar-header pull-left">
            <a href="/teacher/home/0" class="navbar-brand">
                <small>
                    <i class="menu-icon fa fa-cloud"></i>
                    教师系统
                </small>
            </a><!-- /.brand -->
        </div>
        <!-- /.navbar-header -->

        <div class="navbar-header pull-right" role="navigation">
            <ul class="nav ace-nav">
                <li>
                    <a href="/account/logout">
                        <img class="nav-user-photo" src=""/>
                        退出账号
                        <i class="ace-icon fa fa-caret-down"></i>
                    </a>
                </li>
            </ul>
            <!-- /.ace-nav -->
        </div>
        <!-- /.navbar-header -->
        <div class="navbar-buttons navbar-header pull-right" role="navigation">
            <span id="news"></span>
        </div>
    </div>
    <!-- /.container -->
</div>
<div class="main-container container" id="main-container">
    <div class="sidebar responsive" id="sidebar">
        <ul class="nav nav-list">
            <li>
                <a href="/teacher/home/0">
                    <i class="menu-icon fa fa-home"></i>
                    <span class="menu-text"> 首页 </span>
                </a>
            </li>
            <li>
                <a href="/teacher/pubTest" class="dropdown-toggle">
                    <i class="menu-icon fa fa-star"></i>
                    <span class="menu-text"> 发布试卷 </span>

                    <b class="arrow fa fa-angle-down"></b>
                </a>
            </li>
        </ul>
    </div>
    <div class="main-content" style="padding-left: 50px">
        <% for (int i = 0; i < result.size(); i++) {
            Map<String, Object> item = result.get(i);
        %>
        <div class="main-content-inner">
            <% long sum = (long) item.get("right_count") + (long) item.get("wrong_count");
                sum = sum == 0 ? 1 : sum; %>
            <div style="width:50%; height:auto; float:left; display:inline">
                <p class="MsoNormal">
                    <span>1. </span><strong><span><%=item.get("que_topic")%></span></strong>&nbsp;
                    <span><%= item.get("ans_right")%></span>
                </p>

                <p class="MsoNormal">
                    <span>A. </span><span><%= item.get("ans_b")%></span>&nbsp;&nbsp;<span>
                    <%=((int) item.get("stu_a") / sum * 100) + "%" %></span>
                </p>

                <p class="MsoNormal"><span style="color: red;">B. </span><span><%= item.get("ans_b")%>&nbsp;&nbsp</span>
                    <%= ((int) item.get("stu_b") / sum * 100) + "%" %>
                    <span> </span></p>

                <p class="MsoNormal"><span>C. </span><span><%= item.get("ans_c")%>&nbsp;&nbsp</span>
                    <%= ((int) item.get("stu_c") / sum * 100) + "%" %><span> </span></p>

                <p class="MsoNormal">
                    <span>D. </span><span><%= item.get("ans_d")%></span>&nbsp;&nbsp<span>
                    <%= ((int) item.get("stu_d") / sum * 100) + "%" %></span>
                </p>
            </div>
            <% if (++i < result.size()) {
                sum = (long) item.get("right_count") + (long) item.get("wrong_count");
                sum = sum == 0 ? 1 : sum; %>
            <div style="width:50%; height:auto; float:left; display:inline">
                <p class="MsoNormal">
                    <span>1. </span><strong><span><%= item.get("que_topic") %></span></strong>&nbsp;<span>
                    <%=item.get("ans_right") %></span>
                </p>

                <p class="MsoNormal">
                    <span>A. </span><span><%=item.get("ans_a") %></span>&nbsp;&nbsp;<span>
                    <%=((int) item.get("stu_a") / sum * 100) + "%" %></span>
                </p>

                <p class="MsoNormal"><span style="color: red;">B. <span><span><%=item.get("ans_b") %>
                            &nbsp;&nbsp</span><%=((int) item.get("stu_b") / sum * 100) + "%" %>
                    <span> </span></p>

                <p class="MsoNormal"><span>C. </span><span><%=item.get("ans_c") %>
                            &nbsp;&nbsp</span><%=((int) item.get("stu_c") / sum * 100) + "%" %><span> </span>
                </p>

                <p class="MsoNormal">
                    <span>D. </span><span><%=item.get("ans_d") %></span>&nbsp;&nbsp<span>
                    <%=((int) item.get("stu_d") / sum * 100) + "%" %></span>
                </p>
            </div>
            <%
                    }
                } %>
            <!-- /.page-content -->
        </div>
        <!-- /.main-content -->

    </div>
    <!-- /.main-container-inner -->

    <a href="#" id="btn-scroll-up" class="btn-scroll-up btn btn-sm btn-inverse">
        <i class="icon-double-angle-up icon-only bigger-110"></i>
    </a>
</div>
</body>
</html>