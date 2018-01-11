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

//это вьюха
@Entity
@Table(name="tour_view")
public class TourView implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="id")
    private Integer id;

    @Column(name = "country", nullable = false)
    private String country;

    @Column(name = "tour_type", nullable = false)
    private String tourType;

    @Column(name = "start_date", nullable = false)
    @DateTimeFormat(pattern = "yyyy-MM-dd") //to handle binding with form input
    @Temporal(value=TemporalType.DATE) //убираем время из Date, оставляем только дату
    private Date startDate;

    @Column(name = "adults", nullable = false)
    private Integer adults;

    @Column(name = "children", nullable = false)
    private Integer children;

    @Column(name = "nights", nullable = false)
    private Integer nights;

    @Column(name = "hotel", nullable = false)
    private String hotel;

    @Column(name = "meal_type", nullable = false)
    private String mealType;

    @Column(name = "price", nullable = false)
    private BigDecimal price;

//    @Column(name = "price_from", nullable = false)
//    private BigDecimal priceFrom;
//
//    @Column(name = "price_to", nullable = false)
//    private BigDecimal priceTo;

    public TourView() {
    }


    public TourView(Integer id, String country, String tourType,
                    Date startDate, Integer adults, Integer children,
                    Integer nights, String hotel, String mealType, BigDecimal price) {
        this.id = id;
        this.country = country;
        this.tourType = tourType;
        this.startDate = startDate;
        this.adults = adults;
        this.children = children;
        this.nights = nights;
        this.hotel = hotel;
        this.mealType = mealType;
        this.price = price;
    }

    public static TourView parse(Map<String, String[]> searchParameters) {
        TourView tourView = new TourView();

        for (Map.Entry<String, String[]> param : searchParameters.entrySet()) {
            String paramName = param.getKey();

            String paramValue = Arrays.toString( param.getValue());
            paramValue = paramValue.substring(1, paramValue.length() - 1);

            Field field = null;
            try {
                //field gets all information about field "paramName"
                field = TourView.class.getDeclaredField(paramName);
                //System.out.println(field);
                //After gerDeclaredField()  field = private java.lang.String main.java.edu.olya.mytour.dao.SearchParam.meal_type
            } catch (NoSuchFieldException e) {
                //throw new Exception("Unknown argument name: " + paramName);
                //System.out.println("Unknown argument name: " + paramName); // for submit button
                continue;
            }
            Class fieldType = field.getType(); //fieldType =  public class java.io.File

            try {
                PropertyEditor pe = PropertyEditorManager.findEditor(fieldType); //Class fieldType = field.getType()
                if (pe == null) { //if editor can't be found
                    //If the specified object argument is an instance of the class or interface
                    //declaring the underlying field
                    if (fieldType == Date.class && paramValue.length() > 0) {
                        //@param obj - the object whose field should be modified
                        //@param value - the new value for the field
                        field.set(tourView, new SimpleDateFormat("yyyy-MM-dd").parse(paramValue)); //field == reference to the field
                    }
                    if (fieldType == BigDecimal.class && (paramValue.length() > 0)) {
                        field.set(tourView, new BigDecimal(paramValue));
                    }
                    // if there is an editor
                } else if(paramValue.length() > 0) {
                    pe.setAsText(paramValue); //Set the property value by parsing a given String
                    field.set(tourView, pe.getValue());
                }
            } catch (NumberFormatException | IllegalAccessException | ParseException e) {
                //throw new ValidationException("Invalid data type " + paramName + " = " + paramValue + ", but required " + fieldType.getCanonicalName());
            }
        }

        return tourView;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public String getTourType() {
        return tourType;
    }

    public void setTourType(String tourType) {
        this.tourType = tourType;
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

    public String getHotel() {
        return hotel;
    }

    public void setHotel(String hotel) {
        this.hotel = hotel;
    }

    public String getMealType() {
        return mealType;
    }

    public void setMealType(String mealType) {
        this.mealType = mealType;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

//    public BigDecimal getPriceFrom() {return priceFrom;}
//
//    public void setPriceFrom(BigDecimal priceFrom) { this.priceFrom = priceFrom; }
//
//    public BigDecimal getPriceTo() { return priceTo; }
//
//    public void setPriceTo(BigDecimal priceTo) { this.priceTo = priceTo; }

    @Override
    public String toString() {
        return "TourView{" +
                "id=" + id +
                ", country='" + country + '\'' +
                ", tourType='" + tourType + '\'' +
                ", startDate=" + startDate +
                ", adults=" + adults +
                ", children=" + children +
                ", nights=" + nights +
                ", hotel='" + hotel + '\'' +
                ", mealType='" + mealType + '\'' +
                ", price=" + price +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        TourView tourView = (TourView) o;

        if (id != null ? !id.equals(tourView.id) : tourView.id != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        return id != null ? id.hashCode() : 0;
    }
}
