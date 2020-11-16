package thread.immutable;

/**
 * @Author: yichuan
 * @Date: 2020/11/14 12:41 下午
 * @Description:
 */
public class FinalStringDemo {
    public static void main(String[] args) {
        String a = "wukong2";
        final String b = "wukong";

        String d = "wukong";

        String c = b + 2;
        String e = d + 2;
        System.out.println(a == c);
        System.out.println(a == e);
    }
}
