package com.xcell.GTManager.dto;

final class DtoMapper {
    private DtoMapper(){}

    static void apply(Runnable... setters){
        for (Runnable setter : setters) setter.run();
    }
}
