package kryklyvets.project.restaurant.dtos;

import lombok.Data;

import java.math.BigDecimal;
import java.util.Map;

@Data
public class CurrencyRequest {
    private String base;
    private Map<String, BigDecimal> rates;
}
