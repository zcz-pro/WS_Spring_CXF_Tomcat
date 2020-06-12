<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html lang="en">
<html>
<head>
    <title>法律咨询意见反馈</title>
    <!--文件上传-->
    <link rel="stylesheet" type="text/css" href="/webuploader/webuploader.css">

    <script src="/static/js/jquery.js"></script>
    <script src="/static/js/angularjs/angular.min.js"></script>
    <script src="/static/js/index.controller.js"></script>
    <!--文件上传-->
    <script src="/webuploader/webuploader.js"></script>

</head>
<body ng-app="zjjt">
<div ng-controller="IndexCtrl" id="indexCtrl" style="display: none;">
    <h2 ng-click="downFile();"><a href="javascript:void(0);">下载</a></h2>
    <h2>Hello Worldsssss!</h2>
    <form class="form-horizontal m-t" id="commentForm">
        <div id="uploader" class="wu-example">
            <!--用来存放文件信息-->
            <div id="thelist" class="uploader-list"></div>
            <div class="btns">
                <div id="picker" style="display: inline-block;line-height: 1.1;vertical-align: middle;margin: 0 12px 0 0;">选择文件</div>
                <button id="ctlBtn" class="btn btn-default" style="border-color:#ccc;background-image:linear-gradient(to bottom,#fff 0,#e0e0e0 100%);text-shadow:0 1px 0 #fff;color:#333;">开始上传</button>
            </div>
        </div>
    </form>

</div>
<script type="text/javascript">
    //        var BASE_URL='http://localhost:8008/';
    var uploader = WebUploader.create({

        // swf文件路径
        swf: 'js/plugins/webuploader/Uploader.swf',

        // 文件接收服务端。
        server: '/oaToThird/updateThirdData/upload',
        //为true，不需要手动调用上传，有文件选择即开始上传。
        auto: false,
        //限制的文件数量
        //fileNumLimit: 2,
        //指明使用二进制的方式上传文件
//        sendAsBinary:true,
        //单个文件的大小限制,
        fileSingleSizeLimit: 5242880000,
        multiple:false,
        // 选择文件的按钮。可选。
        // 内部根据当前运行是创建，可能是input元素，也可能是flash.
        pick: '#picker',

        // 不压缩image, 默认如果是jpeg，文件上传前会压缩一把再上传！
        resize: false,
        accept: {
            title: 'intoTypes',
            extensions: '*',  //指定可接受的后缀 ，后缀为这些时严重通过，所有都通过指定为 *
            mimeTypes: '.*'  //mime类型
        },
        duplicate :true     //去重， 根据文件名字、文件大小和最后修改时间来生成hash Key.
    });
    // 当有文件被添加进队列的时候
    uploader.on( 'fileQueued', function( file ) {
        $("#thelist").html( '<div id="' + file.id + '" class="item">' +
            '<h4 class="info">' + file.name + '</h4>' +
            '<p class="state">等待上传...</p>' +
            '</div>' );
    });
    uploader.on('uploadBeforeSend', function(obj, data, headers) {
//            $.extend(headers, {
//                "Origin": "http://10.101.17.6:8120",
//                "Access-Control-Request-Method": "POST"
//            });
    });
    // 文件上传过程中创建进度条实时显示。
    uploader.on( 'uploadProgress', function( file, percentage ) {
        var $li = $( '#'+file.id ),
            $percent = $li.find('.progress .progress-bar');

        // 避免重复创建
        if ( !$percent.length ) {
            $percent = $('<div class="progress progress-striped active">' +
                '<div class="progress-bar" role="progressbar" style="width: 0%">' +
                '</div>' +
                '</div>').appendTo( $li ).find('.progress-bar');
        }

        $li.find('p.state').text('上传中');

        $percent.css( 'width', percentage * 100 + '%' );
    });
    uploader.on( 'uploadSuccess', function( file,response) {
        $( '#'+file.id ).find('p.state').text('已上传');
    });

    uploader.on( 'uploadError', function( file ) {
        $( '#'+file.id ).find('p.state').text('上传出错');
    });

    uploader.on( 'uploadComplete', function( file ) {
        $( '#'+file.id ).find('.progress').fadeOut();
    });
    $("#ctlBtn").on('click', function() {
        uploader.upload();
    });
</script>
</body>
</html>
