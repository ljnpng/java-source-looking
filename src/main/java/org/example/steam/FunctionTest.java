package org.example.steam;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.function.Function;

/**
 * TODO
 */
public class FunctionTest {


    public static void main(String[] args) {
        Function<List<Integer>, Integer> function = value -> {
            int sum = 0;
            for (Integer i : value) {
                sum += i;
            }
            return sum;
        };

        Function<Map<String,Integer>, ArrayList<Integer>> f2 = map -> {
//        Collection<Integer> values = map.values();
            ArrayList<Integer> ret = new ArrayList<>();
//        ret.addAll(values);
            for (Map.Entry<String, Integer> entry : map.entrySet()) {
                ret.add(entry.getValue());
            }
            return ret;
        };

        Map<String,Integer> map = new HashMap<String, Integer>();
        map.put("岑小村", 59);
        map.put("谷天洛", 82);
        map.put("渣渣辉", 98);
        map.put("蓝小月", 65);
        map.put("皮几万", 70);
        Integer apply = f2.andThen(function).apply(map);
        System.out.println(apply);
    }
}
