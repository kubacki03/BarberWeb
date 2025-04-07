package com.example.testsecurity;

import com.example.testsecurity.Models.Visit;
import com.example.testsecurity.Service.VisitService;
import com.example.testsecurity.Models.UserApp;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.data.util.Pair;

import java.nio.Buffer;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class VisitServiceTest {

    @Mock
    VisitRepository visitRepository;

    @InjectMocks
    VisitService visitService;

    @Test
    void shouldReturnVisitWhenVisitExists() {
        UserApp user = new UserApp();
        Visit visit = new Visit(1L, LocalDateTime.now(),LocalDateTime.now(),15,user);

        when(visitRepository.findById(1L)).thenReturn(Optional.of(visit));

        Visit result  = visitRepository.findById(1L).get();

        assertNotNull(result);
    }


    @Test
    void howPairWork(){
        Pair<String,Integer> p1 = Pair.of("Kuba", 15);
        Pair<String,Integer> p2 = Pair.of("Adam", 23);

        List<Pair<String,Integer>> lista = List.of(p1,p2);

        for(var x : lista){
            System.out.println(x.getFirst());
            System.out.println(x.getSecond());
        }

    }
}
