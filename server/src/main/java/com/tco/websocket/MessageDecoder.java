package com.tco.websocket;

import com.google.gson.Gson;
import com.tco.misc.JSONValidator;

import javax.websocket.DecodeException;
import javax.websocket.Decoder;
import javax.websocket.EndpointConfig;
import java.io.IOException;

public class MessageDecoder implements Decoder.Text<Message> {
    @Override
    public Message decode(String s) throws DecodeException {
        return new Gson().fromJson(s, Message.class);
    }

    @Override
    public boolean willDecode(String s) {
        try {
            JSONValidator.validate(s, Message.class);
        } catch (IOException e) {
            return false;
        }
        return true;
    }

    @Override
    public void init(EndpointConfig endpointConfig) {

    }

    @Override
    public void destroy() {

    }
}
