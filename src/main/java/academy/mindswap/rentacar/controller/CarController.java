package academy.mindswap.rentacar.controller;

import academy.mindswap.rentacar.dto.CarCreateDto;
import academy.mindswap.rentacar.dto.CarDto;
import academy.mindswap.rentacar.dto.UserDto;
import academy.mindswap.rentacar.model.Car;
import academy.mindswap.rentacar.service.CarService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/car")
public class CarController {

    private CarService carService;

    @Autowired
    public CarController (CarService carService){
        this.carService = carService;
    }

    @GetMapping("")
    public ResponseEntity<List<CarDto>> getAllCars(){
        List<CarDto> carsDtos = carService.getAllCars();
        return new ResponseEntity<>(carsDtos, HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<CarDto> createCar(@Valid @RequestBody CarCreateDto carCreateDto, BindingResult bindingResult){
        if (bindingResult.hasErrors()){
            List<FieldError> errors = bindingResult.getFieldErrors();

            for (FieldError error : errors){
                System.out.println(error.getObjectName() + "-" + error.getDefaultMessage());
            }
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
        CarDto savedCar = carService.createCar(carCreateDto);
        return new ResponseEntity<>(savedCar, HttpStatus.CREATED);
    }

    @GetMapping("/{id}")
    public ResponseEntity<CarDto> getCarById(@PathVariable Long id){
        CarDto carDto = carService.getCarById(id);
        return new ResponseEntity<>(carDto,HttpStatus.OK);
    }

    @PutMapping("/{id}")
    public ResponseEntity<CarDto> updateCar(@PathVariable Long id, @Valid @RequestBody CarDto carDto, BindingResult bindingResult){
        if (bindingResult.hasErrors()){
            List<FieldError> errors = bindingResult.getFieldErrors();

            for (FieldError error : errors){
                System.out.println(error.getObjectName() + "-" + error.getDefaultMessage());
            }
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
        CarDto updateCar = carService.updateCar(id, carDto);
        return new ResponseEntity<>(updateCar, HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<CarDto> deleteCar(@PathVariable Long id){
         carService.deleteCar(id);
         return new ResponseEntity<>(HttpStatus.OK);

    }

}
