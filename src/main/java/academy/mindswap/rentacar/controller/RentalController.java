package academy.mindswap.rentacar.controller;


import academy.mindswap.rentacar.dto.RentalsDto;
import academy.mindswap.rentacar.service.RentalsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/rental")
public class RentalController {

    private RentalsService rentalsService;

    @Autowired
    public RentalController (RentalsService rentalsService){
        this.rentalsService = rentalsService;
    }

    @PostMapping
    public ResponseEntity<RentalsDto> createRental(@RequestBody RentalsDto rentalDto, BindingResult bindingResult) {

        if(bindingResult.hasErrors()) {

            List<FieldError> errors = bindingResult.getFieldErrors();

            for (FieldError error : errors) {
                System.out.println(error.getObjectName() + " - " + error.getDefaultMessage());
            }
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
        RentalsDto savedRentalDto = rentalsService.createRental(rentalDto);

        return new ResponseEntity<>(savedRentalDto, HttpStatus.OK);
    }

    @GetMapping("")
    public ResponseEntity<List<RentalsDto>> getRentals() {

        return new ResponseEntity<>(rentalsService.getAllRentals(), HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<RentalsDto> getRentalById(@PathVariable Long id) {
        return new ResponseEntity<>(rentalsService.getRentalById(id), HttpStatus.OK);
    }

    @PutMapping("/{id}")
    public ResponseEntity<RentalsDto> updateRental(@PathVariable Long id,  @RequestBody RentalsDto rentalDto) {
        rentalDto.setId(id);
        RentalsDto rentalDto1 =  rentalsService.updateRental(rentalDto);
        return new ResponseEntity<>(rentalDto1, HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteRental(@PathVariable Long id) {
        rentalsService.deleteRental(id);
        return new ResponseEntity<>("Rental has been deleted",HttpStatus.OK);
    }

}
