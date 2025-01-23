package model;

import lombok.*;

import java.math.BigDecimal;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class Card {
    private BigDecimal paymentMethodID;
    private String cardCountryCode;
    private String cardScheme;
    private String last4Digits;
    private BigDecimal binRange;
    private String cardExpiry;

}
