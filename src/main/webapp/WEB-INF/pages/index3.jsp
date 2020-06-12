<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html lang="en">
<html>
<head>
    <title>法律咨询意见反馈</title>
    <!--文件上传-->
    <link rel="stylesheet" type="text/css" href="/webuploader/webuploader.css">

    <script src="/static/js/jquery.js"></script>
    <!--文件上传-->
    <script src="/webuploader/webuploader.min.js"></script>
</head>
<body>
<h2>Hello Worldsssss!</h2>
<form class="form-horizontal m-t" id="commentForm">
    <div class = "row">
        <div class="btns col-sm-2">
            <div id="picker">选择文件</div>
            <button id="ctlBtn" class="btn default-btn">开始上传</button>
        </div>
        <!--用来存放文件信息-->
        <div id="thelist" class="uploader-list col-sm-10"></div>
    </div>
</form>
<script type="text/javascript">
    $(function(){
        var uploader = WebUploader.create({
            // 选完文件后，是否自动上传。
            auto: false,
            // 文件接收服务端。
            server: '/oaToThird/updateThirdData/upload',
            // 选择文件的按钮。可选。
            // 内部根据当前运行是创建，可能是input元素，也可能是flash.
            pick: '#picker',
            // 不压缩image, 默认如果是jpeg，文件上传前会压缩一把再上传！
            resize: false,
            // 只允许选择图片文件。
            accept: {
                title: 'Images',
                extensions: '*',  //指定可接受的后缀 ，后缀为这些时严重通过，所有都通过指定为 *
                mimeTypes: '.*'  //mime类型
            },
            /* fileSizeLimit :10, //验证文件总大小是否超出限制, 超出则不允许加入队列
             fileSingleSizeLimit :10,   //验证单个文件大小是否超出限制, 超出则不允许加入队列。 */
            duplicate :true //去重， 根据文件名字、文件大小和最后修改时间来生成hash Key.

        });


        // 当文件被加入队列之前触发，此事件的handler返回值为false，则此文件不会被添加进入队列。
        uploader.on( 'beforeFileQueued', function( file ) {
            // 限制图片数量
            img_length = $("#thelist img").length;
            if (img_length >= 6) {
                layer.msg("图片最多上传6张");
                return false;
            }

        });

        // 当有文件添加进来的时候
        uploader.on( 'fileQueued', function( file ) {
            var $li = $(
                    '<div id="' + file.id + '" class="file-item thumbnail col-sm-3" style="width:150px;margin-left:10px;">' +
                    '<img>' +
                    '<div class="info">' + file.name + '</div>' +
                    '<span style="margin-left: 78%;cursor:pointer;" onclick="deleteFile(this)">删除</span>' +
                    '</div>'
                ),
                $img = $li.find('img');
            // $list为容器jQuery实例
            $("#thelist").append( $li );
            // 创建缩略图
            // 如果为非图片文件，可以不用调用此方法。
            // thumbnailWidth x thumbnailHeight 为 100 x 100
            uploader.makeThumb( file, function( error, src ) {
                if ( error ) {
                    $img.replaceWith('<span>不能预览</span>');
                    return;
                }
                $img.attr( 'src', src );
            }, 150, 150 );

        });

        // 文件上传成功，给item添加成功class, 用样式标记上传成功。
        uploader.on( 'uploadSuccess', function( file ) {
            $( '#'+file.id ).addClass('upload-state-done');
            var $li = $( '#'+file.id ),
                $done = $li.find('div.upload-state-done');
            // 避免重复创建
            if ( !$done.length ) {
                $done = $('<div class=""></div>').appendTo( $li );
            }
            $done.html('<font color="blue">上传成功</font>');
        });

        // 文件上传失败，显示上传出错。
        uploader.on( 'uploadError', function( file ) {
            var $li = $( '#'+file.id ),
                $error = $li.find('div.error');
            // 避免重复创建
            if ( !$error.length ) {
                $error = $('<div class="error"></div>').appendTo( $li );
            }
            $error.html('<font color="red">上传失败</font>');
        });

        $("#ctlBtn").click(function(){
            uploader.upload();
        })

    })

    function deleteFile(obj) {
        $(obj).parent().remove();
    }
</script>
</body>
</html>
