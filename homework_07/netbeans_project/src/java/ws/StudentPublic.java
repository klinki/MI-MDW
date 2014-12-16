package ws;

import javax.jws.*;

@WebService(name = "Student")
public class StudentPublic {

    @WebMethod(operationName = "getStudentInfo")
    public StudentInfo getStudentInfo(@WebParam(name = "username") String username) {
        return null;
    }

}
