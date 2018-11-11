package shared;

import java.io.Serializable;

public class Request implements Serializable{

    private long id;
    private String message;

    public Request(long id, String message) {
        this.id = id;
        this.message = message;
    }

    public long getId() {
        return id;
    }

    public String getMessage() {
        return message;
    }

}