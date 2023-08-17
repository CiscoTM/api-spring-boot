package es.tuke.appgestion.services;

import org.springframework.stereotype.Service;

@Service
public class Ioperaciones implements Operaciones {

    
    @Override
    public int factorial(int num) {
        
        if (num < 0){
            throw new ArithmeticException();
        }

        if (num > 23) {
            throw new IllegalArgumentException("Overflow from integer");
        }

        return (num == 1 || num == 0) ? 1 : num * factorial(num-1);
        
    }
    
}
