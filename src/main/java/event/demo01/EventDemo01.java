package event.demo01;

import java.util.ArrayList;
import java.util.List;

/**
 * @Author: yichuan
 * @Date: 2020/4/9 10:36 上午
 * @Description:
 */
public class EventDemo01 {
    public static void main(String[] args) {
       List<String> list =  new ArrayList();
       list.add("测试");
       list.add("a");
       list.add("b");
       list.add("c");
       list.add("d");
       list.forEach( e -> {
           if (e.equals("b")){
               return;
           }
           System.out.println(e);
       });

    }
}
