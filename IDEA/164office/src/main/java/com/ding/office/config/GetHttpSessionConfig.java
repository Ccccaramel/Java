package com.ding.office.config;

import com.ding.office.constant.CommonCode;
import lombok.extern.slf4j.Slf4j;

import javax.websocket.HandshakeResponse;
import javax.websocket.server.HandshakeRequest;
import javax.websocket.server.ServerEndpointConfig;
import java.util.List;
import java.util.Map;

@Slf4j
public class GetHttpSessionConfig extends ServerEndpointConfig.Configurator {

    @Override
    public void modifyHandshake(ServerEndpointConfig sec, HandshakeRequest request, HandshakeResponse response) {
        final Map<String,Object> userProperties = sec.getUserProperties();
        Map<String, List<String>> headers = request.getHeaders();
        List<String> token = headers.get(CommonCode.SEC_WEBSOCKET_PROTOCOL);
        log.info("Sec-Websocket-Key:{}",headers.get("Sec-Websocket-Key"));
        if(token!=null){
            userProperties.put(CommonCode.SEC_WEBSOCKET_PROTOCOL,token.get(0));
        }
        else{
            userProperties.put(CommonCode.SEC_WEBSOCKET_PROTOCOL,"yoke");
        }
    }
}
