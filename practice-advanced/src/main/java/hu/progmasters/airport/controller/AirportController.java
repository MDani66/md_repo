package hu.progmasters.airport.controller;

import hu.progmasters.airport.dto.AirportDto;
import hu.progmasters.airport.dto.command.AirportCreateAndUpdateCommand;
import hu.progmasters.airport.service.AirportService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/api/airportapp/airports")
public class AirportController {

    private final AirportService airportService;
    private static final Logger LOGGER = LoggerFactory.getLogger(AirplaneController.class);

    @Autowired
    public AirportController(AirportService airportService) {
        this.airportService = airportService;
    }

    @PostMapping
    public ResponseEntity<AirportDto> saveAirport(@RequestBody @Valid AirportCreateAndUpdateCommand command) {
        LOGGER.info("Http request, POST /api/airportapp/airports, body: " + command.toString());
        AirportDto airportSaved = airportService.createAirport(command);
        LOGGER.info("Airport is saved: " + airportSaved.toString());
        return new ResponseEntity<>(airportSaved, HttpStatus.CREATED);
    }

    @GetMapping("/{internalid}")
    public ResponseEntity<AirportDto> findAirportByInternalId(@PathVariable("internalid") int internalId) {
        LOGGER.info("Http request, GET /api/airportapp/airports/" + internalId);
        AirportDto airportfound = airportService.findAirportByInternalId(internalId);
        LOGGER.info("Airport found: " + airportfound.toString());
        return new ResponseEntity<>(airportfound, HttpStatus.OK);
    }

    @GetMapping
    public ResponseEntity<List<AirportDto>> listAllAirports() {
        LOGGER.info("Http request, GET /api/airportapp/airports");
        List<AirportDto> airportsToList = airportService.listAllAirports();
        LOGGER.info("List all airports: " + airportsToList.toString());
        return new ResponseEntity<>(airportsToList, HttpStatus.OK);
    }

    @PutMapping("/{internalid}")
    public ResponseEntity<AirportDto> modifyAirport(@PathVariable("internalid") int internalId,
                                                    @RequestBody @Valid AirportCreateAndUpdateCommand command) {
        LOGGER.info("Http request, PUT /api/airportapp/airports/" + internalId + " body: " + command.toString());
        AirportDto airportmodified = airportService.modifyAirport(internalId, command);
        LOGGER.info("Airport modified: " + airportmodified.toString());
        return new ResponseEntity<>(airportmodified, HttpStatus.OK);
    }

    @DeleteMapping("/{internalid}")
    public ResponseEntity<Void> deleteAirport(@PathVariable("internalid") int internalId) {
        LOGGER.info("Http request, DELETE /api/airportapp/airports/" + internalId);
        airportService.deleteAirport(internalId);
        LOGGER.info("Airport successfully deleted");
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }


}
