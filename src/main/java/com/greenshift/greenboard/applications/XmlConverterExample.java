package com.greenshift.greenboard.applications;

import com.greenshift.greenboard.models.entities.User;
import com.greenshift.greenboard.services.UserService;
import com.greenshift.greenboard.singletons.SessionManager;
import jakarta.xml.bind.JAXBContext;
import jakarta.xml.bind.JAXBException;
import jakarta.xml.bind.Marshaller;

import java.io.StringWriter;
import java.util.Arrays;
import java.util.List;

public class XmlConverterExample {
    public static void main(String[] args) throws JAXBException {
        SessionManager.getInstance().useDummyUser();
        User user = SessionManager.getInstance().getCurrentUser();

        UserService userService = new UserService();
        List<User> users = Arrays.stream(userService.getAll()).toList();

        JAXBContext jaxbContext = JAXBContext.newInstance(user.getClass());
        Marshaller marshaller = jaxbContext.createMarshaller();
        marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);

        StringWriter writer = new StringWriter();
        StringBuilder mainXml = new StringBuilder();

        for (User u : users) {
            marshaller.marshal(user, writer);
            String xml = writer.toString();
            // remove the xml header
            xml = xml.substring(xml.indexOf("\n") + 1);
            mainXml.append(xml);
        }

        System.out.println(mainXml);
    }
}
