package event.easyListener;

/**
 * @Author: yichuan
 * @Date: 2020/9/27 3:44 下午
 * @Description:
 */
public interface EventListener {

    /**
     * 事件触发
     * @param event
     */
    void handleEvent(Event event);
}
