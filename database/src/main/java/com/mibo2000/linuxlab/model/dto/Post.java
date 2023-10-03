package com.mibo2000.linuxlab.model.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

/**
 * @author : MibO2000
 */
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class Post extends BasicDummyId {
    String title;
    String body;
    Long userId;
    List<String> tags;
    Integer reactions;
}
