package com.example.demo;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;


import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.junit.jupiter.api.TestInstance.Lifecycle;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.test.context.junit4.SpringRunner;

import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase.Replace;

import com.example.demo.entities.*;

@DataJpaTest
@AutoConfigureTestDatabase(replace=Replace.NONE)
@TestInstance(Lifecycle.PER_CLASS)
class EntityUnitTest {

	@Autowired
	private TestEntityManager entityManager;

	private Doctor d1;

	private Patient p1;

    private Room r1;

    private Appointment a1;
    private Appointment a2;
    private Appointment a3;

    @Test
    void givenAPerson_whenChangingEmail_thenReturnNewEmail() {
        Person person = new Person ("Perla", "Amalia", 24, "p.amalia@hospital.accwe");
        person.setEmail("amalia@hospital.accwe");

        assertEquals("amalia@hospital.accwe", person.getEmail());
    }

    @Test
    void givenAPerson_whenChangingAge_thenReturnNewAge() {
        Person person = new Person ("Perla", "Amalia", 24, "p.amalia@hospital.accwe");
        person.setAge(25);

        assertEquals(25, person.getAge());
    }

    @Test
    void givenAPerson_whenChangingFirstName_thenReturnNewFirstName() {
        Person person = new Person ("Perla", "Amalia", 24, "p.amalia@hospital.accwe");
        person.setFirstName("Carmen");

        assertEquals("Carmen", person.getFirstName());
    }

    @Test
    void givenAPerson_whenChangingLastName_thenReturnNewLastName() {
        Person person = new Person ("Perla", "Amalia", 24, "p.amalia@hospital.accwe");
        person.setLastName("Patricia");

        assertEquals("Patricia", person.getLastName());
    }

    @Test
    void givenADoctor_whenSettingId_thenReturnNewId() {
        Doctor doctor = new Doctor ("Perla", "Amalia", 24, "p.amalia@hospital.accwe");
        doctor.setId(1);

        assertEquals(1, doctor.getId());
    }

    @Test
    void givenAPatient_whenSettingId_thenReturnNewId() {
        Patient patient = new Patient ("Perla", "Amalia", 24, "p.amalia@hospital.accwe");
        patient.setId(1);

        assertEquals(1, patient.getId());
    }

    @Test
    void givenARoom_whenGettingRoomName_thenReturnRoomName() {
        Room room = new Room ("Dermatology");

        assertEquals("Dermatology", room.getRoomName());
    }

    @Test
    void givenTwoAppointments_whenOverlap_thenReturnFalse() {

    }

    /** TODO
     * Implement tests for each Entity class: Doctor, Patient, Room and Appointment.
     * Make sure you are as exhaustive as possible. Coverage is checked ;)
     */
}
