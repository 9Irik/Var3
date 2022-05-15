package entity;

import javax.persistence.*;
import java.util.Date;

import static javax.persistence.GenerationType.IDENTITY;

@Entity
@Table(name = "t_lessons")
public class Lesson implements java.io.Serializable {
    public Integer lessonNum;
    public String name;
    public String info;
    private Group groups;
    private Date statusDate;

    private Lesson() {

    }

    @Id
    @GeneratedValue(strategy = IDENTITY)
    @Column(name = "LessonNum", unique = true, nullable = false)
    public Integer getLessonNum() {
        return lessonNum;
    }

    public void setLessonNum(Integer lessonNum) {
        this.lessonNum = lessonNum;
    }

    @Column(name = "Name", unique = true, nullable = false, length = 100)
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Column(name = "Info", unique = true, nullable = false, length = 500)
    public String getInfo() {
        return info;
    }

    public void setInfo(String info) {
        this.info = info;
    }

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "GroupCipher", nullable = false)
    public Group getGroups() {
        return groups;
    }

    public void setGroups(Group groups) {
        this.groups = groups;
    }

    @Temporal(TemporalType.DATE)
    @Column(name = "StatusDate")
    public Date getStatusDate() {
        return statusDate;
    }

    public void setStatusDate(Date statusDate) {
        this.statusDate = statusDate;
    }
}
