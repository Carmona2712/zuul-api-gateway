package com.raca.zuul.filters;


import com.netflix.zuul.ZuulFilter;
import com.netflix.zuul.context.RequestContext;
import com.netflix.zuul.exception.ZuulException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;

@Component
public class PostTimeTrasncurredFilter extends ZuulFilter {

    private static Logger log = LoggerFactory.getLogger(PostTimeTrasncurredFilter.class);


    @Override
    public String filterType() {
        return "post";
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
        log.info("-> Entrando a post");
        Long timeStart = (Long) httpServletRequest.getAttribute("timeStart");
        Long timeEnd = System.currentTimeMillis();
        Long timeTranscurred =  timeEnd - timeStart;

        log.info("Tiempo transucrrido (seg) : "+timeTranscurred/1000);
        log.info("Tiempo transucrrido (ms) : "+timeTranscurred);


        return null;
    }
}
