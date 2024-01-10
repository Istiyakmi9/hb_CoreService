package com.bot.coreservice.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;
import org.springframework.stereotype.Component;
@Data
@Component
public class FilterModel {
    boolean isActive;
    @JsonProperty("SearchString")
    String searchString ;
    @JsonProperty("PageIndex")
    int pageIndex;
    @JsonProperty("PageSize")
    int pageSize;
    @JsonProperty("SortBy")
    String sortBy;
    int companyId;;
    int offsetIndex;
    Long employeeId;

}
