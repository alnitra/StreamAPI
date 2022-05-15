package PopulationCensus;

import java.util.*;
import java.util.stream.Collectors;

public class Main {
    public static void main(String[] args) {
        List<String> names = Arrays.asList("Jack", "Connor", "Harry", "George", "Samuel", "John");
        List<String> families = Arrays.asList("Evans", "Young", "Harris", "Wilson", "Davies", "Adamson", "Brown");
        Collection<Person> persons = new ArrayList<>();
        for (int i = 0; i < 10_000_000; i++) {
            persons.add(new Person(
                    names.get(new Random().nextInt(names.size())),
                    families.get(new Random().nextInt(families.size())),
                    new Random().nextInt(100),
                    Sex.values()[new Random().nextInt(Sex.values().length)],
                    Education.values()[new Random().nextInt(Education.values().length)])
            );
        }

        long count = persons.stream()
                .filter(value -> value.getAge() < 18)
                .count();
        System.out.println("Number of juveniles is: " + count);

        List<String> youAreInArmy = persons.stream()
                .filter(value -> value.getAge() >= 18)
                .filter(value -> value.getAge() <= 27)
                .filter(value -> value.getSex() == Sex.MAN)
                .map(value -> value.getFamily())
                .collect(Collectors.toList());
        System.out.println("List of future soldiers:" + youAreInArmy);


        List<String> youAreAbleToWork = persons.stream()
                .filter(value -> (((value.getSex()==Sex.MAN) && (value.getAge() <= 65)
                                    && (value.getAge() >= 18) && (value.getEducation() == Education.HIGHER))
                                    || ((value.getSex()==Sex.WOMAN) && (value.getAge() <= 60)
                                && (value.getAge() >= 18) && (value.getEducation() == Education.HIGHER)))
                        )
                .sorted(Comparator.comparing(value -> value.getFamily()))
                .map(value -> value.getFamily() + " "+ value.getName())
                .collect(Collectors.toList());
        System.out.println("List of workoholics:" + youAreAbleToWork);

    }
}
