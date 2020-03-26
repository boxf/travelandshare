package com.projects.travelandshare.restcontroller;

import com.projects.travelandshare.model.dto.CreatePlaceDTO;
import com.projects.travelandshare.model.entity.Place;
import com.projects.travelandshare.service.PlaceDTOService;
import com.projects.travelandshare.service.PlaceService;
import com.projects.travelandshare.util.Counties;
import com.projects.travelandshare.util.Types;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.platform.runner.JUnitPlatform;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.ResponseEntity;
import org.springframework.mock.web.MockHttpServletRequest;
import org.springframework.mock.web.MockMultipartFile;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;
import org.springframework.web.multipart.MultipartFile;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
@RunWith(JUnitPlatform.class)
class PlaceRestControllerTest {

    @InjectMocks
    PlaceRestController placeRestController;
    @Mock
    PlaceService placeService;
    @Mock
    PlaceDTOService placeDTOService;

    @Test
    void getPlaceByCounty() {
    }

    @Test
    void getAllPlace() {
        // given
        List<Place> expectedList = new ArrayList<>();
        Place place = new Place("Calanques", Counties.BOUCHESDURHÔNE_13, Types.MEDIUMMOUNTAIN,3.50, 4.23);
        Place place1 = new Place("Paris", Counties.PARIS_75, Types.MUSEUM, 0.65, 5.36);
        Place place2 = new Place("Lyon", Counties.RHÔNE_69, Types.ARTGALLERY, 2.35, 5.63);
        expectedList.add(place);
        expectedList.add(place1);
        expectedList.add(place2);

        when(placeService.findAllPlace()).thenReturn(expectedList);

        // when
        List <Place> testList = placeRestController.getAllPlace();

        // then
        assertThat(testList.size()).isEqualTo(3);

        assertThat(testList.get(0).getName())
                .isEqualTo(place.getName());

        assertThat(testList.get(1).getName())
                .isEqualTo(place1.getName());

        assertThat(testList.get(2).getName())
                .isEqualTo(place2.getName());
    }

    @Test
    void addNewPlace() throws IOException {
        //Given
        MockHttpServletRequest request = new MockHttpServletRequest();
        RequestContextHolder.setRequestAttributes(new ServletRequestAttributes(request));
        Place place = new Place("Calanques", Counties.BOUCHESDURHÔNE_13, Types.MEDIUMMOUNTAIN,3.50, 4.23);
        when(placeService.registerPlace(any(Place.class))).thenReturn(place);

        //When
        final InputStream inputStream = Thread.currentThread().getContextClassLoader().getResourceAsStream("baceda108b93eaa7a6984dd1b68596f4.jpg");
        final MultipartFile multipartFile = new MockMultipartFile("file", "baceda108b93eaa7a6984dd1b68596f4.jpg", "image/jpg", inputStream);
        CreatePlaceDTO createPlace = new CreatePlaceDTO("Calanques", Counties.BOUCHESDURHÔNE_13, Types.MEDIUMMOUNTAIN,3.50, 4.23, multipartFile);
        ResponseEntity<Object> responseEntity = placeRestController.addNewPlace(createPlace);

        //Then
        assertThat(responseEntity.getStatusCodeValue()).isEqualTo(201);
        assertThat(responseEntity.getHeaders().getLocation().getPath()).isEqualTo("/1");
    }
}