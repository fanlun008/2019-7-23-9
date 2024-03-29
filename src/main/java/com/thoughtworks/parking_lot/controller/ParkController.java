package com.thoughtworks.parking_lot.controller;

import com.thoughtworks.parking_lot.entity.Parkinglot;
import com.thoughtworks.parking_lot.service.ParkingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/parkinglots")
public class ParkController {

    @Autowired
    private ParkingService parkingService;

    @PostMapping("")
    public Parkinglot addParkinglot(@RequestBody Parkinglot parkinglot) {
        Parkinglot parkinglot1 = parkingService.saveParkinglot(parkinglot);
        System.out.println("addParkinglot...");
        return parkinglot1;
    }

    @DeleteMapping("/{id}")
    public String deleteParkinglot(@PathVariable(value = "id") String id) {
        return parkingService.deleteParkinglot(id);
    }

    @GetMapping(params = {"page"})
    public List<Parkinglot> findAll(@RequestParam("page")Integer page){
        List<Parkinglot> all = parkingService.findAll(page-1, 15);
        return all;
    }

    @GetMapping("/{id}")
    public Parkinglot getDetailInfoById(@PathVariable("id")String id) {
        Parkinglot parkinglot = parkingService.findById(id);
        return parkinglot;
    }

    @PutMapping(value = "/{id}", params = {"capacity"})
    public Parkinglot updateCapacity(@PathVariable("id") String id, @RequestParam("capacity")Integer capacity){
        parkingService.updateCapacityById(id, capacity);
        return parkingService.findById(id);
    }

//    @CrossOrigin(value = "http://localhost:8081")
    @GetMapping("/testAxios")
    public Parkinglot test() {
        Parkinglot parkinglot = new Parkinglot();
        parkinglot.setCapacity(10);
        parkinglot.setId("56464");
        parkinglot.setPosition("beijig");
        parkinglot.setName("HHU");
        return parkinglot;
    }
}
