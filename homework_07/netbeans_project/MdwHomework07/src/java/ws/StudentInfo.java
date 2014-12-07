package ws;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
public class StudentInfo {

    @XmlElement(required = true)
    String username;
    @XmlElement(required = true)
    String status;
    @XmlElement(required = true)
    int timesheetId;

    public StudentInfo() {
    }

}
