package ru.alifba.eksmo.util;

import lombok.experimental.UtilityClass;
import ru.alifba.eksmo.model.TreeGuidIdentifiable;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@UtilityClass
public class CollectionUtils {

    @SafeVarargs
    public static <K, V> Map<K, V> combineMaps(Map<K, V>... maps) {
        return Stream.of(maps)
            .flatMap(map -> map.entrySet().stream())
            .collect(Collectors.toMap(Map.Entry::getKey, Map.Entry::getValue));
    }

    public static <T extends TreeGuidIdentifiable> Map<String, List<T>> buildParentChildrenRelations(
        Map<String, T> map) {
        Map<String, List<T>> parentChildren = new HashMap<>();
        map.values().forEach(item -> {
            String parentGuid = item.getParentGuid();
            if (parentGuid == null || parentGuid.length() == 0) {
                parentChildren.put(item.getGuid(), new ArrayList<>());
                return;
            }
            List<T> children = parentChildren.getOrDefault(parentGuid, new ArrayList<>());
            children.add(item);
            parentChildren.put(parentGuid, children);
        });
        return parentChildren;
    }

}
