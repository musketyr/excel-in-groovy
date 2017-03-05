package eig.tasks

import eig.model.test.TestData
import spock.lang.Specification

import java.time.LocalTime

class _08_ImportServiceHoursSpec extends Specification {

    void 'load departures from the airport in the working days'() {
        when:
            Set<LocalTime> departuresFromTheAirportWD = ExcelIntegration.loadDeparturesFromTheAirportWD(TestData.testServiceHoursStream)
        then:
            departuresFromTheAirportWD
            departuresFromTheAirportWD.size() == 84
            LocalTime.of(5, 10) in departuresFromTheAirportWD
            LocalTime.of(6, 39) in departuresFromTheAirportWD
            LocalTime.of(12, 50) in departuresFromTheAirportWD
            LocalTime.of(15, 26) in departuresFromTheAirportWD
            LocalTime.of(16, 57) in departuresFromTheAirportWD
            LocalTime.of(23, 30) in departuresFromTheAirportWD
    }
}
