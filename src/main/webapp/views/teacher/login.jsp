<%@ page language="java" contentType="text/html; charset=utf-8"
         pageEncoding="utf-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=gb2312" />
    <title>会员登录</title>
    <link href="/public/stylesheets/login.css?>" rel="stylesheet" type="text/css" />
</head>
<body>
<div class="container text-center ">
    <div class="header">
        <h1><a href="/" class="logo"><img width="181" src="/public/images/logo_green.png"></a></h1>
        <p>每天积累一点点</p>
    </div>
</div>
<div class="container">
    <div id="login">
        <!--表单-->
        <div class="login-form">
            <form name='form1' method='POST' action='/account/doLogin'>
                <div class="form-group">
                    <label class="required">用户名</label>
                    <input id="txtUsername"  placeholder="用户名或者email" class="form-control" type="text" name="username"/>
                </div>
                <div class="form-group">
                    <label class="required">密码</label>
                    <input id="txtPassword"  placeholder="密码" class="form-control" type="password" name="password"/>
                </div>
                <div class="form-group">
                    <button id="btnSignCheck"  style="color:#fff"  class="btn btn-primary btn-block btn-lg" type="submit">登录</button>
                    <a href="/account/reg">还没有账号？注册</a>
                </div>
            </form>
        </div>
    </div>
</div>
</body>
</html>
