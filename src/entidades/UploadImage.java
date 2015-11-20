package entidades;

import javax.persistence.*;

/**
 * Created by wheezy on 20/11/15.
 */

@Entity
@Table(name = "upload")
public class UploadImage {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;


    private String fileName;

    private String contentType;

    @Lob
    private byte[] content;


}
