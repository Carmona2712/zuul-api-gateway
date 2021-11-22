package com.raca.zuul.filters;


import com.netflix.zuul.ZuulFilter;
import com.netflix.zuul.context.RequestContext;
import com.netflix.zuul.exception.ZuulException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;

@Component
public class PreTimeTrasncurredFilter extends ZuulFilter {

    private static Logger log = LoggerFactory.getLogger(PreTimeTrasncurredFilter.class);


    @Override
    public String filterType() {
        return "pre";
    }

    @Override
    public int filterOrder() {
        return 1;
    }

    @Override
    public boolean shouldFilter() {
        return true;
    }

    @Override
    public Object run() throws ZuulException {

        RequestContext ctx = RequestContext.getCurrentContext();
        HttpServletRequest httpServletRequest = ctx.getRequest();
        log.info(String.format("%s request enrutado a %s",httpServletRequest.getMethod(),httpServletRequest.getRequestURL().toString()));
        Long timeStart = System.currentTimeMillis();
        httpServletRequest.setAttribute("timeStart",timeStart);

        return null;
    }
}
