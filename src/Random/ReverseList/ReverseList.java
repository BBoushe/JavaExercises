package Random.ReverseList;

import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class ReverseList {
//    public static <T> void reverseList(Collection<T> collection){
//        List<T> list = new ArrayList<>(collection);
//        Collections.reverse(list);
//        System.out.println(list);
//    }

    public static <T> void reverseList(Collection <T> collection){
        List<T> list = new ArrayList<>(collection);
        List<T> reversedList = IntStream.range(0, list.size())
                .mapToObj(i -> list.get(list.size() - 1 - i))
                .collect(Collectors.toList());

        System.out.println(reversedList);
    }

    public static void main(String[] args) {
        List<Integer> ints = new ArrayList<>(List.of(1,2,3,4,5,6,7,8));
        reverseList(ints);
    }
}
