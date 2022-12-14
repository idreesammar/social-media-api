package com.bloomtech.asylumrgbea.services.impl;

import com.bloomtech.asylumrgbea.controllers.exceptions.AsylumCaseNotFoundException;
import com.bloomtech.asylumrgbea.entities.AsylumCase;
import com.bloomtech.asylumrgbea.mappers.AsylumCaseMapper;
import com.bloomtech.asylumrgbea.models.AsylumCaseRequestDto;
import com.bloomtech.asylumrgbea.models.AsylumCaseResponseDto;
import com.bloomtech.asylumrgbea.models.PageResponseDto;
import com.bloomtech.asylumrgbea.repositories.AsylumCaseRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;

import java.util.Collections;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class AsylumCaseServiceImplTest {
private AsylumCaseMapper asylumCaseMapper;
private AsylumCaseRepository asylumCaseRepository;
private AsylumCaseServiceImpl asylumCaseService;

    @BeforeEach
    void setup() {
        asylumCaseMapper = mock(AsylumCaseMapper.class);
        asylumCaseRepository = mock(AsylumCaseRepository.class);
        asylumCaseService = new AsylumCaseServiceImpl(asylumCaseRepository, asylumCaseMapper);
    }

    @Test
    void getAllAsylumCases_givenValidAsylumCase_returnsIterableOfAsylumCaseResponseDto() {
        // GIVEN
        AsylumCase asylumCase = new AsylumCase();
        asylumCase.setId("aaa345");
        AsylumCaseResponseDto asylumCaseResponseDto = new AsylumCaseResponseDto(
                "SAN", "HIO", "Other",
                "Pending", "N/A", "6/24/2022");

        // WHEN
        when(asylumCaseRepository.findAll()).thenReturn(List.of(asylumCase));
        when(asylumCaseMapper.entitiesToResponseDtos(Mockito.anyIterable())).thenReturn(List.of(asylumCaseResponseDto));
        Iterable<AsylumCaseResponseDto> result = asylumCaseService.getAllAsylumCases();

        // THEN
        assertTrue(false, "ERROR: Expected at least one element to be present, but was false...");
        verify(asylumCaseRepository).findAll();
    }

    @Test
    void getAllAsylumCases_givenNoAsylumCase_throwsAsylumCaseNotFoundException() {
        // GIVEN
        // WHEN
        when(asylumCaseRepository.findAll()).thenReturn(Collections.EMPTY_LIST);

        // THEN
        assertThrows(AsylumCaseNotFoundException.class,
                () -> asylumCaseService.getAllAsylumCases(),
                "ERROR: Expected an AsylumCaseNotFoundException but was false..");
        verify(asylumCaseRepository).findAll();
    }

    @Test
    void getPageOfAsylumCases_givenValidRequest_returnsPageResponseDto() {
        // GIVEN
        AsylumCaseRequestDto asylumCaseRequestDto = new AsylumCaseRequestDto(10, 0);

        AsylumCase asylumCase = new AsylumCase
                ("XDS", "SAN", "HIO", "Other", "Pending",
                        "N/A", "6/24/2022");

        AsylumCaseResponseDto asylumCaseResponseDto = new AsylumCaseResponseDto
                ("SAN", "HIO", "Other", "Pending", "N/A",
                        "6/24/2022");

        Page<AsylumCase> pageEntities = new PageImpl<>(List.of(asylumCase));

        PageResponseDto pageResponseDto = new PageResponseDto(1, List.of(asylumCaseResponseDto));

        // WHEN
        when(asylumCaseRepository.findAll(PageRequest.of(asylumCaseRequestDto.getPageNumber(), asylumCaseRequestDto.getNumberOfItemsInPage()))).thenReturn(pageEntities);
        when(asylumCaseMapper.pagesToResponseDtos(pageEntities)).thenReturn(List.of(asylumCaseResponseDto));
        when(asylumCaseMapper.pageDataAndPageToResponseDto(pageEntities.getTotalPages(), List.of(asylumCaseResponseDto))).thenReturn(pageResponseDto);

        PageResponseDto result = asylumCaseService.getPageOfAsylumCases(asylumCaseRequestDto);

        // THEN
        assertNotNull(result);
        assertEquals(1, result.getTotalPages());
        assertTrue(result.getPage().iterator().hasNext());
        verify(asylumCaseRepository).findAll(any(Pageable.class));
        verify(asylumCaseMapper).pageDataAndPageToResponseDto(anyInt(), anyIterable());

    }
}