package ws;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name = "student")
public class Student {

    @XmlElement(name = "username", required = true)
    String username;
    @XmlElement(name = "status", required = true)
    String status;

    public Student() {
    }

    ;
 
    public Student(String username, String status) {
        this.username = username;
        this.status = status;
    }

}
