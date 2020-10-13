package event.easyListener;

import java.io.Serializable;
import java.util.Date;
import java.util.Objects;

/**
 * @Author: yichuan
 * @Date: 2020/9/27 3:42 下午
 * @Description: 事件载体
 */
public interface Event extends Serializable {

    Object getSource();

    Date getWhen();

    String getMessage();

    /**
     * 事件回调方法
     */
    void callback();
}