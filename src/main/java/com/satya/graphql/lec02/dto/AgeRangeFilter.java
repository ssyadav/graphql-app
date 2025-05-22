package com.satya.graphql.lec02.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class AgeRangeFilter {
    private Integer minAge;
    private Integer maxAge;
}
