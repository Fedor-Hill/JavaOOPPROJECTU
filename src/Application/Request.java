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

    public void setStatus(REQUEST_STATUS status) {
        this.status = status;
    }

    public REQUEST_STATUS getStatus() {
        return this.status;
    }

    public String getAdamId() {
        return adamId;
    }

    public void setAdamId(String adamId) {
        this.adamId = adamId;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getRequestId() {
        return requestId;
    }

    public void setRequestId(String requestId) {
        this.requestId = requestId;
    }
}