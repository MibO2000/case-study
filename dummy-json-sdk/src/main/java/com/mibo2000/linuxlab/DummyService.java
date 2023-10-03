package com.mibo2000.linuxlab;

import com.mibo2000.linuxlab.dto.DummyPostResponse;
import com.mibo2000.linuxlab.dto.DummyProductResponse;
import com.mibo2000.linuxlab.dto.DummyQuoteResponse;

/**
 * @author : MibO2000
 */
public interface DummyService {
    DummyPostResponse getDummyPost();
    DummyProductResponse getDummyProducts();
    DummyQuoteResponse getDummyQuotes();
}
