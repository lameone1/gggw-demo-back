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
    <title>Me</title>
    <!-- Bootstrap -->
    <link href="${contextPath }/css/bootstrap/bootstrap.css" rel="stylesheet" type="text/css">
    <!--myCSS>-->
    <link href="${contextPath }/css/main.css" rel="stylesheet">
    <link href="${contextPath }/css/common.css" rel="stylesheet">


    <!-- jQuery (necessary for Bootstrap's JavaScript plugins) -->
    <script type="text/javascript" src="../../js/bootstrap/jquery.js"></script>
    <!-- Include all compiled plugins (b\js\bootstrap\jquery.jselow), or include individual files as needed -->
    <script type="text/javascript" src="../../js/bootstrap/bootstrap.min.js"></script>

    <!-- HTML5 shim and Respond.js for IE8 support of HTML5 elements and media queries -->
    <!-- WARNING: Respond.js doesn't work if you view the page via file:// -->
    <!--   [if lt IE 9]  >
    <script src="//cdn.bootcss.com/html5shiv/3.7.2/html5shiv.min.js"></script>
    <script src="//cdn.bootcss.com/respond.js/1.4.2/respond.min.js"></script>
    <!  [endif]-->

    <!--myJS-->
    <script>

        $(function () {
            $('[data-toggle="popover"]').popover()
        })

        var p = 0;

        function run(){
            p+=1;
            $("#baxyt").css("width",p+"%");
            if(p<100){
                var timer=setTimeout("run()",100);
            }else{
                alert("");
            }
        }
    </script>
</head>

<body>




<!--navigationå¼å§    style="background-color: #eeeeee"-->
<div class="navbar light navbar-fixed-top affix-top "  style="" id="mainNav" >
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
                    <a href="${contextPath }/ui/front/index.jsp">Home</a>
                </li>
                <li>
                    <a  href="#">You</a>
                </li>
                <li>
                    <a href="#">Me</a>
                </li>
                <li>
                    <a href="#">little thing</a>
                </li>
                <li>
                    <a href="#">Setting</a>
                </li>
            </ul>
        </div>
    </div>
</div>
<!--navigationç»æ-->

<div class="section" style="margin-top:40px ">
    <div class="container">
        <div class="row">
            <div class="col-md-12">
                <div class="jumbotron">
                    <h1>你特么逼是猪啊 . </h1>
                    <br>
                    <br>
                    <p>你特么逼绝逼是头麻瓜煞笔臭猪</p>
                    <br>
                    <p><a class="btn btn-primary btn-lg"  role="button" data-toggle="collapse" href="#collapseExample" aria-expanded="false" aria-controls="collapseExample">Learn more</a></p>
                    <div class="collapse" id="collapseExample">
                        <div class="well">
                            Anim pariatur cliche reprehenderit, enim eiusmod high life accusamus terry richardson ad squid. Nihil anim keffiyeh helvetica, craft beer labore wes anderson cred nesciunt sapiente ea proident.
                        </div>
                    </div>



                    <button type="button" class="btn btn-lg btn-danger" data-toggle="popover" title="Popover title" data-content="And here's some amazing content. It's very engaging. Right?">ttttttttt</button>

                    <button type="button" class="btn btn-default" data-container="body" data-toggle="popover" data-placement="left" data-content="Vivamus sagittis lacus vel augue laoreet rutrum faucibus.">
                        确定
                    </button>

                </div>
                <div class="alert alert-warning alert-dismissible" role="alert">
                    <button type="button" class="close" data-dismiss="alert" aria-label="Close"><span aria-hidden="true">&times;</span></button>
                    <strong>Warning!</strong> <a href="#" class="alert-link"> Better check yourself, you're not looking too good. </a>
                </div>
            </div>
        </div>

   <!--     <div class="modal fade" id="myModal1" tabindex="-1" role="dialog"
             aria-labelledby="myModalLabel" aria-hidden="true" data-backdrop="static" style="width:300px">
            <div class="modal-dialog">
                <div class="modal-content" >
                    <span style="text-align:center;color:red">æä»¶æ­£å¨ä¸ä¼ è¯·å¿å·æ°é¡µé¢ï¼</span><br />

                    <div class="progress progress-striped active">
                        <div class="bar">
                        </div>
                    </div>
                </div>
            </div>
        </div>
        <!-- Button trigger modal
        <button type="button" class="btn btn-primary btn-lg" data-toggle="modal" data-target="#myModal1" id="BtnSubmit">
            Launch demo modal
        </button>-->
    </div>
</div>


<div class="section">
    <div class="container">
        <div class="row">
            <div class="col-md-6">
                <div class="jumbotron">
                    <h1>Hello, world!</h1>
                    <p>...</p>
                    <p><a class="btn btn-primary btn-lg" href="#" role="button">Learn more</a></p>
                </div>
                <div class="alert alert-info alert-dismissible" role="alert">

                    <strong>Warning!</strong> <a href="#" class="alert-link"> Better check yourself, you're not looking too good. </a>
                </div>
            </div>
            <div class="col-md-6">
                <h1 class="text-primary">bbbbbb <span class="label label-success">Success <span class="badge">4</span> </span></h1>
                <h3 class="text-muted">aaaaaaaaaa</h3>
                <div class="progress">
                    <div id="baxyt" class="progress-bar progress-bar-striped active" role="progressbar" aria-valuenow="0" aria-valuemin="0" aria-valuemax="100" style="width:0%">
                        <span class="sr-only"></span>
                    </div>
                </div>
                <div class="list-group">
                    <a href="#" class="list-group-item active">
                        <h4 class="list-group-item-heading">List group item heading</h4>
                        <p class="list-group-item-text">...</p>
                    </a>
                    <a href="#" class="list-group-item">
                        <h4 class="list-group-item-heading">List group item heading</h4>
                        <p class="list-group-item-text">...</p>
                    </a>
                    <a href="java" class="list-group-item">
                        <h4 class="list-group-item-heading">List group item heading</h4>
                        <p class="list-group-item-text">...</p>
                    </a>
                </div>
            </div>
        </div>
    </div>
</div>


<!--footerå¼å§-->
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
                    常用网站
                </dt>
                <dd>
                    <a href="https://www.baidu.com/">
                        Baidu
                    </a>
                </dd>
                <dd>
                    <a href="https://www.zhihu.com/#signin">
                        知乎
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
                       aaaa
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
            <img class="qrcode" src="../../image/icon/qrcode.png" alt="APICloud">
        </div>
    </section>
    <section class="copyright">
        <label>
            Â©2016&nbsp;CGWPub&nbsp;&nbsp;&nbsp;&nbsp;èç³»çµè¯&nbsp;&nbsp;&nbsp;&nbsp;18758258547
        </label>
    </section>
</footer>
<!--footerç»æ-->

</body>

</html>