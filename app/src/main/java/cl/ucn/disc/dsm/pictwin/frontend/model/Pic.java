package cl.ucn.disc.dsm.pictwin.frontend.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * Pic.
 *
 * @author Benjamin Millas
 */
@Builder
@NoArgsConstructor
@AllArgsConstructor
public final class Pic {

    /**
     * The Id.
     */
    @Getter
    private Long id;

    /**
     * The Instante when the Pic was saved.
     */
    @Getter
    private String timestamp;

    /**
     * The Dislikes
     * **/
    @Getter
    private Integer dislikes;

    /**
     * The Latitude.
     */
    @Getter
    private Double latitude;

    /**
     * The Longitude.
     */
    @Getter
    private Double longitude;

    /**
     * The Error.
     */
    @Getter
    private Double error;

    /**
     * The Views.
     */
    @Getter
    private Integer views;
    /**
     * The Name.
     */
    @Getter
    private String name;

    /**
     * The Picture.
     */
    @Getter
    private byte[] picture;

    /**
     * The Owner.
     */
    @Getter
    @Setter
    private User owner;


}
