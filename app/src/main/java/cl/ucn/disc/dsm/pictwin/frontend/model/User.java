package cl.ucn.disc.dsm.pictwin.frontend.model;

import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * @author Benjamin Millas
 * class User.
 */
@Builder
@NoArgsConstructor
@AllArgsConstructor
public final class User {
    /**
     * The Id.
     */
    @Getter
    private Long id;
    /**
     * The Email.
     */
    @Getter
    @NotNull
    private String email;
    /**
     * The Password.
     */
    @Getter
    @Setter
    private String password;
    /**
     * Strikes
     */
    @Getter
    private Integer strikes;

    /**
     * State
     */
    @Getter
    @Setter
    private State state;

    /**
     * The Twins
     */
    @Getter
    private List<Twin> twins ;
}
