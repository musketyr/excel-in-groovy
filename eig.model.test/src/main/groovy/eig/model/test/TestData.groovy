package eig.model.test

import com.google.common.collect.ImmutableList
import com.google.common.collect.ImmutableMap
import com.google.common.collect.LinkedHashMultimap
import com.google.common.collect.Multimap
import com.xlson.groovycsv.CsvParser
import eig.model.Customer
import eig.model.Deal
import eig.model.Order
import eig.model.OrderLine
import eig.model.OrderStatus
import eig.model.Product

import java.time.LocalDateTime
import java.time.format.DateTimeFormatter

/**
 * @see https://www.kaggle.com/kyanyoga/sample-sales-data
 */
class TestData {

    static final ImmutableMap<Integer, Order> orders
    static final ImmutableMap<String, Product> products
    static final ImmutableMap<String, Customer> customers

    static final DateTimeFormatter DATE_TIME_PATTERN = DateTimeFormatter.ofPattern('M/d/yyyy H:mm')

    static {
        Multimap<Integer, OrderLine> orderLines = LinkedHashMultimap.create()
        Map<Integer, Order> blankOrders = [:]
        Map<String, Product> mutableProducts = [:]
        Map<String, Customer> mutableCustomers = [:]

        TestData.getResourceAsStream('testdata.csv').withReader {
            def data = CsvParser.parseCsv(it)
            for (row in data) {
                int orderNumber = readInt(row.ORDERNUMBER)

                if (!blankOrders.containsKey(orderNumber)) {
                    LocalDateTime date = readDate(row.ORDERDATE)
                    OrderStatus status = readStatus(row.STATUS)

                    String customerName = row.CUSTOMERNAME
                    Customer customer = mutableCustomers[customerName]

                    if (!customer) {
                        mutableCustomers[customerName] = customer = new Customer(customerName, row.CONTACTFIRSTNAME, row.CONTACTLASTNAME, row.PHONE, row.ADDRESSLINE1, row.ADDRESSLINE2, row.CITY, row.POSTALCODE, row.STATE, row.COUNTRY, row.TERRITORY)
                    }

                    blankOrders.put(orderNumber, new Order(orderNumber, status, date, customer, ImmutableList.of()))
                }


                String productCode = row.PRODUCTCODE
                Product product = mutableProducts[productCode]

                if (!product) {
                    mutableProducts[productCode] = product = new Product(row.PRODUCTLINE, productCode, readDecimal(row.MSRP))
                }

                orderLines.put(orderNumber, new OrderLine(
                    readInt(row.ORDERLINENUMBER),
                    readInt(row.QUANTITYORDERED),
                    readDecimal(row.PRICEEACH),
                    readDecimal(row.SALES),
                    readDeal(row.DEALSIZE),
                    product
                ))
            }
        }


        ImmutableMap.Builder<Integer, Order>ordersBuilder = ImmutableMap.builder()

        for (Order order in blankOrders.values()) {
            ordersBuilder.put(order.number, new Order(
                order.number,
                order.status,
                order.date,
                order.customer,
                ImmutableList.copyOf(orderLines.get(order.number).sort { a, b -> a.line <=> b.line })
            ))
        }

        orders = ordersBuilder.build()
        products = ImmutableMap.copyOf(mutableProducts)
        customers = ImmutableMap.copyOf(mutableCustomers)

    }

    static InputStream getTestExcelDataStream() {
        TestData.getResourceAsStream('testdata.xlsx')
    }

    static InputStream getTestOrderStream() {
        TestData.getResourceAsStream('testorder.xlsx')
    }

    static InputStream getTestServiceHoursStream() {
        TestData.getResourceAsStream('servicehours.xlsx')
    }

    private static int readInt(String value) {
        Integer.valueOf(value, 10)
    }

    private static LocalDateTime readDate(String value) {
        LocalDateTime.parse(value, DATE_TIME_PATTERN)
    }

    private static BigDecimal readDecimal(String value) {
        new BigDecimal(value)
    }

    private static OrderStatus readStatus(String value) {
        OrderStatus.valueOf(value.toUpperCase().replaceAll(/\s/, '_'))
    }

    private static Deal readDeal(String value) {
        Deal.valueOf(value.toUpperCase().replaceAll(/\s/, '_'))
    }


}
