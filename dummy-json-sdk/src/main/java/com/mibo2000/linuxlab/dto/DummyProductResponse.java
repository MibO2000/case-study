package com.mibo2000.linuxlab.dto;

import com.mibo2000.linuxlab.model.dto.Product;
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
public class DummyProductResponse extends DummyBasicResponse {
    List<Product> products;
}
