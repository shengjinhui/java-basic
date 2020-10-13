package util.collection;

import com.google.common.collect.Lists;
import com.google.common.collect.Sets;
import org.apache.commons.lang3.StringUtils;


import java.util.List;
import java.util.Objects;
import java.util.Set;
import java.util.function.Function;
import java.util.stream.Collectors;
import java.util.stream.Stream;


public class StringHelper {

    public static List<String> splitToStringList(String str) {
        return splitToList(str, ",", Objects::toString);
    }

    public static List<String> splitToStringListAndTrim(String str) {
        return splitToList(str, ",", e -> e.trim());
    }

    public static List<Long> splitToLongList(String str) {
        return splitToList(str, ",", Long::parseLong);
    }

    public static List<Integer> splitToIntegerList(String str) {
        return splitToList(str, ",", Integer::parseInt);
    }

    public static Set<String> splitToStringSet(String str) {
        return splitToSet(str, ",", Objects::toString);
    }

    public static Set<Long> splitToLongSet(String str) {
        return splitToSet(str, ",", Long::parseLong);
    }

    public static Set<Integer> splitToIntegerSet(String str) {
        return splitToSet(str, ",", Integer::parseInt);
    }

    public static List<Long> splitToDistinctList(String str) {
        return splitToDistinctList(str, ",", Long::parseLong);
    }

    public static <T> List<T> splitToList(String str, String delimiter, Function<String, T> transfer) {
        if (StringUtils.isEmpty(str)) {
            return Lists.newArrayList();
        }
        return Stream.of(str.split(delimiter))
                .filter(StringUtils::isNotBlank)
                .map(transfer)
                .collect(Collectors.toList());
    }

    public static <T> List<T> splitToDistinctList(String str, String delimiter, Function<String, T> transfer) {
        if (StringUtils.isEmpty(str)) {
            return Lists.newArrayList();
        }
        return Stream.of(str.split(delimiter))
                .filter(StringUtils::isNotBlank)
                .map(transfer)
                .distinct()
                .collect(Collectors.toList());
    }

    public static <T> Set<T> splitToSet(String str, String delimiter, Function<String, T> transfer) {
        if (StringUtils.isEmpty(str)) {
            return Sets.newHashSet();
        }
        return Stream.of(str.split(delimiter))
                .filter(StringUtils::isNotBlank)
                .map(transfer)
                .collect(Collectors.toSet());
    }

}