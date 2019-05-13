package com.gs.ilp.rest.webservices.restfulwebservicesfirst;


import org.springframework.hateoas.Resource;
import org.springframework.hateoas.mvc.ControllerLinkBuilder;
import org.springframework.http.converter.json.MappingJacksonValue;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.fasterxml.jackson.databind.ser.FilterProvider;
import com.fasterxml.jackson.databind.ser.impl.SimpleBeanPropertyFilter;
import com.fasterxml.jackson.databind.ser.impl.SimpleFilterProvider;

@RestController

public class MyFirstController {

		@GetMapping("/filter1")
	public MappingJacksonValue getFilterData1() {

		Fields ff = new Fields("11", "22", "33");

		SimpleBeanPropertyFilter filter = SimpleBeanPropertyFilter.filterOutAllExcept("field1", "field2");

		FilterProvider filters = new SimpleFilterProvider().addFilter("field-filter", filter);

		MappingJacksonValue mapping = new MappingJacksonValue(ff);

		mapping.setFilters(filters);

		return mapping;
	}

	@GetMapping("/filter2")
	public MappingJacksonValue getFilterData2() {

		Fields ff = new Fields("11", "22", "33");

		SimpleBeanPropertyFilter filter = SimpleBeanPropertyFilter.filterOutAllExcept("field1", "field3");

		FilterProvider filters = new SimpleFilterProvider().addFilter("field-filter", filter);

		MappingJacksonValue mapping = new MappingJacksonValue(ff);

		mapping.setFilters(filters);

		return mapping;
	}

	@GetMapping("/filter3")
	public MappingJacksonValue getFilterData3() {

		Fields ff = new Fields("11", "22", "33");

		ControllerLinkBuilder linkTO = ControllerLinkBuilder
				.linkTo(ControllerLinkBuilder.methodOn(this.getClass()).getFilterData2());

		Resource<Fields> resource = new Resource<Fields>(ff);

		resource.add(linkTO.withRel("filter2"));

		SimpleBeanPropertyFilter filter = SimpleBeanPropertyFilter.filterOutAllExcept("field1", "field3");

		FilterProvider filters = new SimpleFilterProvider().addFilter("field-filter", filter);

		MappingJacksonValue mapping = new MappingJacksonValue(resource);

		mapping.setFilters(filters);

		return mapping;
	}

}
