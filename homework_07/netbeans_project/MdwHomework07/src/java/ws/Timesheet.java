package ws;

import javax.jws.*;

@WebService(name = "Timesheet")
public class Timesheet {

    @WebMethod(operationName = "getTimesheetId")
    public int getTimesheetId(@WebParam(name = "username") String username) {
        return (int) (Math.random() * 100);
    }
}
