package cl.ucn.disc.dsm.pictwin.frontend.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;


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
     75 * The Owner
     76 */
    @Getter
    private User owner;

}
