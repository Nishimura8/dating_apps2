package models;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Lob;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;

@Table(name = "user")
@NamedQueries({
    @NamedQuery(
            name = "getAllUsers",
            query = "SELECT u FROM User AS u ORDER BY u.id DESC"
            ),
    @NamedQuery(
            name = "getUsersCount",
            query = "SELECT COUNT(u) FROM User AS u"
            ),
    @NamedQuery(
            name = "checkRegisteredName",
            query = "SELECT COUNT(u) FROM User AS u WHERE u.name = :name"
            ),
    @NamedQuery(
            name = "checkLoginCodeAndPassword",
            query = "SELECT u FROM User AS u WHERE  u.name = :name AND u.password = :pass"
            )
})
@Entity
public class User {
    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "name", length = 255, nullable = false)
    private String name;

    @Column(name = "prefecture", nullable = false)
    private String prefecture;

    @Column(name = "content", nullable = false)
    private String content;

    @Column(name = "gender", nullable = false)
    private Integer gender;

    @Lob
    @Column(name = "image", columnDefinition="MEDIUMBLOB")
    private String image;

    @Column(name = "age", nullable = false)
    private Integer age;

    @Column(name = "password", length = 64, nullable = false)
    private String password;


    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getPrefecture() {
        return prefecture;
    }

    public void setPrefecture(String prefectures) {
        this.prefecture = prefectures;
    }

    public Integer getAge() {
        return this.age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }

    public Integer getGender() {
        return this.gender;
    }

    public void setGender(Integer gender) {
        this.gender = gender;
    }

    public String getPassword() {
        return this.password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getImage() {
        return this.image;
    }
    public void setImage(String image) {
        this.image = image;
    }
}