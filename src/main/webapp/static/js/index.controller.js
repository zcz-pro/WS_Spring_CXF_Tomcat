/**
 * 控制台控制器
 *
 * Created by on 2017/11/24
 */
angular.module('zjjt',[])
    .controller('IndexCtrl', function ($scope, $http) {
        $("#indexCtrl").css("display","block");//显示内容
        $scope.downFile=function () {
            var url='/oaToThird/updateThirdData/downFile';
            return $http({
                url: url,
                method: "POST",
                data: {},
                responseType: 'arraybuffer'
            }).success(function (data) {
                // document.location.href ="https://timgsa.baidu.com/timg?image&quality=80&size=b9999_10000&sec=1591697789690&di=98336d9cce896739e5aa49996943e7ff&imgtype=0&src=http%3A%2F%2Fa2.att.hudong.com%2F36%2F48%2F19300001357258133412489354717.jpg";

                // request();

                var blob = new Blob([data], {type: "application/octet-stream"});// 这种是xls格式
                var objectUrl = URL.createObjectURL(blob);
                var a = document.createElement('a');
                document.body.appendChild(a);
                a.setAttribute('href', objectUrl);
                a.setAttribute('style', 'display:none');
                a.setAttribute('download', '文件.jpg');
                a.click();
                URL.revokeObjectURL(objectUrl);


                // var picurl='https://timgsa.baidu.com/timg?image&quality=80&size=b9999_10000&sec=1591697789690&di=98336d9cce896739e5aa49996943e7ff&imgtype=0&src=http%3A%2F%2Fa2.att.hudong.com%2F36%2F48%2F19300001357258133412489354717.jpg';
                // var blob=new Blob([''], {type:'application/octet-stream'});
                // var url = URL.createObjectURL(blob);
                // var a = document.createElement('a');
                // a.href =picurl
                // a.download = picurl.replace(/(.*\/)*([^.]+.*)/ig,"$2").split("?")[0];
                // var e = document.createEvent('MouseEvents');
                // e.initMouseEvent('click', true, false, window, 0, 0, 0, 0, 0, false, false, false, false, 0, null);
                // a.dispatchEvent(e);
                // URL.revokeObjectURL(url);
            });
        }


        function request () {
            const req = new XMLHttpRequest();
            req.open('POST', '/oaToThird/updateThirdData/downFile', true);
            req.responseType = 'blob';
            req.setRequestHeader('Content-Type', 'application/json');
            req.onload = function() {
                const data = req.response;
                const a = document.createElement('a');
                const blob = new Blob([data]);
                const blobUrl = window.URL.createObjectURL(blob);
                download(blobUrl) ;
            };
            req.send('{}');
        };
        function download(blobUrl) {
            const a = document.createElement('a');
            a.style.display = 'none';
            a.download = '测试下载.jpg';
            a.href = blobUrl;
            a.click();
            // document.body.removeChild(a);
        }
    });