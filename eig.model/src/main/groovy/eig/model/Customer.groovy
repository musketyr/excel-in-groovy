package eig.model

final class Customer {

    final String companyName
    final String firstName
    final String lastName
    final String phone
    final String addressLine1
    final String addressLine2
    final String city
    final String postalCode
    final String state
    final String country
    final String territory

    Customer(String companyName, String firstName, String lastName, String phone, String addressLine1, String addressLine2, String city, String postalCode, String state, String country, String territory) {
        this.companyName = companyName
        this.firstName = firstName
        this.lastName = lastName
        this.phone = phone
        this.addressLine1 = addressLine1
        this.addressLine2 = addressLine2
        this.city = city
        this.postalCode = postalCode
        this.state = state
        this.country = country
        this.territory = territory
    }

    boolean equals(o) {
        if (this.is(o)) return true
        if (getClass() != o.class) return false

        Customer customer = (Customer) o

        if (addressLine1 != customer.addressLine1) return false
        if (addressLine2 != customer.addressLine2) return false
        if (city != customer.city) return false
        if (companyName != customer.companyName) return false
        if (country != customer.country) return false
        if (firstName != customer.firstName) return false
        if (lastName != customer.lastName) return false
        if (phone != customer.phone) return false
        if (postalCode != customer.postalCode) return false
        if (state != customer.state) return false
        if (territory != customer.territory) return false

        return true
    }

    int hashCode() {
        int result
        result = (companyName != null ? companyName.hashCode() : 0)
        result = 31 * result + (firstName != null ? firstName.hashCode() : 0)
        result = 31 * result + (lastName != null ? lastName.hashCode() : 0)
        result = 31 * result + (phone != null ? phone.hashCode() : 0)
        result = 31 * result + (addressLine1 != null ? addressLine1.hashCode() : 0)
        result = 31 * result + (addressLine2 != null ? addressLine2.hashCode() : 0)
        result = 31 * result + (city != null ? city.hashCode() : 0)
        result = 31 * result + (postalCode != null ? postalCode.hashCode() : 0)
        result = 31 * result + (state != null ? state.hashCode() : 0)
        result = 31 * result + (country != null ? country.hashCode() : 0)
        result = 31 * result + (territory != null ? territory.hashCode() : 0)
        return result
    }
}
