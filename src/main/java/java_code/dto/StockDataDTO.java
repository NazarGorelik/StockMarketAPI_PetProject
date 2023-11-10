package java_code.dto;

public record StockDataDTO(int buy, int hold, String period, int sell, int strongBuy, int strongSell, String symbol) {
}
