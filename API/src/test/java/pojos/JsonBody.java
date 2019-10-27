package pojos;


import java.io.Serializable;
import org.apache.commons.lang.builder.EqualsBuilder;
import org.apache.commons.lang.builder.HashCodeBuilder;
import org.apache.commons.lang.builder.ToStringBuilder;

public class JsonBody implements Serializable
{

private Integer id;
private String name;
private Float price;
private final static long serialVersionUID = -4737677989959512861L;


public JsonBody(String _name,Float _price)
{
	this.name=_name;
	this.price=_price;
}
public Integer getId() {
return id;
}

public void setId(Integer id) {
this.id = id;
}

public String getName() {
return name;
}

public void setName(String name) {
this.name = name;
}

public Float getPrice() {
return price;
}

public void setPrice(Float price) {
this.price = price;
}

@Override
public String toString() {
return new ToStringBuilder(this).append("id", id).append("name", name).append("price", price).toString();
}

@Override
public int hashCode() {
return new HashCodeBuilder().append(name).append(id).append(price).toHashCode();
}

@Override
public boolean equals(Object other) {
if (other == this) {
return true;
}
if ((other instanceof JsonBody) == false) {
return false;
}
JsonBody rhs = ((JsonBody) other);
return new EqualsBuilder().append(name, rhs.name).append(id, rhs.id).append(price, rhs.price).isEquals();
}

}