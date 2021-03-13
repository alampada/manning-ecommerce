package com.ala.manningecommerce.service;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

import com.ala.manningecommerce.controller.resources.Pastry;
import com.ala.manningecommerce.controller.resources.mappers.PastryMapper;
import com.ala.manningecommerce.repository.PastryEntityRepository;
import lombok.AllArgsConstructor;

import org.springframework.stereotype.Service;

@AllArgsConstructor
@Service
public class CakeService {

	private final PastryEntityRepository pastryEntityRepository;

	private final PastryMapper pastryMapper;

	public List<Pastry> getPastries() {
		return StreamSupport.stream(pastryEntityRepository.findAll().spliterator(), false)
				.map(pastryMapper::map)
				.collect(Collectors.toList());
	}
}
