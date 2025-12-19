package com.xcell.GTManager.dto;

/**
 * A helper class, used to map the data from table entities to DTOs.
 */
final class DtoMapper {
    private DtoMapper(){}

    /**
     * Takes a number of setter functions and runs them all.
     * @param setters the setter functions to run
     */
    static void apply(Runnable... setters){
        for (Runnable setter : setters) setter.run();
    }
}
