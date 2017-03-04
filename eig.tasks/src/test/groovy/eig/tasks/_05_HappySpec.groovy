package eig.tasks

import eig.model.test.TestFiles
import org.modelcatalogue.spreadsheet.query.api.SpreadsheetCriteria
import org.modelcatalogue.spreadsheet.query.poi.PoiSpreadsheetCriteria
import spock.lang.Specification

class _05_HappySpec extends Specification {

    void 'draw a smiley'() {
        when:
            File excelFile = TestFiles.newTestFile("test05.xlsx")
            ExcelExporter.drawSmiley().writeTo(excelFile)
            TestFiles.open excelFile
        and:
            SpreadsheetCriteria criteria = PoiSpreadsheetCriteria.FACTORY.forFile(excelFile)
        then: 'there is no exception thrown'
            noExceptionThrown()
        and:
            criteria.query {
                sheet {
                    row {
                        cell {
                            style {
                                foreground yellow
            }   }   }   }   }.size() == 196
            criteria.query {
                sheet {
                    row({ it.number in 5..12}) {
                        cell({it.column in 6..11}) {
                            style {
                                foreground black
            }   }   }   }   }.size() == 12

    }

}
