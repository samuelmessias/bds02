package com.devsuperior.bds02.services;

import javax.persistence.EntityNotFoundException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.devsuperior.bds02.dto.EventDTO;
import com.devsuperior.bds02.entities.City;
import com.devsuperior.bds02.entities.Event;
import com.devsuperior.bds02.repositories.EventRepository;
import com.devsuperior.bds02.services.exceptions.ResourceNotFoundException;

import javassist.NotFoundException;


@Service
public class EventService {
	
	@Autowired
	private EventRepository repository;
	
	@Transactional
	public EventDTO update(Long id, EventDTO dto) throws NotFoundException {
		try {
			Event entity = repository.getOne(id);		
			entity.setName(dto.getName());
			entity.setUrl(dto.getUrl());
			entity.setDate(dto.getDate());
			entity.setCity(new City(dto.getCityId(), ""));
			entity = repository.save(entity);
			return new EventDTO(entity);
		
		}catch(EntityNotFoundException e) {
			throw new ResourceNotFoundException("Id not found " + id);
		}		
		
	}
}
