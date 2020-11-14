package thread.immutable;

import lombok.Data;

/**
 * @Author: yichuan
 * @Date: 2020/11/14 12:01 下午
 * @Description: final变量赋值演示, 演示类final变量的赋值方式
 */
@Data
public class FinalVariableDemo {
    //1. 等号右边直接赋值
    // private final int a = 6;

    private final int a;
    //  2. 构造器中赋值
    //    public FinalVariableDemo(int a) {
    //        this.a = a;
    //    }

    // 3. 在代码块中赋值
    {
        a = 6;
    }


    private  int b;


    public FinalVariableDemo() {

    }
}
