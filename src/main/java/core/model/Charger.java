package core.model;

public class Charger {

    private String _id;
    private String serialNumber;
    private String model;
    private String stationId;
    private boolean enabled;
    private int statusCode;

    public Charger() {
    }

    public Charger(String _id, String serialNumber, String model, String stationId, boolean enabled, int statusCode) {
        this._id = _id;
        this.serialNumber = serialNumber;
        this.model = model;
        this.stationId = stationId;
        this.enabled = enabled;
        this.statusCode = statusCode;
    }

    public Charger(String serialNumber, String model, String stationId, boolean enabled, int statusCode) {
        this.serialNumber = serialNumber;
        this.model = model;
        this.stationId = stationId;
        this.enabled = enabled;
        this.statusCode = statusCode;
    }

    public String get_id() {
        return _id;
    }

    public void set_id(String _id) {
        this._id = _id;
    }

    public String getSerialNumber() {
        return serialNumber;
    }

    public void setSerialNumber(String serialNumber) {
        this.serialNumber = serialNumber;
    }

    public String getModel() {
        return model;
    }

    public void setModel(String model) {
        this.model = model;
    }

    public String getStationId() {
        return stationId;
    }

    public void setStationId(String stationId) {
        this.stationId = stationId;
    }

    public boolean isEnabled() {
        return enabled;
    }

    public void setEnabled(boolean enabled) {
        this.enabled = enabled;
    }

    public int getStatusCode() {
        return statusCode;
    }

    public void setStatusCode(int statusCode) {
        this.statusCode = statusCode;
    }
}
