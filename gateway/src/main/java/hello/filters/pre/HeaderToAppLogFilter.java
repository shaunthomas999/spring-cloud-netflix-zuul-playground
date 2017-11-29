package hello.filters.pre;

import com.netflix.zuul.ZuulFilter;
import com.netflix.zuul.context.RequestContext;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.MDC;
import org.springframework.cloud.sleuth.Span;

import javax.servlet.http.HttpServletRequest;

@Slf4j
public class HeaderToAppLogFilter extends ZuulFilter {

  private static final String HEADER_DELIMITER = "-";

  @Override
  public String filterType() {
    return "pre";
  }

  @Override
  public int filterOrder() {
    return 2;
  }

  @Override
  public boolean shouldFilter() {
    return true;
  }

  @Override
  public Object run() {
    RequestContext ctx = RequestContext.getCurrentContext();
    HttpServletRequest req = ctx.getRequest();

    if(null != req.getHeader("myHeader")) {
      String traceBaggageHeaderName = Span.SPAN_BAGGAGE_HEADER_PREFIX + HEADER_DELIMITER + "myHeader";

      MDC.put(traceBaggageHeaderName, req.getHeader("myHeader"));
      ctx.addZuulRequestHeader(traceBaggageHeaderName, req.getHeader("myHeader"));

      log.info("Added header # {} to MDC for tracing", traceBaggageHeaderName);
    }

    return null;
  }
}
