package hu.progmasters.airport.controller;

import hu.progmasters.airport.dto.AirplaneDto;
import hu.progmasters.airport.dto.command.AirplaneCreateAndUpdateCommand;
import hu.progmasters.airport.service.AirplaneService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/airportapp/airplanes")
public class AirplaneController {

    private final AirplaneService airplaneService;
    private static final Logger LOGGER = LoggerFactory.getLogger(AirplaneController.class);

    @Autowired
    public AirplaneController(AirplaneService airplaneService) {
        this.airplaneService = airplaneService;
    }

    @PostMapping
    public ResponseEntity<AirplaneDto> createAirplane(@Valid @RequestBody AirplaneCreateAndUpdateCommand command) {
        LOGGER.info("Http request, POST /api/airportapp/airplanes body: " + command.toString());
        AirplaneDto airplaneDto = airplaneService.saveAirplane(command);
        LOGGER.info("Airplane created: " + airplaneDto.toString());
        return new ResponseEntity<>(airplaneDto, HttpStatus.CREATED);
    }

    @GetMapping
    public ResponseEntity<List<AirplaneDto>> listAllAirplanes(Optional<String> prefix) {
        prefix.ifPresent(s -> LOGGER.info("Http request, GET /api/airportapp/airplanes?prefix=" + s));
        LOGGER.info("Http request, GET /api/airportapp/airplanes");
        List<AirplaneDto> airplaneDtos = airplaneService.listAllAirplanes(prefix);
        LOGGER.info("List all airplanes: " + airplaneDtos.toString());
        return new ResponseEntity<>(airplaneDtos, HttpStatus.OK);
    }

    @GetMapping("/{internalid}")
    public ResponseEntity<AirplaneDto> findAirplaneById(@PathVariable("internalid") int internalId) {
        LOGGER.info("Http request, GET /api/airportapp/airplanes/" + internalId);
        AirplaneDto airplaneDto = airplaneService.findAirplaneById(internalId);
        LOGGER.info("Airplane found: " + airplaneDto.toString());
        return new ResponseEntity<>(airplaneDto, HttpStatus.OK);
    }

    @PutMapping("/{internalid}")
    public ResponseEntity<AirplaneDto> modifyAirplane(@PathVariable("internalid") int internalId,
                                                      @RequestBody @Valid AirplaneCreateAndUpdateCommand command) {
        LOGGER.info("Http request, PUT /api/airportapp/airplanes/" + internalId + " body: " + command.toString());
        AirplaneDto airplaneModified = airplaneService.modifyAirplaneData(internalId, command);
        LOGGER.info("Airplane modified: " + airplaneModified.toString());
        return new ResponseEntity<>(airplaneModified, HttpStatus.OK);
    }

    @DeleteMapping("/{internalid}")
    public ResponseEntity<Void> deleteAirplane(@PathVariable("internalid") int internalId) {
        LOGGER.info("Http request, DELETE /api/airportapp/airplanes/" + internalId);
        airplaneService.deleteAirplane(internalId);
        LOGGER.info("Airplane successfully deleted");
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

}
