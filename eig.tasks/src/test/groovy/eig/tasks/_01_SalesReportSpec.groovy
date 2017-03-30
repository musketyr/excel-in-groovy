package eig.tasks

import eig.model.test.TestData
import eig.model.test.TestFiles
import org.apache.poi.xssf.usermodel.XSSFSheet
import org.modelcatalogue.spreadsheet.api.Cell
import org.modelcatalogue.spreadsheet.query.api.SpreadsheetCriteria
import org.modelcatalogue.spreadsheet.query.poi.PoiSpreadsheetCriteria
import spock.lang.Specification

class _01_SalesReportSpec extends Specification {

    void 'export to excel with filter'() {
        when:
            File excelFile = TestFiles.newTestFile('test01.xlsx')
            ExcelIntegration.buildSalesReport(TestData.orders).writeTo(excelFile)
            TestFiles.open excelFile
        and:
            SpreadsheetCriteria criteria = PoiSpreadsheetCriteria.FACTORY.forFile(excelFile)
            Collection<Cell> cells = criteria.all().cells
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
                    row(1) {
                        cell('B') {
                            value 'Quantity'
            }   }   }   }
            criteria.exists {
                sheet {
                    row(1) {
                        cell('C') {
                            value 'Price per Item'
            }   }   }   }
            criteria.exists {
                sheet {
                    row(1) {
                        cell('D') {
                            value 'Order Line Number'
            }   }   }   }
            criteria.exists {
                sheet {
                    row(1) {
                        cell('E') {
                            value 'Sales'
            }   }   }   }
            criteria.exists {
                sheet {
                    row(1) {
                        cell('F') {
                            value 'Order Date'
            }   }   }   }
            criteria.exists {
                sheet {
                    row(1) {
                        cell('G') {
                            value 'Status'
            }   }   }   }
            criteria.exists {
                sheet {
                    row(1) {
                        cell('H') {
                            value 'Product Line'
            }   }   }   }
            criteria.exists {
                sheet {
                    row(1) {
                        cell('I') {
                            value 'MSRP'
            }   }   }   }
            criteria.exists {
                sheet {
                    row(1) {
                        cell('J') {
                            value 'Product Code'
            }   }   }   }
            criteria.exists {
                sheet {
                    row(1) {
                        cell('K') {
                            value 'Customer Name'
            }   }   }   }
            criteria.exists {
                sheet {
                    row(1) {
                        cell('L') {
                            value 'Phone'
            }   }   }   }
            criteria.exists {
                sheet {
                    row(1) {
                        cell('M') {
                            value 'Address Line 1'
            }   }   }   }
            criteria.exists {
                sheet {
                    row(1) {
                        cell('N') {
                            value 'Address Line 2'
            }   }   }   }
            criteria.exists {
                sheet {
                    row(1) {
                        cell('O') {
                            value 'City'
            }   }   }   }
            criteria.exists {
                sheet {
                    row(1) {
                        cell('P') {
                            value 'State'
            }   }   }   }
            criteria.exists {
                sheet {
                    row(1) {
                        cell('Q') {
                            value 'Postal Code'
            }   }   }   }
            criteria.exists {
                sheet {
                    row(1) {
                        cell('R') {
                            value 'Country'
            }   }   }   }
            criteria.exists {
                sheet {
                    row(1) {
                        cell('S') {
                            value 'Territory'
            }   }   }   }
            criteria.exists {
                sheet {
                    row(1) {
                        cell('T') {
                            value 'Last Name'
            }   }   }   }
            criteria.exists {
                sheet {
                    row(1) {
                        cell('U') {
                            value 'First Name'
            }   }   }   }
            criteria.exists {
                sheet {
                    row(1) {
                        cell('V') {
                            value 'Deal'
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
