package seedu.nursesched.parser;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import org.junit.jupiter.api.Test;
import parser.ApptParser;
import parser.PatientParser;
import patient.Patient;

import java.time.LocalDate;
import java.time.LocalTime;

public class ParserTest {
    @Test
    public void testExtractInputs_patientAddCommand() {
        String input = "pf add p/Jean Doe a/25 n/Allergic to penicillin";
        PatientParser patientParser = PatientParser.extractInputs(input);

        assertNotNull(patientParser);
        assertEquals("add", patientParser.getCommand());
        assertEquals("Jean Doe", patientParser.getName());
        assertEquals("25", patientParser.getAge());
        assertEquals("Allergic to penicillin", patientParser.getNotes());
        assertEquals(-1, patientParser.getIndex());
    }

    @Test
    public void testExtractInputs_patientDeleteCommand() {
        String input = "pf del 2";
        Patient patientOne = new Patient("Jean Doe", "25", "Allergic to penicillin");
        Patient patientTwo = new Patient("John Doe", "40", "Allergic to peanuts");
        Patient.addPatient(patientOne);
        Patient.addPatient(patientTwo);

        PatientParser patientParser = PatientParser.extractInputs(input);

        assertNotNull(patientParser);
        assertEquals("del", patientParser.getCommand());
        assertEquals("", patientParser.getName());
        assertEquals("", patientParser.getAge());
        assertEquals("", patientParser.getNotes());
        assertEquals(2, patientParser.getIndex());
    }

    @Test
    public void testExtractInputs_patientDeleteInvalidIndexCommand() {
        String input = "pf del 2";

        PatientParser patientParser = PatientParser.extractInputs(input);

        assertNull(patientParser);
    }

    @Test
    public void testExtractInputs_patientListCommand() {
        String input = "pf list";
        PatientParser patientParser = PatientParser.extractInputs(input);

        assertNotNull(patientParser);
        assertEquals("list", patientParser.getCommand());
        assertEquals("", patientParser.getName());
        assertEquals("", patientParser.getAge());
        assertEquals("", patientParser.getNotes());
        assertEquals(-1, patientParser.getIndex());
    }

    @Test
    public void testExtractInputs_patientInvalidCommand() {
        String input = "pf invalid";
        PatientParser patientParser = PatientParser.extractInputs(input);

        assertNull(patientParser);
    }

    @Test
    public void testExtractInputs_patientList() {
        String input = "pf list";
        PatientParser patientParser = PatientParser.extractInputs(input);

        assertNotNull(patientParser);
    }

    @Test
    public void testExtractInputs_patientMissingParameters() {
        String input = "pf add p/Jean Doe a/25";
        PatientParser patientParser = PatientParser.extractInputs(input);

        assertNull(patientParser);
    }

    @Test
    public void testExtractInputs_patientEmptyInput() {
        String input = "";
        PatientParser patientParser = PatientParser.extractInputs(input);

        assertNull(patientParser);
    }

    @Test
    public void testExtractInputs_appointmentUnmarkCommand() {
        String input = "appt unmark p/Jean doe s/13:00 d/2025-02-15";
        ApptParser apptParser = ApptParser.extractInputs(input);

        assertNotNull(apptParser);
        assertEquals("unmark", apptParser.getCommand());
        assertEquals("jean doe", apptParser.getName());
        assertEquals(LocalTime.parse("13:00"), apptParser.getStartTime());
        assertEquals(LocalDate.parse("2025-02-15"), apptParser.getDate());
    }

    @Test
    public void testExtractInputs_appointmentInvalidCommand() {
        String input = "appt invalid";
        ApptParser apptParser = ApptParser.extractInputs(input);
        assertNull(apptParser);
    }

    @Test
    public void testExtractInputs_appointmentMissingParameters() {
        String input = "appt add p/Jean Doe s/13:00";
        ApptParser apptParser = ApptParser.extractInputs(input);
        assertNull(apptParser);
    }

    @Test
    public void testExtractInputs_appointmentAddCommand() {
        String input = "appt add p/Jean doe s/13:00 e/14:00 d/2025-02-15 n/Needs a wheelchair. Very annoying!";
        ApptParser apptParser = ApptParser.extractInputs(input);

        assertNotNull(apptParser);
        assertEquals("add", apptParser.getCommand());
        assertEquals("jean doe", apptParser.getName());
        assertEquals(LocalTime.parse("13:00"), apptParser.getStartTime());
        assertEquals(LocalTime.parse("14:00"), apptParser.getEndTime());
        assertEquals(LocalDate.parse("2025-02-15"), apptParser.getDate());
        assertEquals("needs a wheelchair. very annoying!", apptParser.getNotes());
    }

    @Test
    public void testExtractInputs_appointmentDeleteCommand() {
        String input = "appt del p/Jean doe s/13:00 d/2025-02-15";
        ApptParser apptParser = ApptParser.extractInputs(input);

        assertNotNull(apptParser);
        assertEquals("del", apptParser.getCommand());
        assertEquals("jean doe", apptParser.getName());
        assertEquals(LocalTime.parse("13:00"), apptParser.getStartTime());
        assertEquals(LocalDate.parse("2025-02-15"), apptParser.getDate());
    }

    @Test
    public void testExtractInputs_appointmentMarkCommand() {
        String input = "appt mark p/Jean doe s/13:00 d/2025-02-15";
        ApptParser apptParser = ApptParser.extractInputs(input);

        assertNotNull(apptParser);
        assertEquals("mark", apptParser.getCommand());
        assertEquals("jean doe", apptParser.getName());
        assertEquals(LocalTime.parse("13:00"), apptParser.getStartTime());
        assertEquals(LocalDate.parse("2025-02-15"), apptParser.getDate());
    }

    @Test
    public void testExtractInputs_appointmentInvalidTime() {
        String input = "appt add p/Jean doe s/1pm e/2pm d/2025-02-15 n/Needs wheelchair.";
        String input2 = "appt del p/Jean doe s/1300 d/2025-02-15";
        String input3 = "appt mark p/Jean doe s/24:00 d/2025-02-15";
        ApptParser apptParser = ApptParser.extractInputs(input);
        ApptParser apptParser2 = ApptParser.extractInputs(input2);
        ApptParser apptParser3 = ApptParser.extractInputs(input3);
        assertNull(apptParser);
        assertNull(apptParser2);
        assertNull(apptParser3);
    }

    @Test
    public void testExtractInputs_appointmentInvalidDate() {
        String input = "appt add p/Jean doe s/15:00 e/17:00 d/05 June 2025 n/Needs wheelchair.";
        String input2 = "appt del p/Jean doe s/15:00 d/12-02-2025";
        String input3 = "appt mark p/Jean doe s/15:00 d/2025-15-02";
        ApptParser apptParser = ApptParser.extractInputs(input);
        ApptParser apptParser2 = ApptParser.extractInputs(input2);
        ApptParser apptParser3 = ApptParser.extractInputs(input3);
        assertNull(apptParser);
        assertNull(apptParser2);
        assertNull(apptParser3);
    }
}
