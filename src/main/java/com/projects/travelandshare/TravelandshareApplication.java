package com.projects.travelandshare;

import com.projects.travelandshare.model.entity.Place;
import com.projects.travelandshare.repository.PlaceRepository;
import com.projects.travelandshare.repository.UserRepository;
import com.projects.travelandshare.service.PlaceService;
import com.projects.travelandshare.util.Counties;
import com.projects.travelandshare.util.Types;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import java.util.List;
import java.util.Optional;

@SpringBootApplication
public class TravelandshareApplication {

    public static void main(String[] args) {
        SpringApplication.run(TravelandshareApplication.class, args);

    }
//    @Bean
//    public CommandLineRunner demo (PlaceService placeService){
//        return (args)->{
//            placeService.registerPlace(new Place ("CrazyClimbing", Counties.AlpesMaritimes_06, Types.lowMountain,
//                    0.5454, 4.89595, "abeautifulphto.jpeg" ));
//            placeService.registerPlace(new Place ("MonsterFood", Counties.AlpesMaritimes_06, Types.lowMountain,
//                    0.5454, 4.89595, "abeautifulphto.jpeg" ));
//            placeService.registerPlace(new Place ("CrazyClimbing", Counties.AlpesMaritimes_06, Types.lowMountain,
//                    0.5454, 4.89595, "abeautifulphto.jpeg" ));
//
//
//        };
//    }

}
