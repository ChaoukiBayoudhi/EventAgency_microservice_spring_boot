package tn.esb.siad.eventAgency.Domains;

import jakarta.persistence.*;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.NonNull;

@Entity
@Data
@NoArgsConstructor
@EqualsAndHashCode(exclude = "id")
@Table(name = "address_table")
public class Address {
    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    private Long id;
    @NonNull
    private int houseNumber;
    @NonNull
    private String street;
    @NonNull
    private String city;
    private String state;
    @NonNull
    private int zipCode;
    //implement the 1-1 association between address and participant
    @OneToOne(mappedBy = "address")
    //mappedBy tells the ORM that the association is already mapped by the participant's address attribute
    //mappedBy tells the ORM that the two attributes  are provided by the same association
    private Participant participant;
}
