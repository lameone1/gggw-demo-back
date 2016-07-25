<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<c:set var="contextPath" value="${pageContext.request.contextPath}" />

<html>

<head>
    <meta charset="UTF-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <title>Home</title>
    <!-- Bootstrap -->
    <link href="${contextPath }/static/css/bootstrap/bootstrap.css" rel="stylesheet" type="text/css">
    <!--myCSS>-->
    <link href="${contextPath }/static/css/common.css" rel="stylesheet">
    <link href="${contextPath }/static/css/main.css" rel="stylesheet">

    <!-- jQuery (necessary for Bootstrap's JavaScript plugins) -->
    <script type="text/javascript" src="${contextPath }/static/js/bootstrap/jquery.js"></script>
    <!-- Include all compiled plugins (b\js\bootstrap\jquery.jselow), or include individual files as needed -->
    <script type="text/javascript" src="${contextPath }/static/js/bootstrap/bootstrap.min.js"></script>

    <!-- HTML5 shim and Respond.js for IE8 support of HTML5 elements and media queries -->
    <!-- WARNING: Respond.js doesn't work if you view the page via file:// -->
    <!--   [if lt IE 9]  >
    <script src="//cdn.bootcss.com/html5shiv/3.7.2/html5shiv.min.js"></script>
    <script src="//cdn.bootcss.com/respond.js/1.4.2/respond.min.js"></script>
    <!  [endif]-->

    <!--myJS-->
    <script>

        $(function(){
            $(window).scroll(function () {
                var scrollTop =$(this).scrollTop();//滚动高度
                if(scrollTop > 100 && scrollTop<700 ){
                    $("#mainNav").addClass("dark");
                    $("#mainNav").removeClass("light");
                    $("#mainNav").addClass("white_font")
                }
                if(scrollTop>700 ){
                    $("#mainNav").removeClass("dark")
                    $("#mainNav").addClass("light");
                    $("#mainNav").removeClass("white_font")
                }
                if(scrollTop < 100){
                    $("#mainNav").removeClass("dark");
                    $("#mainNav").removeClass("light");
                    $("#mainNav").addClass("white_font");
                }
            });
        });

        var p = 0;
        //$("#tpcclk").click(run());
        function run(){
            $("#runbar").css("width",p+"%");
            if(p<100){
                p+=1;
                var timer=setTimeout("run()",100);
            }else{
                alert("煞笔啊！");
                p=0;
            }
        }
    </script>

</head>

<body>

<!--navigation开始-->
<div class="navbar navbar-fixed-top affix-top white_font"  id="mainNav" >
    <div class="container">
        <div class="navbar-header">
            <button type="button" class="navbar-toggle" data-toggle="collapse" data-target="#navbar-ex-collapse">
                <span class="sr-only">喂! 你是猪吗 ?</span>
                <span class="icon-bar"></span>
                <span class="icon-bar"></span>
                <span class="icon-bar"></span>
            </button>
            <a class="navbar-brand" href="#"><span>时光机</span></a>
        </div>
        <div class="collapse navbar-collapse" id="navbar-ex-collapse">
            <ul class="nav navbar-nav navbar-right">
                <li class="active">
                    <a href="#">Home</a>
                </li>
                <li>
                    <a href="#">You</a>
                </li>
                <li>
                    <a href="${contextPath }/ui/front/Me.jsp">Me</a>
                </li>
                <li>
                    <a href="${contextPath }/ui/back/login.jsp">little thing</a>
                </li>
                <li>
                    <a href="#">Setting</a>
                </li>
            </ul>
        </div>
    </div>
</div>
<!--navigation结束-->

<div class="cover">

    <!--主题背景开始>-->
    <div class="cover-image" style="background-image : url('${contextPath }/static/image/image.jpg')"></div>
    <div class="container topic">
        <div class="row">
            <div class="col-md-12 text-center">
                <h1 class="text-inverse">影子是我的情人，&nbsp;&nbsp;&nbsp;心是仇敌 </h1>
                <br>
                <br>
                <p class="text-info">独具特色</p>
                <p class="text-muted">创新无限</p>
                <p class="">体验至上</p>
                <p class="text-danger">专业放心</p>
                <br>
                <a id="tpcclk" data-toggle="modal" data-target="#myModal" onclick="run()">Click me</a>
            </div>
        </div>
    </div>

    <!-- Modal模态框 -->
    <div class="modal fade" id="myModal" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" data-backdrop="static" >
        <div class="modal-dialog" role="document">
            <div class="modal-content">
                <div class="modal-header">
                    <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span></button>
                    <h4 class="modal-title" id="myModalLabel">稍等....有惊喜哦~~~</h4>
                </div>
                <div class="modal-body">
                    <div class="progress">
                        <div id="runbar" class="progress-bar progress-bar-striped active" role="progressbar" aria-valuenow="0" aria-valuemin="0" aria-valuemax="100" style="width:0%">
                            <span class="sr-only"></span>
                        </div>
                    </div>
                </div>
            </div>
        </div>
    </div>
    <!--主题背景结束-->
</div>

<!--模块1开始-->
<div class="section">
    <div class="container">
        <div class="row">
            <div class="col-md-6">
                <img src="${contextPath }/static/image/aaaa.jpg"
                     class="img-responsive">
            </div>
            <div class="col-md-6">
                <h1 class="text-primary">记。</h1>
                <h3 class="text-muted">微不足道.</h3>
                <p style="color:#333333 ">
                        你知道吗
                        <br>
                        纵然此时全世界都和你说放弃
                        <br>
                        你只是会更加迷信自己的直觉
                        <br>
                        可是迷信不是错
                        <br>
                        直觉也不是
                        <br>
                        放弃也不是错
                        <br>
                        走下去也不是错
                        <br>
                        我就是觉得
                        <br>
                        如果有一天两个人散了
                        <br>
                        一定是轻轻的放开了手
                        <br>
                        痛都不会再痛。
                </p>
            </div>
        </div>
    </div>
</div>
<!--模块1结束-->

<!--模块2开始-->
<div class="section">
    <div class="container">
        <div class="row">
            <div class="col-md-6">
                <h1 class="text-primary">记。</h1>
                <h3 class="text-muted">三文鱼的味道</h3>
                <p class="text-success">居酒の屋</p>
                <p class="text-danger">
                    <small>
                        味道很好。
                    </small>
                </p>
                <p>
                    窗外，正值炎夏，南方的天气总是炎热潮湿。她已经习惯了这种湿润的闷热。
                    <br>
                    居住的院落是她喜欢的风景。安静而温馨。这一切都是他丈夫给予的。想起她的丈夫，她总是不自觉的溢满了幸福。
                    <br>
                    只是不曾想偏执如她也难免落入相夫教子的俗套生活，但是幸福就好。
                    <br>
                    在无情的岁月中，时间赋予了她清雅温婉的气质，让她更加坦然，懂得珍惜。
                    <br>
                    这样的结局很好，每个女人最后的终结 ，或许都是希望能够有个安稳的家，有那种最平实简单的幸福。而她拥有了。
                    <br>
                    所以她的美好正在进行中，刚开始，何来结束呢。
                </p>
            </div>
            <div class="col-md-6">
                <img src="${contextPath }/static/image/bbbb.jpg"
                     class="img-responsive">
            </div>
        </div>
    </div>
</div>
<!--模块2结束-->

<!--footer开始-->
<footer>

    <section>
        <div class="">
            <dl>
                <dt>
                    Java Website
                </dt>
                <dd>
                    <a href="http://www.csdn.net/">
                        CSDN
                    </a>
                </dd>
                <dd>
                    <a href="http://www.importnew.com/">
                        ImportNew
                    </a>
                </dd>
                <dd>
                    <a href="http://www.iteye.com/">
                        ITeye
                    </a>
                </dd>
                <dd>
                    <a href="http://www.bootcss.com/">
                        Bootstrap
                    </a>
                </dd>
            </dl>
            <dl>
                <dt>
                    常用网址
                </dt>
                <dd>
                    <a href="https://www.baidu.com/">
                        Baidu
                    </a>
                </dd>
                <dd>
                    <a href="https://www.zhihu.com/#signin">
                        知乎
                    </a>
                </dd>
                <dd>
                    <a href="https://www.douban.com/">
                        豆瓣
                    </a>
                </dd>
                <dd>
                    <a href="http://www.youku.com/">
                        Youku
                    </a>
                </dd>
            </dl>
            <dl>
                <dt>
                    API
                </dt>
                <dd>
                    <a href="http://www.nginx.cn/nginx-how-to">
                        Nginx
                    </a>
                </dd>
                <dd>
                    <a target="_blank" href="http://www.jb51.net/article/56448.htm">
                        Redis
                    </a>
                </dd>
                <dd>
                    <a target="_blank" href="#">
                        #####
                    </a>
                </dd>
                <dd>
                    <a target="_blank" href="#">
                        #####
                    </a>
                </dd>
            </dl>
            <dl>
                <dt>
                    CGW
                </dt>
                <dd>
                    <a href="#">
                        关于我
                    </a>
                </dd>
                <dd>
                    <a target="_blank" href="#">
                        $$$$$$$$$
                    </a>
                </dd>
                <dd>
                    <a target="_blank" href="#">
                        @@@@@@@@@@@@@
                    </a>
                </dd>
                <dd>
                    <a href="http://blog.csdn.net/u013850047">
                        Blog
                    </a>
                </dd>
            </dl>
        </div>
        <div class="footer-right">
            <div class="row share">
                <a href="javascript:void(0)" class="share-links weixinImg"></a>
                <a rel="nofollow" href="http://weibo.com/u/5384300941" target="_blank" class="share-links weiboImg"></a>
                <a rel="nofollow" href="https://github.com/ccccgw" target="_blank" class="share-links github"></a>
                <a rel="nofollow" href="http://blog.csdn.net/u013850047" target="_blank" class="share-links csdn"></a>
            </div>
            <img class="qrcode" src="${contextPath }/static/image/icon/qrcode.png" alt="APICloud">
        </div>
    </section>
    <section class="copyright">
        <label>
            ©2016&nbsp;CGWPub&nbsp;&nbsp;&nbsp;&nbsp;联系电话&nbsp;&nbsp;&nbsp;&nbsp;18758258547
        </label>
    </section>
</footer>
<!--footer结束-->


</body>

</html>