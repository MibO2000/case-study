package com.mibo2000.linuxlab.business.dto;

import com.mibo2000.linuxlab.model.dto.Post;
import com.mibo2000.linuxlab.model.dto.Product;
import com.mibo2000.linuxlab.model.dto.Quote;
import lombok.*;

/**
 * @author : MibO2000
 */
@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class DummySingleData {
    Post post;
    Product product;
    Quote quote;
}
