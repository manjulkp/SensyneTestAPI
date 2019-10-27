package utility;
import io.restassured.internal.NameAndValue;
import io.restassured.internal.common.assertion.AssertParameter;

public class Header implements NameAndValue {

    private final String name;
    private final String value;

    /**
     * Create a new header with the given name and value.
     *

     */
    public Header(String name, String value) {
        AssertParameter.notNull(name, "Header name");
        this.name = name;
        this.value = value;
    }

    public String getName() {
        return name;
    }

    public String getValue() {
        return value;
    }

    public boolean hasSameNameAs(Header header) {
        AssertParameter.notNull(header, Header.class);
        return this.name.equalsIgnoreCase(header.getName());
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Header header = (Header) o;

        // HTTP header names are always case-insensitive. Values are usually case-insensitive.
        if (name != null ? !name.equalsIgnoreCase(header.name) : header.name != null) return false;
        if (value != null ? !value.equalsIgnoreCase(header.value) : header.value != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = name != null ? name.hashCode() : 0;
        result = 31 * result + (value != null ? value.hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        final StringBuilder builder = new StringBuilder();
        builder.append(name);
        if (value != null) {
            builder.append("=").append(value);
        }
        return builder.toString();
    }
}
