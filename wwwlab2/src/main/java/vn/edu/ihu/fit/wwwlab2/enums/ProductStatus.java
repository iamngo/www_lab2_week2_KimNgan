package vn.edu.ihu.fit.wwwlab2.enums;

public enum ProductStatus {
    ACTIVE(1),
    NO_ACTIVE(0),
    TERMINALED(-1);
    private int status;

    ProductStatus(int status) {
        this.status = status;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }
}
