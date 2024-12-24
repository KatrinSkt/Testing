package com.gridnine.testing;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Фильтр для удаления перелетов с вылетом до текущего момента времени.
 * Этот фильтр проверяет дату вылета первого сегмента каждого перелета
 * и отфильтровывает те перелеты, где вылет уже произошел.
 */
public class DepartureBeforeNowFilter implements FlightFilter {

    /**
     * Фильтрует список перелетов, удаляя те, которые имеют вылет до текущего момента времени.
     *
     * @param flights список перелетов для фильтрации
     * @return новый список перелетов, содержащий только те, которые имеют вылет в будущем
     */
    @Override
    public List<Flight> filter(List<Flight> flights) {
        return flights.stream()
                .filter(flight -> flight.getSegments().get(0).getDepartureDate().isAfter(LocalDateTime.now()))
                .collect(Collectors.toList());
    }
}
