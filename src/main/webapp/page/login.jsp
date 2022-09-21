<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<!DOCTYPE html>
<!DOCTYPE html>
<html>
<head>
  <meta charset="utf-8">
  <meta name="viewport" content="width=device-width, initial-scale=1.0">
  <link rel="stylesheet" type="text/css" href="css/login.css">
  <title>体育馆管理系统 | 登录</title>
</head>
<body>
<div class="container right-panel-active">

  <div class="container_from container--signup">
    <form action="servlet/LoginController" class="from" id="from1">
      <h2 class="from_title">注册账号</h2>
      <input name="name" type="text" placeholder="姓名" class="input">
      <input name="phone" type="text" placeholder="手机号" class="input">
      <input name="pwd1" type="password" placeholder="密码" class="input">
      <input name="pwd2" type="password" placeholder="确认密码" class="input">
      <button class="btn">点击注册</button>
    </form>
    </form>
  </div>

  <div class="container_from container--signin">
    <form action="#" class="from" id="from2">
      <h2 class="from_title">欢迎登录</h2>
      <input type="text" placeholder="手机或用户名" class="input">
      <input type="password" placeholder="密码" class="input">
      <button class="btn">登录</button>
    </form>
    </form>
  </div>

  <div class="container_overlay">
    <div class="overlay">
      <div class="overlay_panel overlay--left">
        <button class="btn" id="signin">已有账号，直接登陆</button>
      </div>

      <div class="overlay_panel overlay--right">
        <button class="btn" id="signup">没有账号，点击注册</button>
      </div>
    </div>
  </div>

</div>
<script src="js/login.js"></script>
</body>
</html>
