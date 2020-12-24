package inputData;

import java.io.Serializable;
import java.util.Date;

public abstract class Note implements Serializable {
    String header;
    Date dateCreate;

    public String getHeader() {
        return header;
    }

    public void setHeader(String header) {
        this.header = header;
    }

    public Date getDateCreate() {
        return dateCreate;
    }

    public void setDateCreate(Date dateCreate) {
        this.dateCreate = dateCreate;
    }
}
