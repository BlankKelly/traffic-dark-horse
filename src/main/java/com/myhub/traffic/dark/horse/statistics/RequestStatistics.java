package com.myhub.traffic.dark.horse.statistics;

import com.myhub.traffic.dark.horse.core.ResourceSlidingWindowCache;
import com.myhub.traffic.dark.horse.core.SlidingWindow;
import org.checkerframework.checker.nullness.qual.NonNull;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.ConcurrentMap;

public class RequestStatistics {
    public static Map<String, Integer> statistics() {
        Map<String, Integer> countStatistics = new HashMap<>();
        ConcurrentMap<@NonNull String, @NonNull Object> snapshot = ResourceSlidingWindowCache.getCACHE().asMap();

        Set<Map.Entry<@NonNull String, @NonNull Object>> entries = snapshot.entrySet();
        Iterator<Map.Entry<@NonNull String, @NonNull Object>> iterator = entries.iterator();
        while (iterator.hasNext()) {
            Map.Entry<@NonNull String, @NonNull Object> next = iterator.next();
            String key = next.getKey();
            SlidingWindow slidingWindow = (SlidingWindow)next.getValue();

            countStatistics.put(key, slidingWindow.count());
        }

        return countStatistics;
    }
}