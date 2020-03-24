package com.projects.travelandshare.restcontroller;

import com.projects.travelandshare.model.entity.Place;
import com.projects.travelandshare.service.PlaceService;
import com.projects.travelandshare.util.Counties;
import com.projects.travelandshare.util.Types;
import org.junit.Before;
import org.junit.Test;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.platform.runner.JUnitPlatform;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.ResponseEntity;
import org.springframework.mock.web.MockHttpServletRequest;
import org.springframework.mock.web.MockHttpServletResponse;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultMatcher;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;
import org.springframework.web.servlet.HandlerAdapter;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.StreamSupport;

import static org.hamcrest.Matchers.any;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@ExtendWith(MockitoExtension.class)
@RunWith(JUnitPlatform.class)
class PlaceRestControllerTest {

    @Autowired
    private MockMvc mockMvc;

   @InjectMocks
    PlaceRestController placeController;

    @Mock
    PlaceService placeService;

    @Before
    public void setUp() throws Exception {
        mockMvc = MockMvcBuilders.standaloneSetup(placeController).build();
    }

    //    @Test
//    void getPlaceByCounty() {
//    }

//    @Test
//    void getAllPlaceShouldReturnAllPlaces() {
//        List <Place> expectedList = new ArrayList<>();
//        when(placeService.findAllPlace()).thenReturn(expectedList);
//        this.mockMvc.perform(("/places")).andDo(print()).andExpect(status().isOk())
//                .andExpect(content().);
//        Place place = new Place("Calanques", Counties.AIN_01, Types.LOWMOUNTAIN,3.50, 4.23);
//        Place place1 = new Place("Paris", Counties.AIN_01, Types.MUSEUM, 0.65, 5.36);
//        Place place2 = new Place("Lyon", Counties.AUBE_10, Types.BEACH, 2.35, 5.63);
//        List<Place> expectedList = Arrays.asList(place,place1, place2);
//
//        // when
//        when(placeService.findAllPlace()).thenReturn(expectedList);
//        List<Place> placeList = placeController.getAllPlace();
//
//        // then
//        assertThat(placeList.size()).isEqualTo(3);
//
//        assertThat(placeList.get(0).getName())
//                .isEqualTo(place.getName());
//
//        assertThat(placeList.get(1).getName())
//                .isEqualTo(place1.getName());
//        assertThat(placeList.get(2).getName())
//                .isEqualTo(place1.getName());
    }
//
//
//    @Test
//    void addNewPlace() throws Exception {
//
//            MockHttpServletRequest request = new MockHttpServletRequest();
//            RequestContextHolder.setRequestAttributes(new ServletRequestAttributes(request));
//
//            when(placeService.registerPlace(any(Place.class))).thenReturn(true);
//
//            Place place = new Place("Grenoble", Counties.ALPESDEHAUTEPROVENCE_04, Types.LOWMOUNTAIN, 3.2, 25.6);
//            ResponseEntity<Object> responseEntity = placeController.addNewPlace(place);
//
//            assertThat(responseEntity.getStatusCodeValue()).isEqualTo(201);
//            assertThat(responseEntity.getHeaders().getLocation().getPath()).isEqualTo("/1");
//        }
//    }
//}
//}