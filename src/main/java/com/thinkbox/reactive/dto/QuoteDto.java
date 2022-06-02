package com.thinkbox.reactive.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class QuoteDto {

	private String id;	
	private String symbol;
	private String date;
	private Double open;
	private Double high;
	private Double low;
	private Double close;
	private Long volume;
}
