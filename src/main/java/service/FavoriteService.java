package service;

import com.google.common.collect.Lists;
import org.springframework.stereotype.Component;

/**
 * @Author: yichuan
 * @Date: 2020/11/12 4:51 下午
 * @Description:
 */
@Component
public class FavoriteService {
    public String getDetails(String favId) {
        System.out.println(">>>>>>>>" + favId + "getDetail << <<<<<<<<<<<<<");
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return favId + "detail info";
    }
}
