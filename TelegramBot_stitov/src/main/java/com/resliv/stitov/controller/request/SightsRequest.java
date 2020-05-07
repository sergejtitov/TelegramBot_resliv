package com.resliv.stitov.controller.request;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class SightsRequest {

    @NotEmpty
    @Size(max = 100)
    private String name;


    @Size(max = 200)
    private String location;

    @Size(max = 1000)
    private String sightsComment;

    @NotEmpty
    @Size(max = 100)
    private String cityName;
}
