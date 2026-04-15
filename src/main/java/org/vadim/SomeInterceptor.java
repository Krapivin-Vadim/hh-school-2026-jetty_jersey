package org.vadim;

import jakarta.ws.rs.WebApplicationException;
import jakarta.ws.rs.container.ContainerRequestContext;
import jakarta.ws.rs.container.ContainerRequestFilter;
import jakarta.ws.rs.container.ContainerResponseContext;
import jakarta.ws.rs.container.ContainerResponseFilter;
import jakarta.ws.rs.ext.*;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.nio.charset.StandardCharsets;

@Provider
public class SomeInterceptor implements ReaderInterceptor, ContainerResponseFilter, ContainerRequestFilter {

    @Override
    public Object aroundReadFrom(ReaderInterceptorContext readerInterceptorContext) throws IOException, WebApplicationException {
        // РќР°СЃРєРѕР»СЊРєРѕ СЏ РїРѕРЅСЏР», С‚Рѕ Сѓ
        byte[] byteBuffer = readerInterceptorContext.getInputStream().readAllBytes();
        String requestBody = new String(byteBuffer, StandardCharsets.UTF_8);
        System.out.println("Body " + requestBody);
        readerInterceptorContext.setInputStream(new ByteArrayInputStream(byteBuffer));
        return readerInterceptorContext.proceed();
    }


    @Override
    public void filter(ContainerRequestContext containerRequestContext, ContainerResponseContext containerResponseContext) throws IOException {
        System.out.println("Status " + containerResponseContext.getStatus());
        System.out.println("--- SEND RESPONSE ---");
    }

    @Override
    public void filter(ContainerRequestContext containerRequestContext) throws IOException {
        System.out.println("--- GET REQUEST ---");
        System.out.println("Method " + containerRequestContext.getMethod());
    }
}