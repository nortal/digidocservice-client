package com.nortal.sk.client.ws.step;

import javax.xml.ws.soap.SOAPFaultException;

import com.nortal.sk.client.processor.Processor;
import com.nortal.sk.client.step.AbstractStep;
import com.nortal.sk.client.ws.processor.WsProcessor;
import com.nortal.sk.common.model.FaultRsp;
import com.nortal.sk.common.model.GeneralReq;
import com.nortal.sk.common.model.GeneralRsp;
import com.nortal.sk.ws.model.SesscodeReq;
import com.nortal.sk.ws.model.SesscodeRsp;

/**
 * @author Lauri Lättemäe <lauri.lattemae@nortal.com>
 */
public abstract class AbstractWsStep<T extends WsProcessor<?>, V extends GeneralReq> extends AbstractStep<T, V> {
    @Override
    @SuppressWarnings("unchecked")
    public GeneralRsp execute(Processor<?> processor) throws Exception {
        try {
            V req = getReq();
            if (req != null && SesscodeReq.class.isAssignableFrom(req.getClass())) {
                SesscodeRsp rsp = processor.getState().getResponse(SesscodeRsp.class);
                if (rsp != null) {
                    ((SesscodeReq) req).setSesscode(rsp.getSesscode());
                }
            }
            return innerExecute((T) processor, req);
        }
        catch (SOAPFaultException e) {
            return FaultRsp.of(e.getFault().getFaultString(), e.getFault().getDetail().getTextContent());
        }
    }
}
