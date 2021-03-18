package com.yxj.calc;

import java.util.*;

public class LeecodeDemo13 {

    /**
     * I             1
     * V             5
     * X             10
     * L             50
     * C             100
     * D             500
     * M             1000
     * @param num
     * @return
     */
    public static String intToRoman(int num) {

        Map<Integer, String> map = new TreeMap<>(new Comparator<Integer>() {
            @Override
            public int compare(Integer o1, Integer o2) {
                return o2 - o1;
            }
        });
        map.put(1, "I");
        map.put(5, "V");
        map.put(10, "X");
        map.put(50, "L");
        map.put(100, "C");
        map.put(500, "D");
        map.put(1000, "M");

        TreeMap<Integer, String> map2 = new TreeMap<>(new Comparator<Integer>() {
            @Override
            public int compare(Integer o1, Integer o2) {
                return o2 - o1;
            }
        });
        map2.put(4, "IV");
        map2.put(9, "IX");
        map2.put(40, "XL");
        map2.put(90, "XC");
        map2.put(400, "CD");
        map2.put(900, "CM");

        if (map2.containsKey(num)) {
            return map2.get(num);
        }

        StringBuilder sb = new StringBuilder();
        Set<Map.Entry<Integer, String>> entries = map.entrySet();
        int j = 0;
        for (Map.Entry<Integer, String> entry : entries) {
            Integer key = entry.getKey();
            int temp = num / key;
            if (j > 0 || isRange(num)) {
                Map<Integer, String> integerStringSortedMap =  map2.tailMap(num);
                if (integerStringSortedMap.size() > 0) {
                    Integer firstKey = getFirstKey(integerStringSortedMap);
                    if (num >= firstKey && isRange(num) && temp == 0) {
                            num -= firstKey;
                            sb.append(map2.get(firstKey));
                   }
                }
            }
            if (temp > 0) {
                for (int i = 0 ; i < temp; i++) {
                    sb.append(entry.getValue());
                }
                num %= key;
                Map<Integer, String> integerStringSortedMap = map2.tailMap(key);
                if (integerStringSortedMap.size() > 0) {
                    Integer firstKey = getFirstKey(integerStringSortedMap);
                    if (num >= firstKey) {
                        num -= firstKey;
                        sb.append(map2.get(firstKey));
                    }
                }
                j++;
            }
        }
        return sb.toString();
    }

    private static boolean isRange(int num) {
        if (
                num == 4 ||
                num == 9 ||
                (num >= 40 && num < 50)
                || (num >= 90 && num < 100)
               || (num >= 400 && num < 500)
                || (num >= 900 && num < 1000)) {
            return true;
        }
        return false;
    }

    private static Integer getFirstKey(Map<Integer, String> map) {
        Set<Map.Entry<Integer, String>> entries = map.entrySet();
        for (Map.Entry<Integer, String> entry : entries) {
                return entry.getKey();
        }
        return -1;
    }

    public static void main(String[] args) {
        System.out.println(intToRoman(1994));
    }
}
