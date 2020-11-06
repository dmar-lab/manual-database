package dmar.oldbikelist.manual.model;

public class Bike {

    private long id;
    private String bikeNo;
    private String securityCode;

    public long getId() {
        return id;
    }
//?
    public void setId(long id) {
        this.id = id;
    }

    public String getBikeNo() {
        return bikeNo;
    }

    public void setBikeNo(String bikeNo) {
        this.bikeNo = bikeNo;
    }

    public String getSecurityCode() {
        return securityCode;
    }

    public void setSecurityCode(String securityCode) {
        this.securityCode = securityCode;
    }

    @Override
    public String toString() {
        return bikeNo + ' ' + securityCode;
    }
}
