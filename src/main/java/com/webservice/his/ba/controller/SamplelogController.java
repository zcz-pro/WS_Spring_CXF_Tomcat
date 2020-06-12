package com.webservice.his.ba.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import java.util.HashMap;
import java.util.Map;


@Controller
@RequestMapping("/samplelog")
public class SamplelogController {

    @RequestMapping(value="/viewSampleLog")
    public ModelAndView viewSampleLog()throws Exception{
        ModelAndView mv = new ModelAndView();
        mv.setViewName("sample/query/sampleLogQuery");
        return mv;
    }

    @RequestMapping(value="/testRestfulIntface")
    @ResponseBody
    public Map testRestfulIntface()throws Exception{
        Map map=new HashMap();
        map.put("a","123");
        map.put("b","456");
        return map;
    }
}
