package com.example.plantapp.utils;

import java.util.*;
import java.util.function.Function;
import java.util.stream.Collectors;

import static java.util.stream.Collectors.toMap;


public class CollectionUtils {

    public static <K,V> List<K> extractFields(Collection<V> collection, Function<V,K> fieldFunction){
        return collection.stream()
                .map(fieldFunction)
                .filter(Objects::nonNull)
                .distinct()
                .collect(Collectors.toList());
    }

    public static <K,V> Set<K> extractFieldToSet(Collection<V> collection, Function<V,K> fieldFunction){
        return collection.stream()
                .map(fieldFunction)
                .filter(Objects::nonNull)
                .collect(Collectors.toSet());
    }
    public static <K,V> Map<K,V> collectToMap(Collection<V> collection, Function<V,K> fieldFunction ){
        return collection.stream().collect(toMap(fieldFunction,Function.identity(),(v1,v2) -> v1));
    }
}
