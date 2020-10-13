package event.easyListener;

/**
 * @Author: yichuan
 * @Date: 2020/9/27 3:45 下午
 * @Description:
 */
public interface EventSource {

    /**
     * 增加监听器
     * @param listener
     */
    void addListener(EventListener listener);

    /**
     * 通知监听器
     */
    void notifyListener();
}