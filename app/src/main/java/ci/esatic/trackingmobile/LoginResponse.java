package ci.esatic.trackingmobile;

import ci.esatic.trackingmobile.model.Chauffeur;

public class LoginResponse {

    private Integer status;
    private String message;
    private String errors;
    private Chauffeur data;

    public LoginResponse() {
    }

    @Override
    public String toString() {
        return "LoginResponse{" +
                "status=" + status +
                ", message='" + message + '\'' +
                ", errors='" + errors + '\'' +
                ", data=" + data +
                '}';
    }

    public LoginResponse(Integer status, String message, String errors, Chauffeur data) {
        this.status = status;
        this.message = message;
        this.errors = errors;
        this.data = data;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getErrors() {
        return errors;
    }

    public void setErrors(String errors) {
        this.errors = errors;
    }

    public Chauffeur getData() {
        return data;
    }

    public void setData(Chauffeur data) {
        this.data = data;
    }
}