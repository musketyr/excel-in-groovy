package eig.tasks

import eig.model.test.TestData
import spock.lang.Specification

class _07_ImportProductsSpec extends Specification {

    void 'load products'() {
        when:
            Map<String, Integer> productQuantities = ExcelIntegration.loadProducts(TestData.testOrderStream)
        then:
            productQuantities
            productQuantities.size() == 5
            productQuantities['S50_4713'] == 44
            productQuantities['S24_2360'] == 32
            productQuantities['S32_4485'] == 25
            productQuantities['S12_2823'] == 50
            productQuantities['S10_1678'] == 34
    }
}
