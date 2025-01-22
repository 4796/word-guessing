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
public class Response implements Serializable{
    Object object;
    Exception exception;

    public Response(Object object, Exception exception) {
        this.object = object;
        this.exception = exception;
    }

    public Object getObject() {
        return object;
    }

    public void setObject(Object object) {
        this.object = object;
    }

    public Exception getException() {
        return exception;
    }

    public void setException(Exception exception) {
        this.exception = exception;
    }

    @Override
    public String toString() {
        return "Response{" + "object=" + object + ", exception=" + exception + '}';
    }
    
}
