import java.util.*;

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
                    Education.values()[new Random().nextInt(Education.values().length)]
            ));
        }
        System.out.println(
                persons.stream()
                        .filter(x -> x.getAge() < 18)
                        .count()
        );
        List<Person> list1 = persons.stream()
                .filter(x -> x.getSex() == Sex.MAN)
                .filter(x -> x.getAge() > 17 && x.getAge() < 28)
                .toList();
        for (Person person : list1) {
            System.out.println(person.toString());
        }
        List<Person> list2 = persons.stream()
                .filter(x -> x.getEducation() == Education.HIGHER)
                .filter(x -> x.getAge() > 17)
                .filter(x -> x.getAge() < 61 + (x.getSex() == Sex.MAN ? 5 : 0))
                .sorted(Comparator.comparing(Person::getFamily).thenComparing(Person::getName))
                .toList();
        for (Person person : list2) {
            System.out.println(person.toString());
        }
    }
}