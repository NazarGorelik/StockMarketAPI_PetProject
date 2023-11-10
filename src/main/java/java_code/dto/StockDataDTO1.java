package java_code.dto;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class StockDataDTO1 {
    private int buy;
    private int hold;
    private String period;
    private int sell;
    private int strongBuy;
    private int strongSell;
    private String symbol;
}
