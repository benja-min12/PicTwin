package cl.ucn.disc.dsm.pictwin.frontend.model;

import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;


@Builder
@NoArgsConstructor
@AllArgsConstructor
public final class User {

    @Getter
    private Long id;

    @Getter
    @NotNull
    private String email;

    @Getter
    @Setter
    private String password;

    @Getter
    private Integer strikes;


    @Getter
    @Setter
    private State state;


    @Getter
    private List<Twin> twins ;


}
