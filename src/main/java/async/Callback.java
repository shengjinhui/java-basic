package async;

/**
 * @Author: yichuan
 * @Date: 2020/11/12 5:04 下午
 * @Description:
 */
public interface Callback<T> {
    void onSuccess(T t);

    void onError(Throwable error);
}
