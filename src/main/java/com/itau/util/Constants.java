package com.itau.util;

public class Constants {

	private Constants() {

	}

	public static final String ROUTE_CONSULTA_DATOS = "direct:consulta-soap";
	public static final String PROCESO_ID = "procesoId";
	public static final String[] HEADERS = { "custPermId", "custType", "restrictedDbInd", "restrictedDrInd",
			"lockStatusInd", "requestUUID", "dateTime", "originatorName", "originatorType", "terminalId" };
	
	public static final String ROUTE_CONSUMO_SOAP = "direct:consumo-soap";
	public static final String ROUTE_VALIDATOR_STATUS = "direct:validator-data";
	public static final String RESPONSE_STATUS = "responseStatus";
	public static final String RESPONSE_TRNINFOLIST = "responseTrnInfoList";
	public static final String RESPONSE_ACCOUNTS_CONTAC = "responseAccountsContact";
	
	
	
}
