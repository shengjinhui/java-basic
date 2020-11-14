package thread.immutable;

/**
 * @Author: yichuan
 * @Date: 2020/11/14 11:51 上午
 * @Description:
 */
public class FinalMain {
    public static void main(String[] args) {
        final Person person = new Person();
        //  person = new Person(); //这里会报错,编译器过不去 被final的修饰的变量,值不能改变,如果变量为对象,即对应的引用不能改变
//        person.age = 20; // 注意: 对象里面的内容是可以变的
        person.finalVariableDemo.setB(20);
    }
}

class Person {
    final String name = "sjh";
    final Integer age = 18;

    final FinalVariableDemo finalVariableDemo = new FinalVariableDemo();
}