package cl.ucn.disc.dsm.pictwin.frontend.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * class Twin.
 *
 * @author Benjamin Millas
 */
@Builder
@NoArgsConstructor
@AllArgsConstructor
public final class Twin {
    /**
     * The Id.
     */
    @Getter
    private Long id;
    /**
     * The dislike
     */
    @Getter
    @Setter
    private Boolean dislike ;

    /**
     * The Pic
     */
    @Getter
    private Pic my;

    /**
     * The Pic
     */
    @Getter
    private Pic yours;

    /**
    * The Owner
     * */
    @Getter
    private User owner;

}
