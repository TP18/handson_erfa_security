package com.herkoemmlich.decision;


import com.herkoemmlich.decision.domain.Customer;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.Random;

@Service
public class RiskCalculationService {
    // TODO DYU&Team: 07.07.2021 make some super cool calculations here ;-)
    public float calculateRisk(Customer customer) {
        return getRandomRisikorate();
    }

    private float getRandomRisikorate() {

        float leftLimit = 0F;
        float rightLimit = 100F;
        float v = leftLimit + new Random().nextFloat() * (rightLimit - leftLimit);
        return new BigDecimal(v).setScale(2, RoundingMode.HALF_UP).floatValue();
    }
}
