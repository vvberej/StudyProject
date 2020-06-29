import javax.annotation.Generated;
import java.util.*;

public class CollectionUtils {

    public static <T> void addAll(List<? extends T> source, List<? super T> destination) {
        destination.addAll(source);
    }

    public static List newArrayList() {
        return new ArrayList<>();
    }

    public static <T> int indexOf(List<? extends T> source, T t) {
        return source.indexOf(t);
    }

    public static <T> List<T> limit(List<? extends T> source, int size) {
        return new ArrayList<>(source.subList(0, size));
    }

    public static <T> void add(List<T> source, T t) {
        source.add(t);
    }

    public static <T> void removeAll(List<? super T> removeFrom, List<? extends T> c2) {
        removeFrom.removeAll(c2);
    }

    ////true если первый лист содержит все элементы второго
    public static <T> boolean containsAll(List<? extends T> c1, List<? extends T> c2) {
        return c1.containsAll(c2);
    }

    //true если первый лист содержит хотя-бы 1 второго
    public static <T> boolean containsAny(List<? extends T> c1, List<? extends T> c2) {
        for (T t : c2) {
            if(c1.contains(t))
                return true;
        }
        return false;
    }

    //Возвращает лист, содержащий элементы из входного листа в диапазоне от min до max.
    // Элементы сравнивать через Comparable.
    // Прмер range(Arrays.asList(8,1,3,5,6, 4), 3, 6) вернет {3,4,5,6}
    public static <T extends Comparable<? super T>> List<T> range(List<? extends T> list, T min, T max) {
        List<T> result = new ArrayList<>(list);
        Collections.sort(result);
        result.removeIf(i -> i.compareTo(min)<0 || i.compareTo(max) >0);
        return result;
    }

    //Возвращает лист, содержащий элементы из входного листа в диапазоне от min до max.
    // Элементы сравнивать через Comparable.
    // Прмер range(Arrays.asList(8,1,3,5,6, 4), 3, 6) вернет {3,4,5,6}
    public static <T> List<T> range(List<? extends T> list, T min, T max, Comparator<? super T> comparator) {
        List<T> result = new ArrayList<>(list);
        //Collections.sort(result);
        result.sort(comparator);
        result.removeIf(i -> (comparator.compare(i, min)<0 || comparator.compare(i, max) >0));
        return result;
    }

    public static void main(String[] args) {
        CollectionUtils utils = new CollectionUtils();
        List array1 = utils.newArrayList();
        ArrayList<Integer> array2 = new ArrayList<>();
        array2.add(10);
        array2.add(566);
        array2.add(1098);
        array2.add(3);

        ArrayList<Integer> array3 = new ArrayList<>();
        array3.add(8);
        array3.add(1);
        array3.add(3);
        array3.add(5);
        array3.add(6);
        array3.add(4);

        boolean res1  = containsAny(array3, array2);
        System.out.println(res1);

        List<Integer> res2  = range(array3, 3, 6);
        System.out.println(res2.toString());

        Comparator<Integer> comparator = (o1, o2) -> o1.compareTo(o2);
        List<Integer> res4  = range(array3, 3, 6, comparator);
        System.out.println(res4.toString());

        boolean res3  = containsAll(array3, array2);
        System.out.println(res3);

    }
}