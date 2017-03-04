package eig.tasks

import eig.model.test.TestData
import eig.model.test.TestFiles
import org.modelcatalogue.spreadsheet.api.Cell
import org.modelcatalogue.spreadsheet.query.api.SpreadsheetCriteria
import org.modelcatalogue.spreadsheet.query.poi.PoiSpreadsheetCriteria
import spock.lang.Specification

class _04_FillDataSpec extends Specification {

    void 'export sales graphs'() {
        when:
            File excelFile = TestFiles.newTestFile("test04.xlsx")
            ExcelExporter.fillData(TestData.orders).writeTo(excelFile)
            TestFiles.open excelFile
        and:
            SpreadsheetCriteria criteria = PoiSpreadsheetCriteria.FACTORY.forFile(excelFile)
        then: 'there is no exception thrown'
            noExceptionThrown()
        when:
            Cell territoryHeadlineCell = criteria.find {
                sheet 'Territory', {
                    row {
                        cell {
                            value 'Territory'
                        }
            }   }   }
        then:
            territoryHeadlineCell
        when:
            Set<String> territories = new HashSet<String>()
            Cell territoryCell = territoryHeadlineCell.bellow
            territories.add(territoryCell.value as String)
            while (territoryCell?.bellow) {
                territoryCell = territoryCell.bellow
                territories.add(territoryCell.value as String)
            }
        then:
            territories.size() == 4
            'APAC' in territories
            'EMEA' in territories
            'Japan' in territories
            'NA' in territories
        when:
            Cell naCell = criteria.find {
                sheet 'Territory', {
                    row {
                        cell {
                            value 'NA'
                        }
            }   }   }
        then:
            naCell
            naCell.right.value == 3852061.39
        expect: 'territories count is set to proper value'
            criteria.exists {
                sheet('Territory') {
                    row(2) {
                        cell('C') {
                            value 4
            }   }   }   }
        when:
            Cell productsHeadlineCell = criteria.find {
                sheet 'Products', {
                    row {
                        cell {
                            value 'Product'
                        }
            }   }   }
        then:
            productsHeadlineCell
        when:
            Set<String> products = new HashSet<String>()
            Cell productCell = productsHeadlineCell.bellow
            products.add(productCell.value as String)
            while (productCell?.bellow) {
                productCell = productCell.bellow
                products.add(productCell.value as String)
            }
        then:
            products.size() == 7
            'Classic Cars' in products
            'Motorcycles' in products
            'Planes' in products
            'Ships' in products
            'Trains' in products
            'Trucks and Buses' in products
            'Vintage Cars' in products
        expect: 'product count is set to proper value'
            criteria.exists {
                sheet('Products') {
                    row(2) {
                        cell('C') {
                            value 7
            }   }   }   }
        and: 'there are two charts on the first sheet'
            criteria.workbook.workbook.sheets[0].drawingPatriarch.charts.size() == 2
    }

}
