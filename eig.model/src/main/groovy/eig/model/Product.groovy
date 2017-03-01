package eig.model

final class Product {
    final String line
    final String code
    final BigDecimal msrp

    Product(String line, String code, BigDecimal msrp) {
        this.line = line
        this.code = code
        this.msrp = msrp
    }

    boolean equals(o) {
        if (this.is(o)) return true
        if (getClass() != o.class) return false

        Product product = (Product) o

        if (code != product.code) return false
        if (line != product.line) return false
        if (msrp != product.msrp) return false

        return true
    }

    int hashCode() {
        int result
        result = (line != null ? line.hashCode() : 0)
        result = 31 * result + (code != null ? code.hashCode() : 0)
        result = 31 * result + (msrp != null ? msrp.hashCode() : 0)
        return result
    }
}
