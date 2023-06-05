import java.util.ArrayList;
import java.util.stream.Collectors;

public class PersonMultiton {

    ArrayList<Person> people = new ArrayList<>();

    private PersonMultiton() {}

    public Person getPerson(String name) {
        return people.stream().filter(p ->  p.Name.equals(name)).findFirst().get();
    }

    public void createPerson(String name, String vorname) {
        people.add(new Person(name, vorname));
    }


}
