package cn.org.ecrp.ui.helper;

import cn.org.ecrp.ui.base.KeyValue;
import cn.org.ecrp.ui.util.EnumUtil;
import javafx.scene.control.ChoiceBox;
import javafx.util.StringConverter;
import org.apache.commons.collections4.BidiMap;
import org.apache.commons.collections4.bidimap.DualHashBidiMap;

import java.util.Arrays;
import java.util.List;
import java.util.function.Function;
import java.util.stream.Collectors;

/**
 * description <br>
 *
 * @author YourName
 * @date 2021-12-16 15:55
 */
public class ChoiceBoxHelper {

    public static <T extends Enum<T>> void setContentDisplay(
            ChoiceBox<T> choiceBox, Class<T> enumType, Function<T, String> toString
    ) {
        List<KeyValue<String, T>> keyValues = EnumUtil.toKeyValueList(enumType, toString);
        setContentDisplay(choiceBox, keyValues);
    }

    public static <T> void setContentDisplay(ChoiceBox<T> choiceBox, KeyValue<String, T>... keyValues) {
        setContentDisplay(choiceBox, Arrays.asList(keyValues));
    }

    public static <T> void setContentDisplay(ChoiceBox<T> choiceBox, List<KeyValue<String, T>> keyValues) {
        List<T> values = keyValues.stream().map(KeyValue::getValue).collect(Collectors.toList());
        BidiMap<String, T> map = new DualHashBidiMap<>();
        keyValues.forEach(keyValue -> map.put(keyValue.getKey(), keyValue.getValue()));
        setContentDisplay(choiceBox, values, map);
    }

    public static <T> void setContentDisplay(ChoiceBox<T> choiceBox, List<T> items, BidiMap<String, T> itemMappings) {
        choiceBox.setConverter(new StringConverter<T>() {
            @Override
            public String toString(T object) {
                return itemMappings.getKey(object);
            }

            @Override
            public T fromString(String string) {
                return itemMappings.get(string);
            }
        });
        choiceBox.getItems().addAll(items);
    }
}
