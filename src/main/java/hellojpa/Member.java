package hellojpa;

import javax.persistence.*;
import java.util.Date;

@Entity
public class Member {
    //PK
    @Id
    private Long id;

    @Column(name = "name")
    private String username;

    private Integer age;

    @Enumerated(EnumType.STRING)
    private RoleType roleType;

    @Temporal(TemporalType.TIMESTAMP)
    private Date lastModifiedDate;

    @Temporal(TemporalType.TIMESTAMP)
    private Date createdDate;

    @Lob
    private String description;

    public Member(){}

}
