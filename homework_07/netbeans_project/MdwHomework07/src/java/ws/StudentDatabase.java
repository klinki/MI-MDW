package ws;

import java.util.ArrayList;
import java.util.List;

import javax.jws.*;

@WebService(name = "StudentDatabase")
public class StudentDatabase {

    private static List<Student> db = new ArrayList<Student>();

    public StudentDatabase() {
        db.add(new Student("username1", "student"));
        db.add(new Student("username2", "graduate"));
    }

    @WebMethod(operationName = "getInfo")
    public Student getInfo(@WebParam(name = "username") String username) {
        if (username != null && username.length() > 0) {
            for (Student s : db) {
                if (s.username.equals(username)) {
                    return s;
                }
            }
        }
        return null;
    }

}
