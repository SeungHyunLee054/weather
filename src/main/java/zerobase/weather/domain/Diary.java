package zerobase.weather.domain;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import java.time.LocalDate;

import static javax.persistence.GenerationType.IDENTITY;

@Getter
@Setter
@NoArgsConstructor
@Entity
public class Diary extends BaseEntity{
    @Id
    @GeneratedValue(strategy = IDENTITY)
    private int id;
    private String text;
    private LocalDate date;

    public void setDateWeather(DateWeather dateWeather) {
        this.date = dateWeather.getDate();
        this.setWeather(dateWeather.getWeather());
        this.setIcon(dateWeather.getIcon());
        this.setTemperature(dateWeather.getTemperature());
    }
}
