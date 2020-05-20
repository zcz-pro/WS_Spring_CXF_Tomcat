package main.com.example;

import javax.jws.WebParam;
import javax.jws.WebResult;
import javax.jws.WebService;

@WebService
public interface AnalysisXmlService {

    @WebResult(name="findInfoResult")
    public String  findInfo(@WebParam(name = "content") String content);
}