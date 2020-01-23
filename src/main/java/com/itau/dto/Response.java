package com.itau.dto;

import java.io.Serializable;

import javax.xml.bind.annotation.XmlRootElement;

import com.fasterxml.jackson.annotation.JsonAutoDetect;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.databind.node.JsonNodeFactory;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

/**
 * 
 * @author
 *
 */
@XmlRootElement
@JsonAutoDetect
@JsonSerialize
@ApiModel(description = "Response DTO Object")
@JsonPropertyOrder(value = {"TrnInfoList","Status"})
public class Response implements Serializable {

    private static final long serialVersionUID = -6104876573750302537L;

    @JsonProperty(value = "Status")
    @ApiModelProperty(dataType = "Object")
    public transient JsonNode status = JsonNodeFactory.instance.objectNode();
    
    @JsonProperty(value = "TrnInfoList")
    @ApiModelProperty(dataType = "Object")
    public transient JsonNode trnInfoList = JsonNodeFactory.instance.objectNode();

}
