package models;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;

@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class StepsResponse {

    private String name;
    private String steps;
    private Boolean leaf;
    private Integer stepsCount;
    private Boolean hasContent;
}
