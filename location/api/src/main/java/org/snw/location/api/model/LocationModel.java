package org.snw.location.api.model;

import lombok.*;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode
public class LocationModel {

    private String id;

    private String value;

}
