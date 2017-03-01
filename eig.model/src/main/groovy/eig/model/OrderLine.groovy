package eig.model

final class OrderLine {
    final int line
    final int quantity
    final BigDecimal price
    final BigDecimal sales
    final Deal deal
    final Product product

    OrderLine(int line, int quantity, BigDecimal price, BigDecimal sales, Deal deal, Product product) {
        this.line = line
        this.quantity = quantity
        this.price = price
        this.sales = sales
        this.deal = deal
        this.product = product
    }

    boolean equals(o) {
        if (this.is(o)) return true
        if (getClass() != o.class) return false

        OrderLine orderLine = (OrderLine) o

        if (line != orderLine.line) return false
        if (quantity != orderLine.quantity) return false
        if (deal != orderLine.deal) return false
        if (price != orderLine.price) return false
        if (product != orderLine.product) return false
        if (sales != orderLine.sales) return false

        return true
    }

    int hashCode() {
        int result
        result = line
        result = 31 * result + quantity
        result = 31 * result + (price != null ? price.hashCode() : 0)
        result = 31 * result + (sales != null ? sales.hashCode() : 0)
        result = 31 * result + (deal != null ? deal.hashCode() : 0)
        result = 31 * result + (product != null ? product.hashCode() : 0)
        return result
    }
}
