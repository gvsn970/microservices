package com.nexii.MultipleFileUpload.bean;

import java.util.List;

public class Product {
	

		private String id;
		private String name;
		private double price;
		private List<String>photos;
		public List<String> getPhotos() {
			return photos;
		}
		public Product() {}
		public void setPhotos(List<String> photos) {
			this.photos = photos;
		}
		public String getId() {
			return id;
		}
		public void setId(String id) {
			this.id = id;
		}
		public String getName() {
			return name;
		}
		public void setName(String name) {
			this.name = name;
		}
		public double getPrice() {
			return price;
		}
		public void setPrice(double price) {
			this.price = price;
		}
		
	}


