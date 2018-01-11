package edu.olya.tour.model;

import org.springframework.format.annotation.DateTimeFormat;

import java.beans.PropertyEditor;
import java.beans.PropertyEditorManager;
import java.io.Serializable;
import java.lang.reflect.Field;
import java.math.BigDecimal;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Date;
import java.util.Map;
import javax.persistence.*;


@Entity
@Table(name="all_tours")
public class Tour implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="id")
    private Integer id;

    @Column(name = "country_id", nullable = false)
    private Integer countryId;

    @Column(name = "tour_type_id", columnDefinition="integer default 1")
    private Integer tourTypeId;

    //In 2017, JPA still has only @Column(columnDefinition='...') to which you put the literal SQL definition of the column.
    @Column(name = "start_date", columnDefinition="date DEFAULT (now())::date")
    @DateTimeFormat(pattern = "yyyy-MM-dd") //to handle binding with form input
    @Temporal(value=TemporalType.DATE) //to set date without time
    private Date startDate;

    @Column(name = "adults", columnDefinition="integer default 2")
    private Integer adults;

    @Column(name = "children", columnDefinition="integer default 0")
    private Integer children;

    @Column(name = "nights", columnDefinition="integer default 7")
    private Integer nights;

    @Column(name = "hotel_id", nullable = false)
    private Integer hotelId;

    @Column(name = "meal_type_id", nullable = false)
    private Integer mealTypeId;

    @Column(name = "price", columnDefinition ="numeric default 0")
    private BigDecimal price;

    public Tour() {
    }

    public Tour( Integer countryId, Integer tourTypeId, Date startDate,
                 Integer adults, Integer children, Integer nights, Integer hotelId,
                 Integer mealTypeId, BigDecimal price) {
        this.countryId = countryId;
        this.tourTypeId = tourTypeId;
        this.startDate = startDate;
        this.adults = adults;
        this.children = children;
        this.nights = nights;
        this.hotelId = hotelId;
        this.mealTypeId = mealTypeId;
        this.price = price;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getCountryId() {
        return countryId;
    }

    public void setCountryId(Integer countryId) {
        this.countryId = countryId;
    }

    public Integer getTourTypeId() {
        return tourTypeId;
    }

    public void setTourTypeId(Integer tourTypeId) {
        this.tourTypeId = tourTypeId;
    }

    public Date getStartDate() {
        return startDate;
    }

    public void setStartDate(Date startDate) {
        this.startDate = startDate;
    }

    public Integer getAdults() {
        return adults;
    }

    public void setAdults(Integer adults) {
        this.adults = adults;
    }

    public Integer getChildren() {
        return children;
    }

    public void setChildren(Integer children) {
        this.children = children;
    }

    public Integer getNights() {
        return nights;
    }

    public void setNights(Integer nights) {
        this.nights = nights;
    }

    public Integer getHotelId() {
        return hotelId;
    }

    public void setHotelId(Integer hotelId) {
        this.hotelId = hotelId;
    }

    public Integer getMealTypeId() {
        return mealTypeId;
    }

    public void setMealTypeId(Integer mealTypeId) {
        this.mealTypeId = mealTypeId;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    @Override
    public String toString() {
        return "Tour{" +
                "id=" + id +
                ", countryId=" + countryId +
                ", tourTypeId=" + tourTypeId +
                ", startDate=" + startDate +
                ", adults=" + adults +
                ", children=" + children +
                ", nights=" + nights +
                ", hotelId=" + hotelId +
                ", mealTypeId=" + mealTypeId +
                ", price=" + price +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Tour tour = (Tour) o;

        if (id != null ? !id.equals(tour.id) : tour.id != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        return id != null ? id.hashCode() : 0;
    }
}
