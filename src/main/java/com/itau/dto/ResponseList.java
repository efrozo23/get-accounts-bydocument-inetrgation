package com.itau.dto;

import javax.xml.bind.annotation.XmlRootElement;

import com.fasterxml.jackson.annotation.JsonAutoDetect;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.databind.node.JsonNodeFactory;

import io.swagger.annotations.ApiModel;

@XmlRootElement
@JsonAutoDetect
@JsonSerialize
@ApiModel(description = "Response DTO Object List Contacts")
@JsonPropertyOrder(value = {"TrnInfoList","Status"})
public class ResponseList extends Response{
	
	
	private static final long serialVersionUID = 7874327787708945391L;
	
	@JsonProperty(value = "AccountsDetailList")
	public transient JsonNode accountsDetailList = JsonNodeFactory.instance.objectNode();

}
