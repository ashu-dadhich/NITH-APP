package CustomItems;

import java.util.ArrayList;

/**
 * Created by Ashu on 13-08-2016.
 */
public class Model {
    private String date;
    private String info;

    public Model() {
    }

    public Model(String date, String info) {
        this.date = date;
        this.info = info;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getInfo() {
        return info;
    }

    public void setInfo(String info) {
        this.info = info;
    }
}
