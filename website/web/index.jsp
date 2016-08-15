<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<html class="no-js">
<head>
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1">

    <link rel="icon" href="http://www.d9soft.com/upload/2009/2/24/2009022486001345.gif">
    <title>七夕送好礼 真情不降温</title>
    <!-- 新 Bootstrap 核心 CSS 文件 -->
    <link rel="stylesheet" href="//cdn.bootcss.com/bootstrap/3.3.5/css/bootstrap.min.css">

    <!-- 可选的Bootstrap主题文件（一般不用引入） -->
    <link rel="stylesheet" href="//cdn.bootcss.com/bootstrap/3.3.5/css/bootstrap-theme.min.css">
</head>

<body>
<nav class="navbar navbar-inverse navbar-fixed-top">
    <div class="container">
        <div class="navbar-header">
            <button type="button" class="navbar-toggle collapsed" data-toggle="collapse" data-target="#navbar"
                    aria-expanded="false" aria-controls="navbar">
                <span class="sr-only">Toggle navigation</span>
                <span class="icon-bar"></span>
                <span class="icon-bar"></span>
                <span class="icon-bar"></span>
            </button>
            <a class="navbar-brand" href="#">七夕</a>
        </div>
        <div id="navbar" class="navbar-collapse collapse">
            <form class="navbar-form navbar-right">
                <div class="form-group">
                    <input type="text" placeholder="Email" class="form-control">
                </div>
                <div class="form-group">
                    <input type="password" placeholder="Password" class="form-control">
                </div>
                <button type="submit" class="btn btn-success">Sign in</button>
            </form>
        </div>
        <!--/.navbar-collapse -->
    </div>
</nav>
<div class="jumbotron">
    <div class="container">
        <h2>七夕佳节</h2>

        <p>你也许是我近旁的一株木棉，</p><p>
            做为树的形象和我站在一起。</p><p>
            为你承担寒潮、风雷、霹雳；</p><p>
            与你共享雾霭、流岚、虹霓。</p><p>
    </p><p>
            我爱你——</p><p>
            在美丽的七夕，</p><p>更在每一个平凡的朝夕。</p>

        <p><a class="btn btn-primary btn-lg" href="#" role="button">送礼表真心 »</a></p>
    </div>
</div>
<div class="container">
    <!-- Example row of columns -->
    <div class="row">
        <div class="col-md-8">
            <h1>优惠券 免费领</h1>

            <div class="inner form-group">
                <input type="text" placeholder="手机号" class="form-control" id="phone">
                <button type="submit" class="btn btn-success" id="genBtn">生成二维码</button>
            </div>
        </div>
    </div>
    <div class="row" id="rcode">

        <img id="imgid" src="" />
    </div>
    <hr>

    <footer>
        <p>© Company 2016</p>
    </footer>
</div>

<!-- jQuery文件。务必在bootstrap.min.js 之前引入 -->
<script src="//cdn.bootcss.com/jquery/1.11.3/jquery.min.js"></script>
<!-- 最新的 Bootstrap 核心 JavaScript 文件 -->
<script src="//cdn.bootcss.com/bootstrap/3.3.5/js/bootstrap.min.js"></script>

<script>
    $(function () {
        $("#genBtn").click(function () {
            var url;
            var phoneEle = $('#phone').val();
            url = "ajax/get_rcode.htm?phone=" + phoneEle;

            $.ajax({
                type: 'POST',
                url: url,
                async: false,
                success: function (resp) {
                    var res = eval("(" + resp + ")");
                    $("#imgid").attr("src","data:image/jpg;base64," + res.data);
                    if (res.code == 100) {
                        $.alert({
                            title: '提示!',
                            content: '修改成功!',
                            confirmButton: '知道了',
                            confirm: function () {
                                window.location.reload();
                            }
                        });
                    }
                    else {
                        $("#rcode").html("手机号码有误！");
                    }
                },
                error: function (resp) {
                    var res = eval("(" + resp + ")");
                    alert(resp);
                    $.alert({
                        title: '提示',
                        content: res.phone,
                        confirmButton: '知道了'
                    });
                }
            });
        });
    });
</script>


</body>
</html>
