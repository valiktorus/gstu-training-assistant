package by.gstu.workout.service.filtration;

import lombok.Getter;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

@Service
@Getter
public class FiltrationService {
    @Value("${service.default-filtration-value}")
    private String defaultFiltrationValue;
}
