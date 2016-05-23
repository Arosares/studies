package de.uniba.wiai.dsg.ajp.assignment4.issuetracking.logic.impl;

import de.uniba.wiai.dsg.ajp.assignment4.issuetracking.logic.Project;
import de.uniba.wiai.dsg.ajp.assignment4.issuetracking.logic.IssueTrackingException;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import javax.xml.bind.Unmarshaller;

import java.nio.file.Paths;

class ProjectXmlBinding {
	
    private final Marshaller marshaller;
    private final Unmarshaller unmarshaller;

    ProjectXmlBinding() throws JAXBException {
        JAXBContext context = JAXBContext.newInstance(Project.class);
        unmarshaller = context.createUnmarshaller();
        marshaller = context.createMarshaller();
        marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);
    }

    /**
     * Loads a {@link Project} by unmarshalling an XML file into memory.
     *
     * @param path the path of the XML file to be unmarshalled.
     * @return the {@link Project} loaded from the file System.
     * @throws IssueTrackingException if path is <code>null</code> or empty and if there is an
     *                                Exception during unmarshalling
     */
    Project load(String path) throws IssueTrackingException {
        ValidationHelper.assertExistentFile(path);

        try {
            return (Project) unmarshaller.unmarshal(Paths.get(path).toFile());
        } catch (JAXBException e) {
            throw new IssueTrackingException("File <" + path
                    + "> could not be loaded as Project.", e);
        }
    }

    /**
     * Prints a {@link Project} formated as XML to console.
     *
     * @param project the {@link Project} to be printed.
     * @throws IssueTrackingException if project is <code>null</code> or there is an error during
     *                                marshalling
     */
    void print(Project project) throws IssueTrackingException {
        try {
            marshaller.marshal(project, System.out);
        } catch (JAXBException e) {
            throw new IssueTrackingException(
                    "Project could not be marshalled and printed.", e);
        }
    }

    /**
     * Saves a {@link Project} formated as XML to the file-system.
     *
     * @param project the {@link Project} to be saved.
     * @param path    the path in the file-system, where the {@link Project} should
     *                be saved.
     * @throws IssueTrackingException if path is <code>null</code> or empty or project is
     *                                <code>null</code> and if there is an Exception during
     *                                marshalling
     */
    void save(Project project, String path) throws IssueTrackingException {
    	ValidationHelper.assertNotEmpty(path);

        try {
            marshaller.marshal(project, Paths.get(path).toFile());
        } catch (JAXBException e) {
            throw new IssueTrackingException(
                    "Project could not be marshalled and saved in <" + path
                            + ">.", e);
        }
    }
}
