package LiterAlura.dto;

import com.fasterxml.jackson.annotation.JsonAlias;

import java.time.LocalDate;

public record AutorDto(
        @JsonAlias("name") String nome,
        @JsonAlias("birth_year)")LocalDate aniversario,
         @JsonAlias("death_year") LocalDate anoDeMorte){
}
