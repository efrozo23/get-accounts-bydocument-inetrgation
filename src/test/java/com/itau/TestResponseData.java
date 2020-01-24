package com.itau;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.Collections;

import org.apache.camel.BeanInject;
import org.apache.camel.CamelContext;
import org.apache.camel.Exchange;
import org.apache.camel.ProducerTemplate;
import org.apache.camel.builder.AdviceWithRouteBuilder;
import org.apache.camel.test.spring.MockEndpoints;
import org.apache.camel.test.spring.UseAdviceWith;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit4.SpringRunner;

import com.itau.util.Constants;

@RunWith(SpringRunner.class)
@Configuration
@MockEndpoints("log:*")
@UseAdviceWith
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.DEFINED_PORT)
public class TestResponseData {

	private Logger logger = LoggerFactory.getLogger(TestServiceSimulator.class);

	@Value("${server.port}")
	private String port;
	
	@Value("${path.test}")
	private String path;

	private final String URL = "http://localhost:";

	@BeanInject
	private CamelContext camelContext;

	@Autowired
	private TestRestTemplate testRestTemplate;
	
	@Test
	public void testErrorTecnico() throws Exception {

    	camelContext.getRouteDefinition("ROUTE_CONSULTA_SOAP").adviceWith(camelContext, new AdviceWithRouteBuilder() {
            @Override
            public void configure() throws Exception {
              // send the outgoing message to mock
         
              weaveByToUri(Constants.ROUTE_CONSUMO_SOAP).replace().inOut("mock:routeB").removeHeaders("*").to("velocity:templates/responseErrorTecnico.vm");
//              interceptSendToEndpoint(Constant.ROUTE_CONSUMO_SOAP).skipSendToOriginalEndpoint().to("velocity:templates/responseSOAP.vm");
            }
          });
    	
//    	ResponseEntity<String> response = testRestTemplate.exchange(URL + port + "GET/services/v2/accounts/accountsdetailbydocument?custType=1&custPermId=76313970", HttpMethod.GET	,entity, String.class);
//    	logger.info("Respuesta:{}", response.getBody());
//    	assertThat(response.getStatusCodeValue()).isEqualByComparingTo(400);
    	ProducerTemplate producerTemplate = camelContext.createProducerTemplate();
    	Exchange r = producerTemplate.request(Constants.ROUTE_CONSULTA_DATOS, (p)->{p.getIn().setHeader("id_cedula", "1_2345");});
    	logger.info("Objeto obtenido:{}" , r.getIn().getBody(String.class));
		assertThat(r.getIn().getHeader(Exchange.HTTP_RESPONSE_CODE)).isEqualTo(400);
    	
    	
	}
	
	@Test
	public void testErrorEstructura() throws Exception {

		HttpHeaders headers = new HttpHeaders();
		headers.setAccept(Collections.singletonList(MediaType.APPLICATION_JSON_UTF8));
		headers.add("requestUUID", "123456");
    	headers.add("dateTime", "2019-12-30T18:33:12");
    	headers.add("originatorName", "BancaMovil - APP PN");
    	headers.add("originatorType", "47");
    	headers.add("terminalId", "127.0.0.1");
    	HttpEntity<String> entity = new HttpEntity<>(headers);
    	
    	camelContext.getRouteDefinition("ROUTE_CONSULTA_SOAP").adviceWith(camelContext, new AdviceWithRouteBuilder() {
            @Override
            public void configure() throws Exception {
              // send the outgoing message to mock
          	//Ok, funcionan los dos.
//              weaveByToUri(Constants.ROUTE_CONSUMO_SOAP).replace().inOut("mock:routeB").removeHeaders("*").to("velocity:templates/responseErrorEstructura.vm");
              interceptSendToEndpoint(Constants.ROUTE_CONSUMO_SOAP).skipSendToOriginalEndpoint().to("velocity:templates/responseErrorEstructura.vm");
            }
          });
    	
    	ResponseEntity<String> response = testRestTemplate.exchange(URL + port + path, HttpMethod.GET	,entity, String.class);
    	logger.info("Respuesta:{}", response.getBody());
    	assertThat(response.getStatusCodeValue()).isEqualByComparingTo(400);
	}

}
