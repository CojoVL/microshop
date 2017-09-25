package ro.microservices.gateway.filters;

import static org.springframework.cloud.netflix.zuul.filters.support.FilterConstants.PRE_DECORATION_FILTER_ORDER;
import static org.springframework.cloud.netflix.zuul.filters.support.FilterConstants.PRE_TYPE;
import static org.springframework.cloud.netflix.zuul.filters.support.FilterConstants.PROXY_KEY;
import static org.springframework.cloud.netflix.zuul.filters.support.FilterConstants.REQUEST_URI_KEY;

import com.netflix.zuul.ZuulFilter;
import com.netflix.zuul.context.RequestContext;
import org.springframework.context.annotation.Configuration;

@Configuration
public class CustomPathZuulFilter extends ZuulFilter {
    @Override
    public String filterType() {
        return PRE_TYPE;
    }

    @Override
    public int filterOrder() {
        return PRE_DECORATION_FILTER_ORDER + 1;
    }

    @Override
    public boolean shouldFilter() {
        RequestContext context = RequestContext.getCurrentContext();
        return context.get(PROXY_KEY).toString().equalsIgnoreCase("inventory_admin");
    }

    @Override
    public Object run() {
        RequestContext context = RequestContext.getCurrentContext();
        Object originalRequestPath = context.get(REQUEST_URI_KEY);
        String modifiedRequestPath = "/v1" + originalRequestPath;
        context.put(REQUEST_URI_KEY, modifiedRequestPath);

        return null;
    }
}
