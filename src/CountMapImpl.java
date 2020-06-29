import java.util.*;


public class CountMapImpl<K> implements CountMap<K> {

    private Map<K,Integer> innerMap;

    public CountMapImpl() {
        this.innerMap = new HashMap<>();
    }

    @Override
    public void add(K key) {
        if(innerMap.containsKey(key))
            innerMap.put(key,innerMap.get(key)+1);
        else innerMap.put(key,1);
    }

    @Override
    public int getCount(K key) {
        if (!innerMap.containsKey(key))
            return 0;
        return innerMap.get(key);
    }

    @Override
    public int remove(K key) {
        if (!innerMap.containsKey(key))
            return 0;
        int count = innerMap.get(key);
        innerMap.remove(key);
        return count;
    }

    @Override
    public int size() {
        return innerMap.size(); // не вверно??? НАдо просуммировать все vall
    }

    @Override
    public void addAll(CountMap< K> source) {
        CountMapImpl<K> sourceM = (CountMapImpl<K>) source;
        Map<K,Integer> innerSourceMap = innerMap;//sourceM.getInnerMap();
        for (Map.Entry<K,Integer> sm :innerSourceMap.entrySet() ){
            if (innerMap.containsKey(sm.getKey()))
                innerMap.put(sm.getKey(),sm.getValue()+innerMap.get(sm.getKey()));
        }

    }

    @Override
    public Map<K,Integer> toMap() {
        Map<K, Integer> returnMap = new HashMap<>();
        for (Map.Entry<K, Integer> entry : innerMap.entrySet())
            returnMap.put(entry.getKey(),entry.getValue());
        return returnMap;
    }

    @Override
    public void toMap(Map<K,Integer> destination) {
        for (Map.Entry<K,Integer> entry:innerMap.entrySet())
            destination.put(entry.getKey(),entry.getValue());
    }

    public static void main(String[] args) {
        CountMap<Integer> cm1 = new CountMapImpl<>();
        CountMap<String> cm2 = new CountMapImpl<>();
        cm1.add(1);
        cm1.add(111);
        cm1.add(526);

        cm2.add("2");
        cm2.add("222");
        cm2.add("222");
        cm2.add("222");
        cm2.add("877");
        cm2.remove("877");

        Map<Integer,Integer> dest  = new HashMap<>();
        cm1.toMap(dest);
        System.out.println(dest.toString());

        Map<String,Integer> dest2  = new HashMap<>();
        dest2 = cm2.toMap();
        int count1 = cm2.getCount("222");
        int count2 = cm2.getCount("877");
        System.out.println(count1);
        System.out.println(count2);
        System.out.println(dest2.toString());
    }
}
