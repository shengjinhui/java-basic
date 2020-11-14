package async;

import com.google.common.collect.Lists;
import io.reactivex.Flowable;
import lombok.AllArgsConstructor;
import lombok.Data;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.util.CollectionUtils;
import service.FavoriteService;
import service.SuggestionService;
import service.UserService;

import java.util.List;

/**
 * @Author: yichuan
 * @Date: 2020/11/12 4:47 下午
 * @Description:
 */
@Component
public class RxJavaTest {
    @Autowired
    private UserService userService;

    @Autowired
    private SuggestionService suggestionService;

    @Autowired
    private FavoriteService favoriteService;

    @Autowired
    private StreamTest streamTest;

    public String getTop5Goods(Long userId) {

        userService.getFavorites(userId, new Callback<List<String>>() {

            @Override
            public void onSuccess(List<String> list) {
                if (CollectionUtils.isEmpty(list)) {
                }
            }

            @Override
            public void onError(Throwable error) {

            }
        });

        return null;
    }


    public void filterPerson() {
        List<Person> personList = makeList();
        Flowable.fromArray(personList.toArray(new Person[0]))
                .filter(person -> person.getAge() >= 1)
                .map(person -> person.getName())
                .subscribe(System.out::println);

    }

    private List<Person> makeList() {
        return Lists.newArrayList(new Person(1, "sjh"), new Person(2, "haha"));
    }


    public void rpcCall() throws InterruptedException {
        streamTest.rpcCallByReactiveStream();
    }


    @Data
    @AllArgsConstructor
    class Person {
        private Integer age;
        private String name;

    }
}
