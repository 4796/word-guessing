/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package sve;

import java.io.Serializable;

/**
 *
 * @author lazar
 */
public class Request implements Serializable{
    Operacija operacija;
    Object object;

    public Operacija getOperacija() {
        return operacija;
    }

    public void setOperacija(Operacija operacija) {
        this.operacija = operacija;
    }

    public Object getObject() {
        return object;
    }

    public void setObject(Object object) {
        this.object = object;
    }

    public Request(Operacija operacija, Object object) {
        this.operacija = operacija;
        this.object = object;
    }
    
}
