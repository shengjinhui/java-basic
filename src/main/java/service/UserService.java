package service;

import async.Callback;
import com.google.common.collect.Lists;
import org.springframework.stereotype.Component;
import org.springframework.util.CollectionUtils;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @Author: yichuan
 * @Date: 2020/11/12 4:47 下午
 * @Description:
 */
@Component
public class UserService {
    static Map<Long, List<String>> user2Favorites = new HashMap<>();

    static {
        List<String> goodList = Lists.newArrayList("mac", "iwatch", "ipad", "imac", "airpod", "mac pro");
        List<String> goodList2 = Lists.newArrayList("food1", "food1", "food1", "food1", "food1", "food1");
        user2Favorites.put(1L, goodList);
        user2Favorites.put(2L, goodList2);

    }

    public List<String> getFavorites(Long userId, Callback<List<String>> callback) {
        List<String> result = user2Favorites.get(userId);
        try {
            callback.onSuccess(result);
        } catch (Exception e) {
            callback.onError(e);
        }
        return result;
    }
}
