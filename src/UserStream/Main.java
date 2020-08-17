package UserStream;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.IntStream;

import static java.util.stream.Collectors.toMap;

public class Main {

    public static void main(String[] args) {

        List<Person> persons = new ArrayList<>();
        persons.add(new Person("Happy", 1));
        persons.add(new Person("Sad", 3));
        persons.add(new Person("Fast", 5));
        persons.add(new Person("Smart", 4));
        persons.add(new Person("Kind", 6));
        persons.add(new Person("Hangry", 2));

        Map<String, Person> map1 = persons.stream()
                .filter(p -> p.getAge() > 0)
                .map(p -> new Person(p.getName() + "_new", p.getAge() + 1))
                .collect(toMap(p -> p.getName(), p -> p));

        //.filter(p -> p.getName().startsWith("S"))
        Map<String, Person> map2 = Streams.of(persons)
                .filter(p -> p.getAge() > 0)
                .transform(p -> new Person(p.getName() + "_new", p.getAge() + 1))
                .toMap(p -> p.getName(), p -> p);
    }
}