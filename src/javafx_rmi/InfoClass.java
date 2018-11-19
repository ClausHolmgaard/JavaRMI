/*
Implementation af custom class.
Har kun message of ID lige nu, hvor ID ikke bruges.
 */
package javafx_rmi;

import java.io.Serializable;

public class InfoClass implements Serializable {

    private String Message;
    private int ID;

    public void setMessage(String m) {
        Message = m;
    }

    public String getMessage() {
        return Message;
    }

    public void setID(int id) {
        ID = id;
    }

    public  int getID() {
        return ID;
    }
}
