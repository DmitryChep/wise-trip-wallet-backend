package com.wisetripwallet.model;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum TagColour {
    RED("red", "#FF0000" ),
    YELLOW("yellow", "#FFFF00" ),
    ORANGE( "orange", " #FFA500" ),
    BLUE("blue", "#0000FF" ),
    GREEN("green", "#008000" ),
    PURPLE( "purple", "#800080" );

    private final String name;
    private final String hexCode;

}

