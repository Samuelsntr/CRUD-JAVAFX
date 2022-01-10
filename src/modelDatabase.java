import javafx.beans.property.SimpleStringProperty;
 

public class modelDatabase {
    public final SimpleStringProperty nk;
    public final SimpleStringProperty nama;
    public final SimpleStringProperty telpon;
    public final SimpleStringProperty status;
   
    public modelDatabase(String fnk, String fnama, String ftelpon, String fstatus){
        this.nk = new SimpleStringProperty(fnk);
        this.nama = new SimpleStringProperty(fnama);
        this.telpon = new SimpleStringProperty(ftelpon);
        this.status = new SimpleStringProperty(fstatus);
    }
   
    public String getNk() {
        return nk.get();
    }
 
    public void setNk(String value) {
        nk.set(value);
    }
   
 
    public String getNama() {
        return nama.get();
    }
 
    public void setNama(String value) {
        nama.set(value);
    }
   
 
    public String getTelpon() {
        return telpon.get();
    }
 
    public void setTelpon(String value) {
        telpon.set(value);
    }
   
 
    public String getStatus() {
        return status.get();
    }
 
    public void setStatus(String value) {
        status.set(value);
    }
 
}
