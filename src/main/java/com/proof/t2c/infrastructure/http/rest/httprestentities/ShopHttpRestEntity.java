package com.proof.t2c.infrastructure.http.rest.httprestentities;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.proof.t2c.domain.entities.Shop;
import lombok.*;

import java.util.Date;

@Getter @Setter @Builder @NoArgsConstructor @AllArgsConstructor
public class ShopHttpRestEntity {

	// @formatter:off
	private Integer id;
	private String name;
	private String phone;
	private String country;
	private String province;
	private String town;
	private Integer zip;
	private String address;
	@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd/MM/yyyy HH:mm:ss", timezone = "Europe/Madrid")
	private Date createdAt;
	@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd/MM/yyyy HH:mm:ss", timezone = "Europe/Madrid")
	private Date updatedAt;
	// @formatter:on

	public static ShopHttpRestEntity fromEntity(Shop shop) {
		if (shop == null) {
			return null;
		}
		return ShopHttpRestEntity.builder()
			.id(shop.getId())
			.name(shop.getName())
			.phone(shop.getPhone())
			.country(shop.getCountry())
			.province(shop.getProvince())
			.town(shop.getTown())
			.zip(shop.getZip())
			.address(shop.getAddress())
			.createdAt(shop.getCreatedAt())
			.updatedAt(shop.getUpdatedAt())
			.build();
	}

	public Shop toEntity() {
		return Shop.builder()
			.id(this.id)
			.name(this.name)
			.phone(this.phone)
			.country(this.country)
			.province(this.province)
			.town(this.town )
			.zip(this.zip)
			.address(this.address)
			.createdAt(this.createdAt)
			.updatedAt(this.updatedAt)
			.build();
	}

}
