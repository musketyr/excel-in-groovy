package eig.tasks

import eig.model.test.TestData
import eig.model.test.TestFiles
import org.modelcatalogue.spreadsheet.api.BorderStyle
import org.modelcatalogue.spreadsheet.query.api.SpreadsheetCriteria
import org.modelcatalogue.spreadsheet.query.poi.PoiSpreadsheetCriteria
import spock.lang.Specification
import spock.lang.Unroll

class _03_ExportOrderAsExcelSpec extends Specification {

    @Unroll
    void 'export order for #territory'() {
        when:
            File excelFile = TestFiles.newTestFile("test03-${territory}.xlsx")
            ExcelExporter.buildOrder(TestData.orders.values().find { it.customer.territory == territory } ).writeTo(excelFile)
            TestFiles.open excelFile
        and:
            SpreadsheetCriteria criteria = PoiSpreadsheetCriteria.FACTORY.forFile(excelFile)
        then: 'there is no exception thrown'
            noExceptionThrown()
        and:
            criteria.exists {
                sheet {
                    row {
                        cell {
                            style {
                                format { it.startsWith currencyPrefix }
            }   }   }   }   }
            criteria.exists {
                sheet {
                    row {
                        cell {
                            style {
                                border(top) {
                                    style thickBorder
            }   }   }   }   }   }
            criteria.exists {
                sheet {
                    row {
                        cell {
                            style {
                                border(bottom) {
                                    style thinBorder
            }   }   }   }   }   }

        where:
            territory | currencyPrefix | thickBorder        | thinBorder
            'EMEA'    | /\€/           | BorderStyle.DOUBLE | BorderStyle.DASHED    // USA
            'NA'      | /\E\U\R/       | BorderStyle.THICK  | BorderStyle.THIN      // Europe and Middle East
    }

}
