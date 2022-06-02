package com.thinkbox.reactive.entity;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Data
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Document(collection = "quote")
public class Quote {

	@Id
	private String id;	
	private String symbol;
	private String date;
	private Double open;
	private Double high;
	private Double low;
	private Double close;
	private Long volume;
}