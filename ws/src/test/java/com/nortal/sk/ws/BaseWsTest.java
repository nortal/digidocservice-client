package com.nortal.sk.ws;

import org.junit.BeforeClass;

/**
 * @author Lauri Lättemäe <lauri.lattemae@nortal.com>
 */
public class BaseWsTest {
    @BeforeClass
    public static void init() {
        System.setProperty("com.sun.xml.internal.ws.transport.http.client.HttpTransportPipe.dump", "true");
    }
}
