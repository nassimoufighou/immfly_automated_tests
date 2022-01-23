package core.reporting;

import com.aventstack.extentreports.Status;

public class Step {

    private String message;
    private Status status;

    public Step(Status status, String message) {
        this.status = status;
        this.message = message;
    }

    public String getMessage() {
        return message;
    }

    public Status getStatus() {
        return status;
    }
}