package edu.olya.tour.model;

import org.hibernate.annotations.CacheConcurrencyStrategy;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Cacheable
@org.hibernate.annotations.Cache(usage= CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
@Table(name="meal_types")
public class MealType implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="id")
    private Integer id;

    @Column(name = "meal_type", nullable = false)
    private String name;

    public MealType() {
    }

    public MealType(Integer id, String name) {

        this.id = id;
        this.name = name;
    }

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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        MealType mealType = (MealType) o;

        if (id != null ? !id.equals(mealType.id) : mealType.id != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        return id != null ? id.hashCode() : 0;
    }
}
