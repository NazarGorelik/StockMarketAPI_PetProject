package java_code.controllers;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import java_code.dto.StockDataDTO;
import java_code.feign.FeignServiceUtil;
import java_code.util.exceptions.presentationLayer.CompanyTickerDoesntExistException;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequiredArgsConstructor
@SecurityRequirement(name = "bearerAuth")
public class UserController {

    private final FeignServiceUtil feignServiceUtil;

    @Value("${API-TOKEN}")
    private String apiToken;


    @GetMapping("earningResult")
    public ResponseEntity<?> getEarningResult(String companyTicker){
        return feignServiceUtil.earningResult(apiToken, companyTicker);
    }


    //FOR PRIME CLIENTS
    @GetMapping("/recomendation")
    public String getRecommendation(String companyTicker) throws JsonProcessingException {
        ObjectMapper objectMapper = new ObjectMapper();

        ResponseEntity<?> response = feignServiceUtil.recommendation(apiToken, companyTicker);
        String respBody = objectMapper.writeValueAsString(response.getBody());

        List<StockDataDTO> list = objectMapper.readValue(respBody, new TypeReference<>(){});
        StockDataDTO stockDataDTO;
        try {
            stockDataDTO = list.get(0);
        }catch (IndexOutOfBoundsException e){
            throw new CompanyTickerDoesntExistException("Company with such ticker: " + companyTicker + " doesn't exist");
        }
        return buildFinalResponse(stockDataDTO, companyTicker);
    }

    private String buildFinalResponse(StockDataDTO stockDataDTO, String companyTicker){
        StringBuilder finalResponse = new StringBuilder("It is better to ");
        int votesForBuy = stockDataDTO.buy();
        int votesForSell = stockDataDTO.sell();
        if(votesForBuy > votesForSell)
            finalResponse.append("buy the company with ticker: " + companyTicker);
        else
            finalResponse.append("sell the company with ticker: " + companyTicker);

        finalResponse.append(". Here you can check the exact info: " + "\n" + stockDataDTO);

        return finalResponse.toString();
    }
}
