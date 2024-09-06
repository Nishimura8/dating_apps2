package models;

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
@Table(name = "follow")
@NamedQueries({
    @NamedQuery(
            name = "getAllFollow",
            query = "SELECT f FROM Follow AS f ORDER BY f.id DESC"
            ),
    @NamedQuery(
            name = "getFollowCount",
            query = "SELECT COUNT(f) FROM Follow AS f"
            ),
    @NamedQuery(
            name = "getMyAllFollower",
            query = "SELECT f FROM Follow AS f WHERE f.follower = :follower ORDER BY f.id DESC"
            ),
    @NamedQuery(
            name = "getFollowerCount",
            query = "SELECT COUNT(f) FROM Follow AS f  WHERE f.follower = :follower AND f.follow = :follow  ORDER BY f.id DESC"
            ),
    @NamedQuery(
            name = "checkMyRoom",
            query = "SELECT f FROM Follow AS f WHERE f.follower = :follower OR f.follow = :follow ORDER BY f.id DESC"
            ),
    @NamedQuery(
            name = "checkFollow",
            query = "SELECT f FROM Follow AS f WHERE f.follower = :follower AND f.follow = :follow ORDER BY f.id DESC"
            )
})
public class Follow {
    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @ManyToOne
    @JoinColumn(name = "follow_id", nullable = false)
    private User follow;

    @ManyToOne
    @JoinColumn(name = "follower_id", nullable = false)
    private User follower;

    @ManyToOne
    @JoinColumn(name = "room_id", nullable = false)
    private Room room;


    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }


    public User getFollow() {
        return this.follow;
    }

    public void setFollow(User user) {
        this.follow = user;
    }

    public User getFollower() {
        return this.follower;
    }

    public void setFollower(User user) {
        this.follower = user;
    }

    public Room getRoom() {
        return this.room;
    }

    public void setRoom(Room room) {
        this.room = room;
    }

}