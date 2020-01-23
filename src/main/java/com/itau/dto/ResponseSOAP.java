package com.itau.dto;

import java.util.ArrayList;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.node.JsonNodeFactory;

public class ResponseSOAP {

	@JsonProperty(value = "Header")
	public JsonNode header = JsonNodeFactory.instance.objectNode();
	@JsonProperty(value = "Body")
	public Body bodyObject;

	// Getter Methods

	public static class Body {

		@JsonProperty(value = "getAccountsDetailByDocumentResponse")
		public GetAccountsDetailByDocumentResponse getAccountsDetailByDocumentResponse = new GetAccountsDetailByDocumentResponse();
	}

	public static class GetAccountsDetailByDocumentResponse {

		@JsonProperty(value = "HeaderResponse")
		public JsonNode headerResponse = JsonNodeFactory.instance.objectNode();

		@JsonProperty(value = "AccountsDetailList")
		public AccountsDetailList contactList = new AccountsDetailList();

	}

	public static class AccountsDetailList {

		@JsonProperty(value = "AccountsDetailRecord")
		public List<AccountsDetailRecord> accountsDetailRecord = new ArrayList<>();
	}

	public static class AccountsDetailRecord {

		@JsonProperty(value = "acctStatusDesc")
		public String acctStatusDesc;

		@JsonProperty(value = "startDt")
		public String startDt;

		@JsonProperty(value = "desc")
		public String desc;

		@JsonProperty(value = "aging")
		public String aging;

		@JsonProperty(value = "payOffAmt")
		public String payOffAmt;

		@JsonProperty(value = "pmtAddAcct")
		public String pmtAddAcct;

		@JsonProperty(value = "dueDt")
		public String dueDt;
		@JsonProperty(value = "overLimitAmt")
		public String overLimitAmt;
		@JsonProperty(value = "restrict")
		public String restrict;

		@JsonProperty(value = "restrictClass")
		public String restrictClass;
		@JsonProperty(value = "restrictReason")
		public String restrictReason;
		@JsonProperty(value = "curRate")
		public String curRate;

		@JsonProperty(value = "curCode")
		public String curCode;

		@JsonProperty(value = "acctRelType")
		public String acctRelType;

		@JsonProperty(value = "acctRelId")
		public String acctRelId;

		@JsonProperty(value = "AcctBal")
		public JsonNode acctBal = JsonNodeFactory.instance.objectNode();
		@JsonProperty(value = "Acct")
		public JsonNode acct = JsonNodeFactory.instance.objectNode();
		@JsonProperty(value = "OverDraftAcctAmt")
		public JsonNode overDraftAcctAmt = JsonNodeFactory.instance.objectNode();
		@JsonProperty(value = "ExtAcctBal")
		public JsonNode extAcctBal = JsonNodeFactory.instance.objectNode();
		@JsonProperty(value = "SwapAcctBal")
		public JsonNode swapAcctBal = JsonNodeFactory.instance.objectNode();
		@JsonProperty(value = "CurAmt")
		public JsonNode curAmt = JsonNodeFactory.instance.objectNode();
		@JsonProperty(value = "AcctTaxInfo")
		public JsonNode acctTaxInfo = JsonNodeFactory.instance.objectNode();
		@JsonProperty(value = "TaxPaidCurAmt")
		public JsonNode taxPaidCurAmt = JsonNodeFactory.instance.objectNode();
		@JsonProperty(value = "RefInfoAcct")
		public JsonNode refInfoAcct = JsonNodeFactory.instance.objectNode();

	}

}
