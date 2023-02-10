package dmar.oldbikelist.manual.model;

public class Bike {

    private long id;
    private String bikeNo;
    private String securityCode;
    private String other1;
    private String other2;

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

    public String getOther1() {
        return other1;
    }

    public void setOther1(String other1) {
        this.other1 = other1;
    }

    public String getOther2() {
        return other2;
    }

    public void setOther2(String other2) {
        this.other2 = other2;
    }

    @Override
    public String toString() {
        return bikeNo + ' ' + securityCode;
    }
}
