package com.gridnine.testing;

import java.time.Duration;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Фильтр для удаления авиаперелетов с общим временем на земле, превышающим два часа.
 * <p>
 * Этот фильтр проверяет время на земле между сегментами каждого перелета и отфильтровывает
 * те перелеты, у которых общее время на земле между прилетом одного сегмента и вылетом следующего
 * превышает два часа.
 * </p>
 */
class GroundTimeExceedsTwoHoursFilter implements FlightFilter {

    /**
     * Фильтрует список авиаперелетов, удаляя те, у которых общее время на земле превышает два часа.
     *
     * @param flights список авиаперелетов для фильтрации
     * @return новый список авиаперелетов, содержащий только те, у которых время на земле не превышает два часа
     */
    @Override
    public List<Flight> filter(List<Flight> flights) {
        return flights.stream()
                .filter(flight -> {
                    List<Segment> segments = flight.getSegments();
                    Duration totalGroundTime = Duration.ZERO;

                    // Проходим по всем сегментам, кроме последнего
                    for (int i = 0; i < segments.size() - 1; i++) {
                        // Вычисляем время на земле между прилетом одного сегмента и вылетом следующего
                        totalGroundTime = totalGroundTime.plus(Duration.between(
                                segments.get(i).getArrivalDate(),
                                segments.get(i + 1).getDepartureDate()));
                    }

                    // Возвращаем только те перелеты, у которых время на земле не превышает 2 часов
                    return totalGroundTime.toHours() <= 2;
                })
                .collect(Collectors.toList());
    }
}
