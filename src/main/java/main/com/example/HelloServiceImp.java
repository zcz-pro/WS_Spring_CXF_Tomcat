package main.com.example;

/**
 * Created by admin on 2020/4/3.
 */
public class HelloServiceImp implements HelloService {
    @Override
    public String sayHello(String name) {
        return "大家好，我是"+name;
    }
}
