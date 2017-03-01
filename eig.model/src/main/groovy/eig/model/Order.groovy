package eig.model

import com.google.common.collect.ImmutableList

import java.time.LocalDateTime

final class Order {

    final int number
    final OrderStatus status
    final LocalDateTime date
    final Customer customer
    final ImmutableList<OrderLine> lines

    Order(int number, OrderStatus status, LocalDateTime date, Customer customer, ImmutableList<OrderLine> lines) {
        this.number = number
        this.status = status
        this.date = date
        this.customer = customer
        this.lines = lines
    }

    boolean equals(o) {
        if (this.is(o)) return true
        if (getClass() != o.class) return false

        Order order = (Order) o

        if (number != order.number) return false
        if (customer != order.customer) return false
        if (date != order.date) return false
        if (lines != order.lines) return false
        if (status != order.status) return false

        return true
    }

    int hashCode() {
        int result
        result = number
        result = 31 * result + (status != null ? status.hashCode() : 0)
        result = 31 * result + (date != null ? date.hashCode() : 0)
        result = 31 * result + (customer != null ? customer.hashCode() : 0)
        result = 31 * result + (lines != null ? lines.hashCode() : 0)
        return result
    }
}
