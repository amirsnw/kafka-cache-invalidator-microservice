package org.snw.location.core.controller.dto;

import lombok.*;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode
public class LocationDto {

    private String id;

    private String value;
}
