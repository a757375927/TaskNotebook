package tnb.george.me.tasknotebook.bean;

/**
 * Created by GeorgeZou on 2014/11/27.\
 *
 * @Description:<br/>
 * @Author:GeorgeZou(Zousongqi0213@gmail.com)<br/>
 * @Since:2014/11/27<br/>
 */
public class Contact {

    public Contact(){}

    public Contact(int id,String name,String phone,String description){
        this.setId(id);
        this.setName(name);
        this.setPhone(phone);
        this.setDescriotion(description);
    }

    private int id;
    private String name;
    private String phone;
    private String descriotion;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getDescriotion() {
        return descriotion;
    }

    public void setDescriotion(String descriotion) {
        this.descriotion = descriotion;
    }
}
