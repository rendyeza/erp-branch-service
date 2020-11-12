package id.nexbyte.erp.branchservice.model;

import id.nexbyte.erp.core.entity.common.BaseEntity;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.Type;

import javax.persistence.*;
import java.util.UUID;

@Getter
@Setter
@Entity
public class Branch extends BaseEntity {
    @Id
    @GeneratedValue(
            strategy = GenerationType.AUTO,
            generator = "pg-uuid"
    )
    @GenericGenerator(
            name = "pg-uuid",
            strategy = "uuid2",
            parameters = @org.hibernate.annotations.Parameter(
                    name = "uuid_gen_strategy_class",
                    value = "id.nexbyte.erp.core.entity.common.PostgresUUIDGenerationStrategy"
            )
    )
    @Type(type = "pg-uuid")
    @Column(updatable = false, nullable = false)
    private UUID id;

    @Column(nullable = false, columnDefinition = "varchar(50)")
    private String name;

    @Column(columnDefinition = "varchar(100)")
    private String address;

    @Column(name = "phone_number", columnDefinition = "varchar(25)")
    private String phoneNumber;

    @Type(type = "pg-uuid")
    @Column(name = "manager_id")
    private UUID managerId;

    public Branch() {
        super();
    }

    public Branch(String name, String address, String phoneNumber, UUID managerId, UUID createdBy, UUID updatedBy) {
        super(createdBy, updatedBy);
        this.name = name;
        this.address = address;
        this.phoneNumber = phoneNumber;
        this.managerId = managerId;
    }
}
