package com.webservice.his.ba.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;


@Controller
@RequestMapping("/samplelog")
public class SamplelogController {

    @RequestMapping(value="/viewSampleLog")
    public ModelAndView viewSampleLog()throws Exception{
        ModelAndView mv = new ModelAndView();
        mv.setViewName("sample/query/sampleLogQuery");
        return mv;
    }
}
