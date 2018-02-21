package simplebudget;

/**
 * Created by Austin on 2/20/2018.
 *
 * Current placeholder for the User class that will create User Objects for easy data transfer to and from db
 *
 */
public class User {

    private String firstName;
    private String lastName;
    private int age;

    public User(String firstName, String lastName, int age){
        this.setFirstName(firstName);
        this.setLastName(lastName);
        this.setAge(age);
    }


    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }
}
