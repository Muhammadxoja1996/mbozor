package uz.market.mbozor.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Author: Muhammadxo'ja
 * Date: 19.02.2022
 * Time: 21:40
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class PageableDto {
    private Long totalElements;
    private Integer totalPages;
    private Integer size;
    private Integer page;
}
