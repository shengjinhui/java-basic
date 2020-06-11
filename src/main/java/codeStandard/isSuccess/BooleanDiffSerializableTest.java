package codeStandard.isSuccess;

import com.alibaba.fastjson.JSON;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.gson.Gson;

import java.io.IOException;
import java.io.Serializable;

/**
 * @Author: yichuan
 * @Date: 2020/6/11 10:08 上午
 * @Description: 使用不同的序列化工具, 对于isSuccess属性的结果也是不同的
 */
public class BooleanDiffSerializableTest {
    public static void main(String[] args) throws IOException {
        //定一个Model3类型
        Model3 model3 = new Model3();
        model3.setSuccess(true);

        /**
         * 使用fastjson(1.2.16)序列化model3成字符串并输出
         *
         * result => Serializable Result With fastjson :{"hollis":"hollischuang","success":true}
         * reason => fastjson的序列化原理是通过反射遍历类的getter方法
         */
        System.out.println("Serializable Result With fastjson :" + JSON.toJSONString(model3));


        /**
         * 使用Gson(2.8.5)序列化model3成字符串并输出
         *
         * result => Serializable Result With Gson :{"isSuccess":true}
         * reason => Gson序列化原理是通过反射遍历类的属性
         */
        System.out.println("Serializable Result With Gson :" + (new Gson()).toJson(model3));

        /**
         * 使用jackson(2.9.7)序列化model3成字符串并输出
         *
         * result => Serializable Result With jackson :{"success":true,"hollis":"hollischuang"}
         * reason => 同fastJson
         */
        ObjectMapper om = new ObjectMapper();
        System.out.println("Serializable Result With jackson :" + om.writeValueAsString(model3));
    }
}

class Model3 implements Serializable {
    private static final long serialVersionUID = 1836697963736227954L;
    private boolean isSuccess;

    public boolean isSuccess() {
        return isSuccess;
    }

    public void setSuccess(boolean success) {
        isSuccess = success;
    }

    public String getHollis() {
        return "hollischuang";
    }

    @Override
    public String toString() {
        return "Model3{" +
                "isSuccess=" + isSuccess +
                '}';
    }

}
