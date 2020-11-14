package thread.threadlocal;

import lombok.AllArgsConstructor;
import lombok.Data;
import org.aspectj.weaver.ast.Var;

/**
 * @Author: yichuan
 * @Date: 2020/11/12 11:51 下午
 * @Description: 典型案例2 当前信息需要被线程内所有方法共享
 */
public class ThreadLocalNormalUsage10 {
    public static void main(String[] args) {
        Service1 service1 = new Service1();
        service1.process();

        Service2 service2 = new Service2();
        service2.process();

        Service3 service3 = new Service3();
        service3.process();
    }


}

class Service1 {
    public void process() {
        User user = new User("sjh");
        UserContextHolder.holder.set(user);
    }
}


class Service2 {
    public void process() {
        User user = UserContextHolder.holder.get();
        System.out.println("Service2拿到用户名: " + user.getName());
    }
}

class Service3 {
    public void process() {
        User user = UserContextHolder.holder.get();
        System.out.println("Service3拿到的用户名: " + user.getName());
        UserContextHolder.holder.remove();
    }
}

@AllArgsConstructor
@Data
class User {
    private String name;
}

class UserContextHolder {
    // 注意:这里不用通过initialValue初始化,后面会通过set初始化的
    public static ThreadLocal<User> holder = new ThreadLocal<>();
}
