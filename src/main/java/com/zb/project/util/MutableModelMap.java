package com.zb.project.util;

import java.util.HashMap;

/**
 * Compare to ImmutableMap, this map type can contains nullable value.
 *
 * @author Hover Ruan
 */
public class MutableModelMap<K, V> extends HashMap<K, V> {
    public MutableModelMap(K k1, V v1) {
        put(k1, v1);
    }

    public MutableModelMap(K k1, V v1, K k2, V v2) {
        put(k1, v1);
        put(k2, v2);
    }

    public MutableModelMap(K k1, V v1, K k2, V v2, K k3, V v3) {
        put(k1, v1);
        put(k2, v2);
        put(k3, v3);
    }

    public MutableModelMap(K k1, V v1, K k2, V v2, K k3, V v3, K k4, V v4) {
        put(k1, v1);
        put(k2, v2);
        put(k3, v3);
        put(k4, v4);
    }

    public MutableModelMap(K k1, V v1, K k2, V v2, K k3, V v3, K k4, V v4, K k5, V v5) {
        put(k1, v1);
        put(k2, v2);
        put(k3, v3);
        put(k4, v4);
        put(k5, v5);
    }

    public static <K, V> MutableModelMap<K, V> of(K k1, V v1) {
        return new MutableModelMap<K, V>(k1, v1);
    }

    public static <K, V> MutableModelMap<K, V> of(K k1, V v1, K k2, V v2) {
        return new MutableModelMap<K, V>(k1, v1, k2, v2);
    }

    public static <K, V> MutableModelMap<K, V> of(K k1, V v1, K k2, V v2, K k3, V v3) {
        return new MutableModelMap<K, V>(k1, v1, k2, v2, k3, v3);
    }

    public static <K, V> MutableModelMap<K, V> of(K k1, V v1, K k2, V v2, K k3, V v3, K k4, V v4) {
        return new MutableModelMap<K, V>(k1, v1, k2, v2, k3, v3, k4, v4);
    }

    public static <K, V> MutableModelMap<K, V> of(K k1, V v1, K k2, V v2, K k3, V v3, K k4, V v4, K k5, V v5) {
        return new MutableModelMap<K, V>(k1, v1, k2, v2, k3, v3, k4, v4, k5, v5);
    }
}
