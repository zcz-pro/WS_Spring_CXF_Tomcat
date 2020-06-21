package com.webservice.his.ba.controller;

import com.webservice.his.ba.entity.SampleMaster;
import com.webservice.his.ba.eventlisteners.publish.EmailService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import org.springframework.web.servlet.ModelAndView;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.*;
import java.util.*;


@Controller
@RequestMapping("/oaToThird/updateThirdData")
public class OaToThirdUpdateController {
    @Resource
    private EmailService emailService;

    private String fileName;
    private File file;

    public String getFileName() {
        return fileName;
    }

    public void setFileName(String fileName) {
        this.fileName = fileName;
    }

    public File getFile() {
        return file;
    }

    public void setFile(File file) {
        this.file = file;
    }

    @RequestMapping(value="/onStop")
    @ResponseBody
    public SampleMaster onStop(HttpServletRequest request, HttpServletResponse response,@RequestBody Map params)throws Exception{
        System.out.println("终止时间开始");
        String a=request.getParameter("a");
        String aa=null;
        if(params.get("a")!=null){
            aa=params.get("a").toString();
        }
        Map map=new HashMap();
        map.put("a","123");
        map.put("b","456");
        System.out.println("终止时间结束");
        SampleMaster master=new SampleMaster();
        master.setLabNo("1111");
        return master;
    }

    @RequestMapping(value="/upload")
//    @ResponseBody
    public void upload(HttpServletRequest request, HttpServletResponse response,MultipartFile multipartFile)throws Exception{

        try {
            System.out.println("上传文件开始");
            String id=request.getParameter("id");
            String name=request.getParameter("name");
            String type=request.getParameter("type");
            String lastModifiedDate=request.getParameter("lastModifiedDate");
            String size=request.getParameter("size");

            String aaa=request.getParameter("file");
            System.out.println();
            MultipartHttpServletRequest multipartRequest = (MultipartHttpServletRequest) request;
            Map<String, MultipartFile> fileMap = multipartRequest.getFileMap();
            List<String> file_url_list = new ArrayList<>();
            String tempPath=request.getSession().getServletContext().getRealPath("/upload/");
            for (MultipartFile file:fileMap.values()) {
                String fileName=file.getOriginalFilename();// 文件原名称
                // 判断文件类型
                String type2=fileName.indexOf(".")!=-1?fileName.substring(fileName.lastIndexOf(".")+1, fileName.length()):null;
                if(fileName!=null&&fileName!=""){
                    String fileF = fileName.substring(fileName.lastIndexOf("."), fileName.length());//文件后缀
                    fileName = System.currentTimeMillis() + "_" + new Random().nextInt(1000) + fileF;//新的文件名

                    //先判断文件是否存在
                    File file1 =new File(tempPath);
                    //如果文件夹不存在则创建
                    if(!file1 .exists()  && !file1 .isDirectory()){
                        file1.mkdirs();
                    }
                    try {
                        file.transferTo(new File(tempPath+fileName));
                        //保存图片到 影像资料
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
            }
//
//            //获取明细分类
            request.setCharacterEncoding("UTF-8");
            response.setHeader("Access-Control-Allow-Origin", "*");
            //        response.setHeader("Access-Control-Allow-Origin", "http://localhost"); //允许来之域名为http://localhost的请求
            response.setHeader("Access-Control-Allow-Headers", "Origin,No-Cache, X-Requested-With, If-Modified-Since, Pragma, Last-Modified, Cache-Control, Expires, Content-Type, X-E4M-With, userId, token");
            response.setHeader("Access-Control-Allow-Methods", "POST, GET, OPTIONS, DELETE"); //请求允许的方法
            response.setHeader("Access-Control-Max-Age", "3600");    //身份认证(预检)后，xxS以内发送请求不在需要预检，既可以直接跳过预检，进行请求(前面只是照猫画虎，后面才理解)
                    SampleMaster master=new SampleMaster();
                    master.setLabNo("1111");
//                    return master;
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @RequestMapping(value="/upload2")
    public ModelAndView upload2(HttpServletRequest request)throws Exception{
        ModelAndView model=new ModelAndView();
        model.setViewName("index");
        return model;
    }

    /**
     * 下载文件
     * @param request
     * @param response
     * @param params
     * @throws Exception
     */
    @RequestMapping("/downFile")
    public void downFile(HttpServletRequest request, HttpServletResponse response, @RequestBody HashMap<String,Object> params) throws Exception {
        try {
            String url="C:\\Users\\admin\\Pictures\\Saved Pictures\\003GJGYegy6Gyh4Rcut89.jpg";
            File file=new File(url);
            if(file.exists()) {
                InputStream fis = new BufferedInputStream(new FileInputStream(url));
                byte[] buffer = new byte[fis.available()];
                fis.read(buffer);
                fis.close();
                // 清空response
                response.reset();
                // 设置response的Header
                response.addHeader("Content-Disposition", "attachment;filename=" + new String(file.getName().getBytes()));
                response.addHeader("Content-Length", "" + file.length());
                OutputStream toClient = new BufferedOutputStream(response.getOutputStream());
//                response.setContentType("application/octet-stream");
                response.setContentType("application/octet-stream");
                toClient.write(buffer);
                toClient.flush();
                toClient.close();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @RequestMapping(value="/testEvent")
    public void testEvent(HttpServletRequest request)throws Exception{
        List<String> list=new ArrayList<>();
        list.add("127.0.0.2");
        emailService.setBlackList(list);
        emailService.sendEmail("127.0.0.2","this is a syn event listener");
    }
}
