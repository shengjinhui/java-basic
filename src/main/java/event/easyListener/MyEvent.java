package event.easyListener;

import lombok.extern.java.Log;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.logging.LogFactory;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * @Author: yichuan
 * @Date: 2020/9/27 3:43 下午
 * @Description:
 */
@Slf4j
public class MyEvent implements Event {

    private SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");

    private Object source;

    private Date when;

    private String message;

    @Override
    public Object getSource() {
        return source;
    }

    public void setSource(Object source) {
        this.source = source;
    }

    @Override
    public Date getWhen() {
        return when;
    }

    public void setWhen(Date when) {
        this.when = when;
    }

    @Override
    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    @Override
    public void callback() {
        log.info(toString());
    }

    @Override
    public String toString() {
        return "source: " + getSource() + ", message: " + getMessage() + ", when: " + sdf.format(getWhen());
    }
}