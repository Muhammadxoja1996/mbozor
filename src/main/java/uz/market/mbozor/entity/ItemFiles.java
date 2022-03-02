package uz.market.mbozor.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

/**
 * Author: Muhammadxo'ja
 * Date: 02.03.2022
 * Time: 21:40
 */
@Entity
@Data
@Table(name = "item_files")
@NoArgsConstructor
@AllArgsConstructor
public class ItemFiles {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "item_files_sec")
    @SequenceGenerator(name = "item_files_sec", sequenceName = "item_files_sec", allocationSize = 1)
    private Long id;

    @Column(name = "file_name")
    private String fileName;

    @Column(name = "file_type")
    private String fileType;

    @Column(name = "file_format")
    private String fileFormat;

    @Column(name = "file_size")
    private Long fileSize;
}
