package com.nortal.sk.ws;

import com.nortal.sk.ws.model.DigiDocService;

public class SimpleSkWs extends AbstractSkWs {
    {
        setDigiDocService(new DigiDocService(getClass().getResource("/DigiDocService_2_3.wsdl")));
        setEndpoint("https://tsp.demo.sk.ee/DigiDocService");
        setServiceName("Testimine");
    }
}
