package cl.javadevs.springsecurityjwt.services;

import cl.javadevs.springsecurityjwt.dtos.HotelDTO;

public interface HotelsService {
    //rf1
    Boolean add(HotelDTO hotelData);

    //rf3
    Boolean delete(Integer id);
}
