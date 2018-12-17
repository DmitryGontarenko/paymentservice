package com.acc.model;

import lombok.Data;

import java.util.ArrayList;
import java.util.List;

@Data
public class StatisticsResponse {
    private List<OfficeStatResponse> officeStatResponsesList = new ArrayList<>();
    private List<DataStatResponse> dataStatResponseList = new ArrayList<>();
}
