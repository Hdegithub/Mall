package com.geekaca.mall.utils;

import com.geekaca.mall.common.Constants;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.net.InetAddress;
import java.net.UnknownHostException;

@Component
public class NetUtil {

    @Value("${server.port}")
    private int port;


    public int getPort() {
        return port;
    }



    public String getIp() {
        try {
            return InetAddress.getLocalHost().getHostAddress();
        } catch (UnknownHostException e) {
            e.printStackTrace();
        }
        return null;
    }

    public String getWebPath(){
        return "http://"+getIp()+":"+getPort();
    }

    public String getWebUploadPath(){
        return "http://"+getIp()+":"+getPort() +"/upload";
    }
}
