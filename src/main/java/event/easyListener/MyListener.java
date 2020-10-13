package event.easyListener;

/**
 * @Author: yichuan
 * @Date: 2020/9/27 3:44 下午
 * @Description:
 */
public class MyListener implements EventListener {

    @Override
    public void handleEvent(Event event) {
        event.callback();
    }
}
