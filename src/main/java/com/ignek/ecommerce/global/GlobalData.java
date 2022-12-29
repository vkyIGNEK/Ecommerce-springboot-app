package com.ignek.ecommerce.global;

import java.util.ArrayList;
import java.util.List;

import com.ignek.ecommerce.model.Product;

public class GlobalData {
	
	public static List<Product> cart;
	
	static {
		cart = new ArrayList<Product>();
		
	}

}
