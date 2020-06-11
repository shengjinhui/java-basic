package codeStandard.isSuccess;

import com.alibaba.fastjson.JSON;
import com.google.gson.Gson;

import java.io.IOException;
import java.io.Serializable;
import java.util.StringJoiner;

/**
 * @Author: yichuan
 * @Date: 2020/6/11 10:26 上午
 * @Description:
 */
public class SerializableIsSuccessByJsonAndGson {
    public static void main(String[] args) throws IOException {
        /**
         * result => Model3{isSuccess=false}
         * reason => 因为JSON框架通过扫描所有的getter后发现有一个isSuccess方法，然后根据JavaBeans的规范，解析出变量名为success，把model对象序列化城字符串后内容为{"success":true}。
         * 根据{"success":true}这个json串，Gson框架在通过解析后，通过反射寻找Model类中的success属性，但是Model类中只有isSuccess属性，所以，最终反序列化后的Model类的对象中，isSuccess则会使用默认值false。
         */
        Model3 model3 = new Model3();
        model3.setSuccess(true);
        Gson gson = new Gson();
        System.out.println(gson.fromJson(JSON.toJSONString(model3), Model3.class));
    }
}


