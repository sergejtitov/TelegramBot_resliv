package com.resliv.stitov.domain.model;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import lombok.AllArgsConstructor;
import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Column;
import javax.persistence.OneToMany;
import javax.persistence.FetchType;
import javax.persistence.CascadeType;
import java.util.Objects;
import java.util.Set;

@Data
@AllArgsConstructor
@Entity
@Table(name = "m_city")
public class City {

    @Id
    @SequenceGenerator(name = "cityIdSeq", sequenceName = "m_city_id_seq", allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "cityIdSeq")
    @Column(name = "city_id")
    private Long cityId;

    @Column
    private String name;

    @Column
    private String country;

    @Column
    private String comment;

    @JsonManagedReference
    @OneToMany(fetch = FetchType.EAGER, cascade = CascadeType.ALL, orphanRemoval = true, mappedBy = "city")
    private Set<Sights> sights;

    public City() {
    }

    @Override
    public String toString(){
        StringBuilder sb = new StringBuilder("Город: ");
        sb.append(name);
        sb.append("\n");
        sb.append("Страна: ");
        sb.append(country);
        sb.append("\n");
        if (comment != null){
            sb.append(comment);
            sb.append("\n");
        }
        if (!sights.isEmpty()){
            sb.append("Достопримечательности:");
            sb.append("\n");
            for (Sights sight: sights){
                sb.append(sight);
            }
        }
        return  sb.toString();
    }

    @Override
    public int hashCode(){
        return Objects.hash(cityId, name, country, comment);
    }

    @Override
    public boolean equals(Object o){
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        City city = (City) o;
        return Objects.equals(cityId, city.cityId) &&
                Objects.equals(name, city.name) &&
                Objects.equals(country, city.country) &&
                Objects.equals(comment, city.comment);
    }

}
