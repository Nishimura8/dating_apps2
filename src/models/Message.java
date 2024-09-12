package models;

import java.sql.Timestamp;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;

@Entity
@Table(name = "messages")
@NamedQueries({
    @NamedQuery(
            name = "getAllMessages",
            query = "SELECT m FROM Message AS m ORDER BY m.id DESC"
            ),
    @NamedQuery(
            name = "getMessagesCount",
            query = "SELECT COUNT(m) FROM Message AS m"
            ),
    @NamedQuery(
            name = "getMessagesAllRooms",
            query = "SELECT m FROM Message AS m WHERE m.room = :room ORDER BY m.id"
            ),
    @NamedQuery(
            name = "getPostedDay",
            query = "SELECT distinct m.posted_day FROM Message AS m "
                    + "WHERE m.room = :room ORDER BY m.posted_day"
            )
})
public class Message {
    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "content", nullable = false)
    private String content;

    @ManyToOne
    @JoinColumn(name = "user_id", nullable = false)
    private User user;

    @ManyToOne
    @JoinColumn(name = "room_id", nullable = false)
    private Room room;

    @Column(name = "created_at", nullable = false)
    private Timestamp created_at;

    @Column(name = "posted_day", nullable = false)
    private String posted_day;

    @Column(name = "posted_time", nullable = false)
    private String posted_time;

    @Column(name = "posted_week", nullable = false)
    private String posted_week;


    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public Timestamp getCreated_at() {
        return created_at;
    }

    public void setCreated_at(Timestamp created_at) {
        this.created_at = created_at;
    }

    public User getUser() {
        return this.user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public void setRoom(Room room) {
        this.room = room;
    }

    public void setPostedDay(String posted_day) {
        this.posted_day= posted_day;
    }

    public String getPosted_day() {
        return this.posted_day;
    }

    public void setPostedTime(String posted_time) {
        this.posted_time= posted_time;
    }

    public String getPosted_time() {
        return this.posted_time;
    }

    public void setPostedWeek(String posted_week) {
        this.posted_week= posted_week;
    }

    public String getPosted_week() {
        return this.posted_week;
    }

}