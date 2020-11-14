package util.collection;


import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import javafx.util.Pair;
import org.apache.commons.beanutils.ConvertUtils;
import org.springframework.util.CollectionUtils;
import org.springframework.util.StringUtils;

import java.util.*;
import java.util.function.Function;
import java.util.stream.Collectors;

public class ListHelper {
    /**
     * list转map
     *
     * @param list
     * @param keyMapper
     * @param <K>
     * @param <V>
     * @return
     */
    public static <K, V> Map<K, V> toMap(Collection<V> list, Function<V, K> keyMapper) {
        if (CollectionUtils.isEmpty(list)) {
            return Maps.newHashMap();
        }
        return list.stream()
                .collect(Collectors.toMap(
                        keyMapper,
                        Function.identity(),
                        (a, b) -> a
                ));
    }

    /**
     * list进行map重组
     *
     * @param list
     * @param mapper
     * @param <O>
     * @param <V>
     * @return
     */
    public static <O, V> List<V> map(Collection<O> list, Function<O, V> mapper) {
        if (CollectionUtils.isEmpty(list)) {
            return Lists.newArrayList();
        }
        return list.stream().map(mapper).filter(Objects::nonNull).collect(Collectors.toList());
    }

    /**
     * 分割一个ArrayList，返回包含多个ArrayList(指定size)的一个大ArrayList
     * 结果list的最后一个集合可能小于切割的大小
     *
     * @param list      原始集合
     * @param splitSize 分割大小
     * @param <T>       集合类型
     * @return 结果接list<list>
     */
    public static <T> List<List<T>> splitList(List<T> list, int splitSize) {
        List<List<T>> listArray = new ArrayList<>();

        ArrayList<T> loopList = new ArrayList<>();
        for (T element : list) {
            loopList.add(element);
            if (splitSize == loopList.size()) {
                listArray.add(loopList);
                loopList = new ArrayList<>();
            }
        }

        if (0 != loopList.size()) {
            listArray.add(loopList);
        }

        return listArray;
    }

    /**
     * 将一个按照固定分隔符分割开的字符串，切分成指定类型的list的工具类
     *
     * @param source    源字符串
     * @param separator 切分字符串的分割符
     * @param clazz     切分后的list的类型
     * @param <T>       指定类型的枚举
     * @return result
     */
    @SuppressWarnings("unchecked")
    public static <T> List<T> string2List(String source, String separator, Class<T> clazz) {
        List<T> result = new ArrayList<>();
        try {
            if (StringUtils.isEmpty(source)) {
                return result;
            }
            String[] temp = StringUtils.split(source, separator);
            for (String s : temp) {
                if (clazz.equals(String.class)) {
                    result.add((T) s);
                } else {
                    result.add((T) ConvertUtils.convert(s, clazz));
                }
            }

        } catch (Exception e) {
            throw new IllegalArgumentException("field can't convert to list, sourceString:" + source);
        }
        return result;
    }

    @SuppressWarnings("unchecked")
    public static <T> Pair<T, T> string2Pair(String source, String separator, Class<T> clazzK) {
        try {
            if (StringUtils.isEmpty(source)) {
                return null;
            }
            List<T> list = string2List(source, separator, clazzK);
            if (CollectionUtils.isEmpty(list)) {
                return null;
            }
            if (list.size() == 1) {
                return new Pair<>(list.get(0), list.get(0));
            } else if (list.size() == 2) {
                return new Pair<>(list.get(0), list.get(1));
            } else {
                return null;
            }
        } catch (Exception e) {
            throw new IllegalArgumentException("field can't convert to list, sourceString:" + source);
        }
    }

    public static <K, V> Map<K, List<V>> groupBy(Collection<V> collection, Function<V, K> keyMapper) {
        if (CollectionUtils.isEmpty(collection)) {
            return Maps.newHashMap();
        }
        return collection.stream()
                .collect(Collectors.groupingBy(keyMapper));
    }
}