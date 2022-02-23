package uz.market.mbozor.dto;

import lombok.Data;

/**
 * Author: Muhammadxo'ja
 * Date: 19.02.2022
 * Time: 21:39
 */
@Data
public class ContentPageableDto {
    private Object content;
    private PageableDto pageable;
}
