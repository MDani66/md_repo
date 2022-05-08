package hu.progmasters.airport.service;

import hu.progmasters.airport.domain.Airplane;
import hu.progmasters.airport.dto.AirplaneDto;
import hu.progmasters.airport.dto.command.AirplaneCreateAndUpdateCommand;
import hu.progmasters.airport.exceptionhandling.AirplaneNotFoundException;
import hu.progmasters.airport.repository.AirplaneRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class AirplaneService {

    private final AirplaneRepository airplaneRepository;
    private final ModelMapper modelMapper;

    @Autowired
    public AirplaneService(AirplaneRepository airplaneRepository, ModelMapper modelMapper) {
        this.airplaneRepository = airplaneRepository;
        this.modelMapper = modelMapper;
    }


    public AirplaneDto saveAirplane(AirplaneCreateAndUpdateCommand command) {
        Airplane airplaneToSave = modelMapper.map(command, Airplane.class);
        Airplane airplaneSaved = airplaneRepository.save(airplaneToSave);
        return modelMapper.map(airplaneSaved, AirplaneDto.class);
    }

    public List<AirplaneDto> listAllAirplanes(Optional<String> prefix) {
        return airplaneRepository.listAll().stream()
                .filter(airplane -> !airplane.isDeleted())
                .filter(airplane -> prefix.isEmpty() ||
                        airplane.getIdentifier().toLowerCase().startsWith(prefix.get().toLowerCase()))
                .map(airplane -> modelMapper.map(airplane, AirplaneDto.class))
                .collect(Collectors.toList());
    }

    public AirplaneDto findAirplaneById(int internalId) {
        Optional<Airplane> airplaneToFind = airplaneRepository.findById(internalId);
        if (airplaneToFind.isEmpty() || airplaneToFind.get().isDeleted()) {
            throw new AirplaneNotFoundException(internalId);
        } else {
            return modelMapper.map(airplaneToFind.get(), AirplaneDto.class);
        }
    }

    public AirplaneDto modifyAirplaneData(int internalId, AirplaneCreateAndUpdateCommand command) {
        Optional<Airplane> airplaneToFind = airplaneRepository.findById(internalId);
        if (airplaneToFind.isEmpty() || airplaneToFind.get().isDeleted()) {
            throw new AirplaneNotFoundException(internalId);
        } else {
            Airplane airplaneModified = airplaneToFind.get();
            airplaneModified.setIdentifier(command.getIdentifier())
                    .setProducer(command.getProducer())
                    .setNumberOfSeats(command.getNumberOfSeats())
                    .setType(command.getType());
            return modelMapper.map(airplaneModified, AirplaneDto.class);
        }
    }

    public void deleteAirplane(int internalId) {
        Optional<Airplane> airplaneToDelete = airplaneRepository.findById(internalId);
        if (airplaneToDelete.isEmpty() || airplaneToDelete.get().isDeleted()) {
            throw new AirplaneNotFoundException(internalId);
        } else {
            airplaneToDelete.get().setDeleted(true);
        }
    }
}
