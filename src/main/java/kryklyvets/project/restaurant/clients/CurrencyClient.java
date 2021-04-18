package kryklyvets.project.restaurant.clients;

import kryklyvets.project.restaurant.dtos.CurrencyRequest;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

@FeignClient(url = "http://data.fixer.io/api", name="fixer")
@Component
public interface CurrencyClient {
    @RequestMapping(method = RequestMethod.GET, value = "/latest")
    CurrencyRequest getCurrencies(@RequestParam String access_key);
}

