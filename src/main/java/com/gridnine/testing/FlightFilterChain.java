package com.gridnine.testing;

import java.util.ArrayList;
import java.util.List;

/**
 * Класс, представляющий цепочку фильтров для обработки списка авиаперелетов.
 * <p>
 * Этот класс позволяет добавлять несколько фильтров и последовательно применять их
 * к списку авиаперелетов, возвращая отфильтрованный список.
 * </p>
 */
public class FlightFilterChain {
    private final List<FlightFilter> filters = new ArrayList<>();

    /**
     * Добавляет фильтр в цепочку фильтров.
     *
     * @param filter фильтр, который будет добавлен в цепочку
     */
    void addFilter(FlightFilter filter) {
        filters.add(filter);
    }

    /**
     * Применяет все добавленные фильтры к списку авиаперелетов.
     *
     * @param flights список авиаперелетов для фильтрации
     * @return новый список авиаперелетов, прошедших все фильтры
     */
    List<Flight> applyFilters(List<Flight> flights) {
        List<Flight> filteredFlights = flights;
        for (FlightFilter filter : filters) {
            filteredFlights = filter.filter(filteredFlights);
        }
        return filteredFlights;
    }
}
