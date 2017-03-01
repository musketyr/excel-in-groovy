package eig.model.test

import eig.model.Deal
import eig.model.Order
import eig.model.OrderLine
import eig.model.OrderStatus
import spock.lang.Specification

import java.time.LocalDateTime

class TestDataSpec extends Specification {

    void 'sanity checks'() {
        expect:
            TestData.orders
            TestData.orders.size() == 307
            TestData.products
            TestData.products.size() == 109
            TestData.customers
            TestData.customers.size() == 92

        when:
            Order order = TestData.orders.get(10100)
        then:
            // 10100

            order
            order.number == 10100
            order.status == OrderStatus.SHIPPED
            order.date == LocalDateTime.parse('1/6/2003 0:00', TestData.DATE_TIME_PATTERN)
            order.customer
            order.customer.companyName == 'Online Diecast Creations Co.'
            order.customer.phone == '6035558647'
            order.customer.addressLine1 == '2304 Long Airport Avenue'
            order.customer.addressLine2 == ''
            order.customer.city == 'Nashua'
            order.customer.country == 'USA'
            order.customer.state == 'NH'
            order.customer.territory == 'NA'
            order.customer.postalCode == '62005'
            order.customer.lastName == 'Young'
            order.customer.firstName == 'Valarie'

            order.lines
            order.lines.size() == 4

        when:
            OrderLine line = order.lines.find { it.line == 1 }
        then:
            line
            line.line == 1
            line.quantity == 49
            line.price == 34.47
            line.sales == 1689.03
            line.deal == Deal.SMALL
            line.product
            line.product.line == 'Vintage Cars'
            line.product.code == 'S24_3969'
            line.product.msrp == 41 as BigDecimal

    }

}
