package java_code.feign;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestHeader;

@FeignClient(value = "demo", url = "https://finnhub.io/api/v1")
public interface FeignServiceUtil {


    @GetMapping("/stock/earnings?symbol={companyTicker}")
    ResponseEntity<?> earningResult(@RequestHeader("X-Finnhub-Token") String token,
                                    @PathVariable("companyTicker") String companyTicker);

    @GetMapping("/stock/recommendation?symbol={companyTicker}")
    ResponseEntity<?> recommendation(@RequestHeader("X-Finnhub-Token") String token,
                                   @PathVariable("companyTicker") String companyTicker);
}
