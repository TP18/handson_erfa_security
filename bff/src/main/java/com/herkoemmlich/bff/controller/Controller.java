package com.herkoemmlich.bff.controller;

import com.herkoemmlich.bff.domain.Customer;
import com.herkoemmlich.bff.repository.DbServiceRepository;
import com.herkoemmlich.bff.repository.DecisionServiceRepository;

import com.microsoft.aad.msal4j.ClientCredentialFactory;
import com.microsoft.aad.msal4j.ConfidentialClientApplication;
import com.microsoft.aad.msal4j.IAuthenticationResult;
import com.microsoft.aad.msal4j.OnBehalfOfParameters;
import com.microsoft.aad.msal4j.UserAssertion;

import java.util.Base64;
import java.util.Collections;

import com.herkoemmlich.bff.controller.exception.CustomerNotFoundException;
import com.herkoemmlich.bff.controller.exception.BackendNotAvailableException;
import com.herkoemmlich.bff.controller.exception.TokenNotReceivedException;

import lombok.extern.slf4j.Slf4j;

import org.apache.logging.log4j.util.Strings;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.*;

@RestController
@Slf4j
public class Controller {

    @Autowired
    DbServiceRepository dbServiceRepository;

    @Autowired
    DecisionServiceRepository decisionServiceRepository;

//************************ Start Security *******************

    @Value("${azure.token.authority}")
    private String tokenAuthority;

    @Value("${azure.token.clientid}")
	private String clientId;

	@Value("${azure.token.clientsecret}")
	private String clientSecret;

	@Value("${azure.db.scope}")
	private String dbScope;

	@Value("${azure.decision.scope}")
	private String decisionScope;

//************************ End Security *******************

    @PostMapping(path = "/", consumes = "text/plain", produces = "application/json")
    public CalculateResponse calculateRisk(@RequestBody String customerId,
            @RequestHeader(value = "Authorization", required = false) String authHeader) {
        logToken(authHeader);
        log.info("Calculating sterberisiko of customer with id={}", customerId);

        String onBehalfHeader = null;

//************************ Start Security *******************
/*
        if (!Strings.isEmpty(authHeader)) {
            log.info("Issuing onbehalfof token for db service");
            onBehalfHeader = getTokenOnBehalfOf(authHeader, dbScope);
            if (onBehalfHeader == null) {
                throw new TokenNotReceivedException();
            }
        }
*/    
//************************ End Security *******************

        Customer customer = null;
        try {
            customer = dbServiceRepository.getCustomer(customerId,
                    onBehalfHeader);
            if (customer == null) {
                throw new CustomerNotFoundException();
            }
            log.info("customer received = {}", customer);
        } catch (Exception e) {
            throw new BackendNotAvailableException();
        }

//************************ Start Security *******************
/*
        if (!Strings.isEmpty(authHeader)) {
            log.info("Issuing onbehalfof token for decision service");
            onBehalfHeader = getTokenOnBehalfOf(authHeader, decisionScope);
            if (onBehalfHeader == null) {
                throw new TokenNotReceivedException();
            }
        }
*/
//************************ End Security *******************       

        try {
            float calculatedRisk = Float.parseFloat(decisionServiceRepository.calculateRisk(customer,
                    onBehalfHeader));
            log.info("calculated risk={} for customer with id={}", calculatedRisk, customerId);

            return new CalculateResponse(customer, calculatedRisk);
        } catch (Exception e) {
            throw new BackendNotAvailableException();
        }
    }

    public static class CalculateResponse {
        private Customer customer;
        private float risk;

        public CalculateResponse(Customer customer, float risk) {
            this.customer = customer;
            this.risk = risk;
        }

        public Customer getCustomer() {
            return customer;
        }

        public void setCustomer(Customer customer) {
            this.customer = customer;
        }

        public float getRisk() {
            return risk;
        }

        public void setRisk(float risk) {
            this.risk = risk;
        }
    }

//************************ Start Security *******************
/*
    private String getTokenOnBehalfOf(String authHeader, 
                String azureTokenScope) {
		String assertion = authHeader.substring(7);

        try {
            ConfidentialClientApplication application = ConfidentialClientApplication
                    .builder(clientId, ClientCredentialFactory.createFromSecret(clientSecret))
                    .authority(tokenAuthority)
                    .build();

            OnBehalfOfParameters parameters = OnBehalfOfParameters
                    .builder(Collections.singleton(azureTokenScope), new UserAssertion(assertion))
                    .build();
            
            IAuthenticationResult authResult = application.acquireToken(parameters).join();

            return new StringBuilder()
				    .append("Bearer").append(" ")
				    .append(authResult.accessToken())
				    .toString();
        } catch (Exception e) {
            return null;
        }
	}
*/
//************************ End Security *******************

    private void logToken(String authHeader) {
        if (Strings.isEmpty(authHeader)) return;
        
        try {
            String[] chunks = authHeader.substring(7).split("\\.");
            JSONObject json = (JSONObject) new JSONParser().parse(
                    new String(Base64.getDecoder().decode(chunks[1])));

            String subject = (String) json.get("upn");
            String client = (String) json.get("appid");
            String audience = (String) json.get("aud");
            log.info("Got token with subject={} and client={} for audience={}",
                    subject, client, audience);
        } catch (Exception e) {
            log.error("Cannot parse JWT");
        }
    }

}
