package com.sharad.rest.test;

import java.io.StringWriter;

import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.Entity;
import javax.ws.rs.core.Response;
import javax.xml.bind.JAXBContext;
import javax.xml.bind.Marshaller;

import org.codehaus.jackson.map.ObjectMapper;
import org.codehaus.jackson.map.annotate.JsonSerialize;
import org.codehaus.jackson.map.annotate.JsonSerialize.Inclusion;
import org.junit.Test;

import com.sharad.rest.domain.Customer;

/**
 * @author <a href="mailto:bill@burkecentral.com">Bill Burke</a>
 * @version $Revision: 1 $
 */
public class CustomerResourceTest {
	@Test
	public void testCustomerResource() throws Exception {
		Client client = ClientBuilder.newClient();
		try {
			System.out.println("*** Create a new Customer ***");
			Customer cust = new Customer();
			cust.setId(null);
			cust.setFirstName("Sharad");
			cust.setLastName("Garg");
			cust.setStreet("34689 Agree Terrace");
			cust.setCity("Fremont");
			cust.setState("CA");
			cust.setCountry("USA");
			cust.setZip("94555");

			JAXBContext jaxbContext = JAXBContext.newInstance(Customer.class);
			Marshaller jaxbMarshaller = jaxbContext.createMarshaller();
			jaxbMarshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);
			
			StringWriter writer = new StringWriter();
			jaxbMarshaller.marshal(cust, System.out);
			jaxbMarshaller.marshal(cust, writer);
			
			ObjectMapper om = new ObjectMapper();
			
			System.out.println(om.writeValueAsString(cust));
			
			Response response = client
					.target("http://localhost:8080/jerseyRest/services/customers")
					.request().post(Entity.xml(writer.toString()));
			if (response.getStatus() != 201)
				throw new RuntimeException("Failed to create");
			String location = response.getLocation().toString();
			System.out.println("Location: " + location);
			response.close();

			System.out.println("*** GET Created Customer **");
			String customer = client.target(location).request()
					.get(String.class);
			System.out.println(customer);

			String updateCustomer = "<customer>"
					+ "<first-name>William</first-name>"
					+ "<last-name>Burke</last-name>"
					+ "<street>256 Clarendon Street</street>"
					+ "<city>Boston</city>" + "<state>MA</state>"
					+ "<zip>02115</zip>" + "<country>USA</country>"
					+ "</customer>";
			response = client.target(location).request()
					.put(Entity.xml(updateCustomer));
			if (response.getStatus() != 204)
				throw new RuntimeException("Failed to update");
			response.close();
			System.out.println("**** After Update ***");
			customer = client.target(location).request().get(String.class);
			System.out.println(customer);
		} finally {
			client.close();
		}
	}
}
