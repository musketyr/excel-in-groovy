package eig.tasks

import eig.model.Order
import eig.model.OrderLine
import eig.model.test.TestData
import spock.lang.Specification

class _06_ImportOrdersSpec extends Specification {

    void 'load orders'() {
        when:
            List<Order> orders = ExcelIntegration.loadOrders(TestData.testExcelDataStream)
        then:
            orders.size() == TestData.orders.size()
            orders.each {
                assertLoadedProperly(it)
            }
    }

    boolean assertLoadedProperly(Order order) {
        Order reference = TestData.orders[order.number]

        assert order
        assert reference
        assert order.customer
        assert reference.customer
        assert order.customer.companyName == reference.customer.companyName
        assert order.customer.firstName == reference.customer.firstName
        assert order.customer.lastName == reference.customer.lastName
        assert order.customer.phone == reference.customer.phone
        assert order.customer.addressLine1 == reference.customer.addressLine1
        assert order.customer.addressLine2 == reference.customer.addressLine2
        assert order.customer.city == reference.customer.city
        assert order.customer.postalCode == reference.customer.postalCode
        assert order.customer.state == reference.customer.state
        assert order.customer.country == reference.customer.country
        assert order.customer.territory == reference.customer.territory
        assert order.date == reference.date
        assert order.number == reference.number
        assert order.status == reference.status
        assert order.lines
        assert reference.lines
        assert order.lines.size() == reference.lines.size()

        for (OrderLine line in order.lines) {
            assertLoadedProperly(line, reference)
        }


        return true
    }

    boolean assertLoadedProperly(OrderLine line, Order referenceOrder) {
        OrderLine reference = referenceOrder.lines.find {it.line == line.line }

        assert line
        assert reference

        assert line.sales == reference.sales
        assert line.quantity == reference.quantity
        assert line.deal == reference.deal
        assert line.price == reference.price

        assert line.product
        assert reference.product

        assert line.product.code == reference.product.code
        assert line.product.msrp == reference.product.msrp
        assert line.product.line == reference.product.line

        return true
    }

}
