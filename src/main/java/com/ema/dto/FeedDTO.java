package com.ema.dto;

import lombok.*;

import javax.validation.constraints.NotNull;


@NoArgsConstructor @AllArgsConstructor @Setter @Getter @Builder
public class FeedDTO {
    @NotNull
    private String title;

    @NotNull
    private String content;

    private String imageUrl;
}
