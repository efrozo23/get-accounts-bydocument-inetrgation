package com.itau.routes;

import org.apache.camel.builder.RouteBuilder;
import org.apache.camel.model.rest.RestBindingMode;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.env.Environment;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Component;

import com.itau.util.Constants;

import io.swagger.annotations.Api;

/**
 * 
 * @author Assert Solutions S.A.S
 *
 */
@Component
@Api(value = "Initial Proyect Camel-REST", description = "Estrucutura Basica Proyecto Rest Y Camel")
public class RestDslMainRoute extends RouteBuilder {

    @Value("${camel.component.servlet.mapping.context-path}")
    private String contextPath;

    @Autowired
    private Environment env;
    

    @Override
    public void configure() throws Exception {
    // @formatter:off
        restConfiguration()
            .component("servlet")
            .bindingMode(RestBindingMode.json)
            .dataFormatProperty("prettyPrint", "true")
            .enableCORS(true)
            .port(env.getProperty("server.port", "8080"))
            .contextPath(contextPath.substring(0, contextPath.length() - 2))
            // turn on swagger api-doc
            .apiContextPath("/api-doc")
            .apiProperty("api.title",  env.getProperty("api.title"))
            .apiProperty("api.version", env.getProperty("api.version"));
        
        rest().description(env.getProperty("api.description"))
            .consumes(MediaType.APPLICATION_JSON_UTF8_VALUE)
            .produces(MediaType.APPLICATION_JSON_UTF8_VALUE)
        
        .get(env.getProperty("endpoint.healtcheck")).description(env.getProperty("api.description.healtcheck")).outType(String.class)
        	.route().routeId("HEALTCHECK").setBody(constant("OK")).endRest()
        .get(env.getProperty("endpoint.getaccounstbydocument")).id("GET_ACCOUNTS_BY_DOCUMENTS").description(env.getProperty("api.description.getaccounstbydocument")).outType(String.class)
		   	.param().name("restrictedDbInd").description("Filtro").dataType("int").defaultValue("false")
			.required(false)
			.endParam()
			.param().name("restrictedCrInd").description("Filtro dos").dataType("int").defaultValue("false")
			.required(false)
			.endParam()
			.param().name("lockStatusInd").description("Filtro tres").dataType("int").defaultValue("false")
			.required(false)
			.endParam()
			.param().name("acctStatusCode").description("Filtro cuatro").dataType("int").defaultValue("false")
			.required(false)
			.endParam()
			.param().name("additionalStatus").description("Filtro cinco").dataType("int").defaultValue("false")
			.required(false)
			.endParam()
			
        	.to(Constants.ROUTE_CONSULTA_DATOS);
        
       
        // @formatter:on
    }

}
