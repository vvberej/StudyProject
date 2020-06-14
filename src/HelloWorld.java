
public class HelloWorld {
    public static void main(String[] args) {
        System.out.println("Hi, my little world");
        Person person = new Person(false, "Виктория Бережная");
        boolean resM = person.marry(new Person(true, "Pavel"));
        resM = person.marry(new Person(true, "Aleksandr"));
        boolean resD = person.divorce();
    }
}
