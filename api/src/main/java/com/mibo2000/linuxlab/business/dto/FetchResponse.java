package com.mibo2000.linuxlab.business.dto;

import com.mibo2000.linuxlab.model.dto.Post;
import com.mibo2000.linuxlab.model.dto.Product;
import com.mibo2000.linuxlab.model.dto.Quote;
import lombok.*;

import java.util.List;

/**
 * @author : MibO2000
 */
@Builder
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class FetchResponse {
    List<Post> postList;
    List<Product> productList;
    List<Quote> quoteList;
    int pageNo;
    int pageSize;
}
