package com.resliv.stitov.domain.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import lombok.AllArgsConstructor;
import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Column;
import javax.persistence.ManyToOne;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import java.util.Objects;

@Data
@AllArgsConstructor
@Entity
@Table(name = "m_sights")
public class Sights {

    @Id
    @SequenceGenerator(name = "sightsIdSeq", sequenceName = "m_sights_id_seq", allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "sightsIdSeq")
    @Column(name = "sights_id")
    private Long sightsId;

    @Column
    private String name;

    @Column
    private String location;

    @Column(name = "sights_comment")
    private String sightsComment;

    @JsonBackReference
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "city_id")
    private City city;

    public Sights() {
    }

    @Override
    public String toString(){
        StringBuilder sb = new StringBuilder("Название: ");
        sb.append(name);
        sb.append("\n");
        if (location != null){
            sb.append("Местоположение: ");
            sb.append(location);
            sb.append("\n");
        }
        if (sightsComment != null){
            sb.append(sightsComment);
            sb.append("\n");
        }
        return  sb.toString();
    }

    @Override
    public int hashCode(){
        return Objects.hash(sightsId, name, location, sightsComment);
    }

    @Override
    public boolean equals(Object o){
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Sights sight = (Sights) o;
        return Objects.equals(sightsId, sight.sightsId) &&
                Objects.equals(name, sight.name) &&
                Objects.equals(location, sight.location) &&
                Objects.equals(sightsComment, sight.sightsComment);
    }
}
