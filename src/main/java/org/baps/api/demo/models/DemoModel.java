package org.baps.api.demo.models;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class DemoModel {
    private String demoId;
    private String name;
    private Integer number;
}
