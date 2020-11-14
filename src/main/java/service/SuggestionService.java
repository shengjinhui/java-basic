package service;

import com.google.common.collect.Lists;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * @Author: yichuan
 * @Date: 2020/11/12 4:49 下午
 * @Description:
 */
@Component
public class SuggestionService {

    public List<String> getSuggestions() {
        return Lists.newArrayList("food", "basketball", "car", "cloth", "class");
    }
}
