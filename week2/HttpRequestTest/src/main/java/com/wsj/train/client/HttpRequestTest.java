package com.wsj.train.client;

import org.apache.http.HttpEntity;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;

import java.io.IOException;

public class HttpRequestTest {
    public static void main(String[] args) {
        HttpGet get = new HttpGet("http://localhost:8801");

        try (CloseableHttpClient client = HttpClients.createDefault();
            CloseableHttpResponse response = client.execute(get)) {

            HttpEntity entity = response.getEntity();
            System.out.println(EntityUtils.toString(entity));

        } catch (ClientProtocolException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
