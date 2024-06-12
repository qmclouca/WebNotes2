package com.qmclouca.base.Dtos;

import lombok.Data;
import lombok.EqualsAndHashCode;

@EqualsAndHashCode(callSuper = true)
@Data
public class NoteDto extends BaseEntityDto {
        private String title;
        private String content;
        private Long clientId;
}
