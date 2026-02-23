package com.mahmoudelgohary.ecommerce.pojo.request;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;

@AllArgsConstructor
@Data
public class ChangePasswordRequest{

	@JsonProperty("newPassword")
	private String newPassword;

	@JsonProperty("currentPassword")
	private String currentPassword;


}