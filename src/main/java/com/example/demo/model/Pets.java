package com.example.demo.model;


import org.hibernate.annotations.DynamicUpdate;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.ResultSetExtractor;

import javax.persistence.*;
import java.sql.*;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;


@Entity()
@Table(name = "pet")
@DynamicUpdate()
public class Pets{

    @Id @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id")
    private int id;

    @Column(name = "yas")
    private int yas;

    @Column(name = "user")
    private String user;

    @Column(name = "tur")
    private String tur;

    @Column(name = "isim")
    private String isim;


    @Column(name = "cins")
    private String cins;


    @Column(name = "aciklama")
    private String aciklama;

    public String getUser() {
        return user;
    }

    public void setUser(String user) {
        this.user = user;
    }

    public String getIsim() {
        return isim;
    }

    public void setIsim(String isim) {
        this.isim = isim;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getYas() {
        return yas;
    }

    public void setYas(int yas) {
        this.yas = yas;
    }

    public String getTur() {
        return tur;
    }

    public void setTur(String tur) {
        this.tur = tur;
    }

    public String getCins() {
        return cins;
    }

    public void setCins(String cins) {
        this.cins = cins;
    }

    public String getAciklama() {
        return aciklama;
    }

    public void setAciklama(String aciklama) {
        this.aciklama = aciklama;
    }

    public Pets() {

    }

    public List<Pets> getAllPets(){
        try
        {
            // create our mysql database connection
            String myDriver = "org.hibernate.dialect.MySQL5Dialect";
            String myUrl = "jdbc:mysql://localhost:3306/onlinestoredb?verifyServerCertificate=false&useSSL=true&useUnicode=true&useJDBCCompliantTimezoneShift=true&useLegacyDatetimeCode=false&serverTimezone=UTC";
            Class.forName(myDriver);
            Connection conn = DriverManager.getConnection(myUrl, "root", "asd123");

            // our SQL SELECT query.
            // if you only need a few columns, specify them by name instead of using "*"
            String query = "SELECT * FROM pet";
            List<Pets> pets = null;
            // create the java statement
            Statement st = conn.createStatement();

            // execute the query, and get a java resultset
            ResultSet rs = st.executeQuery(query);

            // iterate through the java resultset
            while (rs.next())
            {
                Pets temp = new Pets();
                temp.setId(rs.getInt("id"));
                temp.setIsim(rs.getString("isim"));
                temp.setTur(rs.getString("tur"));
                temp.setCins(rs.getString("cins"));
                temp.setAciklama(rs.getString("aciklama"));
                temp.setYas(rs.getInt("yas"));

                pets.add(temp);
            }
            st.close();
            return pets;
        }
        catch (Exception e)
        {
            System.err.println("Got an exception! ");
            System.err.println(e.getMessage());
            return null;
        }
    }
}
