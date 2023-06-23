package com.rodion.springboot.spring_boot_2_and_spring_web_flux.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public final class Quote {
    private String id;
    private String book;
    private String content;
}
