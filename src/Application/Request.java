package Application;

import java.io.Serializable;
import java.util.UUID;

import Enums.REQUEST_STATUS;

public class Request implements Serializable {
    private String requestId;
    private String adamId;
    private String description;
    private REQUEST_STATUS status;

    public Request(String adamId, String description) {
        this.requestId = UUID.randomUUID().toString();
        this.adamId = adamId;
        this.description = description;
        this.status = REQUEST_STATUS.PENDING;
    }
}