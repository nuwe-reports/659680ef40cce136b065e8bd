package com.example.demo;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;


import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
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

    @BeforeEach
    void initTestMethod(){
        this.d1 = new Doctor ("Perla", "Amalia", 24, "p.amalia@hospital.accwe");
        this.p1 = new Patient("Jose Luis", "Olaya", 37, "j.olaya@email.com");
        this.r1 = new Room ("Dermatology");
    }

    @Test
    void givenADoctor_whenChangingEmail_thenReturnNewEmail() {
        d1.setEmail("amalia@hospital.accwe");

        assertEquals("amalia@hospital.accwe", d1.getEmail());
    }

    @Test
    void givenADoctor_whenChangingAge_thenReturnNewAge() {
        d1.setAge(25);

        assertEquals(25, d1.getAge());
    }

    @Test
    void givenADoctor_whenChangingFirstName_thenReturnNewFirstName() {
        d1.setFirstName("Carmen");

        assertEquals("Carmen", d1.getFirstName());
    }

    @Test
    void givenADoctor_whenChangingLastName_thenReturnNewLastName() {
        d1.setLastName("Patricia");

        assertEquals("Patricia", d1.getLastName());
    }

    @Test
    void givenADoctor_whenSettingId_thenReturnNewId() {
        d1.setId(1);

        assertEquals(1, d1.getId());
    }

    @Test
    void givenAPatient_whenSettingId_thenReturnNewId() {
        p1.setId(1);

        assertEquals(1, p1.getId());
    }

    @Test
    void givenARoom_whenGettingRoomName_thenReturnRoomName() {
        assertEquals("Dermatology", r1.getRoomName());
    }

    @Test
    void givenAnAppointment_whenSettingId_thenReturnNewId() {
        Patient patient = new Patient("Jose Luis", "Olaya", 37, "j.olaya@email.com");
        Doctor doctor = new Doctor ("Perla", "Amalia", 24, "p.amalia@hospital.accwe");
        Room room = new Room("Dermatology");

        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("HH:mm dd/MM/yyyy");

        LocalDateTime startsAt= LocalDateTime.parse("19:30 24/04/2023", formatter);
        LocalDateTime finishesAt = LocalDateTime.parse("20:30 24/04/2023", formatter);

        Appointment appointment = new Appointment(patient, doctor, room, startsAt, finishesAt);

        appointment.setId(1);


        assertEquals(1, appointment.getId());
    }

    @Test
    void givenAnAppointment_whenSettingStartsAt_thenReturnNewStartAt() {
        Patient patient = new Patient("Jose Luis", "Olaya", 37, "j.olaya@email.com");
        Doctor doctor = new Doctor ("Perla", "Amalia", 24, "p.amalia@hospital.accwe");
        Room room = new Room("Dermatology");

        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("HH:mm dd/MM/yyyy");

        LocalDateTime startsAt= LocalDateTime.parse("19:30 24/04/2023", formatter);
        LocalDateTime finishesAt = LocalDateTime.parse("20:30 24/04/2023", formatter);

        Appointment appointment = new Appointment(patient, doctor, room, startsAt, finishesAt);

        LocalDateTime startsAt2= LocalDateTime.parse("20:00 24/04/2023", formatter);


        appointment.setStartsAt(startsAt2);


        assertEquals("20:00 24/04/2023", appointment.getStartsAt().format(formatter));
    }

    @Test
    void givenAnAppointment_whenSettingFinishesAt_thenReturnNewFinishesAt() {
        Patient patient = new Patient("Jose Luis", "Olaya", 37, "j.olaya@email.com");
        Doctor doctor = new Doctor ("Perla", "Amalia", 24, "p.amalia@hospital.accwe");
        Room room = new Room("Dermatology");

        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("HH:mm dd/MM/yyyy");

        LocalDateTime startsAt= LocalDateTime.parse("19:30 24/04/2023", formatter);
        LocalDateTime finishesAt = LocalDateTime.parse("20:30 24/04/2023", formatter);

        Appointment appointment = new Appointment(patient, doctor, room, startsAt, finishesAt);

        LocalDateTime finishesAt2 = LocalDateTime.parse("20:45 24/04/2023", formatter);


        appointment.setFinishesAt(finishesAt2);


        assertEquals("20:45 24/04/2023", appointment.getFinishesAt().format(formatter));
    }

    @Test
    void givenTwoAppointments_whenSameStartsAt_thenReturnTrue() {
        Patient patient2 = new Patient("Paulino", "Antunez", 37, "p.antunez@email.com");
        Doctor doctor2 = new Doctor ("Miren", "Iniesta", 24, "m.iniesta@hospital.accwe");

        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("HH:mm dd/MM/yyyy");

        LocalDateTime startsAt= LocalDateTime.parse("19:30 24/04/2023", formatter);
        LocalDateTime finishesAt = LocalDateTime.parse("19:30 24/04/2023", formatter);

        Appointment appointment = new Appointment(p1, d1, r1, startsAt, finishesAt);
        Appointment appointment2 = new Appointment(patient2, doctor2, r1, startsAt, finishesAt);

        assertTrue(appointment.overlaps(appointment2));

    }

    @Test
    void givenTwoAppointments_whenSameFinishesAt_thenReturnTrue() {
        Patient patient2 = new Patient("Paulino", "Antunez", 37, "p.antunez@email.com");
        Doctor doctor2 = new Doctor ("Miren", "Iniesta", 24, "m.iniesta@hospital.accwe");

        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("HH:mm dd/MM/yyyy");

        LocalDateTime startsAt= LocalDateTime.parse("19:30 24/04/2023", formatter);
        LocalDateTime startsAt2= LocalDateTime.parse("19:00 24/04/2023", formatter);
        LocalDateTime finishesAt = LocalDateTime.parse("19:30 24/04/2023", formatter);

        Appointment appointment = new Appointment(p1, d1, r1, startsAt, finishesAt);
        Appointment appointment2 = new Appointment(patient2, doctor2, r1, startsAt2, finishesAt);

        assertTrue(appointment.overlaps(appointment2));

    }

    @Test
    void givenTwoAppointments_whenOverlapA_thenReturnTrue() {
        Patient patient2 = new Patient("Paulino", "Antunez", 37, "p.antunez@email.com");
        Doctor doctor2 = new Doctor ("Miren", "Iniesta", 24, "m.iniesta@hospital.accwe");

        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("HH:mm dd/MM/yyyy");

        LocalDateTime startsAt= LocalDateTime.parse("19:00 24/04/2023", formatter);
        LocalDateTime finishesAt = LocalDateTime.parse("20:00 24/04/2023", formatter);

        LocalDateTime startsAt2= LocalDateTime.parse("19:30 24/04/2023", formatter);
        LocalDateTime finishesAt2 = LocalDateTime.parse("19:45 24/04/2023", formatter);

        Appointment appointment = new Appointment(p1, d1, r1, startsAt, finishesAt);
        Appointment appointment2 = new Appointment(patient2, doctor2, r1, startsAt2, finishesAt2);

        assertTrue(appointment.overlaps(appointment2));

    }

    @Test
    void givenTwoAppointments_whenOverlapA2_thenReturnTrue() {
        Patient patient2 = new Patient("Paulino", "Antunez", 37, "p.antunez@email.com");
        Doctor doctor2 = new Doctor ("Miren", "Iniesta", 24, "m.iniesta@hospital.accwe");

        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("HH:mm dd/MM/yyyy");

        LocalDateTime startsAt= LocalDateTime.parse("19:00 24/04/2023", formatter);
        LocalDateTime finishesAt = LocalDateTime.parse("19:15 24/04/2023", formatter);

        LocalDateTime startsAt2= LocalDateTime.parse("18:30 24/04/2023", formatter);
        LocalDateTime finishesAt2 = LocalDateTime.parse("19:45 24/04/2023", formatter);

        Appointment appointment = new Appointment(p1, d1, r1, startsAt, finishesAt);
        Appointment appointment2 = new Appointment(patient2, doctor2, r1, startsAt2, finishesAt2);

        assertTrue(appointment.overlaps(appointment2));

        //This test fail because there is a mistake in the code. Line 106 should be:
        // if ( this.getStartsAt().isAfter(appointment.getStartsAt()) && this.getStartsAt().isBefore(appointment.getFinishesAt())){
        //                return true;
        //            }

    }

    @Test
    void givenTwoAppointments_whenNotOverlap_thenReturnFalse() {
        Patient patient2 = new Patient("Paulino", "Antunez", 37, "p.antunez@email.com");
        Doctor doctor2 = new Doctor ("Miren", "Iniesta", 24, "m.iniesta@hospital.accwe");

        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("HH:mm dd/MM/yyyy");

        LocalDateTime startsAt= LocalDateTime.parse("19:00 24/04/2023", formatter);
        LocalDateTime finishesAt = LocalDateTime.parse("19:15 24/04/2023", formatter);

        LocalDateTime startsAt2= LocalDateTime.parse("19:30 24/04/2023", formatter);
        LocalDateTime finishesAt2 = LocalDateTime.parse("19:45 24/04/2023", formatter);

        Appointment appointment = new Appointment(p1, d1, r1, startsAt, finishesAt);
        Appointment appointment2 = new Appointment(patient2, doctor2, r1, startsAt2, finishesAt2);

        assertFalse(appointment.overlaps(appointment2));

    }




}
