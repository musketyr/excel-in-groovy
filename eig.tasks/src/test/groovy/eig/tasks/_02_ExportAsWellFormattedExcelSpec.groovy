package eig.tasks

import eig.model.test.TestData
import eig.model.test.TestFiles
import org.modelcatalogue.spreadsheet.query.api.SpreadsheetCriteria
import org.modelcatalogue.spreadsheet.query.poi.PoiSpreadsheetCriteria
import spock.lang.Specification

class _02_ExportAsWellFormattedExcelSpec extends Specification {

    void 'export well formatted excel'() {
        when:
            File excelFile = TestFiles.newTestFile('test02.xlsx')
            ExcelIntegration.buildWellFormattedSpreadsheet(TestData.orders).writeTo(excelFile)
            TestFiles.open excelFile
        and:
            SpreadsheetCriteria criteria = PoiSpreadsheetCriteria.FACTORY.forFile(excelFile)
        then: 'there is no exception thrown'
            noExceptionThrown()
        and: 'all the prices are displayed starting with EUR sign'
            criteria.query {
                sheet {
                    row {
                        cell {
                            style {
                                format {
                                    it.startsWith('€')
            }   }   }   }   }   }.size() == 8469
        and: 'dates are well-formatted and displayed in merged cells'
            criteria.query {
                sheet {
                    row {
                        cell {
                            style {
                                format {
                                    it.startsWith('d')
            }   }   }   }   }   }.size() == 307
        and: 'resolved rows are displayed in light green'
            criteria.query {
                sheet {
                    row {
                        cell {
                            style {
                                foreground lightGreen
                            }
                        }
                    }
                }
            }.size() == 432
        and: 'headers are bold'
            criteria.query {
                sheet {
                    row {
                        cell {
                            style {
                                font {
                                    make bold
                            }   }
                        }
                    }
                }
            }.size() == 22
    }

}
