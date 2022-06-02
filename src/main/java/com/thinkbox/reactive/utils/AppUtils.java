package com.thinkbox.reactive.utils;

import com.thinkbox.reactive.dto.ProductDto;
import com.thinkbox.reactive.dto.QuoteDto;
import com.thinkbox.reactive.entity.Product;
import com.thinkbox.reactive.entity.Quote;

import org.springframework.beans.BeanUtils;

public class AppUtils {


    public static QuoteDto entityToDto(Quote quote) {
    	QuoteDto quoteDto = new QuoteDto();
        BeanUtils.copyProperties(quote, quoteDto);
        return quoteDto;
    }

    public static Quote dtoToEntity(QuoteDto quoteDto) {
    	Quote quote = new Quote();
        BeanUtils.copyProperties(quoteDto, quote);
        return quote;
    }
}
