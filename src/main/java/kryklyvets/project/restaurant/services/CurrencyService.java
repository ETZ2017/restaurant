package kryklyvets.project.restaurant.services;

import kryklyvets.project.restaurant.clients.CurrencyClient;
import kryklyvets.project.restaurant.dtos.CurrencyRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class CurrencyService {
    private final CurrencyClient currencyClient;
    @Value("${fixer.access.key}")
    private String accessKey;


    public CurrencyRequest getCurrency(){
        return currencyClient.getCurrencies(accessKey);
    }

}
