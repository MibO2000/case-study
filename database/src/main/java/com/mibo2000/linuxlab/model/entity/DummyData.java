package com.mibo2000.linuxlab.model.entity;

import com.mibo2000.linuxlab.model.dto.Post;
import com.mibo2000.linuxlab.model.dto.Product;
import com.mibo2000.linuxlab.model.dto.Quote;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;


/**
 * @author : MibO2000
 */
@Document("DUMMY_DATA")
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class DummyData {
    @Id
    Long id;
    Product product;
    Post post;
    Quote quote;
}
