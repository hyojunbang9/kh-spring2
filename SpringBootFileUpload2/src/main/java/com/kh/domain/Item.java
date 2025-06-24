package com.kh.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor

public class Item {
	private int id;
	private String name;
	private int price;
	private String description;
	private String pctureUrl;
}
