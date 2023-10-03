package com.mibo2000.linuxlab.model.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * @author : MibO2000
 */
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class Quote extends BasicDummyId {
    String quote;
    String author;
}
