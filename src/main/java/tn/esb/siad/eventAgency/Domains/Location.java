package tn.esb.siad.eventAgency.Domains;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Data //lombok's annotation to generate getters, setters, required args constructor, toString, equals and hash
@NoArgsConstructor
@RequiredArgsConstructor //lombok's annotation to generate a constructor with all required arguments (annotated with @NonNull)
@EqualsAndHashCode(exclude = {"description", "id"}) //lombok's annotation that redefine the equals and hashcode methods
//two locations are equals if they have the same fields values except the description and id
public class Location {
    @Id
    //@GeneratedValue //this annotation tell the ORM that this attribute is generated automatically
    @GeneratedValue(strategy = GenerationType.IDENTITY)// the first added location will have id=1, the second id=2, ...
    private int id;
    @NonNull
    //set the name size to 50 characters
    @Column(name="location_name", length = 50,unique = true) //<=>in SQL : location_name VARCHAR(50) unique
    private String name;
    private int streetNumber;
    private String streetName;
    private String city;
    //set a default value for the country
    @Column(columnDefinition = "VARCHAR(50) default 'Tunisia'")
    private String country;
    @NonNull
    private int zipCode;
    private String description;

}
