package com.imf.alumnos.daw.tfg.alexdiaz.towatchback.model.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class StreamingPlatformMediaDto {
    private String platformName;
    private String url;
}
