package eig.tasks

import eig.model.test.TestData
import eig.model.test.TestFiles
import org.apache.poi.xssf.usermodel.XSSFSheet
import org.apache.poi.xssf.usermodel.XSSFWorkbook
import org.modelcatalogue.spreadsheet.api.Cell
import org.modelcatalogue.spreadsheet.query.api.SpreadsheetCriteria
import org.modelcatalogue.spreadsheet.query.poi.PoiSpreadsheetCriteria
import spock.lang.Specification

// TODO: merged into one task 01
class _02_ExportAsExcelWithFilterSpec extends Specification {

    void 'export to excel with filter'() {
        when:
            File excelFile = TestFiles.newTestFile('test02.xlsx')
            ExcelExporter.buildSpreadsheetWithFilter(TestData.orders).writeTo(excelFile)
            TestFiles.open excelFile
        and:
            SpreadsheetCriteria criteria = PoiSpreadsheetCriteria.FACTORY.forFile(excelFile)
            List<Cell> cells = criteria.all()
        then:
            noExceptionThrown()
            cells.size() == 62128
            criteria.exists {
                sheet {
                    row(1) {
                        cell('A') {
                            value 'Order Number'
            }   }   }   }
            criteria.exists {
                sheet {
                    row(2) {
                        cell('A') {
                            value 10107
            }   }   }   }
        when:
            XSSFSheet sheet = criteria.all().first().row.sheet as XSSFSheet
        then: 'test automatic filter'
            sheet.getCTWorksheet()
            sheet.getCTWorksheet().autoFilter
            sheet.getCTWorksheet().autoFilter.ref == 'A1:V2824'
        and: 'test scroll lock'
            sheet.defaultSheetView.pane.setXSplit
            sheet.defaultSheetView.pane.setYSplit
    }

}
